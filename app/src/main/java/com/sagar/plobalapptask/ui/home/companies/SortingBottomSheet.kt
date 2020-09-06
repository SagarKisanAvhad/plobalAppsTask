package com.sagar.plobalapptask.ui.home.companies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sagar.plobalapptask.R
import com.sagar.plobalapptask.databinding.DialogSortingBottomsheetBinding
import com.sagar.plobalapptask.util.invisible
import com.sagar.plobalapptask.util.visible


class SortingBottomSheet : BottomSheetDialogFragment() {
    lateinit var binding: DialogSortingBottomsheetBinding
    private var flag: Int = 1
    private var listener: SortClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.dialog_sorting_bottomsheet, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    fun setSortClickListener(sortClickListener: SortClickListener, flag: Int) {
        listener = sortClickListener
        this.flag = flag
    }

    private fun setupView() {
        handleClick(flag)
        binding.ivSale.setOnClickListener { callSortClickAndDismiss(1) }
        binding.tvSale.setOnClickListener { callSortClickAndDismiss(1) }

        binding.ivCard.setOnClickListener { callSortClickAndDismiss(2) }
        binding.tvCard.setOnClickListener { callSortClickAndDismiss(2) }

        binding.ivDownload.setOnClickListener { callSortClickAndDismiss(3) }
        binding.tvDownload.setOnClickListener { callSortClickAndDismiss(3) }

        binding.ivSession.setOnClickListener { callSortClickAndDismiss(4) }
        binding.tvSession.setOnClickListener { callSortClickAndDismiss(4) }
    }

    private fun handleClick(flag: Int) {
        resetAll()
        when (flag) {
            1 -> {
                binding.ivSale.visible()
            }
            2 -> {
                binding.ivCard.visible()
            }
            3 -> {
                binding.ivDownload.visible()
            }
            4 -> {
                binding.ivSession.visible()
            }
        }

    }

    private fun callSortClickAndDismiss(flag: Int) {
        listener?.onSortClick(flag)
        dismiss()
    }

    private fun resetAll() {
        binding.ivSale.invisible()
        binding.ivCard.invisible()
        binding.ivDownload.invisible()
        binding.ivSession.invisible()
    }


    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    companion object {
        private var instance: SortingBottomSheet? = null
        fun newInstance(): SortingBottomSheet {
            if (instance != null) {
                return requireNotNull(instance) { "Sorting Bottom sheet instance can't be null" }
            }

            instance = SortingBottomSheet()
            return requireNotNull(instance) { "Sorting Bottom sheet instance can't be null" }
        }
    }

    interface SortClickListener {
        fun onSortClick(flag: Int)
    }

}



