package com.jay.wifi.ui

import com.base.library.ui.BaseActivity
import com.jay.wifi.R
import com.jay.wifi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    R.layout.activity_main
)