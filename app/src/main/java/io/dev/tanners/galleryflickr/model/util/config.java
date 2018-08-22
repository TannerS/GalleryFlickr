package io.dev.tanners.galleryflickr.model.util;

import android.content.Context;

import java.util.HashMap;

public class config {
    public final static String API_KEY_KEY = "api_key";
    public final static String API_KEY_VALUE = "949e98778755d1982f537d56236bbb42";
    public final static String API_BASE = "https://api.flickr.com/";
    public final static String API_BASE_METHOD = "services/rest";
    // had to change this from search to getPhotos due to new limitations in the flickr api, they require a search parameter and since it was not specified, It suggested this
    public final static String METHOD_VALUE = "flickr.photos.getRecent";
    public final static String METHOD_KEY= "method";
    public final static String EXTRA_KEY = "extras";
    public final static String EXTRA_VALUE = "url_t";
    public final static String FORMAT_KEY = "format";
    public final static String FORMAT_VALUE = "json";
    public final static String OTHER_KEY = "nojsoncallback";
    public final static int OTHER_VALUE = 1;
}
//https://api.flickr.com/services/rest/?api_key=949e98778755d1982f537d56236bbb42&method=flickr.photos.getRecent

