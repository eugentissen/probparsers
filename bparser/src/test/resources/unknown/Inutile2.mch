/* http://www.iut-nantes.univ-nantes.fr/~habrias/SitWebLivreBr/automathes.html */
MACHINE 
���� Inutile2 

/* �On sait que pour prouver une op�ration, on doit prouver que P &� I => [S] I. 
Comme la pr�condition dans le cas Inutile1 est en contradiction avec l�invariant, l�ant�c�dent de l�implication est faux et l�implication est vraie. Cette op�ration a donc �t� prouv�e. 
Or on ne pourra jamais lancer une telle op�ration tout en respectant sa pr�condition. Les fonctions r1 et r2 �tant totales, on ne peut avoir un �l�ment appartenant au domaine de r2 et n�appartenant pas au domaine de r1. Donc, ce que l�on a sp�cifi� est inutile. Mais ceci a satisfait plusieurs �tudiants car le prouveur a prouv� l�op�ration ! Maintenant, si l�on corrige la pr�condition et si l�on fait la machine Inutile2, on a une op�ration non prouvable. Il manque, en effet, la substitution a := aa \/ {ea} apr�s le THEN. 
Comment lՎtudiant en est-il venu � �crire la pr�condition de la machine Inutile1 ? Ayant �crit la machine Inutile2, et le prouveur n�ayant pu prouver l�op�ration (normal !), lՎtudiant a modifi� la pr�condition plusieurs fois, jusquՈ ce que l�op�ration soit prouv�e. Ce faisant, il ne s�est d�ailleurs pas aper�u de la contradiction et n�a pas compris la raison du succ�s de la preuve suite � sa modification. 
*/
SETS 
���� AA; BB 
VARIABLES 
���� aa, r1, r2 
INVARIANT 
���� aa <:AA & 
���� r1 :  aa-->BB & 
���� r2 :  aa -->BB 
INITIALISATION 
���� aa, r1, r2 := {}, {}, {} 
OPERATIONS 
�ajoutA (ea, eb1, eb2) =
����� PRE 
           ea : AA &
���������� ea /: dom (r1)� & 
���������� ea /: dom (r2) & 
���������� eb1 : BB & 
���������� eb2 : BB 
� THEN 
������ r1 := r1 \/ {ea |->eb1} || 
������ r2 := r2 \/ {ea� |-> eb2} 
� END 
END 