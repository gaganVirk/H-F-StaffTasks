package nz.co.handfchipping.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import nz.co.handfchipping.Models.Category

@Dao
interface CategoryDao {

    @Query("select * from Categories where id = :categoryId")
    fun getCategory(categoryId: String): Category

    @Query("select * from Categories")
    fun getAllCategories(): LiveData<List<Category>>

    @Query("select * from Categories where projectId = :projectId")
    fun getCategories(projectId: String): LiveData<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: Category)

    @Update
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(category: Category)
}