package com.paulovic.labs.movee.view;

import com.paulovic.labs.movee.R;
import com.paulovic.labs.movee.activity.MoviesActivity;
import com.paulovic.labs.movee.model.MovieData;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MovieItemView extends RelativeLayout {
    private ImageButton mButtonGood;
    private ImageButton mButtonBad;
    private ImageButton mButtonFavorite;
    private ImageButton mButtonRemove;

    private ImageView mScenePicture;
    private TextView mTitleView;
    private TextView mSubtitleView;
    private TextView mCommentView;
    private TextView mLoadingView;

    private MovieData mData;
    private Handler mHandler;
    
    private OnClickListener mButtonRemoveOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            Message msg = mHandler.obtainMessage(MoviesActivity.EVENT_DELETE_ITEM);
            msg.arg1 = mData.getId();
            mHandler.dispatchMessage(msg);
        }
    };

    public MovieItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void bindView(MovieData data, Handler handler) {

        mTitleView.setText(data.getTitle());
        mSubtitleView.setText(data.getSubtitle());
        mCommentView.setText(data.getComment());

        mLoadingView.setVisibility(VISIBLE);
        mScenePicture.setImageDrawable(null);

        mHandler = handler;
        mData = data;

        new LoadImageTask().execute(data.getDrawableResScene());
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
        mLoadingView = (TextView) findViewById(R.id.textLoading);

        mScenePicture = (ImageView) findViewById(R.id.imageScene);

        mButtonRemove.setOnClickListener(mButtonRemoveOnClickListener);

        mButtonGood.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), getResources().getString(R.string.like_button_toast), Toast.LENGTH_SHORT).show();
                
            }
        });

        mButtonBad.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), getResources().getString(R.string.dislike_button_toast), Toast.LENGTH_SHORT).show();
                
            }
        });

        mButtonFavorite.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), getResources().getString(R.string.love_button_toast), Toast.LENGTH_SHORT).show();
                
            }
        });
    }

    private class LoadImageTask extends AsyncTask<Integer, Object, Drawable> {

        @Override
        protected Drawable doInBackground(Integer... params) {
            int resId = params[0];
            Drawable image = getContext().getResources().getDrawable(resId);

            // Fake loading item

            return image;
        }

        @Override
        protected void onPostExecute(Drawable image) {
            mLoadingView.setVisibility(GONE);
            mScenePicture.setImageDrawable(image);
            Animation myFadeInAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fadein);
            mScenePicture.startAnimation(myFadeInAnimation); 
        }
    }
}
