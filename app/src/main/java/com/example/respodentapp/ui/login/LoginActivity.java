package com.example.respodentapp.ui.login;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.respodentapp.MainActivity;
import com.example.respodentapp.MapsActivity;
import com.example.respodentapp.R;
import com.example.respodentapp.data.model.Car;
import com.example.respodentapp.data.model.Case;
import com.example.respodentapp.data.model.Coordinates;
import com.example.respodentapp.data.model.ResLocaton;
import com.example.respodentapp.data.model.Respondent;
import com.example.respodentapp.data.model.UserClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    //private LoginViewModel loginViewModel;
    EditText usernameEditText, passwordEditText;
    ProgressBar loadingProgressBar;
    private FirebaseAuth mAuth;
    FirebaseFirestore firestore ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);
        Button loginButton = (Button) findViewById(R.id.login);
        loadingProgressBar = (ProgressBar) findViewById(R.id.loading);
        Button button = (Button) findViewById(R.id.login);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // Assume Login in was successful
                showDialog();
                hideSoftKeyboard();
                Login();
               // Intent intent = new Intent(LoginActivity.this, MainActivity.class);
               // LoginActivity.this.startActivity(intent);
                hideDialog();
            }
        });
    }
void Login(){
    Task<AuthResult> authResultTask = FirebaseAuth.getInstance().signInWithEmailAndPassword(usernameEditText.getText().toString(), passwordEditText.getText().toString())
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        final FirebaseUser user = mAuth.getCurrentUser();
                        final String id =user.getUid();
                        final String email = user.getEmail();
                        FirebaseDatabase db = FirebaseDatabase.getInstance();
                        DatabaseReference userRef = db.getReference("respondents").child(user.getUid());
                        Toast.makeText(LoginActivity.this,  user.getUid()+"Authentication failed.", Toast.LENGTH_SHORT).show();

                        userRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                // This method is called once with the initial value and again
                                // whenever data at this location is updated.
                                //Respondent value = dataSnapshot.getValue(Respondent.class);

                                String name =  dataSnapshot.child("name").getValue(String.class);
                                String surname =  dataSnapshot.child("surname").getValue(String.class);
                                Date timestamp=dataSnapshot.child("timestamp").getValue(Date.class);
                                ArrayList<Case> cases=new ArrayList<>();
                                long i= 0;
                                while(i<dataSnapshot.child("cases").getChildrenCount()) {
                                    Case ccase = dataSnapshot.child("cases").child("" + (int) i).getValue(Case.class);
                                    cases.add(ccase);
                                    i++;
                                }
                                 Car car =dataSnapshot.child("Car").getValue(Car.class);
                                Coordinates location =dataSnapshot.child("location").getValue(Coordinates.class);
                                Respondent resp =new Respondent(id,email,name,surname,timestamp,cases);
                                ResLocaton resLoc =new ResLocaton(resp,car,location);
                                ((UserClient) (getApplicationContext())).setUser(resLoc);
                                //Toast.makeText(LoginActivity.this, cases.get(0).getDiscription() +location.getLatitude() +car.getMake()+cases.size()+" caselength "+surname+name+"Authentication failed.", Toast.LENGTH_SHORT).show();
                                //Log.d(TAG, "Value is: " + value);
                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                                // Failed to read value
                                Log.w(TAG, "Failed to read value.", error.toException());
                            }
                        });



                       // ((UserClient) (getApplicationContext())).setUser();
                        finish();Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        LoginActivity.this.startActivity(intent);


                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        registrUser();
                        //finish();
                    }
                }
            });

}
   public Respondent MakeRespondent() {
       FirebaseUser user = mAuth.getCurrentUser();
       String id =user.getUid();
       String email = user.getEmail();
       String name = "ResName";
       String surname = "ResName";

       Date timestamp=new Date("06/06/2005");

       ArrayList<Case> cases=new ArrayList<>();
       Case case1=new Case("DD/MM/YYYY","HH:MM","Case1 discription");
       Case case2=new Case("DD/MM/YYYY","HH:MM","Case2 discription");
       cases.add(case1);
       cases.add(case2);
       Respondent resp =new Respondent(id,email,name,surname,timestamp,cases);

        return resp;
    }



void registrUser(){
    Task<AuthResult> authResultTask = FirebaseAuth.getInstance().createUserWithEmailAndPassword(usernameEditText.getText().toString(), passwordEditText.getText().toString())
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Log.d(TAG, "redirectLoginScreen: redirecting to Map screen.");
                        Respondent resp = MakeRespondent();
                   //     DocumentReference documentReference=firestore.collection("respondents").document(user.getUid());
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("respondents").child(user.getUid());
                        Car ccar =new Car("Make","Model", "RegNo");
                        Coordinates location= new Coordinates(45,45);
                        myRef.setValue(resp);
                        myRef.child("Car").setValue(ccar);
                        myRef.child("location").setValue(location);
                        ResLocaton resLocaton = new ResLocaton(resp,ccar,location);

                        ((UserClient) (getApplicationContext())).setUser(resLocaton);


                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        //Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                        LoginActivity.this.startActivity(intent);

                       finish();


                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        //  updateUI(null);
                       // finish();
                    }
                }
            });
}

    /**
     * Redirects the user to the login screen
     */
    private void redirectLoginScreen() {
        Log.d(TAG, "redirectLoginScreen: redirecting to login screen.");

        Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
        LoginActivity.this.startActivity(intent);
        finish();
    }


    private void showDialog() {
        loadingProgressBar.setVisibility(View.VISIBLE);

    }

    private void hideDialog() {
        if (loadingProgressBar.getVisibility() == View.VISIBLE) {
            loadingProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void hideSoftKeyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

}
