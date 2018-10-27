package mobpair.com.newlibprj;

/**
 * Created by ${Mobpair} on 12/3/18.
 */

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import mobpair.com.mylibrary.InstallTrack.TrackLib;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        TrackLib.getInstance().updateFCMToken(refreshedToken, new TrackLib.sendTOFcm() {
            @Override
            public void sendtofcm(String pubId, String offerId, String clickId, String track1, String track2, String track3, String track4, String track5, String track6, String track7, String track8, String track9, String track10, String track11, String track12) {
                Log.d(TAG, "fcm" + pubId + "::" + offerId + "::" + clickId + "::" + track1);
            }
        });
    }
}