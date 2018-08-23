package io.dev.tanners.galleryflickr.support;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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

    /**
     * bind data to view holder
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        PhotoViewHolder mHolder = holder;
        Photo tempPhoto = mItems.get(position);
        mHolder.title.setText(formatText(tempPhoto.getTitle(), tempPhoto.getHeight_c(), tempPhoto.getWidth_c()));
        mHolder.loadImage(tempPhoto.getUrl_c());
    }

    /**
     * make one string to include title and image dims
     * @param title
     * @param h
     * @param w
     * @return
     */
    private String formatText(String title, String h, String w) {
        StringBuilder mBuilder = new StringBuilder();
        mBuilder.append(title);
        mBuilder.append(System.getProperty("line.separator"));
        mBuilder.append(h);
        mBuilder.append(" x ");
        mBuilder.append(w);

        return mBuilder.toString();
    }

    /**
     * item count of listy
     * @return
     */
    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    /**
     * view holder
     */
    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        public ImageView photo;
        public TextView title;

        public PhotoViewHolder(View view) {
            super(view);

            title = view.findViewById(R.id.title);
            photo = view.findViewById(R.id.photo_item_id);
            photo.setClipToOutline(true);
        }

        /**
         * load images into glide
         * @param mUrl
         */
        public void loadImage(String mUrl) {
             Glide.with(mContext)
                        .load(mUrl)
                        .apply(new RequestOptions()
                                .centerCrop()
                        )
                        .transition(new DrawableTransitionOptions()
                                .crossFade())
                        .into(photo);
            }
    }

    /**
     * create the view holder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);

        return new PhotoViewHolder(view);
    }

    /**
     * update adapter
     *
     * @param mItems
     */
    public void updateAdapter(List<Photo> mItems)
    {
        this.mItems = mItems;
        notifyDataSetChanged();
    }
}


