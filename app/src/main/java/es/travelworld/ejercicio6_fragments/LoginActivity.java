package es.travelworld.ejercicio6_fragments;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import es.travelworld.ejercicio6_fragments.databinding.ActivityLoginBinding;
import es.travelworld.ejercicio6_fragments.fragments.LoginFragment;
import es.travelworld.ejercicio6_fragments.fragments.RegisterFragment;
import es.travelworld.ejercicio6_fragments.domain.User;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = new User();
        user.setName("pruebas");
        user.setLastname("hola");

        startLoginFragment();

        //TODO Iniciar el fragment del register
        //TODO hacer pila de fragments
    }

    private void startLoginFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.loginFragmentFrame.getId(),LoginFragment.newInstance(user),"loginFragment")
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    private void startRegisterFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.loginFragmentFrame.getId(),new RegisterFragment(),"registerFragment")
                .commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //TODO si el fragment en uso es el login sale de la app, si es register invoca a startLoginFragment
    }
}