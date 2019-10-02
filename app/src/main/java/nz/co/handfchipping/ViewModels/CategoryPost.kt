package nz.co.handfchipping.ViewModels

import kotlinx.serialization.Serializable
import nz.co.handfchipping.Models.Category

@Serializable
data class CategoryPost(val id: String, val name: String, val projectId: String){
    constructor(category : Category): this(category.id, category.name, category.projectId)
}