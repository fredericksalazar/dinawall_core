/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinawall_core.desktop;

/**
 * create a desktop screen object of the machine
 * 
 * @author frederick
 */

public abstract class Desktop {
    
    public abstract int getWidth();

    public abstract int getHeight();

    public abstract String getEnviroment();

    public abstract String getOperativeSystem();
    
    public abstract boolean setWallpaper(Wallpaper wallpaper);
    
    public abstract Wallpaper getWallpaper();

    @Override
    public String toString() {
        return "Desktop{" + '}';
    }    
    
}
