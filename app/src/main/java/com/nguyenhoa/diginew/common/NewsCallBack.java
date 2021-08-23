package com.nguyenhoa.diginew.common;

import android.widget.TextView;

public interface NewsCallBack {
    void onNewsItemClick(int pos,
                         TextView ivTopic,
                         TextView ivSource,
                         TextView tvTime);
}
