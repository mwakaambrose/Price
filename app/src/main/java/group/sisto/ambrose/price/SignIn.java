package group.sisto.ambrose.price;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import group.sisto.ambrose.price.datamodel.Persons;
import group.sisto.ambrose.price.httpmanager.HttpManager;
import group.sisto.ambrose.price.httpmanager.RequestPackager;

public class SignIn extends AppCompatActivity {

    Button loginBtn, signUpBtn;
    EditText username, password;
    TextView result;
    Persons persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();

        persons = new Persons();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);
        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.login_password);
        result = (TextView) findViewById(R.id.status);
        loginBtn = (Button) findViewById(R.id.login_Btn);
        signUpBtn = (Button) findViewById(R.id.signup_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!username.getText().toString().isEmpty()) {
                    if (!password.getText().toString().isEmpty()) {
                        String baseURL = "http://ambroseogwang/Price/users/user_logon.php?";
                        String encordedParam = "username='" + RequestPackager.encodeValue(username.getText().toString()) + "'&password='" + RequestPackager.encodeValue(password.getText().toString()) + "'";
                        MyTask task = new MyTask(SignIn.this);
                        task.execute(baseURL + encordedParam);
                    } else {
                        result.setText("");
                        result.setText("Please provide a password");
                    }
                } else {
                    result.setText("");
                    result.setText("Please provide a username");
                }

            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
            }
        });
    }

    private void updateDisplay(String s) {
        if (s != null) {
            startActivity(new Intent(this, PostUpdate.class));
        } else {
            result.append("Unknow error: " + s);
        }
    }

    private class MyTask extends AsyncTask<String, String, String> {
        private ProgressDialog progressDialog;

        public MyTask(Context ctx) {
            progressDialog = new ProgressDialog(ctx);
        }

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Signing in...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            return HttpManager.postData(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            //updateDisplay(new PersonJSONParser().parseData(s));
            updateDisplay(s);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

}
