package hu.valamas.hevesitoolbox.szamolasok.felulet;

public class szogkezeles {
    public String tagolas (String bevitel)
    {
        String r =bevitel;
        char[] bevitel_c = bevitel.toCharArray();

        if (bevitel_c.length == 1)  {
            if (bevitel_c[0]=='-')  {
                return "";
            }
        }   else if (bevitel_c.length == 4) {
            if (bevitel_c[1] != '-' && bevitel_c[2] != '-' && bevitel_c[3] != '-') {
                r = bevitel.substring(0,3) + "-" + Character.toString(bevitel_c[3]);
            }
        }




        return r;
    }
    /*
    public String kiiras (String bevitel)
    {
        return "";
    }
    public double szamra (String bevitel)
    {
        return 0;
    }
    */


}
