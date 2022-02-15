package com.example.osagocalculation.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
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
import com.example.osagocalculation.presentation.form.FormFragment
import com.example.osagocalculation.presentation.main.adapter.MainAdapter
import com.example.osagocalculation.presentation.main.listener.OnItemClickListener

class MainFragment : Fragment(R.layout.fragment_main), OnItemClickListener {

    private lateinit var binding: FragmentMainBinding

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

        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        val actionBar = (activity as MainActivity).supportActionBar
        actionBar?.title = getString(R.string.toolbar_main)

        binding.recyclerFactors.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerFactors.adapter = adapter
    }

    override fun onHeaderClicked() {
        adapter.toggleSection()
    }

    private fun observeLiveData() {
        viewModel.factorsLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        viewModel.progressLiveData.observe(viewLifecycleOwner) {
            binding.buttonCalculate.isEnabled = it
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
