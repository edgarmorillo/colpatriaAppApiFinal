# colpatriaAppApiFinal

El proyecto ColpatriaAppApifinal

Tiene una arquitectura MVVM y utiliza tecnologia Jetpack compose para las vistas y el lenguaje de programacion kotlin

la estructura de directorios esta formada por
- model : modelo donde se encuentran las estructuras de los response de cada api.
- remoter : todos los archivos que configuran el entorno de las api
- view : Contiene todas las vistas que se usan en la app
- viewmodel : Contiene el archivo de navegación que permite configurar las pantallas y su flujo y por ultimo, el archivo viewmodel que permite configurar todas las funciones que usan las vistas (como obtener datos del usuario y manejar las respuestas de las apis)
- res (recursos ) : contiene los archivos como los multimedia (imágenes e iconos en el drawable) y los strings que se usan en todo la app

  nota : tener en cuenta que se debe actualizar el android studio y las dependencias que el mismo android studio recomiende.

  con este mismo proyecto se adjunta un video de demostracion y un apk de la aplicacion.

  otra nota : al momento de desarrollar este proyecto una de las APIs empezo a no estar habilitada por temas de que la verison basica del servicio habia llegado a su limite de respuestas por mes. por eso suministro un comprimido adicional que tiene codigo quemado para demostrar la logica de la aplicacion.

  por eso encontraran en los entregables de esta prueba lo siguiente:
  - un archivo coprimido llamda "prueba_final.zip" que tiene el codigo completo de la aplicacion con todas las APis reales
  - un archivo coprimido llamda "prueba.zip" que tiene el codigo completo de la aplicacion pero con codigos de Apis Hardcodeados(quemados) por si las Apis no funcionan
  - una carpeta llamda "apk prueba_final" que posee una apk para probar con la implementacion de las apis reales
  - una carpeta llamda "apk prueba" que posee una apk para probar con la implementacion de las apis quemadas
  - un video llamado "videoPrueba.mp4" que demuestra la ejecucion correcta de la app completamente con todos sus criterios de aplicacion
  - una captura del error que me salio por el tema de el vencimiento de el servivio de la api llamdo : "Error de API - plan basico culmino.jpeg"
  - este repositorio que tiene el codigo compoleto real con las apis al igual que el comprimido "prueba_final.zip" , que es una fiuel copia.
