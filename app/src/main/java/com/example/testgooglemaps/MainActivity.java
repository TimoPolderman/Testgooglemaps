package com.example.testgooglemaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*https://www.youtube.com/watch?v=AV13zohVHBg*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText nameEdit = (EditText) findViewById(R.id.nameEdit);
        final EditText emailEdit = (EditText) findViewById(R.id.emailEdit);
        final EditText phoneEdit = (EditText) findViewById(R.id.phoneEdit);
        final EditText questionEdit = (EditText) findViewById(R.id.questionEdit);

        Button sendButton = (Button) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             String name = nameEdit.getText().toString();
             String email = emailEdit.getText().toString();
             String phone = phoneEdit.getText().toString();
             String question = questionEdit.getText().toString();
                if (TextUtils.isEmpty(name)){
                    nameEdit.setError("Enter a name");
                    nameEdit.requestFocus();
                    return;
                }
                Boolean onError = false;
                if (!isValidEmail(email)){
                    onError = true;
                    emailEdit.setError("Invalid email adress");
                    return;
                }
                if (TextUtils.isEmpty(phone)){
                    phoneEdit.setError("Enter a phone number");
                    phoneEdit.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(question)){
                    questionEdit.setError("Enter a question");
                    questionEdit.requestFocus();
                    return;
                }

                Intent sendEmail = new Intent(android.content.Intent.ACTION_SEND);

                
                sendEmail.setType("plain/text");
                sendEmail.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"timopolderman@gmail.com"});
                sendEmail.putExtra(android.content.Intent.EXTRA_SUBJECT, phone);
                sendEmail.putExtra(android.content.Intent.EXTRA_TEXT,
                        "name:"+name+'\n'+"Email ID:"+email+'\n'+"Message:"+'\n'+question);


                startActivity(Intent.createChooser(sendEmail, "Send mail..."));
            }
        });


    }

    // email check

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
