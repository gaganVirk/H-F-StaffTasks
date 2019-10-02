package nz.co.handfchipping.Fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nz.co.handfchipping.Models.Subcategory
import nz.co.handfchipping.R
import nz.co.handfchipping.ViewModels.CategoryViewModel
import nz.co.handfchipping.ViewModels.SubcategoryViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class AddSubcategoriesFragment : Fragment() {

    private lateinit var templateSubcategoryRecyclerView: RecyclerView
    private lateinit var categoryId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_add_subcategories, container, false)

        categoryId = arguments!!.getString("categoryId")!!


        val categoryModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        val category = categoryModel.getCategory(categoryId)

        val subcategoryModel = ViewModelProvider(this).get(SubcategoryViewModel::class.java)



        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        val model = ViewModelProvider(this).get(SubcategoryViewModel::class.java)


        findNavController().popBackStack()

        return super.onOptionsItemSelected(item)
    }
}