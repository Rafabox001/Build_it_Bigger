package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackboxstudios.rafael.androidjokeslibrary.JokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private boolean navigate = false;
    private boolean repeat = true;
    private String jokeParam = "";

    ProgressDialog d;

    InterstitialAd ads;
    private String AD_UNIT = "ca-app-pub-3940256099942544/6300978111";

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        d = new ProgressDialog(getContext());
        d.setMessage("Getting Intersitial Ad");
        d.setCancelable(false);
        d.setIndeterminate(true);

        ads = new InterstitialAd(getContext());

        ads.setAdUnitId(AD_UNIT);

        ads.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                repeat = true;
                requestNewInterstitial();
                Intent intent = new Intent(getActivity(), JokeActivity.class);
                intent.putExtra("joke", jokeParam);
                startActivity(intent);
            }
        });

        requestNewInterstitial();

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("52B95EE68FA85DD7BBB49D7D2159D19C")
                .build();
        mAdView.loadAd(adRequest);

        root.findViewById(R.id.tellJoke).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJoke();
            }
        });

        return root;
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("52B95EE68FA85DD7BBB49D7D2159D19C")
                .build();

        ads.loadAd(adRequest);
    }

    private void tellJoke() {
        new RetrieveJoke(getActivity(), new JokeRetrievalHandler()).execute();

    }

    private class JokeRetrievalHandler implements RetrieveJoke.OnJokeRetrievedListener {

        @Override
        public void onJokeRetrieved(String joke) {

            Log.d("Joke retrieved: ", joke);

            jokeParam = joke;
            while (repeat){
                if (ads.isLoaded()){
                    ads.show();
                    repeat = false;
                    if (d.isShowing()){
                        d.dismiss();
                    }
                }else{
                    if (!d.isShowing()){
                        d.show();
                    }

                }
            }



        }
    }
}
