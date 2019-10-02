package nz.co.handfchipping.Repositories

import androidx.annotation.WorkerThread
import nz.co.handfchipping.Database.SubcategoryDao
import nz.co.handfchipping.Models.Subcategory

class SubcategoryRepository (private val subcategoryDao : SubcategoryDao){

    fun getSubcategory(subcategoryId : String) = subcategoryDao.getSubCategory(subcategoryId)

    fun getSubcategories(categoryId: String) = subcategoryDao.getSubCategories(categoryId)

    fun getAllSubcategories() = subcategoryDao.getAllSubCategories()

    @WorkerThread
    suspend fun insert(subcategory: Subcategory) {
        subcategoryDao.insert(subcategory)
    }

    @WorkerThread
    suspend fun update(subcategory: Subcategory) {
        subcategoryDao.update(subcategory)
    }

    @WorkerThread
    suspend fun delete(subcategory: Subcategory) {
        subcategoryDao.delete(subcategory)
    }
}