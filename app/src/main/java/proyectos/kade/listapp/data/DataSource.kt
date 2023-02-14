package proyectos.kade.listapp.data

import proyectos.kade.listapp.R
import proyectos.kade.listapp.model.Item

class DataSource {

    fun loadList() = listOf<Item>(
        Item(id= 1, checked = true,name = "Mushroom", description = "Tasty small mushroom from Consum.",photo = R.drawable.mushroom),
        Item(id= 2, checked = false,name = "Tomato", description = "Red and green, perfect for salads. From LIDL.",photo = R.drawable.tomatoe),
        Item(id= 3, checked = false,name = "Steak", description = "Stick your steak with Steve.",photo = R.drawable.steak),
        Item(id= 4, checked = false,name = "Corn", description = "Korn is not corn.",photo = R.drawable.corn),
        Item(id= 5, checked = true,name = "Lettuce", description = "Greenie weenie, not Martini. From Mercadona.",photo = R.drawable.lettuce),
        Item(id= 6, checked = true,name = "Carrot", description = "What's up man?",photo = R.drawable.carrot),
        Item(id= 7, checked = false,name = "Yogurt", description = "Y-O-G-U-R-T. From ALDI.",photo = R.drawable.yogurt),
        Item(id= 8, checked = false,name = "Candy", description = "Bailando plan B, la de Candy.",photo = R.drawable.candy),
        Item(id= 9, checked = true,name = "Tea", description = "Not so good tea for me.",photo = R.drawable.tea),
        Item(id= 10, checked = false,name = "Pear", description = "I like your pears.",photo = R.drawable.pear),
        Item(id= 11, checked = true,name = "Cake", description = "Birthday cake made of too many sugar.",photo = R.drawable.cake)
    )
}