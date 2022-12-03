package ir.co.sadad.cheque.web.rest.errors;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public final class ErrorConstants {

    public static final String ERR_VALIDATION = "error.validation";
    public static final String PROBLEM_BASE_URL = "about:blank";
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL);
    public static final URI CONSTRAINT_VIOLATION_TYPE = URI.create(PROBLEM_BASE_URL);

    public static Map<Integer, String> sayadExceptions() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(110, "server.external.exception.sayad.110");
        map.put(410, "server.external.exception.sayad.410");
        map.put(411, "server.external.exception.sayad.411");
        map.put(412, "server.external.exception.sayad.412");
        map.put(417, "server.external.exception.sayad.417");
        map.put(418, "server.external.exception.sayad.418");
        map.put(419, "server.external.exception.sayad.419");
        map.put(453, "server.external.exception.sayad.453");
        map.put(454, "server.external.exception.sayad.454");
        map.put(460, "server.external.exception.sayad.460");
        map.put(461, "server.external.exception.sayad.461");
        map.put(469, "server.external.exception.sayad.469");
        map.put(491, "server.external.exception.sayad.491");
        map.put(492, "server.external.exception.sayad.492");

        map.put(426, "server.external.exception.sayad.426");
        map.put(427, "server.external.exception.sayad.427");
        map.put(428, "server.external.exception.sayad.428");
        return map;
    }


    private ErrorConstants() {
    }
}
