package nz.co.handfchipping.Repositories

import androidx.annotation.WorkerThread
import nz.co.handfchipping.Database.TaskDao
import nz.co.handfchipping.Models.Task

class TaskRepository (private val taskDao : TaskDao){

    fun getTasks(subcategoryId : String) = taskDao.getTasks(subcategoryId)

    fun getTask(taskId: String) = taskDao.getTask(taskId)


    fun getAllTasks() = taskDao.getAllTasks()

    @WorkerThread
    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    @WorkerThread
    suspend fun update(task: Task) {
        taskDao.update(task)
    }

    @WorkerThread
    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }
}