package com.example.dennispersaudinventoryapplication.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dennispersaudinventoryapplication.Models.User;
import com.example.dennispersaudinventoryapplication.R;
import com.example.dennispersaudinventoryapplication.ViewModel.MainActivityViewModel;
import com.example.dennispersaudinventoryapplication.databinding.LoginFragmentBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class LoginFragment extends Fragment {

    private final int counter = 5;
    LoginFragmentBinding loginFragmentBinding;
    User userData;
    private MainActivityViewModel mainViewModel;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        loginFragmentBinding = LoginFragmentBinding.inflate(inflater, container, false);
        mainViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        loginFragmentBinding.buttonLogin.setOnClickListener(v -> {
            try {

                if (getPasswordInput().equals(mainViewModel.getPasswordByName(getUsernameInput()))) {
                    Snackbar.make(loginFragmentBinding.loginFragment, R.string.toast_loginSuccess,
                            Snackbar.LENGTH_SHORT).show();
                    loginFragmentBinding.getRoot().removeAllViews();
                    ((NavigationHost) requireActivity()).navigateTo(
                            new DataFragment(), false); // Navigate to the next Fragment
                } else {
                    loginFragmentBinding.etPassword.setError(getString(R.string.toast_loginFailed));
                }
            } catch (Exception e) {
                Snackbar.make(loginFragmentBinding.loginFragment, Objects.requireNonNull(e.getMessage()),
                        Snackbar.LENGTH_SHORT).show();
            }
        });

        loginFragmentBinding.buttonCreateAccount.setOnClickListener(v -> {
            try {
                if (!getUsernameInput().equals(mainViewModel.getUsernameByName(getUsernameInput()))) {
                    if (!getUsernameInput().isEmpty() && !getPasswordInput().isEmpty()) {
                        if (getPasswordInput().length() > 5) {

                            userData = new User(getUsernameInput(), getPasswordInput());
                            mainViewModel.insertUser(userData);
                            Snackbar.make(loginFragmentBinding.loginFragment, R.string.toast_createAccountSuccess,
                                    Snackbar.LENGTH_SHORT).show();

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

                Snackbar.make(loginFragmentBinding.loginFragment, Objects.requireNonNull(e.getMessage()),
                        Snackbar.LENGTH_SHORT).show();
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

    private String getUsernameInput() {
        return Objects.requireNonNull(loginFragmentBinding.etUsername.getText()).toString();
    }

    private String getPasswordInput() {
        return Objects.requireNonNull(loginFragmentBinding.etPassword.getText()).toString();
    }
}
