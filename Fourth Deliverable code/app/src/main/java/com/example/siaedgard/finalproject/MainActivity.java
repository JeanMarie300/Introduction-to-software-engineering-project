package com.example.siaedgard.finalproject;

import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

/*    EditText LastName, FirstName ,Birthday , PostalCode, UserName, Password, address, number;
    TextView result;
    String userId, userType;
    private String [] answers = new String [7];
    HashMap<String, String> userInfo = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Password = (EditText) findViewById(R.id.Password);
        UserName = (EditText) findViewById(R.id.userName);
        PostalCode = (EditText) findViewById(R.id.PostalCode);
        Birthday = (EditText) findViewById(R.id.Birthday);
        FirstName = (EditText) findViewById(R.id.FirstName);
        LastName = (EditText) findViewById(R.id.LastName);
        address = (EditText) findViewById(R.id.streetName);
        number = (EditText) findViewById(R.id.number);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if (bd != null) {
            userType = bd.get("USER_TYPE").toString();
        }
    }
    public boolean newUser () {
        User user = new User("1",userInfo.get("FirstName"),userInfo.get("LastName"), userInfo.get("Birthday"), userInfo.get("PostalCode"), userType, userInfo.get("UserName"), userInfo.get("Password"),userInfo.get("address"));
        MyDBHandler dbHandler = new MyDBHandler(this);
        long userID = dbHandler.addUsers(user);
        if(userID == -2) {
            return false;
        } else {
            user.setId(Long.toString(userID));
            userId = Long.toString(userID);
            return true;
        }
    }

    boolean isLegalDate(String date) {
        SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy/MM/dd");
        sdfrmt.setLenient(false);
        try
        {
            sdfrmt.parse(date);
        }
        catch (ParseException e)
        {
            return false;
        }
        return true;
    }

    public void OnFinish(View view) {
        answers[0] = LastName.getText().toString();
        answers[1]=  FirstName.getText().toString();
        answers[2] = Birthday.getText().toString();
        answers[3] = PostalCode.getText().toString();
        answers[4] = UserName.getText().toString();
        answers[5] = Password.getText().toString();
        answers[6] = number.getText().toString() + address.getText().toString();

        boolean invalid = false;
        boolean isValidDate = false;
        boolean temp = false;
        int i =0;
        while(!invalid && i<7 ) {
            if (answers[i].isEmpty()) {
                invalid = true;
            }
            i++;
        }

        if (!invalid) {
            isValidDate = isLegalDate(Birthday.getText().toString());
        }
        if(invalid){
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Empty field alert");
            alert.setMessage("You need to fill up all the field");
            alert.setPositiveButton("OK",null);
            alert.show();
        } else if (!isValidDate) {
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Invalid date");
            alert.setMessage("Please enter a valid birthday");
            alert.setPositiveButton("OK",null);
            alert.show();
        } else {
            userInfo.put("FirstName",answers[1]);
            userInfo.put("LastName",answers[0]);
            userInfo.put("Birthday",answers[2]);
            userInfo.put("PostalCode",answers[3]);
            userInfo.put("UserName",answers[4]);
            userInfo.put("Password",answers[5]);
            userInfo.put("address",answers[6]);

            temp = newUser();
            if (!temp) {
                AlertDialog.Builder  alert = new AlertDialog.Builder(this);
                alert.setTitle("Username already taken");
                alert.setMessage("This username already exist");
                alert.setPositiveButton("OK",null);
                alert.show();
            } else {
                Intent intent = new Intent(this, AdminWelcomePage.class);
                intent.putExtra("FIRST_NAME",  userInfo.get("FirstName"));
                intent.putExtra("LAST_NAME", userInfo.get("LastName"));
                intent.putExtra("USER_TYPE", userType);
                intent.putExtra("USER_ID", userId);
                startActivity(intent);
                finish();
            }

        }
    }
    */
}