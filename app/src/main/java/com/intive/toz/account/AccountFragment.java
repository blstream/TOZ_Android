package com.intive.toz.account;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.intive.toz.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 *  Fragment displaying options in tab 'account'.
 */
public class AccountFragment extends Fragment {

    @BindView(R.id.change_password_btn)
    Button changePassword;

    private Unbinder unbinder;

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        unbinder = ButterKnife.bind(this, view);
    }

    @OnClick(R.id.change_password_btn)
    public void onChangePasswordClicked () {
        Intent intent = new Intent(this.getActivity(), ChangePasswordActivity.class);
        startActivity(intent);
    }
}
