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

import java.awt.Dimension;
import java.awt.HeadlessException;
import static java.awt.Toolkit.getDefaultToolkit;


/**
 * this class has general utilities that are used in the dinawall ecosystem
 * 
 * @author frederick
 */

public class DinaWallUtil {
    
    private String version_lib_core;
    
    private static DinaWallUtil util;
    
    private String os;
    private String os_arch;
    private String os_version;
    private String user_name;
    private String home;
    private String desktop;
        
    private Dimension screen_dimension;
    
    private int width_screen;
    private int height_screen;
    private String separator;
    
    private DinaWallUtil() {
      init();  
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
            
            version_lib_core = "1.0";
            
            getDimensionScreen();
            
        }catch(Exception e){
            
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
    
    
    public static DinaWallUtil getInstance() {
        if (util == null){
            util = new DinaWallUtil();
        }        
        return util;
    }
    
}
