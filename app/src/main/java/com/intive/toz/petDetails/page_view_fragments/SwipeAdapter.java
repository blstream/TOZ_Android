package com.intive.toz.petDetails.page_view_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.intive.toz.Pet;

/**
 * Created by Krzysiek on 2017-04-09.
 */

public class SwipeAdapter extends FragmentStatePagerAdapter {

    Pet pet;

    public SwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new PetImagesFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("count", position + 1);
        fragment.setArguments(bundle);
        return fragment;
    }

//    public void setData(final Pet pet) {
//        this.pet = pet;
//    }

    @Override
    public int getCount() {
        return 5;
    }
}
