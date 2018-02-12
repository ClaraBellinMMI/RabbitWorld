RabbitWorld

Simulation de vie des lapins.

I/ Principe du jeu
Le joueur choisit son nombre de lapins et leur placement initial.
Les lapins se promènent dans un champ de façon aléatoire et dès qu'ils rencontrent 
une carotte, ils la mangent.

II/ Règles de vie
Le jeu est une simulation de la vie des lapins et des carottes. Tous ces acteurs
évoluent sur une grille de jeu de 20x20 cases étant vides ou contenant un acteur.
Le temps est représenté par des tours de jeu.
A chaque tour, les règles de vie du jeu définies sont les suivantes :
	- Les lapins, pouvant être mâles ou femelles et adultes ou bébés, se déplacent
	de façon aléatoire sur le champ (grille de jeu) et perdent un certaine quantité
	de vie (1 point pour les adultes et 2 pour les bébés)
	- Lorsque la vie d’un lapin atteint 0, il meurt (disparaît de la grille)
	- Les carottes, pouvant être normales ou empoisonnées, sont fixes sur des
	cases de la grille
	- Un lapin mâle et un lapin femelle sur des cases adjacentes donnent naissance
	à 2 bébés lapins ayant initialement 10 points de vie, sur des cases adjacentes
	au couple. Ces dernières majorent le nombre de bébés, c’est-à-dire que si
	elles sont trop peu nombreuses pour accueillir les 2 bébés, leur totalité sera
	utilisé et moins de bébés seront nés.
	- Lorsqu’un lapin se déplace sur une case contenant une carotte normale, il la
	consomme et gagne de la vie (3 point pour les adultes, 8 pour les bébés)
	- Lorsqu’un lapin se déplace sur une case contenant une carotte empoisonnée,
	il la consomme et meurt
	- Lorsqu’un bébé lapin a survécu pendant 6 tours, il devient adulte
	- Indifféremment de sa nature, lorsqu’une carotte est mangée, un carotte
	prends racine sur une case aléatoire de la grille. Au bout de 5 tours, si cette
	case est vide la carotte pousse, sinon son apparition est retardée d’un tour
	- Si une carotte n’est pas consommée avant 10 tours depuis sa pousse, elle
	devient empoisonnée.