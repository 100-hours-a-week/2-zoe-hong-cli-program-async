package TaxiCall;

import java.util.concurrent.atomic.AtomicInteger;

public class TaxiThread implements Runnable {
    public enum taxiState {
        UNABLE(0), // 호출 불가
        ABLE(1),   // 호출 가능
        OFF(-1)   // 퇴근 상태
        ;

        private final int num;
        taxiState(int num) {
            this.num = num;
        }
        public int getNum() { return num; }
    }
    public final static int NO_CUSTOMER = -1;
    public final static int WAITING_COUNT = 5;
    private final AtomicInteger currentState = new AtomicInteger(taxiState.UNABLE.getNum());
    private final AtomicInteger customerId = new AtomicInteger(NO_CUSTOMER);
    private int drivingTime = NO_CUSTOMER;
    private int workCount;

    public TaxiThread(int workCount) {
        // 퇴근 전까지 승객을 태울 횟수
        this.workCount = workCount;
    }

    public void run() {
        while (currentState.get() != taxiState.OFF.getNum()) {
            callingTaxi();
            if (currentState.get() == taxiState.OFF.getNum()) { break; }

            drivingTaxi();
            if (workCount <= 0) {
                currentState.set(TaxiThread.taxiState.OFF.getNum());
            }
        }
    }

    private void callingTaxi() {
        // 대기 상태
        // 1. 호출 시도가 들어올 때까지
        // 2. 일정 대기 시간이 지나기 전까지
        int waitFlag = WAITING_COUNT;
        while (customerId.get() == NO_CUSTOMER && currentState.get() != taxiState.OFF.getNum()) {
            if (currentState.get() == taxiState.UNABLE.getNum()) {
                currentState.set(taxiState.ABLE.getNum());
            }
            waitFlag--;
            if (waitFlag <= 0) {
                currentState.set(taxiState.OFF.getNum());
                System.out.println("🚕💨 택시가 손님을 찾지 못했습니다. 업무를 종료합니다.");
            }
            sleep();
        }
    }

    private void drivingTaxi() {
        // 운전(UNABLE): 4초 동안
        // 운전이 완료하면 탑승 카운트를 감소한다.
        while (customerId.get() != NO_CUSTOMER) {
            if (drivingTime < 3) {
                System.out.println("🚖[" + customerId + ": 승차] 운전 중입니다.");
                drivingTime++;
                sleep();
            } else {
                // 운전 완료 후 초기화
                System.out.println("🚖[" + customerId + ": 하차] 운전 완료!");
                drivingTime = NO_CUSTOMER;
                customerId.set(NO_CUSTOMER);
                workCount--;
            }
        }
    }

    public int getCurrentState() {
        return currentState.get();
    }
    public void setCurrentState(int currentState) {
        this.currentState.set(currentState);
    }
    public int getCustomerId() {
        return customerId.get();
    }
    public void setCustomerId(int customerId) {
        this.customerId.set(customerId);
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
