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

import dinawall_core.wallpaper.TimedWallpaper;
import it.sauronsoftware.cron4j.Task;
import it.sauronsoftware.cron4j.TaskExecutionContext;

/**
 * This classs extends of cron4j Task class, create a object that execute a
 * change wallpaper in a time defined 
 * 
 * @author frederick
 */

public class SetTimedWallpaperTask extends Task{
    
    private TimedWallpaper timed_wallpaper;
    private DinaWallCore dinawall_core;

    public SetTimedWallpaperTask(TimedWallpaper timed_wallpaper, DinaWallCore dinawall_core) {
        this.timed_wallpaper = timed_wallpaper;
        this.dinawall_core = dinawall_core;
    }

    public TimedWallpaper getTimed_wallpaper() {
        return timed_wallpaper;
    }

    public void setTimed_wallpaper(TimedWallpaper timed_wallpaper) {
        this.timed_wallpaper = timed_wallpaper;
    }

    public DinaWallCore getDinawall_core() {
        return dinawall_core;
    }

    public void setDinawall_core(DinaWallCore dinawall_core) {
        this.dinawall_core = dinawall_core;
    }
    
    @Override
    public void execute(TaskExecutionContext tec) throws RuntimeException {
        try{
            dinawall_core.setWallpaperDesktop(timed_wallpaper.getUrl());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
