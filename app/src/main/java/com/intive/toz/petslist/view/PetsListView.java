package com.intive.toz.petslist.view;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import com.intive.toz.petslist.model.Pet;


import java.util.List;

/**
 *  pets list interface used to communicate with pets list fragment and presenter.
 */

public interface PetsListView extends MvpLceView<List<Pet>> {
}
