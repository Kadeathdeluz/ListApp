package proyectos.kade.listapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import proyectos.kade.listapp.databinding.ItemViewBinding
import proyectos.kade.listapp.model.Item

class ListAdapter(private val itemList: List<Item>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemViewBinding ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(itemList[position]){
                binding.checkbox.isChecked = this.checked
                binding.itemName.text = this.name
            }
        }
    }

    override fun getItemCount(): Int = itemList.size
}