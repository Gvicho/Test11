package com.example.test11.presenter.recycler_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test11.databinding.ItemAccountBinding
import com.example.test11.presenter.model.AccountUi


class AccountsRecyclerAdapter(
    private val listener: AccountClickListener
) : ListAdapter<AccountUi, RecyclerView.ViewHolder>(
DIFF_CALLBACK
) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AccountUi>() {
            override fun areItemsTheSame(oldItem: AccountUi, newItem: AccountUi): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: AccountUi, newItem: AccountUi): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class BluetoothDevicesViewHolder(private val binding: ItemAccountBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(postion:Int) {
            val account = currentList[postion]
            binding.apply {
                tvName.text = account.accountName
                tvNumber.text = account.accountNumber
                tvMoney.text = account.balance.toString()
            }
            bindClickListener(account)
        }

        private fun bindClickListener(account: AccountUi){
            binding.root.setOnClickListener{
                listener.onClick(account)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BluetoothDevicesViewHolder {
        return BluetoothDevicesViewHolder(ItemAccountBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is BluetoothDevicesViewHolder)holder.bind(position)
    }


}

interface AccountClickListener{
    fun onClick(account: AccountUi)
}