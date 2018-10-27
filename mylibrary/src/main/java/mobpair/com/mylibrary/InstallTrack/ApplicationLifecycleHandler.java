package mobpair.com.mylibrary.InstallTrack;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ${Mobpair} on 7/3/18.
 */

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class ApplicationLifecycleHandler implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {

    private static final String TAG = ApplicationLifecycleHandler.class.getName();
    private static boolean isInBackground = true;
    private String getDatePref;
    private Util util;

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

        util = new Util(activity);

        if (InternetConnectionClass.getInstance(activity).isOnline()) {
            Boolean res = util.getErrorResponse();
            String apiKey = util.getApiKey();
            String serverKey = util.getServerKey();
            String userAgent = util.getUserAgent();
            String refferer_chk = util.getRefferer();
            String domainendpoint = util.getDomainEndPoint();

            if (res) {
                new Util.callapi(util.getFCMToken(), apiKey, serverKey, userAgent, refferer_chk, "INSTALL", domainendpoint, new Util.ThereIsSomeDataToGet() {
                    @Override
                    public void infofun(String pubId, String offerId, String clickId, String track1, String track2, String track3, String track4, String track5, String track6, String track7, String track8, String track9, String track10, String track11, String track12) {

                    }
                }).execute();
            } else {
            }
        } else {
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.d(TAG, "onActivityStarted");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Date date = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(date);

        getDatePref = util.getCurrentDate();

        if (isInBackground) {
            if (getDatePref.equalsIgnoreCase(formattedDate)) {
            } else {
                boolean isFirstTime = util.getIsFirstTime();

                if (isFirstTime) {
                } else {
                    Boolean isInstall = util.getBoolean();

                    String fcmtoken, serverkey, apikey, useragent, clickId, eventId = "ACTIVE", domainendpoint;

                    fcmtoken = util.getFCMToken();
                    serverkey = util.getServerKey();
                    apikey = util.getApiKey();
                    useragent = util.getUserAgent();
                    clickId = util.getClickID();
                    domainendpoint = util.getDomainEndPoint();
                    util.setCurrentDate(formattedDate);

                    if (isInstall) {
                        new Util.callapi(fcmtoken, apikey, serverkey, useragent, clickId, eventId, domainendpoint, new Util.ThereIsSomeDataToGet() {
                            @Override
                            public void infofun(String pubId, String offerId, String clickId, String track1, String track2, String track3, String track4, String track5, String track6, String track7, String track8, String track9, String track10, String track11, String track12) {

                            }
                        }).execute();
                    }
                }
            }
            isInBackground = false;
        } else {
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    @Override
    public void onTrimMemory(int i) {
        if (i == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            isInBackground = true;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {

    }

    @Override
    public void onLowMemory() {

    }
}