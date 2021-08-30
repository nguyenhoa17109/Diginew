package com.nguyenhoa.diginew.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.nguyenhoa.diginew.model.Account;
import com.nguyenhoa.diginew.model.Comment;
import com.nguyenhoa.diginew.model.Keyword;
import com.nguyenhoa.diginew.model.News;
import com.nguyenhoa.diginew.model.OtherApp;
import com.nguyenhoa.diginew.model.Packet;
import com.nguyenhoa.diginew.model.Province;
import com.nguyenhoa.diginew.model.Tag;
import com.nguyenhoa.diginew.model.TagNews;
import com.nguyenhoa.diginew.model.Topic;
import com.nguyenhoa.diginew.model.User;

import java.util.ArrayList;

public class SQLiteDigi extends SQLiteOpenHelper {

    public SQLiteDigi(@Nullable Context context) {
        super(context, Base.DATABASE_NAME,null, Base.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String createTblAccount = "CREATE TABLE " + Base.AccountTable.TABLE_NAME + "(" +
                Base.AccountTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Base.AccountTable.COLUMN_NAME + " TEXT," +
                Base.AccountTable.COLUMN_IMG + " TEXT," +
                Base.AccountTable.COLUMN_ADDRESS+ " TEXT," +
                Base.AccountTable.COLUMN_BIRTH + " TEXT," +
                Base.AccountTable.COLUMN_PHONE + " TEXT);";
        db.execSQL(createTblAccount);

        final String createTblTopic = "CREATE TABLE " + Base.TopicTable.TABLE_NAME + "(" +
                Base.TopicTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Base.TopicTable.COLUMN_NAME + " TEXT, " +
                Base.TopicTable.COLUMN_IMG + " TEXT," +
                Base.TopicTable.COLUMN_SE+" BOOLEAN);";
        db.execSQL(createTblTopic);


        final String createTblProvince = "CREATE TABLE " + Base.ProvinceTable.TABLE_NAME + "(" +
                Base.ProvinceTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Base.ProvinceTable.COLUMN_NAME + " TEXT);";
        db.execSQL(createTblProvince);

        final String createTblKeyword = "CREATE TABLE " + Base.KeywordTable.TABLE_NAME + "(" +
                Base.KeywordTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Base.KeywordTable.COLUMN_KEY + " TEXT, " +
                Base.KeywordTable.COLUMN_NUM_ACCESS + " INTEGER," +
                Base.KeywordTable.COLUMN_DATE_ACCESS+ " TEXT);";
        db.execSQL(createTblKeyword);

        final String createTblOtherApp = "CREATE TABLE " + Base.OtherAppTable.TABLE_NAME + "(" +
                Base.OtherAppTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Base.OtherAppTable.COLUMN_NAME + " TEXT," +
                Base.OtherAppTable.COLUMN_IMG + " INTEGER," +
                Base.OtherAppTable.COLUMN_CONTENT + " INTEGER," +
                Base.OtherAppTable.COLUMN_LINK+" TEXT);";
        db.execSQL(createTblOtherApp);

        final String createTblNews = "CREATE TABLE " + Base.NewsTable.TABLE_NAME+ "(" +
                Base.NewsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Base.NewsTable.COLUMN_SOURCE + " TEXT, " +
                Base.NewsTable.COLUMN_TIME + " TEXT, " +
                Base.NewsTable.COLUMN_TITLE + " TEXT, " +
                Base.NewsTable.COLUMN_NUM_LIKE + " INTEGER, " +
                Base.NewsTable.COLUMN_NUM_CMT + " INTEGER, " +
                Base.NewsTable.COLUMN_IMG + " TEXT, " +
                Base.NewsTable.COLUMN_CONTENT + " TEXT, " +
                Base.NewsTable.COLUMN_TYPE + " TEXT, " +
                Base.NewsTable.COLUMN_URL + " TEXT, " +
                Base.NewsTable.COLUMN_AUDIO + " TEXT, " +
                Base.NewsTable.COLUMN_DATE_SAVE + " TEXT, " +
                Base.NewsTable.COLUMN_DATE_DOWNLOAD + " TEXT, " +
                Base.NewsTable.COLUMN_DATE_LIKE + " TEXT, " +
                Base.NewsTable.COLUMN_IS_LIKE + " BOOLEAN, " +
                Base.NewsTable.COLUMN_ID_PROVINCE + " INTEGER, " +
                Base.NewsTable.COLUMN_ID_TOPIC + " INTEGER, " +
                "FOREIGN KEY (" +Base.NewsTable.COLUMN_ID_PROVINCE + ") REFERENCES "+Base.ProvinceTable._ID+ "," +
                "FOREIGN KEY (" +Base.NewsTable.COLUMN_ID_TOPIC + ") REFERENCES "+Base.TopicTable._ID+ ");";
        db.execSQL(createTblNews);

        final String createTblPacket = "CREATE TABLE " + Base.PacketTable.TABLE_NAME+ "(" +
                Base.PacketTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Base.PacketTable.COLUMN_DATA + " TEXT, " +
                Base.PacketTable.COLUMN_ONNET + " TEXT, " +
                Base.PacketTable.COLUMN_INNET + " TEXT, " +
                Base.PacketTable.COLUMN_SMS + " TEXT, " +
                Base.PacketTable.COLUMN_MYTV + " TEXT, " +
                Base.PacketTable.COLUMN_CLIPS + " TEXT, " +
                Base.PacketTable.COLUMN_HOTNEWS + " TEXT, " +
                Base.PacketTable.COLUMN_MUSIC + " TEXT, " +
                Base.PacketTable.COLUMN_MOVIE + " TEXT, " +
                Base.PacketTable.COLUMN_DATAREMAINING + " TEXT);";
        db.execSQL(createTblPacket);

        final String createTblTag = "CREATE TABLE " + Base.TagTable.TABLE_NAME + "(" +
                Base.TagTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Base.TagTable.COLUMN_NAME + " TEXT);";
        db.execSQL(createTblTag);

        final String createTblTagNews = "CREATE TABLE " + Base.TagNewsTable.TABLE_NAME + "(" +
                Base.TagNewsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Base.TagNewsTable.COLUMN_ID_NEWS + " INTEGER, " +
                Base.TagNewsTable.COLUMN_ID_TAG + " INTEGER," +
                "FOREIGN KEY ("+Base.TagNewsTable.COLUMN_ID_TAG+") REFERENCES "+Base.TagTable._ID+"," +
                "FOREIGN KEY ("+Base.TagNewsTable.COLUMN_ID_NEWS+") REFERENCES "+Base.NewsTable._ID+");" ;
        db.execSQL(createTblTagNews);

        final String createTblUser= "CREATE TABLE " + Base.UserTable.TABLE_NAME+ "(" +
                Base.UserTable._ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                Base.UserTable.COLUMN_NAME + " TEXT," +
                Base.UserTable.COLUMN_IMG + " TEXT);";
        db.execSQL(createTblUser);

        final String createTblComment = "CREATE TABLE " + Base.CommentTable.TABLE_NAME + "(" +
                Base.CommentTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Base.CommentTable.COLUMN_ID_USER + " INTEGER," +
                Base.CommentTable.COLUMN_ID_NEWS + " INTEGER," +
                Base.CommentTable.COLUMN_CONTENT + " TEXT," +
                Base.CommentTable.COLUMN_LIKE + " INTEGER," +
                Base.CommentTable.COLUMN_ANSWER + " BOOLEAN," +
                Base.CommentTable.COLUMN_TIME + " TEXT," +
                Base.CommentTable.COLUMN_IS_LIKE + " BOOLEAN," +
                Base.CommentTable.COLUMN_POSITION + " INTEGER," +
                "FOREIGN KEY ("+Base.CommentTable.COLUMN_ID_USER+") REFERENCES "+Base.UserTable._ID+"," +
                "FOREIGN KEY ("+Base.CommentTable.COLUMN_ID_NEWS+") REFERENCES "+Base.NewsTable._ID+");";
        db.execSQL(createTblComment);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Base.AccountTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Base.TopicTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Base.OtherAppTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Base.NewsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Base.TagTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Base.TagNewsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Base.UserTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Base.CommentTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Base.ProvinceTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Base.KeywordTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Base.PacketTable.TABLE_NAME);
    }
    public boolean CheckIsDataAlreadyInDBorNot(String TableName,
                                                      String dbfield, String fieldValue) {
        SQLiteDatabase sqldb = getReadableDatabase();
        String Query = "Select * from " + TableName + " where " + dbfield + " = '" + fieldValue +"'";
        Cursor cursor = sqldb.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        sqldb.close();
        return true;
    }

    public boolean CheckDataNewsExist(News news) {
        SQLiteDatabase sqldb = getReadableDatabase();

        String Query = "Select * from " + Base.NewsTable.TABLE_NAME + " where " +
                Base.NewsTable.COLUMN_SOURCE + " = '" + news.getSource() +"'" + " AND " +
                Base.NewsTable.COLUMN_TIME + " = '" + news.getTimes() +"'" + " AND " +
                Base.NewsTable.COLUMN_TITLE + " = '" + news.getTitle() +"'" + " AND " +
                Base.NewsTable.COLUMN_IMG + " = '" + news.getImgs() +"'" + " AND " +
                Base.NewsTable.COLUMN_CONTENT + " = '" + news.getContent() +"'" + " AND " +
                Base.NewsTable.COLUMN_URL + " = '" + news.getUrl() +"'" + " AND " +
                Base.NewsTable.COLUMN_AUDIO + " = '" + news.getAudio() +"'";

        Cursor cursor = sqldb.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        sqldb.close();
        return true;
    }

    public boolean CheckDataAccountExist(Account account) {
        SQLiteDatabase sqldb = getReadableDatabase();

        String Query = "Select * from " + Base.AccountTable.TABLE_NAME + " where " +
                        Base.AccountTable.COLUMN_NAME + " = '" + account.getName() +"'" + " AND " +
                Base.AccountTable.COLUMN_IMG + " = '" + account.getImg() +"'" + " AND " +
                Base.AccountTable.COLUMN_ADDRESS + " = '" + account.getAddress() +"'" + " AND " +
                Base.AccountTable.COLUMN_BIRTH + " = '" + account.getBirth() +"'" + " AND " +
                Base.AccountTable.COLUMN_PHONE + " = '" + account.getPhone() +"'";
        Cursor cursor = sqldb.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        sqldb.close();
        return true;
    }

    public boolean CheckDataUserExist(User user) {
        SQLiteDatabase sqldb = getReadableDatabase();

        String Query = "Select * from " + Base.UserTable.TABLE_NAME + " where " +
                Base.UserTable.COLUMN_IMG + " = '" + user.getImgAccount() +"'" + " AND " +
                Base.UserTable.COLUMN_NAME + " = '" + user.getNameUser() +"'";
        Cursor cursor = sqldb.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        sqldb.close();
        return true;
    }

    public boolean CheckDataTagNewsExist(TagNews tagNews) {
        SQLiteDatabase sqldb = getReadableDatabase();
        String Query = "Select * from " + Base.TagNewsTable.TABLE_NAME + " where " +
                Base.TagNewsTable.COLUMN_ID_NEWS + " = '" + tagNews.getIdNews() +"'" + " AND " +
                Base.TagNewsTable.COLUMN_ID_TAG + " = '" + tagNews.getIdTag() +"'" ;
        Cursor cursor = sqldb.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public boolean CheckDataCommentExist(Comment comment) {
        SQLiteDatabase sqldb = getReadableDatabase();
        String Query = "Select * from " + Base.CommentTable.TABLE_NAME + " where " +
                Base.CommentTable.COLUMN_ID_NEWS + " = '" + comment.getIdNews() +"'" + " AND " +
                Base.CommentTable.COLUMN_ID_USER + " = '" + comment.getUser() +"'" + " AND " +
                Base.CommentTable.COLUMN_CONTENT + " = '" + comment.getContentCmt() +"'" + " AND " +
                Base.CommentTable.COLUMN_LIKE + " = '" + comment.getLike() +"'" + " AND " +
                Base.CommentTable.COLUMN_TIME + " = '" + comment.getTime() +"'" + " AND " +
                Base.CommentTable.COLUMN_POSITION + " = '" + comment.getPosition() +"'";
        Cursor cursor = sqldb.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public boolean CheckDataKeywordExist(Keyword keyword) {
        SQLiteDatabase sqldb = getReadableDatabase();

        String Query = "Select * from " + Base.KeywordTable.TABLE_NAME + " where " +
                Base.KeywordTable.COLUMN_KEY + " = '" + keyword.getKey() +"'" + " AND " +
                Base.KeywordTable.COLUMN_DATE_ACCESS + " = '" + keyword.getDateSearchNear() +"'" + " AND " +
                Base.KeywordTable.COLUMN_NUM_ACCESS + " = " + keyword.getNumAccess();
        Cursor cursor = sqldb.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        sqldb.close();
        return true;
    }

    public boolean CheckDataTagExist(Tag tag) {
        SQLiteDatabase sqldb = getReadableDatabase();
        String Query = "Select * from " + Base.TagTable.TABLE_NAME + " where " + Base.TagTable.COLUMN_NAME + " = '" + tag.getTag() +"'";
        Cursor cursor = sqldb.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        sqldb.close();
        return true;
    }

    public long addAccount(Account account){
        SQLiteDatabase r = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Base.AccountTable.COLUMN_NAME, account.getName());
        values.put(Base.AccountTable.COLUMN_IMG, account.getImg());
        values.put(Base.AccountTable.COLUMN_ADDRESS, account.getAddress());
        values.put(Base.AccountTable.COLUMN_BIRTH, account.getBirth());
        values.put(Base.AccountTable.COLUMN_PHONE, account.getPhone());

        return r.insert(Base.AccountTable.TABLE_NAME, null, values);

    }

    public long updateAccount(Account account){
        ContentValues values = new ContentValues();
        values.put(Base.AccountTable.COLUMN_NAME, account.getName());
        values.put(Base.AccountTable.COLUMN_IMG, account.getImg());
        values.put(Base.AccountTable.COLUMN_ADDRESS, account.getAddress());
        values.put(Base.AccountTable.COLUMN_BIRTH, account.getBirth());
        values.put(Base.AccountTable.COLUMN_PHONE, account.getPhone());

        String clause = Base.AccountTable._ID + " = ?";
        SQLiteDatabase db = getWritableDatabase();
        String[] whereArgs = {Integer.toString(account.getId())};
        return db.update(Base.AccountTable.TABLE_NAME, values, clause, whereArgs);
    }

    public int deleteAccount(int id) {
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(Base.AccountTable.TABLE_NAME,
                whereClause, whereArgs);
    }


    public long addTopic(Topic topic){
        SQLiteDatabase r = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Base.TopicTable.COLUMN_SE , topic.getSelected());
        values.put(Base.TopicTable.COLUMN_IMG , topic.getImg());
        values.put(Base.TopicTable.COLUMN_NAME , topic.getName());
        return r.insert(Base.TopicTable.TABLE_NAME , null, values);
    }

    public long addPacket(Packet packet){
        SQLiteDatabase r = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Base.PacketTable.COLUMN_DATA , packet.getData());
        values.put(Base.PacketTable.COLUMN_ONNET , packet.getOnNet());
        values.put(Base.PacketTable.COLUMN_INNET , packet.getInNet());
        values.put(Base.PacketTable.COLUMN_SMS , packet.getSms());
        values.put(Base.PacketTable.COLUMN_MYTV , packet.getMyTV());
        values.put(Base.PacketTable.COLUMN_CLIPS , packet.getClips());
        values.put(Base.PacketTable.COLUMN_HOTNEWS , packet.getHotNews());
        values.put(Base.PacketTable.COLUMN_MUSIC , packet.getMusic());
        values.put(Base.PacketTable.COLUMN_MOVIE , packet.getMovie());
        values.put(Base.PacketTable.COLUMN_DATAREMAINING , packet.getDataRemaining());

        return r.insert(Base.PacketTable.TABLE_NAME , null, values);
    }

    public long updateTopic(Topic topic){
        SQLiteDatabase r = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Base.TopicTable.COLUMN_NAME, topic.getName());
        values.put(Base.TopicTable.COLUMN_IMG, topic.getImg());
        values.put(Base.TopicTable.COLUMN_SE, topic.getSelected());
        String clause = Base.TopicTable._ID + " = ?";
        String[] whereArgs = {Integer.toString(topic.getId())};
        return r.update(Base.TopicTable.TABLE_NAME , values, clause, whereArgs);
    }

    public long deleteTopic(Topic topic){
        SQLiteDatabase r = getWritableDatabase();
        String clause = Base.TopicTable._ID + " = ?";
        String[] whereArgs = {Integer.toString(topic.getId())};
        //delete all news in this topic
        ArrayList<News> list = getAllNewsByTopic(topic.getId());
        for(News news:list){
            deleteNews(news);
        }
        return r.delete(Base.TopicTable.TABLE_NAME, clause, whereArgs);
    }

    public ArrayList<Topic> getAllTopic(){
        ArrayList<Topic> list = new ArrayList<>();
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.query(Base.TopicTable.TABLE_NAME, null, null, null,
                null, null, null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String img = cursor.getString(2);
            list.add(new Topic(id, name, img, false));
        }
        return list;
    }

    public Topic getTopicByID(int idTopic){
        SQLiteDatabase r = getReadableDatabase();
        String sql = "SELECT * FROM "+Base.TopicTable.TABLE_NAME+" topics WHERE topics."
                +Base.TopicTable._ID+" = "+idTopic;
        Cursor cursor = r.rawQuery(sql, null);
        Topic topic = new Topic();
        if(cursor.moveToFirst()){
            Boolean select = cursor.getInt(3) > 0;
            topic = new Topic(idTopic, cursor.getString(1),
                cursor.getString(2), select);
        }
        return topic;
    }
    public Topic getTopicByName(String name){
        SQLiteDatabase r = getReadableDatabase();
        String sql = "SELECT * FROM "+Base.TopicTable.TABLE_NAME+" topics WHERE topics."
                +Base.TopicTable.COLUMN_NAME+" = '"+name+"'";
        Cursor cursor = r.rawQuery(sql, null);
        Topic topic = new Topic();
        if(cursor.moveToFirst()){
            Boolean select = cursor.getInt(3) > 0;
            topic = new Topic(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), select);
        }
        return topic;
    }


    public long addOtherApp(OtherApp app){
        SQLiteDatabase r = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Base.OtherAppTable.COLUMN_NAME, app.getName());
        values.put(Base.OtherAppTable.COLUMN_IMG, app.getImg());
        values.put(Base.OtherAppTable.COLUMN_CONTENT, app.getContent());
        values.put(Base.OtherAppTable.COLUMN_LINK, app.getLink());
        return  r.insert(Base.OtherAppTable.TABLE_NAME, null, values);
    }

    public long updateOtherApp(OtherApp app){
        SQLiteDatabase r = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Base.OtherAppTable.COLUMN_NAME, app.getName());
        values.put(Base.OtherAppTable.COLUMN_IMG, app.getImg());
        values.put(Base.OtherAppTable.COLUMN_CONTENT, app.getContent());
        values.put(Base.OtherAppTable.COLUMN_LINK, app.getLink());
        String clause = Base.OtherAppTable._ID + " = ?";
        String[] whereArgs = {Integer.toString(app.getId())};
        return  r.update(Base.OtherAppTable.TABLE_NAME,  values, clause, whereArgs);
    }

    public long deleteOtherApp(OtherApp app){
        SQLiteDatabase r = getWritableDatabase();
        String clause = Base.OtherAppTable._ID + " = ?";
        String[] whereArgs = {Integer.toString(app.getId())};
        return  r.delete(Base.OtherAppTable.TABLE_NAME, clause, whereArgs);
    }

    public ArrayList<OtherApp> getAllOtherApp(){
        ArrayList<OtherApp> list = new ArrayList<>();
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.query(Base.OtherAppTable.TABLE_NAME, null, null, null,
                null, null, null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int img = cursor.getInt(2);
            int content = cursor.getInt(3);
            String link = cursor.getString(4);
            list.add(new OtherApp(id, name, img, content, link));
        }
        return list;
    }


    public long addNews(News news){
        SQLiteDatabase r = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Base.NewsTable.COLUMN_SOURCE, news.getSource());
        values.put(Base.NewsTable.COLUMN_TIME, news.getTimes());
        values.put(Base.NewsTable.COLUMN_TITLE, news.getTitle());
        values.put(Base.NewsTable.COLUMN_NUM_LIKE, news.getLikes());
        values.put(Base.NewsTable.COLUMN_NUM_CMT, news.getCmts());
        values.put(Base.NewsTable.COLUMN_IMG, news.getImgs());
        values.put(Base.NewsTable.COLUMN_CONTENT, news.getContent());
        values.put(Base.NewsTable.COLUMN_TYPE, news.getType());
        values.put(Base.NewsTable.COLUMN_URL, news.getUrl());
        values.put(Base.NewsTable.COLUMN_AUDIO, news.getAudio());
        values.put(Base.NewsTable.COLUMN_DATE_SAVE, news.getDateSave());
        values.put(Base.NewsTable.COLUMN_DATE_DOWNLOAD, news.getDateDown());
        values.put(Base.NewsTable.COLUMN_DATE_LIKE, news.getDateLike());
        values.put(Base.NewsTable.COLUMN_IS_LIKE, news.isLike());
        values.put(Base.NewsTable.COLUMN_ID_PROVINCE, news.getProvince().getId());
        values.put(Base.NewsTable.COLUMN_ID_TOPIC, news.getTopic().getId());

        //add province and topic
        if(!checkProvinceExist(news.getProvince()))
            addProvince(news.getProvince());
        if(!checkTopicExist(news.getTopic()))
            addTopic(news.getTopic());
        //add all cmt and add all tag
//        ArrayList<Comment> listCmt = news.getListComment();
//        ArrayList<Tag> listTag = news.getListTag();
//        for(Tag tag:listTag){
//            if(!checkTagExist(tag)){
//                addTag(tag);
//            }
//        }
//        for (Comment comment:listCmt){
//            comment.setPosition();
//            addComment(comment);
//        }

        return r.insert(Base.NewsTable.TABLE_NAME, null, values);
    }

    private boolean checkTagExist(Tag t) {
        ArrayList<Tag> listTag = getAllTag();
        for(Tag tag:listTag){
            if(tag.getTag().equals(t.getTag()))   return true;
        }
        return false;
    }
    private boolean checkProvinceExist(Province province){
        ArrayList<Province> list = getAllProvince();
        for(Province province1:list){
            if(province.getName().equals(province1.getName()))
                return true;
        }
        return false;
    }
    private boolean checkTopicExist(Topic topic){
        ArrayList<Topic> list = getAllTopic();
        for(Topic topic1:list){
            if(topic.getName().equals(topic1.getName()))
                return true;
        }
        return false;
    }

    public long updateNews(News news){
        SQLiteDatabase r = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Base.NewsTable.COLUMN_SOURCE, news.getSource());
        values.put(Base.NewsTable.COLUMN_TIME, news.getTimes());
        values.put(Base.NewsTable.COLUMN_TITLE, news.getTitle());
        values.put(Base.NewsTable.COLUMN_NUM_LIKE, news.getLikes());
        values.put(Base.NewsTable.COLUMN_NUM_CMT, news.getCmts());
        values.put(Base.NewsTable.COLUMN_IMG, news.getImgs());
        values.put(Base.NewsTable.COLUMN_CONTENT, news.getContent());
        values.put(Base.NewsTable.COLUMN_TYPE, news.getType());
        values.put(Base.NewsTable.COLUMN_URL, news.getUrl());
        values.put(Base.NewsTable.COLUMN_AUDIO, news.getAudio());
        values.put(Base.NewsTable.COLUMN_DATE_SAVE, news.getDateSave());
        values.put(Base.NewsTable.COLUMN_DATE_DOWNLOAD, news.getDateDown());
        values.put(Base.NewsTable.COLUMN_DATE_LIKE, news.getDateLike());
        values.put(Base.NewsTable.COLUMN_IS_LIKE, news.isLike());
        values.put(Base.NewsTable.COLUMN_ID_PROVINCE, news.getProvince().getId());
        values.put(Base.NewsTable.COLUMN_ID_TOPIC, news.getTopic().getId());

        String clause = Base.NewsTable._ID + " = ?";
        String[] whereArgs = {Integer.toString(news.getId())};
        return  r.update(Base.NewsTable.TABLE_NAME,  values, clause, whereArgs);
    }

    public long deleteNews(News news){
        SQLiteDatabase r = getWritableDatabase();
        String clause = Base.NewsTable._ID + " = ?";
        String[] whereArgs = {Integer.toString(news.getId())};
        //delete all cmt in this news and all tagNews have this news
        ArrayList<Comment> lstCmt = getAllCmtByNews(news.getId());
        ArrayList<TagNews> lstTagNews = getAllTagNewsByNews(news.getId());

        for(Comment comment:lstCmt){
            deleteComment(comment);
        }
        for(TagNews tagNews:lstTagNews){
            deleteTagNews(tagNews);
        }
        return  r.delete(Base.NewsTable.TABLE_NAME,  clause, whereArgs);
    }

    public ArrayList<News> getAllNews(){
        ArrayList<News> list = new ArrayList<>();
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.query(Base.NewsTable.TABLE_NAME, null, null, null,
                null, null, null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String source = cursor.getString(1);
            String time = cursor.getString(2);
            String title = cursor.getString(3);
            int numLike = cursor.getInt(4);
            int numCmt = cursor.getInt(5);
            String img = cursor.getString(6);
            String content = cursor.getString(7);
            String type = cursor.getString(8);
            String url = cursor.getString(9);
            String audio = cursor.getString(10);
            String dateSave = cursor.getString(11);
            String dateDown = cursor.getString(12);
            String dateLike = cursor.getString(13);
            boolean isLike = cursor.getInt(14) > 0;
            int idProvince = cursor.getInt(15);
            int idTopic = cursor.getInt(16);

            Province province = getProvinceByID(idProvince);
            Topic topic = getTopicByID(idTopic);

            //select tag by news
            ArrayList<Tag> lstTag = getAllTagByNews(id);
            //select cmt by news
            ArrayList<Comment> lstCmt = new ArrayList<>();
            News news = new News(id, source, time, title, numLike, numCmt, img, content, type, topic,
                    url, audio, province, lstCmt, lstTag, dateSave, dateDown, dateLike, isLike);
            list.add(news);
        }
        return list;
    }

    private News CNews(Cursor cursor){
        int id = cursor.getInt(cursor.getColumnIndex(Base.NewsTable._ID));
        String source = cursor.getString(cursor.getColumnIndex(Base.NewsTable.COLUMN_SOURCE));
        String time = cursor.getString(cursor.getColumnIndex(Base.NewsTable.COLUMN_TIME));
        String title = cursor.getString(cursor.getColumnIndex(Base.NewsTable.COLUMN_TITLE));
        int numLike = cursor.getInt(cursor.getColumnIndex(Base.NewsTable.COLUMN_NUM_LIKE));
        int numCmt = cursor.getInt(cursor.getColumnIndex(Base.NewsTable.COLUMN_NUM_CMT));
        String img = cursor.getString(cursor.getColumnIndex(Base.NewsTable.COLUMN_IMG));
        String content = cursor.getString(cursor.getColumnIndex(Base.NewsTable.COLUMN_CONTENT));
        String type = cursor.getString(cursor.getColumnIndex(Base.NewsTable.COLUMN_TYPE));
        String url = cursor.getString(cursor.getColumnIndex(Base.NewsTable.COLUMN_URL));
        String audio = cursor.getString(cursor.getColumnIndex(Base.NewsTable.COLUMN_AUDIO));
        String dateSave = cursor.getString(cursor.getColumnIndex(Base.NewsTable.COLUMN_DATE_SAVE));
        String dateDown = cursor.getString(cursor.getColumnIndex(Base.NewsTable.COLUMN_DATE_DOWNLOAD));
        String dateLike = cursor.getString(cursor.getColumnIndex(Base.NewsTable.COLUMN_DATE_LIKE));
        boolean isLike = cursor.getInt(cursor.getColumnIndex(Base.NewsTable.COLUMN_IS_LIKE)) > 0;
        int idProvince = cursor.getInt(cursor.getColumnIndex(Base.NewsTable.COLUMN_ID_PROVINCE));
        int idTopic = cursor.getInt(cursor.getColumnIndex(Base.NewsTable.COLUMN_ID_TOPIC));

        Province province = getProvinceByID(idProvince);
        Topic topic = getTopicByID(idTopic);
        //get tag by news
        ArrayList<Tag> lstTag = getAllTagByNews(id);
        //get cmt by news
        ArrayList<Comment> lstCmt = new ArrayList<>();
        News news = new News(id, source, time, title, numLike, numCmt, img, content, type, topic, url, audio
                , province, lstCmt, lstTag, dateSave, dateDown, dateLike, isLike);
        return news;
    }

    public News getNewsByID(int idNews) {
        String sql = "SELECT * FROM "+Base.NewsTable.TABLE_NAME+" news WHERE news."+Base.NewsTable._ID
                +" = "+idNews;
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(sql, null);
        News news = new News();
        if(cursor.moveToFirst())
            news = CNews(cursor);
        return news;
    }

    public int getIdNewsByNews(News news){
        String sql = "SELECT * FROM "+Base.NewsTable.TABLE_NAME+" news WHERE news."+Base.NewsTable.COLUMN_ID_PROVINCE
                +" = "+news.getProvince().getId()+" AND "+Base.NewsTable.COLUMN_ID_TOPIC+" = "+
                news.getTopic().getId()+" AND "+Base.NewsTable.COLUMN_TYPE+" = '"+news.getType()+
                "' AND "+Base.NewsTable.COLUMN_IS_LIKE+" = "+news.isLike()+" AND "+Base.NewsTable.COLUMN_DATE_SAVE+
                " = '"+news.getDateSave()+"' AND "+Base.NewsTable.COLUMN_DATE_LIKE+" = '"+news.getDateLike()
                +"' AND "+Base.NewsTable.COLUMN_DATE_DOWNLOAD+" = '"+news.getDateDown()+"' AND "+
                Base.NewsTable.COLUMN_TITLE+" = " +
                '"'+news.getTitle()+'"' +
                "AND "+Base.NewsTable.COLUMN_URL+
                " = '"+news.getUrl()+"' AND "+Base.NewsTable.COLUMN_CONTENT+" = '"+news.getContent()
                +"' AND "+Base.NewsTable.COLUMN_SOURCE+ " = '"+news.getSource()+"'";
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(sql, null);
        int k= 0;
        if(cursor.moveToFirst()) k = cursor.getInt(0);
        return k;
    }

    public ArrayList<News> getAllNewsByTag(int idTag){
        ArrayList<News> lstNews = new ArrayList<>();
        String query = "SELECT * FROM "+Base.NewsTable.TABLE_NAME+" news, " + Base.TagTable.TABLE_NAME
                +" tags, "+Base.TagNewsTable.TABLE_NAME+" tagnews WHERE tag."+Base.TagTable._ID+" = "
                +idTag+" AND tag."+Base.TagTable._ID+" = "+" tagmews."+Base.TagNewsTable.COLUMN_ID_TAG
                +" AND news."+Base.NewsTable._ID+" = tagnews."+Base.TagNewsTable.COLUMN_ID_NEWS;
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(query, null);
        while (cursor.moveToNext()){
            lstNews.add(CNews(cursor));
        }
        return lstNews;
    }

    public ArrayList<News> getAllNewsByTypeAsTopic(String topic, String type) {
        int idTopic = getTopicByName(topic).getId();
        String sql = "SELECT * FROM "+Base.NewsTable.TABLE_NAME+" WHERE "+Base.NewsTable.COLUMN_ID_TOPIC
                +" = "+idTopic+" AND "+Base.NewsTable.COLUMN_TYPE+" = '"+type+"'";
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(sql, null);
        ArrayList<News> list = new ArrayList<>();
        while(cursor.moveToNext()){
            News news = CNews(cursor);
            list.add(news);
        }

        return list;
    }

    public ArrayList<News> getAllNewsByProvince(int idProvince){
        ArrayList<News> list = new ArrayList<>();
        String sql = "SELECT * FROM "+Base.NewsTable.TABLE_NAME+" news WHERE news."+
                Base.NewsTable.COLUMN_ID_PROVINCE+" = "+idProvince;
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(sql, null);
        while (cursor.moveToNext()){
            list.add(CNews(cursor));
        }
        return list;
    }

    public ArrayList<News> getAllNewsByTopic(int id) {
        ArrayList<News> list = new ArrayList<>();
        String sql = "SELECT * FROM "+Base.NewsTable.TABLE_NAME+" news WHERE news."+
                Base.NewsTable.COLUMN_ID_TOPIC+" = "+id;
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(sql, null);
        while (cursor.moveToNext()){
            list.add(CNews(cursor));
        }
        return  list;
    }

    public ArrayList<News> getAllNewsByType(String type){
        ArrayList<News> list = new ArrayList<>();
        String sql = "SELECT * FROM "+Base.NewsTable.TABLE_NAME+" ne WHERE ne."+
                Base.NewsTable.COLUMN_TYPE+" = "+"'"+type+"'";
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(sql, null);
        while (cursor.moveToNext()){
            list.add(CNews(cursor));
        }
        return  list;
    }

    public ArrayList<News> getAllNewsRelevant(News news){
        ArrayList<News> list = new ArrayList<>();
        String sql = "SELECT * FROM "+Base.NewsTable.TABLE_NAME+" n WHERE n."+
                Base.NewsTable.COLUMN_TYPE+" = '"+news.getType()+"' AND n."+
                Base.NewsTable.COLUMN_ID_TOPIC+" = "+news.getTopic().getId()+" AND n."+
                Base.NewsTable.COLUMN_ID_PROVINCE+" = "+news.getProvince().getId();
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(sql, null);
        while (cursor.moveToNext()){
            list.add(CNews(cursor));
        }
        return  list;
    }

    public ArrayList<News> getAllNewsDownloaded(){
        ArrayList<News> list = new ArrayList<>();
        String sql = "SELECT * FROM "+Base.NewsTable.TABLE_NAME+" WHERE "+
                Base.NewsTable.COLUMN_DATE_DOWNLOAD+" != ''";
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(sql, null);
        while (cursor.moveToNext()){
            list.add(CNews(cursor));
        }
        return  list;
    }

    public ArrayList<News> getAllNewsSaved(){
        ArrayList<News> list = new ArrayList<>();
        String sql = "SELECT * FROM "+Base.NewsTable.TABLE_NAME+" WHERE "+
                Base.NewsTable.COLUMN_DATE_SAVE+" != ''";
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(sql, null);
        while (cursor.moveToNext()){
            list.add(CNews(cursor));
        }
        return  list;
    }

    public ArrayList<News> getAllNewsLiked(){
        ArrayList<News> list = new ArrayList<>();
        String sql = "SELECT * FROM "+Base.NewsTable.TABLE_NAME+" WHERE "+
                Base.NewsTable.COLUMN_DATE_LIKE+" != ''";
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(sql, null);
        while (cursor.moveToNext()){
            list.add(CNews(cursor));
        }
        return  list;
    }


    public long addTag(Tag tag){
        SQLiteDatabase r = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Base.TagTable.COLUMN_NAME, tag.getTag());
        return r.insert(Base.TagTable.TABLE_NAME, null, values);
    }

    public long updateTag(Tag tag){
        SQLiteDatabase r = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Base.TagTable.COLUMN_NAME, tag.getTag());
        String clause = Base.TagTable._ID + " = ?";
        String[] whereArgs = {Integer.toString(tag.getId())};
        return  r.update(Base.TagTable.TABLE_NAME,  values, clause, whereArgs);
    }

    public long deleteTag(Tag tag){
        SQLiteDatabase r = getWritableDatabase();
        String clause = Base.TagTable._ID + " = ?";
        String[] whereArgs = {Integer.toString(tag.getId())};
        //delete all tagNews have this tag
        ArrayList<TagNews> list = getAllTagNewsByTag(tag.getId());
        for (TagNews tagNews:list){
            deleteTagNews(tagNews);
        }
        return  r.delete(Base.TagTable.TABLE_NAME,  clause, whereArgs);
    }

    public ArrayList<Tag> getAllTag(){
        ArrayList<Tag> list = new ArrayList<>();
        String sql = "SELECT * FROM "+Base.TagTable.TABLE_NAME;
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(sql, null);
        while(cursor.moveToNext()){
            list.add(new Tag(cursor.getInt(0), cursor.getString(1)));
        }
        return list;
    }

    public ArrayList<Tag> getAllTagByNews(int idNews){
        ArrayList<Tag> lstTag = new ArrayList<>();
        String query = "SELECT * FROM " + Base.TagTable.TABLE_NAME +" tg WHERE tg."
                +Base.TagTable._ID +" IN (SELECT DISTINCT tag."+Base.TagNewsTable.COLUMN_ID_TAG
                +" FROM "+ Base.TagNewsTable.TABLE_NAME+" tag WHERE tag."
                +Base.TagNewsTable.COLUMN_ID_NEWS+" = "+idNews+")";
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(query, null);
        while(cursor.moveToNext()){
            lstTag.add(new Tag(cursor.getInt(0), cursor.getString(1)));
        }
        return lstTag;
    }

    public ArrayList<TagNews> getAllTagNewsByTag(int id){
        ArrayList<TagNews> list = new ArrayList<>();
        String sql = "SELECT * FROM "+Base.TagNewsTable.TABLE_NAME+" tags WHERE tags."
                +Base.TagNewsTable.COLUMN_ID_TAG+" = "+id;
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(sql, null);
        while(cursor.moveToNext()){
            list.add(new TagNews(cursor.getInt(0), cursor.getInt(1),
                    cursor.getInt(2)));
        }
        return list;
    }

    public Tag getTagByName(String name){
        String query = "SELECT * FROM " + Base.TagTable.TABLE_NAME +" WHERE "
                +Base.TagTable.COLUMN_NAME+" = '"+name+"'";
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(query, null);
        Tag tag = new Tag();
        if(cursor.moveToFirst())
            tag = new Tag(cursor.getInt(0), cursor.getString(1));
        return tag;
    }


    public long addTagNews(TagNews tagNews){
        SQLiteDatabase r = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Base.TagNewsTable.COLUMN_ID_TAG, tagNews.getIdTag());
        values.put(Base.TagNewsTable.COLUMN_ID_NEWS, tagNews.getIdNews());

        return r.insert(Base.TagNewsTable.TABLE_NAME, null, values);
    }

    public long updateTagNews(TagNews tagNews){
        SQLiteDatabase r = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Base.TagNewsTable.COLUMN_ID_TAG, tagNews.getIdTag());
        values.put(Base.TagNewsTable.COLUMN_ID_NEWS, tagNews.getIdNews());

        String clause = Base.TagNewsTable._ID + " = ?";
        String[] whereArgs = {Integer.toString(tagNews.getId())};
        return  r.update(Base.TagNewsTable.TABLE_NAME,  values, clause, whereArgs);
    }

    public long deleteTagNews(TagNews tagNews){
        SQLiteDatabase r = getWritableDatabase();
        String clause = Base.TagNewsTable._ID + " = ?";
        String[] whereArgs = {Integer.toString(tagNews.getId())};
        return  r.delete(Base.TagNewsTable.TABLE_NAME, clause, whereArgs);
    }

    public ArrayList<TagNews> getAllTagNewsByNews(int idNews){
        ArrayList<TagNews> list = new ArrayList<>();
        String sql = "SELECT * FROM "+Base.TagNewsTable.TABLE_NAME+" tagNs WHERE tagNs."+
                Base.TagNewsTable.COLUMN_ID_NEWS+" = "+idNews;
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(sql, null);
        while(cursor.moveToNext()){
            list.add(new TagNews(cursor.getInt(0), cursor.getInt(1)
                    , cursor.getInt(2)));
        }
        return list;
    }


    public long addUser(User user){
        SQLiteDatabase r = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Base.UserTable.COLUMN_NAME, user.getNameUser());
        values.put(Base.UserTable.COLUMN_IMG, user.getImgAccount());
        return r.insert(Base.UserTable.TABLE_NAME, null, values);
    }

    public long updateUser(User user){
        SQLiteDatabase r = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Base.UserTable.COLUMN_NAME, user.getNameUser());
        values.put(Base.UserTable.COLUMN_IMG, user.getImgAccount());
        String clause = Base.UserTable._ID + " = ?";
        String[] whereArgs = {Integer.toString(user.getId())};
        return  r.update(Base.UserTable.TABLE_NAME,  values, clause, whereArgs);
    }

    public long deleteUser(User user){
        SQLiteDatabase r = getWritableDatabase();
        String clause = Base.UserTable._ID + " = ?";
        String[] whereArgs = {Integer.toString(user.getId())};
        //delete all cmt of this user
        ArrayList<Comment> list = getAllCmtByUser(user.getId());
        for(Comment comment:list){
            deleteComment(comment);
        }
        return  r.delete(Base.UserTable.TABLE_NAME, clause, whereArgs);
    }

    public ArrayList<User> getAllUser(){
        ArrayList<User> list = new ArrayList<>();
        String sql = "SELECT * FROM "+Base.UserTable.TABLE_NAME;
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(sql, null);
        while (cursor.moveToNext()){
            list.add(new User(cursor.getInt(0), cursor.getString(2), cursor.getString(1)));
        }
        return list;
    }

    public int getIDUser(User user){
        String sql = "SELECT * FROM "+Base.UserTable.TABLE_NAME+" WHERE "+Base.UserTable.COLUMN_IMG
                +" = '"+user.getImgAccount()+"' AND "+Base.UserTable.COLUMN_NAME+
                " = '"+user.getNameUser()+"'";
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(sql, null);
        int k = 0;
        if(cursor.moveToFirst())
            k = cursor.getInt(0);
        return k;
    }

    public User getUserByID(int idUser) {
        String sql = "SELECT * FROM "+Base.UserTable.TABLE_NAME+" WHERE "+Base.UserTable._ID
                +" = "+idUser;
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(sql, null);
        User user = new User();
        if(cursor.moveToFirst())
            user = new User(idUser, cursor.getString(2), cursor.getString(1) );
        return user;
    }

    public ArrayList<Account> getAllAccount(){
        ArrayList<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM "+Base.AccountTable.TABLE_NAME;
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(sql, null);
        while (cursor.moveToNext()){
            list.add(new Account(cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5)));
        }
        return list;
    }

    public Account getAccountByID(int idAccount) {
        String sql = "SELECT * FROM "+Base.AccountTable.TABLE_NAME+" WHERE "+Base.AccountTable._ID
                +" = "+idAccount;
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(sql, null);
        Account account = new Account();
        if(cursor.moveToFirst())
            account = new Account(idAccount, cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5) );
        return account;
    }


//    public long addComment(Comment comment){
//        SQLiteDatabase r = getWritableDatabase();
//        if(!isUserExist(comment.getUser()))
//            addUser(comment.getUser());
//        int k = getIDUser(comment.getUser());
//        comment.getUser().setId(k);
//        return r.insert(Base.CommentTable.TABLE_NAME, null, putCmt(comment));
//    }
    public long addComment(Comment comment){
        SQLiteDatabase r = getWritableDatabase();
//        if(!CheckDataUserExist(comment.getUser()))
//            addUser(comment.getUser());
        int k = getIDUser(comment.getUser());
        comment.getUser().setId(k);
        return r.insert(Base.CommentTable.TABLE_NAME, null, putCmt(comment));
    }

//    private boolean isUserExist(User user){
//        ArrayList<User> list = getAllUser();
//        for(User user1:list){
//            if(user1.getImgAccount() == user.getImgAccount()
//                    && user1.getNameUser().equals(user.getNameUser())){
//                return true;
//            }
//
//        }
//        return false;
//    }

    public long updateComment(Comment comment){
        SQLiteDatabase r = getWritableDatabase();
        String clause = Base.CommentTable._ID + " = ?";
        String[] whereArgs = {Integer.toString(comment.getId())};
        return  r.update(Base.CommentTable.TABLE_NAME, putCmt(comment) , clause, whereArgs);
    }

    public long deleteComment(Comment comment){
        SQLiteDatabase r = getWritableDatabase();
        String clause = Base.CommentTable._ID + " = ?";
        String[] whereArgs = {Integer.toString(comment.getId())};
        return  r.delete(Base.CommentTable.TABLE_NAME, clause, whereArgs);
    }

    private ContentValues putCmt(Comment comment){
        ContentValues values = new ContentValues();
        values.put(Base.CommentTable.COLUMN_ID_NEWS, comment.getIdNews());
        values.put(Base.CommentTable.COLUMN_ID_USER, comment.getUser().getId());
        values.put(Base.CommentTable.COLUMN_CONTENT, comment.getContentCmt());
        values.put(Base.CommentTable.COLUMN_LIKE, comment.getLike());
        values.put(Base.CommentTable.COLUMN_ANSWER, comment.isAnswer());
        values.put(Base.CommentTable.COLUMN_TIME, comment.getTime());
        values.put(Base.CommentTable.COLUMN_IS_LIKE, comment.isLikeCmt());
        return values;
    }
    private Comment Ccmt(Cursor cursor){
        int id = cursor.getInt(0);
        int idUser = cursor.getInt(1);
        int idNews = cursor.getInt(2);
        String content = cursor.getString(3);
        int like_num = cursor.getInt(4);
        boolean isAnswer = cursor.getInt(5) > 0;
        String time = cursor.getString(6);
        boolean isLike = cursor.getInt(7) > 0;
        int position = cursor.getInt(8);
        User user = getUserByID(idUser);

        Comment comment = new Comment(id, user, content, like_num, isAnswer, time, isLike,
                idNews, position);
        return comment;
    }

    public ArrayList<Comment> getAllCmtByNews(int idNews){
        ArrayList<Comment> lstCmt = new ArrayList<>();
        String sql = "SELECT * FROM "+Base.CommentTable.TABLE_NAME+" cmts WHERE cmts."+
                Base.CommentTable.COLUMN_ID_NEWS+" = "+idNews+" ORDER BY "
                + Base.CommentTable.COLUMN_POSITION+" ASC";
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(sql, null);
        while (cursor.moveToNext()){

            lstCmt.add(Ccmt(cursor));
        }
        return lstCmt;
    }

    private ArrayList<Comment> getAllCmtByUser(int id) {
        ArrayList<Comment> list = new ArrayList<>();
        String sql = "SELECT * FROM "+Base.CommentTable.TABLE_NAME+" cmts WHERE cmts."+
                Base.CommentTable.COLUMN_ID_USER+" = "+id;
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(sql, null);
        while (cursor.moveToNext()){
            list.add(Ccmt(cursor));
        }
        return list;
    }


    public long addProvince(Province province){
        SQLiteDatabase r = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Base.ProvinceTable.COLUMN_NAME, province.getName());

        return r.insert(Base.ProvinceTable.TABLE_NAME, null, values);
    }

    public long updateProvince(Province province){
        SQLiteDatabase r = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Base.ProvinceTable.COLUMN_NAME, province.getName());

        String clause = Base.ProvinceTable._ID + " = ?";
        String[] whereArgs = {Integer.toString(province.getId())};
        return  r.update(Base.ProvinceTable.TABLE_NAME,  values, clause, whereArgs);
    }

    public long deleteProvince(Province province){
        SQLiteDatabase r = getWritableDatabase();
        String clause = Base.ProvinceTable._ID + " = ?";
        String[] whereArgs = {Integer.toString(province.getId())};
        //delete all news in this province
        ArrayList<News> list = getAllNewsByProvince(province.getId());
        for(News news:list){
            deleteNews(news);
        }

        return  r.delete(Base.ProvinceTable.TABLE_NAME, clause, whereArgs);
    }

    public ArrayList<Province> getAllProvince(){
        ArrayList<Province> list = new ArrayList<>();
        String sql = "SELECT * FROM "+Base.ProvinceTable.TABLE_NAME;
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor1 = r.rawQuery(sql, null);
        while(cursor1.moveToNext()){
            list.add(new Province(cursor1.getInt(0), cursor1.getString(1)));
        }
        return list;
    }

    public Province getProvinceByID(int idProvince){
        String sql = "SELECT * FROM "+Base.ProvinceTable.TABLE_NAME+" pr WHERE pr."
                +Base.ProvinceTable._ID+" = "+idProvince;
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor1 = r.rawQuery(sql, null);
        Province province = new Province();
        if(cursor1.moveToFirst())
            province = new Province(idProvince, cursor1.getString(1));
        return province;
    }


    public long addKeyword(Keyword key){
        SQLiteDatabase r = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Base.KeywordTable.COLUMN_KEY, key.getKey());
        values.put(Base.KeywordTable.COLUMN_NUM_ACCESS, key.getNumAccess());
        values.put(Base.KeywordTable.COLUMN_DATE_ACCESS, key.getDateSearchNear());
        return r.insert(Base.KeywordTable.TABLE_NAME, null, values);
    }

    public long updateKeyword(Keyword key){
        SQLiteDatabase r = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Base.KeywordTable.COLUMN_KEY, key.getKey());
        values.put(Base.KeywordTable.COLUMN_NUM_ACCESS, key.getNumAccess());
        values.put(Base.KeywordTable.COLUMN_DATE_ACCESS, key.getDateSearchNear());

        String clause = Base.KeywordTable._ID + " = ?";
        String[] whereArgs = {Integer.toString(key.getId())};
        return  r.update(Base.KeywordTable.TABLE_NAME,  values, clause, whereArgs);
    }

    public long deleteKeyword(Keyword key){
        SQLiteDatabase r = getWritableDatabase();
        String clause = Base.KeywordTable._ID + " = ?";
        String[] whereArgs = {Integer.toString(key.getId())};
        return  r.delete(Base.KeywordTable.TABLE_NAME, clause, whereArgs);
    }

    public Keyword getKeyByName(String s){
        String sql = "SELECT * FROM "+Base.KeywordTable.TABLE_NAME+" WHERE "+Base.KeywordTable.COLUMN_KEY
                +" = '"+s+"'";
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(sql, null);
        Keyword keyword = new Keyword();
        if (cursor.moveToFirst()){
            keyword = new Keyword(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)
            , cursor.getString(3));
        }
        return keyword;
    }

    public ArrayList<Keyword> getHotKey(){
        ArrayList<Keyword> list = new ArrayList<>();
        String sql = "SELECT * FROM "+Base.KeywordTable.TABLE_NAME+" ORDER BY "+
                Base.KeywordTable.COLUMN_NUM_ACCESS +" LIMIT 12";
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(sql, null);
        while (cursor.moveToNext()){
            list.add(new Keyword(cursor.getInt(0), cursor.getString(1),
                    cursor.getInt(2), cursor.getString(3)));
        }
        return list;
    }

    public ArrayList<Keyword> getRecentKey(){
        ArrayList<Keyword> list = new ArrayList<>();
        String sql = "SELECT * FROM "+Base.KeywordTable.TABLE_NAME+" ORDER BY "+
                Base.KeywordTable.COLUMN_DATE_ACCESS +" DESC LIMIT 12";
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(sql, null);
        while (cursor.moveToNext()){
            list.add(new Keyword(cursor.getInt(0),cursor.getString(1),
                    cursor.getInt(2), cursor.getString(3)));
        }
        return list;
    }

    public ArrayList<Keyword> getAllKey(){
        ArrayList<Keyword> list = new ArrayList<>();
        String sql = "SELECT * FROM "+Base.KeywordTable.TABLE_NAME;
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(sql, null);
        while (cursor.moveToNext()){
            list.add(new Keyword(cursor.getInt(0),cursor.getString(1),
                    cursor.getInt(2), cursor.getString(3)));
        }
        return list;
    }

    public ArrayList<Packet> getAllPacket(){
        ArrayList<Packet> list = new ArrayList<>();
        String sql = "SELECT * FROM "+Base.PacketTable.TABLE_NAME;
        SQLiteDatabase r = getReadableDatabase();
        Cursor cursor = r.rawQuery(sql, null);
        while (cursor.moveToNext()){
            list.add(new Packet(cursor.getString(1),
                    cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10)));
        }
        return list;
    }

    public void handleKey(String s){
        if(!checkKeyExist(s)){
            Keyword keyword = new Keyword(s, 1, MyList.today);
            addKeyword(keyword);
        }else{
            Keyword keyword = getKeyByName(s);
            keyword.setNumAccess(keyword.getNumAccess()+1);
            keyword.setDateSearchNear(MyClass.getNow());
            updateKeyword(keyword);
        }
    }

    private boolean checkKeyExist(String s) {
        ArrayList<Keyword> list = getAllKey();
        for(Keyword keyword1:list){
            if(s.equals(keyword1.getKey()))
                return true;
        }
        return false;
    }

}
