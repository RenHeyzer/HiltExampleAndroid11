package com.radin.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.radin.presentation.base.BaseFragment
import com.radin.presentation.databinding.FragmentCountriesBinding
import com.radin.presentation.extensions.setOnQueryTextListener
import com.radin.presentation.ui.adapters.CountriesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesFragment : BaseFragment() {

    private var _binding: FragmentCountriesBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<CountriesViewModel>()
    private val countriesAdapter = CountriesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCountriesBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
        collectStates()
    }

    private fun initialize() {
        binding.rvCountries.adapter = countriesAdapter
    }

    private fun setupListeners() {
        binding.svCountry.setOnQueryTextListener(
            onTextChange = {
                viewModel.setCountryName(it)
                viewModel.setFullText(binding.switchFullText.isChecked)
            }
        )
    }

    private fun collectStates() {
        viewModel.countriesState.collectState(
            loading = {
                binding.progressBar.isVisible = true
            },
            error = {
                binding.progressBar.isVisible = false
            },
            success = {
                binding.progressBar.isVisible = false
                countriesAdapter.submitList(it)
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}