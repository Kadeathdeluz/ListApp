package proyectos.kade.listapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import proyectos.kade.listapp.adapter.ListAdapter
import proyectos.kade.listapp.data.DataSource
import proyectos.kade.listapp.databinding.ListViewBinding
import proyectos.kade.listapp.model.Item

class ListFragment : Fragment() {

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
        itemList = DataSource().loadList()
        adapter = ListAdapter(itemList)
        recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        binding.fabAddList.setOnClickListener { addItem() }

    }

    private fun addItem() {
        Toast.makeText(requireContext(), itemList[3].toString(), Toast.LENGTH_SHORT ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}