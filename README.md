# Springboot - Excepciones & MapStruct
Implementacion de manejo de excepciones a nivel controlador en Springboot y la utilizacion de `MapStruct` para el mappeo de entidades a sus DTOs correspondientes.

## Como Utilizar
1. Clonar el repositorio
```bash
$ git clone https://github.com/Programacion-Avanzada-UTN-FRVM/example-exceptions.git
$ cd example-exceptions
```

2. Ejecutar un compilado limpio y correr la app
```bash
$ mvn clean install && mvn spring-boot:run
```

(**Nota**: Es importante utilizar el `mvn clean install` si uno cambia la interfaz de `IMarcaMapper` para experimentar, ya que MapStruct necesita compilar la interfaz a una implementacion automatica y de otra manera no funcionara o tirara errores.)