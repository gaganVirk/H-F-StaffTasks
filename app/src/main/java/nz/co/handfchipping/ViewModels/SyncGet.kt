package nz.co.handfchipping.ViewModels

import kotlinx.serialization.Serializable

@Serializable
data class SyncGet(
    val projects: List<ProjectGet>,
    val categories: List<CategoryGet>,
    val subcategories: List<SubcategoryGet>,
    val tasks: List<TaskGet>
)