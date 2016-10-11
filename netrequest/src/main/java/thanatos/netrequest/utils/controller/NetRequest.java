package thanatos.netrequest.utils.controller;

/**
 * Created by lxf52 on 2016/10/9.
 */

public interface NetRequest {



    void addRequest(AllRequest allRequest);

     interface Listener{
        void success(String res);
        void error(int code);
    }
}
