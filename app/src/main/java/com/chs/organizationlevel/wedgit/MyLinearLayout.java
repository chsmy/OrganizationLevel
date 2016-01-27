package com.chs.organizationlevel.wedgit;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.chs.organizationlevel.R;

/**
 * 作者：chs on 2015/12/31 15:24
 * 邮箱：657083984@qq.com
 */
public class MyLinearLayout extends LinearLayout {
    private Context mContext;
    private OnItemClickListener onItemClickListener;
    public MyLinearLayout(Context context) {
        super(context);
        mContext = context;
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }
   public interface OnItemClickListener{
    void onItemClick(int position);
   }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        final int count = getChildCount();
        for(int i = 0;i<count;i++){
            View view = getChildAt(i);
            if(i>0){
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
                layoutParams.setMargins(-40, 0, 0, 0);
                view.setLayoutParams(layoutParams);
            }
            if((count-i)==1){
                view.setBackgroundResource(R.mipmap.organization_bg_blue);
            }else {
                view.setBackgroundResource(R.mipmap.organization_bg_gray);
            }
            final int finalI = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(count>1&&(count-finalI)!=1){
                        onItemClickListener.onItemClick(finalI);
                    }
                }
            });
        }
    }
    public void removeView(int position){
        int count = getChildCount();
        int x = 0;
        for(int i = 0;i<count;i++){
            View view = null;
            if(i>position){
                view = getChildAt(i-x);
                removeView(view);
                x++;
            }
        }
    }
}
