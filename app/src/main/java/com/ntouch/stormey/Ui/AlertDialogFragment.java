package com.ntouch.stormey.Ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

import com.ntouch.stormey.R;

/**
 * Created by mahmoud on 6/26/15.
 */
public class AlertDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.Error_Title))
                .setMessage(context.getString(R.string.errorMessageBody))
                .setPositiveButton(context.getString(R.string.errorButtonMessage), null);

        AlertDialog dialog = builder.create();
        return dialog;
    }
}
