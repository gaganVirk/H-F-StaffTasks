package nz.co.handfchipping.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import nz.co.handfchipping.Models.Subcategory

@Dao
interface SubcategoryDao {

    @Query("select * from Subcategories where categoryId = :categoryId")
    fun getSubCategories(categoryId: String): LiveData<List<Subcategory>>

    @Query("select * from Subcategories where Id = :subcategoryId")
    fun getSubCategory(subcategoryId: String): Subcategory

    @Query("select * from Subcategories")
    fun getAllSubCategories(): LiveData<List<Subcategory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(subcategory: Subcategory)

    @Update
    suspend fun update(subcategory: Subcategory)

    @Delete
    suspend fun delete(subcategory: Subcategory)
}
