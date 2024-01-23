package com.nisanth.cinergy.di.utility

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nisanth.cinergy.BR

/**
 * Single recycler view through-out the project, no extend needed
 * @param T -> DataType
 * Auto type cast happens at BaseViewHolder with BR
 * @param myModel -> Must use same name for all the layout items for auto typecast to happen
 */

/**
 * Sample usage
 * */
//  CommonAdapter(dataModelList, R.layout.item_row1)  for creation
//  (myAdapter as BaseAdapter<NewClass>).addList(dataModelList1)  for adding list after creation

class CommonAdapter<T, A>(
    private val baseList: MutableList<T>,
    @LayoutRes val layoutID: Int,
    private val callBack: A? = null
) : RecyclerView.Adapter<CommonAdapter<T, A>.BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutID, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bindUI(baseList[position])
    }

    override fun getItemCount() = baseList.size

    fun addList(_baseList: List<T>) {
        this.baseList.clear()
        this.baseList.addAll(_baseList)
        notifyDataSetChanged()
    }

    fun addPagingList(_baseList: List<T>) {
        this.baseList.addAll(_baseList)
        notifyDataSetChanged()
    }

    inner class BaseViewHolder(private val binding: androidx.databinding.ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindUI(data: Any?) {
            Log.d("BaseViewAdapter","$data")
            binding.setVariable(BR.myModel, data)
            binding.setVariable(BR.callBack, callBack)
            binding.executePendingBindings()
        }
    }

}