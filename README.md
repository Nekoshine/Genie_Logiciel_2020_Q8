# E-Scape Game 2020/2021
Projet de logiciel porté par l'équipe Q8 composée de :
  * Bastien MAUBON
  * Alan SIGNOR
  * Cédric PLANTET
  * Baptiste GESLOT
  * Yann DAUVIN 
  * Esteban CORMIER

## Dévelopement
Ce projet a debuté le 20 novembre 2020

La livraison aura lieu le 29 janvier 2021

Le projet est réalisé en Java avec IntelliJ et sera distribué sur windows

## Lancement

Pour exécuter ce logiciel, il faut posséder :

- Java 8

Il est également necessaire d'ouvrir les ports 8418,11210 et les tranches 8809-8859, 10811-10861, 10163-10213 et 10288-10338.

Ensuite il faut executer le fichier ***E-scape_Game.jar*** dans le dossier ***livrable***

***ATTENTION*** 

Veuillez noter qu'il faut qu'un compte MJ soit connecté au préalable pour qu'un
joueur puisse acceder à des salles.

Pour executer le logiciel sur une machine MJ, utilisez la ligne de commande suivante :

***java -jar escapegame.jar***

vous pouvez utiliser le couple id/mdp : ***admin/admin*** ou creer un compte MJ avec la clé temporaire suivante : ***JeSuisLaCleSecurisee***

Pour executer le logiciel pour un joueur en local sur la meme machine que le mj, utilisez la ligne de commande suivante : 

***java -jar E-scape_Game.jar***

Pour executer le logiciel sur une machine joueur distincte sûr le même réseau local, utilisez la ligne de commande suivante avec l'ip du maître du jeu recuperable grace à la commande ***ipconfig***:

***java -jar escapegame.jar***ipmj***

Vous trouvrerez dans le dossier ***livrable*** le document ***manuel_d_utilisation.pdf*** détaillant l'ensemble des fonctionnalités.