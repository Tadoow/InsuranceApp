package com.example.osagocalculation.presentation.dialogform.viewpager

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.osagocalculation.R
import com.example.osagocalculation.data.dto.FormData
import com.example.osagocalculation.databinding.FragmentBottomSheetContentBinding

class ViewPagerFragment : Fragment() {

    private lateinit var item: FormData
    private lateinit var binding: FragmentBottomSheetContentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        item = requireArguments().getParcelable(ITEM_KEY)!!
        return inflater.inflate(R.layout.fragment_bottom_sheet_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBottomSheetContentBinding.bind(view)

        binding.textDialogTitle.text = item.name
        binding.editTextDialogForm.hint = item.hint
        binding.editTextDialogForm.inputType = item.inputType
        binding.editTextDialogForm.setText(item.value)
    }

    override fun onResume() {
        super.onResume()
        binding.editTextDialogForm.requestFocus()
        showKeyboard()
    }

    override fun onPause() {
        super.onPause()
        item.value = binding.editTextDialogForm.text.toString()
        requireActivity().supportFragmentManager.setFragmentResult(
            REQUEST_KEY,
            bundleOf(ITEM_KEY to item)
        )
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

}
