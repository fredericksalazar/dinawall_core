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

import java.io.IOException;

public class DesktopGnome extends Desktop{

    private final int width;
    private final int height;
    private final String enviroment;
    private final String operativeSystem;
    private final ProcessBuilder pb;

    private Wallpaper wallpaper;
    private String bash_kde_setup_wallpaper;

    public DesktopGnome(int width,
                        int height,
                        String enviroment,
                        String operativeSystem){

        this.width = width;
        this.height = height;
        this.enviroment = enviroment;
        this.operativeSystem = operativeSystem;
        this.pb = new ProcessBuilder();

    }

    public DesktopGnome(int width,
                        int height,
                        String enviroment,
                        String operativeSystem,
                        Wallpaper wallpaper) {

        this.width = width;
        this.height = height;
        this.enviroment = enviroment;
        this.operativeSystem = operativeSystem;
        this.wallpaper = wallpaper;
        this.pb = new ProcessBuilder();
    }
    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public String getEnviroment() {
        return null;
    }

    @Override
    public String getOperativeSystem() {
        return null;
    }

    @Override
    public Wallpaper getWallpaper() {
        return null;
    }

    /**
     * This method execute a sh script that change a wallpaper in kde
     * enviroment.
     *
     * @param wallpaper
     * @return
     */

    @Override
    synchronized public boolean setWallpaper(Wallpaper wallpaper) {

        try{
            this.wallpaper = wallpaper;

            pb.command("gsettings",
                       "set",
                       "org.gnome.desktop.background",
                       "picture-uri",
                       "file:///"+this.wallpaper.getUrl())
                    .start();

            pb.command("gsettings",
                       "set",
                       "org.gnome.desktop.background",
                       "picture-uri-dark",
                       "file:///"+this.wallpaper.getUrl())
                    .start();

        }catch(Exception e){
            e.addSuppressed(e);
        }
        return true;
    }

    @Override
    public String toString() {
        return "GnomeDesktop{" + "width=" + width + ", height=" + height + ", enviroment=" + enviroment + ", operativeSystem=" + operativeSystem + ", wallpaper=" + wallpaper + '}';
    }
}
