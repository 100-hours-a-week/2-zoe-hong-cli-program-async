package TaxiCall;

import java.util.concurrent.atomic.AtomicInteger;

public class TaxiThread implements Runnable {
    public interface taxiState {
        int UNABLE = 0; // 호출 불가
        int ABLE = 1;   // 호출 가능
        int OFF = -1;   // 퇴근 상태
    }
    public final static int NO_CUSTOMER = -1;
    private final AtomicInteger currentState = new AtomicInteger(taxiState.UNABLE);
    private final AtomicInteger customerId = new AtomicInteger(NO_CUSTOMER);
    private int drivingTime = NO_CUSTOMER;
    private int workCount;

    public TaxiThread(int workCount) {
        // 퇴근 전까지 승객을 태울 횟수
        this.workCount = workCount;
    }

    public void run() {
        while (workCount > 0) {
            callingTaxi();
            drivingTaxi();
        }
        // 퇴근
        currentState.set(TaxiThread.taxiState.OFF);
    }

    private void callingTaxi() {
        // 대기 상태: 호출 시도가 들어올 때까지
        while (customerId.get() == NO_CUSTOMER) {
            if (currentState.get() == taxiState.UNABLE) {
                currentState.set(taxiState.ABLE);
            }
        }
    }

    private void drivingTaxi() {
        // 운전(UNABLE): 4초 동안
        // 운전이 완료하면 탑승 카운트를 감소한다.
        while (customerId.get() != NO_CUSTOMER) {
            if (drivingTime < 4) {
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
