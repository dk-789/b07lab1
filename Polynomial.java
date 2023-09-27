import java.io.*;

public class Polynomial {
    private double[] coefficients;
    private int[] exponents;

    // Constructor to set the Polynomial to zero
    public Polynomial() {
        this.coefficients = new double[0];
        this.exponents = new int[0];
    }

    // Constructor to set the coefficients accordingly
    public Polynomial(double[] givenCoefficients, int[] givenExponents) {
        this.coefficients = givenCoefficients;
        this.exponents = givenExponents;
    }

    // Method to add two polynomials
    public Polynomial add(Polynomial b) {

        /*
         * find the lengths of both Polynomials and assign the greater number to
         * MaxLength Variable -
         * this will allow for the length of the coefficient array to be set
         */

        int maxLength = Math.max(this.coefficients.length, b.coefficients.length);
        int maxDegree = Math.max(this.exponents.length, b.exponents.length);

        // we can assume that length of Coefficient and Exponent array are the same
        double[] resultCoefficients = new double[maxLength];
        int[] resultExponents = new int[maxDegree];

        // first polynomial attributes add
        for (int i = 0; i < this.coefficients.length; i++) {

            int exp = this.exponents[i];
            resultCoefficients[exp] += this.coefficients[i];
            resultExponents[exp] = exp; // Store the exponent
        }

        for (int i = 0; i < b.coefficients.length; i++) {

            int exp = b.exponents[i];
            resultCoefficients[exp] += b.coefficients[i];
            resultExponents[exp] = exp; // Store the exponent
        }

        return new Polynomial(resultCoefficients, resultExponents);
    }

    // multiply method

    public Polynomial multiply(Polynomial b) {

        int maxLength = this.exponents.length + b.exponents.length;
        double[] resultCoefficients = new double[maxLength];
        int[] resultExponents = new int[maxLength];

        for (int i = 0; i < coefficients.length; i++) {
            for (int j = 0; j < b.coefficients.length; j++) {
                int exp = exponents[i] + b.exponents[j];
                resultCoefficients[exp] += coefficients[i] * b.coefficients[j];
                resultExponents[exp] = exp; // Store the resulting exponent
            }
        }

        // Create arrays with non-zero coefficients and corresponding exponents
        int finalSize = 0;
        for (int i = 0; i < maxLength; i++) {
            if (resultCoefficients[i] != 0.0) {
                finalSize++;
            }
        }

        double[] finalCoefficients = new double[finalSize];
        int[] finalExponents = new int[finalSize];

        int index = 0;
        for (int i = 0; i < maxLength; i++) {
            if (resultCoefficients[i] != 0.0) {
                finalCoefficients[index] = resultCoefficients[i];
                finalExponents[index] = resultExponents[i];
                index++;
            }
        }

        return new Polynomial(finalCoefficients, finalExponents);
    }

    // Method to evaluate the polynomial for a given value of x
    public double evaluate(double x) {

        double result = 0.0;

        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, exponents[i]);
        }
        return result;
    }

    // Method to check if a given value is a root of the polynomial
    public boolean hasRoot(double x) {
        return evaluate(x) == 0.0;
    }

    public Polynomial(File file) throws IOException {
        BufferedReader b = new BufferedReader(new FileReader(file));
        String line = b.readLine();
        b.close();

        String[] terms = line.split("(?=[+-])"); // Split on '+' or '-'

        coefficients = new double[terms.length];
        exponents = new int[terms.length];

        for (int i = 0; i < terms.length; i++) {
            String term = terms[i];

            if (!term.isEmpty()) {
                String[] separateParts = term.split("x");
                double coeff = 1.0; // Default coefficient is 1.0

                if (separateParts[0].isEmpty()) {
                    if (term.startsWith("-")) {
                        coeff = -1.0; // If the term starts with '-', set coefficient to -1.0
                    }
                } else {
                    coeff = Double.parseDouble(separateParts[0]);
                }

                int expo = 0; // Default exponent is 0

                if (separateParts.length == 2) {
                    expo = Integer.parseInt(separateParts[1]);
                }

                coefficients[i] = coeff;
                exponents[i] = expo;
            }
        }
    }

    public void saveToFile(String fileName) throws IOException {
        PrintStream writer = new PrintStream(new FileOutputStream(fileName));

        for (int i = 0; i < coefficients.length; i++) {
            double coeff = coefficients[i];
            int expo = exponents[i];

            if (i > 0 && coeff > 0) {
                writer.print("+"); // Add '+' sign for positive coefficients (except the first term)
            }

            if (coeff != 1.0 || expo == 0) {
                writer.print(coeff); // Write coefficient (unless it's 1 and expo > 0)
            }

            if (expo > 0) {
                writer.print("x");

                if (expo != 1) {
                    writer.print(expo); // Write exponent (if it's not 1)
                }
            }
        }

        writer.println(); // Move to the next line
        writer.close();

    }
}
