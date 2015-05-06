package com.android.wekey.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

public class WeKeyUtils {

	public static String getDeviceId(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getDeviceId();
	}
}
