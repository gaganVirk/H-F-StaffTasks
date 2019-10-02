package nz.co.handfchipping.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nz.co.handfchipping.Database.RequirementsDatabase
import nz.co.handfchipping.Models.Category
import nz.co.handfchipping.Repositories.CategoryRepository


class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : CategoryRepository

    init {
        val category = RequirementsDatabase.getDatabase(application).categoryDao()
        repository = CategoryRepository(category)
    }

    fun getCategory(categoryId : String) = repository.getCategory(categoryId)


    fun getAllCategories() = repository.getAllCategories()

    fun getCategories(projectId: String) = repository.getCategories(projectId)

    fun insert(category: Category) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(category)
    }

    fun delete(category: Category) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(category)
    }

    fun update(category: Category) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(category)
    }
}