package gr.repo.kincart.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gr.repo.kincart.databinding.CaseStudyItemBinding
import gr.repo.kincart.models.CaseStudy

class CaseStudyAdapter(
        val context: Context,
        val caseStudyList: MutableList<CaseStudy>)
    : RecyclerView.Adapter<CaseStudyAdapter.ViewHolder>() {

    private lateinit var parentContext: Context

    override fun getItemCount() = caseStudyList.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val binding: CaseStudyItemBinding = CaseStudyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ViewHolder(binding)
}

    inner class ViewHolder(val binding: CaseStudyItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CaseStudy, position: Int) {

            binding.caseStudy = item
        }
    }


    fun updateItems(newItems: List<CaseStudy>) {
        caseStudyList.clear()
        caseStudyList.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(caseStudyList[position], position)
    }

}