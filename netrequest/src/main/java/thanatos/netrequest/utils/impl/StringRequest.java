package thanatos.netrequest.utils.impl;

import java.util.Map;

import thanatos.netrequest.utils.controller.AllRequest;

/**
 * Created by lxf52 on 2016/10/9.
 */

public class StringRequest extends AllRequest {
    public StringRequest(int methed, String url , Map<String ,String> m,String body) {
        Methed = methed;
        Url = url;
        map =m;
        mBody=body;
    }


}
