/**
 * Created by harry on 24/03/2018.
 */

public class StopWatch {
    private long start;

    public StopWatch() {
        start = System.currentTimeMillis();
    }

    public long elapsedTime() {
        return System.currentTimeMillis() - start;
    }
}
