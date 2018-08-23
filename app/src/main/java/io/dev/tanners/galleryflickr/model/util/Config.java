package io.dev.tanners.galleryflickr.model.util;

/**
 * hold rest call changeable options for scalability
 */
public class Config {
    public final static String API_KEY_KEY = "api_key";
    public final static String API_KEY_VALUE = "949e98778755d1982f537d56236bbb42";
    public final static String API_BASE = "https://api.flickr.com/";
    public final static String API_BASE_METHOD = "services/rest/";
    public final static String METHOD_VALUE = "flickr.photos.search";
    public final static String METHOD_KEY= "method";
    public final static String EXTRA_KEY = "extras";
    public final static String EXTRA_VALUE = "url_c";
    public final static String FORMAT_KEY = "format";
    public final static String FORMAT_VALUE = "json";
    public final static String OTHER_KEY = "nojsoncallback";
    public final static int OTHER_VALUE = 1;
    public final static String REQUIRED_KEY = "tags";
    public final static String REQUIRED_VALUE = "space,nature,wallpaper,plants";
    public final static String SAFE_KEY = "safe_search";
    public final static int SAFE_VALUE = 1;
}

