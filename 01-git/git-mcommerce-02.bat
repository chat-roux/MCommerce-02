#-----INITIALISER-LE-REPOSITORY-LOCAL----
git init

#-----AFFICHER-LES-BRANCHES-DU-REPOSITORY-LOCAL----
git branch

#----AJOUTER-LES-REPERTOIRES-ET-FICHIERS-DANS-LE-REPOSITORY-LOCAL----
git add .

#----AJOUTER-LES-REPERTOIRES-ET-FICHIERS-DANS-LE-REPOSITORY-LOCAL----
git remote add origin https://github.com/chat-roux/mcommerce-partie2.git
git remote add origin git@github.com:chat-roux/MCommerce-partie2.git

git remote rm origin

git fetch

git commit -m "Premier commit"

git push -u origin master
git push -u origin master


