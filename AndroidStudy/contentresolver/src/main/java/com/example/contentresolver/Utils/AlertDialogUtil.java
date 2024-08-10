// Translated from Kotlin to Java for Android Framework
package com.example.contentresolver.Utils;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;

import com.example.contentresolver.R;

public class AlertDialogUtil {

    public static void showDialog(
            Context context,
            String title,
            String hint1,
            String hint2,
            DialogClickCallback clickCallback) {

        // Default values if null
        if (title == null) {
            title = "请输入信息";
        }
        if (hint1 == null) {
            hint1 = "输入1";
        }
        if (hint2 == null) {
            hint2 = "输入2";
        }

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        // Inflate the dialog layout
        View dialogContentView = layoutInflater.inflate(R.layout.dialog_edit_layout, null);

        EditText editText1 = dialogContentView.findViewById(R.id.et_dialog_input1);
        EditText editText2 = dialogContentView.findViewById(R.id.et_dialog_input2);
        editText1.setHint(hint1);
        editText2.setHint(hint2);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context)
                .setView(dialogContentView)
                .setTitle(title)
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (clickCallback != null) {
                            clickCallback.onClick(dialog, DialogInterface.BUTTON_NEGATIVE, null, null);
                        }
                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (clickCallback != null) {
                            clickCallback.onClick(dialog, DialogInterface.BUTTON_POSITIVE,
                                    editText1.getText().toString(),
                                    editText2.getText().toString());
                        }
                    }
                });

        alertDialogBuilder.create().show();
    }

    public interface DialogClickCallback {
        void onClick(DialogInterface dialog, int which, String input1, String input2);
    }
}