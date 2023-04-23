/******************************************************************************

Lucas Teltow
Main.java

*******************************************************************************/
import java.io.*;
import java.lang.*;
import java.util.Scanner;

class Main
{
    //takes in an input string and preforms the calculation on it
    //outputs the objects to the console, returns void
    private static void readLine(String input)
    {
        //flag values:
        //Real and Real 0
        //Real and imaginary 1
        //imaginary and real 2
        //imaginary and imaginary 3
        
        //variables
        String[] splitInput = new String[3]; //for storing the input after being split by the ' ' character
        String[] complexTemp = new String[4]; //for storing the complex number after its being split by the '+' or '-' char
        String stringTemp; //for storing a string temporarily
        double temp1  = 0.0; //for storing the double from the string
        double temp2  = 0.0; //for storing the double from the string
        int flag = 0; //flag for storing what type of objects we have to work with
        int intTemp = 0; //a temp value for ints
        int i = 0; //counter variable
        char operation = ' '; //for storing the operation
        boolean isValid = false; //for helping with input validation
        Real realNum1 = new Real(); //the number for storing the first object (real)
        Real realNum2 = new Real(); //the number for storing the second object (real)
        Complex complexNum1 = new Complex(); //the number for storing the first object (complex)
        Complex complexNum2 = new Complex(); //the number for storing the first object (complex)
        
        //spliting the input up
        splitInput = input.split(" ");
        
        //input validation:
        for(i = 0; i < input.length(); i++)
        {
            if((Character.isDigit(input.charAt(i))) || (input.charAt(i) == ' ') || (input.charAt(i) == '+') || (input.charAt(i) == '-') || (input.charAt(i) == '*') || (input.charAt(i) == '/') || (input.charAt(i) == 'i') || (input.charAt(i) == '=') || (input.charAt(i) == '<') || (input.charAt(i) == '>') || (input.charAt(i) == '.')) 
                intTemp = 0;
            else return;
            
            intTemp = i - 1;
            if((input.charAt(i) == '*') || (input.charAt(i) == '/') || (input.charAt(i) == '=') || (input.charAt(i) == '<') || (input.charAt(i) == '>'))
                if(input.charAt(intTemp) != ' ')
                    return;
        }//end of input validation loop
        
        //checking to see if any of the complex numbers are wrong
        if(splitInput[0].indexOf("i") != -1)
            if(splitInput[0].indexOf("i") != (splitInput[0].length() - 1))
                return;
    
        if(splitInput[2].indexOf("i") != -1)
            if(splitInput[2].indexOf("i") != (splitInput[2].length() - 1))
                return;
                
        if(splitInput[0].indexOf("+") != -1)
            if(splitInput[0].indexOf("i") == -1)
                return;
        
        if(splitInput[2].indexOf("+") != -1)
            if(splitInput[2].indexOf("i") == -1)
                return;
                
        if(splitInput[0].indexOf("-") > 0)
            if(splitInput[0].indexOf("i") == -1)
                return;
                
        if(splitInput[2].indexOf("-") > 0)
            if(splitInput[2].indexOf("i") == -1)
                return;
        
        //spliting the string

        
        //grabbing the operation
        operation = splitInput[1].charAt(0);
        
        //storing the data as the correct objects
        //
        //  NUMBER 1
        //
        //if it has an 'i' in it, it needs to be stored as a complex number
        if(splitInput[0].indexOf("i") != -1)
        {
            //a bit of input validation
            for(i = 0; i < splitInput[0].length(); i++)
            {
                if(splitInput[0].charAt(i) == '.')
                {
                    if(isValid)
                        return;
                    else isValid = true;
                }
            }//end of input validation loop
            isValid = false;
            
            //checking to see if it has a negative or positive i value
            if(splitInput[0].indexOf("-") != -1 && splitInput[0].lastIndexOf("-") != 0)
            {
                //checking if the real part is negative, and redoing the code a tad if so
                if(splitInput[0].indexOf("-") == 0)
                {
                    stringTemp = splitInput[0].substring(1, (splitInput[0].length() - 1));
                    complexTemp = stringTemp.split("-");
                    complexTemp[0] = '-' + complexTemp[0];
                }
                else
                {
                    complexTemp = splitInput[0].split("-");
                }
                //getting the correct values into a double so it can be passed into num1 object
                intTemp = complexTemp[1].length() - 1;
                complexTemp[1] = complexTemp[1].substring(0, intTemp);
                temp1 = Double.parseDouble(complexTemp[0]); //storing the real part in temp 1
                temp2 = Double.parseDouble(complexTemp[1]); //storing the imaginary part in temp 2
                temp2 = temp2 * -1; //remember that the i part is negative in the original input
                
                //setting the num1 object to the correct values
                complexNum1.setVal(temp1, temp2);
            }//end of if for when the imaginary part is negative
            
            else if(splitInput[0].indexOf("+") != -1)
            {
                //getting the correct values into a double so it can be passed into num1 object
                complexTemp = splitInput[0].split("\\+");
                temp1 = Double.parseDouble(complexTemp[0]); //storing the real part in temp 1
                
                stringTemp = complexTemp[1].substring(0, (complexTemp[1].length() - 1));
                temp2 = Double.parseDouble(stringTemp); //storing the imaginary part in temp 2
                    //setting the num1 object to the correct values
                complexNum1.setVal(temp1, temp2);
            }//end of else for when the imaginary part is postive
            
            else 
            {
                intTemp = splitInput[0].indexOf("i");
                
                temp1 = 0.0;
                temp2 = Double.parseDouble(splitInput[0].substring(0, intTemp));
                
                complexNum1.setVal(temp1, temp2);
            }//end of else when first number is imaginary
            
             //increment the flag
            flag++;
            flag++;
            
        }//end of if for when the first object is complex
            
        else {
            realNum1.setVal(Double.parseDouble(splitInput[0]));
        }//end of else for when the object is Real
            
           
        
        //  NUMBER 2
        //
        //if it has an 'i' in it, it needs to be stored as a complex number
        if(splitInput[2].indexOf("i") != -1)
        {
            //a bit of input validation
            for(i = 0; i < splitInput[2].length(); i++)
            {
                if(splitInput[2].charAt(i) == '.')
                    if(isValid)
                        return;
                    else isValid = true;
            }//end of input validation loop
            isValid = false;
            
            //checking to see if it has a negative or positive i value
            if(splitInput[2].indexOf("-") != -1 && splitInput[2].indexOf("-") != 0)
            {
                //getting the correct values into a double so it can be passed into num2 object
                complexTemp = splitInput[2].split("-");
                complexTemp[1] = complexTemp[1].substring(0, (complexTemp[1].length() - 1));
                temp1 = Double.parseDouble(complexTemp[0]); //storing the real part in temp 1
                temp2 = Double.parseDouble(complexTemp[1]); //storing the imaginary part in temp 2
                temp2 = temp2 * -1; //remember that the i part is negative in the original input
                
                //setting the num2 object to the correct values
                complexNum2.setVal(temp1, temp2);
            }//end of if for when the imaginary part is negative
            
            else if(splitInput[2].indexOf("+") != -1) {
                //getting the correct values into a double so it can be passed into num2 object
                complexTemp = splitInput[2].split("\\+");
                temp1 = Double.parseDouble(complexTemp[0]); //storing the real part in temp 1
                    intTemp = complexTemp[1].length();
                intTemp = intTemp - 1;
                
                stringTemp = complexTemp[1].substring(0, intTemp);
                temp2 = Double.parseDouble(stringTemp); //storing the imaginary part in temp 2
                    //setting the num2 object to the correct values
                complexNum2.setVal(temp1, temp2);
            }//end of else for when the imaginary part is postive
            
            else {
                intTemp = splitInput[2].indexOf("i");
                
                temp1 = 0.0;
                temp2 = Double.parseDouble(splitInput[2].substring(0, intTemp));
                
                complexNum2.setVal(temp1, temp2);
            }//end of else when second number is imaginary
            
            //increment the flag
            flag++;
            
        }//end of if for when the first object is complex
        
        else {
            realNum2.setVal(Double.parseDouble(splitInput[2]));
        }//end of else for when the object is Real
        
        //flag values:
        //Real and Real 0
        //Real and imaginary 1
        //imaginary and real 2
        //imaginary and imaginary 3
        
        
        //printing out the expression initially inputed
        System.out.print(input);
        System.out.print('\t');
        
        //calling the correct function
        switch (operation)
        {
            default:
                break;
            case '+':   
                        //addition
                        switch (flag)
                        {
                            case 0:   
                                addFunc(realNum1, realNum2);  //real real
                                break;
                        
                            case 1:   
                                addFunc(realNum1, complexNum2);  //real imaginary
                                break;
                        
                            case 2:   
                                addFunc(complexNum1, realNum2);  //imaginary real
                                break;
                        
                            case 3:   
                                addFunc(complexNum1, complexNum2);  //imaginary imaginary
                                break;
            
                        }//end of switch checking which type of objects and calling the correct function
                        
                        break; //end of addition
                        
            case '-':   
                        //subtract
                        switch (flag)
                        {
                            case 0:   
                                subFunc(realNum1, realNum2);  //real real
                                break;
                        
                            case 1:   
                                subFunc(realNum1, complexNum2);  //real imaginary
                                break;
                        
                            case 2:   
                                subFunc(complexNum1, realNum2);  //imaginary real
                                break;
                        
                            case 3:   
                                subFunc(complexNum1, complexNum2);  //imaginary imaginary
                                break;
            
                        }//end of switch checking which type of objects and calling the correct function
                        
                        break; //end of subtract
                        
            case '*':   
                        //multiplication
                        switch (flag)
                        {
                            case 0:   
                                multiFunc(realNum1, realNum2);  //real real
                                break;
                        
                            case 1:   
                                multiFunc(realNum1, complexNum2);  //real imaginary
                                break;
                        
                            case 2:   
                                multiFunc(complexNum1, realNum2);  //imaginary real
                                break;
                        
                            case 3:   
                                multiFunc(complexNum1, complexNum2);  //imaginary imaginary
                                break;
            
                        }//end of switch checking which type of objects and calling the correct function
                        
                        break; //end of multiplication
                        
            case '/':   
                        //division
                        switch (flag)
                        {
                            case 0:   
                                divFunc(realNum1, realNum2);  //real real
                                break;
                        
                            case 1:   
                                divFunc(realNum1, complexNum2);  //real imaginary
                                break;
                        
                            case 2:   
                                divFunc(complexNum1, realNum1);  //imaginary real
                                break;
                        
                            case 3:   
                                divFunc(complexNum1, complexNum2);  //imaginary imaginary
                                break;
            
                        }//end of switch checking which type of objects and calling the correct function
                        
                        break; //end of division
                        
            case '=':   
                        //equals
                        switch (flag)
                        {
                            case 0:   
                                if(equalFunc(realNum1, realNum2))  //real real
                                    System.out.println("true");
                                else System.out.println("false");
                                break; 
                        
                            case 1:   
                                if(equalFunc(realNum1, complexNum2))  //real imaginary
                                    System.out.println("true");
                                else System.out.println("false");
                                break;
                        
                            case 2:   
                                if(equalFunc(complexNum1, realNum1))  //imaginary real
                                    System.out.println("true");
                                else System.out.println("false");
                                break;
                        
                            case 3:   
                                if(equalFunc(complexNum1, complexNum2))  //imaginary imaginary
                                    System.out.println("true");
                                else System.out.println("false");
                                break;
            
                        }//end of switch checking which type of objects and calling the correct function
                        
                        break; //end of equals
            
            case '>':   
                        //greater than
                        switch (flag)
                        {
                            case 0:   
                                if(greaterFunc(realNum1, realNum2))  //real real
                                    System.out.println("true");
                                else System.out.println("false");
                                break; 
                        
                            case 1:   
                                if(greaterFunc(realNum1, complexNum2))  //real imaginary
                                    System.out.println("true");
                                else System.out.println("false");
                                break;
                        
                            case 2:   
                                if(greaterFunc(complexNum1, realNum1))  //imaginary real
                                    System.out.println("true");
                                else System.out.println("false");
                                break;
                        
                            case 3:   
                                if(greaterFunc(complexNum1, complexNum2))  //imaginary imaginary
                                    System.out.println("true");
                                else System.out.println("false");
                                break;
            
                        }//end of switch checking which type of objects and calling the correct function
                        
                        break; //end of greater than
                        
            case '<':   
                        //less than
                        switch (flag)
                        {
                            case 0:   
                                if(lessThanFunc(realNum1, realNum2))  //real real
                                    System.out.println("true");
                                else System.out.println("false");
                                break; 
                        
                            case 1:   
                                if(lessThanFunc(realNum1, complexNum2))  //real imaginary
                                    System.out.println("true");
                                else System.out.println("false");
                                break;
                        
                            case 2:   
                                if(lessThanFunc(complexNum1, realNum1))  //imaginary real
                                    System.out.println("true");
                                else System.out.println("false");
                                break;
                        
                            case 3:   
                                if(lessThanFunc(complexNum1, complexNum2))  //imaginary imaginary
                                    System.out.println("true");
                                else System.out.println("false");
                                break;
            
                        }//end of switch checking which type of objects and calling the correct function
                        
                        break; //end of less than
            
        }//end of switch checking which type of operation and calling the correct function
    }//end of readLine function
    
    //takes in 2 number objects (real or complex or both) and adds them
    //outputs to the console, returns void
    private static void addFunc(Object num1, Object num2)
    {
        //variables
        double[] temp1 = new double[2]; //for storing the data from the complex object
        double[] temp2 = new double[2]; //for storing the data from the complex object
        double temp = 0; //for storing data as a double
        
        //checking to see what type the first number is, then checking the second one inside, making our 4 cases
        if(num1 instanceof Real && !(num1 instanceof Complex))
        {
            if(num2 instanceof Real && !(num2 instanceof Complex))
            {
                temp = ((Real)num1).getVal() + ((Real)num2).getVal();
                System.out.printf("%.2f\n", temp);
            }//end of num2 real
            else
            {
                //print the real part of the combined real complex
                temp1 = ((Complex)num2).getComplexVal();
                temp = temp1[0] + ((Real)num1).getVal();
                if(temp != 0)
                    System.out.printf("%.2f", temp);
                    
                //printing the imaginary part of the combined real complex
                if(temp1[1] < 0)
                    System.out.printf("%.2f\n", temp1[1]);
                else if(temp != 0)
                    System.out.printf("+%.2fi\n", temp1[1]);
                else System.out.printf("%.2fi\n", temp1[1]);
            }//end of num2 complex
        }//end of num1 real
        else
        {
            if(num2 instanceof Real && !(num2 instanceof Complex))
            {
                //print the real part of the combined complex real
                temp1 = ((Complex)num1).getComplexVal();
                temp = temp1[0] + ((Real)num2).getVal();
                if(temp != 0)
                    System.out.printf("%.2f", temp);
                    
                //printing the imaginary part of the combined complex real
                if(temp1[1] < 0)
                    System.out.printf("%.2f\n", temp1[1]);
                else if(temp != 0)
                    System.out.printf("+%.2fi\n", temp1[1]);
                else System.out.printf("%.2fi\n", temp1[1]);
            }//end of num2 real
            else
            {
                //print the real part of the combined complex complex
                temp1 = ((Complex)num1).getComplexVal();
                temp2 = ((Complex)num2).getComplexVal();
                temp = temp1[0] + temp2[0];
                if(temp != 0)
                  System.out.printf("%.2f", temp);
                        
                //checking if the imaginary part is negative and printing it out
                temp = temp1[1] + temp2[1];
                if(temp < 0)
                    System.out.printf("%.2f\n", temp);
                else if(temp != 0)
                    System.out.printf("+%.2fi\n", temp);
                else System.out.printf("%.2fi\n", temp);
            }//end of num2 complex
        }//end of num1 complex
    }//end of addFunc
    
    //takes in 2 number objects (real or complex or both) and subtracts them
    //outputs to the console, returns void
    private static void subFunc(Object num1, Object num2)
    {
        //variables
        double[] temp1 = new double[2]; //for storing the data from the complex object
        double[] temp2 = new double[2]; //for storing the data from the complex object
        double temp; //for storing data as a double
        
        //checking to see what type the first number is, then checking the second one inside, making our 4 cases
        if(num1 instanceof Real && !(num1 instanceof Complex))
        {
            if(num2 instanceof Real && !(num2 instanceof Complex))
            {
                temp = ((Real)num1).getVal() - ((Real)num2).getVal();
                System.out.printf("%.2f%n", temp);
            }//end of num2 real
            else
            {
                //print the real part of the combined real complex
                temp1 = ((Complex)num2).getComplexVal();
                temp = temp1[0] - ((Real)num1).getVal();
                if(temp != 0)
                    System.out.printf("%.2f", temp);
                    
                //printing the imaginary part of the combined real complex
                if(temp1[1] < 0)
                    System.out.printf("%.2f\n", temp1[1]);
                else if(temp != 0)
                    System.out.printf("+%.2fi\n", temp1[1]);
                else System.out.printf("%.2fi\n", temp1[1]);
            }//end of num2 complex
        }//end of num1 real
        else
        {
            if(num2 instanceof Real && !(num2 instanceof Complex))
            {
                //print the real part of the combined complex real
                temp1 = ((Complex)num1).getComplexVal();
                temp = temp1[0] - ((Real)num2).getVal();
                if(temp != 0)
                    System.out.printf("%.2f", temp);
                    
                //printing the imaginary part of the combined complex real
                if(temp1[1] < 0)
                    System.out.printf("%.2fi\n", temp1[1]);
                else if(temp != 0)
                    System.out.printf("+%.2fi\n", temp1[1]);
                else System.out.printf("%.2fi\n", temp1[1]);
            }//end of num2 real
            else
            {
                //print the real part of the combined complex complex
                temp1 = ((Complex)num1).getComplexVal();
                temp2 = ((Complex)num2).getComplexVal();
                
                temp = temp1[0] - temp2[0];
                if(temp != 0)
                  System.out.printf("%.2f", temp);
                        
                //checking if the imaginary part is negative and printing it out
                temp = temp1[1] - temp2[1];
                if(temp < 0)
                    System.out.printf("%.2fi\n", temp);
                else if(temp != 0)
                    System.out.printf("+%.2fi\n", temp);
                else System.out.printf("%.2fi\n", temp);
            }//end of num2 complex
        }//end of num1 complex
    }//end of subFunc
    
    //takes in 2 number objects (real or complex or both) and multiplies them
    //outputs to the console, returns void
    private static void multiFunc(Object num1, Object num2)
    {
        //variables
        double[] temp1 = new double[2]; //for storing the data from the complex object
        double[] temp2 = new double[2]; //for storing the data from the complex object
        double temp; //for storing data as a double
        
        //checking to see what type the first number is, then checking the second one inside, making our 4 cases
        if(num1 instanceof Real && !(num1 instanceof Complex))
        {
            if(num2 instanceof Real && !(num2 instanceof Complex))
            {
                temp = ((Real)num1).getVal() * ((Real)num2).getVal();
                System.out.printf("%.2f\n", temp);
            }//end of num2 real
            else
            {
                //print the real part of the combined real complex
                temp1 = ((Complex)num2).getComplexVal();
                temp = temp1[0] * ((Real)num1).getVal();
                if(temp != 0)
                    System.out.printf("%.2f", temp);
                    
                //printing the imaginary part of the combined real complex
                temp = temp1[1] * ((Real)num1).getVal();
                if(temp < 0)
                    System.out.printf("%.2f\n", temp);
                else if(temp != 0)
                    System.out.printf("+%.2fi\n", temp);
                else System.out.printf("%.2fi\n", temp);
            }//end of num2 complex
        }//end of num1 real
        else
        {
            if(num2 instanceof Real && !(num2 instanceof Complex))
            {
                //print the real part of the combined complex real
                temp1 = ((Complex)num1).getComplexVal();
                temp = temp1[0] * ((Real)num2).getVal();
                if(temp != 0)
                    System.out.printf("%.2f", temp);
                    
                //printing the imaginary part of the combined complex real
                temp1[1] = temp1[1] * ((Real)num2).getVal();
                if(temp1[1] < 0)
                    System.out.printf("%.2f\n", temp1[1]);
                else if(temp != 0)
                    System.out.printf("+%.2fi\n", temp1[1]);
                else System.out.printf("%.2fi\n", temp1[1]);
            }//end of num2 real
            else
            {
                //print the real part of the combined complex complex
                temp1 = ((Complex)num1).getComplexVal();
                temp2 = ((Complex)num2).getComplexVal();
                
                temp = temp1[0] * temp2[0];
                temp = temp + (temp1[1] * temp2[1] * -1);
                if(temp != 0)
                  System.out.printf("%.2f", temp);
                        
                //checking if the imaginary part is negative and printing it out
                temp = temp1[0] * temp2[1];
                temp = temp + (temp1[1] * temp2[0]);
                if(temp < 0)
                    System.out.printf("%.2fi\n", temp);
                else if(temp != 0)
                    System.out.printf("+%.2fi\n", temp);
                else System.out.printf("%.2fi\n", temp);
            }//end of num2 complex
        }//end of num1 complex
    }//end of multiFunc
    
    //takes in 2 number objects (real real) and divides them
    //outputs to the console, returns void
    private static void divFunc(Object num1, Object num2)
    {
        //variables
        double[] temp1 = new double[2]; //for storing the data from the complex object
        double[] temp2 = new double[2]; //for storing the data from the complex object
        double[] vals = new double[4]; //for storing values to do conjugate multiplication with
        double temp3; //for storing a double temporarily
        Real denominator = new Real(); //for calling div again with a valid denominator
        Complex numerator = new Complex(); //for calling div again with a valid denominator

        //splitting into our 4 cases
        if(num1 instanceof Real && !(num1 instanceof Complex))
        {
            if(num2 instanceof Real && !(num2 instanceof Complex))
            {
                //just dividing 2 real numbers and printing them
                temp3 = ((Real)num1).getVal() / ((Real)num2).getVal();
                System.out.printf("%.2f\n", temp3);
            }//end of num2 Real
            else
            {
                //first multiply the top and bottom by the conjugate
                temp1 = ((Complex)num2).getComplexVal();
                temp1[1] = temp1[1] * -1;
                temp1[0] = temp1[0] * ((Real)num1).getVal();
                temp1[1] = temp1[1] * ((Real)num1).getVal();
                             
                //call divFunc again with a new set of objects that are dividable
                denominator.setVal(conj(((Complex)num2)));
                numerator.setVal(temp1[0], temp1[1]);
                divFunc(numerator, denominator);
            }//end of num2 complex
        }//end of num1 real
        else
        {
            if(num2 instanceof Real && !(num2 instanceof Complex))
            {
                //dividing the real part
                temp1 = ((Complex)num1).getComplexVal();
                temp3 = ((Real)num2).getVal() / temp1[0];
                System.out.printf("%.2f", temp3);
                        
                //checking if the imaginary part is negative and printing it out
                temp3 = temp1[1] / ((Real)num2).getVal();
                if(temp3 < 0)
                    System.out.printf("%.2f\n", temp3);
                else if(temp3 != 0)
                    System.out.printf("+%.2fi\n", temp3);
                else System.out.printf("%.2fi\n", temp3);
            }//end of num2 Real
            else
            {
                //getting the vals for the different parts of the equation to solve it
                temp1 = ((Complex)num1).getComplexVal();
                temp2 = ((Complex)num2).getComplexVal();
                vals[0] = temp1[0];     //a
                vals[1] = temp1[1];     //b
                vals[2] = temp2[0];     //c
                vals[3] = temp2[1];     //d
                
                //calculating the real part
                temp1[0] = vals[0] * vals[2];
                temp1[1] = vals[1] * vals[3];
                temp2[0] = temp1[0] + temp1[1];
                
                temp1[0] = vals[2] * vals[2];
                temp1[1] = vals[3] * vals[3];
                temp2[1] = temp1[0] + temp1[1];
                
                temp3 = temp2[0] / temp2[1];
                
                //printing out the real part
                System.out.printf("%.2f", temp3);
                
                //calculating the imaginary part
                temp1[0] = vals[1] * vals[2];
                temp1[1] = vals[0] * vals[3];
                temp2[0] = temp1[0] - temp1[1];
                
                temp1[0] = vals[2] * vals[2];
                temp1[1] = vals[3] * vals[3];
                temp2[1] = temp1[0] + temp1[1];
                
                temp3 = temp2[0] / temp2[1];
                
                //printing out the complex part
                if(temp3 < 0)
                    System.out.printf("%.2f\n", temp3);
                else if(temp3 != 0)
                    System.out.printf("+%.2fi\n", temp3);
                else System.out.printf("%.2fi\n", temp3);
                
            }//end of num2 complex
        }//end of num1 complex
    }//end of divFunc
    
    //takes in 2 number objects (real or complex or both) and compares them to see if the first is greater
    //no output, returns a bool value, true if the first number is larger than the second one
    private static boolean greaterFunc(Object num1, Object num2)
    {
        //checking to see if the first object is a real or a complex, then checking the second one inside to split into our 4 cases
        if(num1 instanceof Real && !(num1 instanceof Complex))
        {
            if(num2 instanceof Real && !(num2 instanceof Complex))
            {
                if(((Real)num1).getVal() > ((Real)num2).getVal())
                    return true;
            }//end of num2 real
            else
            {
                if(((Real)num1).getVal() > getMagnitude((Complex)num2))
                    return true;
            }//end of num2 complex
        }//end of real num1
        else
        {
            if(num2 instanceof Real && !(num2 instanceof Complex))
            {
                if(getMagnitude((Complex)num1) > ((Real)num2).getVal())
                    return true;
            }//end of num2 real
            else
            {
                if(getMagnitude((Complex)num1) > getMagnitude((Complex)num2))
                    return true;
            }//end of num2 complex
        }//end of complex num1
        
        return false;
    }//end of greaterFunc

    //takes in 2 number objects (real or complex or both) and compares them to see if the first is less than the second
    //no output, returns a bool value, true if the first number is smaller than the second one, false otherwise
    private static boolean lessThanFunc(Object num1, Object num2)
    {
        //checking to see if the first object is a real or a complex, then checking the second one inside to split into our 4 cases
        if(num1 instanceof Real && !(num1 instanceof Complex))
        {
            if(num2 instanceof Real && !(num2 instanceof Complex))
            {
                if(((Real)num1).getVal() < ((Real)num2).getVal())
                    return true;
            }//end of num2 real
            else
            {
                if(((Real)num1).getVal() < getMagnitude((Complex)num2))
                    return true;
            }//end of num2 complex
        }//end of real num1
        else
        {
            if(num2 instanceof Real && !(num2 instanceof Complex))
            {
                if(getMagnitude((Complex)num1) < ((Real)num2).getVal())
                    return true;
            }//end of num2 real
            else
            {
                if(getMagnitude((Complex)num1) < getMagnitude((Complex)num2))
                    return true;
            }//end of num2 complex
        }//end of complex num1
        
        return false;
    }//end of lessThanFunc
    
    //takes in 2 number objects (real or imaginary or both) and compares them to see if they are equal
    //no output, returns a bool value, true if the first number has the same magnitute as the second one
    private static boolean equalFunc(Object num1, Object num2)
    {
        double[] temp1 = new double[2];
        double[] temp2 = new double[2];
        
        //checks if they are both reals, or both complex, and returns false if the types dont match
        if(num1 instanceof Real && num2 instanceof Real && !(num1 instanceof Complex) && !(num2 instanceof Complex))
        {
            if(((Real)num1).getVal() == ((Real)num2).getVal())
                return true;
        }//end of check if they are both reals
        
        if(num1 instanceof Complex && num2 instanceof Complex)
        {
            temp1 = ((Complex)num1).getComplexVal();
            temp2 = ((Complex)num2).getComplexVal();
            
            if(temp1[0] == temp2[0] && temp1[1] == temp2[1])
                return true;
        }//end of checking if they're both complex numbers
        
        return false;
    }//end of equalFunc
    
    //used to calculate the magnitute of a complex number, takes in a complex number
    //returns the magnitute of the number
    private static double getMagnitude(Complex obj)
    {
        double[] temp = new double[2];
        temp = obj.getComplexVal();
        double mag = 0;
        
        //uses the quations given in class for the magnitute of complex numbers
        mag = temp[0] * temp[0];
        mag = mag + (temp[1] * temp[1]);
        mag = Math.sqrt(mag);
        return mag;
    }//end of getMagnitude
    
    //takes in a complex object and returns the object multiplied by the conjugate, then returns that value as a real object
    private static double conj(Complex num1)
    {
        double[] temp = new double[2];
        double[] conjugate = new double[2];
        double[] vals = new double[2]; //0: real real, 1: imaginary imaginary
        conjugate = num1.getComplexVal();
        temp = num1.getComplexVal();
        conjugate[1] = conjugate[1] * -1;
        
        //multiplying each part together
        vals[0] = temp[0] * conjugate[0]; //real real
        vals[1] = temp[1] * conjugate[1]; //imaginary imaginary
        
        temp[0] = vals[0] - vals[1]; //i^2 = -1, so vals[1] becomes a negative real

        return temp[0];
    }
    
    //main function
    public static void main(String[] args) throws IOException
    {
        //declaring various variables
        String fileName; //for storing the input file name
        Scanner in = new Scanner(System.in); //scanner for the command line
        
        //taking in the file name
        System.out.println("Enter the input file name");
        fileName = in.nextLine();
        File inFile = new File(fileName);
        
        //check that the file is valid
        if(!inFile.exists())
            return;
        
        //open a scanner for the file
        Scanner inFileScanner = new Scanner(inFile);
        
        //reading in the data from the file
        while(inFileScanner.hasNextLine())
        {
            readLine(inFileScanner.nextLine());
        }
    }//end of main function
}//end of main class