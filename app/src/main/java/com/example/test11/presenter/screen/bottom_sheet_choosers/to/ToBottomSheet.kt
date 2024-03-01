package com.example.test11.presenter.screen.bottom_sheet_choosers.to

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test11.databinding.BottomSheetToBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ToBottomSheet : BottomSheetDialogFragment() {
    private var _binding: BottomSheetToBinding? = null
    private val binding get() = _binding!!
    interface BottomSheetListener {
        fun onOptionSelected(option: String)
    }
    private var listener: BottomSheetListener? = null
    fun setListener(listener: BottomSheetListener) {
        this.listener = listener
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = BottomSheetToBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.validateBtn.setOnClickListener {
            dismiss()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}