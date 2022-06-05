Description
============

Dota 2 is a 5v5 real time strategy game, where each player controls a single hero. These heroes can buy items, cast spells, damage & kill other heroes, destroy buildings, and so much more.

You are given two sample subsets of a Dota 2 “combat log” - a human-readable record of events that happened in a match. The heroes in the game are encoded as `npc_dota_hero_{hero_name}` within the combat log, and items are encoded as `item_{item_name}`. Each line represents one event, and it begins with a timestamp relative to the start of the match. It’s normal for there to be no events for the first few minutes - this is when the players are choosing their hero for the match. If you don’t understand a specific event, rather ignore it and carry on processing the rest. 

The events that we are interested in are:
* Items being purchased
* Heroes killing each other
* Spells being cast (also has the level of the spell, there are multiple different levels for each spell)
* Damage being done to a hero (the word "hits" gives this away) could be from:
  * A normal attack (signified by dota_unknown as the damage type)
  * A spell or item

All other events (items being used, buildings taking damage, gamestate changes, healing done, etc) can be ignored.

We have included [sample data](./data) for testing.

Task
====

Your task is to:

* Process the combat log for both of the matches and extract the events described above.
* Store these significant events in a relational database (H2/PostgreSQL).
* Expose the key queries via a REST API (see below).
* Bonus: Test coverage (optional)

Feel free to use third party dependencies if needed, but only after considering their licensing model.
Please include a reason for pulling additional dependencies.

We have included `TODO` action items in the code to give you a starting point.

Please limit the time you are spending on this to 2 hours and include a short explanation if you omitted, or were not able to finish a part of the task.
Any additional information or explanation should go into the README.md file.

Send us a link to a git repository after you are done.


Notes
=====

The response models and package structure of the REST interface should not be changed, as we are using these classes in our test suite and moving or renaming these will break it.
You can assume the database to be empty at the beginning, our tests will use the API to ingest matches and validate the responses of each of the endpoints.

Timestamps should be parsed as they are given and not be based on EPOCH or today's date. Meaning the long value should represent the game time in milliseconds.


Key Queries
===========

* `POST /api/match`
An endpoint to receive a combat log text file and ingest it into the system. You can post a text file to the endpoint using the following `curl` command:

````curl -H "Content-Type: text/plain" --data-binary @combatlog_1.txt http://localhost:8080/api/match````

* `GET /api/match/$match_id`
A list of the heroes within a match and the number of kills they made.

````
[{
   "hero": "rubick",
   "kills": 7
}, ...]
````

* `GET /api/match/$match_id/$hero_name/items`
Each item purchase (time bought and the item name) made by the specified hero within the selected match

````
[{
   "item": "quelling_blade",
   "timestamp": 530925
}, ...]
````

* `GET /api/match/$match_id/$hero_name/spells`
For each different spell a hero casts in a particular match, the number of times they cast said spell. The spell name is the name as encountered in the log file.

````
[{
   "spell": "abyssal_underlord_firestorm",
   "casts": 83
}, ...]
````

* `GET /api/match/$match_id/$hero_name/damage`
For each hero that was damaged by the specified hero, the number of times we damaged that hero, and the total damage done to that hero

````
[{
   "target": "snapfire",
   "damage_instances": 67
   "total_damage": 79254
}, ...]
````
