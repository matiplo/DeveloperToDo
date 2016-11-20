package com.example.ppp.developertodo.implementation.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ppp.developertodo.R;
import com.example.ppp.developertodo.implementation.ui.fragments.AddEditFragment;

public class AddEditActivity extends AbstractBaseActivity implements AddEditFragment.OnFragmentInteractionListener {



    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_add_edit;
    }


    @Override
    public void onActivityFinish() {
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    public boolean isEditMode() {
        return getIntent().getExtras().getInt(MainActivity.ID) != MainActivity.NO_ID;
    }

    @Override
    public int getIdFromIntent() {
        return getIntent().getExtras().getInt(MainActivity.ID);
    }
}
