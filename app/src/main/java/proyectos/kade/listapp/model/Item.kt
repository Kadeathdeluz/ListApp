package proyectos.kade.listapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class Item(
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "photo")
    var photo: Int,
    @ColumnInfo(name = "checked")
    var checked: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}