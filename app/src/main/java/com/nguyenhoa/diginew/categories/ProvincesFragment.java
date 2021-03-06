package com.nguyenhoa.diginew.categories;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProvincesFragment extends Fragment {
    private Button btChooseProvince;
    private List<Province> provinceList = new ArrayList<>();
    private ProvincesBottomSheet provincesBottomSheet;
//    String linkProvinces = "https://provinces.open-api.vn/api/p/";

    public ProvincesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_provinces, container, false);


        btChooseProvince = view.findViewById(R.id.btChooseProvince);

//        provinceList = MyList.listProvince;

        provinceList = new ArrayList<>();

        btChooseProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataProvinces();
            }
        });

        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if ( provincesBottomSheet!=null && provincesBottomSheet.isVisible() ){
            provincesBottomSheet.onDestroy();
        }
    }

    private void getDataProvinces(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://provinces.open-api.vn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Province>> call = apiService.getNameProvinces();
        call.enqueue(new Callback<List<Province>>() {
            @Override
            public void onResponse(Call<List<Province>> call, Response<List<Province>> response) {
                if(response.isSuccessful()){
                    List<Province> list = response.body();
                    for(int i=0; i<list.size(); i++){
                        provinceList.add(new Province(list.get(i).getName()));
                    }

                    provincesBottomSheet = new ProvincesBottomSheet(provinceList, new ProvincesAdapter.IClickListener() {
                        @Override
                        public void clickItem(Province province) {
                            Bundle result = new Bundle();
                            result.putString("bundleKey", province.getName());
                            getParentFragmentManager().setFragmentResult("requestKey", result);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    provincesBottomSheet.dismiss();
                                }
                            },1500);
                        }
                    });
                    provincesBottomSheet.show(getFragmentManager(), provincesBottomSheet.getTag());
                }
            }
            @Override
            public void onFailure(Call<List<Province>> call, Throwable t) {
                Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();

            }
        });



//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, linkProvinces, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                provinceList.clear();
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        JSONObject object = response.getJSONObject(i);
//                        String city = object.getString("name");
//                        provinceList.add(new Province(city));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                provincesBottomSheet = new ProvincesBottomSheet(provinceList, new ProvincesAdapter.IClickListener() {
//                    @Override
//                    public void clickItem(Province province) {
//                        Toast.makeText(getContext(), province.getName(), Toast.LENGTH_SHORT).show();
//
//                        Bundle bundle = new Bundle();
//                        bundle.putString("province", province.getName());
//                        Fragment fragment = new CategoriesFragment.ChildFragment();
//                        fragment.setArguments(bundle);
//                        getFragmentManager().beginTransaction().replace(R.id.frChildCategory, fragment).commit();
//
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                provincesBottomSheet.dismiss();
//                            }
//                        },1500);
//                    }
//                });
//                provincesBottomSheet.show(getFragmentManager(), provincesBottomSheet.getTag());
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getContext(), "ERROR!", Toast.LENGTH_SHORT).show();
//            }
//        });
//        requestQueue.add(jsonArrayRequest);
    }

}