/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinawall_core.wallpaper;

import java.io.Serializable;
import java.util.List;

/**
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
    
    public DinaWallpaper(){
        
    }

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
        return "DinaWallpaper{" + "name=" + name + ", url=" + url + ", fecha=" + fecha + ", autor=" + autor + ", email=" + email + ", license=" + license + ", preview=" + preview + '}';
    }
    
}
