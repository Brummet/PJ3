package PJ3;

import java.util.*;
import java.io.*;

// You may add new functions or data in this class 
// You may modify any functions or data members here
// You must use Customer, Teller and ServiceArea
// to implement your simulator
class BankSimulator {

    // input parameters
    private int numTellers, customerQLimit;
    private int simulationTime, dataSource;
    private int chancesOfArrival, maxTransactionTime;

    // statistical data
    private int numGoaway, numServed, totalWaitingTime;

    // internal data
    private int customerIDCounter;
    private ServiceArea servicearea; // service area object
    private Scanner dataFile;	   // get customer data from file
    private Random dataRandom;	   // get customer data using random function

    // most recent customer arrival info, see getCustomerData()
    private boolean anyNewArrival;
    private int transactionTime;

    // initialize data fields
    private BankSimulator() {
        // add statements
    }

    private void setupParameters() {
        // read input parameters
        // setup dataFile or dataRandom
        Scanner scanThis = new Scanner(System.in);
        boolean flag = false;
        int temp = 0;
        System.out.println("Enter simulation time(positive integer) : ");
        if (scanThis.hasNextInt()) {
            temp = scanThis.nextInt();
            if (temp < 10000 && temp > 0){
                simulationTime = temp;
            }
        }
        
        System.out.println("Enter maximum transaction time of customers : ");
        if (scanThis.hasNextInt()) {
            temp = scanThis.nextInt();
            if (temp <= 500 && temp > 0){
                maxTransactionTime = temp;
            }
        }
        System.out.println("Enter chances (0% < & <= 100%) of new customer : ");
        if (scanThis.hasNextInt()) {
            temp = scanThis.nextInt();
            if (temp <= 100 && temp > 0){
                chancesOfArrival = temp;
            }
        }
        System.out.println("Enter the number of tellers : ");
        if (scanThis.hasNextInt()) {
            temp = scanThis.nextInt();
            if (temp <= 10 && temp > 0){
                numTellers = temp;
            }
        }
        System.out.println("Enter customer queue limit : ");
        if (scanThis.hasNextInt()) {
            temp = scanThis.nextInt();
            if (temp <= 50 && temp > 0){
                customerQLimit = temp;
            }
        }

        System.out.println("Enter 1/0 to get data from file/Random : ");
        if (scanThis.nextInt() == 1) {
            String fName = "DataFile";
            System.out.println("Enter filename : ");
            if (scanThis.hasNext(fName)) {
                getCustomerData(fName);
            }
        } else {
            getCustomerData();
        }

        //
    }

    // Method used to get customer data from random number generator
    private void getCustomerData() {
        // get next customer data : from file or random number generator
        // set anyNewArrival and transactionTime
        anyNewArrival = ((dataRandom.nextInt(100)+1) <= chancesOfArrival);
        transactionTime = dataRandom.nextInt(maxTransactionTime)+1;
    }

    // Method used to get customer data from file name
    private void getCustomerData(String name) {

    }

    private void doSimulation() {
	// add statements

        // Initialize ServiceArea
        ServiceArea test = new ServiceArea(10, 10, 1);

        // Time driver simulation loop
        for (int currentTime = 0; currentTime < simulationTime; currentTime++) {

            // Step 1: any new customer enters the bank?
            //getCustomerData();
            if (anyNewArrival) {

                // Step 1.1: setup customer data
                // Step 1.2: check customer waiting queue too long?
            } else {
                System.out.println("\tNo new customer!");
            }

            // Step 2: free busy tellers, add to free tellerQ
            // Step 3: get free tellers to serve waiting customers 
        } // end simulation loop

        // clean-up
    }

    private void printStatistics() {
        // add statements into this method!
        // print out simulation results
        // see the given example in project statement
        // you need to display all free and busy tellers
    }

    // *** main method to run simulation ****
    public static void main(String[] args) {
        BankSimulator runBankSimulator = new BankSimulator();
        runBankSimulator.setupParameters();
        runBankSimulator.doSimulation();
        runBankSimulator.printStatistics();
    }

}
