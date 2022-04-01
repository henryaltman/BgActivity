package l1L1L1L1L1l1LLL1LL1ll.lilil1liLi1lLL1l1l.Ll1lLl1l1LL1l1Ll.Ll1lLl1l1LL1l1Ll;

import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.json.JSONObject;

/* compiled from: outline */
/* loaded from: classes.dex */
public class Ll1lLl1l1LL1l1Ll {
    public static float Ll1lLl1l1LL1l1Ll(float f, float f2, float f3, float f4) {
        return ((f - f2) * f3) + f4;
    }

    public static int Ll1lLl1l1LL1l1Ll(int i2, int i3, int i4, int i5) {
        return i2 + i3 + i4 + i5;
    }

    public static int Ll1lLl1l1LL1l1Ll(String str, JSONObject jSONObject, String str2) {
        return jSONObject.optInt(str2, new Integer(str).intValue());
    }



    public static String Ll1lLl1l1LL1l1Ll(long j, SimpleDateFormat simpleDateFormat) {
        return simpleDateFormat.format(new Date(j));
    }



    public static String Ll1lLl1l1LL1l1Ll(Exception exc, StringBuilder sb) {
        sb.append(exc.toString());
        return sb.toString();
    }

    public static String Ll1lLl1l1LL1l1Ll(Object obj, StringBuilder sb) {
        sb.append(obj.getClass().getName());
        return sb.toString();
    }

    public static String Ll1lLl1l1LL1l1Ll(Object obj, StringBuilder sb, String str) {
        sb.append(obj.getClass().getName());
        sb.append(str);
        return sb.toString();
    }

    public static String Ll1lLl1l1LL1l1Ll(String str, int i2) {
        return str + i2;
    }

    public static String Ll1lLl1l1LL1l1Ll(String str, int i2, String str2) {
        return str + i2 + str2;
    }

    public static String Ll1lLl1l1LL1l1Ll(String str, long j) {
        return str + j;
    }

    public static String Ll1lLl1l1LL1l1Ll(String str, Uri uri) {
        return str + uri;
    }

    public static String Ll1lLl1l1LL1l1Ll(String str, Fragment fragment, String str2) {
        return str + fragment + str2;
    }

    public static String Ll1lLl1l1LL1l1Ll(String str, File file) {
        return str + file;
    }

    public static String Ll1lLl1l1LL1l1Ll(String str, File file, String str2) {
        return str + file + str2;
    }

    public static String Ll1lLl1l1LL1l1Ll(String str, IOException iOException) {
        return str + iOException;
    }

    public static String Ll1lLl1l1LL1l1Ll(String str, Exception exc) {
        return str + exc;
    }

    public static String Ll1lLl1l1LL1l1Ll(String str, String str2) {
        return str + str2;
    }

    public static String Ll1lLl1l1LL1l1Ll(String str, String str2, String str3) {
        return str + str2 + str3;
    }



    public static String Ll1lLl1l1LL1l1Ll(StringBuilder sb, int i2, String str) {
        sb.append(i2);
        sb.append(str);
        return sb.toString();
    }

    public static String Ll1lLl1l1LL1l1Ll(StringBuilder sb, int i2, String str, String str2) {
        sb.append(i2);
        return str.replaceAll(str2, sb.toString());
    }

    public static String Ll1lLl1l1LL1l1Ll(StringBuilder sb, String str, String str2) {
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }






    public static StringBuilder Ll1lLl1l1LL1l1Ll(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb;
    }

    public static StringBuilder Ll1lLl1l1LL1l1Ll(StringBuilder sb, String str, String str2, StringBuffer stringBuffer, String str3) {
        sb.append(str);
        sb.append(str2);
        stringBuffer.append(sb.toString());
        return new StringBuilder(str3);
    }


    public static ArrayList Ll1lLl1l1LL1l1Ll(HashMap hashMap, String str, ArrayList arrayList, String str2, String str3) {
        hashMap.put(str, arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(str2);
        arrayList2.add(str3);
        return arrayList2;
    }

    public static HashMap Ll1lLl1l1LL1l1Ll(String str, String str2, String str3, String str4) {
        HashMap hashMap = new HashMap();
        hashMap.put(str, str2);
        hashMap.put(str3, str4);
        return hashMap;
    }

    public static HashMap Ll1lLl1l1LL1l1Ll(HashMap hashMap, String str, HashMap hashMap2, int i2) {
        hashMap.put(str, hashMap2);
        return new HashMap(i2);
    }

    public static List Ll1lLl1l1LL1l1Ll() {
        return Collections.synchronizedList(new ArrayList());
    }



    public static void Ll1lLl1l1LL1l1Ll(SharedPreferences sharedPreferences, String str, long j) {
        sharedPreferences.edit().putLong(str, j).apply();
    }

    public static void Ll1lLl1l1LL1l1Ll(SharedPreferences sharedPreferences, String str, String str2) {
        sharedPreferences.edit().putString(str, str2).apply();
    }

    public static void Ll1lLl1l1LL1l1Ll(SharedPreferences sharedPreferences, String str, boolean z) {
        sharedPreferences.edit().putBoolean(str, z).apply();
    }

    public static void Ll1lLl1l1LL1l1Ll(String str, Exception exc, String str2) {
        Log.e(str2, str + exc);
    }

    public static void Ll1lLl1l1LL1l1Ll(String str, Throwable th, String str2) {
        Log.e(str2, str + th);
    }



    public static void Ll1lLl1l1LL1l1Ll(StringBuilder sb, String str, char c, String str2) {
        sb.append(str);
        sb.append(c);
        sb.append(str2);
    }



    public static void Ll1lLl1l1LL1l1Ll(ArrayList arrayList, String str, String str2, String str3, String str4) {
        arrayList.add(str);
        arrayList.add(str2);
        arrayList.add(str3);
        arrayList.add(str4);
    }

    public static StringBuilder lilil1liLi1lLL1l1l(String str, int i2, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(i2);
        sb.append(str2);
        return sb;
    }

    public static StringBuilder lilil1liLi1lLL1l1l(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        return sb;
    }

    public static StringBuilder lilil1liLi1lLL1l1l(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        return sb;
    }

    public static void lilil1liLi1lLL1l1l(String str, Fragment fragment, String str2) {
        Log.v(str2, str + fragment);
    }

    public static boolean lilil1liLi1lLL1l1l(String str) {
        return new File(str).exists();
    }
}