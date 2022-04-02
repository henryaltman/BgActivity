package defpackage;

import android.content.Context;
import androidx.core.app.NotificationCompat;

/* loaded from: classes.dex */
public interface CommonCallback {
    String getIntentChannelId(Context context);

    NotificationCompat.Builder getIntentNotificationBuilder(Context context);

    String getSyncAccountName();

    String getSyncAccountType();

    int getWallPaperPreviewRes();

    void moveHomeBack();

    void onInstrumentationCreate();

    void onWallPaperSurfaceCreated(boolean z);

    void onWallPaperSurfaceDestroyed(boolean z);
}