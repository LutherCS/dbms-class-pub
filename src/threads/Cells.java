/*
    Java threads example
*/

import java.math.*;
import java.io.*;
import java.text.ParseException;
import java.text.DecimalFormat;

class TArray {
    int TotCells; //total number of cells
    private int cells[]; //array of cells
    TArray(int n, int k) {
        cells = new int[n];
        cells[0] = k;
        TotCells = n;
    }
    //put_to_Cell
    synchronized public void put(int pos, int q) {
        cells[pos]=q;
    }
    //get_from_Cell
    synchronized public int get(int pos) {
        return cells[pos];
    }
    // move left
    synchronized int left (int pos_) {
        if (pos_ > 0) {
            put(pos_, get(pos_)-1);
            put(pos_-1, get(pos_-1)+1);
            pos_--;
        } else {
            pos_=right(pos_);
        }
        return pos_;
    }
    // move right
    synchronized int right (int pos_) {
        if (pos_ < TotCells-1) {
            put(pos_, get(pos_)-1);
            put(pos_+1, get(pos_+1)+1);
            pos_++;
        } else {
            pos_=left(pos_);
        }
        return pos_;
    }
    //showcur
    synchronized void state() {
        int sum=0;
        for (int i=0; i < TotCells; i++) {
            System.out.print(get(i)+" ");
            sum += get(i);
        }
        //System.out.println();
        System.out.println("            Total: "+sum);
        //System.out.println();
    }
}

class myThread extends Thread {
    TArray tArr;
    int num;
    double prob;
    int curpos;

    myThread(TArray tArr_, int num_, double prob_) {
        num = num_;
        prob = prob_;
        this.tArr = tArr_;
        
        //System.out.println("New thread was created");
        curpos = 0;
        this.start();
    }
    
    public void run() {
        System.out.println("New thread created");
        while (true) {
            if (Math.random()<prob) {
                curpos=tArr.left(curpos);
            } else {
                curpos=tArr.right(curpos);
            }
        }
    }
}

public class Cells {
    public Cells() {}
    static public void main(String args[]) {
        new Cells();
        if (args.length!=3) {
            System.err.println("Incorrect number of arguments.");
            System.err.println("Args are: <Number of Cells> <Number of Particles> <Probability>");
            return;
        }
        try {
            DecimalFormat decimalFormat = new DecimalFormat();
            int TotCells = decimalFormat.parse(args[0]).intValue();
            int TotParticles = decimalFormat.parse(args[1]).intValue();
            double Prob = decimalFormat.parse(args[2]).doubleValue();
            TArray cells = new TArray(TotCells, TotParticles);
            for (int i=0; i < TotParticles; i++) {
                new myThread(cells, TotCells, Prob);
            }
            try {
                for (int i=0;i<10;i++) {
                    System.out.println(i+") Current Statement: ");
                    cells.state();
                    //wait 1 second
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted");
            }
            System.out.println("The end of the main thread. Press Ctrl+C to exit.");
        } catch (ParseException e) {
            System.err.println("Wrong number of arguments.");
            System.err.println(e.toString());                                                        //check
        }
    }
}