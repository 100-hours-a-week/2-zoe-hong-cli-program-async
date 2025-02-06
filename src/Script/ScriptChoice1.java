package Script;

public class ScriptChoice1 extends Script {
    public int inputWalkOrVehicle() {
        typing(choiceScript);
        return inputNumber(2);
    }

    public void typingWalking() {
        typing(walking);
    }

    private final String[] choiceScript = new String[] {
            "\t✦ 버스는 미어터지고 길거리는 춥습니다.",
            "\t  걸어갈까요, 대중교통을 이용할까요?\n",
            "\t  1. 걸어간다.",
            "\t  2. 대중교통을 이용한다.",
    };

    private final String[] walking = new String[] {
            line,
            "\t✦ 추운 날이니까 거리는 한산하겠지!",
            "\t  당신은 걸어가기로 결정했습니다.\n",
    };
}