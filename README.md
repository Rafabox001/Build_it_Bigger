# Build_it_Bigger
Android Developer Nanodegree Project 4

How Do I Complete this Project?

Step 0: Starting Point

<p>This is the starting point for the final project, which is provided to you in the <a href="https://github.com/udacity/ud867/tree/master/FinalProject" target="_blank">course repository</a>.</p>

<p>It contains an activity with a banner ad and a button that purports to tell a
joke, but actually just complains. The banner ad was set up following the
instructions <a href="https://developers.google.com/mobile-ads-sdk/docs/admob/android/quick-start" target="_blank">here</a>.</p>

<p>You may need to download the Google Repository from the Extras section of the Android SDK Manager.</p>

<p>When you can build an deploy this starter code to an emulator, you're ready to move on.</p>

Step 1: Create a Java library

<p>Your first task is to create a Java library that provides jokes. Create a new Gradle Java project either using the Android Studio wizard, or by hand. Then
introduce a project dependency between your app and the new Java Library. If you need review, check out demo 4.01 from the course code.</p>

<p>Make the button display a toast showing a joke retrieved from your Java joke
telling library.</p>

Step 2: Create an Android Library

<p>Create an Android Library containing an Activity that will display a joke
passed to it as an intent extra. Wire up project dependencies so that the
button can now pass the joke from the Java Library to the Android Library.</p>

<p>For review on how to create an Android library, check out demo 4.03. For a
refresher on intent extras, check out <a href="http://developer.android.com/guide/components/intents-filters.html" target="_blank">this documentation</a>.</p>

Step 3: Create GCE Module

<p>This next task will be pretty tricky. Instead of pulling jokes directly from
our Java library, we'll set up a GCE development server,
and pull our jokes from there. Follow the instructions in <a href="https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints" target="_blank">this
tutorial</a> to add a GCE module to your project:</p>

<p>Introduce a project dependency between your Java library and your GCE module, and modify the GCE starter code to pull jokes from your Java library. Create an Async task to retrieve jokes. Made the button kick off a task to retrieve a joke, then launch the activity from your Android Library to display it.</p>

Step 4: Add Functional Tests

<p>Add code to test that your Async task successfully retrieves a non-empty
string. For a refresher on setting up Android tests, check out demo 4.09.</p>

Step 5: Add a Paid Flavor

<p>Add free and paid product flavors to your app. Remove the ad (and any
dependencies you can) from the paid flavor.</p>

Optional Tasks

<p>To exceed expectations, complete the following tasks.</p>

Add Interstitial Ad

<p>Follow these instructions to add an interstitial ad to the free version. Display the ad after the user hits the button, and before the joke is shown.</p>

Add Loading Indicator

<p>Add a loading indicator that is shown while the joke is being retrieved, and
disappears when the joke is ready. <a href="http://www.tutorialspoint.com/android/android_loading_spinner.htm" target="_blank">This tutorial is a good place to start</a>.</p>

Configure Test Task

<p>To tie it all together, create a Gradle task that:</p>

<ol>
<li>Launches the GCE local development server</li>
<li>Runs all tests</li>
<li>Shuts the server down again</li>
</ol>
