package com.intive.toz.common.view.navigationTabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.intive.toz.R;

public class NavigationTabsFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(getArguments().getString("msg"));
        return view;
    }

    public static NavigationTabsFragment newInstance(String text) {

        NavigationTabsFragment navigationTabsFragment = new NavigationTabsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("msg", text);
        navigationTabsFragment.setArguments(bundle);
        return navigationTabsFragment;
    }
}
