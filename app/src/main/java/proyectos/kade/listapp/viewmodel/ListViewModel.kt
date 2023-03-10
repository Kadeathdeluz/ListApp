package proyectos.kade.listapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import proyectos.kade.listapp.model.Item

class ListViewModel : ViewModel() {
    private val _itemsList = MutableLiveData<List<Item>>()
    val itemsList: LiveData<List<Item>>
        get() = _itemsList

    private var counter: Int = 0

    fun loadList(): List<Item> = itemsList.value ?: listOf()

    fun updateList(list: List<Item>) {
        if (itemsList.value == null)
            _itemsList.value = listOf()
        else _itemsList.value = list
    }

    fun addItem(item: Item) {
        if(item.id == 0) return
        val tempList: MutableList<Item> =
            itemsList.value?.toMutableList() ?: mutableListOf()
        val find: Item? = tempList.find{it.id == item.id}
        if( find != null)
            tempList[tempList.indexOf(find)] = item
        else tempList.add(item)
        updateList(tempList)

    }

    fun delete(item: Item) {
        val tempList: MutableList<Item> = itemsList.value?.toMutableList() ?: mutableListOf()
        if(tempList.isNotEmpty())
            tempList.remove(item)
        updateList(tempList.toList())
    }

    fun newId(): Int = ++counter
}

class ListViewModelFactory() : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ListViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ListViewModel() as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }
}