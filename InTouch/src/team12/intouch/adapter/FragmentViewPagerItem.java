package team12.intouch.adapter;

import android.support.v4.app.Fragment;

public class FragmentViewPagerItem {
    private String title;
    private Class<? extends Fragment> fragmentClass;

    public FragmentViewPagerItem(String title, Class<? extends Fragment> fragmentClass){
        this.title = title;
        this.setFragmentClass(fragmentClass);
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Class<? extends Fragment> getFragmentClass() {
        return fragmentClass;
    }

    public void setFragmentClass(Class<? extends Fragment> fragmentClass) {
        this.fragmentClass = fragmentClass;
    }

    public Fragment newInstanceOfFragmentClass() throws InstantiationException, IllegalAccessException{
        return this.fragmentClass.newInstance();
    }

}