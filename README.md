# dinawall_core

DinaWall_Core is a central library of the DinaWall APP, this library is a 1 tier and have a principal objetive dispose a central functionality to integrate java with de Linux Desktop Enviroments tha KDE Plamas, Gnome, Unity, Cinnamon, Deeepin etc.

## DinaWall_Core API

this has a 1 essential metho to integrate java apps with the Linux Distributions, **DinaWall_Core** is has implemented a singleton patron desig, then when is initialized this object identify and set the Desktop Enviroment, when is necesary change a wallpaper use a **setWallpaperDesktop(String url)** method with the path to image as String object, dinawall_core change the wallpaper
