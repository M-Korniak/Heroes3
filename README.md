## Heroes 3

Sample gameplay: https://cdn2.unrealengine.com/screens-02-3840x2160-93314bf113a1.jpg?h=720&quality=medium&resize=1&w=1280

What can you observe from it?
* two players
* a board composed of hexagons (height and width can be parameters)
* there are obstacles on the board - stones (let's ignore them for now)
* the units on the board are:
     * some are flying (can fly over obstacles and other units)
     *some are shooting
     *some take up two fields
     * have a certain range of movement
* units have numbers (and probably other characteristics)
* there are additional objects on the board (let's ignore them for now):
     * medical tent
     * ballista
     * ammunition cart
* the unit movement algorithm is as follows:
     * all units go to one list, sorted by movement range (initiative)
     * we select (and remove from the list) the unit with the largest movement range, and then we give the player a list of possible actions that he can perform:
       * ability to move to a given field (normal units cannot move to or through fields with obstacles or other units, but flying units can)
       * possibility of moving and attacking an enemy unit standing next to the field we want to move to
       * possibility to wait (if possible)
       * ability to defend yourself
       * shooting options - for shooting units (we can shoot at any enemy unit)
       * ability to attack any enemy unit standing on the field next to us
     * the player returns his action to us (moving to one of the fields, waiting, defending, and in the future it may be moving and attacking, attacking in place and shooting)
     * if a unit is waiting, we add it to the end of the list (each unit can wait only once per round)
     * if a unit is waiting, it receives a defense bonus (valid until the end of the round) and cannot move anymore this round; for now, it is enough to call some "cap" function, we will enter unit statistics later
     * if the unit moves, we change its position
     * we move on to the next unit
     * when the list is empty, we start a new round
     * IMPORTANT: In the future, there may be unit range modifiers that will cause changes to the movement order

## Homework for May 15, 2023:
Implement the class hierarchy needed for the game. In particular, write:
* algorithm for finding possible movement fields, taking into account that some units can fly
* unit movement algorithm (described above); players can perform all the actions described there, but for now you can choose not to implement the attack and shoot actions
* writing the board state in text form so that the program can be debugged - you will be debugging them, so write them in a way that is convenient for you

You can develop the provided classes or develop your own project from scratch.


## Homework for June 12, 2023:

PRELIMINARY NOTE - UNIT A BRANCH:
A unit is formally some type of creature, e.g. Pikeman, Angel, Green Dragon, etc. with all its statistics and attributes (attack, defense, whether it flies, whether it shoots, etc.).
A unit is a group of units of the same type, e.g. 5 Pikemen, which can occupy a space on the board. The unit also has information about whether one unit in the unit has incomplete health (e.g. 5 Pikemen with 10 life each and 1 Pikeman with 5 life each).
In the description of the task I used the phrase 'unit', but most often I mean 'unit'.

It is mandatory to appear (for a total of 1.5 points):
* unit statistics:
     * attack
     * defense
     * number of arrows (if shooting unit) - how many times a unit can shoot in a given game
     * life units
     * damage (number or range), e.g. 30 or 20-40
     * speed (initiative)
     *ground/flying type
     * examples are here: http://h3.heroes.net.pl/uniti/zamek
* a given player's unit consists of N units, one of which may have incomplete life (e.g. 5 units with 10 lives, 1 unit with 5 lives); when receiving damage, first the partial units lose life, and then the full units
* damage counting algorithm: https://mightandmagic.fandom.com/wiki/Damage_(Heroes)
* ability to attack an opponent's unit in melee:
     * you can attack an enemy unit standing on the field next to you
     * you can move to another field (according to the movement algorithm) and attack the enemy unit standing next to this field (note, there may be several units to choose from)
* 4-5 sample units with game statistics
* initial game state statically initialized, i.e. without loading from a file/standard input
* players make either random moves or moves defined in the code (e.g. first unit A attacks unit B, then unit C attacks unit D, etc.), or load moves from standard input (just implement one of these options)
* the game ends when only one player's units remain on the board
To get this 1,5 points, the project should be in a finished state, i.e. with no features implemented halfway through, the classes should be ordered and divided into packages. The code style should be acceptable. It is better to do fewer fids, but properly.

Extensions:

If a new unit feature appears (shots, area attacks), a unit that has this feature should also appear

* counterattack (0.5p) - each unit performs a counterattack to the first melee attack in a given round, i.e. after receiving damage, it automatically deals damage to the unit that attacked it
* shot option (1p):
     * shooting units can shoot at any enemy unit
     * shooting at units that are more than 10 squares away from the shooter reduces damage by half
     * units do not counterattack shots
     * if there is an enemy unit next to the shooting unit, the shooter cannot shoot - he can only make melee attacks, but this reduces the damage by half
* area attack units (0.5p):
     * dragons attack a given unit and the unit behind it in the same line
     * some shooting units (Lisz - http://h3.heroes.net.pl/uniti/nekropolia) attack a given unit and 6 fields around it
* morale and happiness (1p): http://h3.heroes.net.pl/umiejetnosci/umiejetnosci-podstawowe (description at the bottom of the page)
     * morale value is the sum of:
         * player characteristics (we assume that this is some specific value at the time of player creation)
         * influence on the morale of the player's units - if all units are from one faction, morale = 1, if we add units from another faction, morale drops by 1 for each new faction; in other words, morale = 2 - the number of factions in the player's army
     * happiness is a player feature (we assume that it is some specific value when the player is created)
* magic (2p):
     * http://h3.heroes.net.pl/magia
     * players start the game with a certain number of magic points and a certain power
     * each spell has a cost in magic points
     * each player can cast one spell per round (at the moment when he is to perform an action with his unit, but before performing this action; after casting a spell, the player performs an action with his unit)
     * the hero's power affects the strength of the spells cast
     * we assume that heroes cast spells at advanced level (based on Magic Arrow: it should deal `10 * power + 20` damage)
     * Spell Types:
         * some spells (e.g. magic arrow) deal damage,
         * others (e.g. Bloodlust, Blessing, Haste) temporarily modify the unit's statistics,
         * still others can remove obstacles, attack random spaces on the board, revive dead units, summon new units, take control of the opponent's unit, etc.
     * To get 2 points, you must add at least 4 different spells:
         * max 2 spells that temporarily change unit statistics,
         * max 1 damage-dealing spell,
         * min 1 spells from this list: Hypnosis, Quicksand, Resurrection, Cloning, Wall of Fire, Berserk
