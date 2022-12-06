package com.project.quizapp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.quizapp.DataItem.Category;
import com.project.quizapp.Adapter.AdapterCategory;
import com.project.quizapp.Interface.IHomeData;
import com.project.quizapp.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {
    IHomeData sendDataHome;
    RecyclerView recyclerView;
    List<Category> categoryList = new ArrayList<>();

    // tạo view các môn học
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recycleView);

        // tạo các môn học

        Category category = new Category("Hoá Học", R.drawable.chemistry);
        Category category1 = new Category("Vật lý", R.drawable.physics);
        Category category2 = new Category("Sinh Học", R.drawable.biology);
        Category category3 = new Category("Tiếng Anh", R.drawable.englishh);
        Category category4 = new Category("Android",R.drawable.ic_launcher_foreground);
        Category category5 = new Category("Lịch Sử", R.drawable.history);
        // add các môn hoc vào view
        categoryList.clear();
        categoryList.add(category);
        categoryList.add(category1);
        categoryList.add(category2);
        categoryList.add(category3);
        categoryList.add(category4);
        categoryList.add(category5);

        AdapterCategory adapterSubjects = new AdapterCategory(getContext(), categoryList, sendDataHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapterSubjects);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IHomeData) {
            sendDataHome = (IHomeData) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sendDataHome = null;
    }
}