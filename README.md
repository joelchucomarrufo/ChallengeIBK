**Challengeibk**

Ejemplo práctico (App Banca Móvil)

Contenido

[TOC]

## Funcionalidades Realizadas

#### Login
-----
- Se implemento el login mediante usuario y contraseña, consumiento un servicio api-Rest mockeado.
- Simula la carga de 3 segundos al presionar INGRESAR.
- En caso de no coincidir el usuario y password, se mostrará un mensaje.
- Se inicia el tiempo de sesión de 2 minutos.

#### Home
-----
- Al iniciar se consume un servicio que primero nos traerá un listado de 2 cuentas.
- Al realizar un push to refresh, este consume otro servicio que solo me traerá una cuenta para añadirla al listado.
- En caso haya un error al obtener el listado, este mostrará un mensaje de error dando la opcion de reintentar.

---------
## Funcionalidades Faltantes

#### Home
-----
- Mostrar un card de lista vacia al actualizar el listado.

#### Detalle
-----
- Todo.

---------

## Librerias usadas

- Retrofit.
- LiveData.
- Navigation Component.
- Dagger Hilt.
- Coroutine.
- Material.
- Flow.
- Broadcast Receiver.


