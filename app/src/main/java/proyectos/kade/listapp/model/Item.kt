package proyectos.kade.listapp.model

data class Item(
    val id: Int,
    var name: String,
    var description: String?,
    var photo: Int?,
    var checked: Boolean
)