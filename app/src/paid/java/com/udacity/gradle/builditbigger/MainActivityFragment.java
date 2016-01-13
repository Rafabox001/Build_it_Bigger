package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackboxstudios.rafael.androidjokeslibrary.JokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        root.findViewById(R.id.tellJoke).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJoke();
            }
        });

        return root;
    }

    private void tellJoke() {
        new RetrieveJoke(getActivity(), new JokeRetrievalHandler()).execute();
    }

    private class JokeRetrievalHandler implements RetrieveJoke.OnJokeRetrievedListener {

        @Override
        public void onJokeRetrieved(String joke) {


            Intent intent = new Intent(getActivity(), JokeActivity.class);
            intent.putExtra("joke", joke);
            startActivity(intent);
        }
    }
}
