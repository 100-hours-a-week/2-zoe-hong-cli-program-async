package TaxiCall;

public class TaxiCall {
    public final static int customer1_Id = 1;
    public final static int customer2_Id = 2;
    public final static int myId = 0;
    public TaxiCall() {}

    public void TaxiCallRace() {
        TaxiThread taxiThread = new TaxiThread(2);
        Thread taxi = new Thread(taxiThread);

        CustomerThread customer1Thread = new CustomerThread(taxiThread, customer1_Id);
        Thread customer1 = new Thread(customer1Thread);

        CustomerThread customer2Thread = new CustomerThread(taxiThread, customer2_Id);
        Thread customer2 = new Thread(customer2Thread);

        CustomerThread myThread = new CustomerThread(taxiThread, myId);
        Thread my = new Thread(myThread);

        customer1.start();
        customer2.start();
        my.start();
        taxi.start();

        // 이후 타이핑될 스크립트와의 동기성 유지
        try {
            customer1.join();
            customer2.join();
            my.join();
            taxi.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 메모리 해제
        customer1.interrupt();
        customer2.interrupt();
        my.interrupt();
        taxi.interrupt();
    }
}
