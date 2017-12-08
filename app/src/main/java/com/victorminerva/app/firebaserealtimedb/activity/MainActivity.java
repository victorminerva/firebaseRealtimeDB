package com.victorminerva.app.firebaserealtimedb.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.victorminerva.app.firebaserealtimedb.R;
import com.victorminerva.app.firebaserealtimedb.adapter.ItemLoanAdapter;
import com.victorminerva.app.firebaserealtimedb.model.Favored;
import com.victorminerva.app.firebaserealtimedb.model.Loan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getName();
    private DatabaseReference mDatabase;
    private DatabaseReference loanCloudEndPoint;
    private DatabaseReference favoredCloudEndPoint;
    private ListView listOfLoans;
    private ItemLoanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        favoredCloudEndPoint = mDatabase.child("favoreds");
        loanCloudEndPoint = mDatabase.child("loans");

        listOfLoans = findViewById(R.id.listLoans);

        //addInitialDataToFirebase();

        /** List of loans retrieves */
        final List<Loan> mLoans = new ArrayList<>();
        loanCloudEndPoint.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot, mLoans);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(LOG_TAG, databaseError.getMessage());
            }
        });


        /** List of favoreds */
//        final List<Favored> mFavoreds = new ArrayList<>();
//        favoredCloudEndPoint.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Favored favored = dataSnapshot.getValue(Favored.class);
//                Log.d("LoanFavored: ", favored.toString());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.d(LOG_TAG, databaseError.getMessage());
//            }
//        });
    }

    private void showData(DataSnapshot dataSnapshot, List<Loan> mLoans) {
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            Log.d("LoanFavored: ", snapshot.getKey());

            Loan loan = snapshot.getValue(Loan.class);
            Log.d("LoanFavored: ", loan.getWhatDescription());
            Log.d("LoanFavored: ", loan.getFavored().getName());
            Log.d("LoanFavored: ", "" + loan);
            mLoans.add(loan);
        }
        adapter = new ItemLoanAdapter(mLoans, MainActivity.this);
        listOfLoans.setAdapter(adapter);
    }

    public static List<Loan> getSampleLoans() {
        Calendar calendar1 = GregorianCalendar.getInstance();

        List<Loan> loans = new ArrayList<>();

        //create the dummy loan
        Loan loan = new Loan();
        //loan.setFavored(favored);
        loan.setWhat("Game");
        loan.setWhatDescription("God of War");
        loan.setUntilWhen(calendar1.getTime());
        loan.setReturned(false);
        loans.add(loan);

        return loans;
    }

    public static List<Favored> getSampleFavoreds() {
        List<Favored> favoreds = new ArrayList<>();
        //create the dummy favored
        Favored favored = new Favored();
        favored.setName("Victor Minerva");
        favored.setEmail("victorminerva.m@gmail.com");
        favored.setNumberPhone(85998711720L);

        favoreds.add(favored);

        return favoreds;
    }

    private void addInitialDataToFirebase() {

        List<Favored> sampleFavoreds = getSampleFavoreds();
        List<Loan> sampleLoans = getSampleLoans();

        for (Favored favored : sampleFavoreds) {
            String key = favoredCloudEndPoint.push().getKey();
            favored.setFavoredID(key);
            favored.setName("Victor Minerva");
            favored.setEmail("victorminerva.m@gmail.com");
            favored.setNumberPhone(85998711720L);
            favoredCloudEndPoint.child(favored.getFavoredID()).setValue(favored);

            for (Loan loan : sampleLoans) {
                String keyLoan = loanCloudEndPoint.push().getKey();
                loan.setLoanID(keyLoan);
                loan.setFavored(favored);
                loanCloudEndPoint.child(key).setValue(loan);
            }
        }
    }


}

