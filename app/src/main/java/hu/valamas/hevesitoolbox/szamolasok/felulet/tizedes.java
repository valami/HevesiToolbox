package hu.valamas.hevesitoolbox.szamolasok.felulet;

import java.text.DecimalFormat;

public class tizedes {
    public String tizedes (double szam,int hossz)
    {
        String szam_s;
        if (hossz==1)
        {
            DecimalFormat df = new DecimalFormat("#.#");
            szam_s =df.format(szam);
        } else if (hossz==2) {
            DecimalFormat df =new DecimalFormat("#.##");
            szam_s =df.format(szam);
        } else if (hossz==3) {
            DecimalFormat df =new DecimalFormat("#.###");
            szam_s =df.format(szam);
        } else {
            DecimalFormat df = new DecimalFormat("#");
            szam_s =df.format(szam);
        }

        char szam_c_in[] = szam_s.toCharArray();
        char szam_c_out[] = new char[szam_c_in.length];

        for (byte i=0 ;i < szam_c_in.length ; i++  )
        {
            if (szam_c_in[i]== ',') {
                szam_c_out[i]='.';
            } else {
                szam_c_out[i]=szam_c_in[i];
            }
        }



        String szam_out =new String(szam_c_out);

        return (szam_out);
    }
}
