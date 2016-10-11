package thanatos.applearn.model;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by lxf52 on 2016/9/28.
 */

public class MainModelImpl implements MainModel {

    //创建okHttpClient对象
    private OkHttpClient mOkHttpClient = new OkHttpClient();
    //创建一个Request
    private final Request request = new Request.Builder()
            .url("http://www.cnblogs.com/cr330326/p/5181283.html")
            .build();
    //new call
    private Call call = mOkHttpClient.newCall(request);

    @Override
    public void getDate(final FinishListener listener) {
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                long contentLength = request.body().contentLength();
                listener.error((int) contentLength);
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                String htmlStr = response.body().string();

                listener.complate(Thread.currentThread().getId()+"");
            }
        });

    }
}
