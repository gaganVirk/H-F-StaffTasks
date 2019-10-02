package nz.co.handfchipping.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import nz.co.handfchipping.Models.Task

@Dao
interface TaskDao {
    @Query("select * from Tasks WHERE subcategoryId = :subcategoryId")
    fun getTasks(subcategoryId: String): LiveData<List<Task>>

    @Query("select * from Tasks WHERE id = :taskId")
    fun getTask(taskId: String): LiveData<Task>

    @Query("select * from Tasks")
    fun getAllTasks(): LiveData<List<Task>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)
}