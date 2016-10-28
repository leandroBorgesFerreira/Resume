package br.com.simplepass.curriculo.adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.simplepass.curriculo.R;
import br.com.simplepass.curriculo.domain.Project;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by leandro on 6/3/16.
 */
public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ViewHolder>{
    private List<Project> projectList;
    private List<Project> expandedItems;
    private Context mContext;

    private static final double EXPAND_FACTOR = 3.5;

    public ProjectsAdapter(Context context, List<Project> projectList) {
        this.projectList = projectList;
        mContext = context;
        expandedItems = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View pathView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_project, parent, false);

        return new ViewHolder(pathView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Project project = projectList.get(position);

        holder.projectName.setText(project.getName());
        holder.projectDescription.setText(project.getDescription());
        holder.projectImage.setImageDrawable(project.getDrawable());

        holder.btnStore.setOnClickListener(v1 -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, project.getStoreUri());
            mContext.startActivity(intent);
        });

        holder.expandButton.setOnClickListener(v -> {
            holder.expandButton.setClickable(false);
            int fromHeight = holder.containerView.getHeight();
            int toHeight;

            if(!expandedItems.contains(project)){
                //remover descrição
                holder.expandButton.setText("-");
                expandedItems.add(project);
                toHeight = (int)(fromHeight * EXPAND_FACTOR);
            } else{
                expandedItems.remove(project);
                holder.expandButton.setText("+");
                toHeight = (int)(fromHeight / EXPAND_FACTOR);
            }


            final ValueAnimator valueAnimator = ValueAnimator.ofInt(fromHeight, toHeight);
            valueAnimator.setDuration(600);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    ViewGroup.LayoutParams layoutParams = holder.containerView.getLayoutParams();
                    layoutParams.height =  (int)valueAnimator.getAnimatedValue();
                    holder.containerView.requestLayout();
                }
            });

            if(expandedItems.contains(project)) {
                valueAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        holder.expandButton.setClickable(true);
                    }
                });
            } else{
                valueAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        holder.expandButton.setClickable(true);
                    }
                });
            }

            valueAnimator.start();
        });
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.container_view) View containerView;
        @BindView(R.id.project_name) TextView projectName;
        @BindView(R.id.project_description) TextView projectDescription;
        @BindView(R.id.expand_button) Button expandButton;
        @BindView(R.id.store) Button btnStore;
        @BindView(R.id.project_image) ImageView projectImage;


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
