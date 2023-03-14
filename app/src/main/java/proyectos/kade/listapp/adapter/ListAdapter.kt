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


    class ViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val checkBox = binding.checkbox
        val itemNameET = binding.tvItemName
        val editBtn = binding.btnEdit
        val deleteBtn = binding.btnDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(itemList[position]) { val item = this //To improve readability
                //UI components
                itemNameET.text = item.name
                checkBox.isChecked = item.checked

                //UI components listeners
                editBtn.setOnClickListener {
                    val action = ListFragmentDirections.actionListFragmentToDetailFragment(
                        id = item.id,
                        name = item.name,
                        photo = item.photo,
                        description = item.description,
                        title = item.name,
                        checked = item.checked
                    )
                    binding.root.findNavController().navigate(action)
                }
                deleteBtn.setOnClickListener { currentView ->
                    currentView.findFragment<ListFragment>().delete(item)
                }
                checkBox.setOnCheckedChangeListener { _, isChecked ->
                    item.checked = isChecked
                }

            }
        }
    }

    override fun getItemCount(): Int = itemList.size


}