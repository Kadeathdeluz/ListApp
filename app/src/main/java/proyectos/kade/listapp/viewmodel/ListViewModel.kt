package proyectos.kade.listapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import proyectos.kade.listapp.model.Item

class ListViewModel : ViewModel() {
    private val _itemsList = MutableLiveData<List<Item>>()
    val itemsList: LiveData<List<Item>> get() = _itemsList
    init {
        _itemsList.value = listOf()
    }
    fun loadList(): List<Item> = itemsList.value ?: listOf()
    fun updateList(list: List<Item>) {
        if (itemsList.value == null)
            _itemsList.value = listOf()
        else _itemsList.value = list
    }

    fun addItem(item: Item) {
        val tempList: MutableList<Item> =
            itemsList.value?.toMutableList() ?: mutableListOf()
        Log.e("PRE LIST", "${itemsList.value}")
        if(!tempList.contains(item))
            tempList.add(item)
        else {
            tempList.remove(item)
            tempList.add(item)
        }
        updateList(tempList)
        Log.e("POST LIST", "${itemsList.value}")

    }

    fun delete(item: Item) {
        val tempList: MutableList<Item> = itemsList.value?.toMutableList() ?: mutableListOf()
        tempList.remove(item)
        _itemsList.value = tempList.toList()
    }
}