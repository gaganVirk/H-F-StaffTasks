package nz.co.handfchipping.Adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import nz.co.handfchipping.Models.Task
import nz.co.handfchipping.R

class TaskAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var tasks = emptyList<Task>() // Cached copy of contacts

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.name)
        val timeTextView: TextView = itemView.findViewById(R.id.textViewTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = inflater.inflate(R.layout.task_row, parent, false)



        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val current = tasks[position]

        holder.itemView.setOnClickListener {
            val bundle = Bundle()

            bundle.putString("taskId", current.id)

            it.findNavController().navigate(R.id.action_tasksFragment_to_taskFragment, bundle)
        }

        holder.nameTextView.text = current.name
        holder.timeTextView.text = current.numberOfHours.toString()
    }

    internal fun setTasks(categories: List<Task>) {
        this.tasks = categories
        notifyDataSetChanged()
    }

    override fun getItemCount() = tasks.size
}