package com.vpn.shadovpn.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<String> selectedServer = new MutableLiveData<>();

    public void setSelectedServer(String serverInfo) {
        selectedServer.setValue(serverInfo);
    }

    public LiveData<String> getSelectedServer() {
        return selectedServer;
    }
}
