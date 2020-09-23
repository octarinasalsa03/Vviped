package com.example.vviped.model
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.vviped.R
import com.example.vviped.ui.CommentsActivity
import com.google.android.material.internal.ContextUtils.getActivity
import kotlin.coroutines.coroutineContext

class PostAdapter : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private val usernamepost = arrayOf("akunbaru", "akunfake", "akunsaya")
    private val user_profpict = intArrayOf(R.drawable.profilpic, R.drawable.profilpic, R.drawable.profilpic)
    private val image_post = intArrayOf(R.drawable.profilpic, R.drawable.profilpic, R.drawable.profilpic)
    private val likes_post = arrayOf("10 Likes", "20 Likes", "50 Likes")
    private val comments_post = arrayOf("200 Comments", "15 Comments", "20 Comments")
    private val publisher_post = arrayOf("akunbaru", "akunfake", "akunsaya")
    private val caption_post = arrayOf("akun baru nih", "gapunya foto bagus guys", "halo semua!!!")

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var usernamepost: TextView
        var profpictpost: ImageView
        var imagepost: ImageView
        var likepost: TextView
        var commentpost: TextView
        var publisherpost: TextView
        var captionpost: TextView

        init {
            usernamepost = itemView.findViewById(R.id.username_post)
            profpictpost = itemView.findViewById(R.id.user_profpict_post)
            imagepost = itemView.findViewById(R.id.imagepost_layout)
            likepost = itemView.findViewById(R.id.likes_post)
            commentpost = itemView.findViewById(R.id.comments_post)
            publisherpost = itemView.findViewById(R.id.publisher_post)
            captionpost = itemView.findViewById(R.id.caption_post)

            commentpost.setOnClickListener{
                val context = commentpost.context
                val intent = Intent(context, CommentsActivity::class.java)
                context.startActivity(intent)
            }

        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): PostAdapter.ViewHolder {

        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.posts_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.usernamepost.text = usernamepost[i]
        viewHolder.profpictpost.setImageResource(user_profpict[i])
        viewHolder.imagepost.setImageResource(image_post[i])
        viewHolder.likepost.text = likes_post[i]
        viewHolder.commentpost.text = comments_post[i]
        viewHolder.publisherpost.text = publisher_post[i]
        viewHolder.captionpost.text = caption_post[i]

    }

    override fun getItemCount(): Int {
        return usernamepost.size
    }



}