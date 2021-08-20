package com.jay.wifi.ui

import androidx.recyclerview.widget.RecyclerView
import com.jay.wifi.databinding.ItemWifiBinding
import com.jay.wifi.model.Wifi

class WifiViewHolder(
    private val binding: ItemWifiBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(wifi: Wifi) {
        binding.tvSsid.text = wifi.ssid
        binding.executePendingBindings()
    }

}