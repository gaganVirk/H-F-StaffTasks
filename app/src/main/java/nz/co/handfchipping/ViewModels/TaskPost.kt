package nz.co.handfchipping.ViewModels

import kotlinx.serialization.Serializable
import nz.co.handfchipping.Models.Task

@Serializable
data class TaskPost(val id : String, val name: String, val subcategoryId: String, val numberOfHours: Double, val notes: String) {
    constructor(task : Task): this(task.id, task.name, task.subcategoryId, task.numberOfHours, task.notes)
}