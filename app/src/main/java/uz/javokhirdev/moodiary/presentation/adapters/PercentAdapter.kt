package uz.javokhirdev.moodiary.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.javokhirdev.moodiary.data.model.PercentResponse
import uz.javokhirdev.moodiary.databinding.ItemPercentBinding
import uz.javokhirdev.moodiary.utils.textColor

class PercentAdapter : ListAdapter<PercentResponse, PercentAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemPercentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(private val binding: ItemPercentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PercentResponse) {
            with(binding) {
                textDaysCount.textColor(item.colorText)
                textDays.textColor(item.colorText)

                textDaysCount.text = item.total.toString()
                textDays.text = item.title
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<PercentResponse>() {
        override fun areItemsTheSame(
            oldItem: PercentResponse,
            newItem: PercentResponse
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: PercentResponse,
            newItem: PercentResponse
        ): Boolean = oldItem == newItem
    }
}