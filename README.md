# Kata 2 — Entrada, salida y carga de datos

**Ingeniería de Software 2 — ULPGC**

## 1. Objetivo

Esta kata tiene como objetivo practicar la lectura y escritura de datos mediante `InputStream` y `OutputStream` en Java, separando las responsabilidades de carga, transformación y salida.

Se ha desarrollado una aplicación que lee un conjunto de libros en formato CSV, transforma cada fila en un objeto de dominio y calcula la frecuencia de libros por género.

El diseño permite utilizar distintos orígenes y destinos de datos sin modificar la lógica de procesamiento.

## 2. Tecnologías y dependencias

- Java 27 (OpenJDK).
- Maven para la gestión y compilación del proyecto.
- IntelliJ IDEA como entorno de desarrollo.
- Biblioteca estándar de Java, sin dependencias externas.

## 3. Estructura del proyecto

El código se encuentra en `src/main/java/software/ulpgc/` y consta de los siguientes componentes:

| Clase | Responsabilidad |
|---|---|
| `Book` | Representa un libro mediante su título y género. |
| `BookLoader` | Define la interfaz de carga basada en `InputStream`. |
| `CsvBookLoader` | Lee el CSV, valida su contenido y crea objetos `Book`. |
| `GenreCounter` | Calcula las frecuencias por género mediante un `TreeMap`. |
| `SummaryWriter` | Escribe el resumen utilizando un `OutputStream`. |
| `Main` | Coordina los componentes y configura los orígenes y destinos de datos. |

El dataset utilizado es `src/main/resources/libros.csv`.

## 4. Compilación y ejecución

Con JDK 27 y Maven disponibles, desde la raíz del proyecto:

**Compilar:**

`mvn clean compile`

**Ejecutar:**

`java -cp target/classes software.ulpgc.Main`

También puede ejecutarse directamente la clase `Main` desde IntelliJ IDEA.

La ejecución debe realizarse desde la raíz del proyecto, ya que una de las entradas utiliza una ruta relativa al fichero CSV.

La versión final demuestra dos combinaciones:

- **Fichero → consola:** lectura mediante `FileInputStream` y escritura en `System.out`.
- **Recurso del classpath → memoria:** lectura mediante `getResourceAsStream()` y escritura en `ByteArrayOutputStream`. El resultado almacenado se recupera posteriormente y se muestra en consola.

**Ejemplo de resumen:**

```text
Ciencia ficción: 2
Distopía: 2
Fantasía: 1
Ficción: 1
```

## 5. Repeticiones realizadas

La kata se ha desarrollado tres veces, reconstruyendo la aplicación desde cero y conservando la misma arquitectura general.

| Repetición | Orígenes de entrada | Destinos de salida |
|---|---|---|
| 1 | Fichero y memoria | Consola y fichero |
| 2 | Fichero y memoria | Consola y memoria |
| 3 | Fichero y recurso del classpath | Consola y memoria |

Las variaciones se han centrado en el uso de distintas implementaciones de entrada y salida, manteniendo las responsabilidades de carga, transformación y escritura.

El historial de commits permite identificar el desarrollo de las repeticiones y su integración progresiva en la rama principal `master`.

## 6. Verificación

Se han realizado verificaciones manuales de los siguientes casos:

- Lectura correcta del CSV y generación del resumen.
- Lectura desde distintos orígenes de entrada.
- Escritura en consola, fichero y memoria.
- Gestión de errores ante un fichero inexistente.
- Detección de un CSV vacío.
- Detección de filas mal formadas.
- Comprobación de que el resultado no cambia al reordenar las filas del dataset.
- Comprobación de que distintos orígenes con los mismos datos producen el mismo resumen.
- Gestión de un recurso inexistente mediante `getResourceAsStream()`.

Las pruebas de errores y consistencia se realizaron durante la primera repetición y se comprobó adicionalmente la gestión de recursos inexistentes en la tercera.

## 7. Vídeo explicativo

[Ver vídeo explicativo de la Kata 2](https://youtu.be/-S89xyyaeaU)

En el vídeo se presenta la arquitectura del programa, el funcionamiento
de los streams, la transformación de los datos, las distintas
combinaciones de entrada y salida y la gestión de errores.