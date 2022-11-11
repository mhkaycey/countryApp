package com.kelechi.countries.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kelechi.countries.DetailsActivity
import com.kelechi.countries.model.ModelData
import com.kelechi.countries.R
import kotlinx.android.synthetic.main.item_list.view.*

class myAdapter(
    private var modelData: List<ModelData>
    ):RecyclerView.Adapter<myAdapter.DataViewHolder>(){

private lateinit var  mlistener : onItemClickLister
    interface onItemClickLister{
        fun onItemClick(position: Int)
    }

fun setOnitemClickListener(listener: onItemClickLister) {
    mlistener = listener
}

    class DataViewHolder(itemView: View, listener: onItemClickLister): RecyclerView.ViewHolder(itemView) {


        var tvName: TextView = itemView.findViewById(R.id.tvName)
        var tvCapital: TextView = itemView.findViewById(R.id.tvCapital)
        var ivCountry: ImageView = itemView.findViewById(R.id.ivCountry)

        fun bindData(modelData: ModelData){
            tvName.text = modelData.name?.official
            tvCapital.text = modelData.capital.toString()

            Glide.with(itemView).load(modelData.flags?.png).into(ivCountry)
//            itemView.tvName.text = modelData.name?.official.toString()
//            itemView.tvCapital.text = modelData.capital.toString()
//            Glide.with(itemView).load(modelData.flag).into(itemView.ivCountry)

    }

            init {
              itemView.setOnClickListener {
                  listener.onItemClick(adapterPosition)
              }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        return DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false), mlistener)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        //val items  = modelData[position]

      holder.bindData(modelData[position])


    }

    override fun getItemCount(): Int = modelData.size

    fun filterAdapter(filteredNames: ArrayList <ModelData > ) {
        Log.e("list", filteredNames.toString())
        Log.e("list", filteredNames.size.toString())
        // this.dataList.clear()
        this.modelData = filteredNames
        notifyDataSetChanged()
    }
}