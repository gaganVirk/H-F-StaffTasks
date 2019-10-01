package nz.co.handfchipping.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

class Category(name: String, projectId: String, id: String? = null) {
    @PrimaryKey
    var id: String = id ?: UUID.randomUUID().toString()

    var projectId: String = projectId

    var name: String = name
}