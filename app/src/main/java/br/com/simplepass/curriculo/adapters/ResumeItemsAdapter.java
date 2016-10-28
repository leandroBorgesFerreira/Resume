package br.com.simplepass.curriculo.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.simplepass.curriculo.R;
import br.com.simplepass.curriculo.domain.ResumeItem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by leandro on 6/1/16.
 */
public class ResumeItemsAdapter extends RecyclerView.Adapter<ResumeItemsAdapter.ViewHolder> {
    private Activity mActivity;
    private List<ResumeItem> mResumeItemList;
    private int lastPosition = -1;

    public ResumeItemsAdapter(Activity activity, List<ResumeItem> resumeItemList) {
        mActivity = activity;
        mResumeItemList = resumeItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View pathView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_resume_ability, parent, false);

        return new ViewHolder(pathView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ResumeItem resumeItem = mResumeItemList.get(position);

        holder.abilityName.setText(resumeItem.getName());
        holder.abilityComment.setText(resumeItem.getDescription());
        holder.abilityImage.setImageDrawable(resumeItem.getDrawable());

        setAnimation(holder.containerView, position);

        holder.containerView.setOnClickListener(v -> {
            if(resumeItem.getIntent() != null) {
                Intent intent = resumeItem.getIntent();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(
                            mActivity,
                            new Pair<>(holder.abilityImage, mActivity.getString(R.string.transition_ability)));

                    mActivity.startActivity(intent, activityOptions.toBundle());
                } else {
                    mActivity.startActivity(intent);
                }
            }
        });
    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mActivity, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return mResumeItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.container_view) View containerView;
        @BindView(R.id.ability_name) TextView abilityName;
        @BindView(R.id.ability_comment) TextView abilityComment;
        @BindView(R.id.ability_image) ImageView abilityImage;

        public ViewHolder(View view) {
            super(view);

            try {
                ButterKnife.bind(this, view);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
