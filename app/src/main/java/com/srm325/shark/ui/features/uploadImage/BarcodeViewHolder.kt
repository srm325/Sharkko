package com.srm325.shark.ui.features.uploadImage

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.srm325.shark.R

class BarcodeViewHolder(val item : View) : RecyclerView.ViewHolder(item) {

    val name :TextView  = item.findViewById(R.id.entry_name)
    val element :TextView  = item.findViewById(R.id.type)
    val isRecyclable :TextView  = item.findViewById(R.id.isRecyclable)

}