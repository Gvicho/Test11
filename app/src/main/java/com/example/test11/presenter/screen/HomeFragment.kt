package com.example.test11.presenter.screen

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.test11.R
import com.example.test11.databinding.FragmentHomeBinding
import com.example.test11.presenter.base_fragment.BaseFragment
import com.example.test11.presenter.event.HomePageEvents
import com.example.test11.presenter.extensions.showSnackBar
import com.example.test11.presenter.model.AccountUi
import com.example.test11.presenter.screen.bottom_sheet_choosers.from.FromBottomSheet
import com.example.test11.presenter.screen.bottom_sheet_choosers.to.ToBottomSheet
import com.example.test11.presenter.state.FromState
import com.example.test11.presenter.state.HomePageState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate), FromBottomSheet.BottomSheetListener {

    private val viewModel : HomeFragmentViewModel by viewModels()

    override fun bind() {
        binding.fromFrame.setOnClickListener {
            val fromBottomSheet = FromBottomSheet()
            fromBottomSheet.setListener(this)
            fromBottomSheet.show(parentFragmentManager, fromBottomSheet.tag)

        }

        // Set a click listener on toFrame to show the ToBottomSheet
        binding.toFrame.setOnClickListener {
            val toBottomSheet = ToBottomSheet()
            // You can also set a listener if needed
            // toBottomSheet.setListener(this)
            toBottomSheet.show(parentFragmentManager, toBottomSheet.tag)
        }
    }

    override fun bindObservers() {
        bindStateObservers()
    }

    private fun bindStateObservers(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiStateFlow.collect(){
                    handleResult(it)
                }
            }
        }
    }

    private fun handleResult(state: HomePageState){

        showLoader(state.isLoading)

        state.fromAccountIsSuccess?.let {
            bindFromAccountsStateInfo(it)
        }
    }

    private fun bindFromAccountsStateInfo(account: AccountUi){
        binding.apply {
            if(account.cardType == "VISA"){
                imageLogoFrom.setImageResource(R.drawable.ic_visa_32)
            }else{
                imageLogoFrom.setImageResource(R.drawable.ic_mastercard_32)
            }

            textAccountName.text = account.accountName
            textAccountBalance.text = account.balance.toString()
            textAccountNumber.text = "****" + account.accountNumber.drop(19)
        }
    }

    private fun showErrorMessage(errorMessage:String){
        binding.root.showSnackBar(errorMessage)
    }

    private fun showLoader(loading:Boolean){
        binding.progressBar.visibility = if(loading) View.VISIBLE else View.GONE
    }

    override fun onAccountSelected(account: AccountUi) {
        viewModel.onEvent(HomePageEvents.SetFromAccountFieldState(account))
    }

}