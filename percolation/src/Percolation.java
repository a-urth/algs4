
public class Percolation {
    private WeightedQuickUnionUF unionTree;
    private WeightedQuickUnionUF fullTree;
    private boolean[][] greed;
    private int N;
    private int top;
    private int bottom;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        this.top = 0;
        this.bottom = N*N + 1;
        this.greed = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                greed[i][j] = false;
            }
        }
        // +2 for top and bottom virtual cells
        this.unionTree = new WeightedQuickUnionUF(N*N + 2);
        // +1 for top virtual cell
        this.fullTree = new WeightedQuickUnionUF(N*N + 1);
        // Connecting top and bottom virtual cell with first and last rows
        for (int i = 0; i < N; i++) {
            this.unionTree.union(0, i+1);
            this.unionTree.union(N*N+1, N*N-i);

            this.fullTree.union(0, i+1);
        }
    }

    private int currentIndex(int i, int j) {
        return (i*this.N) + j + 1;
    }

    public boolean isOpen(int inI, int inJ) {
        return this.greed[inI-1][inJ-1];
    }

    public boolean isFull(int inI, int inJ) {
        int i = inI - 1;
        int j = inJ - 1;
        return this.greed[i][j] && this.fullTree.connected(
            this.currentIndex(i, j), this.top);
    }

    public void open(int inI, int inJ) {
        int i = inI - 1;
        int j = inJ - 1;
        if (this.greed[i][j]) {
            return;
        }

        this.greed[i][j] = true;
        int currentIndex = this.currentIndex(i, j);
        // Up
        if (i < this.N - 1 && this.greed[i+1][j]) {
            this.unionTree.union(currentIndex, currentIndex + this.N);
            this.fullTree.union(currentIndex, currentIndex + this.N);
        }
        // Down
        if (i > 0 && this.greed[i-1][j]) {
            this.unionTree.union(currentIndex, currentIndex - this.N);
            this.fullTree.union(currentIndex, currentIndex - this.N);
        }
        // Left
        if (j > 0 && this.greed[i][j-1]) {
            this.unionTree.union(currentIndex, currentIndex - 1);
            this.fullTree.union(currentIndex, currentIndex - 1);
        }
        // Right
        if (j < this.N - 1 && this.greed[i][j+1]) {
            this.unionTree.union(currentIndex, currentIndex + 1);
            this.fullTree.union(currentIndex, currentIndex + 1);
        }
    }

    public boolean percolates() {
        if (this.N == 1) {
            return this.isOpen(1, 1);
        }
        return this.unionTree.connected(this.top, this.bottom);
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Provide size of greed");
        }
        int N = Integer.parseInt(args[0]);
        Percolation p = new Percolation(N);
        In in = new In();
        while (!p.percolates()) {
            System.out.println("Press ENTER to step forward...");
            in.readLine();
            int i = StdRandom.uniform(1, N+1);
            int j = StdRandom.uniform(1, N+1);
            System.out.printf("%d, %d\n", i, j);
            p.open(i, j);
        }
    }
}
