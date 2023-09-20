public class Polynomial {
    private double[] coefficients;

    // Constructor to set the Polynomial to zero
    public Polynomial() {
        this.coefficients = new double[0];
    }

    // Constructor to set the coefficients accordingly 
    public Polynomial(double[] givenCoefficients) {
        this.coefficients = givenCoefficients;
    }

    // Method to add two polynomials
    public Polynomial add(Polynomial b) {
        
        /* find the lengths of both Polynomials and assign the greater number to MaxLength Variable -
        this will allow for the length of the coefficient array to be set */

        int maxLength = Math.max(this.coefficients.length, b.coefficients.length);
        double[] resultCoefficients = new double[maxLength];

        for (int i = 0; i < this.coefficients.length; i++) {
            resultCoefficients[i] += this.coefficients[i];
        }

        for (int i = 0; i < b.coefficients.length; i++) {
            resultCoefficients[i] += b.coefficients[i];
        }

        return new Polynomial(resultCoefficients);
    }

    // Method to evaluate the polynomial for a given value of x
    public double evaluate(double x) {

        double result = 0.0;
        
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    // Method to check if a given value is a root of the polynomial
    public boolean hasRoot(double x) {
        return evaluate(x) == 0.0;
    }
}
