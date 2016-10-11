package thanatos.netrequest.utils.impl;

import android.content.Context;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import thanatos.netrequest.utils.IsNetWork;
import thanatos.netrequest.utils.NetRequestConstant;
import thanatos.netrequest.utils.controller.AllRequest;
import thanatos.netrequest.utils.controller.NetRequest;


/**
 * Created by lxf52 on 2016/10/9.
 */

public class NetRequestImpl implements NetRequest {
    private List<AllRequest> allRequestList = new ArrayList<>();
    public Listener listener;
    private Context context;
    private byte[] body;

    public NetRequestImpl(Context context) {
        this.context = context;
    }

    @Override
    public void addRequest(AllRequest allRequest) {
         allRequestList.add(allRequest);
        for (final AllRequest allRequest1:allRequestList) {
            new Thread(){
                @Override
                public void run() {
                    parseRequest(allRequest1);
                }
            }.start();

        }
    }

    public void parseRequest(AllRequest allRequests){
        if (listener!=null){
            if (allRequests.Url.isEmpty()){
                listener.error(NetRequestConstant.Error.No_Url);
                return;
            }
            if (IsNetWork.isNetworkAvailable(context)){
                listener.error(NetRequestConstant.Error.NetWork_Error);
                return;
            }
        }
        try {
            URL u=new URL(allRequests.Url);
            try {
                HttpURLConnection httpURLConnection= (HttpURLConnection)u.openConnection();
                if (allRequests.Methed== NetRequestConstant.Methed.GET){
                    setGet(httpURLConnection,allRequests);
                }
                if (allRequests.Methed== NetRequestConstant.Methed.POST){
                    setPost(httpURLConnection,allRequests);
                }

            } catch (IOException e) {
                listener.error(NetRequestConstant.Error.GetDate_Error);
            }
        } catch (MalformedURLException e) {
            listener.error(NetRequestConstant.Error.Url_Error);
        }
    }

    private void setGet(HttpURLConnection httpURLConnection,AllRequest allRequests) throws IOException {
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);
        httpURLConnection.connect();
        //给请求设置头部
        if (!allRequests.map.isEmpty()){
            for (Map.Entry<String, String> entry : allRequests.map.entrySet()) {
                System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                httpURLConnection.addRequestProperty(entry.getKey(),entry.getValue());
            }
        }else {

        }
        httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");
        httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
        if (httpURLConnection.getConnectTimeout()>5000){
            listener.error(NetRequestConstant.Error.TimeOut_Error);
            return;
        }
        if (httpURLConnection.getReadTimeout()>5000){
            listener.error(NetRequestConstant.Error.GetDate_Error);
            return;
        }
        if (httpURLConnection.getResponseCode()==400){
            listener.error(NetRequestConstant.Error.Url_Error);
        }
        if (httpURLConnection.getResponseCode()==401){
            listener.error(NetRequestConstant.Error.NoPermission);
        }
        if (httpURLConnection.getResponseCode()==404){
            listener.error(NetRequestConstant.Error.Url_Error);
        }
        if (httpURLConnection.getResponseCode()==200){
            Log.w("thanatos", "parseRequest: -----"+httpURLConnection.getResponseCode() );
            InputStream inputStream = httpURLConnection.getInputStream();
            String textForService = getTextForService(inputStream);
            Log.w("thanatos", "parseRequest: "+textForService );
            if (listener!=null){
                listener.success(textForService);
            }

        }
    }

    private void setPost(HttpURLConnection httpURLConnection,AllRequest allRequests) throws IOException {
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);
        httpURLConnection.connect();
        //给请求设置头部
        if (!allRequests.map.isEmpty()){
            for (Map.Entry<String, String> entry : allRequests.map.entrySet()) {
                System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                httpURLConnection.addRequestProperty(entry.getKey(),entry.getValue());
            }
        }
        //给请求头设置body
        httpURLConnection.setRequestProperty("Content-Type", "application/x-javascript; charset=UTF-8");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(allRequests.mBody.getBytes().length));
        httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");
        httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
        if (httpURLConnection.getConnectTimeout()>5000){
            listener.error(NetRequestConstant.Error.TimeOut_Error);
            return;
        }
        if (httpURLConnection.getReadTimeout()>5000){
            listener.error(NetRequestConstant.Error.GetDate_Error);
            return;
        }
        if (httpURLConnection.getResponseCode()==400){
            listener.error(NetRequestConstant.Error.Url_Error);
        }
        if (httpURLConnection.getResponseCode()==401){
            listener.error(NetRequestConstant.Error.NoPermission);
        }
        if (httpURLConnection.getResponseCode()==404){
            listener.error(NetRequestConstant.Error.Url_Error);
        }
        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(allRequests.mBody.getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
        if (httpURLConnection.getResponseCode()==200){
            Log.w("thanatos", "parseRequest: -----"+httpURLConnection.getResponseCode() );

            if (listener!=null){
                listener.success("发送成功");
            }

        }
    }


    public static String getTextForService(InputStream is) {
        byte[] b = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while ((len = is.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            String text = new String(bos.toByteArray());
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
