package edward.example.com.BaseDatos;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import edward.example.com.BaseDatos.FeedReaderContract.FeedEntry;

public class DatosOpenHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES  =
            "CREATE TABLE IF NOT EXISTS " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry.COLUMN_NAME + " VARCHAR(250), " +
                    FeedEntry.COLUMN_DIREC + " VARCHAR(250), " +
                    FeedEntry.COLUMN_EMAIL + " VARCHAR(200), " +
                    FeedEntry.COLUMN_NUMBER + " VARCHAR(20))";


    /*
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_TITLE + " TEXT," +
                    FeedEntry.COLUMN_NAME_SUBTITLE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
    */
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DATOS.db";

    public DatosOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



        /* @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS CLIENTE (");
        sql.append("NOMBRE VARCHAR(250), ");
        sql.append("DIRECCION VARCHAR(250), ");
        sql.append("EMAIL VARCHAR(200), ");
        sql.append("TELEFONO VARCHAR(20)) ");

        sqLiteDatabase.execSQL(sql.toString());
    }*/

}
