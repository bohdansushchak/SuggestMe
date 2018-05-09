package bohdan.hushcha.sushchak.suggestme.fragments;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import bohdan.hushcha.sushchak.suggestme.R;

public class BaseFragment extends Fragment {

    /**
     * Method to view dialog with title and dialog message
     *
     * @param StringResourceTitle resource id title dialog
     * @param StringResourceMessage resource id message in dialog
     */
    protected void DialogAlert(int StringResourceTitle, int StringResourceMessage) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(StringResourceTitle);
        dialog.setMessage(StringResourceMessage);
        dialog.setNegativeButton(R.string.dialog_btn_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * Method to view dialog with title and message
     *
     * @param Title string title dialog
     * @param Message string message in dialog
     */
    protected void DialogAlert(String Title, String Message) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(Title);
        dialog.setMessage(Message);
        dialog.setNegativeButton(R.string.dialog_btn_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialog.show();
    }
}
