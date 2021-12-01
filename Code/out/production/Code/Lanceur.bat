@echo off

:: Compile et créé un fichier jar, puis créé un fichier bat qui peut être lancé directement
title LANCEMENT

SET dir="%cd%"

:Restart
cls

:: -----------------------------------------------------------------------------------------------::
cd %dir%

:: -----------------------------------------------------------------------------------------------::

cd .\ws
RMDIR /S /Q %dir%\class\
MKDIR %dir%\class
javac -d ../class ../src/Lanceur.java ../src/model/*.java ../src/view/*.java ../src/util/*.java
:: compilation de tous les fichiers
echo [+] Compilation des fichiers achevee

cd ../class
MKDIR META-INF
echo Main-Class: Lanceur > META-INF/MANIFEST.MF
:: creation du manifeste pour le lancement du programme
jar cmf META-INF/MANIFEST.MF ebenial.jar view/*.class model/*.class util/*.class Lanceur.class
:: créé le fichier jar dans le dossier class
echo [+] Fichier JAR construit

:: -----------------------------------------------------------------------------------------------::

cd %dir%

:: -----------------------------------------------------------------------------------------------::

more +2 UniversEbenial.bat > tmp.txt
echo @echo off > UniversEbenial.bat
echo SET dir=%dir% >> UniversEbenial.bat
type tmp.txt >> UniversEbenial.bat
del tmp.txt

echo [+] Fichier executable construit
echo.
echo [+] Operation accomplie
echo [+] Vous pouvez maintenant lancer le fichier UniversEbenial pour acceder au programme !

:: timeout /t 5

SET /p rep=Lancer le programme = o, quitter = n, compiler = c
if %rep% == o (GOTO:Lancer)
if %rep% == n (GOTO:Finir)
if %rep% == c (GOTO:Restart)

:Lancer
CALL universEbenial.bat

:Finir

