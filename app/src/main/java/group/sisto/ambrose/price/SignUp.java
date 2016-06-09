package group.sisto.ambrose.price;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import group.sisto.ambrose.price.httpmanager.HttpManager;

public class SignUp extends AppCompatActivity {

    final String AND = "&";
    EditText username, password, shopname, shopnumber, phonenumber;
    Button signup;
    String BASE_URL = "http://ambroseogwang.com/Price/users/user_signup.php?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        username = (EditText) findViewById(R.id.signup_username);
        password = (EditText) findViewById(R.id.signup_password);
        shopname = (EditText) findViewById(R.id.shopname);
        shopnumber = (EditText) findViewById(R.id.shopnumber);
        phonenumber = (EditText) findViewById(R.id.phonenumber);

        signup = (Button) findViewById(R.id.signup_btn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = "username='" + username.getText().toString() + "'" + AND + "'password='" + password.getText().toString() + "'" + AND + "shopname='" + shopname.getText().toString() + "'" + AND + "shopnumber=" + shopnumber.getText().toString() + AND + "phonenumber='" + phonenumber.getText().toString() + "'";
                MyTask task = new MyTask();
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, BASE_URL + query);
            }
        });


    }

    private void updateDisplay(String s) {
        if (s != null) {
            Toast.makeText(SignUp.this, s, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(SignUp.this, s, Toast.LENGTH_SHORT).show();
        }
    }

    private class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... params) {
            return HttpManager.postData(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            //updateDisplay(new PersonJSONParser().parseData(s));
            updateDisplay(s);
        }
    }
}
