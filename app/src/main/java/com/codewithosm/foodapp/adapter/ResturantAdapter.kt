package com.codewithosm.foodapp.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codewithosm.foodapp.databinding.ItemlayoutresturantbindingBinding
import com.codewithosm.foodapp.mdel.Hours
import com.codewithosm.foodapp.mdel.ResturantResponseItem
import java.text.SimpleDateFormat
import java.util.*


class ResturantAdapter(val action:Action):ListAdapter<ResturantResponseItem,ResturantAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view= ItemlayoutresturantbindingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val res=getItem(position)

        holder.binding.tvResturantname.text="Name:"+res.name
        holder.binding.tvResturantaddress.text="Address:"+res.address
        holder.binding.tvResturantahours.text=gettodatytoHours(res.hours)
        Glide.with(holder.binding.thumbimage)
            .load(res.image)
            .into(holder.binding.thumbimage)

        holder.itemView.setOnClickListener {
            action.onItemClick(res)
        }

    }


    class ViewHolder(itemBinding: ItemlayoutresturantbindingBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        internal val binding: ItemlayoutresturantbindingBinding = itemBinding
    }


    private class DiffCallback : DiffUtil.ItemCallback<ResturantResponseItem>() {
        override fun areItemsTheSame(oldItem: ResturantResponseItem, newItem: ResturantResponseItem): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: ResturantResponseItem, newItem: ResturantResponseItem): Boolean {
            return true
        }
    }

// get day from calendar
private fun gettodatytoHours(hours: Hours):String{
    val calender=Calendar.getInstance()
       val date=calender.time
       val day=SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.time)
  return when(day){
       "Sunday"->hours.Sunday
       "Monday"->hours.Monday
       "Saturday"->hours.Saturday
       "Wednesday"->hours.Wednesday
       "Friday"->hours.Friday
       "Thursday"->hours.Thursday
       "Tuesday"->hours.Tuesday
        else->hours.Sunday
    }


}

    interface Action{
        fun onItemClick(responseItem: ResturantResponseItem)
    }

}