package com.example.osagocalculation.presentation.insurances

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.osagocalculation.R
import com.example.osagocalculation.databinding.FragmentInsurancesBinding
import com.example.osagocalculation.domain.entities.Factors
import com.example.osagocalculation.domain.entities.InsuranceDomain
import com.example.osagocalculation.getFragmentComponent
import com.example.osagocalculation.presentation.insurances.adapter.InsurancesAdapter
import com.example.osagocalculation.presentation.insurances.listener.OnInsuranceClickListener
import com.example.osagocalculation.presentation.main.MainFragment

class InsurancesFragment : Fragment(R.layout.fragment_insurances), OnInsuranceClickListener {

    private lateinit var binding: FragmentInsurancesBinding
    private val factors: List<Factors> by lazy {
        requireArguments().getParcelableArrayList(FACTORS_KEY)!!
    }

    private val viewModel: InsurancesViewModel by viewModels { getFragmentComponent().getViewModelFactory() }

    private val adapter = InsurancesAdapter(this)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigateToMainFragment()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.loadInsurances(factors)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInsurancesBinding.bind(view)

        binding.recyclerInsurances.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerInsurances.adapter = adapter

        observeLiveData()
    }

    override fun onInsuranceClick(insurance: InsuranceDomain) {
        setFragmentResult(REQUEST_KEY, bundleOf(ITEM_KEY to insurance))
        navigateToMainFragment()
    }

    private fun navigateToMainFragment() {
        val fragment =
            activity?.supportFragmentManager?.findFragmentByTag(MainFragment.TAG) as MainFragment
        fragment.backPressed()
    }

    private fun observeLiveData() {
        viewModel.insuranceLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        }
        viewModel.progressLiveData.observe(viewLifecycleOwner) {
            binding.shimmerInsurances.isVisible = it
        }
    }

    companion object {

        const val TAG = "InsurancesFragment"

        private const val FACTORS_KEY = "FACTORS_KEY"
        private const val REQUEST_KEY = "REQUEST_KEY"
        private const val ITEM_KEY = "ITEM_KEY"

        fun newInstance(factors: List<Factors>): Fragment {
            val fragment = InsurancesFragment()
            fragment.arguments = Bundle().apply {
                putParcelableArrayList(FACTORS_KEY, factors as ArrayList)
            }
            return fragment
        }
    }

}
