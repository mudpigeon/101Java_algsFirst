package lib.other;

import java.lang.Math;

public class Rational {
    private int nume, deno;

    public Rational(int numerator, int denominator) {
        int factor_num = factor(numerator, denominator);
        
        this.nume = numerator / factor_num;
        this.deno = denominator / factor_num;
    }

    public Rational plus(Rational b) {
        return new Rational(this.nume * b.deno + this.deno * b.nume, this.deno * b.deno);
    }

    public Rational minus(Rational b) {
        return new Rational(this.nume * b.deno - this.deno * b.nume, this.deno * b.deno);
    }

    public Rational times(Rational b) {
        
        return new Rational(this.nume * b.nume, this.deno * b.deno);
    }

    public Rational divides(Rational b) {

        return new Rational(this.nume * b.deno, this.deno * b.nume);
    }

    public boolean equals(Rational b) {
        int x1 = this.nume;
        int y1 = this.deno;
        int x2 = b.nume;
        int y2 = b.deno;

        if (Math.abs(x1) != Math.abs(x2)) return false;
        if (Math.abs(y1) != Math.abs(y2)) return false;
        return x1 * y1 == x2 * y2;
    }

    @Override
    public String toString() {
        if (nume > 0) {
            if (deno > 0) {
                return nume + "/" + deno;
            } else {
                return "-" + nume + "/" + -deno;
            }
        } else {
            if (deno > 0) {
                return nume + "/" + deno;
            } else {
                return -nume + "/" + -deno;
            }
        }
        
    }

    public static int factor(int a, int b) {
        if (Math.abs(a) < Math.abs(b)) {
            return factor(b, a);
        } else {
            if (a % b != 0) {
                return factor(b, a % b);
            } else {
                return b;
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(factor(36, -150));

        System.out.println(new Rational(12, -42));

        var a = new Rational(-32, 5);
        var b = new Rational(-10, 14);
        System.out.println("a+b = " + a.plus(b).toString());
        System.out.println("a-b = " + a.minus(b).toString());
        System.out.println("a*b = " + a.times(b).toString());
        System.out.println("a/b = " + a.divides(b).toString());

        var x = new Rational(12, 15);
        var y = new Rational(12, -15);
        var z = new Rational(-12, 15);

        System.out.println(x.equals(y));
        System.out.println(x.equals(z));
        System.out.println(y.equals(z));
    }
}
