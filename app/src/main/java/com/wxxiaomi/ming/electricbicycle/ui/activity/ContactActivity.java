package com.wxxiaomi.ming.electricbicycle.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wxxiaomi.ming.electricbicycle.R;
import com.wxxiaomi.ming.electricbicycle.manager.Account;
import com.wxxiaomi.ming.electricbicycle.ui.activity.base.MvpActivity;
import com.wxxiaomi.ming.electricbicycle.im.ui.ContactListFragment;
import com.wxxiaomi.ming.electricbicycle.im.ui.ConversationListFragment;
import com.wxxiaomi.ming.electricbicycle.ui.presenter.ContactPresenter;
import com.wxxiaomi.ming.electricbicycle.ui.presenter.impl.ContactPresenterImpl;
import com.wxxiaomi.ming.electricbicycle.ui.activity.view.ContactView;
import com.wxxiaomi.ming.electricbicycle.ui.weight.adapter.IndexFragmentTabAdapter;
import com.wxxiaomi.ming.electricbicycle.ui.weight.adapter.NewFriendAddItemAdapter;
import com.wxxiaomi.ming.electricbicycle.ui.fragment.base.BaseFragment;
import com.wxxiaomi.ming.electricbicycle.ui.fragment.base.FragmentCallback;
import com.wxxiaomi.ming.electricbicycle.ui.weight.custom.MsgActionProvider;

import java.util.ArrayList;
import java.util.List;

/**
* @author whaoming
* github：https://github.com/whaoming
* created at 2017/2/24 9:50
* TODO: 联系人页面
*/
public class ContactActivity extends MvpActivity<ContactView,ContactPresenter> implements ContactView<ContactPresenter>,FragmentCallback {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentStatePagerAdapter fAdapter;
    private List<Fragment> list_fragment;
    private List<String> list_title;
    private ConversationListFragment demoFragment;
    private ContactListFragment contactFragment;
//    private LinearLayout drawer;
//    private LinearLayoutManager mLayoutManager;

//    private RelativeLayout drawer_ll;
    private MsgActionProvider mActionProvider;
//    private MsgActionProvider mActionProvider2;

    @Override
    protected void initView(Bundle savedInstanceState) {

        setContentView(R.layout.activity_contact2);
        tabLayout = (TabLayout) findViewById(R.id.tab_FindFragment_title);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        viewPager = (ViewPager) findViewById(R.id.vp_FindFragment_pager);
        toolbar = (Toolbar) this.findViewById(R.id.toolbar1);
        toolbar.setTitle(Account.getAccountInfo().nickname);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mLayoutManager = new LinearLayoutManager(this);
        list_fragment = new ArrayList<Fragment>();
        contactFragment = new ContactListFragment();
        demoFragment = new ConversationListFragment();
        list_fragment.add(demoFragment);
        list_fragment.add(contactFragment);
        list_title = new ArrayList<String>();
        list_title.add("");
        list_title.add("");
        fAdapter = new IndexFragmentTabAdapter(getSupportFragmentManager(),
                list_fragment, list_title);
        viewPager.setAdapter(fAdapter);
        viewPager.requestDisallowInterceptTouchEvent(true);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.mipmap.ic_access_time_white_36dp);
        tabLayout.getTabAt(1).setIcon(R.mipmap.ic_perm_contact_cal_white_48dp);
    }

    @Override
    public ContactPresenter getPresenter() {
        return new ContactPresenterImpl();
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.drawer_ll:
                presenter.onAddFriendBtnClick();
                break;
        }
    }

    @Override
    public void updateUnReadMsg(int count) {
        Log.i("wang","更新邀请信息："+count);
        if (mActionProvider!=null) {
            mActionProvider.setBadge(count);
        }
    }

    @Override
    public void setInviteListAdapter(NewFriendAddItemAdapter adapter) {
//        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void runActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this,clazz);
        if(bundle!=null){
            intent.putExtra("value",bundle);
        }
        startActivity(intent);
    }

    @Override
    public void refershChildUI() {
        demoFragment.refresh();
    }

    @Override
    public void onFragmentCallback(BaseFragment fragment, int id, Bundle args) {
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_msg_notify, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_invite:
                presenter.onInviteBtnClick();
                break;
            case R.id.menu_search:
                presenter.onAddFriendBtnClick();
                break;
            case R.id.menu_mine:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
