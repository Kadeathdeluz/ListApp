package proyectos.kade.listapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.findFragment
import androidx.fragment.app.findFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import proyectos.kade.listapp.databinding.FragmentDetailBinding
import proyectos.kade.listapp.model.Item

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding
        get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    private lateinit var name: EditText
    private lateinit var description: EditText
    private lateinit var photo: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        photo = binding.ivPhoto
        name = binding.tiEtItemName
        description = binding.tiEtDescription
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = args.title
        photo.setImageResource(args.photo)
        name.setText(args.name)
        description.setText(args.description)
        binding.btnCancel.setOnClickListener { cancel() }
        binding.btnSave.setOnClickListener { saveItem() }

    }

    private fun cancel() {
        val navController = findNavController()
        navController.previousBackStackEntry?.savedStateHandle?.set("checked", args.checked)
        navController.popBackStack()
        //requireActivity().onBackPressed()

    }

    private fun saveItem() {
        val navController = findNavController()
        val item = Item(
            id = args.id,
            name = name.text.toString(),
            checked = args.checked,
            description = description.text.toString(),
            photo = args.photo
        )
        with(navController.previousBackStackEntry?.savedStateHandle) {
            this?.set("id", item.id)
            this?.set("name", item.name)
            this?.set("description", item.description)
            this?.set("photo", item.photo)
            this?.set("checked", item.checked)
        }
        //requireActivity().onBackPressed()
        navController.popBackStack()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}