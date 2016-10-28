package br.com.simplepass.curriculo.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.simplepass.curriculo.R;

/**
 * Created by leandro on 5/31/16.
 */
public class CircularProgressButton2 extends Button {
    private enum State {
        PROGRESS, IDLE, COMPLETE, ERROR
    }

    //private CircularAnimatedDrawable mAnimatedDrawable;
    Drawable mDrawable;
    private boolean mMorphingInProgress;
    private State mState;
    private CircularAnimatedDrawable mAnimatedDrawable;

    private int mPaddingProgress; //ToDo: Colocar em attr depois!


    public CircularProgressButton2(Context context) {
        super(context);
        init();
    }

    public CircularProgressButton2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircularProgressButton2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CircularProgressButton2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        mPaddingProgress = 0;

        mDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.button_shape, null);

        setBackground(mDrawable);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        if (mState == State.PROGRESS && !mMorphingInProgress) {
            drawIndeterminateProgress(canvas);
        }
    }

    private void drawIndeterminateProgress(Canvas canvas) {
        if (mAnimatedDrawable == null) {
            int offset = (getWidth() - getHeight()) / 2;
            mAnimatedDrawable = new CircularAnimatedDrawable(this,
                    getResources().getDimension(R.dimen.stroke_login_button_loading),
                    ContextCompat.getColor(getContext(), android.R.color.white));

            int left = offset + mPaddingProgress;
            int right = getWidth() - offset - mPaddingProgress;
            int bottom = getHeight() - mPaddingProgress;
            int top = mPaddingProgress;

            mAnimatedDrawable.setBounds(left, top, right, bottom);
            mAnimatedDrawable.setCallback(this);
            mAnimatedDrawable.start();

        } else {
            mAnimatedDrawable.draw(canvas);
        }
    }

    public void stopAnimation(){
        mAnimatedDrawable.stop();
    }

    public void startProgress(){
        this.setText(null);
        setClickable(false);

        int fromWidth = getWidth();
        int fromHeight = getHeight();

        int toHeight =  (int) (fromHeight * 1.2);
        int toWidth = toHeight; //Largura igual altura faz um circulo perfeito

        ValueAnimator widthAnimation = ValueAnimator.ofInt(fromWidth, toWidth);
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.width = val;
                setLayoutParams(layoutParams);
            }
        });

        ValueAnimator heightAnimation = ValueAnimator.ofInt(fromHeight, toHeight);
        heightAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.height = val;
                setLayoutParams(layoutParams);
            }
        });

        /*ValueAnimator strokeAnimation = ValueAnimator.ofFloat(
                getResources().getDimension(R.dimen.stroke_login_button),
                getResources().getDimension(R.dimen.stroke_login_button_loading));

        strokeAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                ((ShapeDrawable)mDrawable).getPaint().setStrokeWidth((Float)animation.getAnimatedValue());
            }
        });*/

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(400);
        animatorSet.playTogether(widthAnimation, heightAnimation);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mMorphingInProgress = false;
                mState = State.PROGRESS;
            }
        });

        animatorSet.start();
    }


}
