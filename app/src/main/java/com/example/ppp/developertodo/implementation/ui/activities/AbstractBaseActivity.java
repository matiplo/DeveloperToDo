package com.example.ppp.developertodo.implementation.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.ppp.developertodo.R;
import com.example.ppp.developertodo.implementation.networking.APIService;

import butterknife.ButterKnife;

public abstract class AbstractBaseActivity extends AppCompatActivity {

    protected static final int NO_ID = -1;
    protected static final String ID = "ID";

    protected APIService getAPIService() {
        return APIService.getInstance(this);
    }

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

    protected void showMessage(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }
}
