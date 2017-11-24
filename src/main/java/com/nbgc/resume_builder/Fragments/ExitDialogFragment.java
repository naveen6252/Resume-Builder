package com.nbgc.resume_builder.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

import com.android.resume_builder.R;

public class ExitDialogFragment extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_msg);
        builder.setPositiveButton(R.string.dialog_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case DialogInterface.BUTTON_POSITIVE:
                        getActivity().finish();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:

                        break;
                }
            }
        });
        builder.setNegativeButton(R.string.dialog_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case DialogInterface.BUTTON_POSITIVE:
                        getActivity().finish();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:

                        break;
                }
            }
        });
        return builder.create();
    }
}
