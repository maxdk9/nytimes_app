package mazzy.and.nytimes_app.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.Unbinder;
import mazzy.and.nytimes_app.R;
import mazzy.and.nytimes_app.db.DbLab;
import mazzy.and.nytimes_app.model.Article;

public class ArticleFragment extends Fragment {
    public static final String ARTICLE_URL="article_url";
    public static final String ARTICLE_OB = "article_ob";


    private View view;
    @BindView(R.id.fragment_article_webview)
    WebView articleWebview;
    @BindView(R.id.fragment_article_progressbar)
    ProgressBar progressBar;
    @BindView(R.id.fragment_article_imagefavorite)
    ImageView articleImageFavorite;

    @BindDrawable(R.drawable.ic_favorite_unchecked)
    Drawable favoriteUnchecked;
    @BindDrawable(R.drawable.ic_favorite_checked)
    Drawable favoriteChecked;

    @OnClick(R.id.fragment_article_imagefavorite)
    void clickOnFavorite(){
        article.setFavorite(!article.isFavorite());

        if(article.isFavorite()){
            DbLab.getInstance(getContext()).AddArticle(article);
        }
        else{
            DbLab.getInstance(getContext()).DeleteArticle(article);
        }
        SetImageFavorite();

    }

    void SetImageFavorite() {
        if(article.isFavorite()){

            articleImageFavorite.setImageDrawable(favoriteChecked);
        }
        else{

            articleImageFavorite.setImageDrawable(favoriteUnchecked);
        }
    }

    private Unbinder unbinder;
    private String artUrl;
    private Article article;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_article, null);
        artUrl = getArguments().getString(ARTICLE_URL);
        article= (Article) getArguments().getSerializable(ARTICLE_OB);
        unbinder = ButterKnife.bind(this, view);
        initArticle(view);
        SetImageFavorite();

        return view;
    }

    private void ShowProgressBar(boolean b) {
        if (b) {
            progressBar.setVisibility(View.VISIBLE);
            articleWebview.setVisibility(View.GONE);
            articleImageFavorite.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            articleWebview.setVisibility(View.VISIBLE);
            articleImageFavorite.setVisibility(View.VISIBLE);
        }
    }

    private void initArticle(View view) {
        articleWebview.getSettings().setJavaScriptEnabled(true); // enable javascript
        articleWebview.setWebViewClient(new ArtWebViewClient());
        articleWebview.loadUrl(artUrl);
    }

    private class ArtWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
            ShowProgressBar(true);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            ShowProgressBar(false);
            super.onPageFinished(view, url);

        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }
    }
}
