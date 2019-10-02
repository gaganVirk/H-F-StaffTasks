package nz.co.handfchipping.ViewModels

import kotlinx.serialization.Serializable


@Serializable
data class TaskGet(val id: String, val name: String, val subcategoryId: String, val numberOfHours: Double, val notes: String)