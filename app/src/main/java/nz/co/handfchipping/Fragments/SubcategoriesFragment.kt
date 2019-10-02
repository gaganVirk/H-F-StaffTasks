package nz.co.handfchipping.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.serialization.ImplicitReflectionSerializer
import nz.co.handfchipping.Adapters.SubcategoryAdapter
import nz.co.handfchipping.R
import nz.co.handfchipping.ViewModels.CategoryViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SubcategoriesFragment : Fragment() {

    private lateinit var subcategoryRecyclerView: RecyclerView

    private lateinit var subcategoryAdapter: SubcategoryAdapter

    private lateinit var categoryId: String

    @ImplicitReflectionSerializer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_subcategories, container, false)


        categoryId = arguments!!.getString("categoryId")!!
        subcategoryRecyclerView = view.findViewById(R.id.subcategoriesRecyclerView)

        subcategoryAdapter = SubcategoryAdapter(requireContext())

        subcategoryRecyclerView.adapter = subcategoryAdapter

        val layoutManager = LinearLayoutManager(requireContext())
        subcategoryRecyclerView.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            subcategoryRecyclerView.context,
            layoutManager.orientation
        )
        subcategoryRecyclerView.addItemDecoration(dividerItemDecoration)


        val categoryModel = ViewModelProviders.of(this)[CategoryViewModel::class.java]

        val categoryName = categoryModel.getCategory(categoryId).name

        (activity as AppCompatActivity).supportActionBar?.title = categoryName + " - Subcategories "


        return view
    }
}
