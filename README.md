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

## Usar en tu proyecto
Si quieres adaptar **MapStruct** y el **sistema de validaciones** en tu propio proyecto de Springboot, sigue las siguientes instrucciones:
1. Abre el archivo `pom.xml` de tu proyecto de Springboot.
2. En la seccion de `dependencies` agrega lo siguiente:
```xml
<project>
    ...
    <dependencies>
		<dependency>
			<groupId>jakarta.validation</groupId>
			<artifactId>jakarta.validation-api</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>org.mapstruct</groupId>
			<artifactId>mapstruct</artifactId>
			<version>1.6.2</version>
		</dependency>
    </dependencies>
    ...
</project>
```

3. Con esto ya se tiene disponible la API de validaciones en todo el proyecto, pero ahora es necesario agregar un plugin extra para que MapStruct funcione.
4. En el archivo `pom.xml` busca la seccion `build`->`plugins` y agrega lo siguiente:
```xml
<project>
    ...
    <build>
        <plugins>
            ...
            <plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.7.0</version>
				<configuration>
					<source>1.8</source>
					<target>1.8</target>
					<annotationProcessorPaths>
						<path>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
							<version>${lombok.version}</version>
						</path>
						<path>
							<groupId>org.mapstruct</groupId>
							<artifactId>mapstruct-processor</artifactId>
							<version>1.6.2</version>
						</path>
					</annotationProcessorPaths>
					<compilerArgs>
						<compilerArg>
							   -Amapstruct.defaultComponentModel=spring
						</compilerArg>
					</compilerArgs>
				</configuration>
			</plugin>
        </plugins>
        ...
    </build>
    ...
</project>
```

5. En la terminal, parado en el root del proyecto, ejecuta el comando `mvn clean install` para instalar las dependencias nuevas.
   1. **IntelliJ**: Si estas usando IntelliJ, presiona las teclas `CTRL + SHIFT + A` y escribe `reload` o `recargar` y dale a la opcion de `Recargar todos los proyectos de Maven`.
