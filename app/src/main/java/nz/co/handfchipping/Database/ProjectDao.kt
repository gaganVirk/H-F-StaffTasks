package nz.co.handfchipping.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import nz.co.handfchipping.Models.Project

@Dao
interface ProjectDao {

    @Query("select * from Projects WHERE id = :id")
    fun getProject(id : String): Project

    @Query("select * from Projects")
    fun getAllProjects(): LiveData<List<Project>>

    @Query("select * from Projects where not synced")
    fun getUnSyncedProjects(): List<Project>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(project: Project)

    @Update
    suspend fun update(project: Project)

    @Delete
    suspend fun delete(project: Project)
}