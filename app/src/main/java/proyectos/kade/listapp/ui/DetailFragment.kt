package proyectos.kade.listapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.textfield.TextInputEditText
import proyectos.kade.listapp.databinding.FragmentDetailBinding
import proyectos.kade.listapp.model.Item
import proyectos.kade.listapp.model.data.ItemRoomDatabase
import proyectos.kade.listapp.repository.ItemRepository
import proyectos.kade.listapp.viewmodel.ListViewModel
import proyectos.kade.listapp.viewmodel.ListViewModelFactory

class DetailFragment : Fragment() {
    lateinit var viewModel: ListViewModel
    private var _binding: FragmentDetailBinding? = null
    private val binding
        get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    private lateinit var nameTIET: TextInputEditText
    private lateinit var descriptionTIET: TextInputEditText
    private lateinit var photoIV: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        nameTIET = binding.tiEtItemName
        photoIV = binding.ivPhoto
        descriptionTIET = binding.tiEtDescription
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = args.title
        photoIV.setImageResource(args.photo)
        nameTIET.setText(args.name)
        descriptionTIET.setText(args.description)
        val repository: ItemRepository =
            ItemRepository(ItemRoomDatabase.getDatabase(requireContext()))
        val factory: ListViewModelFactory = ListViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[ListViewModel::class.java]

        binding.btnSave.setOnClickListener { currentView ->
            if (nameTIET.text.toString().trim().isEmpty())
                Toast.makeText(context, "Please, enter a valid name", Toast.LENGTH_SHORT).show()
            else {
                viewModel.insert(createItem())
                back()
            }
        }
        binding.btnCancel.setOnClickListener { back() }

    }

    private fun createItem(): Item {
        val item = Item(
            name = nameTIET.text.toString().trim(),
            description = descriptionTIET.text.toString(),
            photo = args.photo,
            checked = args.checked
        )
        if (args.id != -1) //args.id only will be -1 for new items so the id will autogenerate -> it's the same as passing a null
            item.id = args.id   //If args.id it's not -1 means you are editing and existing item
        return item
    }


    private fun back() {
        val navController = findNavController()
        navController.popBackStack()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}