package thanatos.applearn.presenter;

import thanatos.applearn.model.MainModel;
import thanatos.applearn.model.MainModelImpl;
import thanatos.applearn.view.MainView;

/**
 * Created by lxf52 on 2016/9/28.
 */

public class MainPresenter {
    private MainModelImpl mainModel;
    private MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
        mainModel = new MainModelImpl();

    }

    public void getRes() {
        view.showProgress();
        mainModel.getDate(new MainModel.FinishListener() {
            @Override
            public void complate(String date) {
                view.success(date);
            }

            @Override
            public void error(int code) {
                view.error(code);
            }
        });
    }
}
