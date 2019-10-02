package nz.co.handfchipping.Repositories

import androidx.annotation.WorkerThread
import nz.co.handfchipping.Database.ProjectDao
import nz.co.handfchipping.Models.Project

class ProjectRepository (private val projectDao : ProjectDao){

    fun getProject(projectId : String) = projectDao.getProject(projectId)

    fun getAllProjects() = projectDao.getAllProjects()

    fun getUnSyncedProjects() = projectDao.getUnSyncedProjects()

    @WorkerThread
    suspend fun insert(project: Project) {
        projectDao.insert(project)
    }

    @WorkerThread
    suspend fun update(project: Project) {
        projectDao.update(project)
    }

    @WorkerThread
    suspend fun delete(project: Project) {
        projectDao.delete(project)
    }
}