package thanatos.applearn;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import thanatos.applearn.presenter.MainPresenter;
import thanatos.applearn.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final int REQUEST_CODE_INTENT = 0;
    private static final String TAG = "thanatos";
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(TAG, "onClick: " );
                MainPresenter presenter = new MainPresenter(MainActivity.this);
                presenter.getRes();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.INTERNET}, REQUEST_CODE_INTENT);
                        Log.w(TAG, "onClick: ----------------111111111111");
                    }
                } else {
//                    MainPresenter presenter = new MainPresenter(MainActivity.this);
//                    presenter.getRes();
                    Log.w(TAG, "onClick: ----------------");
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_INTENT) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                MainPresenter presenter = new MainPresenter(MainActivity.this);
                presenter.getRes();
            } else {
                Toast.makeText(this, "您拒绝了权限", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void showProgress() {
        dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public void success(String date) {
        dialog.dismiss();
        Log.w(TAG, "success: "+date );
    }

    @Override
    public void error(int code) {
        dialog.dismiss();
        Log.w(TAG, "error: "+code );
    }
}
