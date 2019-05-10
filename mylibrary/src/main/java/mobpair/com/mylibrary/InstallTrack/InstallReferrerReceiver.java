package mobpair.com.mylibrary.InstallTrack;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by ${Mobpair} on 6/3/18.
 */

public class InstallReferrerReceiver extends BroadcastReceiver {
    private String TAG = "InstallReferrerReceiver";

    /**
     * it will call when app install from playstore this receiver will call and send intent to {@link TrackLib} Class
     * @param context
     * @param intent
     */
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    public void onReceive(Context context, Intent intent) {
        TrackLib.getInstance().onReceive(context, intent);
    }
}