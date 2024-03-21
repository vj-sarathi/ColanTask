package com.vjsarathi.colantask.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.vjsarathi.colantask.R
import com.vjsarathi.colantask.data.model.response.Result
import com.vjsarathi.colantask.databinding.FragmentFirstBinding
import com.vjsarathi.colantask.presentation.adapter.RickAndMortyAdapter
import com.vjsarathi.colantask.presentation.listener.GenericClickListener
import com.vjsarathi.colantask.presentation.viewmodel.HomeScreenViewModel
import com.vjsarathi.colantask.uiUtils.ProgressDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment(), GenericClickListener<Result> {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy {
        RickAndMortyAdapter(this@FirstFragment, this)
    }

    private val viewModel by viewModels<HomeScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerview.adapter = adapter
        observers()
    }

    private fun observers() {
        viewModel.isLoading.observe(requireActivity()) { loading ->
            if (loading) ProgressDialog.show(requireContext())
            else ProgressDialog.dismiss()
        }

        viewModel.isError.observe(requireActivity()) { errMsg ->
            Snackbar.make(binding.root, errMsg, Snackbar.LENGTH_SHORT).show()
        }

        viewModel.rickAndMortyResponse.observe(requireActivity()) { data ->
            adapter.setData(data.results)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(item: Result?, position: Int?) {
        item?.let { rickData ->
            findNavController().navigate(
                R.id.action_FirstFragment_to_SecondFragment,
                Bundle().apply {
                    putParcelable("RICK_DATA",rickData)
                })
        }
    }
}