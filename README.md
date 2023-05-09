# 요구사항

- [ ] 노선에 역 등록 API 신규 구현
- [ ] 노선에 역 제거 API 신규 구현
- [ ] 노선 조회 API 수정
    - 노선에 포함된 역을 순서대로 보여주도록 응답을 개선합니다.
- [ ] 노선 목록 조회 API 수정
    - 노선에 포함된 역을 순서대로 보여주도록 응답을 개선합니다.

# 용어 사전

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 노선 | line | 구간의 모음, 이름을 가진다 |
| 역 | station | 이름을 가진다 |
| 구간 | section | 출발역(origin) : 기존에 있는 역<br>거리(distance) : 두 역 사이의 거리<br>도착역(destination) : 추가할 역 |
| 출발역 | origin | 기존에 있는 역 |
| 도착역 | destination | 추가할 역 |
| 거리 | distance | 두 역 사이의 거리 |
| 방향 | direction | UP : (상행) → (하행) / 도착역 → 출발역 / 상행방향<br>DOWN : (상행) ← (하행) / 출발역 → 도착역 / 하행방향 |

# 기능목록

## 노선에 역을 등록

- [ ] 역 최초 등록시, 두 역을 동시에 등록해야 한다.
- [ ] 역 등록시 출발역, 도착역, 거리 정보를 입력한다.
    - [ ] 출발역과 도착역은 다른 역이어야 한다.
    - [ ] 거리는 양의 정수이다.
        - [ ] 방향이가 UP일 시 `출발역 -> 도착역` 순이다.
        - [ ] 방향이 DOWN일 시 `도착역 -> 출발역` 순이다.
    - [ ] 해당 노선에 대한 구간 정보가 이미 존재할 경우
        - [ ] 출발역은 해당 노선에 등록된 역이어야 한다.
        - [ ] 도착역은 해당 노선에 등록되지 않은 역이어야 한다.
    - [ ] 이미 존재하는 구간 사이에 역을 추가할 수 있다. == 노선 가운데 역이 등록 될 경우
        - [ ] 거리 정보를 고려해야 한다.
            - [ ] 입력된 거리는 기존 구간의 거리보다 작아야한다.
            - [ ] 거리는 양의 정수이다.
- [ ] 하나의 역은 여러 노선에 등록될 수 있다.

## 노선에 역을 제거

- [ ] 노선에 등록된 역이 2개일 경우, 하나의 역 제거시 두 역 모두 제거된다.
- [ ] 중간 역이 제거될 경우, 양 옆 역의 거리를 합해준다.

# API 명세서

| Method | URL | HttpStatus | Description |
| --- | --- | --- | --- |
| GET | /lines | 200 | 전체 노선 목록을 조회한다. |
| GET | /lines/{lineId} | 200 | 해당 노선을 조회한다. |
| POST | /lines | 201 | 노선을 생성한다. |
| PUT | /lines/{lineId} | 200 | 해당 노선 정보를 수정한다. |
| DELETE | /lines/{lineId} | 204 | 해당 노선을 삭제한다. |
| POST | /lines/{lineId}/stations | 201 | 해당 노선에 구간을 추가한다. |
| DELETE | /lines/{lineId}/stations/{stationId} | 204 | 해당 노선에서 해당 역을 삭제한다. |

## GET /lines

### Request

NONE

### Response

Body

```json
{
  "lines": [
    {
      "id": 1,
      "name": "2호선",
      "color": "GREEN",
      "stations": [
        {
          "id": 1,
          "name": "역삼역"
        },
        {
          "id": 2,
          "name": "삼성역"
        },
        {
          "id": 3,
          "name": "잠실역"
        }
      ]
    },
    {
      "id": 2,
      "name": "3호선",
      "color": "ORANGE",
      "stations": [
        {
          "id": 1,
          "name": "교대역"
        },
        {
          "id": 2,
          "name": "고속터미널역"
        },
        {
          "id": 3,
          "name": "신사역"
        }
      ]
    }
  ]
}
```

## GET /lines/{lineId}

### Request

NONE

### Response

Body

```json
{
  "id": 1,
  "name": "2호선",
  "color": "GREEN",
  "stations": [
    {
      "id": 1,
      "name": "역삼역"
    },
    {
      "id": 2,
      "name": "삼성역"
    },
    {
      "id": 3,
      "name": "잠실역"
    }
  ]
}
```

## POST /lines

### Request

BODY

```json
{
  "name": "2호선",
  "color": "GREEN"
}
```

### Response

BODY

```json
{
  "id": 1,
  "name": "2호선",
  "color": "GREEN"
}
```

## PUT /lines/{lineId}

### Request

BODY

```json
{
  "name": "2호선",
  "color": "GREEN"
}
```

### Response

NONE

## POST /lines/{lineId}/stations

### Request

Body

```json
{
  "origin": 1,
  "destination": 2,
  "distance": 15,
  "direction": "UP"
}
```

### Response

Body

```json
{
  "id": 1,
  "name": "2호선",
  "color": "GREEN",
  "stations": [
    {
      "id": 1,
      "name": "역삼역"
    },
    {
      "id": 2,
      "name": "삼성역"
    },
    {
      "id": 3,
      "name": "잠실역"
    }
  ]
}
```

## DELETE /lines/{lineId}

### Request

NONE

### Response

NONE

## DELETE /lines/{lineId}/stations/{stationId}

### Request

NONE

### Response

NONE

# DB 테이블 구조

```mermaid
erDiagram
    line ||--|{ section : line_id
    line {
        BIGINT id
        VARCHAR(50) name
        VARCHAR(50) color
        TIMESTAMP created_at
        TIMESTAMP updated_at
    }
    station ||--|{ section : origin_id
    station ||--|{ section : destination_id
    station {
        BIGINT id
        VARCHAR(50) name
        TIMESTAMP created_at
        TIMESTAMP updated_at
    }

    section {
        BIGINT id
        BIGINT line_id
        BIGINT origin_id
        BIGINT destination_id
        INT distance
        TIMESTAMP created_at
        TIMESTAMP updated_at
    }
```
