package nz.co.handfchipping.Repositories

import androidx.annotation.WorkerThread
import nz.co.handfchipping.Database.CategoryDao
import nz.co.handfchipping.Models.Category

class CategoryRepository (private val categoryDao : CategoryDao) {

    fun getCategory(categoryId: String) = categoryDao.getCategory(categoryId)

    fun getAllCategories() = categoryDao.getAllCategories()

    fun getCategories(projectId: String) = categoryDao.getCategories(projectId)

    @WorkerThread
    suspend fun insert(category: Category) {
        categoryDao.insert(category)
    }

    @WorkerThread
    suspend fun update(category: Category) {
        categoryDao.update(category)
    }

    @WorkerThread
    suspend fun delete(category: Category) {
        categoryDao.delete(category)
    }
}