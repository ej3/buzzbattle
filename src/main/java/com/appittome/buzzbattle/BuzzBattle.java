package com.appittome.buzzbattle;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Simple app  push some buttons to mutate and send a HttpURLRequest
 */
public class BuzzBattle extends Activity
{
    /* TAG for debug logs */
    public static final String TAG = BuzzBattle.class.getSimpleName();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //remove the status bar and whatnot
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.main);
    }

    /**
     * called from botton#onClick see layout/main.xml
     *
     * @param view
     */
    public void makeRequest(View view) {
        int _id = getCmdId(view.getId());
        //just.. gonna.. do this.
        String _urlStr = "http://www.buzzbattle.co/user/1/command_id/" + _id + ".json";
        Log.d(TAG, "request: "+_urlStr);
        new RequestTask().execute(_urlStr);
    }

    /**
     * async task to complete requests without stalling the UI
     */
    class RequestTask extends AsyncTask<String, Void, Integer> {
        @Override protected Integer doInBackground(String... urls) {
            HttpURLConnection _c = null;
            Integer _r = null;
            try {
                URL _url = new URL(urls[0]);
                _c = (HttpURLConnection) _url.openConnection();
                logResponse(_c);
            } catch (Exception e) {
              Log.d(TAG, "Exception.. yo", e);
            } finally {
                _c.disconnect();
            }
            return _r;
        }
    }

    /*-- utility methods --*/
    /**
     * pretty print the response to logs.
     *
     * @param con connection
     * @return response code received from webservice
     * @throws IOException when the connection is unhappy
     */
    private Integer logResponse(HttpURLConnection con) throws IOException{
       Log.d(TAG, "Response:");
       Log.d(TAG, "\tCODE: "+ con.getResponseCode() );
       Scanner _s = new Scanner(con.getInputStream()).useDelimiter("\\A");
       while(_s.hasNext())
           Log.d(TAG, "\t"+_s.next());
       return con.getResponseCode();
    }
    /**
     * Transform id from button space to webservice command space
     *
     * @param buttonId button id that spawned the request
     * @return integer to send to webservice
     */
    private int getCmdId(int buttonId){
        int _id = 1;
        switch (buttonId) {
            case R.id.A:
                _id = 1;
                break;
            case R.id.B:
                _id = 2;
                break;
            case R.id.C:
                _id = 3;
                break;
            case R.id.D:
                _id = 4;
                break;
            case R.id.E:
                _id = 5;
                break;
            case R.id.F:
                _id = 6;
                break;
        }
        return _id;
    }
}
