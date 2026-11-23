# Singleton 패턴 구현 (Java)

초급프로젝트 일반 분반 11주차 실습 과제

## 1. 언어 및 실행 환경

| 항목 | 내용 |
|------|------|
| 언어 | Java |
| JDK 버전 | OpenJDK 26 |
| OS | Windows 11 |
| 실행 환경 | 단일 프로세스 · 단일 스레드 |

## 2. 프로젝트 구조

```
singleton-java/
├── src/
│   ├── Singleton.java   # Singleton 클래스 구현
│   └── Main.java        # 동일 인스턴스 / 상태 공유 확인용 실행 코드
├── .gitignore
└── README.md
```

## 3. 실행 방법

프로젝트 루트(`singleton-java/`)에서 아래 명령어를 실행합니다.

```bash
# 컴파일
javac -encoding UTF-8 -d out src/*.java

# 실행
java -cp out Main
```

## 4. 구현 내용

```java
public class Singleton {
    private static Singleton instance;   // 유일한 인스턴스 보관
    private int counter;                 // 공유 상태

    private Singleton() { ... }          // 외부에서 new 금지

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();  // 최초 1회만 생성
        }
        return instance;
    }
}
```

- `private` 생성자로 외부에서 `new Singleton()`을 호출하면 컴파일 에러가 발생합니다.
- 인스턴스는 오직 `getInstance()`를 통해서만 얻을 수 있습니다.

## 5. 실행 결과

```
===== 1. 동일한 인스턴스인지 확인 =====
[Singleton] 인스턴스가 생성되었습니다.
first  해시코드: 1482968390
second 해시코드: 1482968390
first == second : true

===== 2. 상태가 공유되는지 확인 =====
변경 전 second.getCounter() : 0
first.increment() 3회 호출
first.getCounter()  : 3
second.getCounter() : 3
상태 공유 여부 : true
```

> 해시코드 값은 실행할 때마다 달라질 수 있지만, 두 변수의 값은 항상 같습니다.

## 6. 설명

인스턴스는 `Singleton` 클래스의 `private static` 필드인 `instance`에 보관되며, static 필드라서 객체가 아닌 클래스 자체에 하나만 존재합니다.
`getInstance()`는 `instance`가 `null`일 때만 객체를 생성하고, 그 이후에는 이미 저장된 객체를 그대로 반환하기 때문에 반복해서 요청해도 새 객체가 만들어지지 않습니다. 실제로 실행 결과에서 "인스턴스가 생성되었습니다" 메시지가 두 번 호출했는데도 한 번만 출력됩니다.
또한 생성자가 `private`이라 클래스 외부에서는 `new`로 다른 인스턴스를 만들 방법이 없습니다.
두 변수 `first`와 `second`를 `==`로 비교한 결과가 `true`이고 `System.identityHashCode` 값도 같으므로, 두 변수가 같은 객체의 주소를 가리키고 있음을 알 수 있습니다.
마지막으로 `first`로 카운터를 3 증가시킨 뒤 `second`로 읽어도 3이 나오는 것으로, 하나의 인스턴스를 공유한다는 것을 다시 확인할 수 있습니다.
