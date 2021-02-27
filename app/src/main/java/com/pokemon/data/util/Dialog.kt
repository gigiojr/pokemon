package com.pokemon.data.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

class Dialog {
    companion object {
        /**
         * Create an [AlertDialog.Builder] whit an title and text.
         *
         * @param context Context application.
         * @param title Title of alert.
         * @param text Messagem to user.
         * @return [AlertDialog.Builder] editable before to be show to user.
         */
        fun createAlertDialog(context: Context?, title: String?, text: String?): AlertDialog.Builder {
            val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
            alertDialog.setTitle(title)
            alertDialog.setMessage(text)
            return alertDialog
        }

        /**
         * Show an alert to user with only "Ok" button to close alert.
         *
         * @param context Context application.
         * @param text Message to user.
         */
        fun showAlert(context: Context?, text: String?) {
            val alertDialog: AlertDialog.Builder = createAlertDialog(context, "", text)
            alertDialog.setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
            alertDialog.show()
        }

        /**
         * Show an alert to user with only "Ok" button to close alert.
         *
         * @param context Context application.
         * @param title Title of alert.
         * @param text Message to user.
         */
        fun showAlert(context: Context?, title: String?, text: String?) {
            val alertDialog: AlertDialog.Builder = createAlertDialog(context, title, text)
            alertDialog.setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
            alertDialog.show()
        }
    }
}