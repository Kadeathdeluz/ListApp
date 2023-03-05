package proyectos.kade.listapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import proyectos.kade.listapp.R
import proyectos.kade.listapp.model.Item
import proyectos.kade.listapp.model.data.DataSource

class ListViewModel : ViewModel() {
    private val _itemsList = MutableLiveData<List<Item>>()
    val itemsList: LiveData<List<Item>> get() = _itemsList
    //???
    fun loadList(): List<Item> = itemsList.value ?: listOf()

    fun addItem(item: Item) {
        val tempList: MutableList<Item> = itemsList.value?.toMutableList() ?: mutableListOf()
        tempList.add(item)
        _itemsList.value = tempList.toList()
    }
}