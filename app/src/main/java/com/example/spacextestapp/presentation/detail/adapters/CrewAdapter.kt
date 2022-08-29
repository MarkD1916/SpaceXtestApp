package com.example.spacextestapp.presentation.detail.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spacextestapp.data.remote.reaponse.entity.Crew
import com.example.spacextestapp.databinding.ItemCrewBinding

class CrewAdapter (val context: Context, private val crews: List<Crew>) : RecyclerView.Adapter<CrewAdapter.CrewViewHolder>() {

    inner class CrewViewHolder(private val binding: ItemCrewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Crew) {
            this.apply {
                bindItem(item)
            }
        }

        private fun bindItem(item: Crew) {
            with(this.binding) {
                crewNameTV.text = item.name
                crewAgencyTv.text = item.agency
                crewStatusTV.text = item.status
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewViewHolder {
        val binding =
            ItemCrewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CrewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CrewViewHolder, position: Int) {
        holder.bind(crews[position])
    }

    override fun getItemCount(): Int {
        return crews.size
    }


}

