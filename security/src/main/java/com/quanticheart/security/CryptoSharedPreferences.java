package com.quanticheart.security;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

@SuppressWarnings("unused")
public class CryptoSharedPreferences implements SharedPreferences {

    private SharedPreferences s;
    private String key;

    public CryptoSharedPreferences(Context context, String namePrefs) {
        new VerifyID(context);
        this.s = context.getSharedPreferences(namePrefs, Context.MODE_PRIVATE);
    }

    public CryptoSharedPreferences(Context context, String namePrefs, String keyAcesse) {
        new VerifyID(context, keyAcesse);
        this.s = context.getSharedPreferences(namePrefs, Context.MODE_PRIVATE);
        key = keyAcesse;
    }

    @Override
    public Map<String, ?> getAll() {
        return this.s.getAll();
    }

    @Override
    public String getString(String s, String s1) {
        return this.s.getString(s, s1);
    }

    @Override
    public Set<String> getStringSet(String s, Set<String> set) {
        return this.s.getStringSet(s, set);
    }

    @Override
    public int getInt(String s, int i) {
        return this.s.getInt(s, i);
    }

    @Override
    public long getLong(String s, long l) {
        return this.s.getLong(s, l);
    }

    @Override
    public float getFloat(String s, float v) {
        return this.s.getFloat(s, v);
    }

    @Override
    public boolean getBoolean(String s, boolean b) {
        return this.s.getBoolean(s, b);
    }

    @Override
    public boolean contains(String s) {
        return this.s.contains(s);
    }

    @Override
    public CryptoEditor edit() {
        return new CryptoEditor(s);
    }

    @Override
    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        s.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        s.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    public class CryptoEditor implements Editor {

        private Editor e;

        @SuppressLint("CommitPrefEdits")
        CryptoEditor(SharedPreferences context) {
            this.e = context.edit();
        }

        @Override
        public Editor putString(String s, String s1) {
            return this.e.putString(s, s1);
        }

        @Override
        public Editor putStringSet(String s, Set<String> set) {
            return null;
        }

        @Override
        public Editor putInt(String s, int i) {
            return null;
        }

        @Override
        public Editor putLong(String s, long l) {
            return null;
        }

        @Override
        public Editor putFloat(String s, float v) {
            return null;
        }

        @Override
        public Editor putBoolean(String s, boolean b) {
            return null;
        }

        @Override
        public Editor remove(String s) {
            return this.e.remove(s);
        }

        @Override
        public Editor clear() {
            return this.e.clear();
        }

        @Override
        public boolean commit() {
            return this.e.commit();
        }

        @Override
        public void apply() {
            e.apply();
        }
    }
}
