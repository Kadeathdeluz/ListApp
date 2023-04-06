package proyectos.kade.listapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import proyectos.kade.listapp.model.Item
import proyectos.kade.listapp.repository.ItemRepository

class ListViewModel(private val repository: ItemRepository) : ViewModel() {
    private val _itemsList = MutableLiveData<List<Item>>()
    val itemsList: LiveData<List<Item>>
        get() = _itemsList

    fun insert(item: Item) = GlobalScope.launch {
        repository.insert(item)
    }

    fun delete(item: Item) = GlobalScope.launch {
        repository.delete(item)
    }

    fun getAllItems() = repository.getAllItems()

}

class ListViewModelFactory(private val repository: ItemRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ListViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ListViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }
}