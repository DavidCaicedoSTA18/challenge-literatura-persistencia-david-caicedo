📚 #Catálogo de Libros - LiterAlura
<div align="center">
</div>
📖 Descripción
Catálogo de Libros es una aplicación de consola desarrollada con Spring Boot que permite gestionar un catálogo literario mediante el consumo de la API Gutendex. Los usuarios pueden buscar libros, registrarlos en una base de datos PostgreSQL y realizar diversas consultas sobre los libros y autores almacenados.
Este proyecto forma parte del desafío de Alura LATAM, enfocado en el consumo de APIs REST y la persistencia de datos con Spring Data JPA.
✨ Características

🔍 Búsqueda de libros: Consulta la API Gutendex por título
💾 Persistencia de datos: Almacenamiento en PostgreSQL
📊 Consultas avanzadas: Filtrado por idioma y autores vivos en determinado año
🚫 Control de duplicados: Prevención de registros duplicados
🎯 Interfaz de consola: Menú interactivo y fácil de usar

🛠️ Tecnologías Utilizadas

Java 17
Spring Boot 3.2.0
Spring Data JPA
PostgreSQL
Jackson (para manejo de JSON)
Maven (gestión de dependencias)
API Gutendex (fuente de datos de libros)

📋 Requisitos Previos

JDK 17 o superior
Maven 3.6+
PostgreSQL 12+
IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)

⚙️ Configuración del Proyecto
1. Clonar el Repositorio
bashgit clone [https://github.com/tu-usuario/catalogo-libros.git](https://github.com/DavidCaicedoSTA18/challenge-conversor-monedas-david-caicedo/tree/master)
cd catalogo-libros
2. Configurar Base de Datos
Crear la base de datos en PostgreSQL:
sqlCREATE DATABASE alura_series;
3. Configurar Credenciales
Editar el archivo src/main/resources/application.properties:
propertiesspring.datasource.url=jdbc:postgresql://localhost/alura_series
spring.datasource.username=postgres
spring.datasource.password=tu_contraseña
4. Instalar Dependencias
bashmvn clean install
5. Ejecutar la Aplicación
bashmvn spring-boot:run
📱 Uso de la Aplicación
Al ejecutar la aplicación, se mostrará el siguiente menú:
Elija la opcion a traves de su número:
1- Buscar libro por titulo
2- Listar libros registrados
3- Listar autores registrados
4- Listar autores vivos en un determinado año
5- Listar libros por idioma
0- Salir
Funcionalidades Detalladas
1️⃣ Buscar Libro por Título

Busca libros en la API Gutendex
Registra el libro y su autor en la base de datos
Muestra la información del libro registrado
Previene duplicados

Ejemplo:
Ingrese el nombre del libro que desea buscar
> pride

------------ LIBRO ---------------
Titulo: Pride and Prejudice
Autor: Austen, Jane
Idioma: en
Numero de descargas: 76493.0
-----------------------------------
2️⃣ Listar Libros Registrados
Muestra todos los libros almacenados en la base de datos local.
3️⃣ Listar Autores Registrados
Muestra todos los autores con sus respectivos libros.
4️⃣ Listar Autores Vivos en un Año
Consulta autores que estaban vivos en un año específico.
Ejemplo:
Ingrese el año vivo de autor(es) que desea buscar
> 1600

Autor: Cervantes Saavedra, Miguel de
Fecha de nacimiento: 1547
Fecha de fallecimiento: 1616
Libros: [Don Quijote]
5️⃣ Listar Libros por Idioma
Filtra libros por idioma seleccionado:

es - Español
en - Inglés
fr - Francés
pt - Portugués

📁 Estructura del Proyecto
src/main/java/com/alura/catalogolibros/
├── CatalogoLibrosApplication.java
├── principal/
│   └── Principal.java
├── model/
│   ├── Libro.java
│   ├── Autor.java
│   ├── DatosLibro.java
│   ├── DatosLibroResultado.java
│   └── DatosAutor.java
├── repository/
│   ├── LibroRepository.java
│   └── AutorRepository.java
└── service/
    ├── ConsumoAPI.java
    ├── ConvierteDatos.java
    └── IConvierteDatos.java
🗄️ Modelo de Base de Datos
Tabla: libros
CampoTipoDescripciónidBIGSERIALClave primariatituloVARCHARTítulo del libro (único)idiomaVARCHARCódigo del idiomanumero_descargasDOUBLECantidad de descargasautor_idBIGINTFK a autores
Tabla: autores
CampoTipoDescripciónidBIGSERIALClave primarianombreVARCHARNombre completo (único)fecha_nacimientoINTEGERAño de nacimientofecha_fallecimientoINTEGERAño de fallecimiento
🔗 API Externa
El proyecto consume la API Gutendex, que proporciona acceso a información de más de 70,000 libros del Proyecto Gutenberg.
Endpoints Utilizados:

Búsqueda por título: https://gutendex.com/books/?search={titulo}

🚀 Características Técnicas

Spring Data JPA: Para el mapeo objeto-relacional
Derived Queries: Consultas automáticas basadas en nombres de métodos
Jackson Annotations: @JsonAlias y @JsonIgnoreProperties para mapeo JSON
Records de Java: Para DTOs inmutables
Relaciones JPA: @ManyToOne y @OneToMany entre Libro y Autor
CommandLineRunner: Para ejecutar el menú en consola

🐛 Manejo de Errores

Validación de libros duplicados
Manejo de autores existentes
Validación de respuestas vacías de la API
Control de opciones inválidas en el menú

📝 Notas Importantes

Solo se registra el primer autor de cada libro (simplificación del modelo)
Solo se registra el primer idioma de cada libro
Los nombres de autores se formatean como "Apellido, Nombre"
La aplicación no requiere clave de API para Gutendex

🤝 Contribuciones
Las contribuciones son bienvenidas. Para contribuir:

Fork el proyecto
Crea una rama para tu feature (git checkout -b feature/AmazingFeature)
Commit tus cambios (git commit -m 'Add some AmazingFeature')
Push a la rama (git push origin feature/AmazingFeature)
Abre un Pull Request

📄 Licencia
Este proyecto está bajo la Licencia MIT - ver el archivo LICENSE para más detalles.
👥 Autor
Tu Nombre

GitHub: @tu-usuario
LinkedIn: tu-perfil

🙏 Agradecimientos

Alura LATAM por el desafío
Proyecto Gutenberg por proporcionar libros de dominio público
Gutendex API por facilitar el acceso a los datos

📊 Estado del Proyecto

![prueba](https://github.com/user-attachments/assets/0d68e4ca-114d-43dc-95e0-9561da33d624)
![preuba2](https://github.com/user-attachments/assets/15a3a5b9-5171-45a0-a22f-e41ba6aaeb4d)
![prueba3](https://github.com/user-attachments/assets/17dc2c16-b42a-4d78-ab79-e1de3240c3f4)


