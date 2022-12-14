## Motivación

Este proyecto fue realizado como un desafío personal para aplicar algunas de las tecnologías que he ido aprendiendo a lo largo del tiempo. También tiene la finalidad de ser parte de un portafolio de temas y tecnologías que he ido aprendiendo a lo largo de los años.

## Objetivo

Mostrar en un mapa (google maps u open street map) lugares de interés turístico que me gustaría visitar alguna vez. La idea es elegir el país, y me marcará el o los atractivos turísticos que hay ahí (solo los que me gustaría visitar). 

### Nota
Este ejemplo tiene muchas cosas que corregir, por ejemplo no se le ha agregado spring-security para limitar los REST expuestos. Ni se ha realizado una restriccion de acceso al proyecto cliente.

## Tecnologías Usadas

- Eclipse IDE for Enterprise Java and Web Developers. Version: 2022-09 (4.25.0)
- Java 8
- SpringBoot 2.7.4
- Apache Tomcat 9.0.65
- JPA
- MariaDb 3.0.8
- Hibernate 5.6.11.Final
- Thymeleaf 3.0.15.RELEASE
- HTML5 & CSS3
- Swagger

## Descripción del proyecto

1) El proyecto está dividido en 2 partes: un servidor REST que acepta peticiones CRUD (Create, Read, Update y Delete) para países y atractivos turísticos.
2) A nivel de base de datos tenemos la tabla **country** con su nombre (en inglés), los códigos de 2 y 3 letras (de acuerdo a la ISO3166). La tabla **touristAttraction** con su nombre (en inglés), que tendrá su nombre (en inglés), y la ubicación geográfica. Internamente esta columna ubicacion tiene como tipo de datos un POINT.
3) Para poder trabajar con JSON con datos geográficos se tuvo que agregar las siguientes dependencias:
  + jackson-core (v2.13.4)
  + jackson-datatype-jts (v1.2.10, groupId: org.n52.jackson)
  + jts-core (v1.19.0, groupId: org.locationtech.jts)
  + hibernate-spatial (v5.6.11.Final)

  Para resolver los diferentes problemas que tuve para enganchar REST con los datos geográficos, referirse al siguiente enlace:
  "Aqui va el enlace de stackoverflow".
4) Para documentar los servicios REST expuestos se utilizó  Spring REST Open API 3.0. Para revisar la documentacion de esta API:
http://localhost:8080/swagger-ui/index.html

  ## Ejemplos JSON de uso del Servicio REST

  1. Agregar un país:
    - Método HTTP: POST.
    - URL: 
    - JSON Body:
    ```json
  {
    "nombre": "MEXICO",
    "alpha2Code": "MX",
    "alpha3Code": "MEX"
  }
    ```
    - JSON Response:
    ```json
  {
    "id": 18,
    "nombre": "MEXICO",
    "alpha2Code": "MX",
    "alpha3Code": "MEX",
    "touristicAttractions":[]
  }    
    ```

  2. Obtener los datos de un país:
    - Método HTTP: GET
    - URL: http://localhost:8080/placestovisit/api/countries/{country_id}. Por ejemplo, {country_id}=1
    - JSON Response:
    ```json
  {
    "nombre": "CHILE",
    "alpha2Code": "CL",
    "alpha3Code": "CHL",
    "touristicAttractions": [
        {
            "id": 1,
            "nombre": "PARQUE NACIONAL TORRES DEL PAINE",
            "ubicacion": {
                "type": "Point",
                "coordinates": [
                    -51.0406792,
                    -72.98436811
                ]
            }
        },
        {
            "id": 2,
            "nombre": "ISLA DE PASCUA",
            "ubicacion": {
                "type": "Point",
                "coordinates": [
                    -27.119404,
                    -109.354704
                ]
            }
        }
    ]
  }    
    ```

  3. Obtener todo el listado de países:
    - Método HTTP: GET
    - URL: http://localhost:8080/placestovisit/api/countries
    - JSON Response:
    ```json
  [
    {
        "id": 1,
        "nombre": "CHILE",
        "alpha2Code": "CL",
        "alpha3Code": "CHL",
        "touristicAttractions": [
            {
                "id": 1,
                "nombre": "PARQUE NACIONAL TORRES DEL PAINE",
                "ubicacion": {
                    "type": "Point",
                    "coordinates": [
                        -51.0406792,
                        -72.98436811
                    ]
                }
            },
            ...
        ]
    },
    ...
  ]
    ```

  4. Agregar una atracción turística:
    - Método HTTP: POST.
    - URL: http://localhost:8080/api/touristic_attractions/
    - JSON BODY:
    ```json
  {
    {
    "nombre":"ISLA DE PASCUA",
    "ubicacion":{
        "type":"Point",
        "coordinates":[
            -27.119404,
            -109.354704
        ]
    },
    "country":{
        "id":1
    }
  }
    ```
    - JSON Response:
    ```json
  {
    "id": 3,
    "nombre": "ISLA DE PASCUA",
    "ubicacion": {
        "type": "Point",
        "coordinates": [
            -27.119404,
            -109.354704
        ]
    }
  }
    ```

  5. Obtener los datos de una atracción turística:
    - Método HTTP: GET.
    - URL: http://localhost:8080/touristic_attractions/{attraction_id}. Por ejemplo {attraction_id}=1
    - JSON Response:
    ```json
  {
    "id": 1,
    "nombre": "PARQUE NACIONAL TORRES DEL PAINE",
    "ubicacion": {
        "type": "Point",
        "coordinates": [
            -51.0406792,
            -72.98436811
        ]
    }
  }
    ```

  6. Listar todas las atracciones turísticas:
    - Método HTTP: GET.
    - URL: http://localhost:8080/touristic_attractions
    - JSON Response:
    ```json
  [
    {
        "id": 1,
        "nombre": "PARQUE NACIONAL TORRES DEL PAINE",
        "ubicacion": {
            "type": "Point",
            "coordinates": [
                -51.0406792,
                -72.98436811
            ]
        }
    },
    ...
  ]
    ```

  7. Obtener datos del país de una atracción turística:
    - Método HTTP: GET.
    - URL: http://localhost:8080/touristic_attractions/{attraction_id}/country.Por ejemplo {attraction_id}=2
    - JSON Response:
    ```json
  {
    "id": 1,
    "nombre": "CHILE",
    "alpha2Code": "CL",
    "alpha3Code": "CHL",
    "touristicAttractions": [
        {
            "id": 1,
            "nombre": "PARQUE NACIONAL TORRES DEL PAINE",
            "ubicacion": {
                "type": "Point",
                "coordinates": [
                    -51.0406792,
                    -72.98436811
                ]
            }
        },
        ...
    ]
  }    
    ```

  ## Referencias

  - Lista de países y sus códigos ISO3166: https://www.iban.com/country-codes
  - Lista de paises y sus coordenadas del centroide: https://developers.google.com/public-data/docs/canonical/countries_csv
  - Spring REST OpenApi 3.0: https://www.baeldung.com/spring-rest-openapi-documentation
  - Spring JPA How to Display the JsonIgnored field on the inverse side in a One-To-Many Bidirectional: https://stackoverflow.com/questions/70829392/spring-jpa-how-to-display-the-jsonignored-field-on-the-inverse-side-in-a-one-to
  - ¿Como solucionar error "No serializer found for class org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor"?: https://es.stackoverflow.com/questions/374212/como-solucionar-error-no-serializer-found-for-class-org-hibernate-proxy-pojo-b
