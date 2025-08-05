package com.vpn.shadovpn;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vpn.shadovpn.fragments.HomeFragment;
import com.vpn.shadovpn.fragments.ProfileFragment;
import com.vpn.shadovpn.fragments.ServerFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottomNavigation);

        // Set default fragment
        loadFragment(new HomeFragment());

        bottomNavigation.setOnItemSelectedListener(item -> {
            Fragment selected = null;

            int id = item.getItemId();

            if (id == R.id.menu_home) {
                selected = new HomeFragment();
            } else if (id == R.id.menu_servers) {
                selected = new ServerFragment();
            } else if (id == R.id.menu_profile) {
                selected = new ProfileFragment(); // ✅ Corrected
            }

            if (selected != null) {
                loadFragment(selected);
                return true;
            }

            return false;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
