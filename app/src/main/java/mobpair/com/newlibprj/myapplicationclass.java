package mobpair.com.newlibprj;

import android.app.Application;

import mobpair.com.mylibrary.InstallTrack.TrackLib;

/**
 * Created by ${Mobpair} on 29/3/18.
 */
public class myapplicationclass extends Application {
    String serverKey = "AAAAb_umDsM:APA91bHbTsKqQUvzxJYXDXzQNFmN-myD165hmT6ROC-3FDg0DFwpa80CM2PcBmI_uUoch5v2GL2490_CkyMcBj3QVHyzBwV69XHMUZW-sCTpSgALCdUfJILn2WmYCR_wbq-oGvjHVjqF";
    String apiKey = "8ed81f399e8c8d849b22f5e3a8df3712_5ae55f00ea2a18046b01c981";

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