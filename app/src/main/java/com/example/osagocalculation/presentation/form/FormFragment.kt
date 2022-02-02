package com.example.osagocalculation.presentation.form

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.osagocalculation.R
import com.example.osagocalculation.data.RepositoryImpl
import com.example.osagocalculation.databinding.FragmentApplicationFormBinding
import com.example.osagocalculation.presentation.form.adapter.FormAdapter

class FormFragment : Fragment(R.layout.fragment_application_form) {

    private lateinit var binding: FragmentApplicationFormBinding
    private val adapter = FormAdapter()
    private val repository = RepositoryImpl()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentApplicationFormBinding.bind(view)

        binding.recyclerApplicationForm.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerApplicationForm.adapter = adapter
        adapter.setData(repository.getFormData())
    }

    companion object {

        const val TAG = "FormFragment"

        fun newInstance(): Fragment {
            return FormFragment()
        }
    }

}
