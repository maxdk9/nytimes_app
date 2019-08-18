package mazzy.and.nytimes_app.adapter;

import android.content.Context;
import android.graphics.Bitmap;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mazzy.and.nytimes_app.R;
import mazzy.and.nytimes_app.activity.MainActivity;
import mazzy.and.nytimes_app.db.DbLab;
import mazzy.and.nytimes_app.fragment.ArticleFragment;
import mazzy.and.nytimes_app.model.Article;
import mazzy.and.nytimes_app.model.Media;
import mazzy.and.nytimes_app.tools.Functions;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private Context context;
    private List<Article> articles;
    FragmentManager fragmentManager;


    public ArticleAdapter(Context c, List<Article> l,FragmentManager fm){
        this.context=c;
        this.articles=l;
        this.fragmentManager=fm;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {


        final Article article=articles.get(i);



        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArticleFragment articleDetailFragment = new ArticleFragment();
                Bundle bundle = new Bundle();
                bundle.putString(ArticleFragment.ARTICLE_URL,article.getUrl());
                articleDetailFragment.setArguments(bundle);
                Functions.addAndInitFragmentWithBackStack(articleDetailFragment, R.id.main_app_container, fragmentManager);

            }
        });
        holder.articleImageFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                article.setFavorite(!article.isFavorite());
                if(article.isFavorite()){
                    holder.articleImageFavorite.setImageResource(R.drawable.ic_favorite_checked);
                    DbLab.getInstance(context).AddArticle(article);
                }
                else{
                    holder.articleImageFavorite.setImageResource(R.drawable.ic_favorite_unchecked);
                    DbLab.getInstance(context).DeleteArticle(article);
                }

            }
        });



        holder.title.setText(article.getTitle());
        holder.articleDate.setText(article.getPublished_date());
        DbLab.getInstance(context).SetFavoriteFromList(article);
        if(article.isFavorite()){
            holder.articleImageFavorite.setImageResource(R.drawable.ic_favorite_checked);
        }
        else{
            holder.articleImageFavorite.setImageResource(R.drawable.ic_favorite_unchecked);
        }
        String imageurl = Article.GetImageUrl(article);
        if (imageurl != null) {
            Glide.with(context)
                    .load(imageurl).transition(DrawableTransitionOptions.withCrossFade()).placeholder(R.drawable.ic_launcher_background).override(40, 40).into(holder.articleIcon);
        }




    }




    @Override
    public int getItemCount() {
        return articles.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_article_mainlayout)
        ConstraintLayout mainLayout;
        @BindView(R.id.item_article_title)
        TextView title;
        @BindView(R.id.item_article_date)
        TextView articleDate;
        @BindView(R.id.item_article_icon)
        ImageView articleIcon;

        @BindView(R.id.item_article_favorite)
        ImageView articleImageFavorite;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
