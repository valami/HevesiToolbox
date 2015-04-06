package hu.valamas.hevesitoolbox.szamolasok.alapmuvelet;

public class trigonometria {
    public double valt(double fok, double perc, double masod) {
        return (Math.toRadians(fok + (perc / 60) + (masod / 3600)));
    }
    public double[] visszavalt(double szog) {
        double fok = Math.floor(szog);
        double t1 = (szog - (Math.floor(szog))) * 60;
        double perc = (Math.floor((szog - (Math.floor(szog))) * 60));
        double masod = (t1 - perc) * 60;
        double[] valt = new double[3];
        valt[0] = fok;
        valt[1] = perc;
        valt[2] = masod;
        return (valt);
    }
    public double heron(double a, double b, double c) {
        double s = (a + b + c) / 2;
        return (Math.sqrt(s * (s - a) * (s - b) * (s - c)));
    }
    public double costetel_szog(double a, double b, double c) {
        return (Math.toDegrees(Math.acos((Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2)) / (2 * a * b))));
    }
    public double costetel_oldal(double a, double b, double gamma) {
        return (Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) - 2 * a * b * (Math.cos(gamma))));
    }
    public double szinusztetel_szog(double tavfelso, double tavalso, double szogalso) {
        return (Math.asin((tavfelso / tavalso) *Math.sin(szogalso)));
    }
    public double szinusztetel_oldal (double szogfelso, double szogalso, double tavalso) {
        return ((Math.sin(szogfelso)/Math.sin(szogalso) )*tavalso);
    }
    public double pi ()
    {
        return 3.141592653589793238462643383279502884197169;
    }
}
