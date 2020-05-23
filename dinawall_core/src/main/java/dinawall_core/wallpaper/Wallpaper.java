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
 * this class is wallpaper object 
 * 
 * @author frederick
 */

public class Wallpaper implements Serializable{
    
    private static final long serialVersionUID = 44L;
    
    private String name;
    private String url; 
    private String extension;
    
    public Wallpaper(){
        
    }
    
    public Wallpaper(String name){
        this.name = name;
    }

    public Wallpaper(String name, String url, String extension) {
        this.name = name;
        this.url = url;
        this.extension = extension;
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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
    
    @Override
    public String toString() {
        return "Wallpaper{" + "name=" + name + ", url=" + url + ", extension=" + extension + '}';
    }   
    
}
