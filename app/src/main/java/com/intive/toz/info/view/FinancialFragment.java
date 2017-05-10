package com.intive.toz.info.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.intive.toz.R;
import com.intive.toz.info.FinancialMvp;
import com.intive.toz.info.model.Info;
import com.intive.toz.info.presenter.FinancialPresenter;
import com.intive.toz.network.ApiClient;
import com.intive.toz.network.PetsApi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fragment to display financial data.
 */
public class FinancialFragment extends MvpFragment<FinancialMvp.FinancialView, FinancialMvp.Presenter> implements FinancialMvp.FinancialView {

    @BindView(R.id.tv_account_number)
    TextView accountNumber;
    @BindView(R.id.tv_name)
    TextView name;
    @BindView(R.id.tv_city_code)
    TextView cityCode;
    @BindView(R.id.tv_street)
    TextView street;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    public PetsApi financialService;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_financial, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        financialService = ApiClient.getPetsApiService();

        ButterKnife.bind(this, view);

        presenter.loadFinancialData(financialService);
    }

    /**
     * set financial data to textviews.
     * @param financialResponse financial response from server.
     */
    public void setFinancialData(final Info financialResponse) {
        accountNumber.setText(financialResponse.getBankAccount().getNumber());
        name.setText(financialResponse.getName());
        cityCode.setText(financialResponse.getAddress().getPostCode()
                         + " " + financialResponse.getAddress().getCity());
        street.setText("ul. " + financialResponse.getAddress().getStreet());

    }

    @Override
    public void showProgres() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgres() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), R.string.financial_info_load_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public FinancialMvp.Presenter createPresenter() {
        return new FinancialPresenter();
    }
}
