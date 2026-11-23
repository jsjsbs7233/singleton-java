/**
 * Singleton 패턴 구현 클래스.
 * 프로그램 전체에서 단 하나의 인스턴스만 존재하도록 보장한다.
 */
public class Singleton {

    // 유일한 인스턴스를 보관하는 static 필드 (클래스 자체에 저장됨)
    private static Singleton instance;

    // 공유 상태 확인용 카운터
    private int counter;

    // private 생성자: 외부에서 new Singleton()으로 객체를 만들 수 없게 막는다.
    private Singleton() {
        this.counter = 0;
        System.out.println("[Singleton] 인스턴스가 생성되었습니다.");
    }

    // 인스턴스를 요청하는 유일한 통로
    // 처음 호출될 때만 생성하고, 이후에는 이미 만들어진 인스턴스를 그대로 반환한다.
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void increment() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}
