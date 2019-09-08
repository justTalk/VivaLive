package com.viva.live.base.component;

import android.content.Context;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.viva.live.base.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author:mingming.liu
 * description:
 * warn:
 * time: 2019-09-07
 */
public abstract class BaseFragment extends Fragment {
    public static List<String> sPendingFragment;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (getUserVisibleHint() != isVisibleToUser) {
            visibleChanged(isVisibleToUser);
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    public void visibleChanged(boolean isVisibleToUser){

    }

    protected void bindEvenListener() {
    }

    public void back(){
        getActivity().onBackPressed();
    }

    public void replaceFragment(int containerId, Fragment fragment){
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(containerId, fragment)
                .addToBackStack(fragment.getTag())
                .commit();
    }

    public void addFragment(int containerId, BaseFragment fragment){
        if (addPendingFragment(fragment)) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .add(containerId, fragment)
                    .addToBackStack(fragment.getTag())
                    .setCustomAnimations(R.anim.base_slide_right_in, R.anim.base_slide_right_out)
                    .commit();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        removeLastPenddingFragment();
    }

    private void removeLastPenddingFragment(){
        if (sPendingFragment == null) {
            return;
        }
        int index = sPendingFragment.lastIndexOf(getUUID());
        if (index != -1) {
            sPendingFragment.remove(index);
        }
    }

    private boolean addPendingFragment(BaseFragment fragment){
        if (sPendingFragment == null) {
            sPendingFragment = new ArrayList<>();
        }
        if (sPendingFragment.contains(fragment.getUUID())) {
            return false;
        }
        return sPendingFragment.add(fragment.getUUID());
    }

    public String getUUID(){
        return getClass().getName();
    }

    /**
     * 显示键盘
     */
    protected void showKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethod.SHOW_FORCED);
    }

    /**
     * 隐藏键盘
     */
    protected boolean hideKeyBoard() {
        final InputMethodManager imm = (InputMethodManager) getActivity().getApplicationContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.hideSoftInputFromWindow(getActivity().findViewById(android.R.id.content)
                .getWindowToken(), 0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public boolean onBackPressed(){
        return false;
    }

    /**
     * 获取上一个Fragment
     */
    public Fragment getLastFragment(){
        FragmentManager fragmentManager = getChildFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments.size() >= 1) {
            return fragments.get(fragments.size() - 1);
        }else {
            fragmentManager =  getActivity().getSupportFragmentManager();
            fragments = fragmentManager.getFragments();
            if (fragments.size() > 1) {
                return fragments.get(fragments.size() - 2);
            }
        }
        return null;
    }

    public Fragment getTopFragment(){
        FragmentManager fragmentManager = getChildFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments.size() >= 1) {
            return fragments.get(fragments.size() - 1);
        }else {
            fragmentManager =  getActivity().getSupportFragmentManager();
            fragments = fragmentManager.getFragments();
            if (fragments.size() > 1) {
                return fragments.get(fragments.size() - 1);
            }
        }
        return null;
    }
}
