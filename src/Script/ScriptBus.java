package Script;
import Transportation.Bus;

import java.util.InputMismatchException;

public class ScriptBus extends Script {
    public Bus takeBus(Bus[] bus) {
        typing(bustext1);
        while (true) {
            try {
                typing(bustext2);
                busType(bus);
                // 버스 검색 1/2/3
                int busNumber = inputCheckBus() - 1;
                busInfo(bus[busNumber]);
                // 1.버스 재검색 2.버스 결정
                if (inputChooseBus() == 2) { return bus[busNumber]; }
            } catch (InputMismatchException e) {
                typing(bustext2);
                busType(bus);
            }
        }
    }

    private void busType(Bus[] bus) {
        String[] str = new String[3];
        for (int i = 0; i < 3; i++) {
            str[i] = "\t" + (i + 1) + "." + bus[i].getInfo();
        }
        typing(str);
    }

    private void busInfo(Bus bus) {
        String[] info = new String[8];
        info[0] = line;
        info[1] = "\t✦ " + bus.getInfo() + "\n";
        info[2] = "\t  " + bus.getDuration();
        info[3] = "\t  " + bus.getCharge();
        info[4] = "\t  " + bus.getRideStop();
        info[5] = "\t  " + bus.getDropStop() + "\n";
        info[6] = "\t  1. 이전 질문으로 돌아간다.";
        info[7] = "\t  2. 이 버스를 탈 것이다.";

        typing(info);
    }

    private int inputCheckBus() {
        return inputNumber(3);
    }

    private int inputChooseBus() {
        return inputNumber(2);
    }

    private final String[] bustext1 = new String[] {
            line,
            "\t✦ 노선도를 보니 별로 멀지 않네.",
            "\t  당신은 버스를 타기로 결정했습니다.\n",
    };

    private final String[] bustext2 = new String[]{
            line,
            "\t✦ 유스케이스 건물까지 가는 버스는 많습니다.",
            "\t  버스 정보를 확인하세요.\n",
    };
}