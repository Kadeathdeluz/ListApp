package proyectos.kade.listapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import proyectos.kade.listapp.databinding.FragmentDetailBinding
import proyectos.kade.listapp.viewmodel.ItemDetailViewModel


class DetailFragment : Fragment() {
    private val viewModel: ItemDetailViewModel by viewModels()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    private lateinit var name: TextView
    private lateinit var description: TextView
    private lateinit var photo: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        photo = binding.ivPhoto
        name = binding.tvItemName
        description = binding.tvDescription
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = args.title
        photo.setImageResource(args.photo)
        name.text = args.name
        description.text = args.description
        binding.btnCancel.setOnClickListener { cancel() }
        binding.btnSave.setOnClickListener { saveItem() }

    }

    private fun cancel() {
        Toast.makeText(context, "Back...", Toast.LENGTH_SHORT).show()
        requireActivity().onBackPressed()

    }

    private fun saveItem() {
        Toast.makeText(context, "Item saved successfully", Toast.LENGTH_SHORT).show()
        requireActivity().onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}