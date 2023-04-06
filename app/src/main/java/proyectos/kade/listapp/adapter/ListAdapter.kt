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


class ListAdapter(var itemList: List<Item>) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {


    class ViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val checkBox = binding.checkbox
        val itemNameTV = binding.tvItemName
        val editIBTN = binding.ibtnEdit
        val deleteIBTN = binding.ibtnDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(itemList[position]) { val item = this //To improve readability
                //UI components
                itemNameTV.text = item.name
                checkBox.isChecked = item.checked

                //UI components listeners
                editIBTN.setOnClickListener {
                    val action = ListFragmentDirections.actionListFragmentToDetailFragment(
                        id = item.id!!,
                        name = item.name,
                        photo = item.photo,
                        description = item.description,
                        title = item.name,
                        checked = item.checked
                    )
                    binding.root.findNavController().navigate(action)
                }
                deleteIBTN.setOnClickListener { currentView ->
                    currentView.findFragment<ListFragment>().delete(item)
                }
                checkBox.setOnClickListener { currentView ->
                    item.checked = checkBox.isChecked
                    currentView.findFragment<ListFragment>().insert(item)
                }

            }
        }
    }

    override fun getItemCount(): Int = itemList.size


}