package com.example.loginuser;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity {

    final String[] from = new String[]{DatabaseHelper._ID,
            DatabaseHelper.NAME,DatabaseHelper.ISLOGGED};
    final int[] to = new int[]{R.id.txtid, R.id.txtname};
    private static final String TAG = "UserActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user);

        DBManager dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        ListView listView = findViewById(R.id.available_userList);
        listView.setEmptyView(findViewById(R.id.empty));

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.row_list, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                Log.e(TAG, "onItemClick: " );
                TextView idText = view.findViewById(R.id.txtid);
                TextView nameText = view.findViewById(R.id.txtname);

                String id = idText.getText().toString();
                String name = nameText.getText().toString();


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {

            Intent add_mem = new Intent(this, CreateUserActivity.class);
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }

    private void openImageDialog(String url) {
        Button login,cancel;
        LayoutInflater li = LayoutInflater.from(this);
        final View prompt = li.inflate(R.layout.custom_dialog_layout, null);

        login = prompt.findViewById(R.id.btnLogin);
        cancel = prompt.findViewById(R.id.btnCancel);

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        alertDialogBuilder.setView(prompt);
        final AlertDialog dialog = alertDialogBuilder.create();
        dialog.setCancelable(false);
        dialog.show();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

}
