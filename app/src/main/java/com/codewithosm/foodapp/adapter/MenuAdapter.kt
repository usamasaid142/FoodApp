package com.codewithosm.foodapp.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codewithosm.foodapp.databinding.ItemlayoutmenuBinding
import com.codewithosm.foodapp.mdel.Menu
import com.codewithosm.foodapp.mdel.ResturantResponseItem
import java.util.*


class MenuAdapter(val action:Action):ListAdapter<Menu,MenuAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view= ItemlayoutmenuBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val menu=getItem(position)

        holder.binding.tvResturantname.text=menu.name
        holder.binding.tvResturantaddress.text="${menu.price} $"

        Glide.with(holder.binding.thumbimage)
            .load(menu.url)
            .into(holder.binding.thumbimage)

        holder.binding.btnAddtoCart.setOnClickListener {
            menu.totalCount=1
            holder.binding.addMoreLayout.visibility=View.VISIBLE
            it.visibility=View.GONE
            action.onItemClickADD(menu)
            holder.binding.tvCount.text=menu.totalCount.toString()
        }

        holder.binding.imageAddOne.setOnClickListener {
            var count=menu.totalCount
            count++
            if (count<=10){
                menu.totalCount=count
              action.onItemClickUpdate(menu)
             holder.binding.tvCount.text=count.toString()

            }

        }

        holder.binding.imageMinus.setOnClickListener {
            var count=menu.totalCount
            count--
            holder.binding.tvCount.text=count.toString()
            menu.totalCount=count
            if (count>0){
                action.onItemClickUpdate(menu)
                holder.binding.tvCount.text=count.toString()
                menu.totalCount=count
            }else{
                menu.totalCount=count
                action.onItemClickRemove(menu)
                holder.binding.addMoreLayout.visibility=View.GONE
                holder.binding.btnAddtoCart.visibility=View.VISIBLE
            }
        }

    }


    class ViewHolder(itemBinding: ItemlayoutmenuBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        internal val binding: ItemlayoutmenuBinding = itemBinding
    }


    private class DiffCallback : DiffUtil.ItemCallback<Menu>() {
        override fun areItemsTheSame(oldItem: Menu, newItem: Menu): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Menu, newItem: Menu): Boolean {
            return true
        }
    }



    interface Action{
        fun onItemClickADD(menu: Menu)
        fun onItemClickRemove(menu: Menu)
        fun onItemClickUpdate(menu: Menu)
    }

}