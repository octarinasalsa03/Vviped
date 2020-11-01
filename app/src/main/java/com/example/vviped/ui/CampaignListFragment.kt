package com.example.vviped.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vviped.R
import com.example.vviped.model.CampaignItem
import com.example.vviped.model.CampaignListAdapter
import com.example.vviped.model.SellingPostItem
import com.example.vviped.model.SellingPostsAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CampaignListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CampaignListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var recyclerView: RecyclerView? = null
    private var campaignListAdapter: CampaignListAdapter? = null

    val campaignLists = arrayListOf<CampaignItem>(
        CampaignItem("organisasi1", R.drawable.profilpic, R.drawable.profilpic,"Bantu Pak Somat Mengobati Kanker", "desc here", "Bekasi, Jawa Barat"),
        CampaignItem("organisasi2", R.drawable.profilpic, R.drawable.profilpic,"Bantu Soni untuk Sekolah", "desc here", "Rawamangun, Jakarta Timur"),
        CampaignItem("organisasi1", R.drawable.profilpic, R.drawable.profilpic,"Bantu Ibu Jumilah Mengobati Kakinya", "desc here", "Malang, Jawa Timur"),
        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_campaign_list, container, false)

        recyclerView = view.findViewById(R.id.recycleView_campaign)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(context)

        campaignListAdapter = context?.let { CampaignListAdapter(it, campaignLists as ArrayList<CampaignItem>, true) }
        recyclerView?.adapter = campaignListAdapter

        return view
    }

}