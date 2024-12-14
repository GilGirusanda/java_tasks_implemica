package com.task;

import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Task1 
{
    // memoization map to store computed factorial values for better efficiency
    private static Map<BigInteger, BigInteger> mem = new HashMap<>();

    // method to compute factorial of a number using memoization and recursion
    static BigInteger getFactorial(BigInteger n) {
        // check for negative input
        if (n.compareTo(BigInteger.ZERO) == -1) {
            throw new IllegalArgumentException("n must be non-negative");
        }
        // check primitive case: 0! = 1, 1! = 1
        if (n.compareTo(BigInteger.ZERO) == 0 || n.compareTo(BigInteger.ONE) == 0) {
            return BigInteger.ONE;
        }
        // get cached result if any
        if (mem.containsKey(n)) {
            return mem.get(n);
        }
        // recursively calculate factorial and store it in memoization map
        BigInteger val = n.multiply(getFactorial(n.subtract(BigInteger.ONE)));
        mem.put(n, val);
        return val;
    }

    // method to compute the nth Catalan number using the next formula:
    // Cn = (2n)! / (n! * (n+1)!)
    static BigInteger getCatalanNumber(BigInteger n) {
        return getFactorial(n.multiply(BigInteger.TWO))
                .divide(getFactorial(n).multiply(getFactorial(n.add(BigInteger.ONE))));
    }

    public static void main( String[] args )
    {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            // prompt user for input
            System.out.print("Enter N: ");
            // read and parse the input as BigInt
            BigInteger n = BigInteger.valueOf(Integer.parseInt(reader.readLine()));
            // compute and print Catalan number
            System.out.println(String.format("Result: %d", getCatalanNumber(n)));
        } catch (Exception e) {
            // print errors during input or computing
            e.printStackTrace();
        }
    }
}
