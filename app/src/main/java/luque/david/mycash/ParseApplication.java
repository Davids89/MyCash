package luque.david.mycash;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by David on 17/10/15.
 */
public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "2DTTY4bU8GjW1ppY9WtMR6NTctLI1xIJtRAbFHI6", "jf2AJUMKbPQbTMD6svRLFIFCNeBjzTnvq3C3eqEK");

    }
}
