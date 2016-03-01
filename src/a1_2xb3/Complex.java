package a1_2xb3;

// Import statements 
import java.lang.Math;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Complex {
	
	private double real_double;
	private double imag_double;

// Constructor for Complex Class
	public Complex() {
		this.real_double = 0.0;
		this.imag_double = 0.0;
	}
	
	public Complex(double real, double imag) {
		this.real_double = real;
		this.imag_double = imag;
	}

// getter methods
	public double real_double() { return real_double; }
	public double imag_double() { return imag_double; }
		
/* adds the real parts and imaginary parts of the two complex numbers separately, 
	 and stores the result as a single complex number
	*/		
	public Complex addition(Complex that) {		
		Complex complexAddition = new Complex(real_double() + that.real_double, imag_double + that.imag_double);		
		return complexAddition;	
	}
	
/* subtracts the real parts and imaginary parts of the two complex numbers separately, 
	 and stores the result as a single complex number
	*/			
	public Complex subtraction(Complex that) {		
		Complex complexSubtraction = new Complex(real_double() - that.real_double, imag_double() - that.imag_double);		
		return complexSubtraction;	
	}
		
/* multiplies the real parts and imaginary parts of the two complex numbers separately, 
	 and stores the result as a single complex number
	*/			
	public Complex multiplication(Complex that) {	
		Complex complexMultiplication = new Complex(real_double() * that.real_double - imag_double() * that.imag_double, real_double() * that.imag_double + imag_double() * that.real_double);		
		return complexMultiplication;	
	}

/* divides the real parts and imaginary parts of the two complex numbers separately, 
	 and stores the result as a single complex number
*/			
	public Complex division(Complex that) {		
		Complex complexDivision = new Complex((real_double() * that.real_double + imag_double() * that.imag_double) / (that.real_double * that.real_double + that.imag_double * that.imag_double), (imag_double() * that.real_double - real_double() * that.imag_double) / (that.real_double * that.real_double + that.imag_double * that.imag_double));		
		return complexDivision;	
	}
	
// The real component and the imaginary component of the square root are calculated separately 	
	public Complex squareRoot() {

// The calculation for the real component of the square root
		double real_sqrt = (1/Math.sqrt(2.0))*(Math.sqrt(Math.sqrt((real_double()*real_double()) + (imag_double()*imag_double())) + real_double()));
		double imag_sqrt;

// if the imaginary component is greater than zero, use a slightly altered formula than the case where the imaginary
// component is less than zero
		if (imag_double() >= 0) {
			imag_sqrt = (1/Math.sqrt(2.0))*(Math.sqrt(Math.sqrt((real_double()*real_double()) + (imag_double()*imag_double())) - real_double()));
		} else {
			imag_sqrt = (-1/Math.sqrt(2.0))*(Math.sqrt(Math.sqrt((real_double()*real_double()) + (imag_double()*imag_double())) + real_double()));
		}
		  Complex complexSqrt = new Complex(real_sqrt, imag_sqrt);
			return complexSqrt;
		}

//Converts the complex number to the polar form r(cos(theta) + i*sin(theta))
	public String polarForm() {
		double radius = Math.sqrt(real_double()*real_double() + imag_double()*imag_double()); //radius value
		double ratio = real_double()/radius;
		double angle = Math.acos(ratio);
		double angle_degs = Math.toDegrees(angle); //angle value in degrees
		return (radius + "(cos " + angle_degs + " + i*sin " + angle_degs + ")");	
}

//Computes the complex conjugate of the complex number
	public Complex conjugation() {
		Complex complexConjugate = new Complex(real_double(),-imag_double());
		return complexConjugate;
	}

// Compares two complex numbers and checks for equality
	public boolean equals(Complex a) {
		if (real_double() == a.real_double && imag_double() == a.imag_double){
			return true;
			}
			return false;
		}
	
	public String toString() { return "(" + real_double() + " + " + imag_double() + "i)"; } //Displays the complex num

//Computes the greatest common denominator of two complex numbers
	public static Complex gcd(Complex a, Complex b) {

		Complex remainder;
		Complex quotient;

// If the norm of complex number 'a' is bigger than the norm of complex number 'b', perform the division a/b
		if ((a.real_double * a.real_double + a.imag_double * a.imag_double > b.real_double*b.real_double + b.imag_double*b.imag_double)) {
			quotient = new Complex(Math.floor(a.division(b).real_double),Math.floor(a.division(b).imag_double));
			remainder = a.subtraction(quotient.multiplication(b));
// Perform recursion until a remainder of 0(the complex number 0 + 0i) is obtained
			if (remainder.real_double != 0 && remainder.imag_double != 0){
				return gcd(a,remainder);
			} 
		
// If the norm of complex number 'b' is bigger than the norm of complex number 'a', perform the division b/a
		} else {
			quotient = new Complex(Math.floor(b.division(a).real_double),Math.floor(b.division(a).imag_double));
			remainder = b.subtraction(quotient.multiplication(a));
// Perform recursion until a remainder of 0(the complex number 0 + 0i) is obtained
			if (remainder.real_double != 0 && remainder.imag_double != 0){
				return gcd(b,remainder);
			}
		}
		return b;
	}

// Given a complex number and an angle, this method rotates the complex number by the angle clockwise, and outputs
// the resulting complex number
	public Complex rotate(Complex a, int angle) {
	double original_ang = 0.0;
	double real_rotated = 0.0;
	double imag_rotated = 0.0;

// The first condition, where the complex number is a vector lying in the first quadrant
	if (a.real_double > 0 && a.imag_double > 0) {
		original_ang = Math.toDegrees(Math.atan(a.imag_double/a.real_double));
		real_rotated = (Math.sqrt(a.real_double * a.real_double + a.imag_double * a.imag_double))*(Math.cos(Math.toRadians(original_ang - angle)));
		imag_rotated = (Math.sqrt(a.real_double * a.real_double + a.imag_double * a.imag_double))*(Math.sin(Math.toRadians(original_ang - angle)));
// The second condition, where the complex number is a vector lying in the second quadrant
	} else if (a.real_double < 0 && a.imag_double > 0) {
		original_ang = Math.toDegrees(Math.atan(a.imag_double/a.real_double)) + 180;
		real_rotated = (Math.sqrt(a.real_double * a.real_double + a.imag_double * a.imag_double))*(Math.cos(Math.toRadians(original_ang - angle)));
		imag_rotated = (Math.sqrt(a.real_double * a.real_double + a.imag_double * a.imag_double))*(Math.sin(Math.toRadians(original_ang - angle)));
// The third condition, where the complex number is a vector lying in the third quadrant
	} else if (a.real_double < 0 && a.imag_double < 0) {
		original_ang = Math.toDegrees(Math.atan(a.imag_double/a.real_double)) + 180;
		real_rotated = (Math.sqrt(a.real_double * a.real_double + a.imag_double * a.imag_double))*(Math.cos(Math.toRadians(original_ang - angle)));
		imag_rotated = (Math.sqrt(a.real_double * a.real_double + a.imag_double * a.imag_double))*(Math.sin(Math.toRadians(original_ang - angle)));
// The fourth condition, where the complex number is a vector lying in the fourth quadrant
	} else if (a.real_double > 0 && a.imag_double < 0) {
		original_ang = Math.toDegrees(Math.atan(a.imag_double/a.real_double)) + 360;
		real_rotated = (Math.sqrt(a.real_double * a.real_double + a.imag_double * a.imag_double))*(Math.cos(Math.toRadians(original_ang - angle)));
		imag_rotated = (Math.sqrt(a.real_double * a.real_double + a.imag_double * a.imag_double))*(Math.sin(Math.toRadians(original_ang - angle)));
	}

	Complex complexRotated = new Complex(real_rotated, imag_rotated);
	return complexRotated;
}

// Using Ohm's law, calculates the current, given the voltage and resistance as complex numbers
	public static Complex get_current(Complex v, Complex	z) {
		
		Complex current = v.division(z);
		return current;			
	}	

// Using Ohm's law, calculates the voltage, given the current and resistance as complex numbers
	public static Complex get_voltage(Complex	i, Complex	z) {
		
		Complex voltage = i.multiplication(z);
		return voltage;
	}

	public static void main(String[] args) throws FileNotFoundException {

/*************************************
 * 
 * 
 *
 * Client code to test the four methods (gcd(Complex a, Complex b), rotate(Complex a, int angle), 
 * get_current(Complex v, Complex	z) and get_voltage(Complex i, Complex z) implemented  in assignment problem Part 2
 * 
 * 
 * 
 *************************************
 */
		
	Scanner input	=	new Scanner(new File("Data/input.txt")); // The text file to read the test cases

	PrintStream output =	new PrintStream(new File("Data/output.txt")); // The text file to store the results
																																				// of the test cases
		
	int checkLine = 0, testCaseNumber = 0;
		
// There are 4 methods to test, so the counter has to be reset to zero after every four lines, so that the next 
// set of test cases can be tested for the 4 implemented methods
	while	(input.hasNextLine()){
		if (checkLine % 4 == 0){ 
			checkLine = 0;
	  	}
			
// The following code checks if the line in the text file has 2 characters or less, if so then the line corresponds
// to the test case numbers (1,2,3,....10). In this case, perform no computations on that line, and just move on to
// the next line in the text file
	String line	= input.nextLine(); 
		if (line.length() <= 2){
			continue;
			}
			
// First computation on the test case, the GCD(greatest common denominator) of the two complex numbers
		if (checkLine == 0){ 
	
/* Create an array. The four values separated by commas in the first line of the input text file are parsed as 	
 * follows: real part of the first complex number, imaginary part of the first complex number, real part of the 
 * second complex number, imaginary part of the second complex number		
 */

			String[] inputValues = line.split(","); //Comma character is the separator of the 4 values
			
			Complex	a = new Complex(Double.parseDouble(inputValues[0]), Double.parseDouble(inputValues[1])); 
			Complex	b = new Complex(Double.parseDouble(inputValues[2]), Double.parseDouble(inputValues[3]));
			
			testCaseNumber++; // increment the test case number
			
			output.println("Test case " + testCaseNumber + ": " + a + " , " + b + "\n");
			output.println("The greatest common divisor for complex number " + a + " and complex number " + b + "is " + gcd(a,b));
			}
			
// Second computation on the test case, rotate the complex number by a given angle
		if (checkLine == 1){ 
			String[] inputValues = line.split(","); //The comma character which splits the values
			
			if (inputValues[1].contains("i")){ // The "i" character in the imaginary part is irrelevant to the 
																				 // computations, so it has to be dealt with
				inputValues[1] = inputValues[1].substring(0, inputValues[1].length()-1);
		  	}
			
			Complex	a = new Complex(Double.parseDouble(inputValues[0]), Double.parseDouble(inputValues[1])); 
			Complex rotate = a.rotate(a,Integer.parseInt(inputValues[2]));
			
			output.println("The complex number "+ a + "rotated " + inputValues[2] + " degrees becomes " + rotate);
			}
			
// Third computation on the test case, use Ohm's law to calculate the current
		if (checkLine == 2){ 
			String[] inputValues = line.split(","); //The comma character which splits the different values 
			
			Complex	a = new Complex(Double.parseDouble(inputValues[0]), Double.parseDouble(inputValues[1]));
			Complex	b = new Complex(Double.parseDouble(inputValues[2]), Double.parseDouble(inputValues[3]));
			
			Complex current = get_current(a,b);
			
			output.println("According to Ohm's law(V=IR), the current is: " + current);
			}
			
// Fourth computation on the test case, use Ohm's law to calculate the voltage
		if (checkLine == 3){
			String[] inputValues = line.split(",");  //The comma character which splits the different values
			
			Complex	a = new Complex(Double.parseDouble(inputValues[0]), Double.parseDouble(inputValues[1]));
			Complex	b = new Complex(Double.parseDouble(inputValues[2]), Double.parseDouble(inputValues[3]));
			
			Complex voltage = get_voltage(a,b);
			
			output.println("According to Ohm's law(V=IR), the voltage is: " + voltage + "\n");
			}
		checkLine++; // Increment and perform the same method calculator on all the other test cases
		}
	input.close();

/*************************************
 * 
 * 
 *
 * Client code to test the other methods implemented in assignment problem Part 1
 * 
 * 
 * 
 *************************************
 */
		
		Complex complex1	= new Complex(1,7);
		Complex complex2 = new Complex(3,2);
		output.println("First test case for part 1");
		output.println(complex1 + " plus " +  complex2 + " equals " + complex1.addition(complex2));
		output.println(complex1 + " minus " +  complex2 + " equals " + complex1.subtraction(complex2));
		output.println(complex1 + " multiplied by " + complex2 + " is " + complex1.multiplication(complex2));
		output.println(complex1 + " divided by " + complex2 + " is " + complex1.division(complex2));
		output.println("Square root of " + complex1 + "is " + complex1.squareRoot());
		output.println("polar form of " + complex1 + "is " + complex1.polarForm());
		output.println("Conjugation of " + complex1 + "is " + complex1.conjugation());
		output.println("\n");
		
		Complex complex11	= new Complex(3,9);
		Complex complex22 = new Complex(2,7);
		output.println("Second test case for part 1");
		output.println(complex11 + " plus " +  complex22 + " equals " + complex11.addition(complex22));
		output.println(complex11 + " minus " +  complex22 + " equals " + complex11.subtraction(complex22));
		output.println(complex11 + " multiplied by " + complex22 + " is " + complex11.multiplication(complex22));
		output.println(complex11 + " divided by " + complex22 + " is " + complex11.division(complex22));
		output.println("Square root of " + complex11 + "is " + complex11.squareRoot());
		output.println("polar form of " + complex11 + "is " + complex11.polarForm());
		output.println("Conjugation of " + complex11 + "is " + complex11.conjugation());
		output.println("\n");
		
		Complex complex111	= new Complex(14,7);
		Complex complex222 = new Complex(2,6);
		output.println("Third test case for part 1");
		output.println(complex111 + " plus " +  complex222 + " equals " + complex1.addition(complex2));
		output.println(complex111 + " minus " +  complex222 + " equals " + complex111.subtraction(complex222));
		output.println(complex111 + " multiplied by " + complex222 + " is " + complex111.multiplication(complex222));
		output.println(complex111 + " divided by " + complex222 + " is " + complex111.division(complex222));
		output.println("Square root of " + complex111 + "is " + complex111.squareRoot());
		output.println("polar form of " + complex111 + "is " + complex111.polarForm());
		output.println("Conjugation of " + complex111 + "is " + complex111.conjugation());
		output.println("\n");
		
		Complex complex1111	= new Complex(3,9);
		Complex complex2222 = new Complex(1,1);
		output.println("Fourth test case for part 1");
		output.println(complex1111 + " plus " +  complex2222 + " equals " + complex1111.addition(complex2222));
		output.println(complex1111 + " minus " +  complex2222 + " equals " + complex1111.subtraction(complex2222));
		output.println(complex1111 + " multiplied by " + complex2222 + " is " + complex1111.multiplication(complex2222));
		output.println(complex1111 + " divided by " + complex2222 + " is " + complex1111.division(complex2222));
		output.println("Square root of " + complex1111 + "is " + complex1111.squareRoot());
		output.println("polar form of " + complex1111 + "is " + complex1111.polarForm());
		output.println("Conjugation of " + complex1111 + "is " + complex1111.conjugation());
		output.println("\n");
		
		Complex complex3	= new Complex(22,6);
		Complex complex4 = new Complex(3,6);
		output.println("Fifth test case for part 1");
		output.println(complex3 + " plus " +  complex4 + " equals " + complex3.addition(complex4));
		output.println(complex3 + " minus " +  complex4 + " equals " + complex3.subtraction(complex4));
		output.println(complex3 + " multiplied by " + complex4 + " is " + complex3.multiplication(complex4));
		output.println(complex3 + " divided by " + complex4 + " is " + complex3.division(complex4));
		output.println("Square root of " + complex3 + "is " + complex3.squareRoot());
		output.println("polar form of " + complex3 + "is " + complex3.polarForm());
		output.println("Conjugation of " + complex3 + "is " + complex3.conjugation());
		output.println("\n");
		
		Complex complex33	= new Complex(16,3);
		Complex complex44 = new Complex(4,4);
		output.println("Sixth test case for part 1");
		output.println(complex33 + " plus " +  complex44 + " equals " + complex33.addition(complex44));
		output.println(complex33 + " minus " +  complex44 + " equals " + complex33.subtraction(complex44));
		output.println(complex33 + " multiplied by " + complex44 + " is " + complex33.multiplication(complex44));
		output.println(complex33 + " divided by " + complex44 + " is " + complex33.division(complex44));
		output.println("Square root of " + complex33 + "is " + complex33.squareRoot());
		output.println("polar form of " + complex33 + "is " + complex33.polarForm());
		output.println("Conjugation of " + complex33 + "is " + complex33.conjugation());
		output.println("\n");
		String a = "abcdefghijkl";
		String b = "cd";
		String c = a.substring(0,a.length()-1);
	}
}	
	

