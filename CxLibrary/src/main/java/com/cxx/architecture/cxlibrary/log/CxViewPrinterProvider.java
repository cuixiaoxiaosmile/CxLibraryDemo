package com.cxx.architecture.cxlibrary.log;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cxx.architecture.cxlibrary.utils.CxDisplayUtil;

/**
 * @author: cuixiaoxiao
 * @time: 2021/3/2
 * @email: cuixiaoxiao@58.com
 * @describe:布局内容提供器
 */
public class CxViewPrinterProvider {
    /**
     * 标识根布局是否添加过log展示布局
     */
    private static String TAG_LOG_VIEW = "TAG_LOG_VIEW";

    /**
     * 标识根布局是否添加过悬浮按钮
     */
    private static String TAG_FLOATING_VIEW = "TAG_FLOATING_VIEW";

    /**
     * 标识logview是否为打开状态
     */
    private boolean isOpen;

    /**
     * activity界面根布局，用于添加log布局，并且不影响其他布局展示
     */
    private FrameLayout rootView;
    /**
     * 用于展示log信息的列表
     */
    private RecyclerView recyclerView;

    /**
     * 悬浮按钮
     */
    private View floatingView;

    /**
     * 展示log的布局
     * @param rootView
     * @param recyclerView
     */
    private View logView;
    public CxViewPrinterProvider(FrameLayout rootView,RecyclerView recyclerView){
        this.rootView = rootView;
        this.recyclerView = recyclerView;
    }

    /**
     * 展示界面悬浮的log按钮
     */
    public void showFloatingView(){
        if(null != rootView.findViewWithTag(TAG_FLOATING_VIEW)){
            return;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.RIGHT | Gravity.BOTTOM;
        layoutParams.bottomMargin = CxDisplayUtil.sp2px(100);
        rootView.addView(getFloatingView(),layoutParams);
        rootView.setTag(TAG_FLOATING_VIEW);
    }

    /**
     * 展示用于展示log信息的logView布局
     */
    private void showLogView(){
        if(null != rootView.findViewWithTag(TAG_LOG_VIEW)){
            return;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CxDisplayUtil.sp2px(160));
        layoutParams.gravity = Gravity.BOTTOM;
        rootView.setTag(TAG_LOG_VIEW);
        rootView.addView(getLogView(),layoutParams);
        isOpen = true;
    }

    /**
     * 关闭logView
     */
    public void closeLogView(){
        isOpen = false;
        rootView.removeView(getLogView());
    }

    /**
     * 关闭log的悬浮按钮
     */
    public void closeFloatingView(){
        rootView.removeView(getFloatingView());
    }

    /**
     * 创建悬浮按钮的TextView，并添加点击事件
     * @return
     */
    private View getFloatingView(){
        if(null != floatingView){
            return floatingView;
        }
        TextView textView = new TextView(rootView.getContext());
        textView.setText("HiLog");
        textView.setBackgroundColor(Color.BLACK);
        textView.setAlpha(0.8f);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isOpen) {
                    showLogView();
                }
            }
        });
        floatingView = textView;
        return floatingView;
    }

    /**
     * 创建用于展示信息的view(RecycleView+TextView关闭按钮)，并添加关闭按钮的点击事件
     * @return
     */
    private View getLogView(){
        if(null != logView){
            return logView;
        }
        FrameLayout logView = new FrameLayout(rootView.getContext());
        logView.addView(recyclerView);
        logView.setBackgroundColor(Color.BLACK);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.END;
        TextView tvClose = new TextView(rootView.getContext());
        tvClose.setText("close");
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeLogView();
            }
        });
        logView.addView(tvClose,layoutParams);
        this.logView = logView;
        return logView;
    }
}
