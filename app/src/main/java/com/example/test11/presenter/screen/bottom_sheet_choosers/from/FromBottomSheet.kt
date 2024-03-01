package com.example.test11.presenter.screen.bottom_sheet_choosers.from

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.test11.databinding.BottomSheetFromBinding
import com.example.test11.presenter.extensions.showSnackBar
import com.example.test11.presenter.model.AccountUi
import com.example.test11.presenter.recycler_adapters.AccountClickListener
import com.example.test11.presenter.recycler_adapters.AccountsRecyclerAdapter
import com.example.test11.presenter.state.FromState
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FromBottomSheet : BottomSheetDialogFragment(), AccountClickListener {



    private var _binding: BottomSheetFromBinding? = null
    private val viewModel: FromBottomSheetViewModel by viewModels()
    private lateinit var accountsRecyclerAdapter: AccountsRecyclerAdapter

    private val binding get() = _binding!!

    interface BottomSheetListener {
        fun onAccountSelected(account: AccountUi)
    }

    private var listener: BottomSheetListener? = null
    fun setListener(listener: BottomSheetListener) {
        this.listener = listener
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = BottomSheetFromBinding.inflate(inflater, container, false)
        accountsRecyclerAdapter = AccountsRecyclerAdapter(this)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = accountsRecyclerAdapter
        bindObservers()
    }

    private fun bindObservers(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiStateFlow.collect(){
                    handleResult(it)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.oneTimeEventFlow.collect(){
                    showErrorMessage(it)
                }
            }
        }
    }

    private fun handleResult(state: FromState){

        showLoader(state.isLoading)

        state.accountsListIsSuccess?.let {
            accountsRecyclerAdapter.submitList(it)
        }
    }

    private fun showErrorMessage(errorMessage:String){
        binding.root.showSnackBar(errorMessage)
    }

    private fun showLoader(loading:Boolean){
        binding.progressBar.visibility = if(loading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(account: AccountUi) {
        listener?.onAccountSelected(account)
        dismiss()
    }
}