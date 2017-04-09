package shuffle;

/**
 * Created by hieunv on 05/04/2017.
 */
public class Main {
    public static void main(String[] args) {
        final String url = "/home/hieunv/Desktop/CyberSpace/ConfessionsClassify/data";
        Shuffle shuffle = new Shuffle(url);
        shuffle.solve();
    }
}
