package com.example.tankinhbui.m2obilecanhan2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PM_Fragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pm_fragment, container, false);
    }

    public static PM_Fragment newInstance(String s) {
        PM_Fragment fragment = new PM_Fragment();
        Bundle args = new Bundle();
        args.putString("strArg1", s);
        fragment.setArguments(args);
        return fragment;
    }


}