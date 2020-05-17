/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinawall_core;

import java.awt.Dimension;
import static java.awt.Toolkit.getDefaultToolkit;


/**
 *
 * @author frederick
 */

public class DinaWallUtil {
    
    private static DinaWallUtil util;
    
    private String os;
    private String os_arch;
    private String os_version;
    private String user_name;
    private String home;
    private String desktop;
    
    private String kde_bash_setup;
    
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
        }catch(Exception e){
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
    
    
    public static DinaWallUtil getInstance() {
        if (util == null){
            util = new DinaWallUtil();
        }        
        return util;
    }
    
}
