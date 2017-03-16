package com.intive.toz.financial.view;

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
import com.intive.toz.financial.IFinancial;
import com.intive.toz.financial.model.FinancialData;
import com.intive.toz.financial.presenter.FinancialPresenter;
import com.intive.toz.network.ApiClient;
import com.intive.toz.network.PetsApi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fragment to display financial data
 */
public class FinancialFragment extends MvpFragment<IFinancial.FinancialView, IFinancial.Presenter> implements IFinancial.FinancialView {

    @BindView(R.id.tv_account_number)
    TextView accountNumber;
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.tv_transfer_receiver_name)
    TextView transferReceiverName;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    public PetsApi financialService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_financial, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        financialService = ApiClient.getPetsApiService();

        ButterKnife.bind(this, view);

        presenter.loadFinancialData(financialService);
    }

    public void setFinancialData(final FinancialData financialResponse) {
        accountNumber.setText(financialResponse.getAccountNumber());
        title.setText(financialResponse.getTitle());
        transferReceiverName.setText(financialResponse.getTransferReceiverName());
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
    public IFinancial.Presenter createPresenter() {
        return new FinancialPresenter(this);
    }
}
