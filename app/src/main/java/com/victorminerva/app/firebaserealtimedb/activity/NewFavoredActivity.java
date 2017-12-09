package com.victorminerva.app.firebaserealtimedb.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.victorminerva.app.firebaserealtimedb.R;
import com.victorminerva.app.firebaserealtimedb.model.Favored;

public class NewFavoredActivity extends AppCompatActivity {

    private static final String TAG = NewFavoredActivity.class.getName();

    private DatabaseReference mDatabase;
    private DatabaseReference favoredCloudEndPoint;

    private AppCompatEditText mNameFiela;
    private AppCompatEditText mEmailField;
    private AppCompatEditText mNumberField;
    private AppCompatButton mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_favored);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        favoredCloudEndPoint = mDatabase.child("favoreds");

        mNameFiela = findViewById(R.id.edt_name);
        mEmailField = findViewById(R.id.edt_email);
        mNumberField = findViewById(R.id.edt_number);

        mSubmitButton = findViewById(R.id.btn_cadastrar);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitFavored();
            }
        });
    }

    private void submitFavored() {
        final String name = mNameFiela.getText().toString();
        final String email = mEmailField.getText().toString();
        final String number = mNumberField.getText().toString();

        // Name is required
        if (TextUtils.isEmpty(name)) {
            mNameFiela.setError("Required");
            return;
        }

        // Email is required
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required");
            return;
        }

        mSubmitButton.setEnabled(false);

        favoredCloudEndPoint.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                writeNewFavored(name, email, number);
                mSubmitButton.setEnabled(true);
                finish();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "submitFavored:onCancelled", databaseError.toException());
                mSubmitButton.setEnabled(true);
            }
        });

    }

    private void writeNewFavored(String name, String email, String number) {
        Favored favored = new Favored();
        String key = favoredCloudEndPoint.push().getKey();

        favored.setFavoredID(key);
        favored.setName(name);
        favored.setEmail(email);
        if (number == null || "".equals(number.trim())) {
            favored.setNumberPhone(null);
        }else{
            favored.setNumberPhone(Long.parseLong(number));
        }
        favoredCloudEndPoint.child(favored.getFavoredID()).setValue(favored);

    }

}
