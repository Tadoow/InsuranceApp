package com.example.osagocalculation.presentation.form

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.osagocalculation.R
import com.example.osagocalculation.data.dto.FormData
import com.example.osagocalculation.databinding.FragmentApplicationFormBinding
import com.example.osagocalculation.presentation.dialogform.DialogFormFragment
import com.example.osagocalculation.presentation.form.adapter.FormAdapter
import com.example.osagocalculation.presentation.form.listener.OnClickItemListener
import com.example.osagocalculation.presentation.form.listener.OnDismissDialogListener
import com.example.osagocalculation.presentation.main.SharedViewModel

class FormFragment : Fragment(R.layout.fragment_application_form), OnClickItemListener,
    OnDismissDialogListener {

    private val viewModel: SharedViewModel by viewModels(ownerProducer = { requireParentFragment() })

    private lateinit var formItems: List<FormData>
    private val adapter = FormAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().supportFragmentManager.setFragmentResultListener(
            REQUEST_KEY,
            this
        ) { _, bundle ->
            val itemToUpdate = bundle.getParcelable<FormData>(ITEM_KEY)!!
            adapter.updateItem(itemToUpdate)
        }

        if (savedInstanceState == null) {
            viewModel.getFormItems()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentApplicationFormBinding.bind(view)

        observeLiveData()

        binding.recyclerApplicationForm.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerApplicationForm.adapter = adapter
    }

    override fun onFormItemClicked(item: FormData) {
        val dialogFormFragment = DialogFormFragment.newInstance(formItems.indexOf(item), formItems)
        dialogFormFragment.show(childFragmentManager, DialogFormFragment.TAG)
    }

    override fun onDialogDismissed() {
        viewModel.getCalculatedFactors(adapter.items)
    }

    private fun observeLiveData() {
        viewModel.formItemsLivaData.observe(this) {
            adapter.setData(it)
            formItems = it
        }
        viewModel.errorLiveData.observe(this) {
            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
        }
    }


    companion object {

        const val TAG = "FormFragment"
        private const val REQUEST_KEY = "REQUEST_KEY"
        private const val ITEM_KEY = "ITEM_KEY"

        fun newInstance(): Fragment {
            return FormFragment()
        }
    }

}
