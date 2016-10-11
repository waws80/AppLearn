package thanatos.applearn.view;

/**
 * Created by lxf52 on 2016/9/28.
 */

public interface MainView {

    void showProgress();
    void success(String date);
    void error(int code);
}
