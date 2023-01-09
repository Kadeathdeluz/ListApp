package proyectos.kade.listapp.model

import android.graphics.Bitmap

data class Item(
    val id: Int,
    var name: String,
    var description: String?,
    var photo: Bitmap?,
    var checked: Boolean
)