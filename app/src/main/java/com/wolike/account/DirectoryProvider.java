package com.wolike.account;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.wolike.ads.AdsLog;


public class DirectoryProvider extends ContentProvider {
    private static final String AUTHORITY = "com.android.cts.contact.directory.provider";
    private static final String CONFIG_NAME = "config";
    private static final String DEFAULT_CONTACT_NAME = "DirectoryContact";
    private static final String DEFAULT_DISPLAY_NAME = "Directory";
    private static final int GAL_BASE = 0;
    private static final int GAL_CALLABLES_FILTER = 7;
    private static final int GAL_CONTACT = 2;
    private static final int GAL_CONTACT_WITH_ID = 3;
    private static final int GAL_DIRECTORIES = 0;
    private static final int GAL_EMAIL_FILTER = 4;
    private static final int GAL_EMAIL_LOOKUP = 8;
    private static final int GAL_FILTER = 1;
    private static final int GAL_MANAGED_PHOTO = 12;
    private static final int GAL_MANAGED_THUMBNAIL = 11;
    private static final int GAL_PHONE_FILTER = 5;
    private static final int GAL_PHONE_LOOKUP = 6;
    private static final int GAL_PRIMARY_PHOTO = 10;
    private static final int GAL_PRIMARY_THUMBNAIL = 9;

    private static final String SET_CUSTOM_PREFIX = "set_prefix";
    private static final String TEST_ACCOUNT_TYPE = "com.android.cts.test";
    private SharedPreferences mSharedPrefs;
    private final UriMatcher mURIMatcher = new UriMatcher(-1);


    public boolean onCreate() {
        AdsLog.d("DirectoryProvider onCreate");


        return true;
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String str;
        String str2;
        String[] strArr = projection;
        String prefix = getPrefix();
        switch (this.mURIMatcher.match(uri)) {
            case 0:
                MatrixCursor cursor = new MatrixCursor(strArr);
                AccountManager ac = (AccountManager) getContext().getSystemService(Context.ACCOUNT_SERVICE);
                Account[] accounts = ac.getAccountsByType(TEST_ACCOUNT_TYPE);
                if (accounts != null) {
                    int length = accounts.length;
                    int i = 0;
                    while (i < length) {
                        Account account = accounts[i];
                        Object[] row = new Object[strArr.length];
                        int i2 = 0;
                        while (i2 < strArr.length) {
                            String column = strArr[i2];
                            if (column.equals("accountName")) {
                                row[i2] = account.name;
                            } else if (column.equals("accountType")) {
                                row[i2] = TEST_ACCOUNT_TYPE;
                            } else if (column.equals("typeResourceId")) {

                            } else if (column.equals("displayName")) {
                                row[i2] = prefix + DEFAULT_DISPLAY_NAME;
                            } else if (column.equals("exportSupport")) {
                                row[i2] = 1;
                            } else if (column.equals("shortcutSupport")) {
                                row[i2] = 0;
                            }
                            i2++;
                            strArr = projection;
                        }
                        cursor.addRow(row);
                        i++;
                        strArr = projection;
                    }
                }
                return cursor;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            default:
                return null;
        }
    }

    public String getType(Uri uri) {
        return null;
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException();
    }

    public Uri insert(Uri uri, ContentValues values) {
        throw new UnsupportedOperationException();
    }

    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException();
    }

    public Bundle call(String method, String arg, Bundle extras) {
        if (method.equals(SET_CUSTOM_PREFIX)) {
            this.mSharedPrefs.edit().putString(SET_CUSTOM_PREFIX, arg).apply();
            long token = Binder.clearCallingIdentity();
            getContext().getContentResolver().update(ContactsContract.Directory.CONTENT_URI, new ContentValues(), null, null);
            Binder.restoreCallingIdentity(token);
        }
        return new Bundle();
    }

    @Override // android.content.ContentProvider
    public AssetFileDescriptor openAssetFile(Uri uri, String mode) {
        if (mode.equals("r")) {
            int resId = 0;
            switch (this.mURIMatcher.match(uri)) {

                default:
                    resId = 0;
                    break;
            }
            if (resId == 0) {
                return null;
            }
            return getContext().getResources().openRawResourceFd(resId);
        }
        throw new IllegalArgumentException("mode must be \"r\"");
    }

    private String getPrefix() {
        return "prefix";
    }

    private boolean isManagedProfile() {
        return "Managed".equals(getPrefix());
    }
}