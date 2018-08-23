package io.dev.tanners.galleryflickr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Photo {

    public Photo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getUrl_c() {
        return url_c;
    }

    public void setUrl_c(String url_c) {
        this.url_c = url_c;
    }

    public String getHeight_c() {
        return height_c;
    }

    public void setHeight_c(String height_c) {
        this.height_c = height_c;
    }

    public String getWidth_c() {
        return width_c;
    }

    public void setWidth_c(String width_c) {
        this.width_c = width_c;
    }

    private String id;
    private String title;
    private String url_c;
    private String height_c;
    private String width_c;


}
