package com.nguyenhoa.diginew.common;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.LruCache;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.categories.ApiService;
import com.nguyenhoa.diginew.categories.CategoriesFragment;
import com.nguyenhoa.diginew.categories.ProvincesAdapter;
import com.nguyenhoa.diginew.categories.ProvincesBottomSheet;
import com.nguyenhoa.diginew.model.Account;
import com.nguyenhoa.diginew.model.Comment;
import com.nguyenhoa.diginew.model.Keyword;
import com.nguyenhoa.diginew.model.NLike;
import com.nguyenhoa.diginew.model.NSave;
import com.nguyenhoa.diginew.model.News;
import com.nguyenhoa.diginew.model.OtherApp;
import com.nguyenhoa.diginew.model.Province;
import com.nguyenhoa.diginew.model.Tag;
import com.nguyenhoa.diginew.model.TagNews;
import com.nguyenhoa.diginew.model.Topic;
import com.nguyenhoa.diginew.model.User;
import com.nguyenhoa.diginew.splash.Splash;
import com.nguyenhoa.diginew.splash.Splash1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyList extends Application {
    public static Account account;
    public static ArrayList<Topic> listTopic;
    public static ArrayList<Topic> list_Fv, list_unFv;
    public static ArrayList<News> listNews, listDownload, listSave, listLike, listText;
    public static ArrayList<Tag> listTag;
    public static ArrayList<Province> listProvince;
    public static ArrayList<OtherApp> list_dis;
    public static ArrayList<Comment> list_Cmt;
    public static ArrayList<Keyword> listKey, listRecentKey, listHotKey;
    public static ArrayList<ArrayList<News>> lists_audio, lists_video_as_topic, lists_info;
    public static String[] spinnerTime = {"Năm nay", "2020", "2019", "2018", "2017"};
    public static String today = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    private static final int NUMBER_TOPIC = 9;
    public static SQLiteDigi sqLite;
    public static final String PREFER_NAME = "sqlOnce";
    SharedPreferences preferences;
    boolean isFirst;

    @Override
    public void onCreate() {
        super.onCreate();
        account = new Account("Nguyen Van A", "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg", "Ha Noi"
                , "13/02/1989", "01294582");
//        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
//        // Use 1/8th of the available memory for this memory cache.
//        final int cacheSize = maxMemory / 3;
//
//        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
//            @Override
//            protected int sizeOf(String key, Bitmap bitmap) {
//                // The cache size will be measured in kilobytes rather than
//                // number of items.
//                return bitmap.getByteCount() / 1024;
//            }
//        };

        sqLite = new SQLiteDigi(getApplicationContext());

        list_Fv = new ArrayList<>();
        list_unFv = new ArrayList<>();
        list_Cmt = new ArrayList<>();

        preferences = getSharedPreferences(PREFER_NAME, MODE_PRIVATE);
        isFirst = preferences.getBoolean(PREFER_NAME, true);

        if(isFirst) {
            runOnce();

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(PREFER_NAME, false);
            editor.commit();
        }
        else {
            listNews = setListNews();

            listRecentKey = sqLite.getRecentKey();
            listHotKey = sqLite.getHotKey();

            listTag = sqLite.getAllTag();
            listProvince = sqLite.getAllProvince();
            listTopic = sqLite.getAllTopic();
            list_dis = sqLite.getAllOtherApp();

            lists_video_as_topic = setDataVideo(listNews);
//        listRelevant = sqLite.getAllNewsRelevant()

//        listsText = setDataText(sqLite.getAllNewsByType("text"));
//        lists_audio = setDataAudio(listNews);
//        lists_info = setDataInfo(listNews);
//        lists_video = setDataVideo(listNews);

//        listOperation = new ArrayList<>();
//        listOperation.add(new Operation(listNews.get(0), "02/08/2021", false, true, false));
//        listOperation.add(new Operation(listNews.get(1), "02/08/2021", false, true, true));
//        listOperation.add(new Operation(listNews.get(3), "01/08/2021", false, true, false));

            listText = sqLite.getAllNewsByType("textnews");
            listDownload = new ArrayList<>();
            listSave = new ArrayList<>();
            listLike = new ArrayList<>();
            for(News operation:listText){
                if(!operation.getDateDown().equals(""))
                    listDownload.add(operation);
                if(!operation.getDateSave().equals(""))
                    listSave.add(operation);
                if(!operation.getDateLike().equals(""))
                    listLike.add(operation);
            }
        }
    }

    private ArrayList<News> setListNews() {
        ArrayList<News> list = sqLite.getAllNews();
        for(int i=0; i<list.size(); i++){
            ArrayList<Tag> lst = sqLite.getAllTagByNews(list.get(i).getId());
            list.get(i).setListTag(lst);
        }
        return list;
    }

//    public static Bitmap getBitmapFromMemoryCache(String key){
//        return mMemoryCache.get(key);
//    }
//    public static void setBitmapFromMemoryCache(String key, Bitmap bitmap){
//        if(getBitmapFromMemoryCache(key) == null){
//            mMemoryCache.put(key, bitmap);
//        }
//    }

    private void runOnce() {

        sqLite.addAccount(account);

        sqLite.addKeyword(new Keyword("Covid-19", 19, "06/08/2021"));
        sqLite.addKeyword(new Keyword("Viet Nam covid", 10, "06/08/2021"));
        sqLite.addKeyword(new Keyword("Virut", 10, "06/08/2021"));
        sqLite.addKeyword(new Keyword("Prime", 10, "07/08/2021"));
        sqLite.addKeyword(new Keyword("Breakdown", 10, "06/08/2021"));
        sqLite.addKeyword(new Keyword("Anthony Fauci", 10, "01/08/2021"));
        sqLite.addKeyword(new Keyword("Wisconsin", 10, "16/08/2021"));
        sqLite.addKeyword(new Keyword("Coronavirus", 10, "06/08/2021"));
        sqLite.addKeyword(new Keyword("Nepal", 10, "06/08/2021"));
        sqLite.addKeyword(new Keyword("Animal", 10, "06/08/2021"));
        sqLite.addKeyword(new Keyword("Washington", 10, "06/08/2021"));
        sqLite.addKeyword(new Keyword("Entertainment", 10, "06/08/2021"));

        sqLite.addTopic(new Topic("Đời sống", "https://cdn.pixabay.com/photo/2017/03/26/21/54/yoga-2176668_1280.jpg"));
        sqLite.addTopic(new Topic("Kinh tế", "https://cdn.pixabay.com/photo/2016/09/19/18/30/calculator-1680905_1280.jpg"));
        sqLite.addTopic(new Topic("Sức khỏe", "https://cdn.pixabay.com/photo/2016/08/10/20/26/blood-pressure-1584223_1280.jpg"));
        sqLite.addTopic(new Topic("Xã hội", "https://cdn.pixabay.com/photo/2014/12/17/21/55/smartphone-571961_1280.jpg"));
        sqLite.addTopic(new Topic("Khoa học", "https://cdn.pixabay.com/photo/2017/02/01/13/52/analysis-2030261_1280.jpg"));
        sqLite.addTopic(new Topic("Giải trí", "https://cdn.pixabay.com/photo/2015/11/22/19/04/crowd-1056764_1280.jpg"));
        sqLite.addTopic(new Topic("Công nghệ", "https://cdn.pixabay.com/photo/2015/06/24/15/45/hands-820272_1280.jpg"));
        sqLite.addTopic(new Topic("Thể thao", "https://cdn.pixabay.com/photo/2016/09/18/14/21/swimmer-1678307_1280.jpg"));
        sqLite.addTopic(new Topic("Tâm sự", "https://cdn.pixabay.com/photo/2013/10/13/18/53/heart-195147_1280.jpg"));
        listTopic = sqLite.getAllTopic();

        sqLite.addOtherApp(new OtherApp("DigiMovie", R.drawable.ic_digimovie, R.raw.digimovie
                , "https://play.google.com/store/apps/details?id=com.vnpt.media.digimovie&hl=en&gl=US"));
        sqLite.addOtherApp(new OtherApp("DigiClip", R.drawable.ic_digiclips, R.raw.digiclip
                , "https://play.google.com/store/apps/details?id=com.vnpt.media.digiclip&hl=en&gl=US"));
        sqLite.addOtherApp(new OtherApp("DigiMusic", R.drawable.ic_digimusic, R.raw.digimusic
                , "https://play.google.com/store/apps/details?id=com.vnpt.media&hl=en&gl=US"));
        sqLite.addOtherApp(new OtherApp("DigiHealth", R.drawable.ic_digihealth, R.raw.digihealth
                , "https://play.google.com/store/apps/details?id=com.vnpt.media.digihealth&hl=en&gl=US"));
        sqLite.addOtherApp(new OtherApp("MyTV", R.drawable.img_mytv, R.raw.mytv
                , "https://play.google.com/store/apps/details?id=vn.vnpt.media.mytvgame&hl=en&gl=US"));
        list_dis = sqLite.getAllOtherApp();

        sqLite.addTag(new Tag("Covid-19"));
        sqLite.addTag(new Tag("virus"));
        sqLite.addTag(new Tag("Animal"));
        listTag = sqLite.getAllTag();


        sqLite.addProvince(new Province( "Ha Noi"));
        sqLite.addProvince(new Province( "Ha Noi"));
        sqLite.addProvince(new Province( "Hai Phong"));
        sqLite.addProvince(new Province( "Ha Giang"));
        sqLite.addProvince(new Province( "Hai Duong"));
        listProvince = sqLite.getAllProvince();
        Log.d("ALO", listProvince.size()+"");


        addNewsInDB(new News("Vietnamnet", "18/08/2021", "3 kịch bản cho năm học mới tại TP HCM",
                200, 100, "https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AANrKQ1.img?h=818&w=1248&m=6&q=60&o=f&l=f",
                "Sở Giáo dục và Đào tạo TP HCM không tổ chức khai giảng, tựu trường năm học mới, tất cả học sinh trừ bậc mầm non sẽ học trực tuyến ít nhất 4-6 tuần đầu năm học.\n" +
                        "\n" +
                        "Ngày 18/8, các phương án và kế hoạch năm học 2021-2022 được Sở Giáo dục và Đào tạo trình UBND TP HCM. Sở nhận định năm học mới khó có thể bắt đầu bằng hình thức trực tiếp, chương trình dạy học trong 4-6 tuần đầu năm là online.\n" +
                        "\n" +
                        "Ở bậc trung học (kể cả giáo dục thường xuyên), học sinh được tổ chức lớp, hướng dẫn kỹ năng, phương pháp học tập trực tuyến và củng cố kiến thức từ ngày 1 đến 5/9. Từ ngày 6/9, học sinh bước vào chương trình chính thức.\n" +
                        "\n" +
                        "Với bậc tiểu học, việc tổ chức lớp... và học chính thức được lùi lại vào mốc thời gian từ ngày 8 đến 19/9 và sau 19/9.\n" +
                        "\n" +
                        "Riêng bậc mầm non, khi dịch bệnh được kiểm soát, học sinh mới có thể đến trường. Do đặc thù là dạy học trực tiếp (giữ, giáo dục và chăm sóc trẻ), nên có thể bắt đầu và kết thúc năm học theo khung thời gian riêng. Khi chưa thể học lại, các trường sẽ tổ chức cho giáo viên làm các đoạn phim ngắn hướng dẫn trẻ sinh hoạt, vui chơi, giáo dục kỹ năng.\n" +
                        "\n" +
                        "Hết khoảng thời gian trên, Sở Giáo dục và Đào tạo tiếp tục đưa ra 3 phương án tổ chức dạy học năm học mới.", "textnews",
                listTopic.get(4), "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
                "z", listProvince.get(1), list_Cmt, listTag, "12/08/2021", "10/08/2021", "", false));
        addNewsInDB(new News("Vietnamnet", "17/08/2021", "Biến thể Delta phủ bóng chuỗi cung ứng toàn cầu", 200
                , 100, "https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AANqppQ.img?h=828&w=1248&m=6&q=60&o=f&l=f","abc", "info",
                listTopic.get(6), "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
                "z", listProvince.get(1), list_Cmt, listTag, "", "", "", false));
        addNewsInDB(new News("Vietnamnet", "31/07/2021", "Vì sao các ca F0 cộng đồng tăng trở lại từ ngày 15-8?", 200
                , 100, "https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AANrIB3.img?h=778&w=1172&m=6&q=60&o=f&l=f&x=453&y=205","abc", "info",
                listTopic.get(3), "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
                "z", listProvince.get(1), list_Cmt, listTag, "", "", "", false));
        addNewsInDB(new News("Vietnamnet", "6", "Bắt tạm giam kẻ vượt chốt kiểm soát dịch, tấn công công an", 200
                , 100, "https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AANrUzh.img?h=808&w=1248&m=6&q=60&o=f&l=f&x=550&y=421","Ngày 18.8, Cơ quan CSĐT Công an TP.Phan Thiết (Bình Thuận) đã khởi tố vụ án, khởi tố bị can và thi hành lệnh bắt tạm giam đối với Lê Kim Luân (sinh năm 1994, ngụ huyện Hàm Thuận Bắc, Bình Thuận) về tội “Chống người thi hành công vụ”.\n" +
                "\n" +
                "Đây là đối tượng vượt chốt kiểm soát dịch tại TP.Phan Thiết và tấn công lực lượng Công an vào ngày 16.8.\n" +
                "\n" +
                "Cụ thể, vào trưa 16.8, Luân điều khiển xe máy 86K1-4427 lưu thông đến chốt kiểm soát dịch trên địa bàn phường Xuân An, TP.Phan Thiết thì lực lượng chức năng tại chốt ra hiệu lệnh dừng xe, kiểm tra giấy tờ lưu thông.", "textnews",
                listTopic.get(8), "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
                "z", listProvince.get(1), list_Cmt, listTag, "", "", "", false));
        addNewsInDB(new News("Vietnamnet", "17/08/2021", "Hà Nội Taliban 2.0 và những giấc mơ bị vỡ vụn của phụ nữ Afghanistan", 200
                , 100, "https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AANrHX6.img?h=808&w=1248&m=6&q=60&o=f&l=f&x=1650&y=1139","Taliban đã cam kết sẽ xây dựng một chính phủ cởi mở và bao trùm sau khi kiểm soát được Kabul nhưng hầu hết các nhà quan sát lo ngại rằng hầu như có rất ít sự thay đổi ở lực lượng này trong 20 năm qua và dự đoán các quy định hà khắc sẽ nhanh chóng quay lại Afghanistan.\n" +
                "Taliban 2.0: Những lời hứa có trở thành sự thật?\n" +
                "\n" +
                "\"Mạng sống, tài sản và danh dự sẽ không bị tổn hại và sẽ được các chiến binh bảo vệ\", người phát ngôn lực lượng Taliban Suhail Shaheen cho biết ngày 16/8. Đây là thông điệp gần đây nhất được lực lực này đưa ra nhằm đảm bảo với người dân Afghanistan rằng họ sẽ được an toàn.", "textnews",
                listTopic.get(2), "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
                "z", listProvince.get(2), list_Cmt, listTag, "12/08/2021", "10/08/2021", "", false));
        addNewsInDB(new News("Vietnamnet", "16/08/2021", "Hưng Yên: Thêm 400 công nhân được tiêm vaccine phòng COVID-19", 200
                , 100, "https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AANrJjL.img?h=360&w=540&m=6&q=60&o=f&l=f&x=260&y=260","abc", "audio",
                listTopic.get(0), "https://sampleswap.org/samples-ghost/PUBLIC%20DOMAIN%20MUSIC/2096[kb]Around-the-World-on-the-Phonograph-Thomas-Edison.mp3.mp3", "z"
                , listProvince.get(0), list_Cmt, listTag, "", "", "", false));
        addNewsInDB(new News("Vietnamnet", "16/08/2021", "Trump nêu lý do quân đội Afghanistan đầu hàng Taliban", 200
                , 100, "https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AANs9I1.img?h=750&w=1248&m=6&q=60&o=f&l=f&x=401&y=256","abc", "audio",
                listTopic.get(1), "https://sampleswap.org/samples-ghost/PUBLIC%20DOMAIN%20MUSIC/2096[kb]Around-the-World-on-the-Phonograph-Thomas-Edison.mp3.mp3", "z"
                , listProvince.get(0), list_Cmt, listTag, "", "", "", false));
        addNewsInDB(new News("Vietnamnet", "16/08/2021", "Bỏ tiền túi trồng vườn rau sạch tặng dân vùng dịch Hà Nội", 200
                , 100, "https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AANrR1F.img?h=936&w=1248&m=6&q=60&o=f&l=f&x=500&y=231","abc", "video",
                listTopic.get(2), "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
                "z", listProvince.get(1), list_Cmt, listTag, "", "", "", false));
        addNewsInDB(new News("Vietnamnet", "16/08/2021", "Hàng loạt người dùng Facebook tại Việt Nam bị khóa tài khoản", 200
                , 100, "https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AANrrOq.img?h=834&w=1152&m=6&q=60&o=f&l=f","abc", "video",
                listTopic.get(5), "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
                "z", listProvince.get(0), list_Cmt, listTag, "", "", "", false));
        listNews = sqLite.getAllNews();

        for(int i=0; i<listNews.size(); i++){
            News n = listNews.get(i);
            for(int j=0; j<n.getListTag().size(); j++)
                sqLite.addTagNews(new TagNews(n.getId(),
                        n.getListTag().get(j).getId()));
        }

    }

    private void addNewsInDB(News news){
        sqLite.addNews(news);
        int x = sqLite.getIdNewsByNews(news);
        for(int i=0; i<news.getListTag().size(); i++){
            sqLite.addTag(news.getListTag().get(i));
            Tag tag = sqLite.getTagByName(news.getListTag().get(i).getTag());
            sqLite.addTagNews(new TagNews(x, tag.getId()));
            Log.d("IDD", x+"");
        }
        User user = new User("https://cdn.pixabay.com/photo/2018/01/25/14/12/nature-3106213_1280.jpg","Nguyen B");
        User user1 = new User("https://cdn.pixabay.com/photo/2012/06/19/10/32/owl-50267_1280.jpg","Tran B");
        sqLite.addComment(new Comment(user, "abc", 9, false, "18/07/2021",
                false, x, 0));
        sqLite.addComment(new Comment(user1, "abc", 9, true, "18/07/2021",
                false, x, 1));
        sqLite.addComment(new Comment(user, "abc", 9, false, "18/08/2021",
                true, x, 2));

    }

//    private Bitmap setBitmap(int i){
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), i);
//        return bitmap;
//    }

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
        for(Topic topic:listTopic){
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

    public static ArrayList<ArrayList<News>> setListDown(ArrayList<News> listOperation) {
        ArrayList<ArrayList<News>> lists_operation = new ArrayList<>();
        Collections.sort(listOperation, Collections.reverseOrder());

        ArrayList<News> lst = new ArrayList<>();
        lst.add(listOperation.get(0));
        lists_operation.add(lst);
        int k=0;
        for(int i=1; i<listOperation.size(); i++){
            if(!listOperation.get(i-1).getDateDown().equals(listOperation.get(i).getDateDown())){
                k++;
                lst = new ArrayList<>();
                lists_operation.add(lst);
            }
            lists_operation.get(k).add(listOperation.get(i));
        }
        return lists_operation;
    }
    public static ArrayList<ArrayList<News>> setListLike(ArrayList<News> listOperation) {
        ArrayList<ArrayList<News>> lists_operation = new ArrayList<>();

        ArrayList<NLike> likes = new ArrayList<>();
        for(News news:listOperation)
            likes.add(new NLike(news));
        Collections.sort(likes, Collections.reverseOrder());

        ArrayList<News> lst = new ArrayList<>();
        lst.add(likes.get(0).news);
        lists_operation.add(lst);
        int k=0;
        for(int i=1; i<listOperation.size(); i++){
            if(!likes.get(i-1).news.getDateLike().equals(likes.get(i).news.getDateLike())){
                k++;
                lst = new ArrayList<>();
                lists_operation.add(lst);
            }
            lists_operation.get(k).add(likes.get(i).news);
        }
        return lists_operation;
    }
    public static ArrayList<ArrayList<News>> setListSave(ArrayList<News> listOperation) {
        ArrayList<ArrayList<News>> lists_operation = new ArrayList<>();

        ArrayList<NSave> likes = new ArrayList<>();
        for(News news:listOperation)
            likes.add(new NSave(news));
        Collections.sort(likes, Collections.reverseOrder());

        ArrayList<News> lst = new ArrayList<>();
        lst.add(likes.get(0).news);
        lists_operation.add(lst);
        int k=0;
        for(int i=1; i<listOperation.size(); i++){
            if(!likes.get(i-1).news.getDateSave().equals(likes.get(i).news.getDateSave())){
                k++;
                lst = new ArrayList<>();
                lists_operation.add(lst);
            }
            lists_operation.get(k).add(likes.get(i).news);
        }
        return lists_operation;
    }


    private ArrayList<ArrayList<News>> setDataText( ArrayList<News> list1){
        ArrayList<ArrayList<News>> lists = new ArrayList<>();
        for(int i=0; i<NUMBER_TOPIC; i++){
            lists.add(setList("textnews", i));
        }
        for(int i=0; i<list1.size(); i++){
//            if(list1.get(i).getType().equals("text")){
                //id topic
                int k = setTopic(list1.get(i));
                //set News to topic k
                lists.get(k).add(list1.get(i));
//            }
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

    private ArrayList<ArrayList<News>> setDataAsTopic(ArrayList<News> list1){
        ArrayList<ArrayList<News>> lists = new ArrayList<>();
        Topic topic = new Topic("Kinh tế", "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg");
        for(int i=0; i<NUMBER_TOPIC; i++){
            ArrayList<News> list = new ArrayList<News>();
            list.add(new News("Vietnamnet", "31/07/2021", "Hon 80 tan gao ung ho cho 2 'ATM gao' o Hà Nội",
                    200, 100, "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg","Covid-19", "textnews",  topic,
                    "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
                    "z", listProvince.get(0), list_Cmt, listTag, "12/08/2021", "10/08/2021", "", false));
            lists.add(list);
        }
        for(int i=0; i<list1.size(); i++){
            String s = list1.get(i).getTopic().getName();
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
        list1.add(new News("Vietnamnet", "31/07/2021", "Hon 80 tan gao ung ho cho 2 'ATM gao' o Hà Nội",
                200, 100, "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg","Covid-19", type,  listTopic.get(i),
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
                "z", listProvince.get(1), list_Cmt, listTag, "", "", "", false));
        return list1;
    }

    private int setTopic(News news){
        String s = news.getTopic().getName();
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
