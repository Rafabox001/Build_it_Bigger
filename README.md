# Build_it_Bigger
Android Developer Nanodegree Project 4

How Do I Complete this Project?

Step 0: Starting Point

This is the starting point for the final project, which is provided to you in the course repository.

It contains an activity with a banner ad and a button that purports to tell a joke, but actually just complains. The banner ad was set up following the instructions here.

You may need to download the Google Repository from the Extras section of the Android SDK Manager.

When you can build an deploy this starter code to an emulator, you're ready to move on.

Step 1: Create a Java library

Your first task is to create a Java library that provides jokes. Create a new Gradle Java project either using the Android Studio wizard, or by hand. Then introduce a project dependency between your app and the new Java Library. If you need review, check out demo 4.01 from the course code.

Make the button display a toast showing a joke retrieved from your Java joke telling library.

Step 2: Create an Android Library

Create an Android Library containing an Activity that will display a joke passed to it as an intent extra. Wire up project dependencies so that the button can now pass the joke from the Java Library to the Android Library.

For review on how to create an Android library, check out demo 4.03. For a refresher on intent extras, check out this documentation.

Step 3: Create GCE Module

This next task will be pretty tricky. Instead of pulling jokes directly from our Java library, we'll set up a GCE development server, and pull our jokes from there. Follow the instructions in this tutorial to add a GCE module to your project:

Introduce a project dependency between your Java library and your GCE module, and modify the GCE starter code to pull jokes from your Java library. Create an Async task to retrieve jokes. Made the button kick off a task to retrieve a joke, then launch the activity from your Android Library to display it.

Step 4: Add Functional Tests

Add code to test that your Async task successfully retrieves a non-empty string. For a refresher on setting up Android tests, check out demo 4.09.

Step 5: Add a Paid Flavor

Add free and paid product flavors to your app. Remove the ad (and any dependencies you can) from the paid flavor.

Optional Tasks

To exceed expectations, complete the following tasks.

Add Interstitial Ad

Follow these instructions to add an interstitial ad to the free version. Display the ad after the user hits the button, and before the joke is shown.

Add Loading Indicator

Add a loading indicator that is shown while the joke is being retrieved, and disappears when the joke is ready. This tutorial is a good place to start.

Configure Test Task

To tie it all together, create a Gradle task that:

Launches the GCE local development server
Runs all tests
Shuts the server down again
