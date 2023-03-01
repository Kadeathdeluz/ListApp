package proyectos.kade.listapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import proyectos.kade.listapp.R
import proyectos.kade.listapp.adapter.ListAdapter
import proyectos.kade.listapp.model.data.DataSource
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
        adapter = ListAdapter(itemList)
        recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        binding.fabAddList.setOnClickListener { addItem() }
        viewModel.itemsList.observe(viewLifecycleOwner) { list ->
            itemList = list
        }

    }

    private fun addItem() {
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(
            name = "New Item",
            photo = R.mipmap.ic_launcher_round,
            description = "Type a short description...",
            title = "Add Item"
        )
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}