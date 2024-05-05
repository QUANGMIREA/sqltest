package com.example.sqltest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText editTextName, editTextAddress, editTextPhone, editTextId;
    Button addButton, viewAllButton, updateButton, deleteButton;
    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new DatabaseHandler(this);
        editTextName = findViewById(R.id.editTextName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextId = findViewById(R.id.editTextId);

        addButton = findViewById(R.id.buttonAdd);
        viewAllButton = findViewById(R.id.buttonViewAll);
        updateButton = findViewById(R.id.buttonUpdate);
        deleteButton = findViewById(R.id.buttonDelete);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudent();
            }
        });

        viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listAllStudents();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStudent();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteStudent();
            }
        });
    }

    private void addStudent() {
        dbHandler.addStudent(new Student(
                editTextName.getText().toString(),
                editTextAddress.getText().toString(),
                editTextPhone.getText().toString()));
        Toast.makeText(this, "Student added", Toast.LENGTH_SHORT).show();
    }

    private void listAllStudents() {
        List<Student> students = dbHandler.getAllStudents();
        StringBuilder sb = new StringBuilder();
        for (Student student : students) {
            sb.append("ID: ").append(student.getId()).append(", Name: ").append(student.getName())
                    .append(", Address: ").append(student.getAddress()).append(", Phone: ").append(student.getPhone_number())
                    .append("\n"); // Appends a new line for each student
        }
        TextView textViewStudents = findViewById(R.id.textViewStudents);
        textViewStudents.setText(sb.toString());
    }


    private void updateStudent() {
        Student student = new Student(
                Integer.parseInt(editTextId.getText().toString()),
                editTextName.getText().toString(),
                editTextAddress.getText().toString(),
                editTextPhone.getText().toString());
        dbHandler.updateStudent(student);
        Toast.makeText(this, "Student updated", Toast.LENGTH_SHORT).show();
    }

    private void deleteStudent() {
        int id = Integer.parseInt(editTextId.getText().toString());
        dbHandler.deleteStudent(id);
        Toast.makeText(this, "Student deleted", Toast.LENGTH_SHORT).show();
    }
}
