package me.mladenrakonjac.modernandroidapp.ui.rvadapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import me.mladenrakonjac.modernandroidapp.databinding.RvItemRepositoryBinding
import me.mladenrakonjac.modernandroidapp.ui.uimodels.Repository


class RepositoryRecyclerViewAdapter(private var items: ArrayList<Repository>,
                                    private var listener: OnItemClickListener)
    : RecyclerView.Adapter<RepositoryRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvItemRepositoryBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun replaceData(arrayList: ArrayList<Repository>) {
        items = arrayList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: RvItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: Repository) {
            binding.repositoryNameTextView.text = repo.repositoryName
            binding.repositoryOwnerTextView.text = repo.repositoryOwner
            binding.numberOfStarsTextView.text = repo.numberOfStars.toString()
            binding.repositoryHasIssuesTextView.visibility =
                if (repo.hasIssues) View.VISIBLE else View.GONE

            binding.root.setOnClickListener { listener.onItemClick(layoutPosition) }
        }
    }
}
