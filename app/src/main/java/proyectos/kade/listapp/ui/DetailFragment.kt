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
import proyectos.kade.listapp.R
import proyectos.kade.listapp.databinding.FragmentDetailBinding
import proyectos.kade.listapp.model.Item
import proyectos.kade.listapp.model.data.ItemRoomDatabase
import proyectos.kade.listapp.repository.ItemRepository
import proyectos.kade.listapp.viewmodel.ListViewModel
import proyectos.kade.listapp.viewmodel.ListViewModelFactory
import java.security.SecureRandom

class DetailFragment : Fragment() {
    lateinit var viewModel: ListViewModel
    private var _binding: FragmentDetailBinding? = null
    private val binding
        get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    private lateinit var nameTIET: TextInputEditText
    private lateinit var descriptionTIET: TextInputEditText
    private lateinit var photoIV: ImageView
    private var currentImage: Int = R.drawable.cake

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

        binding.btnSave.setOnClickListener {
            if (nameTIET.text.toString().trim().isEmpty())
                Toast.makeText(context, "Please, enter a valid name", Toast.LENGTH_SHORT).show()
            else {
                viewModel.insert(createItem())
                back()
            }
        }
        binding.btnCancel.setOnClickListener { back() }

        photoIV.setOnClickListener {
            currentImage = randomImage()
            photoIV.setImageResource(currentImage)
        }

    }

    private fun createItem(): Item {

        val item = Item(
            name = nameTIET.text.toString().trim(),
            description = descriptionTIET.text.toString().trim(),
            photo = currentImage,
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

    private fun randomImage(): Int =
        when(SecureRandom().nextInt(11)+1) {
            1 -> R.drawable.cake
            2 -> R.drawable.candy
            3 -> R.drawable.carrot
            4 -> R.drawable.corn
            5 -> R.drawable.lettuce
            6 -> R.drawable.mushroom
            7 -> R.drawable.pear
            8 -> R.drawable.steak
            9 -> R.drawable.tea
            10 -> R.drawable.tomatoe
            11 -> R.drawable.yogurt
            12 -> R.drawable.too_long
            else -> R.drawable.ic_launcher_foreground
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}