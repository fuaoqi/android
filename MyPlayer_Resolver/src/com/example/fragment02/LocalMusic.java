package com.example.fragment02;

import java.util.ArrayList;
import java.util.List;

import com.faq.adapter.MusicListAdapter;
import com.faq.view.MusicInfo;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class LocalMusic extends Fragment{
	private List<MusicInfo> musicList = new ArrayList<MusicInfo>();
	private ContentResolver resolver;
	private ListView songList;
	private Cursor cursor;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment, null);
		songList = (ListView)view.findViewById(R.id.songList);
		songList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				System.out.println(musicList.get(arg2).getUrl());
			}
		});
		resolver = getActivity().getContentResolver();
		cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,  
                null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
		
		if(cursor == null){
			Log.i("error", "cursor is null");
		}else{
			
			while(cursor.moveToNext()){
				int displayNameCol = cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME);  
	            int albumCol = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);  
	            int idCol = cursor.getColumnIndex(MediaStore.Audio.Media._ID);  
	            int durationCol = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);  
	            int sizeCol = cursor.getColumnIndex(MediaStore.Audio.Media.SIZE);  
	            int artistCol = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);  
	            int urlCol = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
	            
	            String title = cursor.getString(displayNameCol);  
                String album = cursor.getString(albumCol);  
                long id = cursor.getLong(idCol);                  
                int duration = cursor.getInt(durationCol);  
                long size = cursor.getLong(sizeCol);  
                String artist = cursor.getString(artistCol);  
                String url = cursor.getString(urlCol);
                if(title.endsWith(".mp3")){
                	MusicInfo musicInfo = new MusicInfo(id, title);  
                    musicInfo.setAlbum(album);  
                    musicInfo.setDuration(duration);  
                    musicInfo.setSize(size);  
                    musicInfo.setArtist(artist);  
                    musicInfo.setUrl(url);  
                    musicList.add(musicInfo);
                }
			}
		}
		if(songList != null){
			songList.setAdapter(new MusicListAdapter(getActivity().getBaseContext(),musicList));
		}else{
			System.out.println("空指针异常");
		}
		
		System.out.println("size::"+musicList.size());
		return view;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
}
