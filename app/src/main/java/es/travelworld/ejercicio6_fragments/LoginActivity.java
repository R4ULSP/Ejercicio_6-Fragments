package es.travelworld.ejercicio6_fragments;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import es.travelworld.ejercicio6_fragments.databinding.ActivityLoginBinding;
import es.travelworld.ejercicio6_fragments.fragments.LoginFragment;
import es.travelworld.ejercicio6_fragments.fragments.RegisterFragment;
import es.travelworld.ejercicio6_fragments.tools.User;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private User user;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        user = new User();

        startLoginFragment();

        //TODO Iniciar el fragment del register
    }

    private void startLoginFragment() {
        LoginFragment loginFragment = new LoginFragment();
        fragmentTransaction.add(R.id.login_fragment_frame,loginFragment);
        fragmentTransaction.commit();
    }

    private void startRegisterFragment() {
        RegisterFragment registerFragment = new RegisterFragment();
        fragmentTransaction.add(R.id.login_fragment_frame,registerFragment);
        fragmentTransaction.commit();
    }
}