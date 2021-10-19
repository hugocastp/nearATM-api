# Near ATM API

Java API que devuelve la ubicación de los Cajeros Automáticos y Sucursales de Citibanamex cercanos a la dirección proporcionada.

## Importar el proyecto

- Asegurate de tener:
  - `Java (min. version 8)` installed
  - `Maven` installed
- Visual Studio Code:
  - `Extension Pack for Java:` https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack
  - `Spring Boot Extension Pack:` https://marketplace.visualstudio.com/items?itemName=Pivotal.vscode-boot-dev-pack 
  - `Lombok Annotations Support for VS Code:` https://marketplace.visualstudio.com/items?itemName=GabrielBB.vscode-lombok

### Ejecutando el código

- La clase `NearATMApplication.java` es la clase principal. Ejecuta su método `main`.

### Ejemplo

## Muestra la sucursal o ATM más cercano dadas tu latitud y longitud.

# Input

`/sucursal/32.300287/-117.938187`

# Output

```{
    "sucNumber": "968",
    "sucType": "Sucursal",
    "sucAddress": {
        "addressLine1": "Av. Ensenada 130,",
        "addressLine2": "Col. Playas De Tijuana, Tijuana, C.P. 22200, Baja California"
    },
    "sucLatitude": 32.532667,
    "sucLongitude": -117.113335
}
```

![Postman](https://github.com/ungeimer/nearATM-api/blob/master/img/PostmanTest.jpg?raw=true)

* Si pasas algún dato válido, te retornará la sucursal o ATM más cercano.
* Si no pasas ningun dato de localización, se mostrarán todas las sucursales y ATM.


## Geocoding API de Google Maps
La API de Geocoding (https://developers.google.com/maps/documentation/geocoding/start) es un servicio que proporciona codificación geográfica (geocoding) y codificación geográfica inversa de direcciones. Para utilizar esta API de Geocoding, primero debe registrarse en (https://developers.google.com/maps/documentation/geocoding/get-api-key) utilizando su ID de Google. Después de registrarse, Google le proporciona una API key que necesita actualizar en el archivo `application.properties` como

```
config.apikey=<Tu key va aquí>
```


* ***api***
Contiene la clase `SucursalController`, que contiene las solicitudes/enrutadores de la API y brinda las reglas y la lógica del negocio.
Contiene `LocationsAPI`, que contiene las solicitudes para obtener el JSON de ubicaciones (https://www.banamex.com/localizador/jsonP/json5.json) y transformar
el resultado en objetos Sucursal.

* ***data***
Contiene las clases `DistanceCalc`,`SucursalService` y la interfaz `ISucursarService` que contienen la lógica para calcular la sucursal más cercana.

* ***geocode***
Contiene la clase `GeocodeService` Servicio para localizar la latitud y longitud de una sucursal usando el servicio de geocoding de Google.

* ***model***
Contiene las clases `models(POJOs)` para el dominio Sucursal.

## La Ortodrómica
La [ortodrómica] (https://es.wikipedia.org/wiki/Ortodr%C3%B3mica) es el camino más corto entre dos puntos de la superficie terrestre; es el arco del círculo máximo que los une, menor de 180 grados. Entre dos puntos de la superficie terrestre pueden trazarse tres líneas diferentes: ortodrómica, loxodrómica e isoazimutal.

Distancia ortodrómica entre dos puntos a lo largo de un círculo máximo sobre la superficie de una esfera.
Si los puntos estuvieran separados 180 grados, serían puntos opuestos, también conocidos como antípodas, y entre ellos se podrían trazar infinitos arcos de 180 grados de igual longitud.

## Fórmula del semiverseno
La [fórmula del semiverseno] (https://es.wikipedia.org/wiki/F%C3%B3rmula_del_semiverseno) es una importante ecuación para la navegación astronómica, en cuanto al cálculo de la distancia de círculo máximo entre dos puntos de un globo sabiendo su longitud y su latitud. Es un caso especial de una fórmula más general de trigonometría esférica, la ley de los semiversenos, que relaciona los lados y ángulos de los "triángulos esféricos".


