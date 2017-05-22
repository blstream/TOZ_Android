package com.intive.toz.add_pet.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.add_pet.AddPetMvp;
import com.intive.toz.data.DataLoader;
import com.intive.toz.data.DataProvider;
import com.intive.toz.petslist.model.Pet;

/**
 * The type Add pet presenter.
 */
public class AddPetPresenter extends MvpBasePresenter<AddPetMvp.View> implements AddPetMvp.Presenter {
    @Override
    public boolean validate(final String name, final String address, final String description, final int selectedType, final int selectedGender) {
        boolean isValid = true;

        if (name == null || name.isEmpty()) {
            isValid = false;
        }

        if (address == null || address.isEmpty()) {
            isValid = false;
        }

        if (description == null || description.isEmpty()) {
            isValid = false;
        }

        if (selectedType == 0) {
            isValid = false;
        }

        if (selectedGender == 0) {
            isValid = false;
        }

        return isValid;
    }

    @Override
    public void addPet(final Pet pet) {
        DataLoader dataLoader = new DataLoader();
        dataLoader.addPet(new DataProvider.ResponseCallback<Pet>() {
            @Override
            public void onSuccess(final Pet response) {
                getView().onSuccess();
            }

            @Override
            public void onError(final Throwable e) {
                getView().onError();
            }
        }, pet);
    }
}
