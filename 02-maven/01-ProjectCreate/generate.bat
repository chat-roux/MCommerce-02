@ECHO off

SET /p artifactId=veuillez entrer le nom du projet (artifactId) ?
SET /p groupId=veuillez entrer le groupe du projet (groupId) ?
SET currentTime=%date:~-4,4%%date:~-10,2%%date:~-7,2%-%time:~0,2%%time:~3,2%%time:~6,2%

PAUSE

IF NOT EXIST "logGenerate" (
	MKDIR logGenerate
)

ECHO **********************************************************************************************************
ECHO * (01.)CREER LE MODULE 'PARENT' DE L'APPLICATION:
ECHO *
ECHO * 		PROCEDURE : CREER UN PROJET DE TYPE 'PARENT' AVEC LES CARACTERISTIQUES SUIVANTES :
ECHO *
ECHO * 		- UN FICHIER 'POM.XML'.
ECHO * 		- UN ARCHETYPE 'QUICKSTART'.
ECHO * 		- PAS DE REPERTOIRE 'SRC'.
ECHO * 		- DANS LE FICHIER 'POM.XML' : LA PROPRIETE 'PACKAGING' POSSEDE LA VALEUR 'POM' (ET NON PAS 'JAR' ! A CORRIGER !).
ECHO **********************************************************************************************************
CALL ^
mvn archetype:generate ^
 -DgroupId=%groupId% ^
 -DartifactId=%artifactId% ^
 -DarchetypeGroupId=org.codehaus.mojo.archetypes ^
 -DarchetypeArtifactId=pom-root ^
 -DarchetypeVersion=RELEASE ^
 -Dversion=0.0.1-SNAPSHOT ^
 -DinteractiveMode=false ^
 -e -X ^
 > logGenerate/generate-%artifactId%-root--%currentTime%.log

ECHO DONE

IF EXIST "compile.bat" (
	COPY compile.bat %artifactId%\compile.bat
	ECHO 	compile.bat dans %artifactId%
) ELSE (
	ECHO 	fichier non copie : compile.bat
	PAUSE
)

CD ./%artifactId%

MKDIR logGenerate
MKDIR logCompile

ECHO **********************************************************************************************************
ECHO * (02.)CREER LE MODULE 'MODEL' 
ECHO * 
ECHO * 		PROCEDURE : CREER UN PROJET DE TYPE 'ENFANT' AVEC LES CARACTERISTIQUES SUIVANTES :
ECHO *
ECHO * 		- UN FICHIER 'POM.XML' DE TYPE 'POM-ENFANT' (RENSEIGNER LE POM-PARENT).
ECHO * 		- UN ARCHETYPE 'QUICKSTART'.
ECHO * 		- DANS LE FICHIER 'POM.XML' : LA PROPRIETE 'PACKAGING' POSSEDE LA VALEUR 'JAR'.
ECHO **********************************************************************************************************
CALL ^
mvn -B archetype:generate ^
 -DgroupId=%groupId% ^
 -DartifactId=%artifactId%-model ^
 -Dpackage=%groupId%.model ^
 -DarchetypeGroupId=org.apache.maven.archetypes ^
 -DarchetypeArtifactId=maven-archetype-quickstart ^
 -DarchetypeVersion=RELEASE ^
 -Dversion=0.0.1-SNAPSHOT ^
 -DinteractiveMode=false ^
 -e -X ^
 > logGenerate/generate-%artifactId%-model--%currentTime%.log

ECHO DONE

ECHO **********************************************************************************************************
ECHO * (04.)CREER LE MODULE 'BUSINESS' 
ECHO * 
ECHO * 		PROCEDURE : CREER UN PROJET DE TYPE 'ENFANT' AVEC LES CARACTERISTIQUES SUIVANTES :
ECHO *
ECHO * 		- UN FICHIER 'POM.XML' DE TYPE 'POM-ENFANT' (RENSEIGNER LE POM-PARENT).
ECHO * 		- UN ARCHETYPE 'QUICKSTART'.
ECHO * 		- DANS LE FICHIER 'POM.XML' : LA PROPRIETE 'PACKAGING' POSSEDE LA VALEUR 'JAR'.
ECHO **********************************************************************************************************
CALL ^
mvn -B archetype:generate ^
 -DgroupId=%groupId% ^
 -DartifactId=%artifactId%-business ^
 -Dpackage=%groupId%.business ^
 -DarchetypeGroupId=org.apache.maven.archetypes ^
 -DarchetypeArtifactId=maven-archetype-quickstart ^
 -DarchetypeVersion=RELEASE ^
 -Dversion=0.0.1-SNAPSHOT ^
 -DinteractiveMode=false ^
 -e -X ^
 > logGenerate/generate-%artifactId%-business--%currentTime%.log

ECHO DONE

ECHO **********************************************************************************************************
ECHO * (03.)CREER LE MODULE 'PERSISTENCE' 
ECHO * 
ECHO * 		PROCEDURE : CREER UN PROJET DE TYPE 'ENFANT' AVEC LES CARACTERISTIQUES SUIVANTES :
ECHO *
ECHO * 		- UN FICHIER 'POM.XML' DE TYPE 'POM-ENFANT' (RENSEIGNER LE POM-PARENT).
ECHO * 		- UN ARCHETYPE 'QUICKSTART'.
ECHO * 		- DANS LE FICHIER 'POM.XML' : LA PROPRIETE 'PACKAGING' POSSEDE LA VALEUR 'JAR'.
ECHO **********************************************************************************************************
CALL ^
mvn -B archetype:generate ^
 -DgroupId=%groupId% ^
 -DartifactId=%artifactId%-persistence ^
 -Dpackage=%groupId%.persistence ^
 -DarchetypeGroupId=org.apache.maven.archetypes ^
 -DarchetypeArtifactId=maven-archetype-quickstart ^
 -DarchetypeVersion=RELEASE ^
 -Dversion=0.0.1-SNAPSHOT ^
 -DinteractiveMode=false ^
 -e -X ^
 > logGenerate/generate-%artifactId%-persistence--%currentTime%.log

ECHO DONE

ECHO **********************************************************************************************************
ECHO * (05.)CREER LE MODULE 'WEBAPP' 
ECHO * 
ECHO * 		PROCEDURE : CREER UN PROJET DE TYPE 'ENFANT' AVEC LES CARACTERISTIQUES SUIVANTES :
ECHO *
ECHO * 		- UN FICHIER 'POM.XML' DE TYPE 'POM-ENFANT' (RENSEIGNER LE POM-PARENT).
ECHO * 		- UN ARCHETYPE 'WEBAPP' (POINT D'ENTREE DE L'APPLICATION !).
ECHO * 		- DANS LE FICHIER 'POM.XML' : LA PROPRIETE 'PACKAGING' POSSEDE LA VALEUR 'JAR'.
ECHO **********************************************************************************************************
CALL ^
mvn -B archetype:generate ^
 -DgroupId=%groupId% ^
 -DartifactId=%artifactId%-webapp ^
 -Dpackage=%groupId%.webapp ^
 -DarchetypeGroupId=org.apache.maven.archetypes ^
 -DarchetypeArtifactId=maven-archetype-webapp ^
 -DarchetypeVersion=RELEASE ^
 -Dversion=0.0.1-SNAPSHOT ^
 -DinteractiveMode=false ^
 -e -X ^
 > logGenerate/generate-%artifactId%-webapp--%currentTime%.log

ECHO DONE

PAUSE