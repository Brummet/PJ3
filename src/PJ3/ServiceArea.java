package PJ3;

import java.util.*;

//--------------------------------------------------------------------------
//
// Define simulation queues in a service area. Queues hold references to Customer 
// and Teller objects
//
// Customer (FIFO) queue is used to hold waiting customers. If the queue is too long
// (i.e. >  customerQLimnit), customer goes away without entering customer queue
//
// There are several tellers in a service area. Use PriorityQueue to 
// hold BUSY tellers and FIFO queue to hold FREE tellers, 
// i.e. a teller that is FREE for the longest time should start be used first.
//
// To handle teller in PriorityQueue, we need to define comparator 
// for comparing 2 teller objects. Here is a constructor from Java API:
//
// 	PriorityQueue(int initialCapacity, Comparator<? super E> comparator) 
//
// For priority queue, the default compare function is "natural ordering"
// i.e. for numbers, minimum value is returned first
//
// User can define own comparator class for PriorityQueue.
// For teller objects, we like to have smallest end busy interval time first.
//
// The following class define compare() for two tellers :
class CompareTeller implements Comparator<Teller> {

    // overide compare() method
    public int compare(Teller o1, Teller o2) {
        return o1.getEndBusyIntervalTime() - o2.getEndBusyIntervalTime();
    }
}

// DO NOT ADD NEW METHODS OR DATA FIELDS
class ServiceArea {

    // Private data fields:
    // define one priority queue 
    private PriorityQueue<Teller> busyTellerQ;

    // define two FIFO queues
    private Queue<Customer> customerQ;
    private Queue<Teller> freeTellerQ;

    // define customer queue limit
    private int customerQLimit;

    // Constructor 
    public ServiceArea() {
        // add statements
    }

    // Constructor 
    public ServiceArea(int numTellers, int customerQlimit, int startTellerID) {
        // use ArrayDeque to construct FIFO queue objects
        customerQ = new ArrayDeque();
        freeTellerQ = new ArrayDeque();

        // construct PriorityQueue object
        busyTellerQ = new PriorityQueue();
        // overide compare() in Comparator to compare Teller objects
        busyTellerQ = new PriorityQueue<Teller>(numTellers,
                new CompareTeller());

        // initialize customerQlimit
        this.customerQLimit = customerQlimit;
        
        // Construct Teller objects and insert into FreeTellerQ
        Teller test;  
        while (numTellers !=0) {
            freeTellerQ.add(test = new Teller(startTellerID));
            startTellerID++;
            numTellers--;
            //System.out.println(test.toString());
            // tellers are now adding up to 10, dunno about queue yet
            //System.out.println(freeTellerQ.peek());
            //System.out.println(freeTellerQ.poll());
        }
        // add statements
    }

    public Teller removeFreeTellerQ() {
        // remove and return a free teller
        // Add statetments
        // add try/catch block for whatever calls this
        return freeTellerQ.remove();
    }

    public Teller removeBusyTellerQ() {
        // remove and return a busy teller
        // Add statetments
        return busyTellerQ.remove();
    }

    public Customer removeCustomerQ() {
        // remove and return a customer 
        // Add statetments
        return customerQ.remove();
    }

    public void insertFreeTellerQ(Teller teller) {
        // insert a free teller
        freeTellerQ.add(teller);
        // Add statetments
    }

    public void insertBusyTellerQ(Teller teller) {
        // insert a busy teller
        // Add statetments
        busyTellerQ.add(teller);
    }

    public void insertCustomerQ(Customer customer) {
        // insert a customer 
        // Add statetments
        customerQ.add(customer);
    }

    public boolean emptyFreeTellerQ() {
        // is freeTellerQ empty?
        // Add statetments
        boolean check = freeTellerQ.isEmpty();
        return check;
    }

    public boolean emptyBusyTellerQ() {
        // is busyTellerQ empty?
        // Add statetments
        boolean check = busyTellerQ.isEmpty();
        return check;
    }

    public boolean emptyCustomerQ() {
        // is customerQ empty?
        // Add statetments
        boolean check = customerQ.isEmpty();
        return check;
    }

    public int numFreeTellers() {
        // get number of free tellers
        // Add statetments
        int counter = freeTellerQ.size();
        return counter;
    }

    public int numBusyTellers() {
        // get number of busy tellers
        // Add statetments
        return 0;
    }

    public int numWaitingCustomers() {
        // get number of customers 
        // Add statetments
        int counter = customerQ.size();
        return counter;
    }

    public Teller getFrontBusyTellerQ() {
        // get front of busy tellers
        // "retrieve" but not "remove"
        // Add statetments
        return busyTellerQ.peek();
    }

    public boolean isCustomerQTooLong() {
        // is customerQ too long?
        // Add statetments
        boolean check = false;
        if (numWaitingCustomers() > customerQLimit){
            check = true;
        }
        else check = false;
        
        return check;
    }

    public void printStatistics() {
        System.out.println("\t# waiting customers : " + numWaitingCustomers());
        System.out.println("\t# busy tellers      : " + numBusyTellers());
        System.out.println("\t# free tellers      : " + numFreeTellers());
    }

    public static void main(String[] args) {

        // quick check
        ServiceArea sc = new ServiceArea(4, 5, 1001);
        Customer c1 = new Customer(1, 18, 10);
        Customer c2 = new Customer(2, 33, 10);
        Customer c3 = new Customer(3, 21, 10);
        Customer c4 = new Customer(3, 37, 10);
        sc.insertCustomerQ(c1);
        sc.insertCustomerQ(c2);
        sc.insertCustomerQ(c3);
        System.out.println("" + sc.customerQ);
        System.out.println("Remove customer:" + sc.removeCustomerQ());
        System.out.println("Remove customer:" + sc.removeCustomerQ());
        System.out.println("Remove customer:" + sc.removeCustomerQ());

        System.out.println("" + sc.freeTellerQ);
        Teller p1 = sc.removeFreeTellerQ();
        Teller p2 = sc.removeFreeTellerQ();
        Teller p3 = sc.removeFreeTellerQ();
        Teller p4 = sc.removeFreeTellerQ();
        System.out.println("Remove free teller:" + p1);
        System.out.println("Remove free teller:" + p2);
        System.out.println("Remove free teller:" + p3);
        System.out.println("Remove free teller:" + p4);

        p1.freeToBusy(c1, 13);
        p2.freeToBusy(c2, 13);
        p3.freeToBusy(c3, 13);
        p4.freeToBusy(c4, 13);
        sc.insertBusyTellerQ(p1);
        sc.insertBusyTellerQ(p2);
        sc.insertBusyTellerQ(p3);
        sc.insertBusyTellerQ(p4);
        System.out.println("" + sc.busyTellerQ);
        p1 = sc.removeBusyTellerQ();
        p2 = sc.removeBusyTellerQ();
        p3 = sc.removeBusyTellerQ();
        p4 = sc.removeBusyTellerQ();
        System.out.println("Remove busy teller:" + p1);
        System.out.println("Remove busy teller:" + p2);
        System.out.println("Remove busy teller:" + p3);
        System.out.println("Remove busy teller:" + p4);

    }

};
