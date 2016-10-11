package thanatos.cardviewpager;

import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * Created by lxf52 on 2016/10/8.
 */

public class ItemFragment2 extends Fragment {
    @InjectView(R.id.rl)
    RelativeLayout rl;
    @InjectView(R.id.iv)
    ImageView iv;
    @InjectView(R.id.bt)
    Button bt;
    private boolean isClick = true;

    public static ItemFragment2 getInstance(int resId) {
        Bundle bundle = new Bundle();
        bundle.putInt("res", resId);
        ItemFragment2 fragment = new ItemFragment2();
        fragment.setArguments(bundle);
        return fragment;
    }

    private int resId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment2, null);
        ButterKnife.inject(this, view);
        Bundle arguments = getArguments();
        int res = arguments.getInt("res");
        resId = res;
        iv.setBackgroundResource(res);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.rl, R.id.iv, R.id.bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt:
                Intent intent = new Intent(getActivity(), Main2Activity.class);
                intent.putExtra("res", resId);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), iv, "shareName");
                    startActivity(intent, options.toBundle());
                }
                break;
            case R.id.rl:
                break;
            case R.id.iv:
                Log.w("thanatos", "onClick: " );
                if (isClick) {
                    ObjectAnimator.ofFloat(iv, "translationY", 0F, -140F).setDuration(500).start();
                    ObjectAnimator.ofFloat(rl, "translationY", 0F, 100F).setDuration(500).start();
                    handler.sendEmptyMessageDelayed(0x0,2000);
                    isClick = false;
                } else {
                    ObjectAnimator.ofFloat(iv, "translationY", 0F, 0F).setDuration(500).start();
                    ObjectAnimator.ofFloat(rl, "translationY", 0F, 0F).setDuration(500).start();
                    isClick = true;
                }

//                animation.setAnimationListener(new Animation.AnimationListener() {
//                    @Override
//                    public void onAnimationStart(Animation animation) {
////                        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.1f, 0.0f, 1.1f,
////                                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
////                        scaleAnimation.setDuration(500);
////                        scaleAnimation.setFillAfter(true);
////                        scaleAnimation.setRepeatCount(1);
////                        rl.startAnimation(scaleAnimation);
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animation animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animation animation) {
//
//                    }
//                });

                break;
        }
    }


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (isClick){

            }else {
                ObjectAnimator.ofFloat(iv, "translationY", 0F, 0F).setDuration(500).start();
                ObjectAnimator.ofFloat(rl, "translationY", 0F, 0F).setDuration(500).start();
               isClick=true;
            }
        }
    };
}
