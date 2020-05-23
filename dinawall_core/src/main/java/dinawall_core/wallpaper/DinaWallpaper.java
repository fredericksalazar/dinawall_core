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
import java.util.List;

/**
 * This class create a DinaWallpaper object, this object have a list of @Wallpaper
 * object, this wallpapers are setted in the desktop enviroment
 * 
 * @author frederick
 */

public class DinaWallpaper implements Serializable{
    
    private static final long serialVersionUID = 2L;
    
    private String name;
    private String url;
    private String fecha;
    private String autor;
    private String email;
    private String license;
    private String preview;
        
    private List<TimedWallpaper> timedWallpapers;


    public DinaWallpaper(String name, String url, String fecha, String autor, 
                         String email, String license, String preview,
                         List<TimedWallpaper> timedWallpapers) {
        this.name = name;
        this.url = url;
        this.fecha = fecha;
        this.autor = autor;
        this.email = email;
        this.license = license;
        this.preview = preview;
        this.timedWallpapers = timedWallpapers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public List<TimedWallpaper> getTimedWallpapers() {
        return timedWallpapers;
    }

    public void setTimedWallpapers(List<TimedWallpaper> timedWallpapers) {
        this.timedWallpapers = timedWallpapers;
    }

    @Override
    public String toString() {
        return "DinaWallpaper{\n" + 
                              " name = " + name + ",\n"
                            + " url = " + url + ",\n"
                            + " fecha=" + fecha + ",\n"
                            + " autor = " + autor + ",\n"
                            + " email = " + email + ",\n"
                            + " license = " + license + ",\n"
                            + " preview = " + preview + "\n}";
    }
    
}
