package com.faq.adapter;

import java.util.List;

import com.example.fragment02.R;
import com.faq.view.MusicInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MusicListAdapter extends BaseAdapter{
	private LayoutInflater inflater;
	private List<MusicInfo> musics;
	public MusicListAdapter(Context c,List<MusicInfo> musicList){
		inflater = LayoutInflater.from(c);
		musics = musicList;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return musics.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return musics.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder vw;
		if(arg1 == null){
			vw = new ViewHolder();
			arg1 = inflater.inflate(R.layout.song_item, null);
			vw.text2 = (TextView) (arg1).findViewById(R.id.text2);
			arg1.setTag(vw);
		}else{
			vw = (ViewHolder) arg1.getTag();
		}
		vw.text2.setText(musics.get(arg0).getTitle().toString().replace("[mqms2]", ""));
		return arg1;
	}
	
	public class ViewHolder{
		private TextView text2;
	}

}
