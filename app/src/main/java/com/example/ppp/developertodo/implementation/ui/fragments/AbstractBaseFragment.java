package com.example.ppp.developertodo.implementation.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ppp.developertodo.R;
import com.example.ppp.developertodo.adapter.presenters.base.AbstractPresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class AbstractBaseFragment extends Fragment {

    private Unbinder unbinder;

    protected OnFragmentInteractionListener mListener;

    public interface OnFragmentInteractionListener {
        void onActivityFinish();
        boolean isEditMode();
        int getIdFromIntent();
    }

    public AbstractBaseFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(getLayoutResourceId(), container, false);
        unbinder=ButterKnife.bind(this,view);
        return view;
    }

    protected abstract int getLayoutResourceId();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected void showMesssage(int stringId) {
        Toast.makeText(getContext(), stringId, Toast.LENGTH_SHORT).show();
    }



}
