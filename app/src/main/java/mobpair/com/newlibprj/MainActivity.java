package mobpair.com.newlibprj;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
                TrackLib.getInstance().gameStage("stage1");
            }
        });
        Toast.makeText(this, "stage called...", Toast.LENGTH_SHORT).show();
    }
}