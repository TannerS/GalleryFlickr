package io.dev.tanners.galleryflickr.support;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;
import io.dev.tanners.galleryflickr.R;
import io.dev.tanners.galleryflickr.model.Photo;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>{
    private Context mContext;
    protected List<Photo> mItems;

    public PhotoAdapter(@NonNull Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        PhotoViewHolder mHolder = holder;
        Photo tempPhoto = mItems.get(position);
        mHolder.loadImage(tempPhoto.getUrl_t());
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }


    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        public ImageView photo;

        public PhotoViewHolder(View view) {
            super(view);
            photo = view.findViewById(R.id.photo_item_id);
            photo.setClipToOutline(true);
        }

        public void loadImage(String mUrl) {
                Glide.with(mContext)
                        .load(mUrl)
                        .apply(new RequestOptions()
                                .fitCenter()
                        )
                        .transition(new DrawableTransitionOptions()
                                .crossFade())
                        .into(photo);
            }
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);
        return new PhotoViewHolder(view);
    }

    public void updateAdapter(List<Photo> mItems)
    {
        Log.i("DEBUG", "UPDATE");
        this.mItems = mItems;
        notifyDataSetChanged();
    }
}


