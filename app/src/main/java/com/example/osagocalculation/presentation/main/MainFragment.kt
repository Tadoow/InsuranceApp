package com.example.osagocalculation.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.osagocalculation.App
import com.example.osagocalculation.R
import com.example.osagocalculation.data.RepositoryImpl
import com.example.osagocalculation.data.store.FactorsStoreImpl
import com.example.osagocalculation.databinding.FragmentMainBinding
import com.example.osagocalculation.domain.Interactor
import com.example.osagocalculation.domain.entities.Factors
import com.example.osagocalculation.presentation.form.FormFragment
import com.example.osagocalculation.presentation.insurances.InsurancesFragment
import com.example.osagocalculation.presentation.insurances.listener.OnInsurancesFragmentListener
import com.example.osagocalculation.presentation.main.adapter.MainAdapter
import com.example.osagocalculation.presentation.main.listener.OnItemClickListener
import com.google.android.material.appbar.AppBarLayout

class MainFragment : Fragment(R.layout.fragment_main), OnItemClickListener,
    OnInsurancesFragmentListener {

    private lateinit var binding: FragmentMainBinding
    private lateinit var factors: List<Factors>

    // TODO: прикрутить к проекту даггер
    private val viewModel: SharedViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val factorsApi = (activity?.application as App).factorsApi
                val factorsStore =
                    FactorsStoreImpl((activity?.application as App).sharedPreferences)
                val repository = RepositoryImpl(factorsApi, factorsStore)
                val interactor = Interactor(repository)
                return SharedViewModel(interactor) as T
            }
        }
    }

    private val adapter = MainAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        if (savedInstanceState == null) {
            viewModel.getInitialFactors()

            childFragmentManager.beginTransaction()
                .add(R.id.fragment_container_inner, FormFragment.newInstance())
                .commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
        binding = FragmentMainBinding.bind(view)

        observeLiveData()

        binding.recyclerFactors.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerFactors.adapter = adapter

        binding.buttonCalculateInsurance.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_inner, InsurancesFragment.newInstance(factors))
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onFragmentStart() {
        binding.buttonCalculateInsurance.isVisible = false
        binding.buttonBuyInsurance.isVisible = true
        binding.toolbar.title = getString(R.string.calculate_payment)
        binding.toolbar.setTitleTextAppearance(context, R.style.ToolbarTitleSecondary)
        binding.toolbar.navigationIcon =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_back, null)
        (binding.toolbar.layoutParams as AppBarLayout.LayoutParams).scrollFlags =
            AppBarLayout.LayoutParams.SCROLL_FLAG_NO_SCROLL

        binding.toolbar.setNavigationOnClickListener {
            childFragmentManager.popBackStack()
            binding.buttonCalculateInsurance.isVisible = true
            binding.buttonBuyInsurance.isVisible = false
            binding.toolbar.title = getString(R.string.toolbar_main)
            binding.toolbar.setTitleTextAppearance(context, R.style.ToolbarTitle)
            binding.toolbar.navigationIcon = null
            (binding.toolbar.layoutParams as AppBarLayout.LayoutParams).scrollFlags =
                AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
        }
    }

    override fun onHeaderClicked() {
        adapter.toggleSection()
    }

    private fun observeLiveData() {
        viewModel.factorsLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)
            factors = it
        }
        viewModel.progressLiveData.observe(viewLifecycleOwner) {
            binding.buttonCalculateInsurance.isEnabled = it
            binding.buttonCalculateInsurance.text =
                if (it) getString(R.string.calculate_insurance) else ""
            binding.progress.isVisible = !it
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
        }
    }

    companion object {

        const val TAG = "MainFragment"

        fun newInstance(): Fragment {
            return MainFragment()
        }
    }

}
