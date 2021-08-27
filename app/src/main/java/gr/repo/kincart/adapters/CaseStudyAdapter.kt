package gr.repo.kincart.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import gr.repo.kincart.databinding.CaseStudyItemBinding
import gr.repo.kincart.models.CaseStudy

class CaseStudyAdapter
    : ListAdapter<CaseStudy, CaseStudyAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val binding: CaseStudyItemBinding = CaseStudyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ViewHolder(binding)
}

    inner class ViewHolder(val binding: CaseStudyItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CaseStudy, position: Int) {

            binding.caseStudy = item
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, position)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CaseStudy>() {
        // your DiffCallback implementation
        override fun areItemsTheSame(oldItem: CaseStudy, newItem: CaseStudy): Boolean {
            return oldItem.teaser == newItem.teaser
        }

        override fun areContentsTheSame(oldItem: CaseStudy, newItem: CaseStudy): Boolean {
            return oldItem == newItem
        }
    }

}