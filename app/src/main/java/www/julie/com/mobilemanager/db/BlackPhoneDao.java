package www.julie.com.mobilemanager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;

import java.util.ArrayList;
import java.util.List;

import www.julie.com.mobilemanager.bean.BlackNumberInfo;

/**
 * Created by liuxu on 15/7/4.
 */
public class BlackPhoneDao {
    private BlackPhoneOpenHelper helper;

    public BlackPhoneDao(Context context) {
        this.helper = new BlackPhoneOpenHelper(context);
    }

    public Boolean add(String number, String mode) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("number", number);
        contentValues.put("mode", mode);
        long rowid = db.insert("black_info", null, contentValues);
        return rowid != -1;
    }

    public Boolean delete(String number) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int rowNum = db.delete("black_info", "number=?", new String[]{number});
        if (rowNum == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取总记录
     *
     * @return
     */
    public int getTotalNumber() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*) from black_info", null);
        cursor.moveToNext();
        int count = cursor.getInt(0);
        cursor.close();
        db.close();

        return count;
    }

    public String findNumber(String number) {
        String mode = "";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("black_info", new String[]{"mode"}, "number=?",
                new String[]{number}, null, null, null);
        if (cursor.moveToNext()) {
            mode = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return mode;
    }

    public List<BlackNumberInfo> findAll() {
        SQLiteDatabase db = helper.getReadableDatabase();
        List<BlackNumberInfo> blackNumberInfoList = new ArrayList<>();
        Cursor cursor = db.query("black_info", new String[]{"number", "mode"}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            BlackNumberInfo info = new BlackNumberInfo();
            info.setNumber(cursor.getString(0));
            info.setMode(cursor.getString(1));
            blackNumberInfoList.add(info);
        }
        cursor.close();
        db.close();
        SystemClock.sleep(3000);
        return blackNumberInfoList;

    }

    public List<BlackNumberInfo> findPar(int startIndex, int maxCount) {
        SQLiteDatabase db = helper.getReadableDatabase();
        List<BlackNumberInfo> blackNumberInfoList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select number, mode from black_info limit ? offset ?",
                new String[]{String.valueOf(maxCount),
                        String.valueOf(startIndex)});
        while (cursor.moveToNext()) {
            BlackNumberInfo info = new BlackNumberInfo();
            info.setNumber(cursor.getString(0));
            info.setMode(cursor.getString(1));
            blackNumberInfoList.add(info);
        }
        cursor.close();
        db.close();
        return blackNumberInfoList;
    }
}
