package mobpair.com.newlibprj;

import android.app.Application;

import mobpair.com.mylibrary.InstallTrack.TrackLib;

/**
 * Created by ${Mobpair} on 29/3/18.
 */
public class myapplicationclass extends Application {
    String serverKey = "AAAAe1P9fS0:APA91bHDi-vIZpjhxACh-sjqzbdTRFHys2mz_laqshWf-ys9OnLXaOpoHiCL7_NSFo4xmZ_LJU3iV5kHIrlEY7fDVXmDCZab73-O8l2j45XpefT6Sx2CrK_CdbfHdUfE37scq--WuUMM";
    String apiKey = "91b633a132e0f673e3c9d535feb8297e_5b5ac1af8e199504a2137efe";

    @Override
    public void onCreate() {
        super.onCreate();
        TrackLib.getInstance().init(this,serverKey,apiKey,"http://moboaff.makeaff.com/site/track.html?", new TrackLib.sendTOFcm() {
            @Override
            public void sendtofcm(String pubId, String offerId, String clickId, String track1, String track2, String track3, String track4, String track5, String track6, String track7, String track8, String track9, String track10, String track11, String track12) {

            }
        });

    }
}