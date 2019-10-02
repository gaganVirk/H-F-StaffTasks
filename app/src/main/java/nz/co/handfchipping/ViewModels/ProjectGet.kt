package nz.co.handfchipping.ViewModels

import kotlinx.serialization.Serializable


@Serializable
data class ProjectGet(val id : String, val name : String, val address: String, val contactNumber: String, val email: String)