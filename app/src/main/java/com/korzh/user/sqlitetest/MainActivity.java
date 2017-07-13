package com.korzh.user.sqlitetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.korzh.user.sqlitetest.sql.SQLHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLHelper sqlHelper = new SQLHelper(this);
        sqlHelper.getReadableDatabase();

    }
}
