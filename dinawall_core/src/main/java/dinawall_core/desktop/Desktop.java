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
