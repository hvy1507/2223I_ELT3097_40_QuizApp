package com.project.quizapp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.quizapp.Interface.InterfaceHome;
import com.project.quizapp.Interface.InterfaceLevel;
import com.project.quizapp.R;

public class FragmentLevel extends Fragment {
    InterfaceLevel sendData;
    LinearLayout linearLayout1, linearLayout2, linearLayout3;
    InterfaceHome sendDataHome;
    Bundle bundle;
    String category = "";

    // get create dữ liệu
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
    }


    // tạo view dữ liệu
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_level, container, false);

        // Dữ liệu khác null thì
        if (bundle != null) {
            assert getArguments() != null;
            category = bundle.getString("monhoc");
        }

        linearLayout3 = view.findViewById(R.id.linnearLayout3);
        linearLayout2 = view.findViewById(R.id.linnearLayout2);
        linearLayout1 = view.findViewById(R.id.linnearLayout1);

        // Click vào layout sẽ truyền data gồm độ khó và tên môn
        linearLayout1.setOnClickListener(v -> sendData.sendLevelData("kho", category));

        linearLayout2.setOnClickListener(v -> sendData.sendLevelData("trungbinh", category));

        linearLayout3.setOnClickListener(v -> sendData.sendLevelData("de", category));

        return view;
    }

    // Kiểm tra xem context có phải là instance của interface tương ứng ko , nếu có thì gán giá trị của nó = context tương ứng
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof InterfaceLevel) {
            sendData = (InterfaceLevel) context;
        } else if (context instanceof InterfaceHome) {
            sendDataHome = (InterfaceHome) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sendData = null;
        sendDataHome = null;
    }

}
