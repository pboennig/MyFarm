package org.myfarm;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by jakeglass on 1/30/16.
 */
public class ListActivity extends ListFragment implements AdapterView.OnItemClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        PlotDbHelper dbHelper = new PlotDbHelper(getContext());
        Cursor cursor = dbHelper.getPlots("*");

        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getContext(),android.R.layout.simple_list_item_1,cursor,new String[]{"NAME"},new int[] {android.R.id.text1});
        setListAdapter(cursorAdapter);
        getListView().setOnItemClickListener(this);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //do the stuff

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){

    }
}
