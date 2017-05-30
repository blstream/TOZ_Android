package com.intive.toz.petDetails.view;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.intive.toz.R;
import com.intive.toz.data.AddressChecker;
import com.intive.toz.data.IBANFormatter;
import com.intive.toz.info.model.Help;
import com.intive.toz.info.model.Info;
import com.intive.toz.petDetails.presenter.PetDetailsPresenter;
import com.intive.toz.petDetails.view_pager.PetImgViewPagerAdapter;
import com.intive.toz.petslist.model.Pet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Fragment to show details about Pet.
 */
public class PetDetailsFragment extends MvpFragment<PetDetailsView, PetDetailsPresenter>
        implements PetDetailsView {


    @BindView(R.id.name_pet_details)
    TextView nameTv;

    @BindView(R.id.type_pet_details)
    TextView typeTv;

    @BindView(R.id.sex_pet_details)
    TextView sexTv;

    @BindView(R.id.date_pet_details)
    TextView dateTv;

    @BindView(R.id.description_pet_details)
    TextView descriptionTv;

    @BindView(R.id.progress_bar_pet_details)
    ProgressBar progressBar;

    @BindView(R.id.progress_bar_pet_help)
    ProgressBar progressBarHelp;

    @BindView(R.id.tv_text_help)
    TextView tvHowHelp;

    @BindView(R.id.tv_transfer_title)
    TextView tvTransferTitle;

    @BindView(R.id.tv_nr_acc_help)
    TextView tvBankAcc;

    @BindView(R.id.tv_org_name_help)
    TextView tvOrgName;

    @BindView(R.id.tv_city_help)
    TextView tvCity;

    @BindView(R.id.tv_street_help)
    TextView tvStreet;

    @BindView(R.id.btn_help)
    Button helpButton;

    @BindView(R.id.help_fragment_container)
    View helpContainer;

    @BindView(R.id.pager)
    ViewPager viewPager;

    private AddressChecker addressChecker;
    private IBANFormatter ibanFormatter;
    private String id;
    private Unbinder unbinder;
    private Pet pet;
    private PetImgViewPagerAdapter adapter;

    /**
     * pet constructor.
     */
    public PetDetailsFragment() {
    }

    /**
     * set pet id from activity to fragment.
     *
     * @param id pet id
     */
    public void setPetID(final String id) {
        this.id = id;
    }

    @NonNull
    @Override
    public PetDetailsPresenter createPresenter() {
        return new PetDetailsPresenter();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_pet_details, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showPetDetails(final Pet pet, final String petCreatedDate) {
        this.pet = pet;
        setPetInAdapter(pet);
        if (pet.getSex().equals(getString(R.string.male_tag))) {
            sexTv.setText(R.string.pet_sex_male);
        } else {
            sexTv.setText(R.string.pet_sex_female);
        }
        if (pet.getType().equals(getString(R.string.dog_tag))) {
            typeTv.setText(R.string.dog_type);
        } else {
            typeTv.setText(R.string.cat_type);
        }
        nameTv.setText(pet.getName());
        dateTv.setText(petCreatedDate);
        descriptionTv.setText(pet.getDescription());
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(final Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.loadPetsDetails(id);
    }

    @Override
    public void setFinancialData(final Info financial) {
        tvOrgName.setText(financial.getName());
        tvBankAcc.setText(ibanFormatter.toIBAM(financial.getBankAccount().getNumber()));
        tvStreet.setText(financial.getAddress().getStreet());
        tvCity.setText(financial.getAddress().getPostCode() + " " + financial.getAddress().getCity());
        tvStreet.setText(financial.getAddress().getStreet() + " " + addressChecker.getCorrectAddress(financial));
    }

    @Override
    public void setDonateInfo(final Help donate) {
        tvHowHelp.setText(donate.getHowToHelpDescription());
        tvTransferTitle.setText(getString(R.string.title_support_for) + " " + pet.getName());
    }

    /**
     * Send Pet to Adapter.
     *
     * @param pet object which contain url images.
     */
    @Override
    public void setPetInAdapter(final Pet pet) {
        Context context = getActivity().getApplicationContext();
        adapter = new PetImgViewPagerAdapter(context, pet);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void showProgressHelp() {
        progressBarHelp.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressHelp() {
        progressBarHelp.setVisibility(View.INVISIBLE);
    }

    /**
     * Button to create/delete fragment donate information.
     */
    @OnClick(R.id.btn_help)
    public void onHelpButtonClick() {
        if (helpContainer.getVisibility() == View.VISIBLE) {
            helpContainer.setVisibility(View.GONE);
            setColorButton(R.drawable.button_orange);
        } else {
            helpContainer.setVisibility(View.VISIBLE);
            setColorButton(R.drawable.button_grey);
            ibanFormatter = new IBANFormatter();
            addressChecker = new AddressChecker();

            presenter.loadFinancialData();
            presenter.loadHowToDonateData();
        }
    }

    /**
     * On left click.
     */
    @OnClick(R.id.left_nav)
    public void onLeftClick() {
        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
    }

    /**
     * On right click.
     */
    @OnClick(R.id.right_nav)
    public void onRightClick() {
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
    }

    /**
     * method to change color button.
     * getDrawable require api > 21
     *
     * @param color custom button from @drawable.
     */
    public void setColorButton(final int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            helpButton.setBackground(getContext().getDrawable(color));
        } else {
            helpButton.setBackground(getResources().getDrawable(color));
        }
    }
}
