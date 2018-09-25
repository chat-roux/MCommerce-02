##############################################################################
# (01.)INITIALISER UN REPOSITORY LOCAL :
#      -->REPOSITORY A INITIALISER : LE REPPERTOIRE COURANT.
##############################################################################
git init

##############################################################################
# (02.)AFFICHER LE CONTENU D'UN REPOSITORY LOCAL :
#      -->CONTENU A AFFICHER : TOUTES LES BRANCHES DU REPOSITORY LOCAL COURANT.
##############################################################################
git branch

##############################################################################
# (03.)AJOUTER / RETIRER DES ELEMENTS DANS LE PROCHAIN COMMIT :
#      -->ELEMENTS A AJOUTER / RETIRER : UN FICHIER / REPERTOIRE DU REPERTOIRE COURANT.
##############################################################################
git add [NOM DU FICHIER   ]
git add [NOM DU REPERTOIRE]
git rm  [NOM DU FICHIER   ]
git rm  [NOM DU REPERTOIRE]

##############################################################################
# (04.)LANCER LE PROCHAIN COMMIT VERS UN REPOSITORY LOCAL :
#      -->REPOSITORY LOCAL DESTINATAIRE : LE REPOSITORY LOCAL COURANT.
##############################################################################
git commit -m "Premier commit"

##############################################################################
# (05.)ENREGISTRER / DESENREGISTRER UN REPOSITORY DISTANT COMME "CIBLE" D'UN REPOSITORY LOCAL :
#      -->REPOSITORY DISTANT A ENREGISTRER / DESENREGISTRER          : LE REPOSITORY DISTANT FOURNI.
#      -->REPOSITORY LOCAL DANS LEQUEL ENREGISTRER / /DESENREGISTRER : LE REPOSITORY LOCAL COURANT.
##############################################################################
git remote add distant https://github.com/chat-roux/MCommerce-partie2.git
git remote add distant git@github.com:chat-roux/MCommerce-partie2.git
git remote rm distant

##############################################################################
# (06.)TRANSFERER LE CONTENU DU REPOSITORY DISTANT DANS LE REPOSITORY LOCAL :
#      -->ORIGINE DU TRANSFERT     : LA "CIBLE" PRE-ENREGISTREE (REPOSITORY DISTANT).
#      -->DESTINATION DU TRANSFERT : LE REPERTOIRE COURANT (REPOSITORY LOCAL COURANT).
#      -->TYPE DE TRANSFERT : TRANSFERT SANS ECRASEMENT.
##############################################################################
git fetch

##############################################################################
# (07.)TRANSFERER LE CONTENU DU REPOSITORY LOCAL DANS LE REPOSITORY DISTANT :
#      -->ORIGINE DU TRANSFERT     : LE REPERTOIRE COURANT (REPOSITORY LOCAL COURANT).
#      -->DESTINATION DU TRANSFERT : LA "CIBLE" PRE-ENREGISTREE (REPOSITORY DISTANT).
#      -->TYPE DE TRANSFERT : TRANSFERT AVEC ECRASEMENT.
##############################################################################
git push -u distant master
