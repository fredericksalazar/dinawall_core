/**
 * Copyright(C) Frederick Salazar Sanchez <fredefass01@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package dinawall_core;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import dinawall_core.wallpaper.DinaWallpaper;
import dinawall_core.wallpaper.TimedWallpaper;
import java.awt.Dimension;
import java.awt.HeadlessException;
import static java.awt.Toolkit.getDefaultToolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import nicon.notify.core.Notification;


/**
 * this class has general utilities that are used in the dinawall ecosystem
 * 
 * @author frederick
 */

public class DinaWallUtil {
        
    private File dinawall_dir;
    private File config_dir;
    private File installed_dir;
    private File plasma_script_file;
        
    private static DinaWallUtil util;
    
    private String os;
    private String os_arch;
    private String os_version;
    private String user_name;
    private String home;
    private String desktop;
    private String version_lib_core;    
        
    private Dimension screen_dimension;
    
    private int width_screen;
    private int height_screen;
    private String separator;
    private Gson json_file;
    private Reader reader;
    private File din_file;
        
    private DinaWallUtil() {
      init();  
      checkIntegrity();
    }
    
    private void init(){
        try{
            // initialize the basic objects of the app and properties of the system
            
            os = System.getProperty("os.name");
            os_arch = System.getProperty("os.arch");
            os_version = System.getProperty("os.version");
            home = System.getProperty("user.home");
            user_name = System.getProperty("user.name");
            desktop = System.getenv("XDG_CURRENT_DESKTOP");
            separator = System.getProperty("file.separator");
            
            version_lib_core = "0.8";
            
            dinawall_dir = new File(this.getHome()+"/.dinawall");
            config_dir = new File(dinawall_dir.getAbsoluteFile()+"/config");
            installed_dir = new File(home+"/.dinawall/installed");            
                        
            getDimensionScreen();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * This method get a dimension screen 
     */
    
    private void getDimensionScreen(){
        try{
           screen_dimension = getDefaultToolkit(). getScreenSize();
           width_screen = (int) screen_dimension.getWidth();
           height_screen = (int) screen_dimension.getHeight();
        }catch(HeadlessException e){
            e.printStackTrace();
        }
    }
    
    
    /**
     * this method evaluate the integrity of directories of dinawall, if not
     * exists .dinawall directory then invoke to createDinaWallConfigDir()
     * method.
     * 
     */
    
    private void checkIntegrity(){
        try{
            if(!dinawall_dir.exists()){
                createDinaWallConfigDir();
            }else{
                System.out.println("dinawall config directory exists ...");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * This method create a .dinawall directory config into the home directory
     * user.
     */
    
    private void createDinaWallConfigDir(){
                
        if(!dinawall_dir.exists()){
            if(dinawall_dir.mkdir()){
                try {
                    installed_dir.mkdir();
                    config_dir.mkdir();                    
                } catch (Exception ex) {
                    Logger.getLogger(DinaWallUtil.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        }
        System.out.println("the config dir has been created ...");
    }
    
    
    /**
     * This method read a json object and transform this in a java
     * serializable object that contains a configuration of the 
     * dinamic wallappers to set in the desktop
     * 
     * @param json 
     * @return  
     * @throws java.io.IOException 
     */
    
    public DinaWallpaper install_din_object(File json) throws IOException{
        DinaWallpaper dina_wallpaper = null;        
                        
        try{
            System.out.println("load a json file installer ... "+json.getAbsolutePath());
            
            if(json.exists()){
                json_file = new Gson();
                reader = Files.newBufferedReader(Paths.get(json.getAbsolutePath()));

                dina_wallpaper = json_file.fromJson(reader, DinaWallpaper.class);
                
                if(dina_wallpaper != null && dina_wallpaper.getTimedWallpapers()!=null){
                    
                    dina_wallpaper.setPreview(json.getParent()+"/images/"+dina_wallpaper.getPreview());
                    
                    System.err.println("Ruta al preview -> "+dina_wallpaper.getPreview());
                    
                    din_file = new File(this.config_dir+"/"+dina_wallpaper.getName()+".din");
                    
                    dina_wallpaper.getTimedWallpapers().forEach((TimedWallpaper timedWallpaper) -> {
                        
                        if(new File(json.getParent()+"/images/"+timedWallpaper.getName()).exists()){
                            timedWallpaper.setUrl(json.getParent()+"/images/"+timedWallpaper.getName());
                            timedWallpaper.setExtension(timedWallpaper.getName().split("\\.")[1]);
                            
                            int hour = Integer.parseInt(timedWallpaper.getTimed().split(":")[0]);
                            int minute = Integer.parseInt(timedWallpaper.getTimed().split(":")[1]);
                            
                            if(hour >= 0 && hour <= 24){
                                timedWallpaper.setHour(hour);
                                
                                if(minute >= 0 && minute < 60){
                                    timedWallpaper.setMinute(minute);
                                }else{
                                   Notification.show("DinaWall", "A minute value in the json install file is very BAD, the dynamic wallpaper is not installed",
                                        Notification.NICON_DARK_THEME,
                                        Notification.ERROR_MESSAGE); 
                                }
                            }else{
                                Notification.show("DinaWall", "A hour value in the json install file is very BAD, the dynamic wallpaper is not installed",
                                        Notification.NICON_DARK_THEME,
                                        Notification.ERROR_MESSAGE);
                            }
                        }else{
                            Notification.show("DinaWall", "The json installer file has a ERROR, the dynamic wallpaper is not installed",
                                    Notification.NICON_DARK_THEME,
                                    Notification.ERROR_MESSAGE);
                        }
                    });
                    
                    if(din_file.exists()){
                        din_file.delete();
                    }else{
                       try (ObjectOutputStream dina_output = new ObjectOutputStream(new FileOutputStream(this.config_dir+"/"+dina_wallpaper.getName()+".din"))) {
                            dina_output.writeObject(dina_wallpaper);
                            dina_output.close(); 
                        } 
                    }
                }
            }
            
            Notification.show("DinaWall", "The new Dynamic Wallpaper "+dina_wallpaper.getName()+" is installed", Notification.NICON_DARK_THEME, Notification.OK_MESSAGE);
                        
        }catch(JsonIOException | JsonSyntaxException | IOException e){
            Notification.show("DinaWall ERROR", "A Error has ocurred, dynamic wallpaper is not installed", Notification.NICON_DARK_THEME, Notification.ERROR_MESSAGE);
        }finally{
            dina_wallpaper = null;
            reader.close();
            json_file = null;
            din_file = null;
        }
        
        return dina_wallpaper;
    }
    
    
    /**
     * This method get and return a DinaWallpaper object from a .din file
     * in the file system
     * 
     * @param din
     * @return @DinaWallpaper
     */
    
    public DinaWallpaper getDinaWallpaperOfDin(File din){
        ObjectInputStream in;
        DinaWallpaper dina_wallpaper = null;
        
        try{
            if(din != null && din.isFile()){
                
                System.out.println("Leyendo archivo din de -> "+din.getAbsolutePath());
                
                in = new ObjectInputStream(new FileInputStream(din));
                dina_wallpaper = (DinaWallpaper) in.readObject();
                in.close(); 
                
            }
        }catch(IOException | ClassNotFoundException e){
            Notification.show("DinaWall", "Cant not read a .din object, ERROR has ocurred", Notification.NICON_DARK_THEME, Notification.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
        return dina_wallpaper;
    }
        
    public File getInstalledDirectory(){
        return installed_dir;
    }

    public File getDinawall_dir() {
        return dinawall_dir;
    }

    public File getConfig_dir() {
        return config_dir;
    }
    
    public String getOs() {
        return os;
    }

    public String getOs_arch() {
        return os_arch;
    }

    public String getOs_version() {
        return os_version;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getHome() {
        return home;
    }

    public String getDesktop() {
        return desktop;
    }

    public static DinaWallUtil getUtil() {
        return util;
    }

    public int getHeight_screen() {
        return height_screen;
    }

    public int getWidth_screen() {
        return width_screen;
    }

    public String getSeparator() {
        return separator;
    }

    public File getPlasma_script_file() {
        return plasma_script_file;
    }
          
    public void print_properties(){
        System.out.println("system properties:\n"
                         + " os : "+this.os+"\n"
                         + " os_arch : "+this.os_arch+"\n"
                         + " os_version : "+this.os_version+"\n"
                         + " desktop : "+ this.desktop+"\n"
                         + " home : "+this.home+"\n"
                         + " user_name : "+this.user_name);
    }
    
    public String version_lib_core(){
        return this.version_lib_core;
    }
    
    
    synchronized public static DinaWallUtil getInstance() {
        if (util == null){
            util = new DinaWallUtil();
        }        
        return util;
    }
    
}
