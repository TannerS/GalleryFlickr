package io.dev.tanners.galleryflickr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PhotosBase {

    public PhotosBase() {
    }

    public PhotoWrapper getPhotos() {
        return photos;
    }

    public void setPhotos(PhotoWrapper photos) {
        this.photos = photos;
    }

    private PhotoWrapper photos;
}
