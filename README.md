# Message web service

## Endpoint

| Method      | Path        | Body        | Usage    |
| ----------- | ----------- | ----------- | -----------|
| POST        | /message    | Message     | Stores messages in a repository for further processing. If a message id already exists in the repository, the associated message detail is updated with the data from the incoming message

## Validations

- The message id cannot be empty and must only contain positive numeric values
- The date fields must be formatted as ISO-8601 UTC date time
- The score and directors count are not currently validated, and will be stored as 0.0 and 0 in the repository, respectively if not included in the request payload

## Example valid request payload

```json
{
  "msg_id": "123456789",
  "company_name": "company name",
  "registration_date": "2020-10-27T14:34:06.132Z",
  "score": 12.0,
  "directors_count": 123,
  "last_updated": "2020-10-27T14:34:06.132Z"
}