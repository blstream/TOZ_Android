package com.intive.toz.homescreen.view;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import com.intive.toz.Pet;


import java.util.List;

/**
 *  pets list interface used to communicate with pets list fragment and presenter.
 */

public interface PetsListView extends MvpLceView<List<Pet>> {
}
