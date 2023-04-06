package proyectos.kade.listapp.repository

import proyectos.kade.listapp.model.Item
import proyectos.kade.listapp.model.data.ItemRoomDatabase

class ItemRepository(private val db: ItemRoomDatabase) {

    suspend fun insert(item: Item) = db.getItemDao().insert(item)
    suspend fun delete(item: Item) = db.getItemDao().delete(item)

    fun getAllItems() = db.getItemDao().getAllItems()
}