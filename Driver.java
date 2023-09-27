import java.io.*;

public class Driver {
    public static void main(String[] args) {

        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));

        // create polynomial objects
        double[] c1 = { 6, -2, 5 };
        int[] e1 = { 0, 1, 3 };
        Polynomial p1 = new Polynomial(c1, e1);
        double[] c2 = { 1, 3, -4, -2 };
        int[] e2 = { 1, 2, 3, 0 };
        Polynomial p2 = new Polynomial(c2, e2);

        // add two polynomials
        Polynomial s = p1.add(p2);
        System.out.println("Sum = " + s.evaluate(1));

        // multiply two polynomials
        Polynomial product = p1.multiply(p2);
        System.out.println("Product = " + product.evaluate(1));

        // new values to check hasRoot method
        double[] c3 = { 1, -2 };
        int[] e3 = { 1, 0 };
        Polynomial p3 = new Polynomial(c3, e3);
        double[] c4 = { 1, 4, -6, 8 };
        int[] e4 = { 3, 2, 1, 0 };
        Polynomial p4 = new Polynomial(c4, e4);

        Polynomial product2 = p3.multiply(p4);

        // check hasRoot method
        if (product2.hasRoot(2))
            System.out.println("2 is a root of s");
        else
            System.out.println("2 is not a root of s");

        // check from File and Save to File method
        try {
            Polynomial polynomialFromFile = new Polynomial(new File("polynomial.txt"));
            System.out.println("Polynomial from file: P(1) = " + polynomialFromFile.evaluate(1));

            polynomialFromFile.saveToFile("output.txt");
            System.out.println("Polynomial saved to output.txt");
        } catch (IOException e) {
            System.err.println("Error handling files: " + e.getMessage());
        }

    }

}