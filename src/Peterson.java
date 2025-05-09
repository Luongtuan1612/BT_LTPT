public class Peterson implements Lock {
    private volatile boolean[] flag = new boolean[2];
    private volatile int turn;

    @Override
    public void requestCS(int i) {
        int other = 1 - i;
        flag[i] = true;
        turn = other;
        while (flag[other] && turn == other) {
            // busy wait
        }
    }

    @Override
    public void releaseCS(int i) {
        flag[i] = false;
    }
}
