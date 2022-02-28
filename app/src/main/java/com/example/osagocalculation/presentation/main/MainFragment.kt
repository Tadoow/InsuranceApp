package com.example.osagocalculation.presentation.main

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.osagocalculation.R
import com.example.osagocalculation.databinding.FragmentMainBinding
import com.example.osagocalculation.domain.entities.Factors
import com.example.osagocalculation.domain.entities.InsuranceDomain
import com.example.osagocalculation.getFragmentComponent
import com.example.osagocalculation.presentation.dialoginsurance.DialogInsuranceFragment
import com.example.osagocalculation.presentation.form.FormFragment
import com.example.osagocalculation.presentation.insurances.InsurancesFragment
import com.example.osagocalculation.presentation.main.adapter.MainAdapter
import com.example.osagocalculation.presentation.main.listener.OnFactorClickListener
import com.google.android.material.appbar.AppBarLayout

class MainFragment : Fragment(R.layout.fragment_main), OnFactorClickListener {

    private lateinit var binding: FragmentMainBinding
    private lateinit var factors: List<Factors>

    private val viewModel: SharedViewModel by viewModels { getFragmentComponent().getViewModelFactory() }

    private val adapter = MainAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.getInitialFactors()

            childFragmentManager.beginTransaction()
                .add(R.id.fragment_container_inner, FormFragment.newInstance())
                .commit()
        }

        childFragmentManager.setFragmentResultListener(REQUEST_KEY, this) { _, bundle ->
            val selectedInsurance = bundle.getParcelable<InsuranceDomain>(ITEM_KEY)!!
            val dialogInsuranceFragment = DialogInsuranceFragment.newInstance(selectedInsurance)
            dialogInsuranceFragment.show(childFragmentManager, DialogInsuranceFragment.TAG)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        observeLiveData()

        binding.recyclerFactors.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerFactors.adapter = adapter

        val fragment = childFragmentManager.findFragmentByTag(InsurancesFragment.TAG)
        if (fragment != null) {
            buttonCalculatePressed()
        }

        binding.buttonCalculateInsurance.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container_inner,
                    InsurancesFragment.newInstance(factors),
                    InsurancesFragment.TAG
                )
                .addToBackStack(null)
                .commit()
            buttonCalculatePressed()
        }

        binding.toolbar.setNavigationOnClickListener {
            backPressed()
        }
    }

    override fun onHeaderClicked() {
        adapter.toggleSection()
    }

    fun backPressed() {
        childFragmentManager.popBackStack()
        binding.buttonCalculateInsurance.isVisible = true
        binding.buttonBuyInsurance.isVisible = false

        configureToolbar(
            getString(R.string.toolbar_main),
            R.style.ToolbarTitle,
            null,
            AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP
        )
    }

    private fun buttonCalculatePressed() {
        binding.buttonCalculateInsurance.isVisible = false
        binding.buttonBuyInsurance.isVisible = true

        configureToolbar(
            getString(R.string.calculate_payment),
            R.style.ToolbarTitleSecondary,
            ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_back, null),
            AppBarLayout.LayoutParams.SCROLL_FLAG_NO_SCROLL
        )
    }

    private fun configureToolbar(
        title: String,
        style: Int,
        navigationIcon: Drawable?,
        scrollFlags: Int
    ) {
        binding.toolbar.title = title
        binding.toolbar.setTitleTextAppearance(context, style)
        binding.toolbar.navigationIcon = navigationIcon
        (binding.toolbar.layoutParams as AppBarLayout.LayoutParams).scrollFlags = scrollFlags
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
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {

        const val TAG = "MainFragment"
        private const val REQUEST_KEY = "REQUEST_KEY"
        private const val ITEM_KEY = "ITEM_KEY"

        fun newInstance(): Fragment {
            return MainFragment()
        }
    }

}
