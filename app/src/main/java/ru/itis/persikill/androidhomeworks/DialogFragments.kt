package ru.itis.persikill.androidhomeworks

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.itis.persikill.androidhomeworks.fragment.Fragment2

class DialogFragments : DialogFragment() {

    fun Fragment.showDialog(
        title: String = "",
        message: String = "",
        positiveText: String = "",
        positiveAction: Click? = null,
        negativeAction: Click? = null,
        neutralAction: ClickOne? = null,
    ) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveText) { dialog, _ ->
                positiveAction?.invoke()
                dialog.dismiss()
            }
            .setNegativeButton("CANCEL") { dialog, _ ->
                negativeAction?.invoke()
                dialog.dismiss()
            }
            .setNeutralButton("Add") { dialog, _ ->
                neutralAction?.invoke("")
                dialog.dismiss()
            }
            .show()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_1, null, false)
        return AlertDialog.Builder(requireContext())
            .setTitle("Title")
            .setView(view)
            .setMessage("misato")
            .setPositiveButton("Добавить значение") { dialog, _ ->
                dialog.dismiss()
            }
            .setNegativeButton("CANCEL") { dialog, _ ->
                dialog.dismiss()
            }
            .setNeutralButton("Вычесть значение") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }



    companion object {

        fun newInstance(fragmentManager: FragmentManager) {
            return DialogFragments().show(fragmentManager, "Tag")
        }
    }
}