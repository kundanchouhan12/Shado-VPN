package com.vpn.shadovpn.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieAnimationView;
import com.vpn.shadovpn.R;
import com.vpn.shadovpn.viewmodels.SharedViewModel;

public class HomeFragment extends Fragment {

    private TextView txtServer, txtStatus;
    private Button btnToggle;
    private LottieAnimationView lottieVpn;
    private SharedViewModel viewModel;
    private boolean isConnected = false;

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        txtServer = view.findViewById(R.id.txtServer);
        txtStatus = view.findViewById(R.id.txtStatus);
        btnToggle = view.findViewById(R.id.btnToggle);
        lottieVpn = view.findViewById(R.id.lottieVpn);

        // Load default from SharedPreferences
        SharedPreferences prefs = requireContext().getSharedPreferences("ShadoVPN", requireContext().MODE_PRIVATE);
        String savedServer = prefs.getString("selected_server", "🇸🇬 Singapore");
        txtServer.setText("🌍 " + savedServer);

        // Observe selected server from ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.getSelectedServer().observe(getViewLifecycleOwner(), server -> {
            // ✅ Safely remove any IP info if coming from old logic
            String cleanServer = server.replaceAll("\nIP: .*", "");
            txtServer.setText("🌍 " + cleanServer);
        });

        // Always start as disconnected
        showDisconnectedState();

        btnToggle.setOnClickListener(v -> {
            isConnected = !isConnected;
            if (isConnected) {
                showConnectedState();
            } else {
                showDisconnectedState();
            }
        });

        return view;
    }

    private void showConnectedState() {
        txtStatus.setText("Connected");
        btnToggle.setText("Disconnect");
        lottieVpn.setAnimation(R.raw.vpn_connect_anim);
        lottieVpn.playAnimation();
    }

    private void showDisconnectedState() {
        txtStatus.setText("Disconnected");
        btnToggle.setText("Connect");
        lottieVpn.setAnimation(R.raw.vpn_disconnect_anim);
        lottieVpn.playAnimation();
    }
}
