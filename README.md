## Configuración

1. En Eclipse, ir a `File > Open Projects from File System... >` y elegir el repo clonado.
2. Clic derecho al proyecto, `Build Path > Configure Build Path...`.
3. En la pestaña `Libraries`, eliminar el jar de Classpath y agregarlo a Modulepath (el jar está dentro de la carpeta /lib).
4. En la pestaña `Source`, doble clic en `Native library location` y elegir la carpeta /lib.
5. En Eclipse, ir a `Run > Run Configurations...`.
6. En la pestaña `Main`, elegir la clase ExpressionApp como la clase Main.
7. En la pestaña `Arguments`, en `Program arguments`, elegir file_prompt como variable.

Eso es todo, ahora cuando corras el programa te va a pedir un archivo de texto (en src/tests hay uno).
