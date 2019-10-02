package nz.co.handfchipping.Fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nz.co.handfchipping.Models.Task
import nz.co.handfchipping.R
import nz.co.handfchipping.ViewModels.SubcategoryViewModel
import nz.co.handfchipping.ViewModels.TaskViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class AddTasksFragment : Fragment() {

    private lateinit var taskTemplateRecyclerView: RecyclerView
    private lateinit var subcategoryId : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_add_tasks, container, false)
        subcategoryId = arguments!!.getString("subcategoryId")!!

        taskTemplateRecyclerView = view.findViewById(R.id.add_tasksRecyclerView)


        val layoutManager = LinearLayoutManager(requireContext())
        taskTemplateRecyclerView.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            taskTemplateRecyclerView.context,
            layoutManager.orientation
        )
        taskTemplateRecyclerView.addItemDecoration(dividerItemDecoration)

        val subcategoryModel = ViewModelProviders.of(this)[SubcategoryViewModel::class.java]

        val subcategory = subcategoryModel.getSubcategory(subcategoryId)


        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val model = ViewModelProvider(this).get(TaskViewModel::class.java)


        findNavController().popBackStack()

        return super.onOptionsItemSelected(item)
    }
}
