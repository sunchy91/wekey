package com.android.wekey.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.wekey.R;

public class ToastUtils {

	public static void showToast(Context context,int iconResId,int toastTitle,int toastMsg){
		Toast toast = new Toast(context);
		
		LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.toast_layout, null);
		ImageView icon = (ImageView) view.findViewById(R.id.toast_icon);
		TextView title= (TextView) view.findViewById(R.id.toast_title);
		TextView message= (TextView) view.findViewById(R.id.toast_message);
		icon.setImageResource(iconResId);
		title.setText(toastTitle);
		message.setText(toastMsg);
		toast.setView(view );
		toast.setGravity(Gravity.FILL_HORIZONTAL|Gravity.TOP, 0, 0);
		toast.setMargin(0, 0);
		toast.show();
	}
}
