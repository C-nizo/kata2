# Kata 2 — Entrada, salida y carga de datos

**Ingeniería del Software 2 — ULPGC**

## Objetivo

El objetivo de esta kata es practicar la lectura y escritura de datos mediante `InputStream` y `OutputStream` en Java, separando las responsabilidades de carga, procesamiento y salida.

Se ha desarrollado una aplicación que lee libros en formato CSV, representa cada uno mediante un objeto y genera un resumen con la cantidad de libros por género.

El diseño permite utilizar distintos orígenes y destinos de datos sin modificar la lógica de procesamiento.

## Tecnologías y dependencias

- Java 27 (OpenJDK).
- IntelliJ IDEA.
- Maven.
- Git y GitHub.

El proyecto utiliza únicamente la biblioteca estándar de Java, sin dependencias externas.

## Arquitectura

El proyecto sigue una arquitectura hexagonal, separando la lógica de la aplicación de los mecanismos de entrada y salida.

**`architecture`**

- `Book`: representa un libro mediante su título y género.
- `GenreCounter`: calcula las frecuencias por género utilizando un `TreeMap`.
- `BookLoader`: puerto que define la carga de libros mediante un `InputStream`.
- `GenreSummaryWriter`: puerto que define la escritura del resumen mediante un `OutputStream`.
- `GenerateGenreSummaryOperation`: coordina la carga, el procesamiento y la escritura.

**`infrastructure`**

- `CsvBookLoader`: lee y valida los datos CSV, creando objetos `Book`.
- `TextGenreSummaryWriter`: escribe el resumen en formato de texto.
- `Main`: configura los adaptadores y los flujos de entrada y salida.

El dataset utilizado se encuentra en `src/main/resources/libros.csv`.

## Repeticiones realizadas

La aplicación se ha reconstruido tres veces, conservando la misma arquitectura e introduciendo pequeñas variaciones en los orígenes y destinos de datos.

| Repetición | Orígenes de entrada | Destinos de salida |
|---|---|---|
| Primera | Fichero y memoria | Consola y fichero |
| Segunda | Fichero y memoria | Consola y memoria |
| Tercera | Fichero y recurso del classpath | Consola y memoria |

Las variaciones se han centrado en el uso de distintos mecanismos de entrada y salida, manteniendo las responsabilidades de carga, procesamiento y escritura.

El historial de commits permite recuperar cada repetición.

## Compilación y ejecución

Para ejecutar el proyecto desde IntelliJ IDEA:

1. Abrir el proyecto y cargar su configuración Maven.
2. Configurar JDK 27 como SDK del proyecto.
3. Compilar mediante **Build → Build Project**.
4. Ejecutar `software.ulpgc.katas.infrastructure.Main`.

También puede compilarse y ejecutarse desde la raíz del proyecto mediante:

```bash
mvn clean compile
java -cp target/classes software.ulpgc.katas.infrastructure.Main
```

La ejecución debe realizarse desde la raíz del proyecto porque una de las entradas utiliza una ruta relativa al fichero CSV.

La versión final demuestra dos combinaciones:

- **Fichero → consola:** lectura mediante `FileInputStream` y escritura en `System.out`.
- **Recurso del classpath → memoria:** lectura mediante `getResourceAsStream()` y escritura en `ByteArrayOutputStream`. El resultado almacenado se recupera posteriormente y se muestra por consola.

### Resultado esperado

Ambas combinaciones producen el mismo resumen:

```text
Ciencia ficción: 2
Distopía: 2
Fantasía: 1
Ficción: 1
```

## Verificación

Se han comprobado los siguientes casos:

- Lectura correcta del CSV y generación del resumen.
- Utilización de distintos orígenes de entrada.
- Escritura en consola, fichero y memoria.
- Gestión de errores ante archivos inexistentes o vacíos.
- Detección de cabeceras incorrectas y filas malformadas.
- Comprobación de que el orden de las filas no altera el resultado.
- Obtención del mismo resumen cuando distintos orígenes contienen los mismos datos.

## Clonación y comprobación

Se clonó el repositorio desde GitHub en una carpeta independiente y se comprobó que el proyecto compila y se ejecuta correctamente, sin depender de la ubicación original.

Para reproducir la comprobación:

1. Clonar `https://github.com/C-nizo/kata2` desde IntelliJ IDEA.
2. Abrir el proyecto clonado.
3. Configurar JDK 27 y cargar Maven.
4. Compilar el proyecto.
5. Ejecutar `Main` y comprobar el resumen generado.

Este procedimiento permite verificar el funcionamiento del proyecto sin depender de su ubicación original.

## Flujo Git y GitHub

El proyecto utiliza dos ramas:

- `develop`: rama habitual de desarrollo.
- `master`: contiene la versión evaluable.

Cada repetición se implementó en `develop` y, una vez completada y verificada, se integró en `master`.

Entre repeticiones se realizaron commits de preparación para reconstruir el código desde cero.

El historial conserva las tres implementaciones y permite consultar su evolución.

## Vídeo explicativo

Pendiente de incorporación.