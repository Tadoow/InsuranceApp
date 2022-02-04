package com.example.osagocalculation.presentation.dialogform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.osagocalculation.R
import com.example.osagocalculation.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DialogFormFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetBinding
    private lateinit var dialogTitle: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialogTitle = requireArguments().getString(TITLE_KEY)!!
        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentBottomSheetBinding.bind(view)

        binding.textBottomTitle.text = dialogTitle
    }

//    override fun onStart() {
//        super.onStart()
//        val behavior = (dialog as BottomSheetDialog).behavior
//        behavior.state = BottomSheetBehavior.STATE_COLLAPSED
//        behavior.peekHeight = 200
//    }

    companion object {

        const val TAG = "DialogFormFragment"

        private const val TITLE_KEY = "TITLE_KEY"

        fun newInstance(dialogTitle: String): DialogFormFragment {
            val fragment = DialogFormFragment()
            fragment.arguments = Bundle().apply {
                putString(TITLE_KEY, dialogTitle)
            }
            return fragment
        }
    }

}
