package com.stdev.gads2020

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class SkillLeadersAdapter(val context: Context): RecyclerView.Adapter<SkillLeadersAdapter.ViewHolder>() {

    var skillLeadersList : List<SkillIQ> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hours_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return skillLeadersList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name?.text = skillLeadersList[position].name
        holder.hours?.text = skillLeadersList[position].score.toString() +" , " + skillLeadersList[position].country
        Glide.with(context).load(skillLeadersList.get(position).badgeUrl)
            .apply(RequestOptions().fitCenter()
                .error(R.drawable.ic_broken_image))
            .into(holder.image!!)
    }

    fun setSkillsList(skillsList: List<SkillIQ>){
        this.skillLeadersList = skillsList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!){
        val name : TextView? = itemView?.findViewById(R.id.text_view_name)
        val hours : TextView? = itemView?.findViewById(R.id.text_view_details)
        val image : ImageView? = itemView?.findViewById(R.id.image_badge)
    }
}
