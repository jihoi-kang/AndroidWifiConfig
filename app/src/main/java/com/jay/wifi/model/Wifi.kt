package com.jay.wifi.model

import android.net.wifi.SupplicantState

data class Wifi(
    val ssid: String,
    val secure: Boolean,
    val rssi: Int,
    val state: WifiNetworkState,
    val supplicantState: SupplicantState? = null,
    val networkId: Int,
)