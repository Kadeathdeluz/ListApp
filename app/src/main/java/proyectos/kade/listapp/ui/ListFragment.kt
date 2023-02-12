package proyectos.kade.listapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import proyectos.kade.listapp.adapter.ListAdapter
import proyectos.kade.listapp.data.DataSource
import proyectos.kade.listapp.databinding.ActivityMainBinding
import proyectos.kade.listapp.model.Item

class ListFragment : Fragment() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ListAdapter
    private lateinit var itemList: List<Item>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemList = DataSource().loadList()
        adapter = ListAdapter(itemList)
        binding.recyclerView.adapter = adapter
        binding.fabAddList.setOnClickListener { addItem() }
    }

    private fun addItem() {
        Toast.makeText(requireContext(), "FAB clicked", Toast.LENGTH_SHORT ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}