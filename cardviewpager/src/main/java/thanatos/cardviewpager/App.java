package thanatos.cardviewpager;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxf52 on 2016/10/8.
 */

public class App extends Application {
    private List<Activity> activities = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
    }


    public void addActivity(Activity activity) {
        activities.add(activity);
    }


    public void exitActivity() {
        for (int i = 0; i < activities.size(); i++) {
            activities.get(i).finish();
        }
    }

}
