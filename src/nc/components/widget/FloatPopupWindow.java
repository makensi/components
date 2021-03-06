/*
	Copyright 2012 Raul de la Hoz Garrido

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

		http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
 */
package nc.components.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

/**
 * Float Popup
 * 
 * Utility class that allows to works with PopupWindow and give several event to
 * manage several actions.
 * 
 * @author makensi
 * 
 */
public class FloatPopupWindow {

	// properties
	private PopupWindow popupWindow;

	/**
	 * Default constructor
	 * 
	 * @param context
	 */
	public FloatPopupWindow(Context context) {
		popupWindow = new PopupWindow(context);
		// properties
		popupWindow.setTouchable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setFocusable(true);
		popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
		popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		// events
		// set touch interceptor to listen click out popup event.
		popupWindow.setTouchInterceptor(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				boolean result = false;
				// click outside
				if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
					popupWindow.dismiss();
					result = true;
				}
				return result;
			}
		});

	}

	/**
	 * Set content view used by popupWindow
	 * 
	 * @param contentView
	 */
	public void setContentView(View contentView) {
		popupWindow.setContentView(contentView);
	}

	/**
	 * Show popup
	 * 
	 * @param parentView
	 */
	public void show(View parentView) {
		int coordinates[] = { 0, 0 };
		parentView.getLocationOnScreen(coordinates);
		int x = coordinates[0];
		int y = coordinates[1];

		popupWindow.showAtLocation(
				parentView.getRootView().findViewById(android.R.id.content),
				Gravity.NO_GRAVITY, x, y);
	}

	/**
	 * Close popupWindow
	 */
	public void dismiss() {
		popupWindow.dismiss();
	}

	/**
	 * return true if popupWindow is been showed.
	 * 
	 * @return
	 */
	public boolean isShowing() {
		return popupWindow.isShowing();
	}

	/**
	 * Allow set the default PopupWindow dismiss event class to manage pupup
	 * close event
	 * 
	 * @param dismissListener
	 */
	public void setOnDismissListener(OnDismissListener dismissListener) {
		popupWindow.setOnDismissListener(dismissListener);
	}

}
