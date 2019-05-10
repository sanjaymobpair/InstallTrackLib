package mobpair.com.mylibrary.InstallTrack;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.webkit.WebView;

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
        } else {
        }
    }


    public void getKey(final String key) {
        fcmToken = key;
        util.setFCMToken(key);
        //updateFCMToken(key);
    }

    /**
     * it will store all info into sharefpreference
     *
     * @param application
     * @param serverkey
     * @param apikey
     * @param domainendpoint
     * @param sendTOFcm
     */
    public void init(Application application, String serverkey, String apikey, String domainendpoint, sendTOFcm sendTOFcm) {
        util = new Util(application);
        serverKey = serverkey;
        apiKey = apikey;
        domainEndPoint = domainendpoint;


        if (util.getServerKey().equals("null")) {
            util.setServerKey(serverkey);
        }
        if (util.getApiKey().equals("null")) {
            util.setApiKey(apikey);
        }
        if (util.getDomainEndPoint().equals("null")) {
            util.setDomainEndPoint(domainendpoint);
        }

        // TODO: 2019-05-09 get user Agent
        userAgent = new WebView(application).getSettings().getUserAgentString();
        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(application));
        util.setUserAgent(userAgent);

        if (util.getRefferer() != null) {
            refferer_chk = util.getRefferer();
        }
        if (refferer_chk != null) {
        } else {
        }

        // TODO: 2019-05-09 Application Handler
        ApplicationLifecycleHandler handler = new ApplicationLifecycleHandler();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            application.registerActivityLifecycleCallbacks(handler);
            application.registerComponentCallbacks(handler);
        }

    }

    public void updateFCMToken(String fcmtoken, final sendTOFcm dosome) {
        util.setFCMToken(fcmtoken);
        util.setIsFirstTime(false);

        String eventId = "INSTALL";
        Boolean res = util.getBoolean();

        if (serverKey.equals("null") || apiKey.equals("null") || domainEndPoint.equals("null")) {
            Log.d(TAG, " Please Reopen Your App..not getting some data");
        } else {
            if (res) {
            } else {
                new Util.callapi(fcmtoken, apiKey, serverKey, userAgent, refferer_chk, eventId, domainEndPoint, new Util.ThereIsSomeDataToGet() {
                    @Override
                    public void infofun(String pubId, String offerId, String clickId, String track1, String track2, String track3, String track4, String track5, String track6, String track7, String track8, String track9, String track10, String track11, String track12) {
                        dosome.sendtofcm(pubId, offerId, clickId, track1, track2, track3, track4, track5, track6, track7, track8, track9, track10, track11, track12);
                    }
                }).execute();
            }
        }

    }

   /* public void serverKey(String serverkey) {
        if (util.getServerKey() == null) {
            util.setServerKey(serverkey);
            serverKey = serverkey;
        }
    }

    public void apiKey(String apikey) {
        if (util.getApiKey() == null) {
            util.setApiKey(apikey);
            apiKey = apikey;
        }
    }

    public void domainEndPoint(String domainendpoint) {
        if (util.getDomainEndPoint() == null) {
            domainEndPoint = domainendpoint;
            util.setDomainEndPoint(domainendpoint);
        }
    }
*/

    /**
     * Tracking stage also u can track activity
     *
     * @param stage
     * @param sendTOFcm
     */
    public void gameStage(String stage, final sendTOFcm sendTOFcm) {
        Boolean isInstall = util.getBoolean();

        String fcmtoken, serverkey, apikey, useragent, clickId, domainendpoint;

        fcmtoken = util.getFCMToken();
        serverkey = util.getServerKey();
        apikey = util.getApiKey();
        useragent = util.getUserAgent();
        clickId = util.getClickID();
        domainendpoint = util.getDomainEndPoint();

        // TODO: 2019-05-09 If isInstall true than it will call api
        if (isInstall) {
            new Util.callapi(fcmtoken, apikey, serverkey, useragent, clickId, stage, domainendpoint, new Util.ThereIsSomeDataToGet() {
                @Override
                public void infofun(String pubId, String offerId, String clickId, String track1, String track2, String track3, String track4, String track5, String track6, String track7, String track8, String track9, String track10, String track11, String track12) {
                    sendTOFcm.sendtofcm(pubId, offerId, clickId, track1, track2, track3, track4, track5, track6, track7, track8, track9, track10, track11, track12);
                }

            }).execute();
        }
    }

    // TODO: 2019-05-09 Interface to handle Call BAck and send all insof to Main App
    public interface sendTOFcm {
        void sendtofcm(String pubId, String offerId, String clickId, String track1, String track2, String track3, String track4, String track5, String track6, String track7, String track8, String track9, String track10, String track11, String track12);
    }
}