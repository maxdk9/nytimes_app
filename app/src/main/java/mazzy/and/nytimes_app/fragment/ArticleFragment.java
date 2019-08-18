package mazzy.and.nytimes_app.fragment;

import android.graphics.Bitmap;
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
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mazzy.and.nytimes_app.R;

public class ArticleFragment extends Fragment {
    public static final String ARTICLE_URL="article_url";


    private View view;
    @BindView(R.id.fragment_article_webview)
    WebView articleWebview;
    @BindView(R.id.fragment_article_progressbar)
    ProgressBar progressBar;
    private Unbinder unbinder;

    private String artUrl;

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

        unbinder = ButterKnife.bind(this, view);
        initArticle(view);


        return view;
    }

    private void ShowProgressBar(boolean b) {
        if (b) {
            progressBar.setVisibility(View.VISIBLE);
            articleWebview.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            articleWebview.setVisibility(View.VISIBLE);
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
