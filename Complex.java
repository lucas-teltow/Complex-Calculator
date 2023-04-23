//
//  Lucas Teltow
//  Complex.java
//

public class Complex extends Real
{
    //variables
    double imaginary;
    
    //constructors
    public Complex()
    {
        val = 0;
        imaginary = 0;
    }//end of Complex
    public Complex(double real, double imag) //takes in a value so it can set the real and imaginary value
    {
        val = real;
        imaginary = imag;
    }//end of Real(double)
    
    //accessor, returns a double array with the first number being the real part, and the second one being the imaginary part
    public double[] getComplexVal()
    {
        double[] vals = new double[2];
        vals[0] = val;
        vals[1] = imaginary;
        
        return vals;
    }//end of getVal
    
    //mutator, takes in a double for the real and one for the imaginary, returns void
    public void setVal(double real, double imag)
    {
        val = real;
        imaginary = imag;
    }//end of setVal
    
    //checks to see if 2 imaginaries are the same, takes in an imaginary, and returns a bool
    public boolean equals(Complex obj2)
    {
        if(this.getVal() == obj2.getVal())
            return true;
        else return false;
    }//end of equals
    
    //compares 2 complex numbers, returning 1 if the first is larger than the second, -1 if the opposite is true, and 0 otherwise
    public int compareTo(Complex obj1, Complex obj2)
    {
        double[] temp1 = new double[2];
        double[] temp2 = new double[2];
        double mag1, mag2;
        
        temp1 = obj1.getComplexVal();
        temp2 = obj2.getComplexVal();
        
        //grab the values of the different objects and call the getMag to get their magnitude
        mag1 = getMag(temp1[0], temp1[1]);
        mag2 = getMag(temp2[0], temp2[1]);
        
        //compare the magnitudes
        if(mag1 > mag2)
            return 1;
        if(mag1 < mag2)
            return -1;
        else return 0;
        
        
    }//end of compareTo
    
    //returns the data stored in this object (its real and imaginary parts)
    public String toString()
    {
        String temp = (String.valueOf(val) + "+" + String.valueOf(imaginary) + "i");
        return temp;
    }//end of toString
    
    //used to calculate the magnitute of a complex number, takes in 2 doubles
    private double getMag(double a, double b)
    {
        double temp1, temp2;
        //uses the quations given in class for the magnitute of complex numbers
        temp1 = a*a;
        temp2 = b*b;
        temp1 = temp1+temp2;
        temp1 = Math.sqrt(temp1);
        return temp1;
    }//end of getMag
}//end of Complex class