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

/*
class LearningLeadersAdapter(val context:Context) : ListAdapter<LearningLeaders,LearningLeadersAdapter.LearningHoursViewHolder>(DiffCallback) {

    var learningLeadersList : List<LearningLeaders> = listOf()

    companion object DiffCallback : DiffUtil.ItemCallback<LearningLeaders>(){

        override fun areItemsTheSame(oldItem: LearningLeaders, newItem: LearningLeaders): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: LearningLeaders, newItem: LearningLeaders): Boolean {
            return oldItem.name == newItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearningHoursViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.hours_list_item,parent,false)
//        return LearningHoursViewHolder(view)
        return LearningHoursViewHolder(HoursListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: LearningLeadersAdapter.LearningHoursViewHolder, position: Int) {
        holder.bind(
            name = learningLeadersList[position].name,
            hoursAndCounty = learningLeadersList[position].hours.toString() + " , "+ learningLeadersList[position].country,
            image = learningLeadersList[position].badgeUrl
        )

    }

    fun setLeadersList(leadersList : List<LearningLeaders>){
        this.learningLeadersList = leadersList
        notifyDataSetChanged()
    }

    inner class LearningHoursViewHolder(private var binding : HoursListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(name: String,hoursAndCounty : String, image : String){

            binding.textViewName.text = name
            binding.textViewDetails.text = hoursAndCounty
            Glide.with(context).load(image).into(binding.imageBadge)

            binding.executePendingBindings()
        }
    }
}
*/
class LearningLeadersAdapter(val context: Context): RecyclerView.Adapter<LearningLeadersAdapter.ViewHolder>() {

    var learningLeadersList : List<LearningLeaders> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hours_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return learningLeadersList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name?.text = learningLeadersList[position].name
        holder.hours?.text = learningLeadersList[position].hours.toString() +" , " + learningLeadersList[position].country
        Glide.with(context).load(learningLeadersList[position].badgeUrl)
            .apply(RequestOptions().fitCenter()
                .error(R.drawable.ic_broken_image))
            .into(holder.image!!)
    }

    fun setLeadersList(leadersList: List<LearningLeaders>){
        this.learningLeadersList = leadersList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!){
        val name : TextView? = itemView?.findViewById(R.id.text_view_name)
        val hours : TextView? = itemView?.findViewById(R.id.text_view_details)
        val image : ImageView? = itemView?.findViewById(R.id.image_badge)
    }
}

