package br.com.simplepass.curriculo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.simplepass.curriculo.R;
import br.com.simplepass.curriculo.domain.AcademicItem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by leandro on 6/2/16.
 */
public class AcademicsAdapter extends RecyclerView.Adapter<AcademicsAdapter.ViewHolder>{

    private List<AcademicItem> mAcademicItemList;

    public AcademicsAdapter(List<AcademicItem> academicItemList) {
        this.mAcademicItemList = academicItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View pathView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_academic, parent, false);

        return new ViewHolder(pathView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AcademicItem academicItem = mAcademicItemList.get(position);

        holder.academicName.setText(academicItem.getName());
        holder.academicConclusion.setText(academicItem.getConclusion());
        //holder.academicIcon.setImageDrawable(academicItem.getDrawable());
    }

    @Override
    public int getItemCount() {
        return mAcademicItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        //@BindView(R.id.container_view) View containerView;
        @BindView(R.id.academic_name) TextView academicName;
        @BindView(R.id.academic_conclusion) TextView academicConclusion;
       // @BindView(R.id.academic_icon) ImageView academicIcon;

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
