package com.example.dennispersaudinventoryapplication.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dennispersaudinventoryapplication.R;
import com.example.dennispersaudinventoryapplication.data.user.User;
import com.example.dennispersaudinventoryapplication.databinding.LoginFragmentBinding;
import com.example.dennispersaudinventoryapplication.ui.NavigationHost;
import com.example.dennispersaudinventoryapplication.utils.StandardMessages;
import com.example.dennispersaudinventoryapplication.utils.Validator;
import com.example.dennispersaudinventoryapplication.vm.MainActivityViewModel;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

public class LoginFragment extends Fragment {

//    private final int counter = 5;
    MainActivityViewModel mainViewModel;
    LoginFragmentBinding loginFragmentBinding;
    User user;


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        loginFragmentBinding = LoginFragmentBinding.inflate(inflater, container, false);

        loginFragmentBinding.buttonLogin.setOnClickListener(v -> {
            try {
                boolean checkLogin = Validator.validateLogin(getPasswordInput(),
                        mainViewModel.getPasswordByName(getUsernameInput()));

                if (checkLogin) {
                    StandardMessages.displayToast(loginFragmentBinding.loginFragment,
                            getString(R.string.toast_loginSuccess));
                    loginFragmentBinding.getRoot().removeAllViews();
                    ((NavigationHost) requireActivity()).navigateTo(
                            getDataFragment(), true); // Navigate to the next Fragment
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
                        mainViewModel.getUsernameByName(getUsernameInput()));
                boolean checkNotEmpty = Validator.validateNotEmpty(getUsernameInput(), getPasswordInput());
                boolean checkLength = Validator.validatePasswordLength(getPasswordInput());

                if (checkRegistration) {
                    if (checkNotEmpty) {
                        if (checkLength) {
                            user.setUserName(getUsernameInput());
                            user.setUserPassword(getPasswordInput());
                            mainViewModel.insertUser(user.getInstance());
                            StandardMessages.displayToast(loginFragmentBinding.loginFragment,
                                    getString(R.string.toast_createAccountSuccess));
                        } else {
                            loginFragmentBinding.etPassword.setError("Enter a 6 character password.");
                        }
                    } else {
                        loginFragmentBinding.etUsername.setError("Enter a username.");
                        loginFragmentBinding.etPassword.setError("Enter a 6 character password.");
                    }
                } else {
                    loginFragmentBinding.etUsername.setError("Username already exists.");
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

    private DataFragment getDataFragment() {
        return new DataFragment();
    }

    private String getUsernameInput() {
        return Objects.requireNonNull(loginFragmentBinding.etUsername.getText()).toString();
    }

    private String getPasswordInput() {
        return Objects.requireNonNull(loginFragmentBinding.etPassword.getText()).toString();
    }

    public static LoginFragment getLoginFragment() {
        return new LoginFragment();
    }
}