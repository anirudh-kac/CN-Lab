//8. Write a program to find the shortest path between vertices using Bellman ford algorithm.

import java.util.*;

public class Bellman {
    private int n;
    private int[] d;

    public static final int max_val = 999;

    Bellman(int n){
        this.n = n;
        d = new int[n];
    }

    public void bellmanFord(int a[][] , int source){
        for(int i =0 ;i<n;i++){
            d[i] = max_val;
        }

        d[source] = 0;

        for(int u = 0 ;u<n;u++){
            for(int v = 0 ; v<n;v++){
                if(a[u][v] !=max_val){
                    if(d[v] > d[u] + a[u][v]){
                        d[v] = d[u] + a[u][v];
                    }
                }
            }
        }

        for(int u = 0 ;u<n;u++){
            for(int v = 0 ; v<n;v++){
                if(a[u][v] !=max_val){
                    if(d[v] > d[u] + a[u][v]){
                        System.out.println("Graph contains negative edge cycle");
                    }
                }
            }
        }

        for(int i =0 ;i<n;i++){
            System.out.println("Distance from source node to node " + i + "is " + d[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a[][] , num_ver;
        int source;

        System.out.println("Enter number of vertices");
        num_ver = sc.nextInt();

        a = new int[num_ver][num_ver];

        System.out.println("Enter adjacency matrix ");

        for(int i =0 ;i<num_ver;i++){
            for(int j=0;j<num_ver;j++){
                a[i][j] = sc.nextInt();
            }
        }

        System.out.println("Enter source vertex");
        source = sc.nextInt();

        Bellman bellman = new Bellman(num_ver);
        bellman.bellmanFord(a, source);
    }
    
}
