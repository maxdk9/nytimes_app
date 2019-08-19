package mazzy.and.nytimes_app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import mazzy.and.nytimes_app.R;
import mazzy.and.nytimes_app.db.DbLab;
import mazzy.and.nytimes_app.fragment.TabFragment;
import mazzy.and.nytimes_app.tools.Functions;
import mazzy.and.nytimes_app.adapter.TabAdapter;


public class MainActivity extends AppCompatActivity {
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbLab.getInstance(MainActivity.this).UpdateFavoritesIdList();
        setContentView(R.layout.activity_main);
        TabFragment tabFragment=new TabFragment();
        Functions.changeMainFragment(MainActivity.this,tabFragment);

        if(!Functions.checkInternetConnection(MainActivity.this)){
            Toast.makeText(getApplicationContext(), "No internet connection!", Toast.LENGTH_SHORT).show();
        }

    }


}