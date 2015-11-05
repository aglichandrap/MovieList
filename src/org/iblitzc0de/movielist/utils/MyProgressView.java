package org.iblitzc0de.movielist.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.iblitzc0de.movielist.R;

public class MyProgressView extends LinearLayout {
    Button btnRetry;
    Context ctx;
    ViewGroup ivProgress;
    ProgressBar ivProgressAnim;
    TextView tvMessage;

    public MyProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.ctx = context;
        View v = LayoutInflater.from(context).inflate(R.layout.view_progress, null);
        this.ivProgress = (ViewGroup) v.findViewById(R.id.progress);
        this.ivProgressAnim = (ProgressBar) v.findViewById(R.id.progress_anim);
        this.btnRetry = (Button) v.findViewById(R.id.btn_retry);
        this.tvMessage = (TextView) v.findViewById(R.id.tv_message);
        addView(v);
    }

    public void setRetryClickListener(OnClickListener onClickListener) {
        this.btnRetry.setOnClickListener(onClickListener);
    }

    public void startProgress() {
        setVisibility(0);
        this.ivProgress.setVisibility(0);
        this.btnRetry.setVisibility(8);
        this.tvMessage.setVisibility(8);
    }

    public void stopAndGone() {
        setVisibility(8);
    }

    public void stopAndError(String errorMessage, boolean isRetry) {
        setVisibility(0);
        this.ivProgress.clearAnimation();
        this.ivProgress.setVisibility(8);
        if (isRetry) {
            this.btnRetry.setVisibility(0);
        } else {
            this.btnRetry.setVisibility(8);
        }
        this.tvMessage.setVisibility(0);
        this.tvMessage.setText(errorMessage);
    }
}
