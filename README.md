bayes-dota
==========

This is the [task](TASK.md).

Any additional information about your solution goes here.

Observation :-

There are some lines in the data where hero name doesnot start with npc_dota_hero due to which there may be some discripency in the response expected , Please find example below :-
[00:12:22.873] npc_dota_neutral_harpy_scout is killed by npc_dota_creep_goodguys_melee

Implemented all the API's , tests are present inside 
gg.bayes.challenge

To Build the project :-

mvn clean install 

To run the Service use :-

mvn spring-boot:run



Table Structure :-

Please refer 
package gg.bayes.challenge.rest.entity;



Link to access H2 console :-

http://localhost:8080/h2-console



Curl to post the data :-

curl -H "Content-Type: text/plain" --data-binary @data/combatlog_1.txt http://localhost:8080/api/match



