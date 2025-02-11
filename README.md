# 2-KTB: cli-program-async
[판교역에서 카카오테크 부트캠프 장소로 이동하는 시뮬레이션의 CLI 동기 프로그램](https://github.com/chulsu0012/2-ktb-cli-program)을 비동기 프로그램으로 변환하기

**주제 설정:** 택시를 호출하려고 할 때, 다른 고객 스레드와 경쟁한다.<br/>
**개발 환경:** MacOS, IntelliJ, JDK 21

```
1. 간단한 스레드 구현
2. 스레드 간 상호작용 할 수 있는 기능 구현
```

<br/>

## 프로젝트 파일 트리
```
2-ktb-cli-program-async
├─ src
│  ├─ Main.java
│  ├─ Script
│  │  ├─ Script.java
│  │  ├─ ScriptBus.java
│  │  ├─ ScriptChoice1.java
│  │  ├─ ScriptChoice2.java
│  │  ├─ ScriptEnding.java
│  │  └─ ScriptOpening.java
│  ├─ TaxiCall
│  │  ├─ CustomerThread.java
│  │  ├─ TaxiCall.java
│  │  └─ TaxiThread.java
│  └─ Transportation
│     ├─ Bus.java
│     ├─ Taxi.java
│     ├─ Transportation.java
│     ├─ Vehicle.java
│     └─ Walk.java
└─ README.md
```

#### 추가된 클래스
- TaxiThread: 택시 스레드
  - 상태: 호출 불가 / 호출 가능 / 퇴근
  - 호출에 성공하면 즉시 승객 앞에 도착하고 목적지까지 이동하는 데 4초 소요된다.
  - 목표 업무량(승객 탑승 횟수)을 달성하면 퇴근한다.
- CustomerThread: 승객 스레드
  - 호출에 성공할 때까지 2초마다 택시의 호출 가능 여부를 확인하고 호출을 시도한다.
  - 택시가 퇴근하면 호출을 포기한다.
- TaxiCall
  - 택시 스레드 1대를 두고 승객 스레드 3명이 경쟁한다.
  - 택시의 목표 업무량은 2번이다.


#### 수정된 클래스
- Main: 택시를 선택할 경우 택시 및 승객 스레드 간 상호작용을 시작한다.

<br/>

## 설계 시나리오
```bash
# 택시 상태 확인
📞[customerId: 호출 불가] 택시를 기다리는 중...
📞[customerId: 호출 가능] 택시를 호출할 수 있습니다.
----
# 이겼을 경우
🙋‍♀️[customerId] 택시 호출하였습니다!
🚖[customerId: 승차] 운전 중입니다.
🚖[customerId: 하차] 운전 완료!

# 졌을 경우
🔄[customerId] 택시 호출에 실패하였습니다.
📞[customerId: 호출 불가] 택시를 기다리는 중...
----
# 택시가 퇴근했을 경우
❌[customerId] 택시 호출을 포기합니다.
```

<br/>

## 프로그램 시연
택시를 탑승하기로 선택한 시점부터
![시연 영상](https://github.com/user-attachments/assets/aa35ffd3-6b25-4374-8cf0-7a2cbbca19b2)
