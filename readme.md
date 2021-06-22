[![Build Status](https://travis-ci.com/AndrewAlyonkin/filesStorageREST.svg?branch=master)](https://travis-ci.com/AndrewAlyonkin/filesStorageREST)

RESTful приложение, которое взаимодействует с файловым хранилищем и предоставляет
возможность получать доступ к файлам и истории загрузок и осуществлять над ними
все CRUD операции.  
**Сущности:**  
>User
>Event (File file)
>File
>User -> List<File> files + List<Events>  


Технологии: Java, MySQL, Hibernate, HTTP, Servlets, Maven, Flyway.


Выполнено в соответствии с патерном MVC;  
Для миграции БД используется flyway ;  
Все слои приложения покрыты юнит тестами (junit + mockito);  
Проект собирается через Maven;
Рабочее приложение развернуто на heroku.com

* Стек технологий: 
  * Java
  * MySQL
  * JDBC
  * Maven
  * Liquibase, FlyWay 
  * JUnit 
  * Mockito 
  * Travis

## REST API:
## Endpoints
>/rest/users   
#### Method GET - получить список всех пользователей в формате
*[
    {
        "name": "Chuck Norris",
        "storedFiles": [
            {
                "fileURI": "Chuck://test/testDir/testFile.pdf",
                "size": 9000,
                "id": 10002
            },
            {
                "fileURI": "Chuck://test/testDir/testFile.pdf",
                "size": 90000000,
                "id": 10008
            }
        ],
        "events": [
            {
                "storedFile": {
                    "fileURI": "Chuck://test/testDir/testFile.pdf",
                    "size": 9000,
                    "id": 10002
                },
                "downloadDateTime": "Jun 20, 2021, 7:10:25 PM",
                "id": 10006
            }
        ],
        "id": 10000
    },
    {
        "name": "Bruce Lee",
        "storedFiles": [
            {
                "fileURI": "Lee://BruceDir/virusFile.jpeg",
                "size": 35000,
                "id": 10005
            },
            {
                "fileURI": "Lee://BruceDir/testFile.exe",
                "size": 25000,
                "id": 10004
            }
        ],
        "events": [
            {
                "storedFile": {
                    "fileURI": "Lee://BruceDir/virusFile.jpeg",
                    "size": 35000,
                    "id": 10005
                },
                "downloadDateTime": "Jun 20, 2021, 7:10:25 PM",
                "id": 10007
            }
        ],
        "id": 10001
    }
]*

>/rest/users?id={ }
#### Method GET c параметром id={ } -
**(/rest/users?id=10000)**  
получить информацию о пользователе в формате  
*{"fileURI":"Chuck://test/testDir/testFile.pdf",
"size":90000000,"user":{"name":"Chuck Norris","id":10000},"id":null}*  
#### Method DELETE c параметром id={ } - удалить информацию о пользователе из базы
#### Method POST - создать нового пользователя, пример тела запроса:
*{"name": "POST MAN 2","storedFiles": [{"fileURI": "Chuck://test/testDir/testFile.pdf","size": 9000,"id": 10002},{"fileURI": "Chuck://test/testDir/testFile.pdf","size": 90000000,"id": 10008}],"events": [{"storedFile": {"fileURI": "Chuck://test/testDir/testFile.pdf","size": 9000,"id": 10002},"downloadDateTime": "Jun 20, 2021, 7:10:25 PM","id": 10006}],"id": null}*
#### Method PUT - обновить информацию о пользователе в базе  
>/rest/files
#### Method GET - получить список всех файлов в формате  
*[
{
"fileURI": "Chuck://test/testDir/testFile.pdf",
"size": 9000,
"id": 10002
},
{
"fileURI": "Lee://BruceDir/testFile.exe",
"size": 25000,
"id": 10004
},
{
"fileURI": "Lee://BruceDir/virusFile.jpeg",
"size": 35000,
"id": 10005
},
{
"fileURI": "Chuck://test/testDir/testFile.pdf",
"size": 90000000,
"id": 10008
}
]*
#### Method POST - создать файл
>/rest/files?id={ }
#### Method GET - получить информацию о файле, пример запроса
`/rest/files?id=10004`
#### Method DELETE - удалить информацию о файле

>/rest/files?userId={ }  
#### Method GET - получить информацию всех файлах пользователя, пример запроса  
`/rest/files?userId=10000`
>/rest/events  
> #### Method GET - получить список всех событий
>/rest/events?id={ }  
#### Method GET - получить информацию о событии, пример запроса
`/rest/events?id=10006`  
#### Method DELETE - удалить информацию из истории  
>/rest/events?id={ }&userId={ }  
#### Method GET - получить историю пользователя, пример запроса
`/rest/events?userId=10000`
 