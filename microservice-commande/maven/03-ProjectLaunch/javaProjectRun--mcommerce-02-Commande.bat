REM **********************************************************************************************************
REM * (01.)EFFECTUER LES OPERATIONS SUIVANTES :
REM *
REM *      ->LANCER LE FICHIER EXECUTABLE DU PROJET.
REM **********************************************************************************************************
java -verbose ^
 -Dserver.port=9020 ^
 -jar ^
 ./target/mcommande.jar ^
 > .\logExecute\run--mcommerce-02-Commande--Port9020--%date:~6,4%.%date:~3,2%.%date:~0,2%--0%time:~1,1%.%time:~3,2%.%time:~6,2%.log
 
java -verbose ^
 -Dserver.port=9020 ^
 -jar ^
 ./target/mcommande.jar ^
 > .\logExecute\run--mcommerce-02-Commande--Port9020--%date:~6,4%.%date:~3,2%.%date:~0,2%--%time:~0,2%.%time:~3,2%.%time:~6,2%.log
