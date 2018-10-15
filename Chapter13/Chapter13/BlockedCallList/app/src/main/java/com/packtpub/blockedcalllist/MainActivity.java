package com.packtpub.blockedcalllist;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BlockedNumberContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextNumber=findViewById(R.id.editTextNumber);
    }

    public void onClickBlock(View view) {
        String number = mEditTextNumber.getText().toString();
        if (number!=null && number.length()>0) {
            blockNumber(number);
        }
    }
    public void onClickUnblock(View view) {
        String number = mEditTextNumber.getText().toString();
        if (number!=null && number.length()>0) {
            unblockNumber(number);
        }
    }
    public void onClickIsBlocked(View view) {
        String number = mEditTextNumber.getText().toString();
        if (number!=null && number.length()>0) {
            isBlocked(number);
        }
    }

    private void blockNumber(String number) {
        if (BlockedNumberContract.canCurrentUserBlockNumbers(this)) {
            ContentValues values = new ContentValues();
            values.put(BlockedNumberContract.BlockedNumbers.COLUMN_ORIGINAL_NUMBER, number);
            getContentResolver().insert(BlockedNumberContract.BlockedNumbers.CONTENT_URI, values);
        }
    }

    private void unblockNumber(String number) {
        if (BlockedNumberContract.canCurrentUserBlockNumbers(this)) {
            ContentValues values = new ContentValues();
            values.put(BlockedNumberContract.BlockedNumbers.COLUMN_ORIGINAL_NUMBER, number);
            Uri uri = getContentResolver()
                    .insert(BlockedNumberContract.BlockedNumbers.CONTENT_URI, values);
            getContentResolver().delete(uri, null, null);
        }
    }

    public void isBlocked(String number) {
        if (BlockedNumberContract.canCurrentUserBlockNumbers(this)) {
            boolean blocked = BlockedNumberContract.isBlocked(this,number);
            Toast.makeText(MainActivity.this, number + "blocked: " + blocked,
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "User cannot perform this operation",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void getBlockedList() {
        Cursor cursor = getContentResolver().query(
                BlockedNumberContract.BlockedNumbers.CONTENT_URI,
                new String[]{BlockedNumberContract.BlockedNumbers.COLUMN_ID,
                        BlockedNumberContract.BlockedNumbers.COLUMN_ORIGINAL_NUMBER,
                        BlockedNumberContract.BlockedNumbers.COLUMN_E164_NUMBER},
                null, null, null);
    }
}
