package mazzy.and.nytimes_app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import mazzy.and.nytimes_app.R;
import mazzy.and.nytimes_app.fragment.ArticleListFragment;
import mazzy.and.nytimes_app.model.ApiType;
import mazzy.and.nytimes_app.tools.Functions;
import mazzy.and.nytimes_app.ui.TabAdapter;


public class MainActivity extends AppCompatActivity {
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_old);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getSupportFragmentManager());


        ArticleListFragment emailedFragment=new ArticleListFragment();
        emailedFragment.setArguments(GetArticleListFragmentBundle(ApiType.EMAILED));
        ArticleListFragment sharedFragment=new ArticleListFragment();
        sharedFragment.setArguments(GetArticleListFragmentBundle(ApiType.SHARED));
        ArticleListFragment viewedFragment=new ArticleListFragment();
        viewedFragment.setArguments(GetArticleListFragmentBundle(ApiType.VIEWED));


        adapter.addFragment(emailedFragment, Functions.getStringResourceByName("emailedtab",this));
        adapter.addFragment(sharedFragment, Functions.getStringResourceByName("sharedtab",this));
        adapter.addFragment(viewedFragment, Functions.getStringResourceByName("viewedtab",this));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private Bundle GetArticleListFragmentBundle(ApiType t) {
        Bundle b = new Bundle();
        b.putString(ArticleListFragment.ApiTypeKey, t.toString());
        return b;
    }
}