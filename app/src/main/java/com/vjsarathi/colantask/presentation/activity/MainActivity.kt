package com.vjsarathi.colantask.presentation.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.vjsarathi.colantask.databinding.ActivityMainBinding
import com.vjsarathi.colantask.presentation.viewmodel.HomeScreenViewModel
import com.vjsarathi.colantask.uiUtils.ProgressDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseColanActivity() {

    private lateinit var _binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
    }

}