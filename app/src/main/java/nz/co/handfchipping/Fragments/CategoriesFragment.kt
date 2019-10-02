package nz.co.handfchipping.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.serialization.ImplicitReflectionSerializer
import nz.co.handfchipping.Adapters.CategoryAdapter
import nz.co.handfchipping.MainActivity
import nz.co.handfchipping.R
import nz.co.handfchipping.ViewModels.CategoryViewModel
import nz.co.handfchipping.ViewModels.ProjectViewModel



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CategoriesFragment : Fragment() {

    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter

    private lateinit var projectFragment: ProjectsFragment
    private lateinit var projectId: String


    @ImplicitReflectionSerializer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_categories, container, false)


        projectId = arguments!!.getString("projectId")!!

        categoryRecyclerView = view.findViewById(R.id.categoriesRecyclerView)

        categoryAdapter = CategoryAdapter(requireContext())

        categoryRecyclerView.adapter = categoryAdapter

        val layoutManager = LinearLayoutManager(requireContext())
        categoryRecyclerView.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            categoryRecyclerView.context,
            layoutManager.orientation
        )
        categoryRecyclerView.addItemDecoration(dividerItemDecoration)


        var model = ViewModelProviders.of(this)[CategoryViewModel::class.java]


        val projectModel = ViewModelProviders.of(this)[ProjectViewModel::class.java]

        val categoryName = projectModel.getProject(projectId).name

        (activity as AppCompatActivity).supportActionBar?.title = categoryName + " - Categories"


        val projectId = arguments!!.getString("projectId")

        model.getCategories(projectId).observe(this, Observer {
            categoryAdapter.setCategories(it)
        })
        return view
    }
}