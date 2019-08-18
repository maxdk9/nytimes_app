package mazzy.and.nytimes_app.tools;

import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import mazzy.and.nytimes_app.R;

public class Functions {
    public static void changeMainFragment(FragmentActivity fragmentActivity, Fragment fragment){
        fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
    }

    public static void changeMainFragmentWithBackstack(FragmentManager fragmentManager, Fragment fragment){
        fragmentManager.beginTransaction().replace(R.id.main_container,fragment).addToBackStack(null).commit();;
    }

    public static String getStringResourceByName(String aString, Context context) {
        String packageName = context.getPackageName();
        int resId = context.getResources().getIdentifier(aString, "string", packageName);
        return context.getString(resId);
    }


    public static void addAndInitFragmentWithBackStack(Fragment fragment, int container, FragmentManager fm) {

        if(fm.findFragmentByTag(fragment.getClass().getSimpleName())==null) {
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
//            ft.setCustomAnimations(R.animator.fragment_animation_left_enter,R.animator.fragment_animation_left_out, R.animator.fragment_animation_right_enter,R.animator.fragment_animation_right_out);
            ft.add(container, fragment, fragment.getClass().getSimpleName());
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack(fragment.getClass().getSimpleName());
            ft.commit();
        }
    }

}
