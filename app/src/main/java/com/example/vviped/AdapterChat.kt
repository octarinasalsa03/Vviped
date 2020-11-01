package com.example.vviped

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vviped.model.Chat
import com.example.vviped.R


class AdapterChat constructor(private val listViewType: List<Int>,
                              private val listChat: List<Chat>) : RecyclerView.Adapter<AdapterChat.ViewHolder>() {

    companion object {
        val VIEW_TYPE_MY_SELF = 1
        val VIEW_TYPE_USER = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_MY_SELF -> {
                val view = layoutInflater.inflate(R.layout.activity_chat_myself, null)
                ViewHolderChatItemMySelf(view)
            }
            else -> {
                val view = layoutInflater.inflate(R.layout.activity_chat_user, null)
                ViewHolderChatItemUser(view)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chat = listChat[position]
        listViewType[position].let {
            when (it) {
                VIEW_TYPE_MY_SELF -> {
                    val viewHolderChatItemMySelf = holder as ViewHolderChatItemMySelf
                    chat.apply {
                        viewHolderChatItemMySelf.textViewDateTime.text = this.dateTime
                        viewHolderChatItemMySelf.textViewMessage.text = this.message
                        val bitmap = BitmapFactory.decodeFile(this.image)
                        viewHolderChatItemMySelf.imageViewMessage.setImageBitmap(bitmap)

                        if (this.message.isEmpty()) {
                            viewHolderChatItemMySelf.textViewMessage.visibility = View.GONE
                            viewHolderChatItemMySelf.imageViewMessage.visibility = View.VISIBLE
                        } else {
                            viewHolderChatItemMySelf.textViewMessage.visibility = View.VISIBLE
                            viewHolderChatItemMySelf.imageViewMessage.visibility = View.GONE

                        }
                    }
                }
                else -> {
                    val viewHolderChatUser = holder as ViewHolderChatItemUser
                    chat.apply {
                        viewHolderChatUser.textViewDateTime.text = chat.dateTime
                        viewHolderChatUser.textViewMessage.text = chat.message
                        val bitmap = BitmapFactory.decodeFile(this.image)
                        viewHolderChatUser.imageViewMessage.setImageBitmap(bitmap)


                        if (this.message.isEmpty()) {
                            viewHolderChatUser.textViewMessage.visibility = View.GONE
                            viewHolderChatUser.imageViewMessage.visibility = View.VISIBLE

                        } else {
                            viewHolderChatUser.textViewMessage.visibility = View.VISIBLE
                            viewHolderChatUser.imageViewMessage.visibility = View.GONE

                        }
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = listChat.size

    override fun getItemViewType(position: Int): Int = listViewType[position]

    open inner class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class ViewHolderChatItemMySelf constructor(itemView: View) : ViewHolder(itemView) {

        val textViewDateTime: TextView = itemView.findViewById(R.id.date_chat_my_self)
        val textViewMessage: TextView = itemView.findViewById(R.id.text_chat_my_self)
        val imageViewMessage: ImageView = itemView.findViewById(R.id.image_chat_myself)

    }

    inner class ViewHolderChatItemUser constructor(itemView: View) : ViewHolder(itemView) {

        val textViewDateTime: TextView = itemView.findViewById(R.id.date_chat_user)
        val textViewMessage: TextView = itemView.findViewById(R.id.text_chat_user)
        val imageViewMessage: ImageView = itemView.findViewById(R.id.image_chat_user)


    }

}