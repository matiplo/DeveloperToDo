package com.example.ppp.developertodo.implementation.ui.listeners;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by ppp on 2016-11-19.
 */

public abstract class TextValidator implements TextWatcher {

    @Override
    public void afterTextChanged(Editable s) {
        String text=s.toString();
        validate(text);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }
    public  void validate(String text){

    };

    protected boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
