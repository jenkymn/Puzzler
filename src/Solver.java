
import demo.Puzzler;

public class Solver {
    protected static final int SIZE = Puzzler.SIZE;

    //避免生成该类实例
    protected Solver() {
    }

    public static boolean solve(Puzzler p) {
        int[][] num = new int[SIZE][SIZE];
        boolean[][] rFlags = new boolean[SIZE][SIZE + 1],
                cFlags = new boolean[SIZE][SIZE + 1],
                zFlags = new boolean[SIZE][SIZE + 1];
        for (int r = 0; r < SIZE; r++)
            for (int c = 0; c < SIZE; c++)
                if (p.isFixed(r, c)) {
                    int t = p.getNumber(r, c);
                    num[r][c] = t;
                    rFlags[r][t] = true;
                    cFlags[c][t] = true;
                    zFlags[r / 3 * 3 + c / 3][t] = true;
                }

        int r = 0, c = 0;

        outLoop:
        for (; ; ) {//&#
            if (p.isFixed(r, c)) {
                c++;
                if (c >= SIZE) {
                    c = 0;
                    r++;
                    if (r >= SIZE) break outLoop;
                }
                continue outLoop;
            } //&#if(p.isFixed())
            int t = SIZE;
            for (c++; ; ) {//&#
                if (t >= SIZE) {
                    c--;
                    if (c < 0) {
                        c = SIZE - 1;
                        r--;
                        if (r < 0) break outLoop;
                    }
                    if (p.isFixed(r, c)) continue;
                    t = num[r][c];
                    if (t != 0) {
                        rFlags[r][t] = false;
                        cFlags[c][t] = false;
                        zFlags[r / 3 * 3 + c / 3][t] = false;
                        num[r][c] = 0;
                    }
                } else {
                    t++;
                    if (!(rFlags[r][t] ||
                            cFlags[c][t] ||
                            zFlags[r / 3 * 3 + c / 3][t])
                    ) break;
                }
            }//&#for(c++;;);
            num[r][c] = t;
            rFlags[r][t] = true;
            cFlags[c][t] = true;
            zFlags[r / 3 * 3 + c / 3][t] = true;
            c++;
            if (c >= SIZE) {
                c = 0;
                r++;
                if (r >= SIZE) break outLoop;
            }
        }
        if (r < 0) return false;
        for (r = 0; r < SIZE; r++)
            for (c = 0; c < SIZE; c++)
                if (!p.isFixed(r, c)) p.setNumber(r, c, num[r][c]);
        return true;
    }

    //test
    public static void main(String[] args) {
        Puzzler p = new Puzzler();
        int[][] data = {
                {0, 0, 8, 0, 0, 0, 2, 0, 0},
                {0, 3, 0, 8, 0, 2, 0, 6, 0},
                {7, 0, 0, 0, 9, 0, 0, 0, 5},

                {0, 5, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 4, 0, 0, 0, 6, 0, 0},
                {0, 2, 0, 0, 0, 0, 0, 7, 0},

                {4, 0, 0, 0, 8, 0, 0, 0, 6},
                {0, 7, 0, 1, 0, 3, 0, 9, 0},
                {0, 0, 1, 0, 0, 0, 8, 0, 0}
        };
        p.setPuzzler(data);
        System.out.println(solve(p));
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                System.out.print(p.getNumber(r, c) + "  ");
            }
            System.out.println();
        }
    }
}
