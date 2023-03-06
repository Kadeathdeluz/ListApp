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
        itemList = viewModel.loadList()
        viewModel.updateList(itemList)
        adapter = ListAdapter(itemList)
        recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        val navController = findNavController()
        val liveName =
            navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("name")
        liveName?.observe(viewLifecycleOwner) { newName ->
            val newItem = Item(
                id = navController.currentBackStackEntry?.savedStateHandle?.get("id") ?: 0, //Change id to autoincremental
                name = newName,
                description = navController.currentBackStackEntry?.savedStateHandle?.get("description"),
                checked = navController.currentBackStackEntry?.savedStateHandle?.get("checked") ?: true,
                photo = navController.currentBackStackEntry?.savedStateHandle?.get("photo")
            )
            viewModel.addItem(newItem)
        }

        binding.fabAddList.setOnClickListener {
            var id = ++viewModel.id
            addItem(
                Item(
                    id = id,
                    checked = randomCheck(),
                    name = randomName(),
                    description = "Generated item with id: ${id}.",
                    photo = randomPhoto()
                )
            )
        }
        viewModel.itemsList.observe(viewLifecycleOwner) { list ->
            itemList = list
            binding.recyclerView.adapter = ListAdapter(itemList)
        }

    }

    private fun randomName(): String {
        return when ((1..3).random()) {
            1 -> "Coco"
            2 -> "Cherry"
            3 -> "Avocado"
            else -> "None"
        }
    }

    private fun randomPhoto(): Int {
        return when ((1..3).random()) {
            1 -> R.drawable.cake
            2 -> R.drawable.candy
            3 -> R.drawable.corn
            else -> R.drawable.ic_launcher_foreground
        }
    }

    private fun randomCheck() : Boolean {
        return (0..1).random() == 1
    }

    private fun addItem(item: Item) {
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(
            id = item.id,
            name = item.name,
            photo = item.photo ?: R.drawable.ic_launcher_foreground,
            checked = item.checked,
            description = item.description ?: "Type a short description...",
            title = "Add Item"
        )
        findNavController().navigate(action)
    }
    fun delete(item: Item) {
        viewModel.delete(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}