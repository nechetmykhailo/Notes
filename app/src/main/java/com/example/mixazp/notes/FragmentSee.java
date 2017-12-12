package com.example.mixazp.notes;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentSee extends Fragment {

    private ListView lv;
    private EditText etSearch;
    private Button btnSearch;

    private SQLiteConnector connector;
    private SQLiteDatabase db;
    private SimpleCursorAdapter adapter;
    private Cursor cursor;

    public FragmentSee() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragmentsee, null);

        lv = (ListView) v.findViewById(R.id.lv);
        etSearch = (EditText) v.findViewById(R.id.etSearch);
        btnSearch = (Button)v.findViewById(R.id.btnSearch);

        connector = new SQLiteConnector(getActivity(), "Notes", 1);
        db = connector.getReadableDatabase();
        cursor = db.rawQuery("select * from AddNotes", null);

        final String[] cols = new String[]
                {
                        "name",
                        "dateNotes",
                        "_id"
                };

        final int[] views = new int[]
                {
                        R.id.tvName,
                        R.id.tvContent,
                        R.id.tvId
                };

        adapter = new SimpleCursorAdapter(getActivity(), R.layout.test, cursor, cols, views, 0);
        lv.setAdapter(adapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = etSearch.getText().toString();

                if(search.equals("")){
                    Toast.makeText(getActivity(), "Введите текст для поиска", Toast.LENGTH_SHORT).show();
                } else if(getStudentListByKeyword(search)==null) {
                    Toast.makeText(getActivity(), "Заметка ненайдена", Toast.LENGTH_SHORT).show();
                }else {

                    String str = getStudentListByKeyword(search).toString();
                    Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }
    public Cursor  getStudentListByKeyword(String search) {
        db = connector.getReadableDatabase();
        String selectQuery =  "SELECT name " +
                " FROM AddNotes" +
                " WHERE name" + " LIKE '%"+search+"%'";
        cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            return cursor;
        }
        return null;
    }
}
