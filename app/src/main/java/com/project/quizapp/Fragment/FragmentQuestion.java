package com.project.quizapp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.quizapp.Interface.IResultSending;
import com.project.quizapp.QuestionHandle.JsonHandle;
import com.project.quizapp.DataItem.Question;
import com.project.quizapp.R;

import java.util.List;

public class FragmentQuestion extends Fragment {

    TextView txtQuestion, answer_1, answer_2;
    LinearLayout linearLayout3, linearLayout2;
    IResultSending sendResult;
    ProgressBar progressBar;
    Bundle bundle;
    List<Question> questionList;
    String json = "";
    String level = "";
    String category = "";
    int index = 0;
    int result = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);

        txtQuestion = view.findViewById(R.id.txtQuestion);
        answer_1 = view.findViewById(R.id.answer_1);
        answer_2 = view.findViewById(R.id.answer_2);
        linearLayout3 = view.findViewById(R.id.linnearLayout3);
        linearLayout2 = view.findViewById(R.id.linnearLayout2);
        progressBar = view.findViewById(R.id.progressbar);

        // Lay data thong qua Bundle
        if (bundle != null) {
            level = bundle.getString("mess");
            category = bundle.getString("monhoc");
        }

        // Xu ly phan chon cau hoi
        if (level.equals("de") && category.equals("hoa")) {
            json = JsonHandle.getJson(requireContext(), R.raw.chemistry_easy);
            questionList = JsonHandle.getList(json);
            init(index);
        } else if (level.equals("trungbinh") && category.equals("hoa")) {
            json = JsonHandle.getJson(requireContext(), R.raw.chemistry_hard);
            questionList = JsonHandle.getList(json);
            init(index);
        } else if (level.equals("kho") && category.equals("hoa")) {
            json = JsonHandle.getJson(requireContext(), R.raw.chemistry_medium);
            questionList = JsonHandle.getList(json);
            init(index);
        } else if (level.equals("de") && category.equals("vatly")) {
            json = JsonHandle.getJson(requireContext(), R.raw.physic_easy);
            questionList = JsonHandle.getList(json);
            init(index);
        } else if (level.equals("trungbinh") && category.equals("vatly")) {
            json = JsonHandle.getJson(requireContext(), R.raw.physic_medium);
            questionList = JsonHandle.getList(json);
            init(index);
        } else if (level.equals("kho") && category.equals("vatly")) {
            json = JsonHandle.getJson(requireContext(), R.raw.physic_hard);
            questionList = JsonHandle.getList(json);
            init(index);
        } else if (level.equals("de") && category.equals("sinhhoc")) {
            json = JsonHandle.getJson(requireContext(), R.raw.biology_easy);
            questionList = JsonHandle.getList(json);
            init(index);
        } else if (level.equals("trungbinh") && category.equals("sinhhoc")) {
            json = JsonHandle.getJson(requireContext(), R.raw.biology_medium);
            questionList = JsonHandle.getList(json);
            init(index);
        } else if (level.equals("kho") && category.equals("sinhhoc")) {
            json = JsonHandle.getJson(requireContext(), R.raw.biology_hard);
            questionList = JsonHandle.getList(json);
            init(index);
        } else if (level.equals("de") && category.equals("tienganh")) {
            json = JsonHandle.getJson(requireContext(), R.raw.english_easy);
            questionList = JsonHandle.getList(json);
            init(index);
        } else if (level.equals("trungbinh") && category.equals("tienganh")) {
            json = JsonHandle.getJson(requireContext(), R.raw.english_medium);
            questionList = JsonHandle.getList(json);
            init(index);
        } else if (level.equals("kho") && category.equals("tienganh")) {
            json = JsonHandle.getJson(requireContext(), R.raw.english_hard);
            questionList = JsonHandle.getList(json);
            init(index);
        } else if (level.equals("de") && category.equals("android")) {
            json = JsonHandle.getJson(requireContext(), R.raw.coding_easy);
            questionList = JsonHandle.getList(json);
            init(index);
        } else if (level.equals("trungbinh") && category.equals("android")) {
            json = JsonHandle.getJson(requireContext(), R.raw.coding_medium);
            questionList = JsonHandle.getList(json);
            init(index);
        } else if (level.equals("kho") && category.equals("android")) {
            json = JsonHandle.getJson(requireContext(), R.raw.coding_hard);
            questionList = JsonHandle.getList(json);
            init(index);
        }
        // Khai bao Progress Bar
        progressBar.setMax(questionList.size());
        progressBar.setProgress(1);
        // Xử lý sự kiện click vào câu trả lời
        linearLayout3.setOnClickListener(v -> {

            if (answer_1.getText().toString().equals(questionList.get(index).getCorrect())) {
                result++;
            }
            progressBar.setProgress(index + 1);
            if (index < questionList.size() - 1) {
                index++;
                init(index);
            } else {
                //Sau khi bấm vào đáp án câu hỏi cuối cùng , chuyển đến màn result bằng method send , sau đó đặt lại index và result = 0 cho lượt chơi mới
                sendResult.send(result + "", questionList.size() + "", category, level);
                index = 0;
                result = 0;
            }
        });
        //  Xử lý sự kiện click vào câu trả lời
        linearLayout2.setOnClickListener(v -> {
            if (answer_2.getText().toString().equals(questionList.get(index).getCorrect())) {
                result++;
            }
            progressBar.setProgress(index + 1);
            if (index < questionList.size() - 1) {
                index++;
                init(index);
            } else {
                sendResult.send(result + "", questionList.size() + "", category, level);
                index = 0;
                result = 0;
            }
        });

        return view;
    }

    // Nhận vào index để setText cho câu hỏi tiếp theo , khi index tăng thì view câu hỏi sẽ đc ánh xạ lên màn hình
    public void init(int i) {
        txtQuestion.setText(questionList.get(i).getQuestion());
        answer_1.setText(questionList.get(i).getAnswer1());
        answer_2.setText(questionList.get(i).getAnswer2());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IResultSending) {
            sendResult = (IResultSending) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sendResult = null;
    }
}
