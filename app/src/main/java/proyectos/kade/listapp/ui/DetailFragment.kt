package proyectos.kade.listapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import proyectos.kade.listapp.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: ListFragmentArgs by navArgs()

    private lateinit var photo: ImageView
    private lateinit var name: TextView
    private lateinit var description: TextView

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

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}