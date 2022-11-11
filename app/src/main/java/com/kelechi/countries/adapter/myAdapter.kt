package com.kelechi.countries.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kelechi.countries.DetailsActivity
import com.kelechi.countries.model.ModelData
import com.kelechi.countries.R
import kotlinx.android.synthetic.main.item_list.view.*
import java.util.*
import java.util.Collections.addAll
import kotlin.collections.ArrayList

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
    fun getFilter(): Filter {
        return countryFilter
    }

    private val countryFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: List<ModelData> = ArrayList()
            if ((constraint == null) || constraint.isEmpty()) {
                initialDataList.let { filteredList.addAll(it) }
            } else {
                val query = constraint.toString().trim().lowercase(Locale.ROOT)
                initialDataList.forEach {
                    if (it.name!!.common!!.lowercase(Locale.ROOT).contains(query)) {
                        filteredList.add(it)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results?.values is List<*>) {
                modelData.clear()
                modelData.addAll(results.values as List<*>)
                notifyDataSetChanged()
            }
        }
    }
    val initialDataList = List<ModelData>().apply {
        modelData.let { addAll(it) }
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


      holder.bindData(modelData[position])


    }

    override fun getItemCount(): Int = modelData.size




}