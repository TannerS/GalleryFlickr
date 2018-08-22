package io.dev.tanners.galleryflickr.model.util;

import java.util.Map;

import io.dev.tanners.galleryflickr.model.PhotosBase;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET(config.API_BASE_METHOD)
    Call<PhotosBase> getphotos(@Query(config.API_KEY_KEY) String apiKey, @Query(config.METHOD_KEY) String method, @Query(config.EXTRA_KEY) String extras, @Query(config.FORMAT_KEY) String format, @Query(config.OTHER_KEY) int other);
}

//https://api.flickr.com/services/rest/?api_key=949e98778755d1982f537d56236bbb42&method=flickr.photos.getRecent