package com.intive.toz.petDetails.view_pager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.intive.toz.R;
import com.intive.toz.network.ApiClient;
import com.intive.toz.petslist.model.Pet;

import java.util.Arrays;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;
    private Pet pet;
    private String[] img, imgPre;
    private TextView tvImgNr;
    private ImageView imgPet;

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public ViewPagerAdapter(Context context, Pet pet) {
        this.context = context;
        this.pet = pet;

        imgPre = new String[]{
                ApiClient.API_URL + "/" + pet.getImageUrl(),
                "http://kloppex.ru/kart/18/staryy_kot_prev.jpg",
                "http://www.gstatic.com/webp/gallery/1.jpg",
                "http://www.gstatic.com/webp/gallery/2.jpg",
                "http://www.gstatic.com/webp/gallery/4.jpg",
                "http://www.gstatic.com/webp/gallery/5.jpg",
                "http://www.gstatic.com/webp/gallery/3.jpg",
                "http://www.gstatic.com/webp/gallery/2.jpg",
                "http://www.gstatic.com/webp/gallery/1.jpg",
                ApiClient.API_URL + "/" + pet.getImageUrl()
        };

        int min = 1;
        int max = 10;
        int random = min + (int)(Math.random() * ((max - min) + 1));
        img = Arrays.copyOfRange(imgPre, 0, random);
    }

    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item, container,
                false);

        tvImgNr = (TextView) itemView.findViewById(R.id.tv_nr_page);
        imgPet = (ImageView) itemView.findViewById(R.id.iv_pet);

        if (pet != null) {
            int shiftArrayNrToDisplay = 1;
            tvImgNr.setText(position + shiftArrayNrToDisplay + " / " + img.length);

            Glide.with(context)
                    .load(img[position])
                    .centerCrop()
                    .placeholder(R.drawable.ic_pets_black_error48dp)
                    .error(R.color.greyLight)
                    .into(imgPet);
        }

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
