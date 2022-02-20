package com.example.osagocalculation.presentation.insurances

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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
import com.example.osagocalculation.databinding.FragmentInsurancesBinding
import com.example.osagocalculation.domain.Interactor
import com.example.osagocalculation.domain.entities.Factors
import com.example.osagocalculation.presentation.insurances.adapter.InsurancesAdapter
import com.example.osagocalculation.presentation.insurances.listener.OnInsurancesFragmentListener

class InsurancesFragment : Fragment(R.layout.fragment_insurances) {

    private lateinit var binding: FragmentInsurancesBinding
    private val factors: List<Factors> by lazy {
        requireArguments().getParcelableArrayList(
            FACTORS_KEY
        )!!
    }

    private val viewModel: InsurancesViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val factorsApi = (activity?.application as App).factorsApi
                val factorsStore =
                    FactorsStoreImpl((activity?.application as App).sharedPreferences)
                val repository = RepositoryImpl(factorsApi, factorsStore)
                val interactor = Interactor(repository)
                return InsurancesViewModel(interactor) as T
            }

        }
    }

    private val adapter = InsurancesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")

        if (savedInstanceState == null) {
            viewModel.loadInsurances(factors)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
        binding = FragmentInsurancesBinding.bind(view)

        binding.recyclerInsurances.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerInsurances.adapter = adapter

        observeLiveData()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
        (parentFragment as OnInsurancesFragmentListener).onFragmentStart()
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

    private fun observeLiveData() {
        viewModel.insuranceLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
        }
        viewModel.progressLiveData.observe(viewLifecycleOwner) {
            binding.shimmerInsurances.isVisible = it
        }
    }

    companion object {

        const val TAG = "InsurancesFragment"

        private const val FACTORS_KEY = "FACTORS_KEY"

        fun newInstance(factors: List<Factors>): Fragment {
            val fragment = InsurancesFragment()
            fragment.arguments = Bundle().apply {
                putParcelableArrayList(FACTORS_KEY, factors as ArrayList)
            }
            return fragment
        }
    }

}
