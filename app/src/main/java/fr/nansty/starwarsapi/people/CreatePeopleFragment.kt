package fr.nansty.starwarsapi.people

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import fr.nansty.starwarsapi.R


class CreatePeopleFragment : DialogFragment() {

    interface CreatePeopleListener{
        fun onDialogPositiveClick(peopleName : String)
        fun onDialogNegatibeClick()
    }

    var listener: CreatePeopleListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)

        val input = EditText(context)
        with(input){
            inputType = InputType.TYPE_CLASS_TEXT
            hint = context.getString(R.string.createPeople_peopleHint)
        }

        builder.setTitle(getString(R.string.createPeople_title))
            .setView(input)
            .setPositiveButton(getString(R.string.createPeople_positive),
            DialogInterface.OnClickListener { _, _ ->
                listener?.onDialogPositiveClick(input.text.toString())
            })
            .setNegativeButton(getString(R.string.createPeople_negative),
                DialogInterface.OnClickListener{_,_ ->
                    dialog!!.cancel()
                    listener?.onDialogNegatibeClick()
                })

        return builder.create()
    }


}