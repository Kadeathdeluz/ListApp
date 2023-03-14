package proyectos.kade.listapp.model.data

import androidx.lifecycle.LiveData
import androidx.room.*
import proyectos.kade.listapp.model.Item

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * FROM item")
    fun getAllItems(): LiveData<List<Item>>
}
