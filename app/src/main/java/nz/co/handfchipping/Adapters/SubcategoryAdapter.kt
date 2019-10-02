package nz.co.handfchipping.Adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import nz.co.handfchipping.Models.Subcategory
import nz.co.handfchipping.R

class SubcategoryAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<SubcategoryAdapter.SubcategoryViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var subcategories = emptyList<Subcategory>() // Cached copy of contacts

    inner class SubcategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubcategoryViewHolder {
        val itemView = inflater.inflate(R.layout.subcategory_row, parent, false)
        return SubcategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SubcategoryViewHolder, position: Int) {
        val current = subcategories[position]

        holder.nameTextView.text = current.name

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("subcategoryId", current.id)

            it.findNavController().navigate(R.id.action_subcategoriesFragment_to_tasksFragment, bundle)
        }

    }

    internal fun setSubcategory(subcategories: List<Subcategory>) {
        this.subcategories = subcategories
        notifyDataSetChanged()
    }

    override fun getItemCount() = subcategories.size
}