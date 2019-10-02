package nz.co.handfchipping.ViewModels

import kotlinx.serialization.Serializable


@Serializable
data class SubcategoryGet(val id: String, val name: String, val categoryId: String)