package Script;

import Transportation.Bus;
import Transportation.Taxi;
import Transportation.Walk;

public class ScriptEnding extends Script {
    private final String[] ending_first = new String[] {
            line,
            letter,
            "               ✦ Ending ✦",
            "      당신은 제시간에 부트캠프에 도착했습니다.",
            "               축하드립니다!\n",
    };
    private final String[] ending_last = new String[] {
            letter,
            line
    };

    public void ending(Walk walk) {
        typing(ending_first);
        System.out.println("\t\t" + walk.getDuration());
        System.out.println("\t\t" + walk.getCharge() + "\n");
        typing(ending_last);
    }

    public void ending(Bus bus) {
        typing(ending_first);
        System.out.println("\t\t" + bus.getDuration());
        System.out.println("\t\t" + bus.getCharge() + "\n");
        typing(ending_last);
    }

    public void ending(Taxi taxi) {
        typing(ending_first);
        System.out.println("\t\t" + taxi.getDuration());
        System.out.println("\t\t" + taxi.getCharge() + "\n");
        typing(ending_last);
    }
}
