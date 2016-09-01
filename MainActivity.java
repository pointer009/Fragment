package fragment.com.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/*
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
*/
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import static fragment.com.fragment.R.id.button;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {



     /* Request code used to invoke sign in user interactions. */
      private static final int RC_SIGN_IN = 0;

     /* Client used to interact with Google APIs. */
     private GoogleApiClient mGoogleApiClient;

     /* A flag indicating that a PendingIntent is in progress and prevents   * us from starting further intents.   */    private boolean mIntentInProgress;

     private boolean mShouldResolve;

     /*finding connection result*/    private ConnectionResult connectionResult;

     private SignInButton googleSignInButton;
     private Button googleSignOutButton;
     private TextView loggedInUsrName, loggedInUsrMail, usrNotSignedIn;
     private LinearLayout viewContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        googleSignInButton = (SignInButton) findViewById(R.id.sign_in_button);
        googleSignOutButton = (Button) findViewById(R.id.sign_out_button);
        loggedInUsrName = (TextView) findViewById(R.id.loggedInUsrName);
        loggedInUsrMail = (TextView) findViewById(R.id.loggedInUsrMail);
        usrNotSignedIn = (TextView) findViewById(R.id.notSignedIn_tv);
        //      viewContainer = (LinearLayout) findViewById(R.id.text_view_container);

        //googleSignInButton.setOnClickListener(this);
        googleSignInButton.setOnClickListener(this);
        googleSignOutButton.setOnClickListener(this);


        // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Drive.API)
                .addScope(Drive.SCOPE_FILE)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(AppIndex.API).build();












       /* Log.d("lifecycle","onCreate invoked");

       Button b1=(Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                *//*Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.javatpoint.com"));
                startActivity(intent);
                *//*


              *//*  Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(""));
                startActivity(intent);
*//*
               //startActivity(new Intent(getApplicationContext(),MainActivity.class));




             Intent intent=new Intent(getApplicationContext(),SecondActivity.class);
                intent.putExtra("valuse","siddharth");
                startActivity(intent);





            }
        });
*/
    }

    @Override
    protected void onStart() {
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        mGoogleApiClient.connect();
        //connect GoogleApiClient        mGoogleApiClient.connect();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.start(mGoogleApiClient, getIndexApiAction());
    }

     protected void onStop() {
         super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
         AppIndex.AppIndexApi.end(mGoogleApiClient, getIndexApiAction());
         //disconnect GoogleApiClient        if (mGoogleApiClient.isConnected()) {
         mGoogleApiClient.disconnect();
     }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle","onResume invoked");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle","onPause invoked");
    }


     /*    Used for resolving errors during signIn    */
     private void resolveSignInError() {
         if (connectionResult.hasResolution()) {
             try {
                 mIntentInProgress = true;
                 connectionResult.startResolutionForResult(this, RC_SIGN_IN);
             } catch (IntentSender.SendIntentException e) {
                 mIntentInProgress = false;
                 mGoogleApiClient.connect();
             }
         }
     }


   /* @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle","onStop invoked");
    }*/

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycle","onRestart invoked");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle","onDestroy invoked");
    }

    /*@Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
*/


     /*    onConnectionFailed() was started with startIntentSenderForResult and the code RC_SIGN_IN,    we can capture the result inside Activity.onActivityResult.     */    @Override    protected void onActivityResult(int requestCode, int responseCode,
                                                                                                                                                                                                                           Intent intent) {
         if (requestCode == RC_SIGN_IN) {
             if (responseCode != RESULT_OK) {
                 mShouldResolve = false;
             }

             mIntentInProgress = false;

             if (!mGoogleApiClient.isConnecting()) {
                 mGoogleApiClient.connect();
             }
         }
     }


     /*  When the GoogleApiClient object is unable to establish a connection onConnectionFailed() is called   */    @Override
     public void onConnectionFailed(ConnectionResult result) {
         if (!result.hasResolution()) {
             GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this,
                     0).show();
             return;
         }

         if (!mIntentInProgress) {

             connectionResult = result;

             if (mShouldResolve) {

                 resolveSignInError();
             }
         }

     }







     /*    on the successfull connection onConnected is called     */    @Override    public void onConnected(Bundle arg0) {
         mShouldResolve = false;
         try {
             if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
                 Person person = Plus.PeopleApi                        .getCurrentPerson(mGoogleApiClient);
                 String personName = person.getDisplayName();
                 String email = Plus.AccountApi.getAccountName(mGoogleApiClient);

                 loggedInUsrName.setText(personName);
                 loggedInUsrMail.setText(email);

                 Toast.makeText(getApplicationContext(),
                         "You are Logged In " + personName, Toast.LENGTH_LONG).show();
             } else {
                 Toast.makeText(getApplicationContext(),
                         "Couldnt Get the Person Info", Toast.LENGTH_SHORT).show();
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         signOutUI();

     }



     /*    sign out UI     */    private void signOutUI() {
         googleSignInButton.setVisibility(View.GONE);
         usrNotSignedIn.setVisibility(View.GONE);
         googleSignOutButton.setVisibility(View.VISIBLE);
         viewContainer.setVisibility(View.VISIBLE);
     }



     /*    SignIn UI     */    private void signInUI() {
         googleSignInButton.setVisibility(View.VISIBLE);
         usrNotSignedIn.setVisibility(View.VISIBLE);
         googleSignOutButton.setVisibility(View.GONE);
         viewContainer.setVisibility(View.GONE);
     }


     /*    called when the connection is suspended     */
     @Override    public void onConnectionSuspended(int arg0) {
         mGoogleApiClient.connect();
         signInUI();
     }





     public void onClick(View v) {
         switch (v.getId()) {
             case R.id.sign_in_button:
                 onSignInClicked();
                 break;
             case R.id.sign_out_button:
                 onSignOutClicked();
                 break;
         }
     }





     /*    called when signIn Button is clicked     */    private void onSignInClicked() {
         if (!mGoogleApiClient.isConnecting()) {
             mShouldResolve = true;
             resolveSignInError();
         }
     }


       private void onSignOutClicked() {
         if (mGoogleApiClient.isConnected()) {
         Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
         mGoogleApiClient.disconnect();
         signInUI();
     }
 }


     /**
      * ATTENTION: This was auto-generated to implement the App Indexing API.
      * See https://g.co/AppIndexing/AndroidStudio for more information.
      */
     public Action getIndexApiAction() {
         Thing object = new Thing.Builder()
                 .setName("Main Page") // TODO: Define a title for the content shown.
                 // TODO: Make sure this auto-generated URL is correct.
                 .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                 .build();
         return new Action.Builder(Action.TYPE_VIEW)
                 .setObject(object)
                 .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                 .build();
     }
 }


