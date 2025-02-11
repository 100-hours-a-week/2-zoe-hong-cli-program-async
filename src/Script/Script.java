package Script;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Script {
    String line = "---------------------------------------------\n";
    String letter = "            ⭒❃.✮:▹　　◃:✮.❃⭒\n";

    void typing(String[] text) {
        // 타이핑 효과: 한 줄씩 시간 텀을 두고 끊어서 출력한다.
        try {
            for (String s : text) {
                System.out.println(s);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    int inputNumber(int num) {
        Scanner sc = new Scanner(System.in);
        int tmp;

        System.out.print("\t- 번호를 선택해 주세요(숫자만 입력): ");
        while (true) {
            try {
                tmp = sc.nextInt();
                if (tmp < 1 || tmp > num) {
                    throw new InputMismatchException();
                }
                System.out.println();
                break;
            } catch (InputMismatchException e) {
                System.out.print("\t- 번호를 선택해 주세요(숫자만 입력): ");
                sc.nextLine();
            }
        }
        return tmp;
    }
}