package com.example.fragment02;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
	private RadioGroup radioGroup;
	private FragmentManager fraManager;
	LocalMusic lm;
	OnlineMusic om;
	RankingMusic rm;
	Setting set;
	private TextView title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		title = (TextView)findViewById(R.id.title);
		title.setText("本地音乐");
		fraManager = getSupportFragmentManager();
		radioGroup = (RadioGroup)findViewById(R.id.rg_tab);
		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				FragmentTransaction fraTransaction = fraManager.beginTransaction();
				System.out.println("id:"+checkedId);
				switch(checkedId){
					case 1:
						title.setText("本地音乐");
						lm = new LocalMusic();
						if(om != null){
							fraTransaction.hide(om);
						}
						if(rm != null){
							fraTransaction.hide(rm);
						}
						if(set != null){
							fraTransaction.hide(set);
						}
						fraTransaction.add(R.id.content, lm);
						break;
					case 2:
						title.setText("在线歌曲");
						om = new OnlineMusic();
						if(lm != null){
							fraTransaction.hide(lm);
						}
						if(rm != null){
							fraTransaction.hide(rm);
						}
						if(set != null){
							fraTransaction.hide(set);
						}
						fraTransaction.add(R.id.content, om);
						break;
					case 3:
						title.setText("排行榜");
						rm = new RankingMusic();
						if(lm != null){
							fraTransaction.hide(lm);
						}
						if(om != null){
							fraTransaction.hide(om);
						}
						if(set != null){
							fraTransaction.hide(set);
						}
						fraTransaction.add(R.id.content, rm);
						break;
					case 4:
						title.setText("设置");
						set = new Setting();
						if(lm != null){
							fraTransaction.hide(lm);
						}
						if(om != null){
							fraTransaction.hide(om);
						}
						if(rm != null){
							fraTransaction.hide(rm);
						}
						fraTransaction.add(R.id.content, set);
						break;
					default:
							break;
				}
				fraTransaction.commit();
			}
		});
	}

}
