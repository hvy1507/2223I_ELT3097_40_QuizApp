package com.project.quizapp.QuestionHandle;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.quizapp.DataItem.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;
public class JsonHandle {
    public static String getJson(Context context, int file) {
        InputStream inputStream = context.getResources().openRawResource(file);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        return writer.toString();
    }

    public static List<Question> getList(String jsonString) {
        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<Question>>() {}.getType();
        return gson.fromJson(jsonString, listUserType);
    }
}
