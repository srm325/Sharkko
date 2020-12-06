package com.srm325.shark.ui.features.uploadImage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.srm325.shark.R
import com.srm325.shark.data.model.BarcodeResult


class BarcodeResultAdapter(var postList: List<BarcodeResult>) : RecyclerView.Adapter<BarcodeViewHolder>() {
    var currentAdminArea : String = "Los Angeles County"
    private lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarcodeViewHolder {

        context = parent.context
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.single_line, parent, false)
        return BarcodeViewHolder(view)


    }


    override fun onBindViewHolder(holder: BarcodeViewHolder, position: Int) {

        val item = postList[position]
        holder.name.text = item.name
        holder.element.text = item.element
        holder.isRecyclable.text = item.isRecyclable
        if(item.isRecyclable?.toLowerCase()=="no")
            holder.isRecyclable.setTextColor(ContextCompat.getColor(context, R.color.red))
    }

    override fun getItemCount() = postList.size



}