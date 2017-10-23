package nick.Algorithms4th.Fundamentals.DataAbstraction;

import edu.princeton.cs.algs4.StdOut;

public class Rational {

    private final long numerator;
    private final long denominator;

    /**
     * greatest common divisor
     * @param p
     * @param q
     */
    private static long gcd(long p, long q) {
        if (q == 0) return p;
        long r = p % q;
        return gcd(q, r);
    }

    public Rational(int numerator, int denominator) {
        if (denominator == 0)
            throw new RuntimeException("Illegal denominator: 0");
        long gcd = gcd(numerator, denominator);
        if (gcd != 0 && gcd != 1 && gcd != -1) {
            // numerator / gcd 为 long 型，会自动转换为 int 型再赋给 numerator
            numerator /= gcd;
            denominator /= gcd;
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Rational plus(Rational b) {
        long numerator, denominator;
        // (n1/d1) + (n2/d2) = (n1*d2 + n2*d1) / (d1*d2)
        /**
         * Rational 类的分子分母若使用 int 类型存储，
         * 当 this.numerator * b.denominator > Integer.MAX_VALUE 时，会导致运算结果溢出
         */
        numerator = this.numerator * b.denominator + b.numerator * this.denominator;
        denominator = this.denominator * b.denominator;
        long gcd = gcd(numerator, denominator);
        if (gcd != 0 && gcd != 1 && gcd != -1) {
            numerator /= gcd;
            denominator /= gcd;
        }
        // 使用断言来防止溢出
        assert numerator <= Integer.MAX_VALUE
                && numerator >= Integer.MIN_VALUE
                && denominator <= Integer.MAX_VALUE
                && numerator >= Integer.MIN_VALUE
                : "int overflow";
        return new Rational((int) numerator, (int) denominator);
    }

    public Rational minus(Rational b) {
        long numerator, denominator;
        // (n1/d1) - (n2/d2) = (n1*d2 - n2*d1) / (d1*d2)
        numerator = this.numerator * b.denominator - b.numerator * this.denominator;
        denominator = this.denominator * b.denominator;
        long gcd = gcd(numerator, denominator);
        if (gcd != 0 && gcd != 1 && gcd != -1) {
            numerator /= gcd;
            denominator /= gcd;
        }
        return new Rational((int) numerator, (int) denominator);
    }

    public Rational times(Rational b) {
        long numerator, denominator;
        // (n1/d1) * (n2/d2) = (n1*n2) / (d1*d2)
        numerator = this.numerator * b.numerator;
        denominator = this.denominator * b.denominator;
        long gcd = gcd(numerator, denominator);
        if (gcd != 0 && gcd != 1 && gcd != -1) {
            numerator /= gcd;
            denominator /= gcd;
        }
        return new Rational((int) numerator, (int) denominator);
    }

    public Rational devides(Rational b) {
        long numerator, denominator;
        // (n1/d1) / (n2/d2) = (n1*d2) / (n2*d1)
        numerator = this.numerator * b.denominator;
        denominator = this.denominator * b.numerator;
        long gcd = gcd(numerator, denominator);
        if (gcd != 0 && gcd != 1 && gcd != -1) {
            numerator /= gcd;
            denominator /= gcd;
        }
        return new Rational((int) numerator, (int) denominator);
    }

    public boolean equals(Object x) {
        if (this == x) return true;
        if (x == null) return false;
        if (this.getClass() != x.getClass()) return false;
        Rational that = (Rational) x;
        if (this.numerator == that.numerator && this.denominator == that.denominator)
            return true;
        return false;
    }

    public String toString() {
        // long 型除法的结果是商的整数部分，需要先转换为 double 类型，再进行除法
        return (double) numerator / (double) denominator + "";
    }

    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = 3;
        Rational ra = new Rational(a, b);
        Rational rb = new Rational(a, c);
        StdOut.printf("ra = %s\n", ra.toString());
        StdOut.printf("rb = %s\n", rb.toString());
        StdOut.printf("ra / rb = %s\n", ra.devides(rb));
        StdOut.printf("ra - rb = %s\n", ra.minus(rb));
        StdOut.printf("ra + rb = %s\n", ra.plus(rb));
        StdOut.printf("ra * rb = %s\n", ra.times(rb));
        StdOut.printf("ra = rb ? : %s\n", ra.equals(rb));

        a = Integer.MAX_VALUE - 1;
        b = Integer.MAX_VALUE;
        ra = new Rational(a, b);
        rb = new Rational(a, b);
        StdOut.printf("ra + rb = %s\n", ra.plus(rb)); // 此处会发生溢出，导致结果错误
    }

}
