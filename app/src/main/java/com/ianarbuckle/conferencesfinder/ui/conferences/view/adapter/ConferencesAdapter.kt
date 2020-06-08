package com.ianarbuckle.conferencesfinder.ui.conferences.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ianarbuckle.conferencesfinder.R
import com.ianarbuckle.conferencesfinder.utils.provideImage
import conferences.model.Conference

class ConferencesAdapter(private val conferences: List<Conference>) : RecyclerView.Adapter<ConferencesAdapter.ConferencesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConferencesAdapter.ConferencesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.conferences_item_view, parent, false)
        return ConferencesViewHolder(view)
    }

    override fun getItemCount(): Int = conferences.size

    override fun onBindViewHolder(holder: ConferencesAdapter.ConferencesViewHolder, position: Int) {
        val conference = conferences[position]
        holder.bind(conference)
    }

    inner class ConferencesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(conference: Conference) {
            val banner = itemView.findViewById<ImageView>(R.id.banner)
            val title = itemView.findViewById<TextView>(R.id.title)
            val location = itemView.findViewById<TextView>(R.id.location)
            val dates = itemView.findViewById<TextView>(R.id.dates)

            banner.provideImage(itemView.context, conference.logoUrl)
            title.text = conference.name
            location.text = conference.location.country.name + conference.location.country.city
        }
    }
}