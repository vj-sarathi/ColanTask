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
    private lateinit var viewModel: HomeScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        viewModel = ViewModelProvider(this)[HomeScreenViewModel::class.java]

        observers()
    }

    private fun observers() {
        viewModel.isLoading.observe(this) { loading ->
            if (loading) ProgressDialog.show(this)
            else ProgressDialog.dismiss()
        }

        viewModel.isError.observe(this) { errMsg ->
            showSnackBar(_binding.root, errMsg)
        }

        viewModel.rickAndMortyResponse.observe(this) {

        }
    }
}