package nz.co.handfchipping.ViewModels

import kotlinx.serialization.Serializable


@Serializable
data class CategoryGet(val id: String, val name: String, val projectId: String)