package com.jay.wifi.ui

import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.lifecycle.Observer
import com.base.library.ui.BaseActivity
import com.jay.wifi.R
import com.jay.wifi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    R.layout.activity_main
) {

    private val wifiAdapter by lazy {
        WifiAdapter()
    }
    private val wifiManager by lazy {
        applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupUi()
        setupObserve()
    }

    private fun setupUi() {
        binding.rvWifi.adapter = wifiAdapter
        binding.switchWifi.isChecked = wifiManager.isWifiEnabled
        setSwitchWifi(binding.switchWifi.isChecked)

        binding.switchWifi.setOnCheckedChangeListener { _, isChecked ->
            setSwitchWifi(isChecked)
        }
    }

    private fun setupObserve() {
        viewModel.wifiItems.observe(this, Observer {
            wifiAdapter.updateItems(it)
        })
    }

    @SuppressLint("NewApi")
    private fun setSwitchWifi(isChecked: Boolean) {
        val wifiStatus =
            if (isChecked) getString(R.string.text_wifi_on)
            else getString(R.string.text_wifi_off)
        binding.switchWifi.text = String.format(getString(R.string.text_wifi_status), wifiStatus)
    }
}