package com.nguyenhoa.diginew.common;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.model.Account;
import com.nguyenhoa.diginew.model.Comment;
import com.nguyenhoa.diginew.model.News;
import com.nguyenhoa.diginew.model.Operation;
import com.nguyenhoa.diginew.model.OtherApp;
import com.nguyenhoa.diginew.model.Province;
import com.nguyenhoa.diginew.model.Topic;
import com.nguyenhoa.diginew.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class MyList extends Application {
    public static Account account;
    public static ArrayList<Topic> list;
    public static ArrayList<Topic> list_Fv, list_unFv;
    public static ArrayList<News> listNews;
    public static ArrayList<String> listTag;
    public static ArrayList<OtherApp> list_dis;
    public static ArrayList<Comment> list_Cmt;
    public static ArrayList<ArrayList<News>> listsText, lists_audio, lists_video, lists_info;
    public static ArrayList<Operation> listOperation, listDownload, listSave, listLike;
    public static ArrayList<ArrayList<Operation>> lists_operation;
    public static String[] spinnerTime = {"Năm nay", "2020", "2019", "2018", "2017"};
    public static String today = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    private static int NUMBER_TOPIC = 9;
    private static Province province;
    @Override
    public void onCreate() {
        super.onCreate();

        account = new Account("Nguyen Van A", R.drawable.ic_digimusic);

        list = new ArrayList<>();
        list.add(new Topic("Đời sống", R.drawable.sj_life));
        list.add(new Topic("Kinh tế", R.drawable.sj_business));
        list.add(new Topic("Sức khỏe", R.drawable.sj_health));
        list.add(new Topic("Xã hội", R.drawable.sj_all));
        list.add(new Topic("Khoa học", R.drawable.sj_science));
        list.add(new Topic("Giải trí", R.drawable.sj_entertainment));
        list.add(new Topic("Công nghệ", R.drawable.sj_technology));
        list.add(new Topic("Thể thao", R.drawable.sj_sport));
        list.add(new Topic("Tâm sự", R.drawable.sj_confidence));

        list_Fv = new ArrayList<>();
//        list_Fv.add(new Topic("Kinh tế", R.drawable.sj_business));
//        list_Fv.add(new Topic("Giải trí", R.drawable.sj_entertainment));
//        list_Fv.add(new Topic("Tâm sự", R.drawable.sj_confidence));

        list_unFv = new ArrayList<>();

        list_dis = new ArrayList<>();
        list_dis.add(new OtherApp("DigiMovie", R.drawable.ic_digimovie, R.raw.digimovie
                , "https://play.google.com/store/apps/details?id=com.vnpt.media.digimovie&hl=en&gl=US"));
        list_dis.add(new OtherApp("DigiClip", R.drawable.ic_digiclips, R.raw.digiclip
                , "https://play.google.com/store/apps/details?id=com.vnpt.media.digiclip&hl=en&gl=US"));
        list_dis.add(new OtherApp("DigiMusic", R.drawable.ic_digimusic, R.raw.digimusic
                , "https://play.google.com/store/apps/details?id=com.vnpt.media&hl=en&gl=US"));
        list_dis.add(new OtherApp("DigiHealth", R.drawable.ic_digihealth, R.raw.digihealth
                , "https://play.google.com/store/apps/details?id=com.vnpt.media.digihealth&hl=en&gl=US"));
        list_dis.add(new OtherApp("MyTV", R.drawable.img_mytv, R.raw.mytv
                , "https://play.google.com/store/apps/details?id=vn.vnpt.media.mytvgame&hl=en&gl=US"));

        list_Cmt = new ArrayList<>();
        list_Cmt.add(new Comment(new User(R.drawable.ic_account,"Nguyen Van A"),
                "abc", 9, false, "9", false));
        list_Cmt.add(new Comment(new User(R.drawable.ic_account,"Nguyen Van A"),
                "abc", 9, false, "9", true));

        listTag = new ArrayList<>();
        listTag.add("Covid-19");

        province = new Province("Ha Noi");
        listNews = new ArrayList<>();
        listNews.add(new News("Vietnamnet", "31/07/2021", "Hon 80 tan gao ung ho cho 2 'ATM gao' o Hà Nội",
                200, 100, R.drawable.sj_health,"Covid-19", "text",  "Kinh tế",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4", "z", province, list_Cmt, listTag));
        listNews.add(new News("Vietnamnet", "6", "Hon 80 tan gao ung ho cho 2 'ATM gao' o Thành phố Hai Duong", 200
                , 100, R.drawable.sj_life,"abc", "info",  "Thể thao",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4", "z", province, list_Cmt, listTag));
        listNews.add(new News("Vietnamnet", "31/07/2021", "Hon 80 tan gao ung ho cho 2 'ATM gao' o Hà Giang", 200
                , 100, R.drawable.sj_science,"abc", "info",  "Kinh tế",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4", "z", province, list_Cmt, listTag));
        listNews.add(new News("Vietnamnet", "6", "Hon 80 tan gao ung ho cho 2 'ATM gao' o Tỉnh Hà Giang", 200
                , 100, R.drawable.sj_confidence,"abc", "text",  "Kinh tế",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4", "z", province, list_Cmt, listTag));
        listNews.add(new News("Vietnamnet", "6", "Hon 80 tan gao ung ho cho 2 'ATM gao' o Cao Bằng", 200
                , 100, R.drawable.sj_science,"abc", "text",  "Đời sống",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4", "z", province, list_Cmt, listTag));
        listNews.add(new News("Vietnamnet", "6", "Suc khoe cua chung ta", 200
                , 100, R.drawable.sj_science,"abc", "audio",  "Sức khỏe",
                "https://sampleswap.org/samples-ghost/PUBLIC%20DOMAIN%20MUSIC/2096[kb]Around-the-World-on-the-Phonograph-Thomas-Edison.mp3.mp3", "z"
                , province, list_Cmt, listTag));
        listNews.add(new News("Vietnamnet", "6", "Hon 80 tan gao ung ho cho 2 'ATM gao' o tỉnh Cao Bằng", 200
                , 100, R.drawable.sj_science,"abc", "audio",  "Công nghệ",
                "https://sampleswap.org/samples-ghost/PUBLIC%20DOMAIN%20MUSIC/2096[kb]Around-the-World-on-the-Phonograph-Thomas-Edison.mp3.mp3", "z"
                , province, list_Cmt, listTag));
        listNews.add(new News("Vietnamnet", "6", "Hon 80 tan gao ung ho cho 2 'ATM gao' o Hà Nội", 200
                , 100, R.drawable.sj_confidence,"abc", "video",  "Giải trí",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4", "z", province, list_Cmt, listTag));
        listNews.add(new News("Vietnamnet", "6", "Hon 80 tan gao ung ho cho 2 'ATM gao' o Hà Nội", 200
                , 100, R.drawable.sj_science,"abc", "video",  "Tâm sự",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4", "z", province, list_Cmt, listTag));

        listsText = setDataText(listNews);
        lists_audio = setDataAudio(listNews);
        lists_info = setDataInfo(listNews);
        lists_video = setDataVideo(listNews);

        listOperation = new ArrayList<>();
        listOperation.add(new Operation(listNews.get(0), "02/08/2021", false, true, false));
        listOperation.add(new Operation(listNews.get(1), "02/08/2021", false, true, true));
        listOperation.add(new Operation(listNews.get(3), "01/08/2021", false, true, false));

        listDownload = new ArrayList<>();
        listSave = new ArrayList<>();
        listLike = new ArrayList<>();
        for(Operation operation:listOperation){
            if(operation.isDownload())
                listDownload.add(operation);
            else if(operation.isSave())
                listSave.add(operation);
            else
                listLike.add(operation);
        }
//        Log.d("KK", lists_operation.size()+"");
    }

    public static ArrayList<Topic> setListChoseSubjectFV(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences((context));
        Gson gson = new Gson();
        String json = sharedPreferences.getString("listSubjectFV", "");
        ArrayList<Topic> list_Fv = gson.fromJson(json, new TypeToken<ArrayList<Topic>>(){}.getType());

        return list_Fv;
    }

    public static ArrayList<Topic> setListUnFavor(ArrayList<Topic> list_Fv) {
        ArrayList<Topic> list_unFv = new ArrayList<>();
        Log.d("LL", list_Fv.get(0).getName()+"");
        for(Topic topic:list){
            if(check(topic, list_Fv))
                list_unFv.add(topic);
        }
        return list_unFv;
    }
    private static boolean check(Topic topic, ArrayList<Topic> list){
        for(Topic topic1:list){
            if(topic.getName().equals(topic1.getName()))
                return false;
        }
        return true;
    }

    public static ArrayList<ArrayList<Operation>> setListOp(ArrayList<Operation> listOperation) {
        ArrayList<ArrayList<Operation>> lists_operation = new ArrayList<>();
        Collections.sort(listOperation, Collections.reverseOrder());
        ArrayList<Operation> lst = new ArrayList<>();
        lst.add(listOperation.get(0));
        lists_operation.add(lst);
        int k=0;
        for(int i=1; i<listOperation.size(); i++){
            if(!listOperation.get(i-1).getDate().equals(listOperation.get(i).getDate())){
                k++;
                lst = new ArrayList<>();
                lists_operation.add(lst);
            }
            lists_operation.get(k).add(listOperation.get(i));
        }
        return lists_operation;
    }

    private ArrayList<ArrayList<News>> setDataText( ArrayList<News> list1){
        ArrayList<ArrayList<News>> lists = new ArrayList<>();
        for(int i=0; i<NUMBER_TOPIC; i++){
            lists.add(setList("text", i));
        }
        for(int i=0; i<list1.size(); i++){
            if(list1.get(i).getType().equals("text")){
                //id topic
                int k = setTopic(list1.get(i));
                //set News to topic k
                lists.get(k).add(list1.get(i));
            }
        }
        return lists;
    }

    private ArrayList<ArrayList<News>> setDataVideo(ArrayList<News> list1){
        ArrayList<ArrayList<News>> lists = new ArrayList<>();
        for(int i=0; i<NUMBER_TOPIC; i++){
            lists.add(setList("video", i));
        }
        for(int i=0; i<list1.size(); i++){
            if(list1.get(i).getType().equals("video")){
                //id topic
                int k = setTopic(list1.get(i));
                //set News to topic k
                lists.get(k).add(list1.get(i));
            }
        }
        return lists;
    }

    private ArrayList<ArrayList<News>> setDataAudio(ArrayList<News> list1){
        ArrayList<ArrayList<News>> lists = new ArrayList<>();
        for(int i=0; i<NUMBER_TOPIC; i++){
            lists.add(setList("audio", i));
        }
        for(int i=0; i<list1.size(); i++){
            if(list1.get(i).getType().equals("audio")){
                //id topic
                int k = setTopic(list1.get(i));
                //set News to topic k
                lists.get(k).add(list1.get(i));
            }
        }
        return lists;
    }

    private ArrayList<ArrayList<News>> setDataInfo(ArrayList<News> list1){
        ArrayList<ArrayList<News>> lists = new ArrayList<>();
        for(int i=0; i<NUMBER_TOPIC; i++){
            lists.add(setList("info", i));
        }
        for(int i=0; i<list1.size(); i++){
            if(list1.get(i).getType().equals("info")){
                //id topic
                int k = setTopic(list1.get(i));
                //set News to topic k
                lists.get(k).add(list1.get(i));
            }
        }
        return lists;
    }

    private ArrayList<ArrayList<News>> setDataAsTopic(ArrayList<Topic> list2, ArrayList<News> list1){
        ArrayList<ArrayList<News>> lists = new ArrayList<>();
        for(int i=0; i<NUMBER_TOPIC; i++){
            ArrayList<News> list = new ArrayList<News>();
            list.add(new News("Vietnamnet", "31/07/2021", "Hon 80 tan gao ung ho cho 2 'ATM gao' o Hà Nội",
                    200, 100, R.drawable.sj_health,"Covid-19", "text",  "Kinh tế",
                    "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
                    "z", province, list_Cmt, listTag));
            lists.add(list);
        }
        for(int i=0; i<list1.size(); i++){
            String s = list1.get(i).getTopic();
            switch (s){
                case "Đời sống":
                    lists.get(0).add(list1.get(i));
                    break;
                case "Kinh tế":
                    lists.get(1).add(list1.get(i));
                    break;
                case "Sức khỏe":
                    lists.get(2).add(list1.get(i));
                    break;
                case "Xã hội":
                    lists.get(3).add(list1.get(i));
                    break;
                case "Khoa học":
                    lists.get(4).add(list1.get(i));
                    break;
                case "Giải trí":
                    lists.get(5).add(list1.get(i));
                    break;
                case "Công nghệ":
                    lists.get(6).add(list1.get(i));
                    break;
                case "Thể thao":
                    lists.get(7).add(list1.get(i));
                    break;
                case "Tâm sự":
                    lists.get(8).add(list1.get(i));
                    break;
            }
        }
        return lists;
    }

    //set list for each topic
    private ArrayList<News> setList(String type, int i){
        ArrayList<News> list1 = new ArrayList<News>();
        list1.add(new News("Vietnamnet", "6", "Hon 80 tan gao ung ho cho 2 'ATM gao' o Da Nang", 200
                , 100, R.drawable.sj_confidence,"abc", type,  list.get(i).getName(),
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4", listTag));
        return list1;
    }

    private int setTopic(News news){
        String s = news.getTopic();
        switch (s){
            case "Đời sống":
                return 0;
            case "Kinh tế":
                return 1;
            case "Sức khỏe":
                return 2;
            case "Xã hội":
                return 3;
            case "Khoa học":
                return 4;
            case "Giải trí":
                return 5;
            case "Công nghệ":
                return 6;
            case "Thể thao":
                return 7;
            case "Tâm sự":
                return 8;
            default:
                return 0;
        }
    }
}
