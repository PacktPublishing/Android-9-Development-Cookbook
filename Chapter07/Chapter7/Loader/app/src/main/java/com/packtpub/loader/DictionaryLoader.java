package com.packtpub.loader;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

public class DictionaryLoader extends CursorLoader {
    Context mContext;
    public DictionaryLoader(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public Cursor loadInBackground() {
        DictionaryDatabase db = new DictionaryDatabase(mContext);
        return db.getWordList();
    }
}