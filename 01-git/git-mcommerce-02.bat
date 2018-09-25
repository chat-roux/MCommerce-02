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
# (03.)ENREGISTRER DES ELEMENTS DANS LE PROCHAIN COMMIT :
#      -->ELEMENTS A ENREGISTRER : LE CONTENU DU REPERTOIRE COURANT.
##############################################################################
git add .

##############################################################################
# (04.)LANCER LE PROCHAIN COMMIT VERS UN REPOSITORY LOCAL :
#      -->REPOSITORY LOCAL DESTINATAIRE : LE REPOSITORY LOCAL COURANT.
##############################################################################
git commit -m "Premier commit"

##############################################################################
# (05.)ENREGISTRER UN REPOSITORY DISTANT COMME "CIBLE" D'UN REPOSITORY LOCAL :
#      -->REPOSITORY DISTANT A ENREGISTRER         : LE REPOSITORY DISTANT FOURNI.
#      -->REPOSITORY LOCAL DANS LEQUEL ENREGISTRER : LE REPOSITORY LOCAL COURANT.
##############################################################################
git remote add distant https://github.com/chat-roux/MCommerce-partie2.git
git remote add distant git@github.com:chat-roux/MCommerce-partie2.git

##############################################################################
# (06.)DESENREGISTRER LE REPOSITORY DISTANT COMME "CIBLE" D'UN REPOSITORY LOCAL :
#      -->REPOSITORY DISTANT A DES-ENREGISTRER         : LE REPOSITORY DISTANT FOURNI.
#      -->REPOSITORY LOCAL DANS LEQUEL DES-ENREGISTRER : LE REPOSITORY LOCAL COURANT.
##############################################################################
git remote rm distant

##############################################################################
# (07.)RECUPERER LE CONTENU DU REPOSITORY DISTANT DANS LE REPOSITORY LOCAL :
#      -->REPOSITORY DISTANT A RECUPERER         : LA "CIBLE" PRE-ENREGISTREE.
#      -->REPOSITORY LOCAL DANS LEQUEL RECUPERER : LE REPOSITORY LOCAL COURANT.
#      -->TYPE DE RECUPERATION : RECUPERATION SANS ECRASEMENT.
##############################################################################
git fetch

##############################################################################
# (08.)RECUPERER LE CONTENU DU REPOSITORY DISTANT DANS LE REPOSITORY LOCAL :
#      -->REPOSITORY DISTANT A RECUPERER         : LA "CIBLE" PRE-ENREGISTREE.
#      -->REPOSITORY LOCAL DANS LEQUEL RECUPERER : LE REPOSITORY LOCAL COURANT.
#      -->TYPE DE RECUPERATION : RECUPERATION AVEC ECRASEMENT.
##############################################################################
git push -u distant master
