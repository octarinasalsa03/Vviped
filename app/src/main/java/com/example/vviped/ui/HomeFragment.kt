package com.example.vviped.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vviped.Landing
import com.example.vviped.MainChat
import com.example.vviped.R
import com.example.vviped.model.SellingPostItem
import com.example.vviped.model.SellingPostsAdapter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var recyclerView: RecyclerView? = null
    private var sellingPostAdapter: SellingPostsAdapter? = null

    val sellingPosts = arrayListOf<SellingPostItem>(
        SellingPostItem("inisaya", R.drawable.profilpic, R.drawable.profilpic, "Buku Materi Matematika SMA", "Rp 70000", "Kondisi buku masih bagus.", "Bekasi, Jawa Barat"),
        SellingPostItem("akunbaru", R.drawable.profilpic, R.drawable.profilpic, "Buku Novel Andaikan Aku Menjadi", "Rp 30000", "Kondisi buku 80%", "Rawamangun, Jakarta Timur"),
        SellingPostItem("anakbaik", R.drawable.profilpic, R.drawable.profilpic, "Hoodie White", "Rp 80000", "Kondisi hoodie masih sekitar 90%", "Malang, Jawa Timur"),

        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recycleView_home)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(context)

        sellingPostAdapter = context?.let { SellingPostsAdapter(it, sellingPosts as ArrayList<SellingPostItem>, true) }
        recyclerView?.adapter = sellingPostAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btn_chat.setOnClickListener{
            val intent = Intent(activity, MainChat::class.java)
            startActivity(intent)
        }


    }



}