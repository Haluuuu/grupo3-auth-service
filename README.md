# Clean/Hexagonal Architecture Spring Boot Template
Arquitectura para Spring Boot usando **Arquitectura 
Limpia**
y **Arquitectura Hexagonal**. Esta Arquitectura permie
y facilita hacer pruebas unitarias, además que hace más
eficiente la escalabilidad y mantenimiento del código.

Esta arquitectura utiliza el principio de Inversión de 
dependencias. Por medio de interfaces se pueden
desacoplas y acoplar dependencias si en un futuro
se cambia la tecnología.

## Estructura de carpetas
- ``domain``: contiene la lógica negocio pura, no depende
    de librerías externas
  - ``models``: modelos de datos base del sistema
    (usuarios, permisos, roles, etc.)
  - ``services``: servicios base del sistema, utiliza
  únicamente código base (funciones
  de encriptado, validación de contraseña, etc.)
  - ``exceptions``: clases de excepciones para utilizar
  en el sistema e informar un error y mostrar detalles
    (usuario no encontrado, contraseña incorrecta,
  permisos insuficientes, etc.)


- ``application``: contiene la lógica de aplicación, en
esta capa se encuentran los adaptadores y servicios del
sistema
  - ``port``:
    - ``in``: interfaces que actúan como
    puertos de entrada y deben implementarse(iniciar sesión,
    buscar usuario, listar usuarios, etc.)
    - ``out``: interfaces que actúan como puertos de salida
    y deben implementarse (repositorio de usuario, websockets, JWT, etc)
  - ``services``: modulos de alto nivel que conectan los puertos de entrada
  y salida


- ``infrastructure``: en esta capa se contienen las dependencias externas y las
implementaciones de los puertos de entrada y de salida
  - ``entity``: modelos de datos que fungen como tablas en la base de datos
  - ``repository``: repositorios de las entidades
  - ``config``: Beans de configuraciones para satisfacer la inyección de 
  dependencias


## Glosario
- **puertos**: representan las entradas y salidas en el sistema, representan
una aastracción de la lógica de la aplicación y establecen las reglas de cuales
van a ser las entradas y las salidas


- **servicios**: en la arquitectura hexagonal y arquitectura limpia, los 
servicios son la implementación de los puertos de entrada junto con la
inyección de dependencias de uno o más puertos de salida.


- **inversión de dependencias**: es uno de los principios SOLID, la inversión de
dependencias establece que los modelos de más bajo nivel como los servicios
no deben depender de dependencias de alto nivel.


- **módulos de bajo nivel**: pueden ser interfaces, clases abstractas o
servicios que utilizan dependencias inyectadas.
No contienen código externo, solo lógica abstraída de la aplicación


- **módulos de alto nivel**: implementan interfaces y utilizan código
externo de dependencias externas y librerías. (repositorios, utilidades
de autenticación con JWT, funciones de mensajería, etc.)