package hu.valamas.hevesitoolbox.szamolasok.eovwgs;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class api extends Application implements HttpGetAsyncTask.MoveCompleteListener{
    private final String BASE_URL = "http://users.atw.hu/valamas/wgseov2/";
    Context mContext;
    String Mode;


    public interface OnkEventListener
    {
        public void onErrorTrack(String Result);
        public void onReadyTrack(String[] Result);
    }
    private OnkEventListener listener;


    //Váltó
    public void atvalt (Context context ,String mode ,double be1 ,double be2 ,double be3) {
        this.listener = null;
        mContext = context;
        Mode =mode;
        StringBuilder moveURL;
        if (mode.equals("ew")) {
            String y = String.valueOf(be1);
            String x = String.valueOf(be2);
            String h = String.valueOf(be3);

            moveURL = new StringBuilder(BASE_URL);
            moveURL.append("api.php?mode=");
            moveURL.append(mode);
            moveURL.append("&y=");
            moveURL.append(y);
            moveURL.append("&x=");
            moveURL.append(x);
            moveURL.append("&h=");
            moveURL.append(h);
        } else {
            String x = String.valueOf(be1);
            String y = String.valueOf(be2);
            String z = String.valueOf(be3);

            moveURL = new StringBuilder(BASE_URL);
            moveURL.append("api.php?mode=");
            moveURL.append(mode);
            moveURL.append("&x=");
            moveURL.append(x);
            moveURL.append("&y=");
            moveURL.append(y);
            moveURL.append("&z=");
            moveURL.append(z);
        }
        //HTML kérés
        String url = moveURL.toString();
        Log.d("wgs_url", url);
        new HttpGetAsyncTask(mContext ,this).execute(url);
    }

    public void onTaskComplete(String aResult) {
        Log.d("wgs_complete", aResult);

        String darab = aResult;
        if (darab.equals("\nhiba")){
            Toast.makeText(mContext, "Érvénytelen koordináta !" ,Toast.LENGTH_SHORT).show();
            return;
        }
        String[] darabarr = darab.substring(1).split(";");
        listener.onReadyTrack(darabarr);
    }

    public void onError(String aError) {
        Log.d("wgs_error", aError);
        listener.onErrorTrack(aError);
    }

    public void setOnkEventListener(OnkEventListener listener)
    {
        this.listener = listener;
    }
}
