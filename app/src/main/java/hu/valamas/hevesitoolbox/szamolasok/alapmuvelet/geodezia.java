package hu.valamas.hevesitoolbox.szamolasok.alapmuvelet;

import com.example.valamas.hevesitoolbox.R;

public class geodezia {
    public double[] polaris (double  KY, double KX, double szog ,double tav)    {
        double[] r = new double[2];
        r[0] = KY + (tav * Math.sin(szog));
        r[1] = KX + (tav * Math.cos(szog));
        return r;

    }
    public double [] iranyszog (double KY ,double KX, double VY ,double VX) {
        double[] r =new double[2];

        double szogu = ((VY - KY) / (VX - KX));
        double szoge = Math.toDegrees(Math.atan(szogu));
        double szog = 0;

        if ((VY - KY) > 0 & (VX - KX) > 0) {
            szog = szoge;
        } else if ((VY - KY) > 0 & (VX - KX) < 0) {
            szog = szoge + 180;
        } else if ((VY - KY) < 0 & (VX - KX) < 0) {
            szog = szoge + 180;
        } else if ((VY - KY) < 0 & (VX - KX) > 0) {
            szog = szoge + 360;
        }
        else if ((VY - KY) > 0 & (VX - KX) == 0) {
            szog = 90;
        } else if ((VY - KY) == 0 & (VX - KX) > 0) {
            szog = 0;
        } else if ((VY - KY) < 0 & (VX - KX) == 0) {
            szog = 270;
        } else if ((VY - KY) == 0 & (VX - KX) < 0) {
            szog = 180;
        }else{
            szog = 666;
        }
        r[0] = szog;
        r[1] = Math.sqrt((((VY - KY) * (VY - KY)) + ((VX - KX) * (VX - KX))));

        return r;
    }

}
