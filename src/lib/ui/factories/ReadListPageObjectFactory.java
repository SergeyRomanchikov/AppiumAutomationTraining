package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.ReadListPageObject;
import lib.ui.android.AndroidReadListPageObject;
import lib.ui.ios.iOSReadListPageObject;

public class ReadListPageObjectFactory {
    public static ReadListPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidReadListPageObject(driver);
        } else {
            return new iOSReadListPageObject(driver);
        }
    }

}
