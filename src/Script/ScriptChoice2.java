package Script;

public class ScriptChoice2 extends Script {
    public int inputBusOrTaxi() {
        typing(choiceScript);
        return inputNumber(2);
    }

    public void typingTaxi() {
        typing(takeTaxi);
    }

    private final String[] choiceScript = new String[] {
            line,
            "\t✦ 길을 잃어버릴 일은 없을 거야.",
            "\t  당신은 대중교통을 이용하기로 결정했습니다.\n",
            line,
            "\t✦ 어떤 대중교통 수단을 이용할까요?",
            "\t  1. 버스",
            "\t  2. 택시",
    };

    private final String[] takeTaxi = new String[] {
            line,
            "\t✦ 사람들에게 치이지 않고 편하게 가고 싶어!",
            "\t  운 좋게 택시를 바로 잡을 수 있었습니다.\n",
    };
}
