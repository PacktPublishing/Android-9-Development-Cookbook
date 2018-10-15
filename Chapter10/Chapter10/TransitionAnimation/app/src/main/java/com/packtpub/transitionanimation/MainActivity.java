package com.packtpub.transitionanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goAnimate(View view) {
        //Resource file solution
        ViewGroup root = findViewById(R.id.layout);
        Scene scene = Scene.getSceneForLayout(root, R.layout.activity_main_end, this);
        Transition transition = TransitionInflater.from(this)
                .inflateTransition(R.transition.transition_move);
        TransitionManager.go(scene, transition);

        //Code only solution
//        ViewGroup root = findViewById(R.id.layout);
//        Scene scene = new Scene(root);
//
//        Transition transition = new ChangeBounds();
//        TransitionManager.beginDelayedTransition(root,transition);
//
//        TextView textViewTop = findViewById(R.id.textViewTop);
//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)textViewTop.getLayoutParams();
//        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,1);
//        params.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
//        textViewTop.setLayoutParams(params);
//
//        TextView textViewBottom = findViewById(R.id.textViewBottom);
//        params = (RelativeLayout.LayoutParams) textViewBottom.getLayoutParams();
//        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,0);
//        params.addRule(RelativeLayout.ALIGN_PARENT_TOP, 1);
//        textViewBottom.setLayoutParams(params);
//
//        TransitionManager.go(scene);
    }
}
