package org.midnightas.advio;

import android.os.Bundle;
import android.view.WindowManager.LayoutParams;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import org.midnightas.advio.Advio;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);
		initialize(new Advio(), config);
	}
}
