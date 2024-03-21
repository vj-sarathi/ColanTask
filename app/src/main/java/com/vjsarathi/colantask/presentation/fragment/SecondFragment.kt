package com.vjsarathi.colantask.presentation.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.vjsarathi.colantask.Constants.Constants
import com.vjsarathi.colantask.R
import com.vjsarathi.colantask.data.model.response.Result
import com.vjsarathi.colantask.databinding.FragmentSecondBinding
import com.vjsarathi.colantask.uiUtils.BindingAdapter.loadImageWithURL

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rickData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(Constants.RICK_DATA, Result::class.java)
        } else {
            arguments?.getParcelable(Constants.RICK_DATA)
        }
        viewModel.setData(rickData)
        viewModel.getRickData()?.image?.let { imageUrl ->
            binding.ivHeader.loadImageWithURL(imageUrl)
        }

        binding.tvName.text = resources.getString(R.string.name, viewModel.getRickData()?.name ?: R.string.no_data)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}