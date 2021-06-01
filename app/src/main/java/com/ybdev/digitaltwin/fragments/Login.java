package com.ybdev.digitaltwin.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.ybdev.digitaltwin.R;
import com.ybdev.digitaltwin.util.MyApp;
import com.ybdev.digitaltwin.util.MySP;


public class Login extends Fragment {

    private TextView SignIn_LBL_createAccount;
    private MaterialButton SignIn_BTN_login;
    private TextInputEditText emailEdt;

    protected View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_login, container, false);
        }
        findViews(view);

        SignIn_LBL_createAccount.setOnClickListener(v ->
                NavHostFragment.findNavController(this).navigate(R.id.action_login_to_register));

        SignIn_BTN_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = emailEdt.getText().toString();
                MySP.getInstance().putString(MySP.KEYS.USER_EMAIL, userEmail);
                NavHostFragment.findNavController(Login.this).navigate(R.id.action_login_to_projectList);
            }
        });

        return view;
    }

    private void findViews(View view) {
        emailEdt = view.findViewById(R.id.SignIn_LBL_email);
        SignIn_LBL_createAccount = view.findViewById(R.id.SignIn_LBL_createAccount);
        SignIn_BTN_login = view.findViewById(R.id.SignIn_BTN_login);
    }
}