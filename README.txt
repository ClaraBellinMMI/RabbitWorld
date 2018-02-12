RabbitWorld

Simulation de vie des lapins.

I/ Principe du jeu
Le joueur choisit son nombre de lapins et leur placement initial.
Les lapins se prom�nent dans un champ de fa�on al�atoire et d�s qu'ils rencontrent 
une carotte, ils la mangent.

II/ R�gles de vie
Le jeu est une simulation de la vie des lapins et des carottes. Tous ces acteurs
�voluent sur une grille de jeu de 20x20 cases �tant vides ou contenant un acteur.
Le temps est repr�sent� par des tours de jeu.
A chaque tour, les r�gles de vie du jeu d�finies sont les suivantes :
	- Les lapins, pouvant �tre m�les ou femelles et adultes ou b�b�s, se d�placent
	de fa�on al�atoire sur le champ (grille de jeu) et perdent un certaine quantit�
	de vie (1 point pour les adultes et 2 pour les b�b�s)
	- Lorsque la vie d�un lapin atteint 0, il meurt (dispara�t de la grille)
	- Les carottes, pouvant �tre normales ou empoisonn�es, sont fixes sur des
	cases de la grille
	- Un lapin m�le et un lapin femelle sur des cases adjacentes donnent naissance
	� 2 b�b�s lapins ayant initialement 10 points de vie, sur des cases adjacentes
	au couple. Ces derni�res majorent le nombre de b�b�s, c�est-�-dire que si
	elles sont trop peu nombreuses pour accueillir les 2 b�b�s, leur totalit� sera
	utilis� et moins de b�b�s seront n�s.
	- Lorsqu�un lapin se d�place sur une case contenant une carotte normale, il la
	consomme et gagne de la vie (3 point pour les adultes, 8 pour les b�b�s)
	- Lorsqu�un lapin se d�place sur une case contenant une carotte empoisonn�e,
	il la consomme et meurt
	- Lorsqu�un b�b� lapin a surv�cu pendant 6 tours, il devient adulte
	- Indiff�remment de sa nature, lorsqu�une carotte est mang�e, un carotte
	prends racine sur une case al�atoire de la grille. Au bout de 5 tours, si cette
	case est vide la carotte pousse, sinon son apparition est retard�e d�un tour
	- Si une carotte n�est pas consomm�e avant 10 tours depuis sa pousse, elle
	devient empoisonn�e.