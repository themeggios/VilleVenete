# Ville Venete


## IDE e applicativi
- progetto creato con JetBrains IntelliJ IDEA Ultimate, garantito il funzionamento solo su questo IDE
- necessario Tomcat 11, occorre patchare XAMPP

## Creazione database
Creare un dabatase `villevenete` ed importare il file `.sql` nel database

## XAMPP patch
1. eliminare la cartella `C:\xampp\tomcat`
2. scaricare tomcat 11 [qui](https://dlcdn.apache.org/tomcat/tomcat-11/v11.0.2/bin/apache-tomcat-11.0.2-windows-x64.zip)
3. aprire il file `C:\xampp\xampp-control.ini` e andare alla sezione `[BinaryNames]` modificare `Tomcat=tomcat8.exe` in `Tomcat=tomcat11.exe`
