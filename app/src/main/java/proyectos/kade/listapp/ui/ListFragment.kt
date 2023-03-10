package proyectos.kade.listapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import proyectos.kade.listapp.R
import proyectos.kade.listapp.adapter.ListAdapter
import proyectos.kade.listapp.databinding.ListViewBinding
import proyectos.kade.listapp.model.Item
import proyectos.kade.listapp.viewmodel.ListViewModel

class ListFragment : Fragment() {
    private val viewModel: ListViewModel by viewModels()

    private var _binding: ListViewBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ListAdapter
    private lateinit var itemList: List<Item>

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ListViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemList = viewModel.loadList() //First time just get an empty list
        viewModel.updateList(itemList)
        adapter = ListAdapter(itemList)

        recyclerView = binding.recyclerView

        with(recyclerView) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        val navController = findNavController()

        with(navController.currentBackStackEntry?.savedStateHandle) {
            this?.getLiveData<Int>("id")
                ?.observe(viewLifecycleOwner) { newID ->
                    val newItem = Item(
                        id = newID ?: 0,
                        name = this["name"] ?: "noName",
                        description = this["description"] ?: "noDescription",
                        checked = this["checked"] ?: true,
                        photo = this["photo"] ?: R.drawable.ic_launcher_foreground
                    )
                    //navController.clearBackStack("name")
                    viewModel.addItem(newItem)
                }
        }

        binding.fabAddList.setOnClickListener {
            val newId = viewModel.newId()
            addItem(
                Item(
                    id = newId,
                    checked = false,
                    name = "Name$newId",
                    description = "Generated item with id: $newId",
                    photo = R.drawable.cake
                )
            )
        }

        viewModel.itemsList.observe(viewLifecycleOwner) { list ->
            itemList = list
            binding.recyclerView.adapter = ListAdapter(itemList)
        }

    }

    private fun addItem(item: Item) {
        with(item) {
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(
                id = id,
                name = name,
                photo = photo,
                checked = checked,
                description = description,
                title = "Add Item"
            )
            findNavController().navigate(action)
        }
    }

    fun delete(item: Item) =
        viewModel.delete(item)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}