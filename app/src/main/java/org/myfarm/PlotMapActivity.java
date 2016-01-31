package org.myfarm;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Created by jakeglass on 1/30/16.
 */
public class PlotMapActivity extends Activity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_layout);

        listView = (ListView) findViewById(R.id.plots_list_view);
    }

    private class PlotListView extends ListView {

    }
}
