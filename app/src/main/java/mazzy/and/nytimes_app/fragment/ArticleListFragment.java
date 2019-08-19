package mazzy.and.nytimes_app.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mazzy.and.nytimes_app.R;
import mazzy.and.nytimes_app.adapter.ArticleAdapter;
import mazzy.and.nytimes_app.db.DbLab;
import mazzy.and.nytimes_app.model.ArticleType;
import mazzy.and.nytimes_app.model.Article;
import mazzy.and.nytimes_app.model.ResponseResult;
import mazzy.and.nytimes_app.webservice.ApiInterface;
import mazzy.and.nytimes_app.webservice.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleListFragment extends Fragment {

    private static final String TAG = ArticleListFragment.class.getSimpleName();
    public static final String ApiTypeKey = "apitype_key";


    @BindView(R.id.fragment_article_list_progressbar)
    ProgressBar progressBar;
    @BindView(R.id.fragment_article_list_recyclerview)
    RecyclerView recyclerView;



    private Unbinder unbinder;
    private List<Article> articleList = new ArrayList<Article>();
    private ArticleAdapter articleAdapter;
    private ArticleType apiType;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_acticle_list, container, false);

        Bundle bundle=getArguments();
        apiType= ArticleType.valueOf(bundle.getString(ApiTypeKey));
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        FragmentManager fragmentManager=getFragmentManager();
        articleAdapter = new ArticleAdapter(getActivity(), articleList,fragmentManager);
        recyclerView.setAdapter(articleAdapter);
        ShowProgressBar(true);
        GetArticles();


        return view;
    }





    private void GetArticles() {
        if (apiType == ArticleType.FAVORITE) {
            articleList.clear();
            articleList.addAll(DbLab.getInstance(getContext()).getArticles());
            ShowProgressBar(false);
            return;
        }

        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<ResponseResult> call = apiInterface.getMostPopular("mostpopular", "v2", apiType.getTypeString(), 30, ServiceGenerator.API_KEY);
        call.enqueue(new Callback<ResponseResult>() {
            @Override
            public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {
                if (response.isSuccessful()) {
                    ResponseResult responseResult = (ResponseResult) response.body();


                    articleList.addAll(responseResult.getResults());
                    articleAdapter.notifyDataSetChanged();
                } else {
                    Log.e(TAG, "failed" + response.message());
                }
                ShowProgressBar(false);
            }

            @Override
            public void onFailure(Call<ResponseResult> call, Throwable t) {
                Log.e(TAG, "failed" + t.getMessage());

                ShowProgressBar(false);
            }
        });

    }

    private void ShowProgressBar(boolean b) {
        if (b) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
