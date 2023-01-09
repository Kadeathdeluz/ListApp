package proyectos.kade.listapp.model

data class ItemList(
    val id: Int,
    val name: String,
    val items: List<Item>
)