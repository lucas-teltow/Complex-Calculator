//
//  Lucas Teltow
//  Real.java
//

public class Real
{
    //variables
    double val;
    
    //constructors
    public Real()
    {
        val = 0;
    }//end of Real
    public Real(double num) //takes in a value to set val too
    {
        val = num;
    }//end of Real(double)
    
    //accessor, returns an array of double val
    public double getVal()
    {
        return val;
    }//end of getVal
    
    //mutator, takes in a double, returns void
    public void setVal(double num)
    {
        val = num;
    }//end of setVal
    
    //checks to see if 2 Reals are the same, takes in a Real, and returns a bool
    public boolean equals(Real obj2)
    {
        double temp = obj2.getVal();
        if(val == temp)
            return true;
        else return false;
    }//end of equals
    
    //compares 2 Reals, returning 1 if the first is larger than the second, -1 if the opposite is true, and 0 otherwise
    public int compareTo(Real obj1, Real obj2)
    {
        double temp1 = obj1.getVal();
        double temp2 = obj2.getVal();
        if(temp1 > temp2)
            return 1;
        if(temp1 < temp2)
            return -1;
        else return 0;
    }//end of compareTo
    
    //returns the data stored in this object (its value)
    public String toString()
    {
        return String.valueOf(val);
    }//end of toString
    
    
    
}//end of Real class