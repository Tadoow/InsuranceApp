package com.example.osagocalculation.presentation.dialogform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.osagocalculation.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DialogFormFragment : BottomSheetDialogFragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
    }

    companion object {

        const val TAG = "DialogFormFragment"

        fun newInstance(): DialogFormFragment {
            return DialogFormFragment()
        }
    }

}
