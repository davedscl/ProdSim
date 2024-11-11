public interface ControlSystemAPI {
    void setEmptyPlaceSensor(boolean value); //setzt den Belegtstatus des Leergutplatzes. value ist ein binärer Wert (true=belegt, false = frei)
    boolean getEmptyPlaceSensor(); //liefert einen binären Wert, welcher den Belegtstatus des Leergutplatzes angibt, zurück (true=belegt, false = frei)
    void setFullPlaceSensor(boolean value); //setzt den Belegtstatus des Vollgutplatzes. value ist ein binärer wert (true= belegt, false=frei)
    boolean getFullPlaceSensor(); //liefert einen binären Wert, welcher den Belegtstatus des Vollgutplatzes angibt, zurück (true=belegt, false = frei)
    int startTimer(int seconds, Runnable callback); //startet einen Timer, der nach einer gewissen Anzahl von Sekunden einmalig eine Callback-Funktion aufruft. Liefert außerdem eine Timer-ID zurück.
    void killTimer(int id); //löscht einen noch laufenden Timer (verhindert damit das Aufrufen der callback)
}
