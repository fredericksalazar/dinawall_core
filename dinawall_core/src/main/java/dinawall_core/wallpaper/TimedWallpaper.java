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

package dinawall_core.wallpaper;

import java.io.Serializable;

/**
 * this class create a objects timedwallpapers which extends of Wallpaper, 
 * the difference between a wallpaper object and a timedwallpaper object is 
 * that the latter in addition to the wallpaper information has its execution 
 * time
 * 
 * @author frederick
 */


public class TimedWallpaper extends Wallpaper implements Serializable{
    
    private static final long serialVersionUID = 2L;
    
    private int hour;
    private int minute;
    private String timed;

    public TimedWallpaper(){
        
    }
    
    public TimedWallpaper(String name, String timed){
        super(name);
        this.timed = timed;
    }
        
    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getTimed() {
        return timed;
    }

    public void setTimed(String timed) {
        this.timed = timed;
    }
    
    @Override
    public String toString() {
        return "TimedWallpaper{" + "nombre=" + this.getName() + ",\n"
                                 + "url=" + this.getUrl()+",\n"
                                 + "extension="+this.getExtension()+",\n"
                                 + "timed="+this.getTimed()+"}";
    }
    
}
