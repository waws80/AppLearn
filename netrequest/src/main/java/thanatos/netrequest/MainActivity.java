package thanatos.netrequest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import thanatos.netrequest.utils.NetRequestConstant;
import thanatos.netrequest.utils.controller.NetRequest;
import thanatos.netrequest.utils.impl.NetRequestImpl;
import thanatos.netrequest.utils.impl.StringRequest;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "thanatos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetRequestImpl netRequest = new NetRequestImpl(this);
        StringRequest request = new StringRequest(NetRequestConstant.Methed.GET, "http://192.168.1.102:8080",null,"");
        netRequest.addRequest(request);
        netRequest.listener=new NetRequest.Listener() {
            @Override
            public void success(String res) {
                Log.w(TAG, "success: -----------------"+res );
            }
            @Override
            public void error(int code) {

            }
        };
    }
}
