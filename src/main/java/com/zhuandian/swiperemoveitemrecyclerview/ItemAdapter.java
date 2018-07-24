package com.zhuandian.swiperemoveitemrecyclerview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * desc :
 * author：xiedong
 * data：2018/7/24
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHodler> {

    private List<ItemEntity> mdatas;
    private Context context;
    private OnSwipeItemDelClickListener onSwipeItemDelClickListener;
    private boolean isAllowSwipe = false;


    public ItemAdapter(List<ItemEntity> mdatas, Context context) {
        this.mdatas = mdatas;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHodler(LayoutInflater.from(context).inflate(R.layout.item_datas, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final ViewHodler holder, final int position) {

        final DisplayMetrics metrics = new DisplayMetrics();

        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        final int width = metrics.widthPixels;     // 屏幕宽度（像素）
        holder.tvContent.setLayoutParams(new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.MATCH_PARENT));
        holder.tvContent.setText(mdatas.get(position).getContent());

        //设置是否运行滚动
        holder.rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return isAllowSwipe();
            }
        });
        holder.tvDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSwipeItemDelClickListener != null) {
                    onSwipeItemDelClickListener.delItem(position);
                }
            }
        });

        holder.rootView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                System.out.println("------------" + holder.tvDel.getWidth() + "-------" + scrollX + "-----" + oldScrollX);
                mdatas.get(position).setOpen(true);
                if (scrollX > holder.tvDel.getWidth() / 2 && mdatas.get(position).isOpen()) {
                    holder.rootView.smoothScrollBy(width + holder.tvDel.getWidth(), 0);
                } else {
                    holder.rootView.smoothScrollBy(0, 0);
                }


//                Rect scrollBounds = new Rect();
//                holder.rootView.getHitRect(scrollBounds);
//                if (holder.tvDel.getLocalVisibleRect(scrollBounds)) {
//                    //子控件至少有一个像素在可视范围内
//                    // Any portion of the childView, even a single pixel, is within the visible window
//                } else {
//                    //子控件完全不在可视范围内
//                    // NONE of the childView is within the visible window
//                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }

    class ViewHodler extends RecyclerView.ViewHolder {
        TextView tvContent;
        TextView tvDel;
        HorizontalScrollView rootView;

        public ViewHodler(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            tvDel = (TextView) itemView.findViewById(R.id.tv_del);
            rootView = itemView.findViewById(R.id.hs_root);
        }
    }


    /**
     * 删除一条数据
     *
     * @param position
     */
    public void removeItemByPosition(int position) {
        mdatas.remove(position);
        notifyItemRemoved(position);
        //重新排列数据
        notifyItemRangeChanged(position, mdatas.size());
    }


    public boolean isAllowSwipe() {
        return isAllowSwipe;
    }

    public void setAllowSwipe(boolean allowSwipe) {
        isAllowSwipe = allowSwipe;
    }

    public void setOnSwipeItemDelClickListener(OnSwipeItemDelClickListener onSwipeItemDelClickListener) {
        this.onSwipeItemDelClickListener = onSwipeItemDelClickListener;
    }

    interface OnSwipeItemDelClickListener {
        void delItem(int position);
    }
}
