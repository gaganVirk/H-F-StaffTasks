package nz.co.handfchipping.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "Tasks",
    foreignKeys = [ForeignKey(
        entity = Subcategory::class,
        parentColumns = ["id"],
        childColumns = ["subcategoryId"],
        onDelete = ForeignKey.CASCADE
    )
    ]
)
class Task(name: String, subcategoryId: String, numberOfHours: Double, notes: String, id: String? = null) {
    @PrimaryKey
    var id: String = id ?: UUID.randomUUID().toString()

    var subcategoryId: String = subcategoryId

    var name: String = name

    var numberOfHours: Double = numberOfHours

    var notes: String = notes
}