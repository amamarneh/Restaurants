package com.am.restauarnts.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.am.restauarnts.R;
import com.am.restauarnts.ui.base.BaseViewHolder;
import com.am.restauarnts.ui.models.Food;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodMandatoryAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<Food> items;

    public int getPositionSelected() {
        return mPositionSelected;
    }

    public FoodMandatoryAdapter(List<Food> items) {
        this.items = items;
    }

    private ItemHolder mHolderSelected;
    private int mPositionSelected = -1;
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food_mandatory, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }
    public Food getItem(){
        if (mPositionSelected==-1) return null;
        return items.get(mPositionSelected);
    }

    @Override
    public int getItemCount() {
        return items!=null?items.size():0;
    }
    class ItemHolder extends BaseViewHolder implements Checkable{
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvInfo)
        TextView tvInfo;
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.radioButton)
        RadioButton radioButton;
        @BindView(R.id.layoutHolder)
        View layoutHolder;
        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        @Override
        public void onBind(int position) {
            tvName.setText(items.get(position).getName());
            tvInfo.setText(items.get(position).getDescription());
            tvPrice.setText(items.get(position).getPrice()+" NIS");
            layoutHolder.setOnClickListener(view -> {
                if(mHolderSelected != null)
                    mHolderSelected.setChecked(false);
                mHolderSelected  = this;
                mHolderSelected.setChecked(true);
                mPositionSelected = getAdapterPosition();
            });
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        if(mHolderSelected != null)
                            mHolderSelected.setChecked(false);
                        mHolderSelected  = ItemHolder.this;
                        mHolderSelected.setChecked(true);
                        mPositionSelected = getAdapterPosition();
                    }
                }
            });
        }

        @Override
        public void setChecked(boolean b) {
            radioButton.setChecked(b);
        }

        @Override
        public boolean isChecked() {
            return radioButton.isChecked();
        }

        @Override
        public void toggle() {

        }
    }
}
