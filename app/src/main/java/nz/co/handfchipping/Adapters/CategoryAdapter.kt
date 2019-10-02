package nz.co.handfchipping.Adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import nz.co.handfchipping.Models.Category
import nz.co.handfchipping.R

class CategoryAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var categories = emptyList<Category>() // Cached copy of contacts

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = inflater.inflate(R.layout.category_row, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val current = categories[position]

        holder.nameTextView.text = current.name

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("categoryId", current.id)

            it.findNavController().navigate(R.id.action_categoriesFragment_to_subcategoriesFragment, bundle)
        }
    }

    internal fun setCategories(categories: List<Category>) {
        this.categories = categories
        notifyDataSetChanged()
    }

    override fun getItemCount() = categories.size
}