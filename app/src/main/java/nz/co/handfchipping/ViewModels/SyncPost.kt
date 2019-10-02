package nz.co.handfchipping.ViewModels

import kotlinx.serialization.Serializable
import nz.co.handfchipping.Models.Category
import nz.co.handfchipping.Models.Project
import nz.co.handfchipping.Models.Subcategory
import nz.co.handfchipping.Models.Task
import nz.co.handfchipping.Network.DateSerializer
import java.util.*

@Serializable
data class SyncPost(
    val projects: List<ProjectPost>,
    val categories: List<CategoryPost>,
    val subcategories: List<SubcategoryPost>,
    val tasks: List<TaskPost>,
    @Serializable(with = DateSerializer::class) val lastUpdated: Date?
) {

    constructor(
        projects: List<Project>,
        categories: List<Category>,
        subcategories: List<Subcategory>,
        tasks: List<Task>,
        lastUpdated: Date?,
        bugFix: Boolean = true
    ) : this(
        projects.map { ProjectPost(it) },
        categories.map { CategoryPost(it) },
        subcategories.map { SubcategoryPost(it) },
        tasks.map { TaskPost(it) },
        lastUpdated
    )
}
