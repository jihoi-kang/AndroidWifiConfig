package com.jay.wifi.ui.wifi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.base.library.ui.BaseViewModel
import com.jay.wifi.model.Wifi
import com.jay.wifi.model.WifiNetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WifiViewModel @Inject constructor() : BaseViewModel() {

    private val _wifiItems = MutableLiveData<List<Wifi>>()
    val wifiItems: LiveData<List<Wifi>> get() = _wifiItems

    init {
        _wifiItems.value = getDatas()
    }

    private fun getDatas(): List<Wifi> =
        listOf(
            Wifi("Jihoi", true, 0, WifiNetworkState.CONNECTED, null, 0),
            Wifi("Becon", true, 0, WifiNetworkState.CONNECTED, null, 0),
            Wifi("Becon5G", true, 0, WifiNetworkState.CONNECTED, null, 0),
            Wifi("derkwoo_ap", true, 0, WifiNetworkState.CONNECTED, null, 0),
            Wifi("derkwoo_ap_5g", true, 0, WifiNetworkState.CONNECTED, null, 0),
            Wifi("borisgym2", true, 0, WifiNetworkState.CONNECTED, null, 0),
            Wifi("iptime", true, 0, WifiNetworkState.CONNECTED, null, 0),
        )

}