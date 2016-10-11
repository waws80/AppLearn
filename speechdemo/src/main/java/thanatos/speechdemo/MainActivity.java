package thanatos.speechdemo;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    private static final int SPEECH_RESULT = 1;
    private static final String TAG = "thanatos";
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.speechText)
    TextView speechText;
    @InjectView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);

    }

    @butterknife.OnClick({R.id.speechText, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.speechText:
                break;
            case R.id.fab:
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                try {
                    startRecognizerIntent();
                }catch (ActivityNotFoundException a){
                    Toast.makeText(this, "你的设备没有speech服务", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    private void startRecognizerIntent(){
        Intent intent=new Intent();
        intent.setAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,"en-US");
        startActivityForResult(intent,SPEECH_RESULT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==SPEECH_RESULT){
            if (resultCode==RESULT_OK&&data!=null){
                ArrayList<String> arrayList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                for (int i = 0; i <arrayList.size() ; i++) {
                    Log.w(TAG, "onActivityResult: "+arrayList.get(i) );
                }


            }
        }
    }
}
