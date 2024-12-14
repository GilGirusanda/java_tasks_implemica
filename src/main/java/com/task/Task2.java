package com.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

public class Task2 {

    // class to represent a city (index) and the weight (distance)
    static class Node implements Comparable<Node> {
        int index;
        int weight;
    
        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
        
        // compare nodes by their weights, for priority queue
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.weight, other.weight);
        }
    }    

    // class to represent the cities and connections using adjacency matrix
    static class Graph {
        private int size; // num of cities
        private int[][] matrix; // adjacency matrix to store connections and weights

        public Graph(int size) {
            this.size = size;
            this.matrix = new int[this.size][this.size];

            // init all the weights to 0
            for (int i = 0; i < this.size; i++)
                for (int j = 0; j < this.size; j++)
                    this.matrix[i][j] = 0;
        }

        public Graph() {
            this(1);
        }

        // print the adj matrix (just for debug)
        @Override
        public String toString() {
            StringBuilder output = new StringBuilder();
            
            for (int i = 0; i < this.size; i++) {
                for (int j = 0; j < this.size; j++) {
                    output.append(String.format("%d\t", this.matrix[i][j]));
                }
                output.append('\n');
            }

            return output.toString();
        }
        
        // add an edge between two cities with a given weight
        public void add(int i, int j, int weight) {
            if (weight <= 0) {
                System.out.println("Enter valid weight value (w > 0)");
            }

            if (i > this.size - 1 || j > this.size - 1) {
                System.out.println("Invalid vertex");
            } else if (i == j) {
                System.out.println("Same vertex");
            } else {
                this.matrix[i][j] = weight;
            }
        }

        // remove an edge btw two cities
        public void remove(int i, int j) {
            if (i > this.size - 1 || j > this.size - 1) {
                System.out.println("Invalid vertex");
            } else if (i == j) {
                System.out.println("Same vertex");
            } else {
                this.matrix[i][j] = 0;
            }
        }

        // find the shortest path from a source city to a target city
        // utilizing algorithm of Dijkstra
        public int getShortestPath(int srcIndex, int targetIndex) {
            int[] dist = new int[this.size]; // array of distances btw source and the rest of cities
            Arrays.fill(dist, Integer.MAX_VALUE); // set default distances to INT MAX 
            dist[srcIndex] = 0; // init distance btw source city and itself

            PriorityQueue<Node> pq = new PriorityQueue<>(); // init min-heap
            pq.add(new Node(srcIndex, 0));

            // process nodes in the queue
            while (!pq.isEmpty()) {
                Node current = pq.poll(); // get the head and remove it from the queue
                int index = current.index;
                int currentWeight = current.weight;

                // check neighbours of the current vertex
                for (int neighbour = 0; neighbour < this.size; neighbour++) {
                    int weight = this.matrix[index][neighbour];

                    // if there is a connection
                    if (weight > 0) {
                        int newDist = currentWeight + weight;

                        // if there is a shorter path, update distance
                        if (newDist < dist[neighbour]) {
                            dist[neighbour] = newDist;
                            pq.add(new Node(neighbour, newDist));
                        }
                    }
                }
            }

            // return the path weight to the target city
            return dist[targetIndex];
        }
    }
        
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Input:");
            int numberOfTests = Integer.parseInt(reader.readLine()); // init number of passes
            int counter = 0;

            while ((counter += 1) <= numberOfTests) {
                int numberOfCities = Integer.parseInt(reader.readLine()); // init number of cities per pass
                    
                Map<String, Integer> citynameToIndexMapping = new HashMap<>();

                Graph g = new Graph(numberOfCities);
                for (int i = 0, neighbourIndex = 0, weight = 0; i < numberOfCities; i++) {
                    String cityName = reader.readLine(); // read city name
                    citynameToIndexMapping.put(cityName, i); // map city name to its index
                    int numberOfNeighbours = Integer.parseInt(reader.readLine());
                    for (int k = 0; k < numberOfNeighbours; k++) {
                        // split neighbour 1-based index and path weight to it
                        String[] input = reader.readLine().split(" ", 2);
                        neighbourIndex = Integer.parseInt(input[0]);
                        weight = Integer.parseInt(input[1]);
                        // add connection to the graph with 0-based indexing 
                        g.add(i, neighbourIndex - 1, weight);
                    }
                }

                // init number of routes to calculate
                int numberOfPathsToFind = Integer.parseInt(reader.readLine());
                int resultingWeights[] = new int[numberOfPathsToFind];

                for (int i = 0; i < numberOfPathsToFind; i++) {
                    String cityNeighbours[] = reader.readLine().split(" ", 2);
                    Integer cityFromIdx = citynameToIndexMapping.get(cityNeighbours[0]); // init source city
                    Integer cityToIdx = citynameToIndexMapping.get(cityNeighbours[1]); // init target city
                    
                    // check if the indices present, in case of invalid neighbour name
                    if (Objects.isNull(cityFromIdx)) {
                        System.out.println("City 'from' name is invalid");
                        continue;
                    }
                    if (Objects.isNull(cityToIdx)) {
                        System.out.println("City 'to' name is invalid");
                        continue;
                    }

                    // compute the shortest path weight
                    resultingWeights[i] = g.getShortestPath(cityFromIdx, cityToIdx);
                }

                // print the results
                System.out.println("\nOutput:");
                for (int val: resultingWeights)
                    System.out.println(val);
            }

        } catch (Exception e) {
            e.printStackTrace(); // print errors if any
        }
    }    
}
