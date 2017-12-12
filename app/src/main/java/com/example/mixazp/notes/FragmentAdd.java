package com.example.mixazp.notes;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class FragmentAdd extends Fragment {

    private EditText etName;
    private EditText etContent;
    private EditText etIdOK;
    private Button btnAdd;
    private Button btnUpdate;
    private Button btnDel;
    private SQLiteConnector connector;
    private SQLiteDatabase db;


    public FragmentAdd() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragmentadd, null);

        etName = (EditText) v.findViewById(R.id.etName);
        etContent = (EditText) v.findViewById(R.id.etContent);
        etIdOK = (EditText) v.findViewById(R.id.etIdOk);
        btnAdd = (Button) v.findViewById(R.id.btnAdd);
        btnUpdate = (Button) v.findViewById(R.id.btnUpdate);
        btnDel = (Button) v.findViewById(R.id.btnDel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            connectorAdd();
            }

        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectorUpdate();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectDel();
            }
        });

        return v;
    }

    private void connectDel() {
        connector = new SQLiteConnector(getActivity(), "Notes", 1);
        db = connector.getWritableDatabase();

        db.delete("AddNotes", null, null);
        Toast.makeText(getActivity(), "Заметки удалены", Toast.LENGTH_SHORT).show();
    }

    private void connectorUpdate() {
        connector = new SQLiteConnector(getActivity(), "Notes", 1);
        db = connector.getWritableDatabase();

        String name = etName.getText().toString();
        String content = etContent.getText().toString();
        String member = etIdOK.getText().toString();
        ContentValues aContentValues = new ContentValues();

        aContentValues.put("name", name);
        aContentValues.put("content", content);

        db.update("AddNotes", aContentValues, "_id = ?", new String []{member});
        Toast.makeText(getActivity(), "Данные обновлены", Toast.LENGTH_SHORT).show();
    }

    private void connectorAdd() {
        connector = new SQLiteConnector(getActivity(), "Notes", 1);
        db = connector.getWritableDatabase();

        String name = etName.getText().toString();
        String content = etContent.getText().toString();
        String member = etIdOK.getText().toString();

        Date date = new Date(System.currentTimeMillis());

        String date1 = date.toString();

        ContentValues aContentValues = new ContentValues();

        aContentValues.put("name", name);
        aContentValues.put("content", content);
        aContentValues.put("dateNotes", date1);

        db.insert("AddNotes", null, aContentValues);
        Toast.makeText(getActivity(), "Данные добавлены", Toast.LENGTH_SHORT).show();
    }
}
