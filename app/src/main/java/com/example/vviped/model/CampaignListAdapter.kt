package com.example.vviped.model

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vviped.R
import com.example.vviped.UploadSellingActivity

class CampaignListAdapter(
    private var context: Context,
    private var campaignLists: List<CampaignItem>,
    private var isFragment: Boolean = false
) : RecyclerView.Adapter<CampaignListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val organizationnamepost = itemView.findViewById<TextView>(R.id.organizationname_post)
        val organizationprofpict_post = itemView.findViewById<ImageView>(R.id.organization_profpict_post)
        val imagecampaign = itemView.findViewById<ImageView>(R.id.imagecampaign_layout)
        val campaignname = itemView.findViewById<TextView>(R.id.campaign_name)
        val campaigndesc = itemView.findViewById<TextView>(R.id.campaign_deskripsi)
        val campaignlocation = itemView.findViewById<TextView>(R.id.lokasi_campaign)
        val donatebyselling = itemView.findViewById<Button>(R.id.donatewithselling_btn)

        fun bindView(campaignItem: CampaignItem) {
            organizationnamepost.text = campaignItem.organization_name
            organizationprofpict_post.setImageResource(campaignItem.organization_profpict)
            imagecampaign.setImageResource(campaignItem.image_campaign)
            campaignname.text = campaignItem.campaign_name
            campaigndesc.text = campaignItem.campaign_desc
            campaignlocation.text = campaignItem.campaign_location

            donatebyselling.setOnClickListener() {
                val context = donatebyselling.context
                val intent = Intent(context, UploadSellingActivity::class.java)
                context.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CampaignListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.campaign_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return campaignLists.size
    }

    override fun onBindViewHolder(viewHolder: CampaignListAdapter.ViewHolder, position: Int) {
        viewHolder.bindView(campaignLists[position])
    }
}