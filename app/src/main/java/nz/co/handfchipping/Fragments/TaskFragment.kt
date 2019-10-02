package nz.co.handfchipping.Fragments

import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.serialization.ImplicitReflectionSerializer
import nz.co.handfchipping.Extensions.setOnTextChangedListener
import nz.co.handfchipping.Models.Task
import nz.co.handfchipping.R
import nz.co.handfchipping.ViewModels.TaskViewModel

/**
 * A simple [Fragment] subclass.
 */
class TaskFragment : Fragment() {

    lateinit var task: Task
    private lateinit var taskId: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_task, container, false)

        taskId = arguments!!.getString("taskId")!!

        val editTextNotes = view.findViewById<EditText>(R.id.editTextNotes)

        val editTextEstimatedTime = view.findViewById<EditText>(R.id.editTextEstimatedTime)

        val taskId = arguments!!.getString("taskId")!!

        val model = ViewModelProvider(this).get(TaskViewModel::class.java)

        model.getTask(taskId).observe(this, Observer {
            task = it
            (activity as AppCompatActivity).supportActionBar?.title = task.name
            editTextNotes.setText(task.notes)
            editTextEstimatedTime.setText(task.numberOfHours.toString())
        })

        editTextNotes.setOnTextChangedListener {
            task.notes = it
        }

        editTextEstimatedTime.setOnTextChangedListener {
            task.numberOfHours = it.toDoubleOrNull() ?: 0.0
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    @ImplicitReflectionSerializer
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuItemSave) {
            if(task.numberOfHours != 0.0 && task.notes != "") {
                val model = ViewModelProvider(this).get(TaskViewModel::class.java)

                model.update(task).run {
                    findNavController().popBackStack()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}