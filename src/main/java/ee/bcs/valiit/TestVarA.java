package ee.bcs.valiit;

import org.springframework.web.bind.annotation.PutMapping;

public class TestVarA {

    public static void main(String[] args) {


        // System.out.println(rkPindala(5, 6));
        System.out.println(secondLetter("12345678"));
        //System.out.println("Tegu on sajandiga: " + aastaArv(0));
    }

    public static int rkPindala(int a, int b) {
        int pindala = a * b;
        return pindala;
    }

    public static int aastaArv(int a) {
        a--;
        return a / 100 + 1;
    }

    public static String secondLetter(String in) {
        String out = "";
        for (int i = 0; i < in.length(); i = i + 2) {
            if (i % 2 == 0) {
                out = out + in.substring(i, i + 1) + " ";
            }
        }
        return out;
    }
}


// Test A
//        Ülesanne 1:
//        Kirjuta Controleri funktsioon mis läheb tööle PUT päringu peale urlile/employee/12 ja
//        loeb sisse töötaja id PATHist
//
//        @PutMapping("employee/{employeeId}")
//        public String employeeId(@Request @PathVariable("employeeId") Long id)
//
//        Ülesanne 2:
//        Kirjuta meetod,mis tagastab ristküliku pindala täisarvulisel kujul ning mille sisendparameetriteks
//        on kaks täisarvu(ristküliku küljepikkused).Kutsu see meetod main()-meetodist välja ja prindi tulemus
//        standardväljundisse.
//
//        Ülesanne 3:
//        Koosta json,kus on list töötajatest.Igal töötajal on nimi,aadress,ametikoht,puhkuseavaldused.
//        Puhkuseavaldused sisaldavad omakora algus ja lõpu kuupäeva
//
//[
//        {
//            "nimi":"Malle",
//            "aadress":"Maardu",
//            "ametikoht":"keevitaja",
//            "puhkuseavaldused":[{
//                "algus":"05.05",
//                "lõpp":"08.08"
//                }]
//        }
//]

//        TODO Ülesanne 4:Kirjuta SQL lause mis küsib andmebaasist töötaja id-ga 5.Tulna nimed on id,name,address
//        SELECT * FROM töötaja WHERE id='5';
//        TODO Ülesanne 5:
//        Kirjuta meetod,mis võtab sisendparameetriks aastaarvu täisarvulisel kujul vahemikus 1-2019 ja
//        tagastab täisarvu vahemikus 1-21vastavalt sellele,mitmendasse sajandisse antud aasta kuulub.Meetodi nime
//        võid ise välja mõelda.Kutsu see meetod välja main()-meetodist järgmiste erinevate
//        sisendväärtustega:
//        0,1,128,598,1624,1827,1996,2017.
//        Prindi need väärtused standardväljundisse.