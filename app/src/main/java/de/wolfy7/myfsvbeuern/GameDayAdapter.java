package de.wolfy7.myfsvbeuern;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GameDayAdapter extends ArrayAdapter<GameDay>{

  public GameDayAdapter(Context context, ArrayList<GameDay> gamedaylist) {
    super(context, 0, gamedaylist);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent){

    GameDay gameday = getItem(position);
    if(convertView == null){
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.gamedaylistitem, parent,false);
    }
    TextView tvHomeTeam = (TextView) convertView.findViewById(R.id.HomeTeam);
    TextView tvGuestTeam = (TextView) convertView.findViewById(R.id.GuestTeam);
    TextView tvDate = (TextView) convertView.findViewById(R.id.Date);
    TextView tvTime = (TextView) convertView.findViewById(R.id.Time);
    TextView tvLocation = (TextView) convertView.findViewById(R.id.Location);
/*
    if(gameday.bNextGame){
      RelativeLayout rvRL = (RelativeLayout) convertView.findViewById(R.id.RelativeLayout1);
      rvRL.setBackgroundColor(Color.YELLOW);
    }
*/
    tvHomeTeam.setText(gameday.sHomeTeam);
    tvGuestTeam.setText(gameday.sGuestTeam);
    tvDate.setText(gameday.sGameDate);
    tvTime.setText(gameday.sGameStart);
    tvLocation.setText(gameday.sLocation);

    return convertView;
  }

}
