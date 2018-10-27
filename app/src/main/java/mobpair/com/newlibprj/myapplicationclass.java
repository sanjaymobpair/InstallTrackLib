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
        TrackLib.getInstance().init(this,serverKey,apiKey,"http://moboaff.makeaff.com/site/track.html?");

       /* TrackLib.getInstance().serverKey(serverKey);
        TrackLib.getInstance().apiKey(apiKey);
        TrackLib.getInstance().domainEndPoint("moboaff.com");
*/

    }
}