package org.sogrey.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.sogrey.viewpagerindicatorview.R;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 切换标签控件基类
 * 
 * @author savant-pan
 * 
 */
public class TabIndicatorView extends LinearLayout implements View.OnTouchListener {
	private LinearLayout tabHost;
	private List<View> viewList = new Vector<View>();

	/**
	 * 界面刷新Handler
	 */
	private Handler refreshHandler;
	private int viewCount = 0;
	private int currentIndex = 0;
	private OnIndicateChangeListener onIndicateChangeListener;
	private boolean notify = false;

	/**
	 * 标签切换监听接口
	 */
	public interface OnIndicateChangeListener {
		public void onTabChanged(int position);
	}

	public TabIndicatorView(Context context) {
		super(context);
		this.init();
	}

	public TabIndicatorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.init();
	}

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.tab_indicator_layout, this);
		this.tabHost = (LinearLayout) findViewById(R.id.tab_host);
		this.refreshHandler = new TabIndicateHandler(TabIndicatorView.this);
	}

	public void setOnIndicateChangeListener(OnIndicateChangeListener onIndicateChangeListener) {
		if (onIndicateChangeListener == null) {
			throw new NullPointerException();
		}
		this.onIndicateChangeListener = onIndicateChangeListener;
	}
	
	/**
	 * 设置文字标签数据
	 * 
	 * @param titleArray
	 *            文字标签数组
	 */
	public void setupLayout(String titleArray[]) {
		if (titleArray == null || titleArray.length == 0) {
			throw new NullPointerException();
		}

		this.setupLayout(Arrays.asList(titleArray));
	}

	/**
	 * 设置文字标签数据
	 * 
	 * @param titleList
	 *            文字标签列表
	 */
	public void setupLayout(List<String> textList) {
		if (textList == null || textList.size() == 0) {
			throw new NullPointerException();
		}

		final int len = textList.size();

		final List<View> list = new ArrayList<View>();
		for (int index = 0; index < len; index++) {
			final View view = LayoutInflater.from(getContext()).inflate(R.layout.tab_indicator_item, null);
			final TextView textView = (TextView) view.findViewById(R.id.indicator_text);
			textView.setText(textList.get(index));
			list.add(view);
		}

		this.setupTabLayout(list);
	}

	public void setupTabLayout(List<View> list) {
		if (list == null || list.size() == 0) {
			throw new NullPointerException();
		}

		this.viewCount = list.size();
		final LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		final LayoutParams linelp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		linelp.gravity = Gravity.CENTER_VERTICAL;

		lp.weight = 1f / viewCount;
		for (int index = 0; index < viewCount; index++) {
			final View view = list.get(index);
			this.tabHost.addView(view, lp);
			if (index != viewCount - 1) {
				final View lineView = LayoutInflater.from(getContext()).inflate(R.layout.tab_indicator_line,
						null);
				this.tabHost.addView(lineView, linelp);
			}
			view.setOnTouchListener(this);
			this.viewList.add(view);
		}

		this.refrash();
	}

	/**
	 * 设置当前显示TAB
	 * 
	 * @param position
	 *            前显示TAB位置
	 */
	public void setCurrentTab(int position) {
		this.setCurrentTab(position, true);// 默认�?��通知接口返回位置
	}

	/**
	 * 设置当前显示TAB
	 * 
	 * @param position
	 *            前显示TAB位置
	 * @param notify
	 *            是否通知接口返回位置
	 */
	public void setCurrentTab(int position, boolean notify) {
		this.notify = notify;
		this.currentIndex = position;
		this.refrash();
	}

	/**
	 * 事件处理
	 */
	@Override
	public boolean onTouch(View view, MotionEvent event) {
		final View lastView = this.viewList.get(this.currentIndex);
		if (lastView != view) {
			this.setCurrentIndex(view);
			this.refrash();
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 设置当前 currentIndex�?
	 * 
	 * @param touchView
	 */
	private void setCurrentIndex(View touchView) {
		for (int index = 0; index < this.viewCount; index++) {// 切换刷新界面
			final View view = this.viewList.get(index);
			if (touchView == view) {
				this.currentIndex = index;
			}
		}
	}

	/**
	 * 更新条目
	 */
	private void refrash() {
		this.refreshHandler.sendEmptyMessage(0);
	}

	protected void refreshIndicateView() {
		for (int index = 0; index < viewCount; index++) {// 切换刷新界面
			final View view = viewList.get(index);
			if (index == currentIndex) {
				refreshItemView(view, true);
				if (onIndicateChangeListener != null) {
					if (!notify) {// use only once
						notify = true;
					} else {
						onIndicateChangeListener.onTabChanged(index);
					}
				}
			} else {
				refreshItemView(view, false);
			}
		}
	}

	/**
	 * 更新选中项，重写则自定义
	 * 
	 * @param view
	 * @param isLast
	 */
	private void refreshItemView(View view, boolean isCurrent) {
		final View line = view.findViewById(R.id.bottom_line);
		final TextView textView = (TextView) view.findViewById(R.id.indicator_text);
		final Resources resources = getContext().getResources();
		if (isCurrent) {
			textView.setTextColor(resources.getColor(R.color.frame));
			line.setVisibility(View.VISIBLE);
		} else {
			textView.setTextColor(resources.getColor(R.color.title));
			line.setVisibility(View.INVISIBLE);
		}
	}

}

class TabIndicateHandler extends Handler {
	private TabIndicatorView absTabIndicateView;

	public TabIndicateHandler(TabIndicatorView absTabIndicateView) {
		this.absTabIndicateView = absTabIndicateView;
	}

	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		if (this.absTabIndicateView != null) {
			this.absTabIndicateView.refreshIndicateView();
		}
	}
}
