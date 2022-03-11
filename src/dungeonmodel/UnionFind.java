package dungeonmodel;

import java.util.Arrays;

/**
 * Union find is a utility class to combine disjoinnt set.
 * It initializes parent and rank array. It keeps track of 
 * the disjoint set, parent and rank.
 *
 */
class UnionFind {
  private int[] parent; 
  private int[] rank;

  /**
   * Constructor to create a union find object.
   * It initializes the parent and rank array with 0.
   *  
   * @param size represents the size of parent and rank. 
   */
  protected UnionFind(int size) {
    parent = new int[size];
    rank = new int[size];
    Arrays.fill(rank, 0);
    for (int i = 0; i < size; i++) {
      parent[i] = i;
    }
  }

  /**
   * Helper method to find the root of the current set.
   * @param i represent to element whose root is required.
   * @return return the root of the given element.
   */
  private int find(int i) {
    if (parent[i] != i) {
      parent[i] = find(parent[i]);
    }
    return parent[i];
  }

  /**
   * Returns true if it is possible to combine disjoint 
   * set, else return false.
   * 
   * @param x represent first element of disjoint set.
   * @param y represent second element of the set.
   * @return return true if union of sets is possible.
   */
  protected boolean union(int x, int y) {
    int i = find(x);
    int j = find(y);
    if (i == j) {
      return false;
    }
    if (rank[i] > rank[j]) { 
      parent[j] = i;
    } else {
      parent[i] = j;
    }
    if (rank[i] == rank[j]) {
      rank[j] = rank[j] + 1;
    }
    return true;
  }
}