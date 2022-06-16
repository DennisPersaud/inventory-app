package com.example.dennispersaudinventoryapplication.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dennispersaudinventoryapplication.R;
import com.example.dennispersaudinventoryapplication.db.model.User;
import com.example.dennispersaudinventoryapplication.databinding.LoginFragmentBinding;
import com.example.dennispersaudinventoryapplication.ui.NavigationHost;
import com.example.dennispersaudinventoryapplication.utils.StandardMessages;
import com.example.dennispersaudinventoryapplication.utils.Validator;
import com.example.dennispersaudinventoryapplication.vm.MainActivityViewModel;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    LoginFragmentBinding loginFragmentBinding;
    User user;


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        loginFragmentBinding = LoginFragmentBinding.inflate(inflater, container, false);

        loginFragmentBinding.buttonLogin.setOnClickListener(v -> {
            try {
                boolean checkLogin = Validator.validateLogin(getPasswordInput(),
                        initVM().getPasswordByName(getUsernameInput()));

                if (checkLogin) {
                    StandardMessages.displayToast(loginFragmentBinding.loginFragment,
                            getString(R.string.toast_loginSuccess));
                    loginFragmentBinding.getRoot().removeAllViews();
                    ((NavigationHost) requireActivity()).navigateTo(
                            new DataFragment(), true); // Navigate to the next Fragment
                } else {
                    loginFragmentBinding.etPassword.setError(getString(R.string.toast_loginFailed));
                }
            } catch (Exception e) {
                StandardMessages.displayToast(loginFragmentBinding.loginFragment,
                        Objects.requireNonNull(e.getMessage()));
            }
        });

        loginFragmentBinding.buttonCreateAccount.setOnClickListener(v -> {
            try {
                boolean checkRegistration = Validator.validateLogin(getUsernameInput(),
                        initVM().getUsernameByName(getUsernameInput()));
                boolean checkNotEmpty = Validator.validateNotEmpty(getUsernameInput(), getPasswordInput());
                boolean checkLength = Validator.validatePasswordLength(getPasswordInput());

                if (checkRegistration && checkNotEmpty && checkLength) {

                    user.setUserName(getUsernameInput());
                    user.setUserPassword(getPasswordInput());
                    initVM().insertUser(user.getInstance());
                    StandardMessages.displayToast(loginFragmentBinding.loginFragment,
                            getString(R.string.toast_createAccountSuccess));
                } else {
                    loginFragmentBinding.etUsername.setError("Error: Please try again.");
                }
            } catch (Exception e) {
                StandardMessages.displayToast(loginFragmentBinding.loginFragment,
                        Objects.requireNonNull(e.getMessage()));
            }
        });
        return loginFragmentBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        if (loginFragmentBinding != null) {
            loginFragmentBinding.getRoot().removeAllViews();
        }
        super.onDestroyView();
    }

    private MainActivityViewModel initVM() {
        return new ViewModelProvider(this).get(MainActivityViewModel.class);
    }

    private String getUsernameInput() {
        return Objects.requireNonNull(loginFragmentBinding.etUsername.getText()).toString();
    }

    private String getPasswordInput() {
        return Objects.requireNonNull(loginFragmentBinding.etPassword.getText()).toString();
    }

}