package thanatos.applearn.model;

/**
 * Created by lxf52 on 2016/9/28.
 */

public interface MainModel {
    void getDate(FinishListener listener);
    interface  FinishListener{
        void complate(String date);
        void error(int code);
    }
}
