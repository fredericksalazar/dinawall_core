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

package dinawall_core.init;

/**
 *
 * @author frederick
 */
public class DinaWallDaemon {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                
        dinawall_core.DinaWallCore.getInstance().init_dinawall_daemon();
        
    }
    
}
