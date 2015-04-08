/**
 * @author Sogrey
 * 2015��4��3��
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
 * @author Sogrey 2015��4��3��
 */
public class MainActivity extends Activity {
	/**
	 * @author Sogrey
	 * 
	 *         2015��4��3��
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
		tv1.setText("��һ��");
		mViewMap.put("��һ��", tv1);
		TextView tv2 = new TextView(this);
		tv2.setText("�ڶ���");
		mViewMap.put("�ڶ���", tv2);
		TextView tv3 = new TextView(this);
		tv3.setText("������");
		mViewMap.put("������", tv3);
		TextView tv4 = new TextView(this);
		tv4.setText("���ĸ�");
		mViewMap.put("���ĸ�", tv4);
		TextView tv5 = new TextView(this);
		tv5.setText("�����");
		mViewMap.put("�����", tv5);
		TextView tv6 = new TextView(this);
		tv6.setText("������");
		mViewMap.put("������", tv6);
		TextView tv7 = new TextView(this);
		tv7.setText("��7��");
		mViewMap.put("��7��", tv7);
		TextView tv8= new TextView(this);
		tv8.setText("��8��");
		mViewMap.put("��8��", tv8);
		TextView tv9= new TextView(this);
		tv9.setText("��9��");
		mViewMap.put("��9��", tv9);

		mViewPagerIndicatorView.setupLayout(mViewMap);
	}
}
