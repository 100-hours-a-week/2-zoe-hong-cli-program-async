package TaxiCall;

public class CustomerThread implements Runnable {
    TaxiThread taxiThread;
    private final int customerId;

    public CustomerThread(TaxiThread taxiThread, int customerId) {
        this.taxiThread = taxiThread;
        this.customerId = customerId;
    }

    public void run() {
        while (taxiThread.getCurrentState() != TaxiThread.taxiState.OFF.getNum() && taxiThread.getCustomerId() != customerId) {
            // 승객이 탑승에 성공하거나 호출하려던 택시가 퇴근하기 전까지
            // 호출을 시도한다.

            // 택시의 호출 가능 여부를 2초마다 반복하여 확인
            waitingTaxi();

            // 호출 가능시 호출 시도
            callingTaxi();
            sleep();
        }
        // 호출하려던 택시가 퇴근하였을 경우
        if (taxiThread.getCurrentState() == TaxiThread.taxiState.OFF.getNum()) {
            System.out.println("❌[" + customerId + "] 택시 호출을 포기합니다.");
        }
    }

    private void waitingTaxi() {
        while (taxiThread.getCurrentState() == TaxiThread.taxiState.UNABLE.getNum() && taxiThread.getCurrentState() != TaxiThread.taxiState.OFF.getNum()) {
            System.out.println("📞["+ customerId + ": 호출 불가] 택시를 기다리는 중...");
            sleep();
        }
    }

    private void callingTaxi() {
        if (taxiThread.getCurrentState() == TaxiThread.taxiState.ABLE.getNum() && taxiThread.getCurrentState() != TaxiThread.taxiState.OFF.getNum()) {
            System.out.println("📞[" + customerId + ": 호출 가능] 택시를 호출할 수 있습니다.");

            // 호출 시도: 내 아이디를 택시의 승객 번호에 등록한다.
            if (taxiThread.getCustomerId() == TaxiThread.NO_CUSTOMER) {
                taxiThread.setCustomerId(customerId);
            }

            // 호출 성공 여부: 내 아이디가 택시의 승객 번호로 등록되었는가
            if (taxiThread.getCustomerId() == customerId) {
                // 호출 성공
                System.out.println("🙋‍♀️[" + customerId + "] 택시를 호출하였습니다!");
                taxiThread.setCurrentState(TaxiThread.taxiState.UNABLE.getNum());
            } else {
                // 호출 실패
                System.out.println("🔄[" + customerId + "] 택시 호출에 실패하였습니다.");
            }
        }
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
