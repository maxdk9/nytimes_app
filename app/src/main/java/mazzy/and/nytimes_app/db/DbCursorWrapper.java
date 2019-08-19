package mazzy.and.nytimes_app.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import mazzy.and.nytimes_app.model.Article;
import mazzy.and.nytimes_app.model.Media;
import mazzy.and.nytimes_app.model.MediaMetadata;

public class DbCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public DbCursorWrapper(Cursor cursor) {
        super(cursor);
    }


    public Article GetArticle(){

        Article article = new Article();
        String abstr=getString(getColumnIndex(DbSchema.Cols.abst));
        String adx_keywords=getString(getColumnIndex(DbSchema.Cols.adx_keywords));
        Long asset_id=getLong(getColumnIndex(DbSchema.Cols.asset_id));
        String byline=getString(getColumnIndex(DbSchema.Cols.byline));
        String clmn=getString(getColumnIndex(DbSchema.Cols.clmn));
        String count_type=getString(getColumnIndex(DbSchema.Cols.count_type));
        Integer email_count=getInt(getColumnIndex(DbSchema.Cols.email_count));
        Integer eta_id=getInt(getColumnIndex(DbSchema.Cols.eta_id));
        Long id=getLong(getColumnIndex(DbSchema.Cols.id));
        String imageUrl=getString(getColumnIndex(DbSchema.Cols.imageUrl));
        String nytdsection=getString(getColumnIndex(DbSchema.Cols.nytdsection));
        String published_date=getString(getColumnIndex(DbSchema.Cols.published_date));
        String section=getString(getColumnIndex(DbSchema.Cols.section));
        String title=getString(getColumnIndex(DbSchema.Cols.title));


        article.setFavorite(true);
        article.setAbstr(abstr);
        article.setAdx_keywords(adx_keywords);
        article.setAsset_id(asset_id);
        article.setByline(byline);
        article.setColumn(clmn);
        article.setCount_type(count_type);
        article.setEmail_count(email_count);
        article.setEta_id(eta_id);
        article.setId(id);
        article.setTitle(title);
        article.setNytdsection(nytdsection);
        article.setPublished_date(published_date);
        article.setSection(section);
        if(!imageUrl.isEmpty()){
            Media[] mediaArray = new Media[1];
            Media media=new Media();
            mediaArray[0]=media;
            MediaMetadata[] mediaMetadataArray = new MediaMetadata[1];
            MediaMetadata mediaMetadata = new MediaMetadata();
            mediaMetadata.setUrl(imageUrl);
            mediaMetadataArray[0]=mediaMetadata;
            media.setMediaMetadata(mediaMetadataArray);
            article.setMedia(mediaArray);
        }




        return article;


    }


}
