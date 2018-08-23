package io.dev.tanners.galleryflickr.model.util;

import io.dev.tanners.galleryflickr.model.PhotosBase;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * api rest call's interface
 */
public interface ApiInterface {
    @GET(Config.API_BASE_METHOD)
    Call<PhotosBase> getPhotos(
            @Query(Config.API_KEY_KEY) String apiKey,
            @Query(Config.METHOD_KEY) String method,
            @Query(Config.EXTRA_KEY) String extras,
            @Query(Config.FORMAT_KEY) String format,
            @Query(Config.REQUIRED_KEY) String tags,
            @Query(Config.SAFE_KEY) int safe,
            @Query(Config.OTHER_KEY) int other
    );
}