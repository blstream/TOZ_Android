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

/**
 * Class adapter to show images.
 */
public class PetImgViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;
    private Pet pet;
    private TextView tvImgNr;
    private ImageView imgPet;

    /**
     * counstructor adapter.
     *
     * @param context from fragment.
     * @param pet     object contain url.
     */
    public PetImgViewPagerAdapter(final Context context, final Pet pet) {
        this.context = context;
        this.pet = pet;
    }

    @Override
    public int getCount() {
        return pet.getGallery() == null ? 0 : pet.getGallery().size();
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

        if (pet.getGallery() != null) {
            int shiftArrayNrToDisplay = 1;
            tvImgNr.setText(position + shiftArrayNrToDisplay + " / " + pet.getGallery().size());

            int imgSex;

            if (pet.getType().contains("DOG")) {
                imgSex = R.drawable.dog;
            } else {
                imgSex = R.drawable.cat;
            }

            Glide.with(context)
                    .load(ApiClient.API_DOMAIN + pet.getGallery().get(position).getFileUrl())
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
