package com.intive.toz.petDetails.page_view_images;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Adaper to contains fragment - images.
 */
public class SwipeAdapter extends FragmentStatePagerAdapter {

    private static final int NR_IMAGES = 10;
    private String idPet;

    /**
     * constructor SwipeAdapter.
     * @param fm fragmentManager to change fragment in adapter.
     */
    public SwipeAdapter(final FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(final int position) {
        Fragment fragment = new PetImagesFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("count", position + 1);
        bundle.putString("idPet", idPet);

        fragment.setArguments(bundle);

        return fragment;
    }

    /**
     * method to send Id from Activity to Adapter.
     * @param idPet contain id selected pet.
     */
    public void setPetId(final String idPet) {
        this.idPet = idPet;
    }

    @Override
    public int getCount() {
        return NR_IMAGES;
    }
}
