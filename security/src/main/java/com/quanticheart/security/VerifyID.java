package com.quanticheart.security;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.UUID;

class VerifyID {

    private static final String PREF_NAME = "CRY_C";
    private static final String PREF_KEY_SIMPLE = "K_S";
    private static final String PREF_KEY_WITH_KEY = "K_K";

    VerifyID(Context context) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        init(sharedPrefs, null);
    }

    VerifyID(Context context, String keyAccess) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        init(sharedPrefs, keyAccess);
    }

    private void init(SharedPreferences sharedPrefs, String keyAccess) {
        String uniqueID = null;

        if (keyAccess == null) {
            uniqueID = sharedPrefs.getString(PREF_KEY_SIMPLE, null);
        } else {
            uniqueID = sharedPrefs.getString(PREF_KEY_WITH_KEY, null);
        }

        if (uniqueID == null) {

            String sp = ":::";

            if (keyAccess == null) {
                uniqueID = UUID.randomUUID().toString() + sp;
            } else {
                uniqueID = UUID.randomUUID().toString() + keyAccess + sp;
            }

            SharedPreferences.Editor editor = sharedPrefs.edit();

            if (keyAccess == null) {
                editor.putString(PREF_KEY_SIMPLE, Encrypt.md5(uniqueID));
            } else {
                editor.putString(PREF_KEY_WITH_KEY, Encrypt.md5(uniqueID));
            }

            editor.apply();
        }

        Log.e("Key S", Encrypt.md5Decode(sharedPrefs.getString(PREF_KEY_SIMPLE, "ERROR!!!")));
        Log.e("Key K", Encrypt.md5Decode(sharedPrefs.getString(PREF_KEY_WITH_KEY, "ERROR!!!")));
    }
}