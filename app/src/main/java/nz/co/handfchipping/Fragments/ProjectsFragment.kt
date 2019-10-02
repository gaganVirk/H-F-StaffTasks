package nz.co.handfchipping.Fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.serialization.ImplicitReflectionSerializer
import nz.co.handfchipping.Adapters.ProjectAdapter
import nz.co.handfchipping.Database.RequirementsDatabase
import nz.co.handfchipping.R
import nz.co.handfchipping.ViewModels.ProjectViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ProjectsFragment : Fragment() {

    private lateinit var projectRecyclerView: RecyclerView

    private lateinit var projectAdapter: ProjectAdapter


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.projects_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuItemLogOut) {

            Thread(kotlinx.coroutines.Runnable {
                RequirementsDatabase.getDatabase(requireContext()).clearAllTables()

                val sharedPreferences =
                    requireContext().getSharedPreferences("hfsystems", Context.MODE_PRIVATE).edit()

                sharedPreferences.clear()

                sharedPreferences.apply()

                this.requireActivity().runOnUiThread {
                    findNavController().setGraph(R.navigation.login_graph)
                }
            }).start()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_projects, container, false)

        projectRecyclerView = view.findViewById(R.id.projectsRecyclerView)

        projectAdapter = ProjectAdapter(requireContext())

        projectRecyclerView.adapter = projectAdapter

        val layoutManager = LinearLayoutManager(requireContext())
        projectRecyclerView.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            projectRecyclerView.context,
            layoutManager.orientation
        )
        projectRecyclerView.addItemDecoration(dividerItemDecoration)

        val model = ViewModelProviders.of(this)[ProjectViewModel::class.java]

        model.getAllProjects().observe(this, Observer {
            projectAdapter.setProjects(it)
        })

        view.findViewById<FloatingActionButton>(R.id.floatingActionButtonAdd).setOnClickListener {
            findNavController().navigate(R.id.action_projectsFragment_to_createUpdateProjectFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {}

        setHasOptionsMenu(true)

        return view
    }

    @ImplicitReflectionSerializer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        GlobalScope.launch {
//            RequestManager.getInstance(requireContext()).sync(requireContext())
//        }
    }


}