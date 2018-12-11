package a59070108.kmitl.ac.th.mobilefinal;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    private SQLiteDatabase sqLiteDatabase;
    public DBHelper(Context context) {
        super(context, "plengDB.db", null, 1);
        this.context = context;

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FRIEND_TABLE =
                "CREATE TABLE user(userid VARCHAR(200) UNIQUE," +
                "name VARCHAR(200), " +
                "age INT(20)," +
                "password VARCHAR(200)" +
                ")";
        db.execSQL(CREATE_FRIEND_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_FRIEND_TABLE = "DROP TABLE IF EXISTS time";
        db.execSQL(DROP_FRIEND_TABLE);
        onCreate(db);
    }


    public void addData(User user) {
        try{
            sqLiteDatabase = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", user.getName());
            values.put("password", user.getPassword());
            values.put("userid", user.getUserId());
            values.put("age", user.getAge());
            sqLiteDatabase.insertOrThrow("user", null, values);
            Toast.makeText(context,"บันทึกข้อมูลสำเร็จ", Toast.LENGTH_SHORT).show();
            sqLiteDatabase.close();
        }catch (SQLiteConstraintException e){
            Log.i("Time", e.getMessage());
            Toast.makeText(context,"บันทึกข้อมูลไม่สำเร็จ", Toast.LENGTH_SHORT).show();
        }
    }





    public void updateTime(User user) {
        try{
            sqLiteDatabase = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", user.getName());
            values.put("password", user.getPassword());
            values.put("userid", user.getUserId());
            values.put("age", user.getAge());
            sqLiteDatabase.update("user", values, "userid=" + "'" + user.getUserId() + "'", null);
            Toast.makeText(context,"บันทึกข้อมูลสำเร็จ", Toast.LENGTH_SHORT).show();
            sqLiteDatabase.close();
        }catch (SQLiteConstraintException e){
            Log.i("Time", e.getMessage());
            Toast.makeText(context,"บันทึกข้อมูลไม่สำเร็จ", Toast.LENGTH_SHORT).show();
        }
    }






    public User getUser(String userId) {
        User user = new User();
        String whereClause = "userid = '"+userId+"'";
        try{
            sqLiteDatabase = this.getWritableDatabase();
            Cursor cursor = sqLiteDatabase.query
                    ("user", null, whereClause, null, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
            }

            while(!cursor.isAfterLast()) {
                user.setUserId(cursor.getString(0));
                user.setName(cursor.getString(1));
                user.setAge(cursor.getInt(2));
                user.setPassword(cursor.getString(3));
                cursor.moveToNext();
            }

            sqLiteDatabase.close();
        }catch (SQLiteConstraintException e){
            Log.i("Time", e.getMessage());
            Toast.makeText(context,"โหลดข้อมูลไม่สำเร็จ", Toast.LENGTH_SHORT).show();
        }

        return user;
    }
}
