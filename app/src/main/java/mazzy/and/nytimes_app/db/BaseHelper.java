package mazzy.and.nytimes_app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseHelper extends SQLiteOpenHelper {

    private static final int VERSION=1;
    private static final String DATABASE_NAME= "articlebase.db";

    public BaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DbSchema.NAME + "(" +
                " _id integer primary key autoincrement, " +
                DbSchema.Cols.abst + ", " +
                DbSchema.Cols.adx_keywords + ", " +
                DbSchema.Cols.asset_id + ", " +
                DbSchema.Cols.byline + ", " +
                DbSchema.Cols.clmn + ", " +
                DbSchema.Cols.count_type + ", " +
                DbSchema.Cols.email_count + ", " +
                DbSchema.Cols.eta_id + ", " +
                DbSchema.Cols.id + ", " +
                DbSchema.Cols.nytdsection + ", " +
                DbSchema.Cols.published_date + ", " +
                DbSchema.Cols.section + ", " +
                DbSchema.Cols.source + ", " +
                DbSchema.Cols.subsection + ", " +
                DbSchema.Cols.title + ", " +
                DbSchema.Cols.type + ", " +
                DbSchema.Cols.updated + ", " +
                DbSchema.Cols.uri + ", " +
                DbSchema.Cols.url + ", " +
                DbSchema.Cols.imageUrl +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
