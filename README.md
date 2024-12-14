# Description

This project includes solutions to three computational problems, implemented in Java, each addressing a unique algorithmic challenge:

# Requirements

This project is configured as a Maven-based Java application. Below are the requirements to build and run the project:

## Prerequisites

1. **Java Development Kit (JDK)**  
   - Required Version: 23

2. **Apache Maven**  
   - Required Version: 3.6.0 or later

## How to Build and Run

1. Clone or download the project to your local machine.
2. Navigate to the project's root directory where the `pom.xml` file is located.
3. Set up the project using Maven:
    ```bash
    mvn clean install
    ```
4. To run the application `Task1` (there are also `Task2`, `Task3`):
    
    Build:
    ```bash
    mvn package -P task1
    ```

    Run:
    ```bash
    java -jar target\solution-task1.jar
    ```

## 1. Counting Correct Bracket Expressions

**Problem Statement:**

Given a number N, find the number of correct bracket expressions that can be formed using N opening and N closing parentheses. A correct bracket expression is defined as one where every opening parenthesis has a corresponding closing parenthesis in the correct order.

**Example:**
```Input: N = 1
Valid: ()
Invalid: )(, )), ((
Result: 1 correct expression.
Input: N = 2
Valid: ()(), (())
Result: 2 correct expressions.
```

## 2. Finding Minimum Transportation Cost Between Cities

**Problem Statement:**

Given a list of cities and their transportation costs (positive integers), determine the minimum cost path between pairs of specified cities. Each city is represented as a string with at most 10 characters. The solution supports multiple test cases, with constraints on the number of cities and queries.

**Input Format:**

- s: Number of test cases (≤ 10).
- n: Number of cities (≤ 10000).
- p: Number of neighbors for a city, followed by the neighbor index and cost.
- r: Number of queries, followed by the source and destination city pairs.

**Output:** 
Minimum cost for each query.

**Example Input/Output:**

```
Input:
1
4
gdansk
2
2 1
3 3
bydgoszcz
3
1 1
3 1
4 4
torun
3
1 3
2 1
4 1
warszawa
2
2 4
3 1
2
gdansk warszawa
bydgoszcz warszawa

Output:
3
2
```

## 3. Sum of Digits in 100 Factorial

**Problem Statement:**

Compute the sum of the digits of the number 100! (100 factorial).

**Example Output:**

```
(for 100!)
Sum of digits = 648
```