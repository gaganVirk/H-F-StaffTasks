package nz.co.handfchipping.Models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Projects")
class Project(name: String, address: String, contactNumber: String, email: String, id: String? = null) {
    @PrimaryKey
    var id: String = id ?: UUID.randomUUID().toString()

    var name: String = name

    var address: String = address

    var contactNumber: String = contactNumber

    var email: String = email

    var synced: Boolean = false


}