package nz.co.handfchipping.Fragments

import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import nz.co.handfchipping.Extensions.setOnTextChangedListener
import nz.co.handfchipping.Models.Project
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
class CreateUpdateProjectFragment : Fragment() {

    val project = Project("", "", "", "")

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_update_project, container, false)

        view.findViewById<EditText>(R.id.editTextName).setOnTextChangedListener {
            project.name = it
        }

        view.findViewById<EditText>(R.id.editTextAddress).setOnTextChangedListener {
            project.address = it
        }

        view.findViewById<EditText>(R.id.editTextContactNumber).setOnTextChangedListener {
            project.contactNumber = it
        }

        view.findViewById<EditText>(R.id.editTextEmail).setOnTextChangedListener {
            project.email = it
        }

        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuItemSave) {
            if(project.address != "" && project.name != "" && project.email != "" && project.contactNumber != "") {
                val model = ViewModelProviders.of(this)[ProjectViewModel::class.java]

                model.insert(project).run {
                    findNavController().popBackStack()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}