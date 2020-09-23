package com.example.vviped.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vviped.R

class GridPhotosAdapter : RecyclerView.Adapter<GridPhotosAdapter.ViewHolder>() {

    private val my_images = intArrayOf(R.drawable.profilpic, R.drawable.profilpic, R.drawable.profilpic)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var myimages: ImageView

        init {
            myimages = itemView.findViewById(R.id.myimage)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): GridPhotosAdapter.ViewHolder {

        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.profilphotosgrid_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.myimages.setImageResource(my_images[i])

    }

    override fun getItemCount(): Int {
        return my_images.size
    }
}