package proyectos.kade.listapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import proyectos.kade.listapp.databinding.ItemViewBinding
import proyectos.kade.listapp.model.Item
import proyectos.kade.listapp.ui.ListFragment
import proyectos.kade.listapp.ui.ListFragmentDirections


class ListAdapter(private val itemList: List<Item>) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {


    class ViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            //To improve readability
            val checkBox = binding.checkbox
            val itemNameET = binding.tvItemName
            val editBtn = binding.btnEdit
            val deleteBtn = binding.btnDelete

            with(itemList[position]) {

                checkBox.setOnCheckedChangeListener { _, isChecked ->
                    this.checked = isChecked
                }
                checkBox.isChecked = this.checked
                itemNameET.text = this.name

                editBtn.setOnClickListener {
                    val action = ListFragmentDirections.actionListFragmentToDetailFragment(
                        id = this.id,
                        name = this.name,
                        photo = this.photo,
                        description = this.description,
                        title = this.name,
                        checked = this.checked
                    )
                    holder.binding.root.findNavController().navigate(action)
                }

                deleteBtn.setOnClickListener {
                    it.findFragment<ListFragment>().delete(itemList[position])
                }
            }
        }
    }

    override fun getItemCount(): Int = itemList.size


}