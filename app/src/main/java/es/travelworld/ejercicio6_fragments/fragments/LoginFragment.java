package es.travelworld.ejercicio6_fragments.fragments;

import static es.travelworld.ejercicio6_fragments.domain.References.KEY_USER;
import static es.travelworld.ejercicio6_fragments.domain.References.LOGIN_ERROR;
import static es.travelworld.ejercicio6_fragments.domain.References.LOGIN_SUCCESSFUL;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import es.travelworld.ejercicio6_fragments.R;
import es.travelworld.ejercicio6_fragments.databinding.FragmentLoginBinding;
import es.travelworld.ejercicio6_fragments.domain.User;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private FragmentLoginBinding binding;
    private User user;
    private OnClickItemLoginFragment listener;

    public interface OnClickItemLoginFragment {
        void loginButton(User user, String code);

        void loginNewAccountButton(User user);
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(User receivedUser) {
        LoginFragment loginFragment = new LoginFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_USER, receivedUser);
        loginFragment.setArguments(bundle);
        return loginFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = getArguments().getParcelable(KEY_USER);
        }
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        setListeners();
        return view;
    }

    private void setListeners() {
        binding.loginForgotPasswordButton.setOnClickListener(this);
        binding.loginNewAccountButton.setOnClickListener(this);
        binding.loginButton.setOnClickListener(this);

        binding.loginInputUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not implemented yet
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateForm();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Not implemented yet
            }
        });

        binding.loginInputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not implemented yet
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateForm();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Not implemented yet
            }
        });
    }

    private void validateForm() {
        binding.loginButton.setEnabled(false);
        boolean userValidation = false;
        boolean passwordValidation = false;

        if (binding.loginInputUser.getText() != null && !binding.loginInputUser.getText().toString().equals("")) {
            userValidation = true;
        }
        if (binding.loginInputPassword.getText() != null && !binding.loginInputPassword.getText().toString().equals("")) {
            passwordValidation = true;
        }

        if (userValidation && passwordValidation) {
            binding.loginButton.setEnabled(true);
        }
    }

    @Override
    public void onClick(View view) {
        if (binding.loginForgotPasswordButton.equals(view)) {
            Snackbar.make(binding.getRoot(), R.string.wip_feature, BaseTransientBottomBar.LENGTH_LONG).show();
        } else if (binding.loginNewAccountButton.equals(view)) {
            listener.loginNewAccountButton(user);
        } else if (binding.loginButton.equals(view)) {
            login();
        }
    }

    private void login() {
        if (Objects.requireNonNull(binding.loginInputPassword.getText()).toString().equals(user.getPassword()) && Objects.requireNonNull(binding.loginInputUser.getText()).toString().equals(user.getName())) {
            listener.loginButton(user, LOGIN_SUCCESSFUL);
        } else {
            listener.loginButton(user, LOGIN_ERROR);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Inicializa el listener con el contexto recibido
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnClickItemLoginFragment) {
            listener = (OnClickItemLoginFragment) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}