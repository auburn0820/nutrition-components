# nutrition-components

## 프로젝트 개요

### 실행방법

```bash
./gradlew jibDockerBuild
docker-compose up
```

### SwaggerUI

```
localhost:8080/api-docs
```

### 참고 REST API 설계 방법론

https://cloud.google.com/apis/design?hl=ko

# Nutrient API Documentation

## Base URL

```bash
/api/v1.0/nutrients
```

## Endpoints

### 1. Create Nutrient

**POST** `/api/v1.0/nutrients`

### Request

- **Body Parameters**

```json
{
  "food_code": "string",
  "group_name": "string",
  "food_name": "string",
  "research_year": "string",
  "maker_name": "string",
  "reference_name": "string",
  "serving_size": "number",
  "calorie": "number",
  "carbohydrate": "number",
  "protein": "number",
  "fat": "number",
  "sugars": "number",
  "sodium": "number",
  "cholesterol": "number",
  "saturated_fatty_acids": "number",
  "trans_fat": "number"
}
```

### Response

- **Status**:
    - `201 Created`
- **Body**

```json
{
  "code": "number",
  "message": "string",
  "data": {
    "food_code": "string",
    "group_name": "string",
    "food_name": "string",
    "research_year": "string",
    "maker_name": "string",
    "reference_name": "string",
    "serving_size": "number",
    "calorie": "number",
    "carbohydrate": "number",
    "protein": "number",
    "fat": "number",
    "sugars": "number",
    "sodium": "number",
    "cholesterol": "number",
    "saturated_fatty_acids": "number",
    "trans_fat": "number"
  }
}
```

### 2. Get Nutrient by ID

**GET** `/api/v1.0/nutrients/{id}`

### Request

- **Path Parameters**
    - `id`: The ID of the nutrient to retrieve

### Response

- **Status**:
    - `200 OK`
    - `404 NOT FOUND` : When can’t find nutrient by id
- **Body**

```json
{
  "code": "number",
  "message": "string",
  "data": {
    "food_code": "string",
    "group_name": "string",
    "food_name": "string",
    "research_year": "string",
    "maker_name": "string",
    "reference_name": "string",
    "serving_size": "number",
    "calorie": "number",
    "carbohydrate": "number",
    "protein": "number",
    "fat": "number",
    "sugars": "number",
    "sodium": "number",
    "cholesterol": "number",
    "saturated_fatty_acids": "number",
    "trans_fat": "number"
  }
}

```

### 3. Get All Nutrients

**GET** `/api/v1.0/nutrients`

### Request

- **Query Parameters**
    - `page`: Page number
    - `size`: Page size
    - `sort`: Sort by field
    - `food_name`: (Optional) Filter by food name
    - `research_year`: (Optional) Filter by research year
    - `maker_name`: (Optional) Filter by maker name
    - `food_code`: (Optional) Filter by food code

### Response

- **Status**:
    - `200 OK`
- **Body**

```json
{
  "code": "number",
  "message": "string",
  "data": {
    "content": [
      {
        "food_code": "string",
        "group_name": "string",
        "food_name": "string",
        "research_year": "string",
        "maker_name": "string",
        "reference_name": "string",
        "serving_size": "number",
        "calorie": "number",
        "carbohydrate": "number",
        "protein": "number",
        "fat": "number",
        "sugars": "number",
        "sodium": "number",
        "cholesterol": "number",
        "saturated_fatty_acids": "number",
        "trans_fat": "number"
      }
    ],
    "pageable": {
      "sort": {
        "sorted": "boolean",
        "unsorted": "boolean",
        "empty": "boolean"
      },
      "page_number": "number",
      "page_size": "number",
      "offset": "number",
      "unpaged": "boolean",
      "paged": "boolean"
    },
    "total_pages": "number",
    "total_elements": "number",
    "last": "boolean",
    "size": "number",
    "number": "number",
    "sort": {
      "sorted": "boolean",
      "unsorted": "boolean",
      "empty": "boolean"
    },
    "first": "boolean",
    "number_of_elements": "number",
    "empty": "boolean"
  }
}

```

### 4. Update Nutrient

**PUT** `/api/v1.0/nutrients/{id}`

### Request

- **Path Parameters**
    - `id`: The ID of the nutrient to update
- **Body Parameters**

```json
{
  "food_code": "string",
  "group_name": "string",
  "food_name": "string",
  "research_year": "string",
  "maker_name": "string",
  "reference_name": "string",
  "serving_size": "number",
  "calorie": "number",
  "carbohydrate": "number",
  "protein": "number",
  "fat": "number",
  "sugars": "number",
  "sodium": "number",
  "cholesterol": "number",
  "saturated_fatty_acids": "number",
  "trans_fat": "number"
}
```

### Response

- **Status**:
    - `200 OK`
    - `404 NOT FOUND` : When can’t find nutrient by id
- **Body**

```json
{
  "code": "number",
  "message": "string",
  "data": {
    "food_code": "string",
    "group_name": "string",
    "food_name": "string",
    "research_year": "string",
    "maker_name": "string",
    "reference_name": "string",
    "serving_size": "number",
    "calorie": "number",
    "carbohydrate": "number",
    "protein": "number",
    "fat": "number",
    "sugars": "number",
    "sodium": "number",
    "cholesterol": "number",
    "saturated_fatty_acids": "number",
    "trans_fat": "number"
  }
}

```

### 5. Delete Nutrient

**DELETE** `/api/v1.0/nutrients/{id}`

### Request

- **Path Parameters**
    - `id`: The ID of the nutrient to delete

### Response

- **Status**:
    - `204 NO CONTENT`
    - `404 NOT FOUND` : When can’t find nutrient by id
- **Body**

```json
{
  "code": "number",
  "message": "string"
}
```