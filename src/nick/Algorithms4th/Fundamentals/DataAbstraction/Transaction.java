package nick.Algorithms4th.Fundamentals.DataAbstraction;

import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.StdOut;

public class Transaction {

    private final String who;
    private final Date when;
    private final double amount;

    public Transaction(String who, Date when, double amount) {
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    public Transaction(String s) {
        String[] parameters = s.split(" ");
        if (parameters.length != 3) throw new RuntimeException("Illegal parameter: " + s);
        this.who = parameters[0];
        this.when = new Date(parameters[1]);
        this.amount = Double.parseDouble(parameters[2]);
    }

    public String who() {
        return who;
    }

    public Date when() {
        return when;
    }

    public double amount() {
        return amount;
    }

    public String toString() {
        return who + " had a " + amount + " RMB deal at " + when;
    }

    public boolean equals(Object x) {
        // 使用 '==' 比较的是两个变量的引用是否相同
        if (this == x) return true;
        if (x == null) return false;
        if (this.getClass() != x.getClass()) return false;
        Transaction that = (Transaction) x;
        if (this.who.equals(that.who) &&
                this.when.equals(that.when) &&
                this.amount == that.amount) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Transaction t = new Transaction("Nick", new Date(10, 20, 2017), 10);
        StdOut.printf("The person of this transaction: %s\n", t.who());
        StdOut.printf("The date of this transaction: %s\n", t.when());
        StdOut.printf("The amount of this transaction: %f\n", t.amount());
        StdOut.println(t);
        t = new Transaction("Nick 10/20/2017 15");
        StdOut.println(t);
    }

}
