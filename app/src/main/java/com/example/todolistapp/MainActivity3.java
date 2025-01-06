package com.example.todolistapp;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    Button add;
    AlertDialog dialog;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        add = findViewById(R.id.addbutton);
        layout = findViewById(R.id.container);

        buildDialog();

        add.setOnClickListener(v -> {
            dialog.show();
        });
    }

    public void buildDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog, null);

        final EditText name = view.findViewById(R.id.edittext);

        builder.setView(view);
        builder.setTitle("Enter your Task:")
                .setPositiveButton("SAVE", (dialog, which) -> {
                    String taskName = name.getText().toString().trim();
                    if (!taskName.isEmpty()) {
                        addCard(taskName);
                        showAlert("Task added", "Task added: " + taskName);
                    }else {
                        showAlert("Task not added", "Empty input");
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    showAlert("Dialog canceled", "Dialog was canceled");
                });

        dialog = builder.create();
    }

    private void addCard(String name) {
        final View view = getLayoutInflater().inflate(R.layout.card, null);
        TextView nameView = view.findViewById(R.id.name);
        Button delete = view.findViewById(R.id.delete);

        nameView.setText(name);
        delete.setOnClickListener(v -> {
            layout.removeView(view);
            showAlert("Task deleted", "Task deleted: " + name);
        });

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(0, 0, 0, 20); // Left, Top, Right, Bottom margins
        view.setLayoutParams(layoutParams);

        layout.addView(view);

    }

    private void showAlert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }



}
