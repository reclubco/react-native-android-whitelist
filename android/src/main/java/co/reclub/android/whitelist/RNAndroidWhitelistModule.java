
package co.reclub.android.whitelist;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatCheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;


import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class RNAndroidWhitelistModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNAndroidWhitelistModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNAndroidWhitelist";
  }

  private static final Intent[] AUTO_START_INTENTS = {
    new Intent().setComponent(new ComponentName("com.samsung.android.lool", "com.samsung.android.sm.ui.battery.BatteryActivity")),
    new Intent("miui.intent.action.OP_AUTO_START").addCategory(Intent.CATEGORY_DEFAULT),
    new Intent().setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity")),
    new Intent().setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity")),
    new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity")),
    new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity")),
    new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity")),
    new Intent().setComponent(new ComponentName("com.oppo.safe", "com.oppo.safe.permission.startup.StartupAppListActivity")),
    new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity")),
    new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager")),
    new Intent().setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity")),
    new Intent().setComponent(new ComponentName("com.asus.mobilemanager", "com.asus.mobilemanager.entry.FunctionActivity")).setData(Uri.parse("mobilemanager://function/entry/AutoStart"))
};

  @ReactMethod
  public void AlertIfWhitelist(String title, String message, String dontShowAgainText, String positiveText, String negativeText) {
    // read "do not show again" flag
    final SharedPreferences settings = this.getCurrentActivity().getSharedPreferences("Whitelist",Context.MODE_PRIVATE);
    final String saveIfSkip = "skipWhitelistMessage";
    boolean skipMessage = settings.getBoolean(saveIfSkip, false);
    // Show dialog only when "do not show again" hasn't been enabled yet
    if (!skipMessage) {
      final SharedPreferences.Editor editor = settings.edit();
      // Check if intent of the android whitelist apps activity is callable
      for (Intent intent : AUTO_START_INTENTS){
        if (isCallable(intent)) {
          // Prepare dialog
          final AppCompatCheckBox dontShowAgain = new AppCompatCheckBox(this.getCurrentActivity());
          dontShowAgain.setText(dontShowAgainText);
          dontShowAgain.setLeft(20);
          dontShowAgain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              editor.putBoolean(saveIfSkip, isChecked);
              editor.apply();
            }
          });

          final RelativeLayout layout = new RelativeLayout(this.getCurrentActivity());
          layout.setPadding(50,50,0,0);
          layout.addView(dontShowAgain);

          new AlertDialog.Builder(this.getCurrentActivity())
            .setTitle(title)
            .setMessage(message)
            .setView(layout)
            .setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int which) {
              try {
                for (Intent intent : AUTO_START_INTENTS)
                  if (isCallable(intent)) {
                    editor.putBoolean(saveIfSkip, true);
                    editor.apply();
                    reactContext.startActivity(intent);
                    break;
                  }
              } catch (Exception e) {
                e.printStackTrace();
              }
              }
            })
            .setNegativeButton(negativeText, null)
            .show();
          break;
        }
      }
    }
  }

  private boolean isCallable(Intent intent) {
    return reactContext.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null;
  }
}