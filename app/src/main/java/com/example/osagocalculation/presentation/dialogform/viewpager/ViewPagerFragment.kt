package com.example.osagocalculation.presentation.dialogform.viewpager

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.osagocalculation.R
import com.example.osagocalculation.data.dto.FormData
import com.example.osagocalculation.databinding.FragmentBottomSheetContentBinding
import com.example.osagocalculation.presentation.dialogform.DialogFormFragment
import com.example.osagocalculation.presentation.dialogform.DialogViewModel
import com.example.osagocalculation.presentation.dialogform.listener.OnClickButtonListener

class ViewPagerFragment : Fragment(), OnClickButtonListener {

    private val viewModel: DialogViewModel by viewModels({ requireParentFragment() })
    private lateinit var item: FormData
    private lateinit var binding: FragmentBottomSheetContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        viewModel.setFragmentListener(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        item = requireArguments().getParcelable(ITEM_KEY)!!
        Log.d(TAG, "onCreateView: ${item.name}")
        return inflater.inflate(R.layout.fragment_bottom_sheet_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ${item.name}")
        binding = FragmentBottomSheetContentBinding.bind(view)

        binding.textDialogTitle.text = item.name
        binding.editTextDialogForm.inputType = item.inputType
        binding.editTextDialogForm.hint = item.hint

        viewModel.stateLiveData.observe(this) {
            if (it) {
                item.value = binding.editTextDialogForm.text.toString()
                requireActivity().supportFragmentManager.setFragmentResult(
                    REQUEST_KEY,
                    bundleOf(ITEM_KEY to item)
                )
            }
            Log.d(TAG, "onViewCreated: $it")
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ${item.name}")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ${item.name}")
        binding.editTextDialogForm.requestFocus()
        showKeyboard()
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ${item.name}")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ${item.name}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView: ${item.name}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ${item.name}")
    }

    private fun showKeyboard() {
        val inputMethodManager =
            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(
            binding.editTextDialogForm,
            InputMethodManager.SHOW_IMPLICIT
        )
    }

    companion object {

        const val TAG = "DialogInnerFragment"

        private const val ITEM_KEY = "ITEM_KEY"
        private const val REQUEST_KEY = "REQUEST_KEY"

        fun newInstance(item: FormData): Fragment {
            val fragment = ViewPagerFragment()
            fragment.arguments = Bundle().apply {
                putParcelable(ITEM_KEY, item)
            }
            return fragment
        }
    }

    override fun onNextButtonClicked() {
        item.value = binding.editTextDialogForm.text.toString()
        requireActivity().supportFragmentManager.setFragmentResult(
            REQUEST_KEY,
            bundleOf(ITEM_KEY to item)
        )
    }

}
