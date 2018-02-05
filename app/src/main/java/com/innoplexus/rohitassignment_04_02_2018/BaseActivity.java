package com.innoplexus.rohitassignment_04_02_2018;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ProgressBar;

/**
 * Created by Rohit K. Pawar on 04-Feb-18.
 */

public class BaseActivity extends AppCompatActivity {
    protected ProgressDialog progressDialog;
    protected AlertDialog internetDialog;
    protected Context mContext;

    public static ProgressDialog createProgressDialog(Context mContext) {
        ProgressDialog dialog = new ProgressDialog(mContext);
        try {
            dialog.show();
        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.progress_dialog_layout);
        ProgressBar pb = (ProgressBar) dialog.findViewById(R.id.progressBar1);
        pb.getIndeterminateDrawable().setColorFilter(mContext.getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
        dialog.dismiss();
        return dialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();

        if (internetDialog != null && internetDialog.isShowing())
            internetDialog.dismiss();
    }

    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public void showProgress() {
        if (progressDialog == null)
            progressDialog = createProgressDialog(mContext);
        if (!progressDialog.isShowing())
            progressDialog.show();
    }
}
