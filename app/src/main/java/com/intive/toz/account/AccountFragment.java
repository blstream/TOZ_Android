package com.intive.toz.account;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.intive.toz.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 *  Fragment displaying options in tab 'account'.
 */
public class AccountFragment extends Fragment {

    private Unbinder unbinder;

    /**
     * Create new Instance of AccountFragment.
     * @return new Instance of AccountFragment.
     */
    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * Start Activity on changePassword button clicked.
     */
    @OnClick(R.id.change_password_btn)
    public void onChangePasswordClicked() {
        Intent intent = new Intent(this.getActivity(), ChangePasswordActivity.class);
        startActivity(intent);
    }
}
