package io.dev.tanners.galleryflickr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dev.tanners.galleryflickr.model.PhotosBase;
import io.dev.tanners.galleryflickr.model.util.ApiInterface;
import io.dev.tanners.galleryflickr.model.util.Config;
import io.dev.tanners.galleryflickr.support.PhotoAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ApiInterface apiInterface;
    private RecyclerView mRecyclerView;
    private PhotoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // set up list
        setUpList();
        // set up rest call
        createApiCall();
        // get photos from rest api call
        getPhotos();
    }

    /**
     * set up list to display images
     */
    private void setUpList()
    {
        mRecyclerView = findViewById(R.id.main_list);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 2);
        mGridLayoutManager.setSmoothScrollbarEnabled(true);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mAdapter = new PhotoAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Call back for rest call
     * @return
     */
    private Callback<PhotosBase> photoCallback()
    {
        return new Callback<PhotosBase>() {
            @Override
            public void onResponse(Call<PhotosBase> call, Response<PhotosBase> response) {
                if (response.isSuccessful()) {
                    PhotosBase base = response.body();

                    if(base != null) {
                        if(base.getPhotos() != null) {
                            // update adapter with images
                            mAdapter.updateAdapter(base.getPhotos().getPhoto());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<PhotosBase> call, Throwable t) {
                t.printStackTrace();
            }
        };
    }

    /**
     * create rest call with json parser
     */
    private void createApiCall() {
        ObjectMapper mMapper = new ObjectMapper();
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(Config.API_BASE)
                .addConverterFactory(JacksonConverterFactory.create(mMapper))
                .build();

        apiInterface = mRetrofit.create(ApiInterface.class);
    }

    /**
     * make rest call
     */
    private void getPhotos()
    {
        apiInterface.getPhotos(
                Config.API_KEY_VALUE,
                Config.METHOD_VALUE,
                Config.EXTRA_VALUE,
                Config.FORMAT_VALUE,
                Config.REQUIRED_VALUE,
                Config.OTHER_VALUE,
                Config.SAFE_VALUE
        ).enqueue (photoCallback());
    }
}
