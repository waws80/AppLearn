package thanatos.fanshe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "thanatos";
    @butterknife.InjectView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butterknife.ButterKnife.inject(this);
    }

    @butterknife.OnClick(R.id.tv)
    public void onClick() {

        Class<TextView> aClass = (Class<TextView>) new TextView(this).getClass();
        //获取包名加类名
        String name = aClass.getName();
        Log.w(TAG, "onClick: "+name);
        //获取类名
        String simpleName = aClass.getSimpleName();
        Log.w(TAG, "onClick: "+simpleName );
        //获取接口
        Class<?>[] interfaces = aClass.getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            Log.w(TAG, "onClick: "+interfaces[i].getName() );

        }
         //获取父类
        Class<? super TextView> superclass = aClass.getSuperclass();
        Log.w(TAG, "onClick: "+superclass.getSimpleName() );

    }
}
