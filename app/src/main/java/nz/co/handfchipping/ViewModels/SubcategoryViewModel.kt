package nz.co.handfchipping.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nz.co.handfchipping.Database.RequirementsDatabase
import nz.co.handfchipping.Models.Subcategory
import nz.co.handfchipping.Repositories.SubcategoryRepository

class SubcategoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : SubcategoryRepository

    init {
        val subcategoryDao = RequirementsDatabase.getDatabase(application).subcategoryDao()
        repository = SubcategoryRepository(subcategoryDao)

    }

    fun getSubcategory(subcategoryId : String) = repository.getSubcategory(subcategoryId)

    fun getSubcategories(categoryId: String) = repository.getSubcategories(categoryId)

    fun getAllSubcategories() = repository.getAllSubcategories()

    fun insert(subcategory: Subcategory) = viewModelScope.launch {
        repository.insert(subcategory)
    }

    fun delete(subcategory: Subcategory) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(subcategory)
    }

    fun update(subcategory: Subcategory) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(subcategory)
    }
}
