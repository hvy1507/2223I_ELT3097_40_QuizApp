package com.project.quizapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.quizapp.DataItem.Category;
import com.project.quizapp.Interface.IHomeData;
import com.project.quizapp.R;

import java.util.List;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder> {

    Context context;
    IHomeData sendDataHome;
    List<Category> subjects;

    // Constructor của class
    public AdapterCategory(Context context, List<Category> subjects, IHomeData sendDataHome) {
        this.context = context;
        this.subjects = subjects;
        this.sendDataHome = sendDataHome;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.items_subject, parent, false);
        return new ViewHolder(view);
    }

    // set view cho môn học và ảnh môn học theo từng vị trí trong list ,xử lý sự kiện click và chuyển sang FragmentLevel , kèm theo đó là data môn học
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // Lấy ra từng object ở vị trí position trong List
        Category subject = subjects.get(position);
        // Set text và hình ảnh cho từng môn
        holder.txtText.setText(subject.getSubjectName());
        holder.imageView.setImageDrawable(context.getResources().getDrawable(subject.getSubjectImage()));
        //Ứng với từng position thì sẽ dùng method send để truyền data môn học cho từng item
        holder.itemView.setOnClickListener(v -> {
            if (position == 0) {
                sendDataHome.send("hoa");
            } else if (position == 1) {
                sendDataHome.send("vatly");
            } else if (position == 2) {
                sendDataHome.send("sinhhoc");
            } else if (position == 3) {
                sendDataHome.send("tienganh");
            } else if (position == 4) {
                sendDataHome.send("android");
            } else if (position == 5) {
                sendDataHome.send("lichsu");
            }
        });

    }

    // Lấy ra độ dài của list
    @Override
    public int getItemCount() {
        return subjects.size();
    }

    // Inner class - ánh xạ view theo data và hold data đó để khi build lại sẽ ko phải tìm lại data đó nữa
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtText;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtText = itemView.findViewById(R.id.txtViewHolder);
            imageView = itemView.findViewById(R.id.imgImage);
        }
    }

}
