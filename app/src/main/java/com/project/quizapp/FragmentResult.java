package com.project.quizapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class FragmentResult extends Fragment {

    Bundle bundle;
    String result, question, category, level;
    LinearLayout linearLayout1, linearLayout2;
    IBackHome sendHome;
    TextView txtText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
    }


    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        txtText = view.findViewById(R.id.txtText1);
        linearLayout2 = view.findViewById(R.id.linnearLayout2);
        linearLayout1 = view.findViewById(R.id.linnearLayout1);

        if (bundle != null) {
            result = bundle.getString("result");
            question = bundle.getString("question");
            category = bundle.getString("monhoc");
            level = bundle.getString("level");

            txtText.setText(result + " / " + question);

            // share sheet
            linearLayout2.setOnClickListener(v -> {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Đáp án");
                    String shareMessage = "Tôi đã trả lời đúng " + result + " câu hỏi ở độ khó " + level + " và môn học " + category;
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "Hãy chọn một"));
                } catch (Exception e) {
                    //e.toString();
                }
            });
// Sau khi bấm nút hoàn thành , một lịch sử chơi được tạo ra và add vào tronng Lịch sử , sau đó trở về màn home bằng cách gọi interface
            // insert data sau khi hoàn thiện
            linearLayout1.setOnClickListener(v -> {
                AppDatabase database = Room.databaseBuilder(requireContext(), AppDatabase.class, "mydb").allowMainThreadQueries().build();
                ItemDAO itemDAO = database.getItemDAO();
                Result result1 = new Result();
                String category1 = "";
                switch (category) {
                    case "hoa":
                        category1 = "Hoá";
                        break;
                    case "vatly":
                        category1 = "Vật lý";
                        break;
                    case "sinhhoc":
                        category1 = "Sinh học";
                        break;
                    case "tienganh":
                        category1 = "Tiếng anh";
                        break;
                    case "android":
                        category1 = "Android";
                        break;
                }

                result1.setMonHoc(category1);
                result1.setResult(result + "/" + question);
                String level1;
                if (level.equals("de")) {
                    level1 = "Dễ";
                } else if (level.equals("trungbinh")) {
                    level1 = "Trung Bình";
                } else {
                    level1 = "Khó";
                }

                result1.setLevel(level1);
                @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                result1.setDate(dateFormat.format(cal.getTime()));
                //Insert object kết quả vào cơ sở dữ liệu
                itemDAO.insert(result1);
                //Back ve home
                sendHome.sendHome("home");

            });
        }

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IBackHome) {
            sendHome = (IBackHome) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sendHome = null;
    }

}