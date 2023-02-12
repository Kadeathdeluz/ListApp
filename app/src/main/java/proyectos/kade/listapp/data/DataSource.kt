package proyectos.kade.listapp.data

import proyectos.kade.listapp.model.Item

class DataSource {

    fun loadList() = listOf<Item>(
        Item(id= 1, checked = true,name = "Mushroom", description = null,photo = null),
        Item(id= 2, checked = false,name = "Tomato", description = null,photo = null),
        Item(id= 3, checked = false,name = "Steak", description = null,photo = null),
        Item(id= 4, checked = false,name = "Corn", description = null,photo = null),
        Item(id= 5, checked = true,name = "Lettuce", description = null,photo = null),
        Item(id= 6, checked = true,name = "Carrot", description = null,photo = null),
        Item(id= 7, checked = false,name = "Yogurt", description = null,photo = null),
        Item(id= 8, checked = false,name = "Candy", description = null,photo = null),
        Item(id= 9, checked = true,name = "Tea", description = null,photo = null),
        Item(id= 10, checked = false,name = "Pear", description = null,photo = null),
        Item(id= 11, checked = true,name = "Apple", description = null,photo = null)
    )
}