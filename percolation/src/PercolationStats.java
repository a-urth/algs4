
public class PercolationStats {

    private double[] results;

    public PercolationStats(int N, int T) {
        this.results = new double[T];
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < T; i++) {
            Percolation p = new Percolation(N);
            int openedNumber = 0;
            while (!p.percolates()) {
                int x = StdRandom.uniform(1, N+1);
                int y = StdRandom.uniform(1, N+1);
                if (!p.isOpen(x, y)) {
                    openedNumber++;
                    p.open(x, y);
                }
            }
            this.results[i] = (double) openedNumber/(N*N);
        }
    }

    public double mean() {
        return StdStats.mean(this.results);
    }

    public double stddev() {
        return StdStats.stddev(this.results);
    }

    public double confidenceLo() {
        return this.mean() - (1.96 * this.stddev()/Math.sqrt(this.results.length));
    }

    public double confidenceHi() {
        return this.mean() + (1.96 * this.stddev()/Math.sqrt(this.results.length));
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException();
        }
        PercolationStats p = new PercolationStats(Integer.parseInt(args[0]),
            Integer.parseInt(args[1]));
        System.out.printf("mean                    = %g\n", p.mean());
        System.out.printf("stddev                  = %g\n", p.stddev());
        System.out.printf("95%% confidence interval = %g, %g\n",
            p.confidenceLo(), p.confidenceHi());
    }
}