package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.blackbozstudios.gce.joketellingapp.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by Anirudh on 13/09/15.
 */
public class RetrieveJoke extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    private OnJokeRetrievedListener jokeListener;

    ProgressDialog d;
    Context c1;

    public RetrieveJoke(Context c, OnJokeRetrievedListener listener) {
        jokeListener = listener;
        this.c1 = c;
        d = new ProgressDialog(c1);
        d.setMessage("Loading joke");
        d.setCancelable(false);
        d.setIndeterminate(true);


    }

    public RetrieveJoke(){
        d = null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (d != null){
            d.show();
        }

    }

    @Override
    protected String doInBackground(Void... params) {
        if (myApiService == null) {  // Only do this once
            /**
             * Uncomment this block to test with a local deployment of backend module
             */
            /*MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    // if using genymotion it seems to have the address of 10.0.3.2 instead of 10.0.2.2
                    .setRootUrl("http://10.0.3.2:8080/_ah/api/")

                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });*/
            // end options for devappserver
            /**
             * To test with the published GCE Use this lines instead of previous method
             *
             * if you want to deploy this backend module to your own GCE do so and in the next method
             * replace 'gce-joke-telling-app' for your own google project id
             */

            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://gce-joke-telling-app.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }


    }

    public RetrieveJoke setListener(OnJokeRetrievedListener listener) {
        this.jokeListener = listener;
        return this;
    }


    @Override
    protected void onPostExecute(String result) {
        if (d != null){
            d.dismiss();
        }

        jokeListener.onJokeRetrieved(result);
    }

    public interface OnJokeRetrievedListener {
        void onJokeRetrieved(String joke);
    }
}
