package thanatos.cardviewpager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Main2Activity extends AppCompatActivity {
    private App app;

    @InjectView(R.id.iv)
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (App) getApplication();

        setContentView(R.layout.activity_main2);
        ButterKnife.inject(this);
        int res = getIntent().getIntExtra("res", 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            iv.setTransitionName("shareName");
        }
        if (res != 0)
            iv.setBackgroundResource(res);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            app.exitActivity();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

        }
        return true;
    }
}
