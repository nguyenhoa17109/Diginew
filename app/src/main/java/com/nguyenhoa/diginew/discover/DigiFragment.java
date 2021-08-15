package com.nguyenhoa.diginew.discover;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.common.MyList;
import com.nguyenhoa.diginew.model.News;
import com.nguyenhoa.diginew.model.OtherApp;

import java.io.InputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DigiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DigiFragment extends Fragment {
    private Button btOpen;
    private TextView tv;
    private ImageView iv;
    private int index;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DigiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DigiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DigiFragment newInstance(String param1, String param2) {
        DigiFragment fragment = new DigiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_digi, container, false);

        init(view);

        Bundle bundle = getArguments();
        index = bundle.getInt("digi");

        setTopic(index);
        return view;
    }

    private void init(View view){
        btOpen = view.findViewById(R.id.btOpen);
        tv = view.findViewById(R.id.tvDigiMovie);
        iv = view.findViewById(R.id.ivDigiMovie);
    }

    private void setTopic(int index) {
        OtherApp app = MyList.list_dis.get(index);

        iv.setImageResource(app.getImg());
        tv.setMovementMethod(new ScrollingMovementMethod());
        try {
            Resources res = getResources();
            InputStream in_s = res.openRawResource(app.getContent());
            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            tv.setText(new String(b));
        } catch (Exception e) {
            tv.setText("Error: can't show content.");
        }

        btOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(app.getLink()));
                startActivity(intent);
            }
        });
    }
}