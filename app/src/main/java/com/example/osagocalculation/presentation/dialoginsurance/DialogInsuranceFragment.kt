package com.example.osagocalculation.presentation.dialoginsurance

import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import coil.request.ImageRequest
import com.example.osagocalculation.App
import com.example.osagocalculation.R
import com.example.osagocalculation.databinding.FragmentInsuranceBottomSheetBinding
import com.example.osagocalculation.domain.entities.InsuranceDomain
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DialogInsuranceFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_insurance_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentInsuranceBottomSheetBinding.bind(view)

        val insurance = requireArguments().getParcelable<InsuranceDomain>(ITEM_KEY)

        insurance?.let {
            binding.itemInsurance.textInsuranceName.text = it.name
            binding.itemInsurance.textInsurancePrice.text =
                resources.getString(R.string.ruble_sign, it.price)
            binding.itemInsurance.textInsuranceRating.text = it.rating
        }

        if (insurance?.iconUrl.isNullOrEmpty()) {
            insurance?.let {
                binding.itemInsurance.imageInsurance.setImageDrawable(ColorDrawable(it.iconBackground))
                binding.itemInsurance.textInsuranceIconTitle.text = it.iconTitle
                binding.itemInsurance.textInsuranceIconTitle.isVisible = true
                binding.itemInsurance.textInsuranceIconTitle.setTextColor(it.fontColor)
            }
        } else {
            binding.itemInsurance.textInsuranceIconTitle.isVisible = false
            val request = ImageRequest.Builder(view.context.applicationContext)
                .data(insurance?.iconUrl)
                .crossfade(true)
                .target(binding.itemInsurance.imageInsurance)
                .build()
            (view.context.applicationContext as App).imageLoader.enqueue(request)
        }

        binding.buttonReady.setOnClickListener {
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        val behavior = (dialog as BottomSheetDialog).behavior
        behavior.peekHeight = Resources.getSystem().displayMetrics.heightPixels
    }

    companion object {

        const val TAG = "DialogInsuranceFragment"
        private const val ITEM_KEY = "ITEM_KEY"

        fun newInstance(insurance: InsuranceDomain): DialogInsuranceFragment {
            val fragment = DialogInsuranceFragment()
            fragment.arguments = Bundle().apply {
                putParcelable(ITEM_KEY, insurance)
            }
            return fragment
        }
    }

}
