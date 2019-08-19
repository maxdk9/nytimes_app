package mazzy.and.nytimes_app.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mazzy.and.nytimes_app.R;
import mazzy.and.nytimes_app.model.ArticleType;
import mazzy.and.nytimes_app.tools.Functions;
import mazzy.and.nytimes_app.adapter.TabAdapter;

public class TabFragment extends Fragment {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    TabAdapter tabAdapter;


    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);


        unbinder = ButterKnife.bind(this, view);

        tabAdapter = new TabAdapter(getFragmentManager());


        ArticleListFragment emailedFragment=new ArticleListFragment();
        emailedFragment.setArguments(GetArticleListFragmentBundle(ArticleType.EMAILED));
        ArticleListFragment sharedFragment=new ArticleListFragment();
        sharedFragment.setArguments(GetArticleListFragmentBundle(ArticleType.SHARED));
        ArticleListFragment viewedFragment=new ArticleListFragment();
        viewedFragment.setArguments(GetArticleListFragmentBundle(ArticleType.VIEWED));
        ArticleListFragment favoritesFragment=new ArticleListFragment();
        ((ArticleListFragment) favoritesFragment).setArguments(GetArticleListFragmentBundle(ArticleType.FAVORITE));




        tabAdapter.addFragment(emailedFragment, Functions.getStringResourceByName("emailedtab",getContext()));
        tabAdapter.addFragment(sharedFragment, Functions.getStringResourceByName("sharedtab",getContext()));
        tabAdapter.addFragment(viewedFragment, Functions.getStringResourceByName("viewedtab",getContext()));
        tabAdapter.addFragment(favoritesFragment, Functions.getStringResourceByName("favoritestab",getContext()));
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private Bundle GetArticleListFragmentBundle(ArticleType t) {
        Bundle b = new Bundle();
        b.putString(ArticleListFragment.ApiTypeKey, t.toString());
        return b;
    }


}
