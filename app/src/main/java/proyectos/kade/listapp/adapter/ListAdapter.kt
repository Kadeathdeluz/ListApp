package proyectos.kade.listapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import proyectos.kade.listapp.R
import proyectos.kade.listapp.databinding.ItemViewBinding
import proyectos.kade.listapp.model.Item
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
            with(itemList[position]) {
                binding.checkbox.isChecked = this.checked
                binding.tvItemName.text = this.name
                binding.editButton.setOnClickListener {
                    val action = ListFragmentDirections.actionListFragmentToDetailFragment(
                        name = name,
                        photo = this.photo ?: R.drawable.corn,
                        description = this.description.toString()
                    )
                    holder.binding.root.findNavController().navigate(action)
                }
                binding.deleteButton.setOnClickListener {
                    Toast.makeText(
                        binding.root.context,
                        "${holder.binding.tvItemName.text} deleted successful",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun getItemCount(): Int = itemList.size


}