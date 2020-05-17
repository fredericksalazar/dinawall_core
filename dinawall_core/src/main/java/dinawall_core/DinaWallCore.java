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
import dinawall_core.desktop.Wallpaper;

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
    
    private DinaWallCore(){
        init();
    }
    
    protected void init(){
        try{
            dinaWall_util = DinaWallUtil.getInstance();
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
    
    public static DinaWallCore getInstance(){
        if(dinaWall_core == null){
            dinaWall_core = new DinaWallCore();
        }
        
        return dinaWall_core;
    }
    
}
