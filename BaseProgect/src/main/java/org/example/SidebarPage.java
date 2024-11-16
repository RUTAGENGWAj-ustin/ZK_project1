package org.example;

public class SidebarPage {
    private String name;
    private String  image;
    private String  pathOfIcon;
    private String  url;

    public SidebarPage(String name, String image, String pathOfIcon, String url) {
        this.name = name;
        this.image = image;
        this.pathOfIcon = pathOfIcon;
        this.url = url;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPathOfIcon() {
        return pathOfIcon;
    }

    public void setPathOfIcon(String pathOfIcon) {
        this.pathOfIcon = pathOfIcon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
