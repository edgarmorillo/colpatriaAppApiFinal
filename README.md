El proyecto ColpatriaAppApifinal

Tiene una arquitectura MVVM y utiliza tecnología Jetpack compose para las vistas y el lenguaje de programación kotlin

la estructura de directorios esta formada por
- modelo : modelo donde se encuentran las estructuras de los response de cada api.
- remoter : todos los archivos que configuran el entorno de las api
- view : Contiene todas las vistas que se usan en la app
- viewmodel : Contiene el archivo de navegación que permite configurar las pantallas y su flujo y por ultimo, el archivo viewmodel que permite configurar todas las funciones que usan las vistas (como obtener datos del usuario y manejar las respuestas de las apis)
- res (recursos ) : contiene los archivos como los multimedia (imágenes e iconos en el drawable) y los strings que se usan en todo la app


  Nota : tener en cuenta que se debe actualizar el android studio y las dependencias que el mismo android studio recomiende.

  con este mismo proyecto se adjunta un video de demostración y un apk de la aplicación.

  otra nota : al momento de desarrollar este proyecto una de las APIs empezó a no estar habilitada por temas de que la versión básica del servicio había llegado a su límite de respuestas por mes. por eso suministro un comprimido adicional que tiene código quemado para demostrar la lógica de la aplicación.

  por eso encontrarán en los entregables de esta prueba lo siguiente:

  - un archivo coprimido llamda "prueba_final.zip" que tiene el código completo de la aplicación con todas las APis reales
  - un archivo coprimido llamda "prueba.zip" que tiene el código completo de la aplicación pero con códigos de Apis Hardcodeados(quemados) por si las Apis no funcionan
  - una carpeta llamada "apk prueba_final" que posee una apk para probar con la implementación de las apis reales
  - una carpeta llamada "apk prueba" que posee una apk para probar con la implementación de las apis quemadas
  - un video llamado "videoPrueba.mp4" que demuestra la ejecución correcta de la app completamente con todos sus criterios de aplicación
  - una captura del error que me salio por el tema de el vencimiento del servicio de la api llamado : "Error de API - plan basico culmino.jpeg"
  - este repositorio que tiene el código completo real con las apis al igual que el comprimido "prueba_final.zip" , que es una copia fiel. ( https://github.com/edgarmorillo/colpatriaAppApiFinal
 )

