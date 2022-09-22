package com.example.dennispersaudinventoryapplication.feature_inventory.presentation.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dennispersaudinventoryapplication.R;
import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.User;
import com.example.dennispersaudinventoryapplication.databinding.LoginFragmentBinding;
import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.value_objects.Password;
import com.example.dennispersaudinventoryapplication.feature_inventory.domain.model.value_objects.Username;
import com.example.dennispersaudinventoryapplication.feature_inventory.presentation.NavigationHost;
import com.example.dennispersaudinventoryapplication.feature_inventory.presentation.inventory.DataFragment;
import com.example.dennispersaudinventoryapplication.feature_inventory.utils.StandardMessages;
import com.example.dennispersaudinventoryapplication.feature_inventory.presentation.vm.MainActivityViewModel;

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
                if (!getUsernameInput().toString().isEmpty() && !getPasswordInput().toString().isEmpty()) {
                    if (getPasswordInput().toString().equals(initVM().getPasswordByName(getPasswordInput().toString()))) {
                        StandardMessages.displayToast(loginFragmentBinding.loginFragment,
                                getString(R.string.toast_loginSuccess));
                        loginFragmentBinding.getRoot().removeAllViews();
                        ((NavigationHost) requireActivity()).navigateTo(
                                new DataFragment(), true); // Navigate to the next Fragment

                    } else {
                        loginFragmentBinding.etPassword.setError("Incorrect Password");
                    }
                } else {
                    loginFragmentBinding.etUsername.setError("Error: Enter username.");
                    loginFragmentBinding.etPassword.setError("Error: Enter password.");
                }
//                boolean checkLogin = Validator.validateLogin(getPasswordInput(),
//                        initVM().getPasswordByName(getUsernameInput()));
//
//                if (checkLogin) {
//                    StandardMessages.displayToast(loginFragmentBinding.loginFragment,
//                            getString(R.string.toast_loginSuccess));
//                    loginFragmentBinding.getRoot().removeAllViews();
//                    ((NavigationHost) requireActivity()).navigateTo(
//                            new DataFragment(), true); // Navigate to the next Fragment
//                } else {
//                    loginFragmentBinding.etPassword.setError(getString(R.string.toast_loginFailed));
//                }
            } catch (Exception e) {
                StandardMessages.displayToast(loginFragmentBinding.loginFragment,
                        Objects.requireNonNull(e.getMessage()));
            }
        });

        loginFragmentBinding.buttonCreateAccount.setOnClickListener(v -> {
            try {

                if (!getUsernameInput().toString().isEmpty() && !getPasswordInput().toString().isEmpty()) {
                    if (getPasswordInput().toString().length() <= 6) {
                        Log.d("DEBUG", String.valueOf(getUsernameInput()));
                        Log.d("DEBUG", String.valueOf(getPasswordInput()));
                        user.setUserName(getUsernameInput());
                        user.setUserPassword(getPasswordInput());
                        initVM().insertUser(user);
                        StandardMessages.displayToast(loginFragmentBinding.loginFragment,
                                getString(R.string.toast_createAccountSuccess));
                    } else {
                        loginFragmentBinding.etPassword.setError("Password must be 6 characters.");
                    }
                } else {

                    loginFragmentBinding.etUsername.setError("Error: Enter username.");
                    loginFragmentBinding.etPassword.setError("Error: Enter password.");
                }
//                boolean checkRegistration = Validator.validateLogin(getUsernameInput(),
//                        initVM().getUsernameByName(getUsernameInput()));
//                boolean checkNotEmpty = Validator.validateNotEmpty(getUsernameInput(), getPasswordInput());
//                boolean checkLength = Validator.validatePasswordLength(getPasswordInput());
//
//                if (checkRegistration && checkNotEmpty && checkLength) {
//
//                    user.setUserName(getUsernameInput());
//                    user.setUserPassword(getPasswordInput());
//                    initVM().insertUser(user.getInstance());
//                    StandardMessages.displayToast(loginFragmentBinding.loginFragment,
//                            getString(R.string.toast_createAccountSuccess));
//                } else {
//                    loginFragmentBinding.etUsername.setError("Error: Please try again.");
//                }
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

    private Username getUsernameInput() {

        // TODO: Convert from String to Username
        return new User(loginFragmentBinding.etUsername.getText(), loginFragmentBinding.etPassword.getText());
//        return Objects.requireNonNull(loginFragmentBinding.etUsername.getText()).toString();
    }

    private Password getPasswordInput() {

        // TODO: Convert from String to Password
        return Objects.requireNonNull(loginFragmentBinding.etPassword.getText()).toString();
    }

}