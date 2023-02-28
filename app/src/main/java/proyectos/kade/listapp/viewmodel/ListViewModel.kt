package proyectos.kade.listapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import proyectos.kade.listapp.model.Item

class ListViewModel : ViewModel() {
    val item = MutableLiveData<Item>()
    private var name = "currentName"
    private var checked = false

    fun loadList() {

    }

}