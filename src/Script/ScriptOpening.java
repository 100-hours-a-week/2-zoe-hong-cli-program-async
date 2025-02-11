package Script;

public class ScriptOpening extends Script {
    private final String[] opening = new String[] {
            line,
            letter,
            "                판교역에서",
            "               부트캠프까지",
            "               어떻게 가지?\n",
            letter,
            line,
            "\t✦ 당신은 카카오테크 부트캠프 OT에 가기 위해",
            "\t  판교역에서 내렸습니다.\n",
            "\t✦ 그러나 아뿔싸!",
            "\t  출근 시간대와 겹쳐 거리가 너무나 혼잡합니다.\n",
            "\t✦ 무사히 유스케이스 건물에 도착할 수 있을까요?\n",
            line,
    };

    public void typingOpening() {
        typing(opening);
    }


}
