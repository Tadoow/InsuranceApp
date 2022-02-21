package com.example.osagocalculation.presentation.main

import android.graphics.drawable.Drawable
import android.os.Bundle
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

    // TODO: решил оставить даггер на финальную неделю
    private val viewModel: SharedViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val factorsApi = (activity?.application as App).factorsApi
                val factorsStore =
                    FactorsStoreImpl((activity?.application as App).applicationContext)
                val repository = RepositoryImpl(factorsApi, factorsStore)
                val interactor = Interactor(repository)
                return SharedViewModel(interactor) as T
            }
        }
    }

    private val adapter = MainAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.getInitialFactors()

            childFragmentManager.beginTransaction()
                .add(R.id.fragment_container_inner, FormFragment.newInstance())
                .commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    override fun onHeaderClicked() {
        adapter.toggleSection()
    }

    override fun onFragmentStart() {
        binding.buttonCalculateInsurance.isVisible = false
        binding.buttonBuyInsurance.isVisible = true

        configureToolbar(
            getString(R.string.calculate_payment),
            R.style.ToolbarTitleSecondary,
            ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_back, null),
            AppBarLayout.LayoutParams.SCROLL_FLAG_NO_SCROLL
        )

        binding.toolbar.setNavigationOnClickListener {
            childFragmentManager.popBackStack()
            binding.buttonCalculateInsurance.isVisible = true
            binding.buttonBuyInsurance.isVisible = false

            configureToolbar(
                getString(R.string.toolbar_main),
                R.style.ToolbarTitle,
                null,
                AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
            )
        }
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
