# Avatar: Battle for Freedom

[![build](https://github.com/ArturoFierroPardo/avatar-battle-for-freedom/actions/workflows/build.yml/badge.svg)](https://github.com/ArturoFierroPardo/avatar-battle-for-freedom/actions/workflows/build.yml)
[![Java 17](https://img.shields.io/badge/Java-17-orange)](https://openjdk.org/projects/jdk/17/)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

[English](README.en.md) · **Español**

Un juego de estrategia por turnos para dos jugadores, jugado en la consola y
ambientado en Pandora. Cada jugador manda una facción — la **RDA** humana o los
**Na'vi** —, compra unidades, las tripula, las despliega en una rejilla y lucha
hasta que uno de los dos ejércitos queda aniquilado.

Escrito en Java como práctica de la asignatura de Programación Orientada a
Objetos del grado en Ingeniería de Sistemas de Telecomunicación de la
Universidad CEU San Pablo. La entrega original se conserva en el historial del
repositorio; la versión actual añade compilación con Maven, una batería de
pruebas y los arreglos de los defectos que se listan al final de este fichero.

---

## Contenido

- [Cómo se juega](#cómo-se-juega)
- [Unidades y tripulantes](#unidades-y-tripulantes)
- [Escenarios](#escenarios)
- [Cómo ejecutarlo](#cómo-ejecutarlo)
- [Diseño](#diseño)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Pruebas](#pruebas)
- [Revisión de código: defectos encontrados y corregidos](#revisión-de-código-defectos-encontrados-y-corregidos)
- [Licencia](#licencia)

---

## Cómo se juega

Los jugadores alternan turnos. En tu turno eliges una acción del menú:

| Opción | Acción |
|---|---|
| 1 | Comprar unidades, y los tripulantes que cada una necesita para operar |
| 2 | Mover una unidad |
| 3 | Atacar a una unidad enemiga |
| 4 | Abrir tu centro de mandos |
| 5 | Finalizar el turno |
| 6 | Guardar la partida y salir |

Todo cuesta **puntos**. Empiezas con una asignación que depende del escenario,
la gastas en unidades y tripulantes, y ganas más destruyendo unidades enemigas
y matando a sus tripulantes. Una unidad sin la tripulación completa no puede
moverse ni atacar.

El combate se resuelve primero contra la **energía de defensa** de la unidad.
Una vez agotada, el daño sobrante se reparte a partes iguales entre los
tripulantes; los que se quedan sin vida mueren, y una unidad que pierde a toda
su tripulación queda destruida y se retira del tablero. El fuego amigo es
posible, y el registro del centro de mandos lo indica cuando ocurre.

Moverse y atacar también consumen **energía de movimiento** y **energía de
ataque**, que no se regeneran: una unidad tiene una vida útil finita por mucho
daño que sea capaz de aguantar.

El **centro de mandos** es el registro privado de cada jugador: toda acción de
cualquiera de los dos bandos queda anotada con su hora, pero ves más detalle de
tus propios movimientos que de los del rival — a ti te dicen que el enemigo ha
movido una unidad, no a dónde.

Las partidas se pueden guardar y retomar; varias conviven en `partidas.dat` y se
ofrecen como lista al arrancar.

### Una sesión, abreviada

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

## Unidades y tripulantes

Cada facción tiene su propio catálogo. Las máquinas las tripulan humanos, a las
bestias las montan los Na'vi.

### RDA — máquinas

| Unidad | Coste | Defensa | Ataque | Potencia | Alcance ataque | Movimiento | Tripulantes |
|---|---|---|---|---|---|---|---|
| AT-99 Escorpion | 15000 | 7500 | 7500 | 500 | 10 | 20 | 4 |
| Aerospatiale SA-2 Samson | 2500 | 1000 | 1000 | 80 | 5 | 10 | 2 |
| Mitsubishi MK-6 | 500 | 0 | 1000 | 20 | 1 | 2 | 1 |

### Na'vi — bestias

| Unidad | Coste | Defensa | Ataque | Potencia | Alcance ataque | Movimiento | Tripulantes |
|---|---|---|---|---|---|---|---|
| Thanator | 8000 | 9000 | 9000 | 600 | 5 | 50 | 3 |
| Banshee de Montaña | 2000 | 3500 | 8000 | 300 | 10 | todo el mapa | 2 |
| Titanothere Cabezamartillo | 1000 | 11000 | 11000 | 300 | 1 | 2 | 1 |

La Banshee es la excepción: no tiene alcance de movimiento y salta a una casilla
libre cualquiera del mapa, a un coste fijo de energía. El MK-6 es el otro caso
especial — no tiene energía de defensa, así que cada impacto va directo a su
piloto.

### Tripulantes

| Tripulante | Facción | Cargo | Coste | Vida | Puntos al derrotarlo |
|---|---|---|---|---|---|
| Comandante de SecOps | RDA | Oficial | 100 | 150 | 400 |
| Artillero | RDA | Guerrero | 50 | 150 | 100 |
| Omaticaya | Na'vi | Oficial | 100 | 300 | 400 |
| Tipani | Na'vi | Guerrero | 50 | 300 | 250 |

---

## Escenarios

| Escenario | Rejilla | Puntos iniciales |
|---|---|---|
| Árbol de las Almas | 100 × 100 | 5 000 |
| Jardines Colgantes | 200 × 200 | 20 000 |
| Árbol Madre | 300 × 300 | 45 000 |

---

## Cómo ejecutarlo

Necesitas un **JDK 17 o posterior** y Maven.

```bash
git clone https://github.com/ArturoFierroPardo/avatar-battle-for-freedom.git
cd avatar-battle-for-freedom
mvn package
java -jar target/avatar-battle-for-freedom.jar
```

Dos de los mensajes del juego llevan caracteres acentuados, así que en una
consola que no esté en UTF-8 — típicamente el símbolo del sistema de Windows —
conviene ejecutar antes `chcp 65001` para que se vean bien.

Las partidas guardadas se escriben en `partidas.dat`, en el directorio de
trabajo.

En un IDE, abre la carpeta como proyecto Maven: NetBeans, IntelliJ IDEA y
Eclipse leen el `pom.xml` directamente, sin configurar nada más.

Otros comandos útiles:

```bash
mvn test              # ejecuta la batería de pruebas
mvn javadoc:javadoc   # genera el Javadoc dentro de target/
```

---

## Diseño

El modelo es una jerarquía de herencia con dos raíces abstractas. `Unidad`
recoge todo lo que tienen en común las piezas jugables — coste, las tres
reservas de energía, potencia, alcance y tripulación — y deja que cada unidad
concreta aporte sus propios números. Las unidades que gastan energía de
movimiento a un coste fijo en lugar de por casilla sobrescriben `restarEnergia`,
y la Banshee sobrescribe `moverUnidad` por completo porque ignora el alcance de
movimiento.

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

Algunas decisiones que merece la pena señalar:

- **El tablero es un `Unidad[][]`.** Eso guarda la ocupación y la posición dos
  veces — en la rejilla y en las `Coordenadas` de cada unidad — y hay que
  mantenerlas sincronizadas a mano. `colocarUnidadSiLibre` es el único punto de
  entrada que escribe en la rejilla, y es lo que hace eso manejable.
- **Tres reservas de energía separadas** (defensa, ataque y movimiento) es lo
  que da carácter a cada unidad: el Titanothere es un muro que apenas se mueve,
  la Banshee es frágil pero recorre todo el mapa.
- **Todo es `Serializable`**, de modo que una partida en curso se escribe entera
  en disco con un solo `writeObject`, incluidos los registros del centro de
  mandos. Cada una de esas clases fija su `serialVersionUID` para que modificar
  el código no invalide las partidas ya guardadas.
- **Excepciones comprobadas propias** (`FueradeMapaException`,
  `PuntosdeCompraInsuficientesException`, `SinEnergiaAtaqueException`, …) llevan
  las violaciones de las reglas hasta el menú, que es la única capa que sabe
  cómo explicárselas al jugador.
- **Las referencias hacia atrás quedan fuera de `equals` y `hashCode`.** Una
  unidad conoce a su dueño, el dueño conoce su ejército y el ejército conoce sus
  unidades, así que comparar una unidad por su dueño entraría en recursión
  infinita.

---

## Estructura del proyecto

```
src/main/java/
├── main/                      punto de entrada
├── interfaz/
│   ├── partida/               bucle de turnos y condiciones de victoria
│   ├── menu/                  el menú del turno y los mercados
│   └── centrodemandos/        registro de acciones por jugador
├── modelos/
│   ├── jugador/               puntos, ejército, acciones
│   ├── ejercito/
│   ├── equipo/                RDA / NAVI
│   ├── escenario/             escenarios y el tablero
│   ├── coordenadas/           casillas, límites y desplazamiento
│   ├── unidad/                Unidad → Maquina | Bestia
│   ├── ser/                   Ser → Humano | Navi
│   └── excepciones/           violaciones de las reglas
├── io/                        guardado y carga
└── utils/                     lectura por consola

src/test/java/                 77 pruebas con JUnit 5
```

---

## Pruebas

77 pruebas con JUnit 5 sobre la lógica de dominio: límites del tablero,
escenarios, unidades, tripulantes, el ejército, las acciones del jugador y la
persistencia.

```bash
mvn test
```

Son pruebas de regresión tanto como pruebas unitarias: cada defecto de la lista
de abajo tiene una prueba que lo fija, con un comentario que explica qué fallaba
antes. La batería se comprobó por mutación — al reintroducir a mano los bugs
originales que arreglan, falla; al deshacerlo, vuelve a pasar entera.

---

## Revisión de código: defectos encontrados y corregidos

Revisar la entrega original antes de publicarla sacó a la luz lo siguiente. Se
listan porque los arreglos son el grueso de la diferencia respecto a la versión
de la práctica, y porque un par de ellos son de la clase de error que conviene
recordar.

**La lectura por consola perdía datos.** `Utils` creaba un `BufferedReader`
nuevo sobre `System.in` en cada lectura. Cada uno llenaba su buffer interno con
un bloque entero de la entrada y descartaba el resto al quedar el objeto fuera
de uso, así que se perdían hasta 8 KB por lectura. Teclear a mano ocultaba el
problema, porque la consola entrega los datos línea a línea; redirigir la
entrada desde un fichero hacía el juego inusable. Ahora el lector es único y
compartido.

**El fin de la entrada provocaba un bucle infinito.** Al final del flujo
`readLine` devuelve `null`, `Integer.parseInt(null)` lanza
`NumberFormatException`, y `leerEntero` lo trataba como una errata corregible y
volvía a preguntar, indefinidamente. Un minuto de ejecución generaba 19 millones
de líneas de «introduzca un número». Ahora existe `EntradaCerradaException`, se
captura en `main`, y el juego guarda y termina de forma ordenada.

**Comprar una unidad la colocaba en 828 casillas.** `comprarUnidad` tenía la
condición de colocación invertida: `colocarUnidadSiLibre` devuelve `true` cuando
ha colocado la unidad, y el código seguía sorteando casillas precisamente en ese
caso. En el mapa de 300×300, una sola compra esparcía referencias a la misma
unidad por centenares de casillas, y se la podía atacar en todas ellas.

**`hashCode` desbordaba la pila.** `Unidad`, `Jugador` y `Escenario` incluían
cada uno una referencia que cerraba un ciclo — unidad → jugador → ejército →
unidad —, así que en cuanto una unidad tenía dueño, `hashCode()` y `equals()` se
llamaban a sí mismos hasta agotar la pila. Meter cualquiera de esos objetos en
un `HashMap` o un `HashSet` abortaba el programa.

**Varios `equals` comparaban objetos con `==`.** El tablero en `Escenario`, la
lista de unidades en `Ejercito`, los jugadores en `Partida` y `CentroDeMandos`:
comprobaban si dos objetos eran *el mismo*, de modo que dos valores iguales
nunca resultaban iguales.

**`Coordenadas` rompía el contrato entre `equals` y `hashCode`.** `hashCode`
mezclaba el escenario y las casillas recorridas, que `equals` no mira, así que
dos coordenadas iguales podían dar códigos distintos y no encontrarse dentro de
un `HashSet`.

**Un off-by-one en el borde del tablero abortaba la partida.** El tablero es
`new Unidad[xmax][ymax]`, luego el último índice válido es `xmax - 1`, pero la
comprobación de límites era `> xmax` y dejaba pasar `xmax`. Una unidad que se
moviera al borde del mapa abortaba la partida con un
`ArrayIndexOutOfBoundsException`. En `Unidad.atacarUnidad`, el aviso de «no
puede atacar fuera del mapa» además no cortaba la iteración, así que el ataque
podía ejecutarse igualmente.

**El daño se contaba de más, y destruir una unidad lanzaba una excepción.**
`Jugador.recibirDano` ponía la energía de defensa a cero *antes* de calcular el
sobrante, así que repartía entre la tripulación la potencia entera del atacante
en vez de solo la parte que atravesaba la armadura. También eliminaba a los
tripulantes muertos dentro de un for-each sobre su propia lista — un
`ConcurrentModificationException` en el momento en que se destruía una unidad — y
dividía entre el número de tripulantes sin comprobar que no fuera cero.

**`maxmov` era un `String`.** Contenía el texto «Casilla aleatoria» para la
Banshee, y el getter le aplicaba `Integer.parseInt`, así que preguntar a una
Banshee por su alcance de movimiento lanzaba `NumberFormatException`. Ahora es un
`int`, y la etiqueta se genera solo al mostrar el valor.

**No había ningún `serialVersionUID`.** Cualquier modificación de una clase
serializable habría dejado ilegibles todas las partidas guardadas, con un
`InvalidClassException`. Las 32 lo fijan ahora.

**El flujo de compra estaba duplicado seis veces.** `Menu.compraUnidad` repetía
el mismo bloque de unas 50 líneas una vez por cada unidad comprable de cada
facción, con la compra de tripulantes anidada dentro de cada copia. Unificarlo
bajó `Menu.java` de 649 a 500 líneas y dejó a la vista tres incoherencias entre
las copias: la rama de la RDA hacía un `return` dentro del bucle y por tanto
compraba siempre exactamente una unidad por muchas que pidieras; las dos ramas
anulaban la unidad cuya tripulación no se podía completar y acto seguido
reasignaban a ella el valor de retorno, con lo que el centro de mandos
registraba la compra como exitosa y la unidad a medio tripular seguía ocupando
su casilla; y quedarse sin puntos a mitad de la tripulación abortaba toda la
compra con la unidad ya pagada.

**Cosas menores.** Bloques `catch` vacíos en `Io` se tragaban los errores de
entrada y salida, y la ausencia del fichero de partidas en la primera ejecución
era indistinguible de un fallo real. `setX` y `setY` asignaban antes de validar,
así que una coordenada rechazada se guardaba igualmente. Un constructor de
`Coordenadas` recibía un quinto parámetro `int` que existía solo para distinguir
una sobrecarga. Se renombraron los identificadores con caracteres no ASCII
(`checkMapaAñadir`, `recibirdaño`). Los 36 ficheros seguían llevando la cabecera
de la plantilla de NetBeans, la etiqueta de autor decía `artur`, todos los
Javadoc de clase estaban vacíos, y el punto de entrada guardaba una nota
personal sobre la fecha de entrega de la práctica. El proyecto compila ahora
limpio con `-Xlint:all`; antes emitía 36 avisos.

---

## Licencia

[MIT](LICENSE) © Arturo Fierro Pardo

Avatar y su universo son propiedad de 20th Century Studios. Esto es un proyecto
de estudiante, sin ánimo de lucro y sin ninguna vinculación con los titulares de
los derechos; la licencia cubre únicamente el código fuente.
