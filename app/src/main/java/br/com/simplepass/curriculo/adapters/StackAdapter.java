package br.com.simplepass.curriculo.adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;
import java.util.List;

import br.com.simplepass.curriculo.R;
import br.com.simplepass.curriculo.domain.StackItem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by leandro on 6/3/16.
 */
public class StackAdapter extends RecyclerView.Adapter<StackAdapter.ViewHolder>{
    List<StackItem>  stackItemList;
    Context mContext;
    List<StackItem> expandedItems;

    private static final double EXPAND_FACTOR = 2.6;


    public StackAdapter(Context context, List<StackItem> stackItemList) {
        this.stackItemList = stackItemList;
        mContext = context;
        expandedItems = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View pathView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_stack, parent, false);

        return new ViewHolder(pathView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final StackItem stackItem = stackItemList.get(position);

        holder.stackDescription.setText(stackItem.getDescription());

        basicConfigPieChart(holder.pieChart);

        holder.pieChart.setCenterText(stackItem.getName());

        ArrayList<String> xVals = new ArrayList<>();
        xVals.add("");
        xVals.add("");

        ArrayList<Entry> yVals1 = new ArrayList<>();
        yVals1.add(new Entry(stackItem.getLevel(), 3));
        yVals1.add(new Entry((100 - stackItem.getLevel()), 0));

        PieDataSet dataSet = new PieDataSet(yVals1, "");

        setColor(dataSet, position, mContext);
        dataSet.setDrawValues(false);

        PieData data = new PieData(xVals, dataSet);
        holder.pieChart.setData(data);

        holder.pieChart.invalidate();
        holder.pieChart.getLegend().setEnabled(false);

        holder.pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
    }

    @Override
    public int getItemCount() {
        return stackItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.container_view) View containerView;
        @BindView(R.id.stack_description) TextView stackDescription;
        @BindView(R.id.stack_chart) PieChart pieChart;
        @BindView(R.id.expand_button) Button expandButton;

        public ViewHolder(View view) {
            super(view);

            try {
                ButterKnife.bind(this, view);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void setColor(PieDataSet dataSet, int position, Context context){
        int color = position % 10;

        switch (color){
            case 0:
                dataSet.setColors(new int[] {android.R.color.holo_orange_dark, R.color.white}, mContext);
                break;
            case 1:
                dataSet.setColors(new int[] {android.R.color.holo_blue_dark, R.color.white}, mContext);
                break;
            case 2:
                dataSet.setColors(new int[] {android.R.color.holo_green_light, R.color.white}, mContext);
                break;
            case 3:
                dataSet.setColors(new int[] {android.R.color.holo_red_dark, R.color.white}, mContext);
                break;
            case 4:
                dataSet.setColors(new int[] {android.R.color.holo_orange_light, R.color.white}, mContext);
                break;
            case 5:
                dataSet.setColors(new int[] {R.color.yellow, R.color.white}, mContext);
                break;
            case 6:
                dataSet.setColors(new int[] {android.R.color.holo_purple, R.color.white}, mContext);
                break;
            case 7:
                dataSet.setColors(new int[] {android.R.color.darker_gray, R.color.white}, mContext);
                break;
            case 8:
                dataSet.setColors(new int[] {R.color.cian, R.color.white}, mContext);
                break;
            case 9:
                dataSet.setColors(new int[] {R.color.teal, R.color.white}, mContext);
        }
    }

    private void basicConfigPieChart(PieChart pieChart) {
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);

        pieChart.setDescription("");
        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setHoleRadius(60f);
    }
}
