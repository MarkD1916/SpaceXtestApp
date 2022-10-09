package com.example.spacextestapp.presentation.list.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.spacextestapp.R
import com.example.spacextestapp.databinding.ItemLaunchesBinding
import com.example.spacextestapp.domain.model.DetailLaunchData
import com.example.spacextestapp.domain.model.ListAndDetailData
import com.example.spacextestapp.ext.toLaunchResultColorString
import com.example.spacextestapp.ext.toLaunchResultString


interface AdapterActionListener {

    fun itemClick(item: DetailLaunchData) {

    }
}

class LaunchesAdapter(
    val context: Context,
    private val actionListener: AdapterActionListener
) : PagingDataAdapter<ListAndDetailData,
        LaunchesAdapter.Holder>(LaunchesDiffCallback()),
    View.OnClickListener {

    private val lastPosition = -1

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val launch = getItem(position)?.list ?: return

//        val animation: Animation = AnimationUtils.loadAnimation(
//            context,
//            if (position > lastPosition) R.anim.up_from_bottom else R.anim.down_from_top
//        )
//        holder.itemView.startAnimation(animation)

        with(holder.binding) {
            launchItemId.tag = getItem(position)?.detail
            launchNameTV.text =
                context.resources.getString(R.string.mission_name, launch.launchName)
            dateTV.text = launch.date
            flightTV.text =
                context.resources.getString(R.string.flight_count, launch.flight.toString())
            statusTV.text =
                launch.launchSuccess.toLaunchResultString().toLaunchResultColorString(context)
            loadMissionImage(launchIV, launch.imageUrl)
        }


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLaunchesBinding.inflate(inflater, parent, false)
        binding.launchItemId.setOnClickListener(this)
        return Holder(binding)
    }

    private fun loadMissionImage(imageView: ImageView, url: String) {
        imageView.load(url) {
            crossfade(true)
            error(R.drawable.ic_image_error_place_holder)
            placeholder(R.drawable.ic_image_load_place_holder)
            transformations(CircleCropTransformation())
        }
    }

    override fun onViewDetachedFromWindow(holder: Holder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }

    class Holder(
        val binding: ItemLaunchesBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onClick(v: View) {
        val item = v.tag as DetailLaunchData
        when (v.id) {
            R.id.launchItemId -> {
                actionListener.itemClick(item)
            }
        }
    }
}

class LaunchesDiffCallback : DiffUtil.ItemCallback<ListAndDetailData>() {
    override fun areItemsTheSame(oldItem: ListAndDetailData, newItem: ListAndDetailData): Boolean {
        return oldItem.list.id == newItem.list.id
    }

    override fun areContentsTheSame(
        oldItem: ListAndDetailData,
        newItem: ListAndDetailData
    ): Boolean {
        return oldItem == newItem
    }
}