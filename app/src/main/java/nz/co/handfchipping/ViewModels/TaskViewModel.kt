package nz.co.handfchipping.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nz.co.handfchipping.Database.RequirementsDatabase
import nz.co.handfchipping.Models.Task
import nz.co.handfchipping.Repositories.TaskRepository

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : TaskRepository

    init {
        val task = RequirementsDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(task)
    }

    fun getTask(taskId: String) = repository.getTask(taskId)

    fun getTasks(subcategoryId : String) = repository.getTasks(subcategoryId)

    fun getAllTasks() = repository.getAllTasks()

    fun insert(task : Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(task)
    }

    fun delete(task : Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(task)
    }

    fun update(task : Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(task)
    }
}