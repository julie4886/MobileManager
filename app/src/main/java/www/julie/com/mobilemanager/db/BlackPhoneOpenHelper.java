package www.julie.com.mobilemanager.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by liuxu on 15/7/4.
 */
public class BlackPhoneOpenHelper extends SQLiteOpenHelper {
    private final static String CREATE_TABLE = "create table black_info (black_id integer primary key autoincrement," +
            "number varchar(20)," +
            "mode varchar(2))";
    private final static String TABLE_NAME = "safe.db";

    public BlackPhoneOpenHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
