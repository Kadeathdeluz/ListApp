package proyectos.kade.listapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import proyectos.kade.listapp.R
import proyectos.kade.listapp.adapter.ListAdapter
import proyectos.kade.listapp.databinding.ListViewBinding
import proyectos.kade.listapp.model.Item
import proyectos.kade.listapp.model.data.ItemRoomDatabase
import proyectos.kade.listapp.repository.ItemRepository
import proyectos.kade.listapp.viewmodel.ListViewModel
import proyectos.kade.listapp.viewmodel.ListViewModelFactory

class ListFragment : Fragment() {

    lateinit var viewModel: ListViewModel

    private var _binding: ListViewBinding? = null
    private val binding get() = _binding!!

    private lateinit var listAdapter: ListAdapter
    private lateinit var itemList: List<Item>

    private lateinit var recyclerView: RecyclerView
    private lateinit var addFAB: FloatingActionButton

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
        recyclerView = binding.recyclerView
        addFAB = binding.fabAddList
        itemList = listOf<Item>()
        listAdapter = ListAdapter(itemList)

        with(recyclerView) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        val repository: ItemRepository =
            ItemRepository(ItemRoomDatabase.getDatabase(requireContext()))
        val factory: ListViewModelFactory = ListViewModelFactory(repository)

        viewModel = ViewModelProvider(this,factory)[ListViewModel::class.java]

        viewModel.getAllItems().observe(viewLifecycleOwner) { list ->
            listAdapter.itemList = list
            listAdapter.notifyDataSetChanged()
        }

        addFAB.setOnClickListener {
            addItem(
                Item(
                    checked = false,
                    name = "Name",
                    description = "Generated item ",
                    photo = R.drawable.cake
                )
            )
        }

    }

    private fun addItem(item: Item) {
        with(item) {
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(
                name = name,
                photo = photo,
                checked = checked,
                description = description,
                title = "Add Item"
            )
            findNavController().navigate(action)
        }
    }

    fun insert(item: Item) =
        viewModel.insert(item)

    fun delete(item: Item) =
        viewModel.delete(item)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}