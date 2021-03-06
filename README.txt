   _____ _      ______ _   _ _____  ______ _____  __  __          _   _ 
  / ____| |    |  ____| \ | |  __ \|  ____|  __ \|  \/  |   /\   | \ | |
 | (___ | |    | |__  |  \| | |  | | |__  | |__) | \  / |  /  \  |  \| |
  \___ \| |    |  __| | . ` | |  | |  __| |  _  /| |\/| | / /\ \ | . ` |
  ____) | |____| |____| |\  | |__| | |____| | \ \| |  | |/ ____ \| |\  |
 |_____/|______|______|_| \_|_____/|______|_|  \_\_|  |_/_/    \_\_| \_|
                                                                        
                                                                        
                     ------- THE 8 PAGES -------


Helló,

Ez itt egy hátrahagyott üzenet. Slenderman üldöz, és ezt azért hagyom hátra, hogy te is meg tudj menekülni.

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

A projekt alapvetően az IntelliJ IDEA fejlesztői környezetben lett megírva.
A 100%-os működéshez kérlek, ezt használd.

Követelmények:
        - Minimum JDK 11 vagy afeletti verzió
          maga a projekt openjdk 11.0.10-ben volt írva és tesztelve.
        - Java Swing library (Ez alapból be van építve JDK 11-be)
        - Windows vagy Linux OS
        - minimum ~100 MB szabad RAM és 10 MB szabad tárhely

//////////// /////////////
// A PROJEKT MEGNYITÁSA //
//////////// /////////////

IntelliJ IDEA fejlesztői környezetben:
        1. A "slenderman" mappa, ami ez a README.txt fájl mellett található, maga a projekt gyökere.
           Ezt nyisd meg IDEA-n belül.
        2. A jobb felső sarokban levő zöld háromszög futtatás gombbal tudod futtatni.
           (Több teendőd nincs. a .bat és .sh scriptek csak abban az esetben kellenek, ha nincs IDEA környezet.)
        4. Jó játékot :)

Amennyiben az IDEA használata nem lehetséges, vagy probléma adódik:
        A. WINDOWS esetén, tedd ezeket ebben a sorrendben:
                1. futtasd a WIN_build.bat fájlt
                2. futtasd a WIN_run.bat fájlt
                3. Jó játékot :)
        B. LINUX esetén a következő parancsokat futtasd a "slenderman" mappán belül, ebben a sorrendben:
                1. chmod +x LIN_build.sh
                2. chmod +x LIN_run.sh
                3. ./LIN_build.sh
                4. ./LIN_run.sh
                5. Jó játékot :)
        - Ha ezen scriptek futtatásánál gondok adódnak, az .sh fájlokban levő
          parancsok kimásolásával és manuális futtatásával is tégy egy próbát.
        (IntelliJ IDEA teljesen ingyenes nekünk hallgatóknak és nagyon ajánlom a használatát :) )

//////////// /////////////
//       JAVADOC        //
//////////// /////////////

A teljes kód (osztályok és függvények) JavaDoc dokumentációval el van látva.
Ennek a legenerált HTML változatát ez a fájl mellett levő "JavaDoc" mappában találhatod,
vagy IntelliJ IDEA-ban legenerálhatod saját magad a "Tools -> Generate JavaDoc" opcióval.

//////////// /////////////
//      JÁTÉKMENET      //
//////////// /////////////

Mozgás: WASD
Papír felvétele: F

Ahhoz, hogy papírt vehess fel, először a tereptárgy felé kell nézned.
Megjelenik egy "Press 'F' to pick up" szöveg, ami jelzi, hogy a papírt fel tudod venni.
Ha a tereptárgy, amin papír van, valamelyik oldaladon vagy mögötted van, a papírt nem veheted fel.

//////////// /////////////
//SAJÁT PÁLYA BETÖLTÉSE //
//////////// /////////////

Saját pályát írni egyszerű, ennek a részletes leírását az EXAMPLE_MAP.txt fájlban találod.
Az EXAMPLE_MAP.txt fájlt pedig be tudod tölteni, mint játszható pálya a
"JÁTÉK INDÍTÁSA FÁJLBÓL" menü opcióval.

Alapjáraton a beépített pályának a szöveges változatát tárolja, de ezt nyugodtan lehet módosítani.
(A beépített pálya kódban van rögzítve- a fájl változtatásával vagy a fájl hiányában az nem változik.)
