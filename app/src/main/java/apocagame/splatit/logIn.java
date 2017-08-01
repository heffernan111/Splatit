package apocagame.splatit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class logIn extends AppCompatActivity {
    LoginButton login_button;
    CallbackManager callbackManager;
    TextView txtStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_log_in);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken !=null){
            Intent mainMenuIntent = new Intent(this, MenuAcitivity.class);
            startActivity(mainMenuIntent);

        }else
        initializeControls();
        loginWithFB();

    }

    private  void initializeControls(){
        callbackManager = CallbackManager.Factory.create();
        login_button = (LoginButton)findViewById(R.id.login_button);
        txtStatus = (TextView)findViewById(R.id.txtStatus);

    }

    private void loginWithFB(){
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent j = new Intent(getApplicationContext(),MenuAcitivity.class);
                startActivity(j);

            }

            @Override
            public void onCancel() {
                txtStatus.setText("Login Cancelled");

            }

            @Override
            public void onError(FacebookException error) {
                txtStatus.setText("Login Error"+error.getMessage());

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


}
