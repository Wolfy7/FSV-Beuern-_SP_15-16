package de.wolfy7.myfsvbeuern;

import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import android.R.string;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


class GameDay {
  public String sHomeTeam;
  public String sGuestTeam;
  public String sGameDate;
  public String sGameStart;
  public Location lGameLocation;
  public String sLocation;
  public boolean bNextGame;
  public boolean bGameOver = false;


  public GameDay(String sHomeTeam, String sGuestTeam, String sGameDate, String sGameStart, double dLatitude, double dLongitude, String sLocation){
    this.sHomeTeam = sHomeTeam;
    this.sGuestTeam = sGuestTeam;
    this.sGameDate = sGameDate;
    this.sGameStart = sGameStart;
    this.sLocation = sLocation;
    this.bNextGame = false;

    this.lGameLocation = new Location("GameLocation");
    this.lGameLocation.setLatitude(dLatitude);
    this.lGameLocation.setLongitude(dLongitude);

      SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
      TimeZone.setDefault(TimeZone.getTimeZone("Europe/Berlin"));
      Date dGameDate = new Date();
      Date dToday = new Date();
      try {

          dGameDate = formatter.parse(sGameDate);

          long t1 = dGameDate.getTime();
          long t2 = dToday.getTime();

         long lDaysDiff = (dGameDate.getTime() - dToday.getTime()) / 86400000;

          if( lDaysDiff > 0 && lDaysDiff < 6 ) { // 86400000 = ms pro Tag
              this.bNextGame = true;
          }

        if( lDaysDiff < 0 ) {
          this.bGameOver = true;
        }

          Calendar c = Calendar.getInstance();
          c.setTime(dGameDate);
          String day;

          switch (c.get(Calendar.DAY_OF_WEEK)){
              case Calendar.SUNDAY:
                  day = "Sonntag";
              break;
              case Calendar.MONDAY:
                  day = "Montag";
                  break;
              case Calendar.TUESDAY:
                  day = "Dienstag";
                  break;
              case Calendar.WEDNESDAY:
                  day = "Mittwoch";
                  break;
              case Calendar.THURSDAY:
                  day = "Donnerstag";
                  break;
              case Calendar.FRIDAY:
                  day = "Freitag";
                  break;
              case Calendar.SATURDAY:
                  day = "Samstag";
                  break;
              default:
                  day = "";
                  break;
          }

          this.sGameDate = day +", " + this.sGameDate;

          //Log.d("DBG:", dGameDate.toString() +" "+dToday.toString() + " " + this.bGameOver );

      } catch (ParseException e) {
         Log.d("DBG:", e.toString());
      }


  }
}

public class ProgramActivity extends ListActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_program);

      ArrayList<GameDay> lGameList = new ArrayList<GameDay>();

      lGameList.add(new GameDay("FC Grüningen II", "FSV Beuern II", "02.08.2015", "13:00", 50.5141673, 8.7300959, "Rasenplatz, Paul-Hutten-Ring, 35415 Pohlheim"));
      lGameList.add(new GameDay("Kardes Spor Gießen", "FSV Beuern", "02.08.2015", "15:00", 50.5933761, 8.6845055, "Hartplatz, Ringallee, 35390 Gießen"));
      lGameList.add(new GameDay("TV Dornholzhausen", "FSV Beuern", "09.08.2015", "15:00", 50.494693, 8.6141485, "Rasenplatz, In den Bachgärten, 35428 Langgöns"));
      lGameList.add(new GameDay("SG Salzböde-Lahn II", "FSV Beuern II", "16.08.2015", "13:00", 50.700223, 8.689699, "Rasenplatz, 35112 Fronhausen"));
      lGameList.add(new GameDay("SG Salzböde-Lahn", "FSV Beuern", "16.08.2015", "15:00", 50.700223, 8.689699, "Rasenplatz, 35112 Fronhausen"));
      lGameList.add(new GameDay("FSV Beuern II", "TSF Heuchelheim III", "18.08.2015", "19:00", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("FSV Beuern", "Kurdischer FC GI", "23.08.2015", "15:00", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("FSG Garbenteich/Hausen II", "FSV Beuern II", "29.08.2015", "16:00", 50.5520451, 8.7396642, "Rasenplatz, Gebrüder-Happel-Allee, 35415 Pohlheim"));
      lGameList.add(new GameDay("FSG Garbenteich/Hausen", "FSV Beuern", "29.08.2015", "18:00", 50.5520451, 8.7396642, "Rasenplatz, Gebrüder-Happel-Allee, 35415 Pohlheim"));
      lGameList.add(new GameDay("FSV Beuern II", "SV Kurdistan Gießen", "06.09.2015", "13:00", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("FSV Beuern", "GSV Hellas Giessen", "06.09.2015", "15:00", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("TSF Heuchelheim III", "FSV Beuern II", "08.09.2015", "20:30", 50.58179, 8.6235846, "Kunstrasenplatz, Schwimmbadstraße 4, 35452 Heuchelheim"));
      lGameList.add(new GameDay("FSV Beuern", "TV Dornholzhausen", "10.09.2015", "19:00", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("TSV 05 Allendorf/Lahn II", "FSV Beuern", "13.09.2015", "13:00", 50.5519068, 8.6179403, "Rasenplatz, Untergasse 34, 35398 Gießen"));
      lGameList.add(new GameDay("Blau-Weiss Giessen II", "FSV Beuern II", "27.09.2015", "13:00", 50.591372, 8.6845055, "Hartplatz, Ringallee 45, 35390 Gießen"));
      lGameList.add(new GameDay("Blau-Weiss Giessen", "FSV Beuern", "27.09.2015", "15:00", 50.591372, 8.683519, "Hartplatz, Ringallee 45, 35390 Gießen"));
      lGameList.add(new GameDay("FSV Beuern II", "TSV Rödgen II ", "04.10.2015", "13:00", 50.6274186, 8.683519, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("FSV Beuern", "TSV Rödgen ", "04.10.2015", "15:00", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("FSV Beuern", "ACE Gießen", "08.10.2015", "19:00", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("1.SC Sachsenhausen II", "FSV Beuern II", "11.10.2015", "13:00", 50.593923, 8.657566, "Rasenplatz, Gleiberger Weg, 35398 Gießen"));
      lGameList.add(new GameDay("1.SC Sachsenhausen", "FSV Beuern", "11.10.2015", "15:00", 50.593923, 8.657566, "Rasenplatz, Gleiberger Weg, 35398 Gießen"));
      lGameList.add(new GameDay("FSV Beuern II", "Blau-Weiss Giessen III", "15.10.2015", "19:00", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("FSV Beuern II", "ACE Gießen II", "18.10.2015", "13:00", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("FSV Beuern", "SV Dorf-Güll ", "18.10.2015", "15:00", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("FSV Beuern", "SV SW Giessen ", "25.10.2015", "15:00", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("ASV Giessen II", "FSV Beuern", "01.11.2015", "12:30", 50.593923, 8.657566, "Rasenplatz, Gleiberger Weg, 35398 Gießen"));
      lGameList.add(new GameDay("FSV Beuern II", "FSG Biebertal III ", "08.11.2015", "12:30", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("FSV Beuern", "FSG Biebertal II ", "08.11.2015", "14:30", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("FC Besa Giessen II", "FSV Beuern", "15.11.2015", "13:00", 50.587071, 8.698898, "Kunstrasenplatz, Grünberger Str. 126, 35394 Gießen"));
      lGameList.add(new GameDay("FSV Beuern II", "FTSG Giessen II", "22.11.2015", "13:00", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("FSV Beuern", "FTSG Giessen", "22.11.2015", "14:45", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("FSV Beuern II", "FC Grüningen II ", "29.11.2015", "12:30", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("FSV Beuern", "Kardes Spor Gießen", "29.11.2015", "14:30", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("Blau-Weiss Giessen III", "FSV Beuern II", "28.02.2016", "14:30", 50.591372, 8.6845055, "Hartplatz, Ringallee 45, 35390 Gießen"));
      lGameList.add(new GameDay("Kurdischer FC GI", "FSV Beuern", "28.02.2016", "14:30", 50.568671, 8.649517, "Hartplatz, Lahnstr. 240, 35398 Gießen"));
      lGameList.add(new GameDay("FSV Beuern II", "FSG Garbenteich/Hausen II", "06.03.2016", "13:00", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("FSV Beuern", "FSG Garbenteich/Hausen", "06.03.2016", "15:00", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("GSV Hellas Giessen", "FSV Beuern", "13.03.2016", "15:00", 50.5984058, 8.6685225, "Hartplatz, Launsbacher Weg, 35398 Gießen"));
      lGameList.add(new GameDay("SV Kurdistan Gießen", "FSV Beuern II", "13.03.2016", "15:00", 0, 0, "")); // ToDo
      lGameList.add(new GameDay("FSV Beuern", "TSV 05 Allendorf/Lahn II", "20.03.2016", "15:00", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("ACE Giessen II", "FSV Beuern II", "28.03.2016", "13:00", 50.592204, 8.713439, "Rasenplatz, Heyerweg 43, 35394 Gießen"));
      lGameList.add(new GameDay("ACE Giessen", "FSV Beuern", "28.03.2016", "15:00", 50.592204, 8.713439, "Rasenplatz, Heyerweg 43, 35394 Gießen"));
      lGameList.add(new GameDay("FSV Beuern II", "Blau-Weiss Giessen II", "03.04.2016", "13:00", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("FSV Beuern", "Blau-Weiss Giessen ", "03.04.2016", "15:00", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("TSV Rödgen II", "FSV Beuern II", "10.04.2016", "13:00", 50.5950578, 8.750405, "Rasenplatz, Geiselstrauchweg, 35394 Gießen"));
      lGameList.add(new GameDay("TSV Rödgen", "FSV Beuern", "10.04.2016", "15:00", 50.5950578, 8.750405, "Rasenplatz, Geiselstrauchweg, 35394 Gießen"));
      lGameList.add(new GameDay("FSV Beuern II", "1.SC Sachsenhausen II ", "17.04.2016", "13:00", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("FSV Beuern", "1.SC Sachsenhausen", "17.04.2016", "15:00", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("SV Dorf-Güll", "FSV Beuern", "24.04.2016", "15:00", 50.507381, 8.748593, "Rasenplatz, Lerchenstraße 43, 35415 Pohlheim"));
      lGameList.add(new GameDay("SV SW Giessen", "FSV Beuern", "01.05.2016", "15:00", 50.5778971, 8.6566206, "Hartplatz, Lahnstr., 35398 Gießen"));
      lGameList.add(new GameDay("FSV Beuern II", "SG Salzböde-Lahn II ", "03.05.2016", "19:00", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("FSV Beuern", "SG Salzböde-Lahn", "04.05.2016", "19:00", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("FSV Beuern", "ASV Giessen II", "08.05.2016", "15:00", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("FSG Biebertal II", "FSV Beuern", "15.05.2016", "13:00", 50.6253208, 8.5968149, "Rasenplatz, Fellingshäuser Str., 35444 Biebertal"));
      lGameList.add(new GameDay("FSG Biebertal III", "FSV Beuern II", "15.05.2016", "15:00", 50.6253208, 8.5968149, "Rasenplatz, Fellingshäuser Str., 35444 Biebertal"));
      lGameList.add(new GameDay("FSV Beuern", "FC Besa Giessen II", "22.05.2016", "15:00", 50.6274186, 8.816116, "Hartplatz, Neue Straße, 35418 Buseck"));
      lGameList.add(new GameDay("FTSG Giessen II", "FSV Beuern II", "29.05.2016", "13:00", 50.5879423, 8.6951853, "Rasenplatz, An der Liebigshöhe 6, 35394 Gießen"));
      lGameList.add(new GameDay("FTSG Giessen", "FSV Beuern", "29.05.2016", "15:00", 50.5879423, 8.6951853, "Rasenplatz, An der Liebigshöhe 6, 35394 Gießen"));

      for(int i = lGameList.size() - 1; i>=0;  i--)
      {
        if(lGameList.get(i).bGameOver) lGameList.remove(i);
      }

      //ListAdapter adapter = new ArrayAdapter<GameDay>(getApplicationContext(), R.layout.gamedaylistitem, lGameList);
      GameDayAdapter adapter = new GameDayAdapter(this, lGameList);
      final ListView lv = (ListView)findViewById(android.R.id.list);
      lv.setAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView list, View view, int position, long id){
      super.onListItemClick(list, view, position, id);

        GameDay gd = (GameDay) getListView().getItemAtPosition(position);
        //String item = (String) getListAdapter().getItem(position);
        //Toast.makeText(this, gd.sHomeTeam + " - " + gd.sGuestTeam, Toast.LENGTH_LONG).show();

        try {
            Log.d("DBG:", "Try google.navigation");
            //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?&daddr="+gd.lGameLocation.getLatitude()+","+gd.lGameLocation.getLongitude()));
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="+gd.lGameLocation.getLatitude()+","+gd.lGameLocation.getLongitude()));
            startActivity(intent);
      } catch (Exception e) {
        Log.d("DBG:", "Error: Try google.navigation");
          try {
              Log.d("DBG:", "Try http://maps.google.com/maps?");
              Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?&daddr="+gd.lGameLocation.getLatitude()+","+gd.lGameLocation.getLongitude()));
              //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="+gd.lGameLocation.getLatitude()+","+gd.lGameLocation.getLongitude()));
              startActivity(intent);
        } catch (Exception ex) {
          //Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
          Log.d("DBG:", ex.toString());
        }
      }
    }

}
