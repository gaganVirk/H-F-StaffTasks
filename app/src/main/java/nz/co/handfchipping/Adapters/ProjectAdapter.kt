package nz.co.handfchipping.Adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import nz.co.handfchipping.Models.Project
import nz.co.handfchipping.R

class ProjectAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var projects = emptyList<Project>() // Cached copy of contacts

    inner class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.name)
        val addressTextView: TextView = itemView.findViewById(R.id.address)
        val abbreviationTextView: TextView = itemView.findViewById(R.id.textViewAbbreviation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val itemView = inflater.inflate(R.layout.project_row, parent, false)

        return ProjectViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val current = projects[position]

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("projectId", current.id)

            it.findNavController().navigate(R.id.action_projectsFragment_to_categoriesFragment, bundle)
        }

        holder.nameTextView.text = current.name
        holder.addressTextView.text = current.address
        holder.abbreviationTextView.text = current.name.first().toString()

    }

    internal fun setProjects(projects: List<Project>) {
        this.projects = projects
        notifyDataSetChanged()
    }

    override fun getItemCount() = projects.size
}