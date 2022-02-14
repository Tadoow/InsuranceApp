package com.example.osagocalculation.presentation.dialogform

import android.content.DialogInterface
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.osagocalculation.R
import com.example.osagocalculation.data.dto.FormData
import com.example.osagocalculation.databinding.FragmentBottomSheetBinding
import com.example.osagocalculation.presentation.dialogform.viewpager.ViewPagerAdapter
import com.example.osagocalculation.presentation.form.listener.OnDismissDialogListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DialogFormFragment : BottomSheetDialogFragment() {

    private val clickedItemPosition by lazy { requireArguments().getInt(POSITION_KEY) }
    private lateinit var formItemsList: List<FormData>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        formItemsList = requireArguments().getParcelableArrayList(ITEMS_KEY)!!
        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentBottomSheetBinding.bind(view)

        val adapter = ViewPagerAdapter(this)
        binding.viewPagerBottomSheet.adapter = adapter
        adapter.setItems(formItemsList)

        binding.viewPagerBottomSheet.currentItem = clickedItemPosition
        if (clickedItemPosition == formItemsList.lastIndex) {
            binding.textDialogNext.text = getString(R.string.confirm)
            binding.imageDialogNext.isVisible = false
        }

        binding.viewPagerBottomSheet.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> binding.buttonDialogBack.isVisible = false
                    formItemsList.lastIndex -> {
                        binding.textDialogNext.text = getString(R.string.confirm)
                        binding.imageDialogNext.isVisible = false
                    }
                    else -> {
                        binding.buttonDialogBack.isVisible = true
                        binding.imageDialogNext.isVisible = true
                        binding.textDialogNext.text = getString(R.string.next)
                    }
                }
            }
        })

        binding.buttonDialogBack.setOnClickListener {
            binding.viewPagerBottomSheet.currentItem--
        }

        binding.buttonDialogNext.setOnClickListener {
            when (binding.viewPagerBottomSheet.currentItem) {
                formItemsList.lastIndex -> {
                    dismiss()
                }
                else -> binding.viewPagerBottomSheet.currentItem++
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        (parentFragment as OnDismissDialogListener).onDialogDismissed()
    }

    override fun onStart() {
        super.onStart()
        val behavior = (dialog as BottomSheetDialog).behavior
        behavior.peekHeight = Resources.getSystem().displayMetrics.heightPixels
    }

    companion object {

        const val TAG = "DialogFormFragment"

        private const val POSITION_KEY = "POSITION_KEY"
        private const val ITEMS_KEY = "ITEMS_KEY"

        fun newInstance(position: Int, formItems: List<FormData>): DialogFormFragment {
            val fragment = DialogFormFragment()
            fragment.arguments = Bundle().apply {
                putInt(POSITION_KEY, position)
                putParcelableArrayList(ITEMS_KEY, formItems as ArrayList)
            }
            return fragment
        }
    }

}
