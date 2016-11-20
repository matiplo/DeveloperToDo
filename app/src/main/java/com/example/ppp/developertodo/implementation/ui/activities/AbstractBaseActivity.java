package com.example.ppp.developertodo.implementation.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.ppp.developertodo.R;

import butterknife.ButterKnife;

public abstract class AbstractBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
    }

    protected abstract int getLayoutResourceId();

    protected void showMesssage(int stringId) {
        Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show();
    }
}
