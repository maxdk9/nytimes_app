package mazzy.and.nytimes_app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Debug;
import android.support.constraint.ConstraintLayout;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import mazzy.and.nytimes_app.model.Article;

public class DbLab {
    private static DbLab instance;
    private Context context;
    private SQLiteDatabase mDatabase;
    private List<Long> favoritesIdList=new ArrayList<Long>();

    public  void SetFavoriteFromList(Article article) {
        article.setFavorite(favoritesIdList.contains(article.getId()));
    }


    public void UpdateFavoritesIdList(){
        favoritesIdList.clear();
        List<Article> articles=getArticles();
        for (Article article : articles) {
            favoritesIdList.add(article.getId());
        }
    }

    public static DbLab getInstance(Context c){
        if (instance == null) {
            instance=new DbLab(c);
        }
        return instance;
    }

    private DbLab(Context c) {
        context=c.getApplicationContext();
        mDatabase=new BaseHelper(context).getWritableDatabase();
    }


    public Article GetCrime(long articleId) {

        DbCursorWrapper cursorWrapper=queryArticle(
                DbSchema.Cols.id+" = ?",
                new String[]{String.valueOf(articleId)}
        );
        try{
            if (cursorWrapper.getCount() == 0) {
                return null;
            }
            else {
                cursorWrapper.moveToFirst();
                return cursorWrapper.GetArticle();
            }
        }
        finally {
            cursorWrapper.close();
        }
    }

    public List<Article> getArticles() {

        List<Article> articles = new ArrayList<Article>();
        DbCursorWrapper cursor = queryArticle(null, null);

        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                articles.add(cursor.GetArticle());
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return articles;
    }


    public void UpdateArticle(Article article) {

        String idstring=String.valueOf(article.getId());
        ContentValues values = getContentValues(article);
        mDatabase.update(DbSchema.NAME, values, DbSchema.Cols.id + " = ?", new String[]{idstring});
    }

    public void AddArticle(Article article) {
        ContentValues values = getContentValues(article);
        if(!favoritesIdList.contains(article.getId())){
            favoritesIdList.add(article.getId());
        }

        mDatabase.insert(DbSchema.NAME, null, values);
    }


    private static ContentValues getContentValues(Article article) {
        ContentValues res = new ContentValues();
        res.put(DbSchema.Cols.abst, article.getAbstr());
        res.put(DbSchema.Cols.adx_keywords,article.getAdx_keywords());
        res.put(DbSchema.Cols.asset_id, article.getAsset_id());
        res.put(DbSchema.Cols.byline, article.getByline());
        res.put(DbSchema.Cols.clmn, article.getColumn());
        res.put(DbSchema.Cols.count_type, article.getCount_type());
        res.put(DbSchema.Cols.email_count, article.getEmail_count());
        res.put(DbSchema.Cols.eta_id, article.getEta_id());
        res.put(DbSchema.Cols.id, article.getId());
        res.put(DbSchema.Cols.imageUrl, Article.GetImageUrl(article));
        res.put(DbSchema.Cols.nytdsection, article.getNytdsection());
        res.put(DbSchema.Cols.published_date, article.getPublished_date());
        res.put(DbSchema.Cols.section, article.getSection());
        res.put(DbSchema.Cols.source, article.getSource());
        res.put(DbSchema.Cols.subsection, article.getSubsection());
        res.put(DbSchema.Cols.title, article.getTitle());
        res.put(DbSchema.Cols.updated, article.getUpdated());
        res.put(DbSchema.Cols.uri, article.getUri());
        res.put(DbSchema.Cols.url, article.getUrl());

        return res;
    }



    public void DeleteArticle(Article article) {
        String idstring=String.valueOf(article.getId());
        favoritesIdList.remove(article.getId());


        int result=mDatabase.delete(DbSchema.NAME,DbSchema.Cols.id +" = "+article.getId(),null);
        Log.d("DbLab","Deleted rows :"+result);
    }


    public DbCursorWrapper queryArticle(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(DbSchema.NAME, null, whereClause, whereArgs, null, null, null);
        return new DbCursorWrapper(cursor);
    }




}
