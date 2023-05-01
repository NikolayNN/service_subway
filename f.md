# Контракты
## get_user_info
### Назначение
Метод предназначен для предоставления данных пользователю после обращения к ресурсу.

### Входные параметры
Отсутствуют


#### Пример запроса
GET /

### Описание выходных параметров
В случае успешного выполнения запроса будет возвращен IP-адрес пользователя в качестве тела ответа со статусом 200 OK. Если возникли ошибки при выполнении запроса, будет возвращен соответствующий код ошибки с описанием проблемы.

### Выходные параметры
| Название параметра | Тип данных | Обязательность | Описание | Пример значения |
| --- | --- | --- | --- | --- | 
| ip_address | string | Да | IP-адрес пользователя | - | 46.242.8.104
| latitude | float | Да | Широта местоположения пользователя | [-90.000000;90.000000] |
| longitude | float | Да | Долгота местоположения пользователя | [-180.000000;180.000000] |
| country | string | Да |Страна местоположения | Russia
| city | string | Да | Город местоположения | Moscow
| country_code | string | Да | Код страны по стандарту ISO 3166 alpha-2В | ru
| encoding | string | Да | Кодировка для корректного отображения символов | UTF-8
| temp | float | Да | Температура | 15
| condition | string | Да |  Погодное описание |clear — ясно. partly-cloudy — малооблачно. cloudy — облачно с прояснениями. overcast — пасмурно. drizzle — морось. light-rain — небольшой дождь. rain — дождь. moderate-rain — умеренно сильный дождь. heavy-rain — сильный дождь. continuous-heavy-rain — длительный сильный дождь. showers — ливень. wet-snow — дождь со снегом. light-snow — небольшой снег. snow — снег. snow-showers — снегопад. hail — град. thunderstorm — гроза. thunderstorm-with-rain — дождь с грозой. thunderstorm-with-hail — гроза с градом.|
| pressure | integer | Да | Давление (в мм рт. ст.) | 756 |
| humidity | integer | Да | Влажность воздуха (в процентах) | 13 |
| metro | string | Да | Название станции метро | Чистые пруды |
| flag_url | string | Да |Ссылка на изображение флага страны в формате svg | https://example.com/image.svg
| data_flag| string | Да | Файл с изображением флага страны в формате svg | image.svg
| extract_wiki | string| Да | Первый абзац о городе из страницы в Википедии | город в России, административный центр Астраханской области, образует городской округ город Астрахань. Является центром Астраханской агломерации. Астрахань — старейший экономический и культурный центр Нижнего Поволжья и Прикаспия, входит в Перечень исторических городов России.
| quote_text | string| Да | Цитата дня на латыни | veni, vidi, vici
| quote_eng | string| Да | Цитата дня на английском языке | I came, I saw, I conquered
| quote_id | integer| Да | Идентификтор цитаты, привязанный к дате | 20230420 
| currency | string | Да | Код валюты по ISO 4217 alpha-3 | - | RUB
| data_currency_rate | float| Да | Список пар с информацией о курсе валют. Каждая пара содержит поля: "from" - исходная валюта, "to" - валюта назначения, "rate" - текущий курс обмена между валютами. | -




#### Пример успешного ответа
```javascript
200 Ok
{
    "ip_address": "46.242.8.104",
    "latitude": 12.345678,
    "longitude": 12.345678,
    "country": "Russia",
    "city": "Moscow",
    "country_code": "ru",
    "encoding": "UTF-8",
    "temp": 20,
    "condition": "Ясно",
    "pressure": 745,
    "humidity": 15,
    "metro": "Китай-город",
    "flag_url": "https://flagicons.lipis.dev/flags/4x3/ru.svg",
    "data_flag": "flag_ru.svg",
    "extract_wiki": "Москва — столица России, город федерального значения, административный центр Центрального федерального округа и центр Московской области, в состав которой не входит. Крупнейший по численности населения город России и её субъект — 13 097 539 человек (2023), самый населённый из городов, полностью расположенных в Европе, занимает 22-е место среди городов мира по численности населения. Центр Московской городской агломерации. Самый крупный город Европы по площади",
    "quote_text": "macte virtute sic itur ad astra",
    "quote_eng": "those who excel, thus reach the stars",
    "quote_id": 20230421,
    "currency": "RUB",
    "data_currency_rate": [
    {
        "from": "USD",
        "to": "RUB",
        "rate": 79,4
    },
    {
        "from": "EUR",
        "to": "RUB",
        "rate": 81,16
    }
          ]
}
```

#### Пример неудачного ответа
```javascript
503 Service Unavailable 
```



## get_location_by_ip
### Назначение
Метод предназначен для получения координат пользователя по IP-адресу.

### Входные параметры
| Название параметра | Тип параметра | Тип данных | Обязательность | Описание | Варианты значений |
| --- | --- | --- | --- | --- | --- |
| ip_address | Query  | string | Да | IP-адрес пользователя | - |

#### Пример запроса
GET /location?ip_address=46.242.8.104

### Описание выходных параметров
В случае успешного выполнения запроса будет возвращены широта и долгота пользователя в качестве тела ответа со статусом 200 OK. Если возникли ошибки при выполнении запроса, будет возвращен соответствующий код ошибки с описанием проблемы.

### Выходные параметры
| Название параметра | Тип данных | Обязательность | Описание | Варианты значений |
| --- | --- | --- | --- | --- |
| latitude | float | Да | Широта местоположения пользователя | [-90.000000;90.000000] |
| longitude | float | Да | Долгота местоположения пользователя | [-180.000000;180.000000] |


#### Пример успешного ответа
```javascript
200 Ok
{
    "latitude": 50.730609,
    "longitude": 8.787759
}
```
#### Пример неудачного ответа
```javascript
404 Not Found
{
    "message":"Данного IP-адреса не существует в диапозоне возможных IP-адресов"
}
```


## get_location_by_coordinates
### Назначение
Метод предназначен для определения страны и города по координатам.

### Входные параметры
| Название параметра | Тип параметра | Тип данных | Обязательность | Описание | Варианты значений |
| --- | --- | --- | --- | --- | --- |
| latitude |Query| float | Да | Широта местоположения пользователя | [-90.000000;90.000000] |
| longitude |Query| float | Да | Долгота местоположения пользователя | [-180.000000;180.000000] |

#### Пример запроса
GET /location?lat={широта}&lon={долгота}

### Описание выходных параметров
В случае успешного выполнения запроса будет возвращены страна, город, код страны по стандарту ISO 3166 alpha-2В и кодировка UTF-8 языка пользователя в качестве тела ответа со статусом 200 OK. Если возникли ошибки при выполнении запроса, будет возвращен соответствующий код ошибки с описанием проблемы.


### Выходные параметры
| Название параметра | Тип данных | Обязательность | Описание | Варианты значений |
| --- |--- | --- | --- | --- |
| country | string | Да |Страна местоположения |
| city | string | Да | Город местоположения |
| country_code | string | Да | Код страны по стандарту ISO 3166 alpha-2В |
| encoding | string | Да | Кодировка для корректного отображения символов | UTF-8


#### Пример успешного ответа
```javascript
200 Ok 
{
    "country": "Russian Federation",
    "city":"Moscow",
    "country_code":"ru_Ru",
    "encoding":"UTF-8",

}
```
#### Пример неудачного ответа
```javascript
404 Not Found
{
    "message": "Местоположение по заданным координатам не найдено"
}
```

## get_weather_by_coordinates
### Назначение
Метод предназначен для определения погоды по местоположению.

### Входные параметры
| Название параметра | Тип параметра | Тип данных | Обязательность | Описание | Варианты значений |
| --- | --- | --- | --- | --- | --- |
| city | Query | string | Да | Город, для которого запрашивается информация | - |
| country | Query | string | Да | Страна, для которой запрашивается информация| - |
| date | Query | string | Да | Дата в формате YYYY-MM-DD | - |
| units | Query | string | Нет | Единицы измерения температуры | metric, imperial |
| lang | Query | string | Нет | Язык отображения данных | - |
| latitude |Query| float | Да | Широта местоположения пользователя | [-90.000000;90.000000] |
| longitude |Query| float | Да | Долгота местоположения пользователя | [-180.000000;180.000000] |


#### Пример запроса
GET /weather?city=Moscow&country=RussianFederation&date=2023-04-17&units=metric&lang=RU&latitude=43.234567&longtitude=12.34567

### Описание выходных параметров
В случае успешного выполнения запроса будет возвращена погода пользователя в качестве тела ответа со статусом 200 OK. Если возникли ошибки при выполнении запроса, будет возвращен соответствующий код ошибки с описанием проблемы.

### Выходные параметры
| Название параметра |Тип данных | Описание | Варианты значений
| --- |--- | --- |--- |
| url | string |Ссылка на населенный пункт на сайте погоды  | - |
| temp | float | Температура | - |
| condition | string | Погодное описание |clear — ясно. partly-cloudy — малооблачно. cloudy — облачно с прояснениями. overcast — пасмурно. drizzle — морось. light-rain — небольшой дождь. rain — дождь. moderate-rain — умеренно сильный дождь. heavy-rain — сильный дождь. continuous-heavy-rain — длительный сильный дождь. showers — ливень. wet-snow — дождь со снегом. light-snow — небольшой снег. snow — снег. snow-showers — снегопад. hail — град. thunderstorm — гроза. thunderstorm-with-rain — дождь с грозой. thunderstorm-with-hail — гроза с градом.|
| pressure | integer | Давление (в мм рт. ст.) | - |
| humidity | integer | Влажность воздуха (в процентах) | - |

#### Пример успешного ответа
```javascript
200 Ok
{
    "url": "https://example.ru/pogoda/moscow"
    "temp": 14,
    "condition": "clear",
    "pressure": 745,
    "humidity": 83,
},
```

#### Пример неудачного ответа
```javascript
404 Not Found
{
    "message": "Город не найден"
}
```


## get_metro_by_coordinates
### Назначение
Метод предназначен для определения ближайшей к пользователю станции метро.

### Входные параметры
| Название параметра | Тип параметра | Тип данных | Обязательность | Описание | Варианты значений |
| --- | --- | --- | --- | --- | --- |
| latitude |Query| float | Да | Широта местоположения пользователя | [-90.000000;90.000000] |
| longitude |Query| float | Да | Долгота местоположения пользователя | [-180.000000;180.000000] |
| radius | Query | integer | Нет | Радиус поиска станций метро в метрах | 1000 |


#### Пример запроса
GET /metro?latitude=55.753215&longtitude=37.620548&radius=1000

### Описание выходных параметров
В случае успешного выполнения запроса будет возвращено название ближайшей станции метро в качестве тела ответа со статусом 200 OK. Если возникли ошибки при выполнении запроса, будет возвращен соответствующий код ошибки с описанием проблемы.

### Выходные параметры
| Название параметра | Тип данных | Обязательность | Описание | Варианты значений |
| --- |--- | --- | --- | --- |
| country | string | Нет |Страна местоположения |
| city | string | Нет | Город местоположения |
| metro | string | Да | Название станции метро |  |


#### Пример успешного ответа
```javascript
200 Ok
{
    "country": "Россия"
    "city": "Москва",
    "metro": "Чистые пруды",
},
```

#### Пример неудачного ответа
```javascript
404 Not Found 
{
    "message": "Станция метро не найдена"
}
```


## get_flag_by_country
### Назначение
Метод предназначен для предоставления иконки флага по названию страны.

### Входные параметры
| Название параметра | Тип параметра | Тип данных | Обязательность | Описание | Варианты значений |
| --- | --- | --- | --- | --- | --- |
| country | Query | string | Да | Страна, для которой запрашивается флаг| - |
| country_code | Query | string | Да | Код страны по стандарту ISO 3166 alpha-2В | -


#### Пример запроса
GET /flags?country=Russia&country_code=ru

### Описание выходных параметров
В случае успешного выполнения запроса будет возвращена ссылка на иконку страны и файл с флагом страны качестве тела ответа со статусом 200 OK. Если возникли ошибки при выполнении запроса, будет возвращен соответствующий код ошибки с описанием проблемы.

### Выходные параметры
| Название параметра | Тип данных | Обязательность | Описание | Варианты значений |
| --- |--- | --- | --- | --- |
| flag_url | string | Да |Ссылка на изображение флага страны в формате svg |
| data| string | Да | Файл с изображением флага страны в формате svg |

#### Пример успешного ответа
```javascript
200 Ok
{
    "flag_url": "https://example.com/image.svg",
    "data": "image.svg""
}
```   


#### Пример неудачного ответа
```javascript
404 Not Found
{
    "message": "Флаг не найден"
}
```

## get_wiki_city
### Назначение
Метод предназначен для получения первого абзаца о городе в Википедии.Происходит поиск статьи и сравнение данных статьи с доступными координатами пользователя. Если статья верна, то пользователь увидит первый абзац о своем городе из Википедии на своем языке. Если такой статьи нет, то он увидит цитату дня на латинском языке.

### Шаг 1.
Получение ссылки на Википедию.

### Входные параметры
| Название параметра | Тип параметра | Тип данных | Обязательность | Описание | Варианты значений |
| --- | ---| ---| --- | --- | --- |
| city | Query | string | Да | Город, для которого запрашивается информация | - |
| country | Query | string | Да | Страна, для которой запрашивается информация| - |

#### Пример запроса
GET /wikilocation?city=Astrakhan&country=Russia

### Описание выходных параметров
В случае успешного выполнения запроса будет возвращенен id страницы в Википедии для выбранного города в теле ответа со статусом 200 ОК. Если возникли ошибки при выполнении запроса, будет возвращен соответствующий код ошибки с описанием проблемы и после этого переходим к шагу 5.

### Выходные параметры
| Название параметра | Тип данных | Обязательность | Описание | Варианты значений |
| --- |--- | --- | --- | --- |
| page_id | integer | Да | Id страницы выбранного города из Википедии | - |

#### Пример успешного ответа
```javascript
200 Ok
{
    "page_id": 20673
},
```

#### Пример неудачного ответа
```javascript
404 Not Found
{
    "message": "Страница не найдена"
}
```
### Шаг 2.
Получение координат города на странице в Википедии.

### Входные параметры
| Название параметра | Тип параметра | Тип данных | Обязательность | Описание | Варианты значений |
| --- | --- | --- | --- | --- | --- |
| page_id| Query | integer | Да | Id страницы выбранного города из Википедии | - |

#### Пример запроса
Get /coordinates?page_id=20673

### Описание выходных параметров
В случае успешного выполнения запроса будут возвращенены широта и долгота для выбранного города из Википедии в теле ответа со статусом 200 ОК. Если возникли ошибки при выполнении запроса, будет возвращен соответствующий код ошибки с описанием проблемы и после этого переходим к шагу 5.

### Выходные параметры
| Название параметра | Тип данных | Обязательность | Описание | Варианты значений |
| --- | --- | --- | --- | --- | 
| latitude_wiki | float | Да | Широта города из статьи в Википедии | [-90.000000;90.000000] |
| longitude_wiki |float | Да | Долгота города из статьи в Википедии | [-180.000000;180.000000] || wikilink | string | Да | Ссылка на выбранный город из Википедии | - |

#### Пример успешного ответа
```javascript
200 Ok
{
    "latitude_wiki ": 60.282729,
    "longitude_wiki": 69.420228
}
```

#### Пример неудачного ответа
```javascript
404 Not Found
{
    "message": "Координаты не найдены"
}
```
### Шаг 3.
Сравнение координат из Википедии с координатами пользователя.

### Входные параметры
| Название параметра | Тип параметра | Тип данных | Обязательность | Описание | Варианты значений |
| --- | --- | --- | --- | --- | --- |
| latitude_wiki |Query| float | Да | Широта города из статьи в Википедии | [-90.000000;90.000000] |
| longitude_wiki |Query| float | Да | Долгота города из статьи в Википедии | [-180.000000;180.000000] |
| page_id |Query| integer | Да | Id страницы города на Википедии | - |
| latitude |Query| float | Да | Широта местоположения пользователя | [-90.000000;90.000000] |
| longitude |Query| float | Да | Долгота местоположения пользователя | [-180.000000;180.000000] |

#### Пример запроса
Get /coordinates_comparison?page_id=20673&latitude_wiki=12.345678&longitude_wiki=12.45678&latitude=12.345687&longitude=12.345687

### Описание выходных параметров
В случае успешного выполнения запроса будет возвращен статус сравнения в теле ответа со статусом 200 ОК. Если статус положительный, то переходим к шагу 4. Если статус отрицательный, то сразу переходим к шагу 5. Если возникли ошибки при выполнении запроса, будет возвращен соответствующий код ошибки с описанием проблемы и после этого переходим к шагу 5.

| Название параметра | Тип данных | Обязательность | Описание | Варианты значений |
| --- | --- | --- | --- | --- | 
| status | bool | Да | Статус сравнения координат города на вики с координатами пользоватля | true/false

#### Пример успешного ответа
```javascript
200 Ok
{
    "status": true
}
}
```

#### Пример неудачного ответа
```javascript
404 Not Found
{
    "message": "Координаты не найдены"
}
```

### Шаг 4.
Получение первого абзаца о городе из страницы на Википедии.

### Входные параметры
| Название параметра | Тип параметра | Тип данных | Обязательность | Описание | Варианты значений |
| --- | --- | --- | --- | --- | --- |
| page_id | Query | integer | Да | Id страницы выбранного города из Википедии | - |
| status | Query | bool | Да | Статус сравнения координат города на вики с координатами пользоватля | true/false

#### Пример запроса
Get /wiki_fact?page_id=20673&status=true

### Описание выходных параметров
В случае успешного выполнения запроса будет возвращен первый абзац о городе из Википедии теле ответа со статусом 200 ОК. Если возникли ошибки при выполнении запроса, будет возвращен соответствующий код ошибки с описанием проблемы и после этого переходим к шагу 5.

### Выходные параметры
| Название | Тип данных | Обязательный|  Описание | Варианты значений |
| --- | --- | --- | --- |--- |
| extract_wiki | string| Да | Первый абзац о городе из страницы в Википедии |

#### Пример успешного ответа
```javascript
200 Ok
{
    "extract_wiki":"Астрахань — город в России, административный центр Астраханской области, образует городской округ город Астрахань. Является центром Астраханской агломерации. Астрахань — старейший экономический и культурный центр Нижнего Поволжья и Прикаспия, входит в Перечень исторических городов России. Располагается в верхней части дельты реки Волги, на 11 островах Прикаспийской низменности. Расстояние до Москвы по автодороге составляет 1400 км. Город разделён на 4 административных района: Кировский, Советский, Ленинский и Трусовский. Население составляет 475 629 человек (2021), площадь — 208,7 км². В Астрахани проживают представители более 200 национальностей, 19 конфессий, действуют 170 обществ национальных культур."

}
```

#### Пример неудачного ответа
```javascript
404 Not found
{
    "message": "Информация не найдена"
}
```


### Шаг 5.
Получение цитаты дня на латинском языке.

### Входные параметры
| Название параметра | Тип параметра | Тип данных | Обязательность | Описание | Варианты значений |
| --- | --- | --- | --- | --- | --- |
| date | Query | date | Да | Дата в формате YYYY-MM-DD | -

#### Пример запроса
Get /quote?date=2020-04-20

### Описание выходных параметров
В случае успешного выполнения запроса будет возвращена цитата дня в теле ответа со статусом 200 ОК. Если возникли ошибка при выполнении запроса, то будет возвращен соответствующий код ошибки с описанием проблемы.

### Выходные параметры
| Название | Тип данных | Обязательный|  Описание | Варианты значений |
| --- | --- | --- | --- |--- |
| quote_text | string| Да | Цитата дня на латыни | - 
| quote_eng | string| Да | Цитата дня на английском языке | - 
| quote_id | integer| Да | Идентификтор цитаты, привязанный к дате | - 


#### Пример успешного ответа
```javascript
200 Ok
{
    "quote_text":"veni, vidi, vici",
    "quote_eng":"I came, I saw, I conquered",
    "quote_id": 20230420

}
```

#### Пример неудачного ответа
```javascript
404 Not found
{
    "message": "Цитата не найдена"
}
```


## get_currency
### Назначение
Метод предназначен для определения валюты пользователя согласно его стране.

### Входные параметры
| Название параметра | Тип параметра | Тип данных | Обязательность | Описание | Варианты значений |
| --- | --- | --- | --- | --- | --- |
| country_code | Query | string | Да | Код страны по стандарту ISO 3166 alpha-2В | - |

#### Пример запроса
GET /get_currency?country_code=RU

### Описание выходных параметров
Код страны переводим в код валюты по стандарту ISO 4217 alpha-3 и передаем в тело запроса  со статусом 200 OK. Если возникли ошибки при выполнении запроса, будет возвращен соответствующий код ошибки с описанием проблемы.


### Выходные параметры
| Название параметра | Тип данных | Обязательность | Описание | Варианты значений |
| --- |--- | --- | --- | --- |
| currency | string | Да | Код валюты по ISO 4217 alpha-3 | - |


#### Пример успешного ответа
```javascript
200 Ok 
{
    "currency": "RUB"
}
```

#### Пример неудачного ответа
```javascript
400 Bad request
{
  "message": "Invalid country code"
}
```

## get_currency_value
### Назначение
Метод предназначен для конвертации из одной валюты в другую для заданного пользователем количества валюты, которое нужно конвертировать

### Входные параметры
| Название | Тип данных | Обязательный |Описание | Варианты значений |
| --- | --- | --- | --- |--- |
| from | Query | string | Да | Из какой валюты совершается конвертация | -
| to | Query| string | Да | В какую волюту совершается конвертация | - 
| amount | Query| integer | Нет | Количество валюты, которое необходимо конвертировать | -

#### Пример запроса
GET /convert?from=AED&to=GBP&amount=100

### Описание выходных параметров
В случаем успешного выполнения метода возвращаются текущие курсы доллара и евро к рублю в тело запроса  со статусом 200 OK. Если возникли ошибки при выполнении запроса, будет возвращен соответствующий код ошибки с описанием проблемы.

### Выходные параметры
| Название | Тип данных | Обязательный|  Описание | Варианты значений |
| --- | --- | --- | --- |--- |
| data_value | string | Да | "from" - исходная валюта, "to" - валюта назначения, "total_value" - общее конвертированное значение | - |


#### Пример успешного ответа
```javascript
200 Ok 
{
  "data": [
    {
        "from": "AED",
        "to": "GBR",
        "total_value": 10
    },
          ]
}
```

#### Пример неудачного ответа
```javascript
400 Bad Request
{
    "response_info": "Invalid country code"
}
```

## get_exchange_rate
### Назначение
Метод предназначен для получения курса рубля по отношению в доллару и евро.

### Входные параметры
| Название | Тип данных | Обязательный |Описание | Варианты значений |
| --- | --- | --- | --- |--- |
| pair | string | да | Пара валют, разделенная запятой. Формат пары валют: [код валюты 1] [код валюты 2]. |USDRUB, EURRUB.

#### Пример запроса
GET /currency_rate?pair=USDRUB,EURRUB

### Описание выходных параметров
В случаем успешного выполенния метода возвращаются текущие курсы доллара и евро к рублю в тело запроса  со статусом 200 OK. Если возникли ошибки при выполнении запроса, будет возвращен соответствующий код ошибки с описанием проблемы.

### Выходные параметры
| Название | Тип данных | Обязательный|  Описание | Варианты значений |
| --- | --- | --- | --- |--- |
| data_currency_rate | float| Да | Список пар с информацией о курсе валют. Каждая пара содержит поля: "from" - исходная валюта, "to" - валюта назначения, "rate" - текущий курс обмена между валютами. |



#### Пример успешного ответа
```javascript
200 Ok 
{
  "data_currency_rate": [
    {
        "from": "USD",
        "to": "RUB",
        "rate": 81,5
    },
    {
        "from": "EUR",
        "to": "RUB",
        "rate": 90,16
    }
          ]
}
```

#### Пример неудачного ответа
```javascript
400 Bad Request
{
    "response_info": "Invalid country code"
}
```