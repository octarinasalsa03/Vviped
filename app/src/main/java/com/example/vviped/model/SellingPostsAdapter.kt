package com.example.vviped.model

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.vviped.MainChat
import com.example.vviped.R
import com.example.vviped.UploadSellingActivity
import com.example.vviped.ui.CampaignListFragment
import com.example.vviped.ui.HomeFragment
import com.example.vviped.ui.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.sellingposts_layout.*

class SellingPostsAdapter(
    private var context: Context,
    private var sellingPosts: List<SellingPostItem>,
    private var isFragment: Boolean = false
) : RecyclerView.Adapter<SellingPostsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val usernamepost = itemView.findViewById<TextView>(R.id.username_post)
        val profpictpost = itemView.findViewById<ImageView>(R.id.user_profpict_post)
        val imagepost = itemView.findViewById<ImageView>(R.id.imagepost_layout)
        val productname = itemView.findViewById<TextView>(R.id.product_name)
        val productprice = itemView.findViewById<TextView>(R.id.harga_produk)
        val productdesc = itemView.findViewById<TextView>(R.id.produk_deskripsi)
        val sellerlocation = itemView.findViewById<TextView>(R.id.lokasi_penjual)
        val buyButton = itemView.findViewById<Button>(R.id.btn_buy)


        fun bindView(sellingPost: SellingPostItem) {
            usernamepost.text = sellingPost.usernamepost
            profpictpost.setImageResource(sellingPost.user_profpict)
            imagepost.setImageResource(sellingPost.image_post)
            productname.text = sellingPost.product_name
            productprice.text = sellingPost.product_price
            productdesc.text = sellingPost.product_description
            sellerlocation.text = sellingPost.seller_location

            buyButton.setOnClickListener() {
                val context = buyButton.context
                val intent = Intent(context, MainChat::class.java)
                context.startActivity(intent)
            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellingPostsAdapter.ViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.sellingposts_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: SellingPostsAdapter.ViewHolder, position: Int) {
        viewHolder.bindView(sellingPosts[position])

    }

    override fun getItemCount(): Int {
        return sellingPosts.size
    }





}
