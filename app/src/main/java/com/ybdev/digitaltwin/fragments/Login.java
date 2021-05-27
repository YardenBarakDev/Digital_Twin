package com.ybdev.digitaltwin.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ybdev.digitaltwin.R;


public class Login extends Fragment {

    private TextView SignIn_LBL_createAccount;

    protected View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view == null){
            view = inflater.inflate(R.layout.fragment_login, container, false);
        }
        SignIn_LBL_createAccount =  view.findViewById(R.id.SignIn_LBL_createAccount);

        SignIn_LBL_createAccount.setOnClickListener(v ->
                NavHostFragment.findNavController(this).navigate(R.id.action_login_to_register));

        return view;
    }
}