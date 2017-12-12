package com.example.mixazp.notes;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private FragmentAdd fragmentAdd;
    private FragmentSee fragmentSee;
    private Button btnAdd;
    private Button btnSee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSee = (Button) findViewById(R.id.btnSee);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentAdd = new FragmentAdd();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container, fragmentAdd);
                ft.commit();
            }
        });

        btnSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentSee = new FragmentSee();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container, fragmentSee);
                ft.commit();
            }
        });

    }
}
