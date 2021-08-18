package com.nguyenhoa.diginew.categories;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.model.Province;

import java.util.List;

public class ProvincesBottomSheet extends BottomSheetDialogFragment implements ProvincesAdapter.IClickListener {
    private List<Province> provinceList;
    private ProvincesAdapter.IClickListener iClickListener;

    public ProvincesBottomSheet(List<Province> provinceList, ProvincesAdapter.IClickListener iClickListener) {
        this.provinceList = provinceList;
        this.iClickListener = iClickListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), R.style.MaterialDialogSheet);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet_provinces, null);
        bottomSheetDialog.setContentView(view);

        RecyclerView rvProvince = view.findViewById(R.id.rvProvinces);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvProvince.setLayoutManager(linearLayoutManager);

        ProvincesAdapter provincesAdapter = new ProvincesAdapter(provinceList, this::clickItem);
        rvProvince.setAdapter(provincesAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        rvProvince.addItemDecoration(itemDecoration);
        provincesAdapter.notifyDataSetChanged();

        return bottomSheetDialog;
    }


    @Override
    public void clickItem(Province province) {
        iClickListener.clickItem(province);
    }
}
