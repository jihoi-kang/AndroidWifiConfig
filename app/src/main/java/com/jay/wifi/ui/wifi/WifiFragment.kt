package com.jay.wifi.ui.wifi

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.base.library.ui.BaseFragment
import com.jay.wifi.R
import com.jay.wifi.databinding.FragmentWifiBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WifiFragment : BaseFragment<FragmentWifiBinding, WifiViewModel>(
    R.layout.fragment_wifi
) {
    private val wifiManager by lazy {
        context?.applicationContext?.getSystemService(Context.WIFI_SERVICE) as WifiManager
    }

    private val wifiAdapter by lazy {
        WifiAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupObserve()
    }

    private fun setupUi() {
        binding.rvWifiList.adapter = wifiAdapter
        binding.btnStartStreaming.isVisible = wifiManager.isWifiEnabled

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            showWifiDialog()
            binding.rvWifiList.isVisible = false
        } else {
            showWifiList()
        }

        binding.btnStartStreaming.setOnClickListener {
            val action = WifiFragmentDirections.actionWifiToStreaming()
            findNavController().navigate(action)
        }
    }

    private fun setupObserve() {
        viewModel.wifiItems.observe(viewLifecycleOwner, {
            wifiAdapter.updateItems(it)
        })
    }

    private fun showWifiDialog() {
        startActivity(Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY))
    }

    private fun showWifiList() { // below version Q
        wifiManager.isWifiEnabled = true
    }

}