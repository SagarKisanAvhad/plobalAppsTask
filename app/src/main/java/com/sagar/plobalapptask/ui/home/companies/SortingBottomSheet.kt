package com.sagar.plobalapptask.ui.home.companies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sagar.plobalapptask.R
import com.sagar.plobalapptask.data.db.entities.Company
import com.sagar.plobalapptask.databinding.DialogSortingBottomsheetBinding
import com.sagar.plobalapptask.util.invisible
import com.sagar.plobalapptask.util.visible


class SortingBottomSheet : BottomSheetDialogFragment() {
    lateinit var binding: DialogSortingBottomsheetBinding
    private var company: Company? = null
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SortClickListener)
            listener = context
    }

    private fun setupView() {
        binding.ivSale.setOnClickListener { handleClick(1) }
        binding.tvSale.setOnClickListener { handleClick(1) }

        binding.ivCard.setOnClickListener { handleClick(2) }
        binding.tvCard.setOnClickListener { handleClick(2) }

        binding.ivDownload.setOnClickListener { handleClick(3) }
        binding.tvDownload.setOnClickListener { handleClick(3) }

        binding.ivSession.setOnClickListener { handleClick(4) }
        binding.tvSession.setOnClickListener { handleClick(4) }
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



