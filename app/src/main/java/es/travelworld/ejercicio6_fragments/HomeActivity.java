package es.travelworld.ejercicio6_fragments;

import static es.travelworld.ejercicio6_fragments.domain.References.HOME_FRAGMENT;
import static es.travelworld.ejercicio6_fragments.domain.References.KEY_USER;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import es.travelworld.ejercicio6_fragments.databinding.ActivityHomeBinding;
import es.travelworld.ejercicio6_fragments.domain.User;
import es.travelworld.ejercicio6_fragments.fragments.HomeFragment;
import es.travelworld.ejercicio6_fragments.fragments.WipFragment;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = getIntent().getParcelableExtra(KEY_USER);

        setSupportActionBar(binding.toolbar);

        startHomeFragment();
    }

    private void startHomeFragment() {
        HomeFragment fragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HOME_FRAGMENT);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.homeFragmentFrame.getId(),
                        fragment != null ? fragment : HomeFragment.newInstance(user),
                        HOME_FRAGMENT)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.eurodisney) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.disneylandparis.com/es-es/"));
            startActivity(intent);
        }

        if (item.getItemId() == R.id.rentacar) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            WipFragment wipFragment = WipFragment.newInstance();
            wipFragment.show(fragmentManager, null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

}