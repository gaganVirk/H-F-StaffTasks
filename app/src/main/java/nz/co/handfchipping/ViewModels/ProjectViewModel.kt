package nz.co.handfchipping.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nz.co.handfchipping.Database.RequirementsDatabase
import nz.co.handfchipping.Models.Project
import nz.co.handfchipping.Repositories.ProjectRepository

class ProjectViewModel(application : Application) : AndroidViewModel(application){
    private val repository : ProjectRepository

    init {
        val project = RequirementsDatabase.getDatabase(application).proejctDao()
        repository = ProjectRepository(project)
    }

    fun getProject(projectId : String) = repository.getProject(projectId)

    fun getAllProjects() = repository.getAllProjects()

    fun getUnsyncedProjects() = repository.getUnSyncedProjects()

    fun insert(project: Project) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(project)
    }

    fun delete(project: Project) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(project)
    }

    fun update(project: Project) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(project)
    }
}


