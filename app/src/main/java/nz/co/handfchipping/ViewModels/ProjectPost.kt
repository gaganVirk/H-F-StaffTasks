package nz.co.handfchipping.ViewModels

import kotlinx.serialization.Serializable
import nz.co.handfchipping.Models.Project


@Serializable
data class ProjectPost(val id: String, val name: String, val contactNumber: String, val address: String, val email: String) {
    constructor(project: Project): this(project.id, project.name, project.contactNumber, project.address, project.email)
}