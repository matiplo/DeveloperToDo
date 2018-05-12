package com.example.ppp.developertodo.implementation.ui.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ppp.developertodo.R;
import com.example.ppp.developertodo.adapter.presenters.implementations.MainPresenter;
import com.example.ppp.developertodo.adapter.presenters.interfaces.IMainPresenter;
import com.example.ppp.developertodo.implementation.networking.APIService;
import com.example.ppp.developertodo.implementation.storage.repository.TodoRepository;
import com.example.ppp.developertodo.implementation.threading.MainThread;
import com.example.ppp.developertodo.implementation.ui.adapters.RecyclerViewAdapter;
import com.example.ppp.developertodo.implementation.ui.interfaces.IMainView;
import com.example.ppp.developertodo.logic.executors.ThreadExecutor;
import com.example.ppp.developertodo.logic.model.Todo;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends AbstractBaseActivity implements IMainView, APIService.onNetworkInteractionListener {

    protected static final int ADD_REQUEST = 1;
    protected static final int EDIT_REQUEST = 2;

    @BindView(R.id.rvTodos)
    RecyclerView mRecyclerView;

    private RecyclerViewAdapter mAdapter;

    private IMainPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();


    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_add) {
            onClickAdd();
            return true;
        }
        if (id == R.id.action_download) {
            onClickDownload();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void init() {
        mAdapter = new RecyclerViewAdapter(this, this);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPresenter = new MainPresenter(ThreadExecutor.getInstance(), MainThread.getInstance(), new TodoRepository(), this);
    }


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }


    public void onClickAdd() {
        starAddEditActivity(NO_ID, ADD_REQUEST);
    }

    public void onClickEdit(int id) {
        starAddEditActivity(id, EDIT_REQUEST);
    }

    private void starAddEditActivity(int id, int requestType){
        Intent intent = new Intent(this, AddEditActivity.class);

        intent.putExtra(ID, id);
        startActivityForResult(intent, requestType);

    }

    public void onClickDelete(final List<Integer> list) {

        DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        mPresenter.deleteTodosById(list);

                        break;

                    case DialogInterface.BUTTON_NEGATIVE:

                        break;
                }
            }
        };


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_question)
                .setPositiveButton(R.string.yes, dialogListener)
                .setNegativeButton(R.string.no, dialogListener)
                .show();
    }


    public void onClickDownload() {
        getAPIService().downloadNextTodo();
    }


    @Override
    public void onTodosDeled() {
        mAdapter.finishActionMode();
        mPresenter.getAllTodos();
        showMesssage(R.string.todos_deleted);
    }

    public void onTodoAdded() {
        showMesssage(R.string.todo_added);
    }

    @Override
    public void onTodoEdited() {
        mAdapter.finishActionMode();
        showMesssage(R.string.todo_updated);
    }

    @Override
    public void onTodoDownloaded(Todo todo) {
        showMessage("Downloaded: NAME: " + todo.getName() + " DURATION: " + todo.getDuration());

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == ADD_REQUEST)
                onTodoAdded();
            else if (requestCode == EDIT_REQUEST)
                onTodoEdited();
        }

    }


    @Override
    public void showTodos(List<Todo> todoList) {
        mAdapter.addNewTodos(todoList);
    }
}
