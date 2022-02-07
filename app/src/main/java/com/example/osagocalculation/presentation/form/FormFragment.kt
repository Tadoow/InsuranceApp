package com.example.osagocalculation.presentation.form

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.osagocalculation.R
import com.example.osagocalculation.data.RepositoryImpl
import com.example.osagocalculation.databinding.FragmentApplicationFormBinding
import com.example.osagocalculation.presentation.dialogform.DialogFormFragment
import com.example.osagocalculation.presentation.form.adapter.FormAdapter
import com.example.osagocalculation.presentation.form.listener.OnItemClickListener

class FormFragment : Fragment(R.layout.fragment_application_form), OnItemClickListener {

    private val adapter = FormAdapter(this)
    private val repository = RepositoryImpl()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentApplicationFormBinding.bind(view)

        binding.recyclerApplicationForm.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerApplicationForm.adapter = adapter
        adapter.setData(repository.getFormData())
    }

    override fun onFormClicked(formItem: String) {
        val dialogFormFragment = DialogFormFragment.newInstance(formItem)
        dialogFormFragment.show(parentFragmentManager, DialogFormFragment.TAG)
    }

    companion object {

        const val TAG = "FormFragment"

        fun newInstance(): Fragment {
            return FormFragment()
        }
    }

}
