REM **********************************************************************************************************
REM * (01.)CREER LE MODULE 'PARENT' DE L'APPLICATION :
REM *
REM *      PROCEDURE : CREER UN MODULE 'PARENT' AVEC LES CARACTERISTIQUES SUIVANTES :
REM *
REM *      ->(01.01.)UN FICHIER 'POM.XML'.
REM *      ->(01.02.)UN ARCHETYPE 'QUICKSTART'.
REM **********************************************************************************************************
mvn archetype:generate ^
 -DgroupId=fr.afpa ^
 -DartifactId=GestionDUtilisateurs-03 ^
 -DarchetypeGroupId=org.apache.maven.archetypes ^
 -DarchetypeArtifactId=maven-archetype-quickstart ^
 -DarchetypeVersion=1.1 ^
 -Dversion=1.0-SNAPSHOT ^
 -DinteractiveMode=false ^
 -e -X ^
 > ./logGenerate/generate--GestionDUtilisateurs-03.log


REM **********************************************************************************************************
REM * (02.)CONFIGURER LE MODULE 'PARENT' CREE PRECEDEMMENT :
REM *
REM *      PROCEDURE : CONFIGURER LE MODULE 'PARENT' EN EFFECTUANT LES TACHES SUIVANTES :
REM *
REM *      ->(02.01.)POINTER SUR LE REPERTOIRE DU MODULE PARENT CREE PRECEDEMMENT.
REM *      ->(02.02.)SUPPRIMER LE REPERTOIRE 'SRC' : 
REM *      ->(02.03.)DANS LE FICHIER 'POM.XML' : 
REM *                ->LA PROPRIETE 'PACKAGING' : DEFINIR SA VALEUR A 'POM' (ET NON 'JAR' !! A CORRIGER !!)
REM **********************************************************************************************************
cd GestionDUtilisateurs-03

rmdir /S /Q ./src


REM **********************************************************************************************************
REM * (03.)CREER LE MODULE 'MODEL' 
REM * 
REM *      PROCEDURE : CREER UN PROJET DE TYPE 'ENFANT' AVEC LES CARACTERISTIQUES SUIVANTES :
REM *
REM *      ->(03.01.)POINTER SUR LE REPERTOIRE DU MODULE PARENT CREE PRECEDEMMENT.
REM *      ->(03.02.)UN FICHIER 'POM.XML' DE TYPE 'POM-ENFANT' (RENSEIGNER LA PROPRIETE 'PARENT').
REM *      ->(03.03.)UN ARCHETYPE 'QUICKSTART'.
REM *      ->(03.04.)DANS LE FICHIER 'POM.XML' : 
REM *                ->LA PROPRIETE 'PACKAGING' : DEFINIR SA VALEUR A 'JAR'.
REM **********************************************************************************************************
mvn -B archetype:generate ^
 -DgroupId=fr.afpa ^
 -DartifactId=GestionDUtilisateurs-03-model ^
 -Dpackage=fr.afpa.model ^
 -DarchetypeGroupId=org.apache.maven.archetypes ^
 -DarchetypeArtifactId=maven-archetype-quickstart ^
 -DarchetypeVersion=1.1 ^
 -DinteractiveMode=false ^
 -e -X ^
 > ./logGenerate/generate--GestionDUtilisateurs-03-model.log


REM **********************************************************************************************************
REM * (04.)CREER LE MODULE 'PERSISTENCE' 
REM * 
REM *      PROCEDURE : CREER UN PROJET DE TYPE 'ENFANT' AVEC LES CARACTERISTIQUES SUIVANTES :
REM *
REM *      ->(04.01.)POINTER SUR LE REPERTOIRE DU MODULE PARENT CREE PRECEDEMMENT.
REM *      ->(04.02.)UN FICHIER 'POM.XML' DE TYPE 'POM-ENFANT' (RENSEIGNER LA PROPRIETE 'PARENT').
REM *      ->(04.03.)UN ARCHETYPE 'QUICKSTART'.
REM *      ->(04.04.)DANS LE FICHIER 'POM.XML' : 
REM *                ->LA PROPRIETE 'PACKAGING' : DEFINIR SA VALEUR A 'JAR'.
REM **********************************************************************************************************
mvn -B archetype:generate ^
 -DgroupId=fr.afpa ^
 -DartifactId=GestionDUtilisateurs-03-persistence ^
 -Dpackage=fr.afpa.persistence ^
 -DarchetypeGroupId=org.apache.maven.archetypes ^
 -DarchetypeArtifactId=maven-archetype-quickstart ^
 -DarchetypeVersion=1.1 ^
 -DinteractiveMode=false ^
 -e -X ^
 > ./logGenerate/generate--GestionDUtilisateurs-03-persistence.log


REM **********************************************************************************************************
REM * (05.)CREER LE MODULE 'BUSINESS' 
REM * 
REM *      PROCEDURE : CREER UN PROJET DE TYPE 'ENFANT' AVEC LES CARACTERISTIQUES SUIVANTES :
REM *
REM *      ->(05.01.)POINTER SUR LE REPERTOIRE DU MODULE PARENT CREE PRECEDEMMENT.
REM *      ->(05.02.)UN FICHIER 'POM.XML' DE TYPE 'POM-ENFANT' (RENSEIGNER LA PROPRIETE 'PARENT').
REM *      ->(05.03.)UN ARCHETYPE 'QUICKSTART'.
REM *      ->(05.04.)DANS LE FICHIER 'POM.XML' : 
REM *                ->LA PROPRIETE 'PACKAGING' : DEFINIR SA VALEUR A 'JAR'.
REM **********************************************************************************************************
mvn -B archetype:generate ^
 -DgroupId=fr.afpa ^
 -DartifactId=GestionDUtilisateurs-03-business ^
 -Dpackage=fr.afpa.business ^
 -DarchetypeGroupId=org.apache.maven.archetypes ^
 -DarchetypeArtifactId=maven-archetype-quickstart ^
 -DarchetypeVersion=1.1 ^
 -DinteractiveMode=false ^
 -e -X ^
 > ./logGenerate/generate--GestionDUtilisateurs-03-business.log


REM **********************************************************************************************************
REM * (06.)CREER LE MODULE 'UI' 
REM * 
REM *      PROCEDURE : CREER UN PROJET DE TYPE 'ENFANT' AVEC LES CARACTERISTIQUES SUIVANTES :
REM *
REM *      ->(06.01.)POINTER SUR LE REPERTOIRE DU MODULE PARENT CREE PRECEDEMMENT.
REM *      ->(06.02.)UN FICHIER 'POM.XML' DE TYPE 'POM-ENFANT' (RENSEIGNER LA PROPRIETE 'PARENT').
REM *      ->(06.03.)UN ARCHETYPE 'WEBAPP'.
REM *      ->(06.04.)DANS LE FICHIER 'POM.XML' : 
REM *                ->LA PROPRIETE 'PACKAGING' : DEFINIR SA VALEUR A 'WAR'.
REM **********************************************************************************************************
mvn -B archetype:generate ^
 -DgroupId=fr.afpa ^
 -DartifactId=GestionDUtilisateurs-03-ui ^
 -Dpackage=fr.afpa.ui ^
 -DarchetypeGroupId=org.apache.maven.archetypes ^
 -DarchetypeArtifactId=maven-archetype-webapp ^
 -DarchetypeVersion=RELEASE ^
 -DinteractiveMode=false ^
 -e -X ^
 > ./logGenerate/generate--GestionDUtilisateurs-03-ui.log

