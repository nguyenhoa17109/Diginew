package com.nguyenhoa.diginew.common;

import android.provider.BaseColumns;

public final class Base {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Digi2.db";

    public Base() {
    }

    public static class AccountTable implements BaseColumns{
        public static final String TABLE_NAME = "account";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_IMG = "img";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_BIRTH = "birth";
        public static final String COLUMN_PHONE = "phone";
    }

    public static class TopicTable implements BaseColumns{
        public static final String TABLE_NAME = "topics";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_IMG = "img";
        public static final String COLUMN_SE = "selected";
    }

    public static class OtherAppTable implements BaseColumns{
        public static final String TABLE_NAME = "others";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_IMG = "img";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_LINK = "links";
    }

    public static class NewsTable implements BaseColumns{
        public static final String TABLE_NAME = "news";
        public static final String COLUMN_SOURCE = "source";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_NUM_LIKE = "numLike";
        public static final String COLUMN_NUM_CMT = "numComment";
        public static final String COLUMN_IMG = "img";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_URL = "url";
        public static final String COLUMN_AUDIO = "audioLink";
        public static final String COLUMN_DATE_SAVE = "dateSave";
        public static final String COLUMN_DATE_DOWNLOAD = "dateDown";
        public static final String COLUMN_DATE_LIKE = "dateLike";
        public static final String COLUMN_IS_LIKE = "isLiked";
        public static final String COLUMN_ID_PROVINCE = "province";
        public static final String COLUMN_ID_TOPIC = "topic";
    }

    public static class TagTable implements BaseColumns{
        public static final String TABLE_NAME = "tags";
        public static final String COLUMN_NAME = "name";
    }

    public static class TagNewsTable implements BaseColumns{
        public static final String TABLE_NAME = "tagsNews";
        public static final String COLUMN_ID_NEWS = "idNews";
        public static final String COLUMN_ID_TAG = "idTag";
    }

    public static class UserTable implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_IMG = "img";
    }

    public static class CommentTable implements BaseColumns{
        public static final String TABLE_NAME = "comments";
        public static final String COLUMN_ID_USER = "user";
        public static final String COLUMN_ID_NEWS = "news";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_LIKE = "likes";
        public static final String COLUMN_ANSWER = "answer";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_IS_LIKE = "isLiked";
        public static final String COLUMN_POSITION = "position";
    }

    public static class ProvinceTable implements BaseColumns{
        public static final String TABLE_NAME = "provinces";
        public static final String COLUMN_NAME = "name";
    }

    public static class KeywordTable implements BaseColumns{
        public static final String TABLE_NAME = "keywords";
        public static final String COLUMN_KEY = "keys";
        public static final String COLUMN_NUM_ACCESS = "numAccess";
        public static final String COLUMN_DATE_ACCESS = "dateAccess";
    }

}
