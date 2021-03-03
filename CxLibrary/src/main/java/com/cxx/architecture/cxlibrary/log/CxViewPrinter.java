package com.cxx.architecture.cxlibrary.log;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cxx.architecture.cxlibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: cuixiaoxiao
 * @time: 2021/3/2
 * @email: cuixiaoxiao@58.com
 * @describe: 可视化日志打印器, 将log信息打印在界面上
 */
public class CxViewPrinter implements CxLogPrinter {
    private RecyclerView recyclerView;
    private LogAdapter logAdapter;
    private CxViewPrinterProvider viewPrinterProvider;

    /**
     * 初始化recycleView，CxViewPrinterProvider
     *
     * @param activity
     */
    public CxViewPrinter(Activity activity) {
        recyclerView = new RecyclerView(activity);
        logAdapter = new LogAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(logAdapter);
        viewPrinterProvider = new CxViewPrinterProvider((FrameLayout) activity.findViewById(android.R.id.content),recyclerView);
    }

    @Override
    public void printer(@Nullable CxLogConfig cxLogConfig, int type, String tag, @Nullable String printString) {
        //将log信息添加到recyclerView,并滚动到相应位置
        logAdapter.addData(new CxLogMo(System.currentTimeMillis(), type, tag, printString));
        recyclerView.scrollToPosition(logAdapter.getItemCount() - 1);
    }

    /**
     * 获取内容提供器
     *
     * @return
     */
    public CxViewPrinterProvider getViewProvider() {
        return viewPrinterProvider;
    }

    private static class LogAdapter extends RecyclerView.Adapter<LogViewHolder> {
        private List<CxLogMo> dataList = new ArrayList<>();

        public void addData(CxLogMo cxLogMo) {
            dataList.add(cxLogMo);
            notifyItemChanged(dataList.size() - 1);
        }

        @NonNull
        @Override
        public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new LogViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cxlog_msg_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
            CxLogMo cxLogMo = dataList.get(position);
            holder.tagView.setText(cxLogMo.getFlattened());
            holder.messageView.setText(cxLogMo.getLog());
            holder.tagView.setTextColor(getLogTextColor(cxLogMo.getLevel()));
            holder.messageView.setTextColor(getLogTextColor(cxLogMo.getLevel()));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        /**
         * 获取日志展示颜色
         *
         * @param logLevel
         * @return
         */
        private int getLogTextColor(int logLevel) {
            int highlight;
            switch (logLevel) {
                case CxLogType.V:
                    highlight = 0xffbbbbbb;
                    break;
                case CxLogType.D:
                    highlight = 0xffffffff;
                    break;
                case CxLogType.I:
                    highlight = 0xff6a8759;
                    break;
                case CxLogType.W:
                    highlight = 0xffbbb529;
                    break;
                case CxLogType.E:
                    highlight = 0xffff6b68;
                    break;
                default:
                    highlight = 0xffffff00;
                    break;
            }
            return highlight;
        }
    }

    private static class LogViewHolder extends RecyclerView.ViewHolder {
        TextView tagView;
        TextView messageView;

        public LogViewHolder(@NonNull View itemView) {
            super(itemView);
            tagView = itemView.findViewById(R.id.cxlog_msg_item_tv_tag);
            messageView = itemView.findViewById(R.id.cxlog_msg_item_tv_msg);
        }
    }
}
