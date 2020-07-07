package com.ianarbuckle.conferencesfinder.ui.conferences.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ianarbuckle.conferencesfinder.R
import com.ianarbuckle.conferencesfinder.utils.provideImage
import conferences.model.Conference
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeFormatterBuilder
import org.threeten.bp.temporal.ChronoField
import kotlinx.android.synthetic.main.conferences_item_view.view.*

class ConferencesAdapter(private val conferences: List<Conference>) : RecyclerView.Adapter<ConferencesAdapter.ConferencesViewHolder>() {

    private var onItemClickListener: ((Conference) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConferencesAdapter.ConferencesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.conferences_item_view, parent, false)
        return ConferencesViewHolder(view)
    }

    override fun getItemCount(): Int = conferences.size

    override fun onBindViewHolder(holder: ConferencesAdapter.ConferencesViewHolder, position: Int) {
        val conference = conferences[position]
        holder.bind(conference)
    }

    fun onItemClickListener(callback: (Conference) -> Unit) {
        onItemClickListener = callback
    }

    inner class ConferencesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(conference: Conference) {
            val name = conference.location.country.name
            val city = conference.location.country.city
            val startDate = conference.dates.startDate

            itemView.status.text = conference.status
            itemView.banner.provideImage(itemView.context, conference.logoUrl)
            itemView.title.text = conference.name

            val locationFormat = itemView.context.getString(R.string.location_format, name, city)
            itemView.venueName.text = locationFormat

            val dateFormat = dateFormat("yyyy-MM-dd")
            val parseStartDate = LocalDate.parse(startDate, dateFormat)
            itemView.dates.text = parseStartDate.toString()

            itemView.setOnClickListener {
                onItemClickListener?.invoke(conference)
            }
        }
    }

    fun dateFormat(pattern: String): DateTimeFormatter {
        val dateTimeFormatter = DateTimeFormatter.ofPattern(pattern)
        return DateTimeFormatterBuilder().append(dateTimeFormatter)
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter()
    }
}