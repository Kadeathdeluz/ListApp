package proyectos.kade.listapp.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import proyectos.kade.listapp.R

class SelectImageDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        activity?.let {
            val names = arrayOf<String>(
                "Cake",
                "Candy",
                "Carrot",
                "Corn",
                "Lettuce",
                "Mushroom",
                "Pear",
                "Steak",
                "Tea",
                "Tomato",
                "Yogurt",
                "Too_long"
            )
            val values = arrayOf<Int>(
                R.drawable.cake,
                R.drawable.candy,
                R.drawable.carrot,
                R.drawable.corn,
                R.drawable.lettuce,
                R.drawable.mushroom,
                R.drawable.pear,
                R.drawable.steak,
                R.drawable.tea,
                R.drawable.tomatoe,
                R.drawable.yogurt,
                R.drawable.too_long
            )
            val mutMapOfImages = mutableMapOf<String,Int>()
            for (count in names.indices) {
                mutMapOfImages.put(names[count], values[count])
            }

            val builder = AlertDialog.Builder(it)
            var selectedImage: Int
            builder.setTitle(R.string.select_image)
                .setItems(names
                ) { _, which ->
                    selectedImage = mutMapOfImages[names[which]] ?: R.drawable.ic_launcher_foreground
                    currentImage.value = selectedImage
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")

    companion object {
        const val TAG = "SelectImageDialog"
    }
}