package com.intive.toz.petDetails.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.intive.toz.R;
import com.intive.toz.info.model.Info;
import com.intive.toz.network.ApiClient;
import com.intive.toz.network.PetsApi;
import com.intive.toz.info.model.Help;
import com.intive.toz.petDetails.presenter.HelpPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fragment to show how to donate particular pet.
 */
public class HelpFragment extends MvpFragment<HelpPetMvp.HelpPetView, HelpPetMvp.Presenter>
        implements HelpPetMvp.HelpPetView {

    public PetsApi service;

    @BindView(R.id.progress_bar_pet_help)
    ProgressBar progressBar;

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

    /**
     * empty constructor.
     */
    public HelpFragment() {
        // Required empty public constructor
    }

    @Override
    public HelpPresenter createPresenter() {
        return new HelpPresenter();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_help_pet, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        service = ApiClient.getPetsApiService();

        ButterKnife.bind(this, view);

        presenter.loadFinancialData(service);

        presenter.loadHowToDonateData(service);
    }

    @Override
    public void setFinancialData(final Info financial) {
        tvOrgName.setText(financial.getName());
        tvBankAcc.setText(financial.getBankAccount().getNumber());
        tvStreet.setText(financial.getAddress().getStreet());
        tvCity.setText(financial.getAddress().getPostCode() + " " + financial.getAddress().getCity());
    }

    @Override
    public void setDonateInfo(final Help donate) {
        tvHowHelp.setText(donate.getHowToHelpDescription());
        tvTransferTitle.setText("Tytuł przelewu");
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(final Throwable e) {
        e.printStackTrace();
    }

}
