package hu.valamas.hevesitoolbox.szamolasok.felulet;

import java.text.DecimalFormat;

public class szogkezeles {
    public String[] tagolas (String bevitel)
    {
        String[] r = new String[3];
        // r[0] tagolt szám ; r[1] tizedes ; r[2] Szög
        r[0] = bevitel;
        double rn = 0;
        double rnr = 0;
        int i = 0;
        byte tized = 0;

        if (bevitel.equals("")){
            r[0]="";
            r[1]="0";
            r[2]="0";
            return (r);
        }

        char[] bevitel_c = bevitel.toCharArray();

        if (bevitel_c.length >= 2)  {
            if (bevitel_c[1] == '-')    {
                r[1] = "1";
                i = 1;
            }
            if (bevitel_c[1]=='.')  {
                tized = 1;
            }

        } if (bevitel_c.length >=3 )   {
            if (bevitel_c[2]=='-') {
                r[1] = "2";
                i = 1;
            }
            if (bevitel_c[2]=='.')  {
                tized = 1;
            }

        }  if (bevitel_c.length >=4)  {
            if (bevitel_c[3]=='-') {
                r[1] = "3";
                i = 1;
            }
            if (bevitel_c[3]=='.')  {
                tized = 1;
            }
        }

        if (i==0)   {
            r[1] = "0";
        }

        if (bevitel_c.length == 1)  {
            if (bevitel_c[0]=='-' || bevitel_c[0]=='.')  {
                r[0] = "";
            }
        }

        if (bevitel_c.length == 4 && r[1].equals("0") && tized == 0 )  {
            if (bevitel_c[3] != '-')
                r[0] = Character.toString(bevitel_c[0]) + Character.toString(bevitel_c[1]) + Character.toString(bevitel_c[2]) + "-" + Character.toString(bevitel_c[3]);
        }

        if (bevitel_c.length == 5 && r[1].equals("1") && bevitel_c[4] !='-' && tized == 0 )  {
            r[0] = Character.toString(bevitel_c[0]) + Character.toString(bevitel_c[1]) + Character.toString(bevitel_c[2]) + Character.toString(bevitel_c[3]) + "-" + Character.toString(bevitel_c[4]) ;
        }

        if (bevitel_c.length == 6 && r[1].equals("2") && bevitel_c[5] !='-' && tized == 0 )   {
            r[0] = Character.toString(bevitel_c[0]) + Character.toString(bevitel_c[1]) + Character.toString(bevitel_c[2]) + Character.toString(bevitel_c[3]) + Character.toString(bevitel_c[4]) + "-" + Character.toString(bevitel_c[5]) ;
        }

        if (bevitel_c.length == 7 && r[1].equals("3") && bevitel_c[6] !='-' && tized == 0 )   {
            r[0] = Character.toString(bevitel_c[0]) + Character.toString(bevitel_c[1]) + Character.toString(bevitel_c[2]) + Character.toString(bevitel_c[3]) + Character.toString(bevitel_c[4]) + Character.toString(bevitel_c[5]) + "-" + Character.toString(bevitel_c[6]) ;
        }

        //Számolás

        if (tized==1)   {
            rn = Double.parseDouble(bevitel);
        } else {
            switch (r[1]) {
                case "0":
                    rn = Double.parseDouble(bevitel.substring(0));
                    break;
                case "1":
                    rn = Double.parseDouble(Character.toString(bevitel_c[0]));
                    if (bevitel_c.length == 3) {
                        double rnm = Double.parseDouble(Character.toString(bevitel_c[2]));
                        rn = rn + (rnm / 60);
                    } else if (bevitel_c.length == 4) {
                        double rnm = Double.parseDouble(Character.toString(bevitel_c[2]) + Character.toString(bevitel_c[3]));
                        rn = rn + (rnm / 60);
                    } else if (bevitel_c.length == 6) {
                        double rnm = Double.parseDouble(Character.toString(bevitel_c[2]) + Character.toString(bevitel_c[3]));
                        double rns = Double.parseDouble(Character.toString(bevitel_c[5]));
                        rn = rn + (rnm / 60) + (rns / 3600);
                    } else if (bevitel_c.length == 7) {
                        double rnm = Double.parseDouble(Character.toString(bevitel_c[2]) + Character.toString(bevitel_c[3]));
                        double rns = Double.parseDouble(Character.toString(bevitel_c[5]) + Character.toString(bevitel_c[6]));
                        rn = rn + (rnm / 60) + (rns / 3600);
                    }
                    break;
                case "2":
                    rn = Double.parseDouble(Character.toString(bevitel_c[0]) + Character.toString(bevitel_c[1]));
                    if (bevitel_c.length == 4) {
                        double rnm = Double.parseDouble(Character.toString(bevitel_c[3]));
                        rn = rn + (rnm / 60);
                    } else if (bevitel_c.length == 5) {
                        double rnm = Double.parseDouble(Character.toString(bevitel_c[3]) + Character.toString(bevitel_c[4]));
                        rn = rn + (rnm / 60);
                    } else if (bevitel_c.length == 7) {
                        double rnm = Double.parseDouble(Character.toString(bevitel_c[3]) + Character.toString(bevitel_c[4]));
                        double rns = Double.parseDouble(Character.toString(bevitel_c[6]));
                        rn = rn + (rnm / 60) + (rns / 3600);
                    } else if (bevitel_c.length == 8) {
                        double rnm = Double.parseDouble(Character.toString(bevitel_c[3]) + Character.toString(bevitel_c[4]));
                        double rns = Double.parseDouble(Character.toString(bevitel_c[6]) + Character.toString(bevitel_c[7]));
                        rn = rn + (rnm / 60) + (rns / 3600);
                    }
                    break;
                case "3":
                    rn = Double.parseDouble(Character.toString(bevitel_c[0]) + Character.toString(bevitel_c[1]) + Character.toString(bevitel_c[2]));
                    if (bevitel_c.length == 5) {
                        double rnm = Double.parseDouble(Character.toString(bevitel_c[4]));
                        rn = rn + (rnm / 60);
                    } else if (bevitel_c.length == 6) {
                        double rnm = Double.parseDouble(Character.toString(bevitel_c[4]) + Character.toString(bevitel_c[5]));
                        rn = rn + (rnm / 60);
                    } else if (bevitel_c.length == 8) {
                        double rnm = Double.parseDouble(Character.toString(bevitel_c[4]) + Character.toString(bevitel_c[5]));
                        double rns = Double.parseDouble(Character.toString(bevitel_c[7]));
                        rn = rn + (rnm / 60) + (rns / 3600);
                    } else if (bevitel_c.length == 9) {
                        double rnm = Double.parseDouble(Character.toString(bevitel_c[4]) + Character.toString(bevitel_c[5]));
                        double rns = Double.parseDouble(Character.toString(bevitel_c[7]) + Character.toString(bevitel_c[8]));
                        rn = rn + (rnm / 60) + (rns / 3600);
                    }
                    break;
            }
        }
        rnr = Math.toRadians(rn);
        r[2] = Double.toString(rnr);
        return r;
    }
    public String kiiras (double bevitel)
    {
        DecimalFormat df = new DecimalFormat("#");
        DecimalFormat df2 = new DecimalFormat("##");
        df2.setMinimumIntegerDigits(2);

        int fok = (int) Math.floor(bevitel);
        double t1 = (bevitel - (Math.floor(bevitel))) * 60;
        int perc = (int) (Math.floor((bevitel - (Math.floor(bevitel))) * 60));
        int masod = Integer.parseInt(df.format( (t1 - perc) * 60));
        return (df2.format(fok) + "-" + df2.format(perc) +"-"+ df2.format(masod));
    }
}
