package es.travelworld.ejercicio6_fragments;


import static es.travelworld.ejercicio6_fragments.domain.References.LOGIN_FRAGMENT;
import static es.travelworld.ejercicio6_fragments.domain.References.REGISTER_FRAGMENT;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import es.travelworld.ejercicio6_fragments.databinding.ActivityLoginBinding;
import es.travelworld.ejercicio6_fragments.fragments.LoginFragment;
import es.travelworld.ejercicio6_fragments.fragments.RegisterFragment;
import es.travelworld.ejercicio6_fragments.domain.User;

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnClickItemLoginFragment {

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
        //TODO valorar mandar el usuario desde el viewmodel
    }

    private void startLoginFragment() {
        LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentByTag(LOGIN_FRAGMENT);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.loginFragmentFrame.getId(),
                        fragment!=null?fragment:LoginFragment.newInstance(user),
                        LOGIN_FRAGMENT)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    private void startRegisterFragment(User user) {
        RegisterFragment fragment = (RegisterFragment) getSupportFragmentManager().findFragmentByTag(REGISTER_FRAGMENT);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.loginFragmentFrame.getId(),
                        fragment!=null?fragment:RegisterFragment.newInstance(user),
                        REGISTER_FRAGMENT)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //TODO si el fragment en uso es el login sale de la app, si es register invoca a startLoginFragment
    }

    @Override
    public void loginButton(User user, String code) {

    }

    @Override
    public void loginNewAccountButton(User user) {
        startRegisterFragment(user);
    }
}