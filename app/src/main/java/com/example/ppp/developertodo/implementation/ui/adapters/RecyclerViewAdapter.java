package com.example.ppp.developertodo.implementation.ui.adapters;

import android.content.Context;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.android.multiselector.ModalMultiSelectorCallback;
import com.bignerdranch.android.multiselector.MultiSelector;
import com.bignerdranch.android.multiselector.SwappingHolder;
import com.example.ppp.developertodo.R;
import com.example.ppp.developertodo.implementation.ui.activities.MainActivity;
import com.example.ppp.developertodo.implementation.ui.interfaces.IMainView;
import com.example.ppp.developertodo.logic.model.Todo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;

    //data source
    private List<Todo> mToDoItems;

    //view
    public final IMainView mView;

    public RecyclerViewAdapter(IMainView view, Context context) {
        this.context = context;
        this.mToDoItems = new ArrayList<>();
        this.mView = view;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todoitem_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Todo item = mToDoItems.get(position);
        holder.mToDoName.setText(item.getName());
        holder.mDuration.setText(String.valueOf(item.getDuration()));
    }

    @Override
    public int getItemCount() {
        return mToDoItems.size();
    }

    //access to row via ViewHolder
    public class ViewHolder extends SwappingHolder implements View.OnClickListener {

        @BindView(R.id.todoitem_name)
        public TextView mToDoName;

        @BindView(R.id.todoitem_duration)
        public TextView mDuration;


        public ViewHolder(View view) {
            super(view, mMultiSelector);
            view.setOnClickListener(this);
            ButterKnife.bind(this, view);
            mMultiSelector.setSelectable(true);

        }

        @Override
        public void onClick(View view) {

            if (mActionMode == null) {
                mActionMode = ((MainActivity) context).startSupportActionMode(mActionModeCallback);
            }

            mMultiSelector.tapSelection(ViewHolder.this);

            mActionMode.invalidate();

            if (mActionMode != null && mMultiSelector.getSelectedPositions().size() == 0) {
                mActionMode.finish();

            }

        }
    }


    private ActionMode mActionMode;
    private MultiSelector mMultiSelector = new MultiSelector();

    public ModalMultiSelectorCallback mActionModeCallback
            = new ModalMultiSelectorCallback(mMultiSelector) {

        ///
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            super.onCreateActionMode(actionMode, menu);
            actionMode.getMenuInflater().inflate(R.menu.context_menu, menu);
            actionMode.setTitle(context.getString(R.string.select_items));

            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            super.onPrepareActionMode(actionMode, menu);
            actionMode.setSubtitle(String.format("%s%s", context.getString(R.string.items_selected), mMultiSelector.getSelectedPositions().size()));

            if (mMultiSelector.getSelectedPositions().size() == 1) {
                menu.findItem(R.id.action_edit).setVisible(true);
            } else {
                menu.findItem(R.id.action_edit).setVisible(false);
            }
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            if (item.getItemId() == R.id.action_edit)
                ((MainActivity) context).onClickEdit(getSelectedItemsIds().get(0));
            else if (item.getItemId() == R.id.action_delete)
                ((MainActivity) context).onClickDelete(getSelectedItemsIds());

            mActionMode.finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

            mActionMode = null;
            mMultiSelector.clearSelections();
        }


    };


    public void addNewTodos(List<Todo> todoList) {
        // clean up old data
        if (mToDoItems != null) {
            mToDoItems.clear();
        }
        mToDoItems.addAll(todoList);

        notifyDataSetChanged();
    }

    private List<Integer> getSelectedItemsIds() {
        List<Integer> result = new ArrayList<>();
        for (int position : mMultiSelector.getSelectedPositions()
                ) {
            result.add(mToDoItems.get(position).getId());
        }
        return result;
    }
}
