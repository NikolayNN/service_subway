# SUBWAY_SERVICE

Микросервис SUBWAY_SERVICE предоставляет API для определения ближайшей станции метро к пользователю на основе заданных
координат и радиуса поиска.
Для работы с микросервисом доступна документация API, которая содержит описание запросов и ответов, и доступна по адресу
http://{host}:{port}/{base-url}/swagger-ui/index.html.
По умолчанию http://localhost:8080/subway_service/swagger-ui/index.html.

### Начало работы

Для начала работы необходимо настроить файл application.yml.
В нем следует указать base-url для внутреннего микросервиса get_location_by_coordinates,
который будет использоваться для определения города по координатам пользователя.
Это можно сделать, установив значение параметра

```
app.plugin.location.internal.base-url
```
Значение по умолчанию не предусмотрено

---

Также можно изменить внешние сервисы на другие, если это необходимо.
Для провайдера [NOMINATIM](https://nominatim.org/), который используется для определения границ поиска станций метро для каждого города,
можно установить base-url параметр

```
app.plugin.region.nominatim.base-url
```
По умолчанию используется https://nominatim.openstreetmap.org/search

---

Для провайдера [OVERPASS](https://wiki.openstreetmap.org/wiki/Overpass_API/), который используется для получения данных о станциях метро в границах города,
можно установить base-url параметр

```
app.plugin.subway.overpass.base-url
```

По умолчанию используется https://www.overpass-api.de/api/interpreter

---

### API

Запрос на определение ближайшей станции метро выполняется через API, используя HTTP-метод GET и следующий путь:
/api/v1/metro.
В запросе необходимо указать координаты пользователя (latitude и longitude) и радиус поиска (radius).
Если радиус не задан, то используется значение по умолчанию - 1000 м.

```agsl
GET /api/v1/metro?latitude=53.1234&longitude=27.4345&radius=2000
```

В ответ на запрос возвращается объект с информацией о ближайшей станции метро, включающий название страны, название
города, название станции метро, цвет линии метро и координаты станции метро.
Если ближайшая станция метро не найдена, в ответ приходит сообщение с описанием ошибки.

```agsl
{
    "country": "Беларусь",
    "city": "Минск",
    "metro": "Нямига"
    "color": "blue"
    "metroCoordinate" : {
            "latitude": 53.1234,
            "longitude": 27.5678
        }
}
```

### Принцип работы микросервиса основан на следующих шагах:

1. Получение координат пользователя и радиуса поиска.
2. Определение города по заданным координатам с помощью внутреннего микросервиса get_location_by_coordinates.
3. Определение границ поиска станций метро для заданного города с помощью внешнего сервиса Nominatim.
4. Поиск станций метро внутри границ города.
5. Определение ближайшей станции метро на основе расстояния до каждой станции метро.

> Для повышения производительности и снижения количества запросов к внешнему сервису Nominatim, запрос на получение
> станций метро для каждого города кешируется. Это позволяет сократить количество запросов в день и снизить вероятность
> возникновения узких мест в работе микросервиса.
