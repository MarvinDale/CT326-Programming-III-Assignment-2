## Assignment 2

Modify the payroll system you completed for Assignment 1 so that the earnings() method is passed an implementation of the attached taxCalculator interface. Write or modify the test program where you create an array of the various subtypes of Employee objects. Iterate over this array and calculate the payroll for each Employee object, as done previously, but this time you will have to pass a taxCalculator implementation each time you call the earnings() method. Therefore, the abstract earnings() method in the Employee class should be redefined as follows:

public abstract Money earnings(taxCalculator calc);

The Employee class will also need one additional class instance variable that will be passed to the calculateTax() method defined in the tacCalculator implementation passed to the earnings() method:

private Money taxCredits;

The earnings() methods, in each of the sub-classes, should be reimplemented to return the net earnings, after tax is deducted, for each employee object. You should test your earnings() methods using three different implementations of the taxCalculator interface as follows: using a class that implements taxCalculator, using an anonymous inner class and using a lambda function. Please refer to the Age interface example covered in the online classes, as a reference implementation, on how to use these types of constructs.
