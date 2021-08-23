package com.jay.wifi.ui.streaming

import com.base.library.ui.BaseFragment
import com.jay.wifi.R
import com.jay.wifi.databinding.FragmentStreamingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StreamingFragment : BaseFragment<FragmentStreamingBinding, StreamingViewModel>(
    R.layout.fragment_streaming
)