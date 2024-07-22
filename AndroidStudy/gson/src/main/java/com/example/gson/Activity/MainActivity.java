package com.example.gson.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gson.Entity.Grade;
import com.example.gson.Entity.Student;
import com.example.gson.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edt_json, edt_obj;
    private Button btn_toJson, btn_toObj;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initGson();
        setLinsteners();
    }

    private void initGson() {
        //gson = new Gson();
        gson = new GsonBuilder()
                .serializeNulls() //允许导出null值
                .setPrettyPrinting() //格式化输出
                .setDateFormat("yyyy/mm/dd") //日期输出格式
                .create();
    }

    private void setLinsteners() {
        btn_toJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //objToJson(); //简单对象序列化
                //formatObjToJson(); //格式化日期对象序列化
                //complexObjToJson(); //嵌套对象序列化
                //arrayObjToJson(); //对象数组序列化
                //collectionObjToJson(); //对象集合序列化
            }
        });
        btn_toObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //jsonToObj();
                //formatJsonToObj();
                //complexJsonToObj();
                //arrayJsonToObj();
                //collectionJsonToObj();
            }
        });
    }

    private void collectionJsonToObj() {
        String jsonStr = edt_json.getText().toString();
        Type collectionType = new TypeToken<List<Student>>(){}.getType();
        List<Student> studentList = gson.fromJson(jsonStr, collectionType);
        edt_obj.setText(studentList.toString());
    }

    private void collectionObjToJson() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("喜多郁代", 1));
        studentList.add(new Student("山田凉", 2));
        String jsonStr = gson.toJson(studentList);
        edt_json.setText(jsonStr);
    }

    private void arrayJsonToObj() {
        String jsonStr = edt_json.getText().toString();
        Student[] students = gson.fromJson(jsonStr, Student[].class);
        edt_obj.setText(Arrays.asList(students).toString());
    }

    private void arrayObjToJson() {
        Student[] students = new Student[2];
        students[0] = new Student("喜多郁代", 1);
        students[1] = new Student("山田凉", 2);
        String jsonStr = gson.toJson(students);
        edt_json.setText(jsonStr);
    }

    private void complexJsonToObj() {
        String jsonStr = edt_json.getText().toString();
        Student student = gson.fromJson(jsonStr, Student.class);
        edt_obj.setText(student.toString());
    }

    private void complexObjToJson() {
        Student student = new Student("喜多郁代", 114514);
        Grade grade = new Grade(99, 100);
        student.setGrade(grade);
        String jsonStr = gson.toJson(student);
        edt_json.setText(jsonStr);
    }

    private void formatJsonToObj() {
        String jsonStr = edt_json.getText().toString();
        Student student = gson.fromJson(jsonStr, Student.class);
        edt_obj.setText(student.toString());
    }

    private void formatObjToJson() {
        Student student = new Student("喜多郁代", 114514);
        SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd");
        Date date = null;
        try {
            date = format.parse("1999/9/9");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        student.setDate(date);

        String jsonStr = gson.toJson(student);
        edt_json.setText(jsonStr);
    }

    private void jsonToObj() {
        String jsonStr = edt_json.getText().toString();
        Student student = gson.fromJson(jsonStr, Student.class);
        edt_obj.setText(student.toString());
    }

    private void objToJson() {
        Student student = new Student("喜多郁代", 114514);
        String jsonStr = gson.toJson(student);
        edt_json.setText(jsonStr);
    }

    private void findViews() {
        edt_json = findViewById(R.id.edt_json);
        edt_obj = findViewById(R.id.edt_obj);
        btn_toJson = findViewById(R.id.btn_toJson);
        btn_toObj = findViewById(R.id.btn_toObj);
    }
}