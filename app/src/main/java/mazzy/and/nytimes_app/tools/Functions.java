package mazzy.and.nytimes_app.tools;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import mazzy.and.nytimes_app.R;

public class Functions {
    public static void changeMainFragment(FragmentActivity fragmentActivity, Fragment fragment){
        fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.main_app_container,fragment).commit();
    }


    public static String getStringResourceByName(String aString, Context context) {
        String packageName = context.getPackageName();
        int resId = context.getResources().getIdentifier(aString, "string", packageName);
        return context.getString(resId);
    }


    public static void addAndInitFragmentWithBackStack(Fragment fragment, int container, FragmentManager fm) {

        if(fm.findFragmentByTag(fragment.getClass().getSimpleName())==null) {
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            ft.add(container, fragment, fragment.getClass().getSimpleName());

            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack(fragment.getClass().getSimpleName());

            ft.commit();
        }
    }

    public static boolean checkInternetConnection(Context context) {

           ConnectivityManager con_manager = (ConnectivityManager)
                   context.getSystemService(Context.CONNECTIVITY_SERVICE);

           return (con_manager.getActiveNetworkInfo() != null
                   && con_manager.getActiveNetworkInfo().isAvailable()
                   && con_manager.getActiveNetworkInfo().isConnected());
     }
}
