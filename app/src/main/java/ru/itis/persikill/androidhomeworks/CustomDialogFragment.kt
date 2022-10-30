package ru.itis.persikill.androidhomeworks

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import ru.itis.persikill.androidhomeworks.databinding.FragmentCustomDialogBinding

class CustomDialogFragment(
    val onAddButtonClicked: (position: Int, MyLogic.Item) -> Unit
) :
    DialogFragment() {

    private var _binding: FragmentCustomDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCustomDialogBinding.inflate(layoutInflater)
        return (binding.root)
    }

    override fun onStart() {
        super.onStart()

        val metrics: DisplayMetrics = resources.displayMetrics
        val width: Int = metrics.widthPixels
        dialog?.window?.setLayout(
            width,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initValues()
    }

    private fun initValues() {
        with(binding) {
            btnSubmit.setOnClickListener {
                val description = etDescription.text.toString()
                val title = etTitle.text.toString()
                val position = if (etTitle.text.toString().isNotBlank())
                    Integer.valueOf(etPosition.text.toString()) else Repository.dataList.size
                onAddButtonClicked(position, MyLogic.Item(description, title))
                dialog?.hide()
            }
        }
    }
}
