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
import dinawall_core.desktop.DesktopMacOS;
import dinawall_core.wallpaper.DinaWallpaper;
import dinawall_core.wallpaper.TimedWallpaper;
import dinawall_core.wallpaper.Wallpaper;
import it.sauronsoftware.cron4j.InvalidPatternException;
import it.sauronsoftware.cron4j.Scheduler;
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
    private DinaWallpaper current_dinawall;
    
    private boolean isSupported = false;
    
    private Scheduler dinawall_daemon;
    
    private File json_file;
    private File json_directory;
    
    private ArrayList<DinaWallpaper> list_dinaWall;
    private Collection<File> list_din_files;
    
    
    private DinaWallCore(){
        init();
    }
    
    protected void init(){
        try{
            dinaWall_util = DinaWallUtil.getInstance();
            list_dinaWall = this.get_dinawall_installed();
            dinawall_daemon = new Scheduler();
            setDesktopEnviroment();
        }catch(Exception e){
            System.err.println("Error iniciando dinawall_core -> "+e);
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
            
            String os = dinaWall_util.getOs();
            
            switch (os.toUpperCase()){
                case "LINUX":
                    setLinuxDesktopEnviroment();
                    break;
                case "MAC OS X":
                    setMacOSDesktopEnviroment();
                    break;
            }
            
            if(!this.isSupported){
                Notification.show("DinaWall", "Your System is NOT Supported", Notification.NICON_DARK_THEME,Notification.ERROR_MESSAGE);
            }
        }catch(Exception e){
            System.err.println("Error ajustando entorno de escritorio -> "+e);
        }
    }
    
    
    /**
     * This method create a desktop enviroment object based in the desktop
     * enviroment kde, gnome, cinnamon etc.
     * 
     */
    
    private void setLinuxDesktopEnviroment(){
        //Create a KDE Desktop enviroment
        if("KDE".equals(dinaWall_util.getDesktop().toUpperCase())){
            desktop_enviroment = new DesktopKDE(dinaWall_util.getWidth_screen(), 
                                                dinaWall_util.getHeight_screen(), 
                                                dinaWall_util.getDesktop(),
                                                dinaWall_util.getOs());
            
             System.err.println("the Linux KDE desktop enviroment has been setted ...");
             
             this.isSupported = true;

        }
    }
    
    
    /**
     * this method create a desktop enviroment object to the macOS system.
     */
    
    private void setMacOSDesktopEnviroment(){
        desktop_enviroment = new DesktopMacOS(dinaWall_util.getWidth_screen(),
                                              dinaWall_util.getHeight_screen(),
                                              "macOS",
                                              dinaWall_util.getOs());
        
        System.err.println("the macOS desktop enviroment has been setted ...");
        
        this.isSupported = true;
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
        
        try{
            if(image_url != null){
                tokens_file =  image_url.split(dinaWall_util.getSeparator());
                name_file = tokens_file[tokens_file.length-1];

                tokens_name = name_file.split("\\.");

                wallpaper = new Wallpaper(tokens_name[0], image_url,tokens_name[tokens_name.length-1]);
                System.out.println(wallpaper.toString());

                System.out.println("El wallpaper será ajustado en el entorno de escritorio ...");

                desktop_enviroment.setWallpaper(wallpaper);

                wallpaper = null;
              }
        }catch(Exception e){
            System.err.println("Error ajustando wallpaper -> "+e);
        }
    }
    
    /**
     * This method set a wallpaper in the desktop enviroment usgin a @Wallpaper
     * object 
     * 
     * @param wallpaper 
     */
    
    public void setWallpaperDesktop(Wallpaper wallpaper){
        this.desktop_enviroment.setWallpaper(wallpaper);
    }
        
    
    /**
     * This method is used to install a dinawallpaper, with params have a 
     * path to json file selected
     * 
     * @param json_path
     * @return 
     */
    
    public DinaWallpaper install_dinawallpaper(String json_path){
        
        DinaWallpaper installed = null;
        
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
                       
                        installed = dinaWall_util.install_din_object(new File(dinaWall_util.getInstalledDirectory()+
                                                                    "/"+json_directory.getName()+"/"+
                                                                    json_file.getName()));
                        
                        if(installed == null){
                            FileUtils.deleteDirectory(new File(dinaWall_util.getInstalledDirectory()+"/"+json_directory.getName()));
                        }else{
                          list_dinaWall.add(installed);  
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(DinaWallCore.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
        return installed;
    }
    
    /**
     * This method return a list of danwallpapers installed in dinawall, this
     * objects are a .din files of the config tmp dir.
     * 
     * @return 
     */
    
    public ArrayList<DinaWallpaper> get_dinawall_installed(){
        Collection<File> listFiles;
        DinaWallpaper dina_wallpaper;
        
        System.out.println("dinawall_core.DinaWallCore.get_dinawall_installed()");
        
        if(list_dinaWall == null || list_dinaWall.isEmpty()){
            
            list_dinaWall = new ArrayList();
            
            try{
                listFiles = FileUtils.listFiles(dinaWall_util.getConfig_dir(),new String[]{"din"}, true);
                
                System.out.println("Total archivos din : "+listFiles.size());
                
                   for(File file : listFiles){
                       if(!"current.din".equals(file.getName())){
                            dina_wallpaper = dinaWall_util.getDinaWallpaperOfDin(file);
                            list_dinaWall.add(dina_wallpaper);
                       }
                   }

            }catch(Exception e){
                System.err.println("Error obteniendo lista de wallpapers -> "+e);
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
        current_dinawall = null;
        
        try{
            list_din_files = FileUtils.listFiles(dinaWall_util.getConfig_dir(),new String[]{"din"}, true);
            
            for(File current : list_din_files){
                if(current.getName().toLowerCase().equals("current.din")){
                    current_dinawall = dinaWall_util.getDinaWallpaperOfDin(current);                    
                }
            }
                        
        }catch(Exception e){
            System.err.println("Error obteniendo current wallpaper -> "+e);
        } finally{
            list_din_files = null;
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
            init_dinawall_daemon();
        }catch(IOException e){
            Notification.show("DinaWall", "A ERROR Has ocurred setting a current dinawallpaper", Notification.NICON_DARK_THEME, Notification.ERROR_MESSAGE);
        }
    }
    
    
    /**
     * This method delete a dinawallpaper of the config dir and installed dir
     * @param dina_wall 
     */
    public void deleteDinaWallpaper(DinaWallpaper dina_wall){
        try{
            File file = new File(dinaWall_util.getConfig_dir()+"/"+dina_wall.getName()+".din");
            file.delete();
            File directory = new File(dinaWall_util.getInstalledDirectory()+"/"+dina_wall.getName());
            directory.delete();
            file = null;
            directory = null;
        }catch(Exception e){
            System.err.println("ERROR in deleteDinaWallpaper -> "+e);
        }
    }
    
    /**
     * this method star a daemon of dnamicwallpaper to change dynamically the
     * wallpaper based in the hour
     * 
     */
    
    public void init_dinawall_daemon(){
        SetTimedWallpaperTask dinawall_task;
                
        try{
            getCurrentDinaWallpaper();
            
            if(dinawall_daemon.isStarted()){
                dinawall_daemon.stop();
            }
            
            if(this.current_dinawall != null){                
                for(TimedWallpaper timed_wallpaper : this.current_dinawall.getTimedWallpapers()){                                       
                    dinawall_task = new SetTimedWallpaperTask(timed_wallpaper, this);                        
                    dinawall_daemon.schedule(String.valueOf(timed_wallpaper.getMinute())+" "+String.valueOf(timed_wallpaper.getHour())+" * * *", 
                                           dinawall_task);                    
                }
            }else{
                Notification.show("DinaWall", "Dont have a Dynamic Wallpaper current, please select a dinawall in the panel", Notification.NICON_DARK_THEME, Notification.INFO_ICON);
            }
            
            dinawall_daemon.start();
            
            System.out.println("The daemon has started ...");
            
        }catch(InvalidPatternException | IllegalStateException e){
            System.err.println("Error iniciando demonio-> "+e);
        }
    }
    
    public void stop_daemon(){
        this.dinawall_daemon.stop();
    }
    
    public void start_daemon(){
        this.dinawall_daemon.start();
    }
    
    synchronized public static DinaWallCore getInstance(){
        if(dinaWall_core == null){
            dinaWall_core = new DinaWallCore();
        }
        
        return dinaWall_core;
    }
    
}
