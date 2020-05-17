/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinawall_core.desktop;

import java.io.IOException;

/**
 * this class is a KDE desktop enviroment, this set the wallpaper in the desktop
 * aditionally hae properties of the enviroment
 * 
 * @author frederick
 */
public class DesktopKDE extends Desktop{
    
    private final int width;
    private final int height;
    private final String enviroment;
    private final String operativeSystem;
    private Wallpaper wallpaper;
    private String bash_kde_setup_wallpaper;
    
        
    private ProcessBuilder pb;
    private Process bash;

    public DesktopKDE(int width, int height, String enviroment, String operativeSystem) {
        this.width = width;
        this.height = height;
        this.enviroment = enviroment;
        this.operativeSystem = operativeSystem;
        
        this.pb = new ProcessBuilder();
    }  
    

    public DesktopKDE(int width, int height, String enviroment, String operativeSystem, Wallpaper wallpaper) {
        this.width = width;
        this.height = height;
        this.enviroment = enviroment;
        this.operativeSystem = operativeSystem;
        this.wallpaper = wallpaper;
        
        this.pb = new ProcessBuilder();
    }
        

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public String getEnviroment() {
        return this.enviroment;
    }

    @Override
    public String getOperativeSystem() {
        return this.operativeSystem;
    }

    /**
     * This method execute a sh script that change a wallpaper in kde
     * enviroment.
     * 
     * @param wallpaper
     * @return 
     */
    
    @Override
    public boolean setWallpaper(Wallpaper wallpaper) {
        
        try{     
            this.wallpaper = wallpaper;
            
            pb.command("qdbus",
                       "org.kde.plasmashell",
                       "/PlasmaShell",
                       "org.kde.PlasmaShell.evaluateScript",get_bash_script_setup_kde_wallaper())
                    .start();
        }catch(IOException e){
            e.addSuppressed(e);
        }
        return true;
    }

    @Override
    public Wallpaper getWallpaper() {
        return this.wallpaper;
    }
    
    private String get_bash_script_setup_kde_wallaper(){
               
        this.bash_kde_setup_wallpaper = "var allDesktops = desktops();for (i=0;i<allDesktops.length;i++) {d = allDesktops[i];d.wallpaperPlugin = 'org.kde.image';d.currentConfigGroup = Array('Wallpaper', 'org.kde.image', 'General');d.writeConfig('Image', 'file:"+wallpaper.getUrl()+"')}";
        System.out.println(bash_kde_setup_wallpaper);
        return this.bash_kde_setup_wallpaper;
    }

    @Override
    public String toString() {
        return "DesktopKDE{" + "width=" + width + ", height=" + height + ", enviroment=" + enviroment + ", operativeSystem=" + operativeSystem + ", wallpaper=" + wallpaper + '}';
    }
    
}
