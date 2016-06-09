package group.sisto.ambrose.price;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import group.sisto.ambrose.price.httpmanager.HttpManager;

public class PostDemo extends AppCompatActivity {

    Button loginBtn;
    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_demo);
        getSupportActionBar().hide();
        loginBtn = (Button) findViewById(R.id.subButton);
        username = (EditText) findViewById(R.id.subUsername);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change the endp point url where the data is being posted
                String baseURL = "http://ambroseogwang.com/Price/subscription/subscribe.php?";
                //encode the data correctly. and walla you are done.
                String encordedParam = "username='" + username.getText().toString() + "'";
                MyTask task = new MyTask();
                task.execute(baseURL + encordedParam);
            }
        });
    }

    private void updateDisplay() {
        Toast.makeText(PostDemo.this, "Subscibed succesfully.", Toast.LENGTH_SHORT).show();
        username.setText("");
    }

    private class MyTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            return HttpManager.postData(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            updateDisplay();
        }
    }
}
