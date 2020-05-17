/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinawall_core.desktop;

/**
 * this class is wallpaper object 
 * 
 * @author frederick
 */
public class Wallpaper {
    
    private String name;
    private String url; 
    private String extension;

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
