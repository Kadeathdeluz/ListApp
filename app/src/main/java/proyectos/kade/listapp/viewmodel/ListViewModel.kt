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


    fun loadList(): List<Item> = itemsList.value ?: listOf()

    fun updateList(list: List<Item>) {

    }

    fun insert(item: Item) {


    }

    fun delete(item: Item) {

    }


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