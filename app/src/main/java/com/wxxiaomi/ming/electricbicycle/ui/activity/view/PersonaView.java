package com.wxxiaomi.ming.electricbicycle.ui.activity.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.wxxiaomi.ming.electricbicycle.dao.bean.UserCommonInfo2;
import com.wxxiaomi.ming.electricbicycle.ui.presenter.base.BasePre;
import com.wxxiaomi.ming.electricbicycle.ui.activity.base.BaseView;
import com.wxxiaomi.ming.electricbicycle.ui.weight.pull2refreshreview.PullToRefreshRecyclerView;


/**
 * Created by 12262 on 2016/6/21.
 */
public interface PersonaView<T extends BasePre> extends BaseView {
    Intent getIntent();
    void setViewData(UserCommonInfo2 info);
    void setBtnView(Drawable drawable);
//    void setAdapter(RecyclerView.Adapter adapter);
    ImageView getHeadView();

    PullToRefreshRecyclerView getListView();

    ImageView getBackImgContent();
}