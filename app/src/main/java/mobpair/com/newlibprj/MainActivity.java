package mobpair.com.newlibprj;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import mobpair.com.mylibrary.InstallTrack.TrackLib;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrackLib.getInstance().gameStage("stage1", new TrackLib.sendTOFcm() {
                    @Override
                    public void sendtofcm(String pubId, String offerId, String clickId, String track1, String track2, String track3, String track4, String track5, String track6, String track7, String track8, String track9, String track10, String track11, String track12) {

                    }
                });
            }
        });
    }

}