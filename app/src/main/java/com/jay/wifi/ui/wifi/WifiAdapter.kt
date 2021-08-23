package com.jay.wifi.ui.wifi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jay.wifi.R
import com.jay.wifi.databinding.ItemWifiBinding
import com.jay.wifi.model.Wifi

class WifiAdapter : RecyclerView.Adapter<WifiViewHolder>() {
    private val wifiItems = mutableListOf<Wifi>()

    fun updateItems(wifiItems: List<Wifi>) {
        this.wifiItems.addAll(wifiItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WifiViewHolder {
        val binding: ItemWifiBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_wifi,
            parent,
            false,
        )

        return WifiViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WifiViewHolder, position: Int) {
        holder.onBind(wifiItems[position])
    }

    override fun getItemCount(): Int = wifiItems.size

}