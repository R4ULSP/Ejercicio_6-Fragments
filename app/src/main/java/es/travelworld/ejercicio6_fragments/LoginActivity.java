package es.travelworld.ejercicio6_fragments;


import static es.travelworld.ejercicio6_fragments.domain.References.KEY_USER;
import static es.travelworld.ejercicio6_fragments.domain.References.LOGIN_FRAGMENT;
import static es.travelworld.ejercicio6_fragments.domain.References.LOGIN_SUCCESSFUL;
import static es.travelworld.ejercicio6_fragments.domain.References.REGISTER_FRAGMENT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import es.travelworld.ejercicio6_fragments.databinding.ActivityLoginBinding;
import es.travelworld.ejercicio6_fragments.fragments.LoginErrorFragment;
import es.travelworld.ejercicio6_fragments.fragments.LoginFragment;
import es.travelworld.ejercicio6_fragments.fragments.RegisterFragment;
import es.travelworld.ejercicio6_fragments.domain.User;

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnClickItemLoginFragment, RegisterFragment.OnClickItemRegisterFragment {

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
    }

    private void startLoginFragment() {
        LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentByTag(LOGIN_FRAGMENT);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.loginFragmentFrame.getId(),
                        fragment != null ? fragment : LoginFragment.newInstance(user),
                        LOGIN_FRAGMENT)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    private void startRegisterFragment(User user) {
        RegisterFragment fragment = (RegisterFragment) getSupportFragmentManager().findFragmentByTag(REGISTER_FRAGMENT);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.loginFragmentFrame.getId(),
                        fragment != null ? fragment : RegisterFragment.newInstance(user),
                        REGISTER_FRAGMENT)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //TODO si el fragment en uso es el login sale de la app, si es register invoca a startLoginFragment
        if (getSupportFragmentManager().findFragmentByTag(LOGIN_FRAGMENT) != null &&
                getSupportFragmentManager().findFragmentByTag(LOGIN_FRAGMENT).isVisible()) {
            finish();
        }
    }

    @Override
    public void loginButton(User user, String code) {
        if(code == LOGIN_SUCCESSFUL){
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra(KEY_USER, user);
            startActivity(intent);
        }else{
            FragmentManager fragmentManager = getSupportFragmentManager();
            LoginErrorFragment loginErrorFragment = LoginErrorFragment.newInstance();
            loginErrorFragment.show(fragmentManager,null);
        }
    }

    @Override
    public void loginNewAccountButton(User user) {
        startRegisterFragment(user);
    }

    @Override
    public void registerJoinButton(User user) {
        this.user = user;
        startLoginFragment();
        //TODO enviar el usuario al login con los datos del formulario
        Snackbar.make(binding.getRoot(), "Nombre: " + user.getName() + "  Apellidos: " + user.getLastname() + "  Edad:" + user.getAgeGroup(), BaseTransientBottomBar.LENGTH_LONG).show();

    }
}