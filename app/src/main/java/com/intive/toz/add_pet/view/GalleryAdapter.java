package com.intive.toz.add_pet.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.esafirm.imagepicker.model.Image;
import com.intive.toz.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryAdapter extends BaseAdapter {

    @BindView(R.id.image)
    ImageView imageView;

    private List<Image> images;
    private Context context;

    public GalleryAdapter(Context context) {
        this.context = context;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public int getCount() {
        return images == null ? 0 : images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.item_image_gallery, parent, false);
            ButterKnife.bind(this, view);
        }

        imageView.setImageBitmap(BitmapFactory.decodeFile(images.get(position).getPath()));

        return view;
    }
}
