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


package dinawall_core.desktop;

import dinawall_core.wallpaper.Wallpaper;

/**
 * this class is a object desktop for macOS system, this object
 * set the wallpaper in the macOS
 * 
 * @author fredericksalazar
 */

public class DesktopMacOS extends Desktop{
    
    private final int width;
    private final int height;
    private final String enviroment;
    private final String operativeSystem;
    private final ProcessBuilder pb;
    
    private Wallpaper wallpaper;
    
    
    public DesktopMacOS(int width, int height, String enviroment, String operativeSystem) {
        this.width = width;
        this.height = height;
        this.enviroment = enviroment;
        this.operativeSystem = operativeSystem;
        this.pb = new ProcessBuilder();
    }
    
    public DesktopMacOS(int width, int height, String enviroment, String operativeSystem, Wallpaper wallpaper) {
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
     * this method set the wallpaper into a desktop enviroment for macOS operative
     * system
     * 
     * @param wallpaper
     * @return 
     */
    
    @Override
    synchronized public boolean setWallpaper(Wallpaper wallpaper) {
       try{
           this.wallpaper = wallpaper;
                        
            pb.command("osascript",
                       "-e","tell application \"Finder\"",
                       "-e","set desktop picture to POSIX file \""+this.wallpaper.getUrl()+"\"",
                       "-e","end tell")
                    .start();
            System.out.println(pb.toString());
       }catch(Exception e){
           System.out.println("dinawall_core.desktop.DesktopMacOS.setWallpaper()");
       }
       
       return true;
    }

    @Override
    public Wallpaper getWallpaper() {
        return this.wallpaper;
    }
    
    @Override
    public String toString() {
        return "macOS{" + "width=" + width + ", height=" + height + ", enviroment=" + enviroment + ", operativeSystem=" + operativeSystem + ", wallpaper=" + wallpaper + '}';
    }
    
}
