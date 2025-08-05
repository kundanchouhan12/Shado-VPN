package com.vpn.shadovpn.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vpn.shadovpn.R;
import com.vpn.shadovpn.adapters.ServerAdapter;
import com.vpn.shadovpn.models.ServerModel;
import com.vpn.shadovpn.viewmodels.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

public class ServerFragment extends Fragment {

    private RecyclerView recyclerView;
    private ServerAdapter serverAdapter;
    private List<ServerModel> serverList;
    private SharedViewModel viewModel;

    public ServerFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_server, container, false);

        // ViewModel initialization
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        recyclerView = view.findViewById(R.id.recyclerServers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        serverList = new ArrayList<>();
        loadDummyServers();

        serverAdapter = new ServerAdapter(serverList, getContext());
        recyclerView.setAdapter(serverAdapter);

        // Handle server selection
        serverAdapter.setOnItemClickListener(serverModel -> {
            String serverName = serverModel.getCountryName(); // e.g., 🇺🇸 United States
            String dummyIp = "192.168.43.21"; // you can randomize or generate later
            String finalText = serverName + "\nIP: " + dummyIp;

            // Save in SharedPreferences (optional)
            SharedPreferences prefs = requireContext().getSharedPreferences("ShadoVPN", Context.MODE_PRIVATE);
            prefs.edit().putString("selected_server", finalText).apply();

            // Update LiveData
            viewModel.setSelectedServer(finalText);

            // Toast
            Toast.makeText(getContext(), serverName + " selected", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    private void loadDummyServers() {
        serverList.add(new ServerModel("🇺🇸 United States", 45));
        serverList.add(new ServerModel("🇩🇪 Germany", 78));
        serverList.add(new ServerModel("🇸🇬 Singapore", 25));
        serverList.add(new ServerModel("🇮🇳 India", 210));
        serverList.add(new ServerModel("🇬🇧 United Kingdom", 102));
        serverList.add(new ServerModel("🇯🇵 Japan", 350));
    }
}
