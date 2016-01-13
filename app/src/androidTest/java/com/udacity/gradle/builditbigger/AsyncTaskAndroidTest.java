package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.content.Context;
import android.support.v4.util.Pair;
import android.test.ApplicationTestCase;
import android.text.TextUtils;
import android.util.Log;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Rafael on 13/01/2016.
 */

public class AsyncTaskAndroidTest extends ApplicationTestCase<Application> {


        private static final String ASYNC_TEST_TAG = "ASYNC_TEST_TAG";
        private String mJokeString = null;
        private Exception mError = null;
        private CountDownLatch signal = null;

        public AsyncTaskAndroidTest() {
            super(Application.class);
        }


        @Override
        protected void setUp() throws Exception {
            signal = new CountDownLatch(1);
        }


        @Override
        protected void tearDown() throws Exception {
            signal.countDown();
        }

        public void testEndpointGetJoke() throws InterruptedException {
            RetrieveJoke task = new RetrieveJoke();

            //noinspection unchecked
            task.setListener(new RetrieveJoke.OnJokeRetrievedListener() {
                @Override
                public void onJokeRetrieved(String joke) {
                    mJokeString = joke;
                    signal.countDown();
                }
            }).execute();

            signal.await();

            assertNull(mError);
            assertFalse(TextUtils.isEmpty(mJokeString));
            assertTrue(mJokeString != null && mJokeString.length() > 0);
            Log.i(ASYNC_TEST_TAG, "Returned joke: " + mJokeString);
        }
    }


