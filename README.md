# Cmd E-commerce Java

***


## Descripción general
![release](https://img.shields.io/github/release/FacuConci21/cmd-ecommerce-java.svg) ![is-open](https://img.shields.io/github/issues-pr/FacuConci21/cmd-ecommerce-java.svg) ![is-close](https://img.shields.io/github/issues-pr-closed/FacuConci21/cmd-ecommerce-java.svg) ![watchers](https://img.shields.io/github/watchers/FacuConci21/cmd-ecommerce-java.svg) ![stars](https://img.shields.io/github/stars/FacuConci21/cmd-ecommerce-java.svg) ![downloads](https://img.shields.io/github/downloads/FacuConci21/cmd-ecommerce-java/total.svg)

Aplicación ecommerce de consola en lenguaje Java. El propósito de esta aplicación es practicar lenguajes, integrar conceptos y practicar el trabajo en equipo en Github.

![release](https://img.shields.io/github/release/FacuConci21/cmd-ecommerce-java.svg) ![is-open](https://img.shields.io/github/issues-pr/FacuConci21/cmd-ecommerce-java.svg) ![is-close](https://img.shields.io/github/issues-pr-closed/FacuConci21/cmd-ecommerce-java.svg) ![watchers](https://img.shields.io/github/watchers/FacuConci21/cmd-ecommerce-java.svg) ![stars](https://img.shields.io/github/stars/FacuConci21/cmd-ecommerce-java.svg) ![downloads](https://img.shields.io/github/downloads/FacuConci21/cmd-ecommerce-java/total.svg)

## Instalación

Clonar el repositorio:
```batch
git clone https://github.com/FacuConci21/cmd-ecommerce-java.git
```
Una vez clonado el repositorio, dirigirse al IDE y abrir nuestro proyecto, hacer build y ejecutar Main.
Si lo que se quiere hacer son pruebas en el backend, se deben ejecutar haciendo uso de los controladores correspondientes en BackendTester.

#### Requisitos
- Utilizar Java 8
- Maven 3.6.3

## Descripción del proyecto
Las funcionalidades principales que te permite realizar esta aplicación son:
- Añadir un producto
- Modificar un producto
- Eliminar un producto
- Listar los productos añadidos




Al iniciar la aplicación podrás ver un menú como este:

![image](https://raw.githubusercontent.com/FacuConci21/cmd-ecommerce-java/develop/resources/images/menuprincipal.png)

### Añadir un producto

Si seleccionás la opción  `Alta de producto ` verás las opciones correspondientes y deberás ingresar los datos solicitados:


![image](https://raw.githubusercontent.com/FacuConci21/cmd-ecommerce-java/develop/resources/images/altaprod.png)


Si querés escribir más de una palabra en los campos "Nombre" y "Descripción", deberás hacerlo separado por guiones (pequeño bug).


### Modificar un producto

Al seleccionar la opción  `Modificación de producto ` verás un listado de los mismos, del cual deberás seleccionar uno (ingresando el número que sale a su izquierda) e ingresar los datos solicitados:

![image](https://raw.githubusercontent.com/FacuConci21/cmd-ecommerce-java/develop/resources/images/modifprod1.png)

![image](https://raw.githubusercontent.com/FacuConci21/cmd-ecommerce-java/develop/resources/images/modifprod2.png)


Si no hay productos dados de alta y querés modificar uno, te aparecerá un mensaje como este:

![image](https://raw.githubusercontent.com/FacuConci21/cmd-ecommerce-java/develop/resources/images/modifsinprod.png)



### Eliminar un producto

Para eliminar un producto, deberás seleccionar la opción  `Baja de producto ` y verás un listado de los mismos. Deberás seleccionar uno (ingresando el número que aparece a su izquierda):

![image](https://raw.githubusercontent.com/FacuConci21/cmd-ecommerce-java/develop/resources/images/bajadeprod.png)


Si no hay productos dados de alta y querés eliminar uno te aparecerá un mensaje como este:

![image](https://raw.githubusercontent.com/FacuConci21/cmd-ecommerce-java/develop/resources/images/bajasinprod.png)



### Listar los productos añadidos

Para ver todos los productos dados de alta, deberás seleccionar la opción  `Lista de productos ` y verás algo como esto:

![image](https://raw.githubusercontent.com/FacuConci21/cmd-ecommerce-java/develop/resources/images/listaconproductos.png)


Si no hay productos en la lista te saldrá un mensaje como este:

![image](https://raw.githubusercontent.com/FacuConci21/cmd-ecommerce-java/develop/resources/images/listavacia.png)


### Salir de la aplicación

Para salir de la aplicación deberás ingresar el número 0 o el número 5.

![image](https://raw.githubusercontent.com/FacuConci21/cmd-ecommerce-java/develop/resources/images/salir.png)



### Updates
#### Integración de maven

Se migró el proyecto de tipo java simple a un proyecto de tipo maven con el objetivo de administrar dependencias, proporcionar una mejor arquitectura y administrar el versionado del proyecto.


#### Backend Integrado

En esta actualización integramos un backend al frontend existente para llevar a cabo las tareas de la aplicación mas eficientemente.
Ahora cuenta con la característica de persistencia de datos, los usuarios podran registrar productos y luego consultarlos y visualizarlos después de haber cerrado la aplicación.
La persistencia de datos fue realizada en archivos .json, los cuales son gestionados a través de la dependencia "json-simple" y se encuentran guardados en el proyecto de manera local en los recursos del mismo más específicamente en la carpeta Server/products-cluster


#### Testing Integrado

Se implementó en la capa de test todas las pruebas necesarias a realizarse sobre el backend, en un paso previo a implementar en nuestro backend en el frontend.


#### Otros

- Se realizaron mejoras en la UI/UX, implementando interfaces que se encargan del manejo de los resultados lanzados por consola.
- Se implementó una interfaz para el manejo de rutas.
- Se mejoró la gestión de opciones en el menú en la capa del frontend.
- Se buscó mejorar el funcionamiento del programa y asegurar la escalabilidad del proyecto haciendo uso de las buenas prácticas, código reutilizable y limpio.


### Contactános

<div style="display: flex; justify-content: space-evenly; width: 350px;">
      <a href="https://www.linkedin.com/in/matisantillan11/">
        <img
          src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"
          alt="LinkedIn"
        />
      </a>
<a href="https://www.linkedin.com/in/facundo-ignacio-conci-caceres/">
        <img
          src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"
          alt="LinkedIn"
        />
      </a>
<a href="https://www.linkedin.com/in/matias-nahuel-murua-martinez-02b996168/">
        <img
          src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"
          alt="LinkedIn"
        />
      </a>
    </div>

<div style="display: flex; justify-content: space-evenly; width: 550px;">
      <a href="mailto:matias1.santillan@gmail.com">
        <img
          src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white"
          alt="Gmail"
        />
      </a>
      <a href="mailto:matias1.santillan@gmail.com">
        <img
          src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white"
          alt="Gmail"
        />
      </a>
      <a href="mailto:facui.conci97@gmail.com">
        <img
          src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white"
          alt="Gmail"
        />
      </a>
      <a href="mailto:nicole.lizarraga182@gmail.com">
          <img
            src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white"
            alt="Gmail"
          />
        </a>
    </div>
