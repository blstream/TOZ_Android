package com.intive.toz.add_pet.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.intive.toz.R;
import com.intive.toz.add_pet.model.GalleryItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The type Gallery adapter.
 */
public class GalleryAdapter extends BaseAdapter {

    /**
     * The Image view.
     */
    @BindView(R.id.image)
    ImageView imageView;

    /**
     * The Add button.
     */
    @BindView(R.id.add_button)
    View addButton;

    private List<GalleryItem> images;
    private Context context;
    private int size;

    /**
     * Instantiates a new Gallery adapter.
     *
     * @param context the context
     * @param size    the size
     */
    public GalleryAdapter(final Context context, final int size) {
        this.context = context;
        this.size = size;
    }

    /**
     * Sets images.
     *
     * @param images the images
     */
    public void setImages(final List<GalleryItem> images) {
        this.images = images;
    }

    @Override
    public int getCount() {
        return images == null ? 0 : images.size();
    }

    @Override
    public Object getItem(final int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.item_image_gallery, parent, false);
            view.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, size));
            ButterKnife.bind(this, view);
        }

        if (!images.get(position).isButton()) {
            addButton.setVisibility(View.GONE);
            Glide.with(context)
                    .load(images.get(position).getImage().getPath())
                    .centerCrop()
                    .into(imageView);
        }

        return view;
    }
}
