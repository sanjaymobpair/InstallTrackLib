package mobpair.com.mylibrary.InstallTrack;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * when UncaughtException occur and crash app that this class help to reopen app
 */
public class MyExceptionHandler implements Thread.UncaughtExceptionHandler {
    private Application application;

    MyExceptionHandler(Application a) {
        application = a;
    }

    /**
     * Reopen app if app is crash
     *
     * @param thread
     * @param throwable thorough the particilar Error by crash app
     */
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        Intent intent = new Intent(application, ApplicationLifecycleHandler.class);
        intent.putExtra("crash", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(application.getBaseContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager mgr = (AlarmManager) application.getBaseContext().getSystemService(Context.ALARM_SERVICE);
        assert mgr != null;
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 70000, pendingIntent);
        System.exit(2);
    }
}