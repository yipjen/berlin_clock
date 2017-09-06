Story: The Berlin Clock

Meta:
@scope interview

Narrative:
    As a clock enthusiast
    I want to tell the time using the Berlin Clock
    So that I can increase the number of ways that I can read the time



Scenario: Midnight + 1s
When the time is 00:00:01
Then the clock should look like
O
OOOO
OOOO
OOOOOOOOOOO
OOOO


Scenario: Dawn
When the time is 05:23:07
Then the clock should look like
O
ROOO
OOOO
YYRYOOOOOOO
YYYO


Scenario: lunch time
When the time is 11:55:34
Then the clock should look like
Y
RROO
ROOO
YYRYYRYYRYY
OOOO

Scenario: late afternoon
When the time is 17:30:05
Then the clock should look like
O
RRRO
RROO
YYRYYROOOOO
OOOO


Scenario: evening
When the time is 22:11:55
Then the clock should look like
O
RRRR
RROO
YYOOOOOOOOO
YOOO



Scenario: Midnight
When the time is 00:00:00
Then the clock should look like
Y
OOOO
OOOO
OOOOOOOOOOO
OOOO

Scenario: Middle of the afternoon
When the time is 13:17:01
Then the clock should look like
O
RROO
RRRO
YYROOOOOOOO
YYOO

Scenario: Just before midnight
When the time is 23:59:59
Then the clock should look like
O
RRRR
RRRO
YYRYYRYYRYY
YYYY

Scenario: Midnight
When the time is 24:00:00
Then the clock should look like
Y
RRRR
RRRR
OOOOOOOOOOO
OOOO


