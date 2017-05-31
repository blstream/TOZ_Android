package com.intive.toz.petDetails.view_pager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.intive.toz.R;
import com.intive.toz.add_pet.view.FullScreenImageActivity;
import com.intive.toz.network.ApiClient;
import com.intive.toz.petslist.model.Pet;

/**
 * Class adapter to show images.
 */
public class PetImgViewPagerAdapter extends PagerAdapter {

    public static final String IMG_URL = "img_url";

    private Context context;
    private Pet pet;

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
        return pet.getGallery() == null ? 1 : pet.getGallery().size();
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.viewpager_item, container,
                false);

        TextView tvImgNr = (TextView) itemView.findViewById(R.id.tv_nr_page);
        ImageView imgPet = (ImageView) itemView.findViewById(R.id.iv_pet);


        int imgSex;

        if (pet.getType().contains("DOG")) {
            imgSex = R.drawable.dog;
        } else {
            imgSex = R.drawable.cat;
        }

        if (pet.getGallery() != null) {
            int shiftArrayNrToDisplay = 1;
            tvImgNr.setText(position + shiftArrayNrToDisplay + " / " + pet.getGallery().size());
            final String url = ApiClient.API_DOMAIN + pet.getGallery().get(position).getFileUrl();

            imgPet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    Intent intent = new Intent(context, FullScreenImageActivity.class);
                    intent.putExtra(IMG_URL, url);
                    context.startActivity(intent);
                }
            });

            Glide.with(context)
                    .load(url)
                    .error(imgSex)
                    .into(imgPet);
        } else {
            tvImgNr.setText(context.getString(R.string.no_images));
            Glide.with(context)
                    .load(imgSex)
                    .centerCrop()
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
