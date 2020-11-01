package com.example.vviped.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vviped.Landing
import com.example.vviped.R
import com.example.vviped.login
import com.example.vviped.model.SellingPostItem
import com.example.vviped.model.SellingPostsAdapter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_profile.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var recyclerView: RecyclerView? = null
    private var sellingPostAdapter: SellingPostsAdapter? = null
    private lateinit var auth: FirebaseAuth

    val sellingPosts = arrayListOf<SellingPostItem>(
        SellingPostItem(
            "inisaya",
            R.drawable.profilpic,
            R.drawable.profilpic,
            "Buku Materi Matematika SMA",
            "Rp 70000",
            "Kondisi buku masih bagus.",
            "Bekasi, Jawa Barat"
        ),
        SellingPostItem(
            "inisaya",
            R.drawable.profilpic,
            R.drawable.profilpic,
            "Baju Atasan Lengan Panjang",
            "Rp 45000",
            "Kondisi masih sekitar 95%",
            "Bekasi, Jakarta Timur"
        ),
        SellingPostItem(
            "inisaya",
            R.drawable.profilpic,
            R.drawable.profilpic,
            "Komik One Piece",
            "Rp 20000",
            "Kondisi buku 70%",
            "Bekasi, Jawa Timur"
        ),

        )



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        recyclerView = view.findViewById(R.id.recycleView_sellingPostProfile)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(context)

        sellingPostAdapter = context?.let { SellingPostsAdapter(
            it,
            sellingPosts as ArrayList<SellingPostItem>,
            true
        ) }
        recyclerView?.adapter = sellingPostAdapter


           return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()


        btn_logout.setOnClickListener{
            auth.signOut()
            val intent = Intent(activity, Landing::class.java)
            startActivity(intent)
            Toast.makeText(
                activity,
                "you've been logged out",
                Toast.LENGTH_SHORT
            ).show()
          }


        }
    }
