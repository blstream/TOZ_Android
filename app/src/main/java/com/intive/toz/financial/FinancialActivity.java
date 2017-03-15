package com.intive.toz.financial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.intive.toz.R;
import com.intive.toz.network.ApiClient;
import com.intive.toz.network.PetsApi;

/**
 * Activity to display financial support information.
 */
public class FinancialActivity extends AppCompatActivity implements FinancialView {

    private TextView accountNumber;
    private TextView title;
    private TextView transferReceiverName;

    private ProgressBar progressBar;

    public PetsApi financialService;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial);
        initViews();

        financialService = ApiClient.getPetsApiService();

        FinancialPresenter presenter = new FinancialPresenter(this);
        presenter.loadFinancialData(financialService);
    }

    private void initViews() {
        accountNumber = (TextView) findViewById(R.id.tv_account_number);
        title = (TextView) findViewById(R.id.tv_title);
        transferReceiverName = (TextView) findViewById(R.id.tv_transfer_receiver_name);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    /**
     * set text from response in textview's.
     *
     * @param financialResponse from json
     */
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
        Toast.makeText(this, R.string.financial_info_load_error, Toast.LENGTH_LONG).show();
    }
}
