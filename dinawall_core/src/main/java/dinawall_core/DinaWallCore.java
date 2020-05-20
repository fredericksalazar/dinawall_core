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

import dinawall_core.desktop.Desktop;
import dinawall_core.desktop.DesktopKDE;
import dinawall_core.wallpaper.DinaWallpaper;
import dinawall_core.wallpaper.Wallpaper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import nicon.notify.core.Notification;
import org.apache.commons.io.FileUtils;


/**
 * this class is the API core of dinawall app, in this class is defined
 * the central functionalities to set wallpapers in linux distributions
 * with support to the kde, gnome and cinnamon desktop enviroments
 * 
 * @author frederick
 */

public final class DinaWallCore {
    
    private static DinaWallCore dinaWall_core;
    
    private DinaWallUtil dinaWall_util;
    private Desktop desktop_enviroment;
    private Wallpaper wallpaper;
    private File json_file;
    private File json_directory;
    
    private ArrayList list_dinaWall;
    
    private DinaWallCore(){
        init();
    }
    
    protected void init(){
        try{
            dinaWall_util = DinaWallUtil.getInstance();
            list_dinaWall = new ArrayList();
            setDesktopEnviroment();
        }catch(Exception e){
            
        }
    }
    
    /**
     * This method create a Desktop Object based in then configuration
     * of the operative system
     */
    
    private void setDesktopEnviroment(){
        try{
            System.out.println("dinawall_core.DinaWallCore.setDesktopEnviroment()");
            dinaWall_util.print_properties();
                        
            if("LINUX".equals(dinaWall_util.getOs().toUpperCase())){
                                
                //Create a KDE Desktop enviroment
                if("KDE".equals(dinaWall_util.getDesktop().toUpperCase())){
                    desktop_enviroment = new DesktopKDE(dinaWall_util.getWidth_screen(), 
                                                        dinaWall_util.getHeight_screen(), 
                                                        dinaWall_util.getDesktop(),
                                                        dinaWall_util.getOs());
                    System.out.println(desktop_enviroment.toString());
                    
                }
            }
        }catch(Exception e){
        }
    }
    
    
    /**
     * This method create a Wallpaper object and set this wallpaper
     * in the desktop enviroment object
     * 
     * @param image_url 
     */
    
    public void setWallpaperDesktop(String image_url){
        String[] tokens_file;
        String[] tokens_name;
        
        String name_file;        
        
        if(image_url != null){
          tokens_file =  image_url.split(dinaWall_util.getSeparator());
          name_file = tokens_file[tokens_file.length-1];
          
          System.out.println(name_file);
          
          tokens_name = name_file.split("\\.");
                    
          wallpaper = new Wallpaper(tokens_name[0], image_url,tokens_name[tokens_name.length-1]);
          System.out.println(wallpaper.toString());
          desktop_enviroment.setWallpaper(wallpaper);
        }
    }
    
    
    /**
     * this method is used to install a dinawallpaper, with params have a 
     * path to json file selected
     * 
     * @param json_path
     */
    
    public void install_dinawallpaper(String json_path){
        
        if(json_path != null){
            json_file = new File(json_path);
            if(json_file.exists()){
                json_directory = json_file.getParentFile();                
                
                if(json_directory.isDirectory()){
                    
                    try {
                        System.out.println("json parent directory : "+json_directory.getAbsolutePath());
                        
                        //copy the directory of dinawallpaper to installed directory
                        
                        FileUtils.copyDirectoryToDirectory(json_directory.getAbsoluteFile(),
                                                           dinaWall_util.getInstalledDirectory());
                       
                        list_dinaWall.add(dinaWall_util.install_din_object(
                                                        new File(dinaWall_util.getInstalledDirectory()+
                                                                 "/"+json_directory.getName()+"/"+
                                                                 json_file.getName())));
                    } catch (IOException ex) {
                        Logger.getLogger(DinaWallCore.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
    }
    
    /**
     * This method return a list of danwallpapers installed in dinawall, this
     * objects are a .din files of the config tmp dir.
     * 
     * @return 
     */
    
    public ArrayList get_dinawall_installed(){
        Collection<File> listFiles  = null;
        DinaWallpaper dina_wallpaper;
        
        if(list_dinaWall == null || list_dinaWall.isEmpty()){
            try{
                listFiles = FileUtils.listFiles(dinaWall_util.getConfig_dir(),new String[]{"din"}, true);

                    System.out.println("Total din objects : "+listFiles.size());

                   for(File file : listFiles){
                       dina_wallpaper = dinaWall_util.getDinaWallpaperOfDin(file);
                       list_dinaWall.add(dina_wallpaper);
                   }

            }catch(Exception e){
                e.printStackTrace();
            }
            finally{
                listFiles = null;            
            }
        }
                
        return this.list_dinaWall;
    }
    
    /**
     * this method return a current dinawallpaper setted in the desktop, this
     * is a current.din file
     * 
     * @return DinaWallpaper
     */
    
    public DinaWallpaper getCurrentDinaWallpaper(){
        DinaWallpaper current_dinawall = null;
        
        try{
            
            Collection<File> list_files = FileUtils.listFiles(dinaWall_util.getConfig_dir(),new String[]{"din"}, true);
            
            for(File current : list_files){
                if(current.getName().equals("current.din")){
                    current_dinawall = dinaWall_util.getDinaWallpaperOfDin(current);
                }
            }
            
            list_files = null;
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return current_dinawall;
    }
    
    /**
     * This method set a dinamic wallpaper has current wallpaper
     * 
     * @param dina_wall 
     */
    public void setCurrentDinaWallpaper(DinaWallpaper dina_wall){
        try{
            FileUtils.copyFile(new File(dinaWall_util.getConfig_dir()+"/"+dina_wall.getName()+".din"), 
                               new File(dinaWall_util.getConfig_dir()+"/current.din"));
        }catch(Exception e){
            Notification.show("DinaWall", "A ERROR Has ocurred setting a current dinawallpaper", Notification.NICON_DARK_THEME, Notification.ERROR_MESSAGE);
        }
    }
    
    public static DinaWallCore getInstance(){
        if(dinaWall_core == null){
            dinaWall_core = new DinaWallCore();
        }
        
        return dinaWall_core;
    }
    
}
