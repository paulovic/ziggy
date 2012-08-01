package com.paulovic.labs.movee.view;

import com.paulovic.labs.movee.R;
import com.paulovic.labs.movee.model.MovieData;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MovieItemView extends RelativeLayout {
    private ImageButton mButtonGood;
    private ImageButton mButtonBad;
    private ImageButton mButtonFavorite;
    private ImageButton mButtonRemove;

    private ImageView mScenePicture;
    private TextView mTitleView;
    private TextView mSubtitleView;
    private TextView mCommentView;

    public MovieItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void bindView(MovieData data, Handler handler) {
        mTitleView.setText(data.getTitle());
        mSubtitleView.setText(data.getSubtitle());
        mCommentView.setText(data.getComment());
        mScenePicture.setImageResource(data.getDrawableResScene());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        
        mButtonGood = (ImageButton) findViewById(R.id.buttonGood);
        mButtonBad = (ImageButton) findViewById(R.id.buttonBad);
        mButtonFavorite = (ImageButton) findViewById(R.id.buttonFavorite);
        mButtonRemove = (ImageButton) findViewById(R.id.buttonRemove);

        mTitleView = (TextView) findViewById(R.id.textTitle);
        mSubtitleView = (TextView) findViewById(R.id.textSubtitle);
        mCommentView = (TextView) findViewById(R.id.textComment);

        mScenePicture = (ImageView) findViewById(R.id.imageScene);
        
        /**
         * TODO create button click listeners
         * */
    }
}
