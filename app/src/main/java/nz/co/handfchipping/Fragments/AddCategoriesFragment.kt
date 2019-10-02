package nz.co.handfchipping.Fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.serialization.ImplicitReflectionSerializer
import nz.co.handfchipping.Models.Category
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
class AddCategoriesFragment : Fragment() {

    private lateinit var templateCategoryRecyclerView : RecyclerView
    @ImplicitReflectionSerializer
    private lateinit var projectId: String

    @ImplicitReflectionSerializer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("testing", "testing")
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_categories, container, false)

        projectId = arguments!!.getString("projectId")!!

        templateCategoryRecyclerView = view.findViewById(R.id.add_categoriesRecyclerView)



        val layoutManager = LinearLayoutManager(requireContext())
        templateCategoryRecyclerView.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            templateCategoryRecyclerView.context,
            layoutManager.orientation
        )
        templateCategoryRecyclerView.addItemDecoration(dividerItemDecoration)


        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    @ImplicitReflectionSerializer
    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        val model = ViewModelProvider(this).get(CategoryViewModel::class.java)


        findNavController().popBackStack()

        return super.onOptionsItemSelected(item)
    }

}
