package com.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Task3 {
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

    static int getSumOfDigits(BigInteger n) {
        // convert a string object to an array of primitive characters
        char[] chars = n.toString().toCharArray();
        int sum = 0;
        // loop through an array of characters and sum converted digits
        for(char c: chars) {
            sum += Character.getNumericValue(c);
        }
        return sum;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            // prompt user for input
            System.out.print("Enter N: ");
            // read and parse the input as BigInt
            BigInteger n = BigInteger.valueOf(Integer.parseInt(reader.readLine()));
            // compute factorial according to the enterd number 'n'
            BigInteger factorialOfN = getFactorial(n);
            // compute and print sum of digits
            System.out.println(String.format("Result: %d", getSumOfDigits(factorialOfN)));
        } catch (Exception e) {
            // print errors during input or computing
            e.printStackTrace();
        }
    }
}
