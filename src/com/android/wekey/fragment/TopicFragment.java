package com.android.wekey.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.wekey.R;
import com.android.wekey.adapter.CommonAdapter;
import com.android.wekey.adapter.ViewHolder;
import com.android.wekey.entity.TopicEntity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class TopicFragment extends Fragment {

	@ViewInject(R.id.topic_list)
	private ListView topicList;
	
	private ArrayList<TopicEntity> data = new ArrayList<TopicEntity>();

	private String title;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View contextView = inflater.inflate(R.layout.topic_fragment, container,
				false);
        ViewUtils.inject(this, contextView);
        Bundle mBundle = getArguments();  
        title = mBundle.getString("arg");  
		return contextView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		fakeData();
		CommonAdapter<TopicEntity> topicAdapter = new CommonAdapter<TopicEntity>(getActivity(), data, R.layout.topic_item) {

			@Override
			public void convert(ViewHolder helper, TopicEntity item) {
				helper.setText(R.id.topic, item.topic);
				helper.setText(R.id.topic_hot_count, item.topicHotCount);
			}
		};
		topicList.setAdapter(topicAdapter);
	}

	private void fakeData(){
		data.clear();
		for(int i=0;i<10;i++){
			TopicEntity topic = new TopicEntity();
			topic.topic="#"+title+i+"#";
			topic.topicHotCount=i*10+"万";
			data.add(topic);
		}
	}
}
