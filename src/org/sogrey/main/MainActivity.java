/**
 * @author Sogrey
 * 2015年4月3日
 */
package org.sogrey.main;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.sogrey.view.ViewPagerIndicatorView;
import org.sogrey.viewpagerindicatorview.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * @author Sogrey 2015年4月3日
 */
public class MainActivity extends Activity {
	/**
	 * @author Sogrey
	 * 
	 *         2015年4月3日
	 */
	ViewPagerIndicatorView mViewPagerIndicatorView;
	LinkedHashMap<String, View> mViewMap = new LinkedHashMap<String, View>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		initData();
	}

	private void initViews() {
		mViewPagerIndicatorView = (ViewPagerIndicatorView) findViewById(R.id.fragment_details_indicator);
	}

	private void initData() {
		TextView tv1 = new TextView(this);
		tv1.setText("第一个");
		mViewMap.put("第一个", tv1);
		TextView tv2 = new TextView(this);
		tv2.setText("第二个");
		mViewMap.put("第二个", tv2);
		TextView tv3 = new TextView(this);
		tv3.setText("第三个");
		mViewMap.put("第三个", tv3);


		mViewPagerIndicatorView.setupLayout(mViewMap);
	}
}
