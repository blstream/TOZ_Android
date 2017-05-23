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

import java.util.ArrayList;
import java.util.List;

/**
 * Class adapter to show images.
 */
public class PetImgViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;
    private Pet pet;
    private List img = new ArrayList();
    private TextView tvImgNr;
    private ImageView imgPet;

    /**
     * counstructor adapter.
     *
     * @param context from fragment.
     * @param pet object contain url.
     */
    public PetImgViewPagerAdapter(final Context context, final Pet pet) {
        this.context = context;
        this.pet = pet;

        String image = ApiClient.IMAGES_BASE_URL + pet.getImageUrl();
        img.add(image);
    }

    @Override
    public int getCount() {
        return img.size();
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item, container,
                false);

        tvImgNr = (TextView) itemView.findViewById(R.id.tv_nr_page);
        imgPet = (ImageView) itemView.findViewById(R.id.iv_pet);

        if (pet != null) {
            int shiftArrayNrToDisplay = 1;
            tvImgNr.setText(position + shiftArrayNrToDisplay + " / " + img.size());

            int imgSex;

            if (pet.getType().contains("DOG")) {
                imgSex = R.drawable.dog;
            } else {
                imgSex = R.drawable.cat;
            }

            Glide.with(context)
                    .load(img.get(position))
                    .centerCrop()
                    .placeholder(imgSex)
                    .error(imgSex)
                    .into(imgPet);
        }

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((RelativeLayout) object);
    }
}
