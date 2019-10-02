package nz.co.handfchipping.ViewModels

import kotlinx.serialization.Serializable
import nz.co.handfchipping.Models.Subcategory

@Serializable
data class SubcategoryPost (val id: String, val name: String, val categoryId: String) {
    constructor(subcategory : Subcategory): this(subcategory.id, subcategory.name, subcategory.categoryId)
}