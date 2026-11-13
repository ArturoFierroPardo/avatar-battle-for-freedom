**English** · [Español](README.md)

# Avatar: Battle for Freedom

[![build](https://github.com/ArturoFierroPardo/avatar-battle-for-freedom/actions/workflows/build.yml/badge.svg)](https://github.com/ArturoFierroPardo/avatar-battle-for-freedom/actions/workflows/build.yml)
[![Java 17](https://img.shields.io/badge/Java-17-orange)](https://openjdk.org/projects/jdk/17/)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

A two-player, turn-based strategy game played on the console, set on Pandora.
Each player commands a faction — the human **RDA** or the **Na'vi** — buys
units, crews them, deploys them on a grid and fights until one army is wiped
out.

Written in Java as a coursework project for the Object-Oriented Programming
module of the Telecommunication Systems Engineering degree at Universidad CEU
San Pablo. The original submission is preserved in this repository's history;
the current version adds a Maven build, a test suite and fixes for the defects
listed at the end of this file.

> The game's interface is in Spanish, and so are the code identifiers and the
> Javadoc.

---

## Contents

- [How it plays](#how-it-plays)
- [Units and crew](#units-and-crew)
- [Battlegrounds](#battlegrounds)
- [Running it](#running-it)
- [Design](#design)
- [Project layout](#project-layout)
- [Tests](#tests)
- [Code review: defects found and fixed](#code-review-defects-found-and-fixed)
- [License](#license)

---

## How it plays

Players alternate turns. On your turn you pick one action from the menu:

| Option | Action |
|---|---|
| 1 | Buy units, and the crew each one needs to operate |
| 2 | Move a unit |
| 3 | Attack an enemy unit |
| 4 | Open your command centre |
| 5 | End your turn |
| 6 | Save the game and quit |

Everything costs **points**. You start with an allowance that depends on the
battleground, you spend it on units and crew, and you earn more by destroying
enemy units and killing their crew. A unit without its full crew can neither
move nor attack.

Combat resolves against a unit's **defence energy** first. Once that is gone,
the surplus damage is split evenly among the crew; crew members who run out of
life die, and a unit that loses its whole crew is destroyed and removed from
the board. Friendly fire is possible, and the command centre log says so when
it happens.

Moving and attacking also burn **movement energy** and **attack energy**, which
do not regenerate — so a unit has a finite useful life regardless of how much
damage it takes.

The **command centre** is each player's private log: every action by either
side is recorded with a timestamp, but you see more detail about your own moves
than about your opponent's — you are told that the enemy moved a unit, not
where it went.

Games can be saved and resumed; several saved games live side by side in
`partidas.dat` and are offered as a list on startup.

### A session, abbreviated

```
	AVATAR BATTLE FOR FREEDOM

-------------------ESCENARIOS-------------------

1) Arbol de las Almas:
	Nombre: Arbol de las Almas
	Puntos iniciales de cada jugador: 5000
	Dimension: 100X100
...
Jugador 1, elija su nombre:
Jugador 2, elija su nombre:

-------------------SELECCION DE EQUIPO-------------------

	1) Equipo RDA
	2) Equipo NAVI

La partida comienza en el escenario Arbol de las Almas.
Luchara Arturo del equipo NAVI contra Quaritch del equipo RDA

TURNO---------->  1
Turno de Arturo

-------------------MENU-------------------

	1) Comprar una unidad
	2) Mover una unidad
	3) Atacar a una unidad enemiga
	4) Ver el centro de mandos
	5) Finalizar el turno
	6) Finalizar y guardar partida
```

---

## Units and crew

Each faction has its own roster. Machines are crewed by humans, beasts are
ridden by Na'vi.

### RDA — machines

| Unit | Cost | Defence | Attack | Power | Range | Move | Crew |
|---|---|---|---|---|---|---|---|
| AT-99 Escorpion | 15000 | 7500 | 7500 | 500 | 10 | 20 | 4 |
| Aerospatiale SA-2 Samson | 2500 | 1000 | 1000 | 80 | 5 | 10 | 2 |
| Mitsubishi MK-6 | 500 | 0 | 1000 | 20 | 1 | 2 | 1 |

### Na'vi — beasts

| Unit | Cost | Defence | Attack | Power | Range | Move | Crew |
|---|---|---|---|---|---|---|---|
| Thanator | 8000 | 9000 | 9000 | 600 | 5 | 50 | 3 |
| Banshee de Montaña | 2000 | 3500 | 8000 | 300 | 10 | anywhere | 2 |
| Titanothere Cabezamartillo | 1000 | 11000 | 11000 | 300 | 1 | 2 | 1 |

The Banshee is the odd one out: it has no movement range at all and teleports
to a random free square anywhere on the map, at a flat energy cost. The MK-6 is
the other special case — it has no defence energy, so every hit goes straight
to its pilot.

### Crew

| Crew | Faction | Rank | Cost | Life | Points when killed |
|---|---|---|---|---|---|
| Comandante de SecOps | RDA | Officer | 100 | 150 | 400 |
| Artillero | RDA | Warrior | 50 | 150 | 100 |
| Omaticaya | Na'vi | Officer | 100 | 300 | 400 |
| Tipani | Na'vi | Warrior | 50 | 300 | 250 |

---

## Battlegrounds

| Battleground | Grid | Starting points |
|---|---|---|
| Árbol de las Almas | 100 × 100 | 5 000 |
| Jardines Colgantes | 200 × 200 | 20 000 |
| Árbol Madre | 300 × 300 | 45 000 |

---

## Running it

Requires a **JDK 17 or newer** and Maven.

```bash
git clone https://github.com/ArturoFierroPardo/avatar-battle-for-freedom.git
cd avatar-battle-for-freedom
mvn package
java -jar target/avatar-battle-for-freedom.jar
```

Two of the game's messages contain accented characters. On a console that is
not already using UTF-8 — the Windows command prompt, typically — run `chcp
65001` first so they display correctly.

Saved games are written to `partidas.dat` in the working directory.

In an IDE, open the folder as a Maven project — NetBeans, IntelliJ IDEA and
Eclipse all read `pom.xml` directly, with no further setup.

Other useful targets:

```bash
mvn test              # run the test suite
mvn javadoc:javadoc   # generate the Javadoc under target/
```

---

## Design

The model is a plain inheritance hierarchy with two abstract roots. `Unidad`
holds everything a playable piece has in common — cost, the three energy
pools, power, range and crew — and leaves each concrete unit to supply its own
numbers. Units that spend movement energy at a flat rate rather than per square
override `restarEnergia`, and the Banshee overrides `moverUnidad` outright
because it ignores movement range.

```mermaid
classDiagram
    direction TB

    class Partida {
        -int turnos
        -boolean terminada
        +comienzaPartida()
    }
    class Menu {
        +mostrarMenu()
    }
    class CentroDeMandos {
        -List~String~ acciones
        +anadirAccion(String)
    }
    class Jugador {
        -int puntos
        +comprarUnidad(Unidad)
        +comprarSeres(Unidad, Ser)
        +recibirDano(Unidad, Coordenadas)
    }
    class Ejercito {
        -List~Unidad~ unidades
    }
    class Escenario {
        <<abstract>>
        -Unidad[][] mapa
        +colocarUnidadSiLibre(Unidad, Coordenadas)
        +estaLibre(Unidad, Coordenadas, Escenario)
    }
    class Unidad {
        <<abstract>>
        -int energiadefensa
        -int energiataque
        -int energiamov
        +moverUnidad(Escenario)
        +atacarUnidad(Jugador, Escenario)
        +restarEnergia()
    }
    class Ser {
        <<abstract>>
        -int vida
    }
    class Coordenadas {
        -int x
        -int y
        +desplazada(Unidad, int, int, Escenario)$
    }

    class Maquina
    class Bestia
    class Humano
    class Navi
    <<abstract>> Maquina
    <<abstract>> Bestia
    <<abstract>> Humano
    <<abstract>> Navi

    Partida "1" --> "2" Jugador
    Partida "1" --> "2" CentroDeMandos
    Partida --> Escenario
    Partida ..> Menu : un turno
    Menu --> Jugador
    Jugador "1" --> "1" Ejercito
    Jugador --> Escenario
    Ejercito "1" --> "*" Unidad
    Unidad "1" --> "*" Ser : tripulantes
    Unidad --> Coordenadas : posicion
    Escenario "1" --> "*" Unidad : mapa

    Unidad <|-- Maquina
    Unidad <|-- Bestia
    Maquina <|-- AT99
    Maquina <|-- Aerospatiale
    Maquina <|-- MK6
    Bestia <|-- Banshee
    Bestia <|-- Thanator
    Bestia <|-- Titanothere

    Ser <|-- Humano
    Ser <|-- Navi
    Humano <|-- Comandante
    Humano <|-- Artillero
    Navi <|-- Omaticaya
    Navi <|-- Tipani

    Escenario <|-- ArbolAlmas
    Escenario <|-- Jardines
    Escenario <|-- ArbolMadre
```

A few decisions worth calling out:

- **The board is a `Unidad[][]`.** Occupancy and position are therefore stored
  twice — in the grid and in each unit's `Coordenadas` — and have to be kept in
  step by hand. `colocarUnidadSiLibre` is the single entry point that writes to
  the grid, which is what makes that tractable.
- **Energy as three separate pools** (defence, attack, movement) is what gives
  each unit its character: the Titanothere is a wall that can barely move, the
  Banshee is fragile but roams the whole map.
- **Everything is `Serializable`** so a game in progress can be written whole
  to disk with one `writeObject`, including the command centre logs. Every such
  class pins its `serialVersionUID` so that editing the code does not invalidate
  existing saves.
- **Custom checked exceptions** (`FueradeMapaException`,
  `PuntosdeCompraInsuficientesException`, `SinEnergiaAtaqueException`, …) carry
  rule violations up to the menu, which is the only layer that knows how to
  explain them to the player.
- **Back-references are excluded from `equals` and `hashCode`.** A unit knows
  its owner, the owner knows its army and the army knows its units, so
  comparing a unit by its owner would recurse forever.

---

## Project layout

```
src/main/java/
├── main/                      entry point
├── interfaz/
│   ├── partida/               turn loop and win conditions
│   ├── menu/                  the turn menu and the markets
│   └── centrodemandos/        per-player action log
├── modelos/
│   ├── jugador/               points, army, actions
│   ├── ejercito/
│   ├── equipo/                RDA / NAVI
│   ├── escenario/             battlegrounds and the board
│   ├── coordenadas/           squares, bounds and displacement
│   ├── unidad/                Unidad → Maquina | Bestia
│   ├── ser/                   Ser → Humano | Navi
│   └── excepciones/           rule violations
├── io/                        saving and loading
└── utils/                     console input

src/test/java/                 77 JUnit 5 tests
```

---

## Tests

77 JUnit 5 tests over the domain logic — board bounds, scenarios, units, crew,
the army, the player's actions and persistence:

```bash
mvn test
```

They are regression tests as much as unit tests: every defect in the list below
has a test that pins it, with a comment explaining what used to go wrong. The
suite was checked by mutation — reintroducing the fixes' original bugs by hand
makes it fail, and undoing that makes it pass again.

---

## Code review: defects found and fixed

Reviewing the original submission before publishing it turned up the following.
They are listed because the fixes are the bulk of the diff against the
coursework version, and because a couple of them are the kind of mistake worth
remembering.

**The console reader lost input.** `Utils` created a new `BufferedReader` over
`System.in` on every single read. Each one filled its internal buffer with a
whole block of input and then threw the remainder away when the object went out
of scope, so up to 8 KB of input vanished per read. Typing at a keyboard hid
the problem, because a terminal hands over one line at a time; redirecting
input from a file made the game unplayable. There is now one shared reader.

**End of input meant an infinite loop.** At the end of the stream `readLine`
returns `null`, `Integer.parseInt(null)` throws `NumberFormatException`, and
`leerEntero` treated that as a recoverable typo and asked again — forever.
A minute of running produced 19 million lines of "please enter a number".
There is now an `EntradaCerradaException`, caught in `main`, and the game saves
and exits cleanly.

**Buying one unit put it on 828 squares.** `comprarUnidad` had its placement
condition inverted: `colocarUnidadSiLibre` returns `true` when it has placed
the unit, and the code kept drawing new random squares in exactly that case. On
the 300×300 map a single purchase scattered references to the same unit across
hundreds of squares, every one of which could be attacked.

**`hashCode` overflowed the stack.** `Unidad`, `Jugador` and `Escenario` each
included a reference that closed a cycle — unit → player → army → unit — so as
soon as a unit had an owner, `hashCode()` and `equals()` called themselves
until the stack ran out. Putting any of these objects in a `HashMap` or
`HashSet` crashed the program.

**Several `equals` compared objects with `==`.** The board in `Escenario`, the
unit list in `Ejercito`, the players in `Partida` and `CentroDeMandos`: they
tested whether two objects were the *same* instance, so two equal values were
never equal.

**`Coordenadas` broke the `equals`/`hashCode` contract.** `hashCode` mixed in
the scenario and the squares travelled, which `equals` does not look at, so two
equal coordinates could hash differently and fail to be found in a `HashSet`.

**An off-by-one on the board edge crashed the game.** The board is
`new Unidad[xmax][ymax]`, so the last valid index is `xmax - 1`, but the bounds
check was `> xmax` and let `xmax` through. A unit moving to the edge of the map
aborted the game with an `ArrayIndexOutOfBoundsException`. In
`Unidad.atacarUnidad` the "you cannot attack off the map" warning also failed
to stop the iteration, so the attack could go ahead anyway.

**Damage was over-counted, and destroying a unit threw.**
`Jugador.recibirDano` zeroed the defence energy *before* computing the surplus,
so it divided the attacker's full power among the crew instead of only the part
that got through the armour. It also removed dead crew inside a for-each over
their own list — a `ConcurrentModificationException` the moment a unit was
destroyed — and divided by the crew count without checking it was not zero.

**`maxmov` was a `String`.** It held the text "Casilla aleatoria" for the
Banshee, and the getter ran `Integer.parseInt` over it, so asking a Banshee for
its movement range threw `NumberFormatException`. It is an `int` now, and the
label is produced only when the value is displayed.

**No `serialVersionUID` anywhere.** Any edit to a serializable class would have
made every existing saved game unreadable with an `InvalidClassException`. All
32 of them now pin it.

**The purchase flow was duplicated six times.** `Menu.compraUnidad` repeated the
same ~50-line block once per buyable unit per faction, with the crew purchase
nested inside each copy. Unifying it cut `Menu.java` from 649 to 500 lines and
exposed three inconsistencies between the copies: the RDA branch returned inside
the loop and so always bought exactly one unit however many you asked for; both
branches nulled out a unit whose crew could not be completed and then
immediately reassigned the return value to it, so the command centre logged the
purchase as a success and the half-crewed unit went on occupying its square; and
running out of points mid-crew aborted the whole purchase with the unit already
paid for.

**Smaller things.** Empty `catch` blocks in `Io` swallowed I/O errors, and a
missing save file on first run was indistinguishable from a real failure.
`setX`/`setY` assigned before validating, so a rejected coordinate was stored
anyway. A `Coordenadas` constructor took a fifth `int` parameter that existed
only to disambiguate an overload. Non-ASCII identifiers (`checkMapaAñadir`,
`recibirdaño`) were renamed. The 36 files still carried the NetBeans template
header, the author tag read `artur`, every class-level Javadoc was empty, and
the entry point held a personal note about the coursework deadline. The project
now compiles clean under `-Xlint:all`; it used to emit 36 warnings.

---

## License

[MIT](LICENSE) © Arturo Fierro Pardo

Avatar and its setting are the property of 20th Century Studios. This is a
non-commercial student project with no affiliation to the rights holders; the
licence covers the source code only.
