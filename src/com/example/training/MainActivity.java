package com.example.training;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button btn_abso = (Button) findViewById(R.id.btn_absolutelayout);
        Button btn_frame = (Button) findViewById(R.id.btn_framelayout);
        Button btn_relative = (Button) findViewById(R.id.btn_relativelayout);
        Button btn_table = (Button) findViewById(R.id.btn_tablelayout);
        
        btn_abso.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              setContentView(R.layout.absole_layout);
            }
        });
        
        btn_frame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              setContentView(R.layout.frame_layout);
            }
        });
        
        btn_relative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	setContentView(R.layout.relative_layout);
            }
        });
        
        btn_table.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	setContentView(R.layout.table_layout);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
