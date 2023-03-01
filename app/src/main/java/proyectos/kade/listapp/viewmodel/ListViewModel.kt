package proyectos.kade.listapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import proyectos.kade.listapp.model.Item
import proyectos.kade.listapp.model.data.DataSource

class ListViewModel : ViewModel() {
    private val _itemsList = MutableLiveData<List<Item>>()
    val itemsList: LiveData<List<Item>> get() = _itemsList
    //???
    fun loadList(): List<Item> = DataSource().loadList()


}