# Cholecystitis microservice
3 components spring boot based microservice for handling medical data
Microservice consist of following components: microservice itself, config server, PostgreSQL database.

> * Localisation
> * HATEOAS
## Commands:
* [POST] Patient x - Create new record 
```
localhost:8080/<ClinicName>/
```
* [GET]  GetRecordById - Find report by record id
```
localhost:8080/<ClinicName>/record/<RecordID>
```
* [PUT]  Edit - edit selected record
```
localhost:8080/<ClinicName>/edit/<RecordID>
```
Body have to contain json with desired field and new value.
\
Example:
```
{
    "field": "WBC",
    "value": "5.6"
}
```
* [DELETE]  RemoveById - delete record by its id
```
localhost:8080/<ClinicName>/delete/<RecordID>
```

## Language support
English, lithuanian and russian languages are supported. Language can be selected by header attribute __Accept-Language__ with __en__, __lt__ and __rus__ values respectivly.

## Microservice health check
Microservice has several actuator's open endpoints to check CPU and environment status.
