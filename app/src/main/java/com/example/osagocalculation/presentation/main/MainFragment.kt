package com.example.osagocalculation.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.osagocalculation.R
import com.example.osagocalculation.data.RepositoryImpl
import com.example.osagocalculation.databinding.FragmentMainBinding
import com.example.osagocalculation.domain.Repository
import com.example.osagocalculation.presentation.form.FormFragment
import com.example.osagocalculation.presentation.main.adapter.MainAdapter
import com.example.osagocalculation.presentation.main.listener.OnItemClickListener

class MainFragment : Fragment(R.layout.fragment_main), OnItemClickListener {

    private lateinit var binding: FragmentMainBinding
    private val adapter = MainAdapter(this)

    private val repository: Repository = RepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction()
                .add(R.id.fragment_container_inner, FormFragment.newInstance())
                .commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        val actionBar = (activity as MainActivity).supportActionBar
        actionBar?.title = getString(R.string.app_name)

        binding.recyclerCoefficients.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerCoefficients.adapter = adapter
        adapter.setData(repository.getData())
    }

    override fun onHeaderClicked() {
        adapter.toggleSection()
    }

    companion object {

        const val TAG = "MainFragment"

        fun newInstance(): Fragment {
            return MainFragment()
        }
    }

}
