package proyectos.kade.listapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import proyectos.kade.listapp.R
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
            with(itemList[position]) {
                binding.checkbox.setOnClickListener {
                    this.checked = binding.checkbox.isChecked
                }
                binding.checkbox.isChecked = this.checked
                binding.tvItemName.text = this.name
                binding.editButton.setOnClickListener {
                    val action = ListFragmentDirections.actionListFragmentToDetailFragment(
                        id = id,
                        name = name,
                        photo = this.photo ?: R.drawable.corn,
                        description = this.description.toString(),
                        title = name,
                        checked = binding.checkbox.isChecked
                    )
                    holder.binding.root.findNavController().navigate(action)
                }
                binding.deleteButton.setOnClickListener {
                    it.findFragment<ListFragment>().delete(itemList[position])
                }
            }
        }
    }

    override fun getItemCount(): Int = itemList.size


}