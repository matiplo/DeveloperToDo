package com.example.ppp.developertodo.implementation.ui.activities;

import android.app.Activity;
import android.os.Bundle;

import com.example.ppp.developertodo.R;
import com.example.ppp.developertodo.implementation.ui.fragments.AbstractAddEditFragment;
import com.example.ppp.developertodo.implementation.ui.fragments.AddFragment;
import com.example.ppp.developertodo.implementation.ui.fragments.EditFragment;

public class AddEditActivity extends AbstractBaseActivity implements AbstractAddEditFragment.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AbstractAddEditFragment fragment = isEditMode() ? new EditFragment() : new AddFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_add_edit;
    }


    @Override
    public void onActivityFinish() {
        setResult(Activity.RESULT_OK);

        finish();
    }


    private boolean isEditMode() {
        return getIntent().getExtras().getInt(ID) != NO_ID;
    }

    @Override
    public int getIdFromIntent() {
        return getIntent().getExtras().getInt(ID);
    }
}
