Buenas,

Cuando se importa el Nuestras_Boletas.postman_collection.json se crear de manera random '?' al final de algunas urls, este simbolo puede hacer que las pruebas test no funcionen. Por lo tanto, le invito a seguir los siguientes pasos para que todos las pruebas funcionen de manera correcta.

1. Al descargar el proyecto hacer clean and build.
2. Correr el nboletas-web.
3. Correr el  archivo nboletas.sql que esta en el archivo sql.
4. importar el archivo Nuestras_Boletas.postman_collection.json.
5. Correr los test.
(Si llega a salir algun error siga los siguientes pasos)
6. Mirar donde se encuentrar el error e ir a la carpeta y borrar el simbolo '?'.
Repetir los paso 5 y 6 hasta que salga los test en verde, gracias.

Muchas gracias