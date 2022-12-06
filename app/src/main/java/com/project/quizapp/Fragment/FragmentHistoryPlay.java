package com.project.quizapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.project.quizapp.Adapter.AdapterHistoryPlay;
import com.project.quizapp.RoomDatabase.AppDatabase;
import com.project.quizapp.DataItem.ItemDAO;
import com.project.quizapp.R;
import com.project.quizapp.DataItem.Result;

import java.util.List;

public class FragmentHistoryPlay extends Fragment {
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_play, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewHistory);

        // Khởi tạo database là một instance của Database
        AppDatabase database = Room.databaseBuilder(requireContext(), AppDatabase.class, "mydb").allowMainThreadQueries().build();

        // Tạo instance itemDao của interface ItemDao
        ItemDAO itemDAO = database.getItemDAO();

        // Lấy dữ liệu cho từng items của List Kết quả bằng cách truy cập đến phương thức getItems
        List<Result> items = itemDAO.getItems();
        System.out.println(items);

        // Ánh xạ view sau khi data từ list đc lấy ra và gán vào
        AdapterHistoryPlay adapterHistory = new AdapterHistoryPlay(getContext(), items);
        recyclerView.setAdapter(adapterHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}