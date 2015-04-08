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
		TextView tv4 = new TextView(this);
		tv4.setText("第四个");
		mViewMap.put("第四个", tv4);
		TextView tv5 = new TextView(this);
		tv5.setText("第五个");
		mViewMap.put("第五个", tv5);
		TextView tv6 = new TextView(this);
		tv6.setText("第六个");
		mViewMap.put("第六个", tv6);
		TextView tv7 = new TextView(this);
		tv7.setText("第7个");
		mViewMap.put("第7个", tv7);
		TextView tv8= new TextView(this);
		tv8.setText("第8个");
		mViewMap.put("第8个", tv8);
		TextView tv9= new TextView(this);
		tv9.setText("第9个");
		mViewMap.put("第9个", tv9);

		mViewPagerIndicatorView.setupLayout(mViewMap);
	}
}
