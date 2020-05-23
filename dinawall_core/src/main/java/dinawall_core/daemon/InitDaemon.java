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

package dinawall_core.daemon;

import dinawall_core.DinaWallCore;
import dinawall_core.wallpaper.DinaWallpaper;
import dinawall_core.wallpaper.TimedWallpaper;
import it.sauronsoftware.cron4j.InvalidPatternException;
import it.sauronsoftware.cron4j.Scheduler;
import nicon.notify.core.Notification;

/**
 *
 * @author frederick
 */
public class InitDaemon {
    
    private DinaWallpaper current;
    private final DinaWallCore dinawall_core;
    private Scheduler dina_sheduled;

    public InitDaemon() {
        dinawall_core = DinaWallCore.getInstance();
    }
    
    
    /**
     * this method star a daemon of dnamicwallpaper to change dynamically the
     * wallpaper based in the hour
     * 
     */
    
    public void init_dinawall_daemon(){
        SetTimedWallpaperTask dinawall_task;
        
        try{
            current = dinawall_core.getCurrentDinaWallpaper();
            
            if(current != null){
                System.out.println("initializing a dinawall_daemon ....\n");
                System.out.println("current dinawallpaper is : \n"
                        + current.toString());
                
                for(TimedWallpaper timed_wallpaper : current.getTimedWallpapers()){
                    
                    System.out.println("ajustando wallpaper a cron ->"+timed_wallpaper.getUrl()+ " in -> "+timed_wallpaper.getTimed());
                    
                    dina_sheduled  = new Scheduler();
                    dinawall_task = new SetTimedWallpaperTask(timed_wallpaper, dinawall_core);
                        
                    dina_sheduled.schedule(String.valueOf(timed_wallpaper.getMinute())+" "+String.valueOf(timed_wallpaper.getHour())+" * * *", 
                                           dinawall_task);
                    dina_sheduled.start();
                }
            }else{
                Notification.show("DinaWall", "Dont have a Dynamic Wallpaper current, please select a dinawall in the panel", Notification.NICON_DARK_THEME, Notification.INFO_ICON);
            }
            
        }catch(InvalidPatternException | IllegalStateException e){
            e.printStackTrace();
        }
    }
    
}
