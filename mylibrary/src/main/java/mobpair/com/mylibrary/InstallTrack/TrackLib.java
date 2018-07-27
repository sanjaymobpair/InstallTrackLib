package mobpair.com.mylibrary.InstallTrack;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by ${Mobpair} on 21/3/18.
 */

public class TrackLib {
    private String TAG = TrackLib.class.getName();
    @SuppressLint("StaticFieldLeak")
    private static TrackLib instance = new TrackLib();
    private Util util;
    private String refferer_chk, serverKey, apiKey, fcmToken, domainEndPoint;
    private String userAgent;

    public static TrackLib getInstance() {
        return instance;
    }

    void onReceive(Context context, Intent intent) {
        String REFFERER_VALUE = "referrer";
        String referrer = intent.getStringExtra(REFFERER_VALUE);

        if (referrer != null) {
            util = new Util(context);
            util.setRefferer(referrer);
            Log.d(TAG, "refferer : " + referrer);
        } else {
            Log.d(TAG, "refferer : Else");
        }
    }

    public void init(Application application) {
        util = new Util(application);
        Log.d(TAG, "Init : ServerKey" + serverKey + "ApiKey :" + apiKey + "FcmToken" + fcmToken);
        userAgent = new WebView(application).getSettings().getUserAgentString();
        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(application));
        util.setUserAgent(userAgent);

        if (util.getRefferer() != null) {
            refferer_chk = util.getRefferer();
        }
        if (refferer_chk != null) {
            Log.d(TAG, "If : Refferer" + refferer_chk);
        } else {
            Log.d(TAG, "Else : Null Refferer");
        }

        ApplicationLifecycleHandler handler = new ApplicationLifecycleHandler();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            application.registerActivityLifecycleCallbacks(handler);
            application.registerComponentCallbacks(handler);
        }
    }

    public void updateFCMToken(String fcmtoken) {
        util.setFCMToken(fcmtoken);
        util.setIsFirstTime(false);

        fcmToken = fcmtoken;
        Log.d(TAG, "Token1 : " + fcmToken);
        Log.d(TAG, "Token1 : " + serverKey);
        Log.d(TAG, "Token1 : " + apiKey);
        Log.d(TAG, "Token1 : " + refferer_chk);
        Log.d(TAG, "user : " + userAgent);

        String eventId = "INSTALL";
        Boolean res = util.getBoolean();
        Log.d(TAG, "Boolean" + res);
        if (res) {

        } else {
            new Util.callapi(fcmToken, apiKey, serverKey, userAgent, refferer_chk, eventId, domainEndPoint).execute();
        }
    }

    public void serverKey(String serverkey) {
        if (util.getServerKey() == null) {
            util.setServerKey(serverkey);
            serverKey = serverkey;
        }
        Log.d(TAG, "Token : " + serverkey);
    }

    public void apiKey(String apikey) {
        if (util.getApiKey() == null) {
            util.setApiKey(apikey);
            apiKey = apikey;
        }
        Log.d(TAG, "Token : " + apikey);
    }

    public void domainEndPoint(String domainendpoint) {
        if (util.getDomainEndPoint() == null) {
            domainEndPoint = domainendpoint;
            util.setDomainEndPoint(domainendpoint);
        }
        Log.d(TAG, "Token : " + domainendpoint);
    }

    public void gameStage(String stage) {
        Boolean isInstall = util.getBoolean();

        Log.d(TAG, "isFirst : Else" + isInstall);
        String fcmtoken, serverkey, apikey, useragent, clickId, domainendpoint;

        fcmtoken = util.getFCMToken();
        serverkey = util.getServerKey();
        apikey = util.getApiKey();
        useragent = util.getUserAgent();
        clickId = util.getClickID();
        domainendpoint = util.getDomainEndPoint();

        Log.d(TAG, "gameStage : " + fcmtoken + " :: " + serverkey + " :: " + apikey + " :: " + useragent + " :: " + clickId + " :: " + domainendpoint);
        if (isInstall) {
            new Util.callapi(fcmtoken, apikey, serverkey, useragent, clickId, stage, domainendpoint).execute();
        }
    }
}