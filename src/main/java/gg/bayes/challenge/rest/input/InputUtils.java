package gg.bayes.challenge.rest.input;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class InputUtils {

    public static final String PREFIX = "npc_dota_hero_";
    public static String[] sanitizeInput(String[] inputs) {

        inputs = Arrays.stream(inputs).map(
                element -> element.contains(PREFIX)?StringUtils.substringAfter(element, "npc_dota_hero_"):element

        ).toArray(String[]::new);

        inputs[0] = StringUtils.substringAfter(inputs[0], "[");
        inputs[0] = StringUtils.substringBefore(inputs[0], "]");
        String[] times = inputs[0].split(":");
        long milliseconds;
        milliseconds = Integer.valueOf(times[0]) * 60 * 60 * 1000;
        milliseconds += Integer.valueOf(times[1]) * 60 * 1000;
        milliseconds += Integer.valueOf(StringUtils.substringBefore(times[2], ".")) * 1000;
        milliseconds += Integer.valueOf(StringUtils.substringAfter(times[2], "."));
        inputs[0] = String.valueOf(milliseconds);
        return inputs;
    }
}
