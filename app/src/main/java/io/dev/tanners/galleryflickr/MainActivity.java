package io.dev.tanners.galleryflickr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dev.tanners.galleryflickr.model.PhotosBase;
import io.dev.tanners.galleryflickr.model.util.ApiInterface;
import io.dev.tanners.galleryflickr.model.util.config;
import io.dev.tanners.galleryflickr.support.PhotoAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

//public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<PhotosBase>{
public class MainActivity extends AppCompatActivity {
    private ApiInterface apiInterface;
    private RecyclerView mRecyclerView;
    private PhotoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpList();
        createApiCall();
        getPhotos();
    }

    private void setUpList()
    {
        mRecyclerView = findViewById(R.id.main_list);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 2);
        mGridLayoutManager.setSmoothScrollbarEnabled(true);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mAdapter = new PhotoAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private Callback<PhotosBase> photoCallback()
    {
        return new Callback<PhotosBase>() {
            @Override
            public void onResponse(Call<PhotosBase> call, Response<PhotosBase> response) {

                Log.d("REST REQUEST", call.request().url().toString());

                if (response.isSuccessful()) {
                    PhotosBase base = response.body();

                    if(base != null) {
                        if(base.getPhotos() != null) {
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

    private void createApiCall() {
        ObjectMapper mMapper = new ObjectMapper();
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(config.API_BASE)
                .addConverterFactory(JacksonConverterFactory.create(mMapper))
                .build();

        apiInterface = mRetrofit.create(ApiInterface.class);
    }

    private void getPhotos()
    {
        apiInterface.getphotos(config.API_KEY_VALUE, config.METHOD_VALUE, config.EXTRA_VALUE, config.FORMAT_VALUE, config.OTHER_VALUE).enqueue (photoCallback());
    }
}
