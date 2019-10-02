package nz.co.handfchipping.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.serialization.ImplicitReflectionSerializer
import nz.co.handfchipping.Adapters.TaskAdapter
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
class TasksFragment : Fragment() {

    private lateinit var taskRecyclerView: RecyclerView
    private lateinit var taskAdapter : TaskAdapter
    private lateinit var subcategoryId : String
    private lateinit var timeTextView : TextView

    @ImplicitReflectionSerializer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tasks, container, false)

        taskRecyclerView = view.findViewById(R.id.tasksRecyclerView)
        taskAdapter = TaskAdapter(requireContext())
        taskRecyclerView.adapter = taskAdapter

        val layoutManager = LinearLayoutManager(requireContext())
        taskRecyclerView.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            taskRecyclerView.context,
            layoutManager.orientation
        )
        taskRecyclerView.addItemDecoration(dividerItemDecoration)

        val model = ViewModelProvider(this).get(TaskViewModel::class.java)

        val subcategoryModel = ViewModelProvider(this).get(SubcategoryViewModel::class.java)


        subcategoryId = arguments!!.getString("subcategoryId")!!


        val subcategoryName = subcategoryModel.getSubcategory(subcategoryId).name

        (activity as AppCompatActivity).supportActionBar?.title = subcategoryName + " - Tasks"

        model.getTasks(subcategoryId).observe(this, Observer {
            taskAdapter.setTasks(it)

        })

        view.findViewById<FloatingActionButton>(R.id.floatingActionButtonAdd).setOnClickListener {
            val bundle = Bundle()
            bundle.putString("subcategoryId", subcategoryId)
            findNavController().navigate(R.id.action_tasksFragment_to_addTasksFragment, bundle)
        }

        return view


    }
}