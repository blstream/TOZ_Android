package com.intive.toz.add_pet.presenter;

import android.view.View;

import com.esafirm.imagepicker.model.Image;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.add_pet.AddPetMvp;
import com.intive.toz.data.DataLoader;
import com.intive.toz.data.DataProvider;
import com.intive.toz.data.RxBus;
import com.intive.toz.petslist.model.Pet;

import java.io.File;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * The type Add pet presenter.
 */
public class AddPetPresenter extends MvpBasePresenter<AddPetMvp.View> implements AddPetMvp.Presenter {

    private List<Image> images;
    private CompositeDisposable disposables;
    private RxBus rxBus;

    private static class EndEvent {
    }

    /**
     * Instantiates a new Add pet presenter.
     */
    public AddPetPresenter() {
        disposables = new CompositeDisposable();
        rxBus = new RxBus();

        disposables.add(rxBus
                .asFlowable()
                .subscribe(
                        new Consumer<Object>() {
                            @Override
                            public void accept(@NonNull final Object event) throws Exception {
                                if (event instanceof EndEvent) {
                                    getView().showProgressBar(View.GONE);
                                    getView().onSuccess();
                                }
                            }
                        }));
    }

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
    public void uploadImages(final String id) {

        DataLoader dataLoader = new DataLoader();
        for (int i = 0; i < images.size(); i++) {
            final Image image = images.get(i);
            final int position = i;

            File file = new File(image.getPath());
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("file", file.getName(), requestFile);

            dataLoader.uploadImage(new DataProvider.ResponseCallback<ResponseBody>() {
                @Override
                public void onSuccess(final ResponseBody response) {
                    if (position == images.size() - 1) {
                        if (rxBus.hasObservers()) {
                            rxBus.send(new EndEvent());
                        }
                    }
                }

                @Override
                public void onError(final Throwable e) {
                    e.printStackTrace();
                    if (position == images.size() - 1) {
                        if (rxBus.hasObservers()) {
                            rxBus.send(new EndEvent());
                        }
                    }
                }
            }, id, body);
        }
    }

    @Override
    public void addPet(final Pet pet, final List<Image> images) {
        getView().showProgressBar(View.VISIBLE);
        this.images = images;
        DataLoader dataLoader = new DataLoader();
        dataLoader.addPet(new DataProvider.ResponseCallback<Pet>() {
            @Override
            public void onSuccess(final Pet response) {
                if (images.size() == 0) {
                    getView().showProgressBar(View.GONE);
                    getView().onSuccess();
                } else {
                    uploadImages(response.getId());
                }

            }

            @Override
            public void onError(final Throwable e) {
                getView().showProgressBar(View.GONE);
                getView().onError();
            }
        }, pet);
    }

    @Override
    public void detachView(final boolean retainInstance) {
        super.detachView(retainInstance);
        disposables.clear();
    }
}
