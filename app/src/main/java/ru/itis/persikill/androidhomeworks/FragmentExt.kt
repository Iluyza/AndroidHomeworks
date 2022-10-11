package ru.itis.persikill.androidhomeworks
import android.app.AlertDialog
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

typealias Click = () -> Unit
typealias ClickOne = (String) -> Unit

fun Fragment.showDialog(
    title: String = "",
    message: String = "",
    positiveText: String = "",
    positiveAction: Click? = null,
    negativeAction: Click? = null,
    neutralAction: ClickOne? = null,
) {

    val input = EditText(context)
    AlertDialog.Builder(requireContext())
        .setTitle(title)
        .setMessage(message)
        .setView(input)
        .setPositiveButton("Прибавить значение") { dialog, _ ->
            positiveAction?.invoke()
            dialog.dismiss()
        }
        .setNegativeButton("Выйти") { dialog, _ ->
            negativeAction?.invoke()
            dialog.dismiss()
        }
        .setNeutralButton("Вычесть значение") { dialog, _ ->
            neutralAction?.invoke("")
            dialog.dismiss()
        }
        .show()
}