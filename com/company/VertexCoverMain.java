package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class VertexCoverMain {
    Vector<Vector<Integer>> adjList;
    Vector<Integer> degree;
    int v, e;

    public VertexCoverMain(int v, int e) {
        this.v = v;
        this.e = e;
        adjList = new Vector<>();
        degree=new Vector<>();
        for (int i = 0; i < v; i++) {
            adjList.add(new Vector<>());
            degree.add(0);
        }

    }

    int random() {
        Vector<Vector<Integer>> adjList1=new Vector<>();
        for (int i=0;i<adjList.size();i++)
        {
            adjList1.add(new Vector<>());
            for (int j=0;j<adjList.get(i).size();j++)
            {
                adjList1.get(i).add(adjList.get(i).get(j));
            }
        }
        int nE = 0, n = 0;
        boolean color[] = new boolean[v];
        for (int i = 0; i < adjList1.size(); i++) {
            Vector<Integer> vector = adjList1.get(i);
            if (vector.isEmpty()) {
                n++;
                color[i] = true;
            }
        }

        Random r = new Random();
        Vector<Integer> covers = new Vector<>();
        while (nE < 2 * e) {
            int from = r.nextInt(v);
            if (!color[from]) {
                covers.add(from);
                color[from] = true;
                n++;
                int size = (adjList1.get(from)).size();
                nE += size;
                int t = r.nextInt(size);
                int to = adjList1.get(from).get(t);
                if (!color[to]) {
                    covers.add(to);
                    color[to] = true;
                    nE += adjList1.get(to).size();
                    n++;
                    for (int i = 0; i < adjList1.size(); i++) {
                        Vector<Integer> vector1 = adjList1.get(i);
                        if (!color[i]) {
                            if (vector1.removeElement(to))
                                nE++;
                            if (vector1.removeElement(from))
                                nE++;
                        }
                        if (vector1.isEmpty())
                            color[i] = true;
                    }
                }

            }
        }

        return covers.size();
    }

    int priority() {
        Vector<Integer> degree1=new Vector<>(this.degree);
        Vector<Vector<Integer>> adjList1=new Vector<>(this.adjList);

        int nE = 0, n = 0;
        boolean color[] = new boolean[v];
        for (int i = 0; i < adjList1.size(); i++) {
            Vector<Integer> vector = adjList1.get(i);
            if (vector.isEmpty()) {
                n++;
                color[i] = true;
            }
        }

        Vector<Integer> covers = new Vector<>();
        class Degree implements Comparable<Degree> {
            int u, v, indexOfV, degree;

            public Degree(int u, int v, int indexOfV, int degree) {
                this.u = u;
                this.v = v;
                this.indexOfV = indexOfV;
                this.degree = degree;
            }

            @Override
            public int compareTo(Degree o) {
                return Integer.compare(degree, o.degree);
            }
        }

        while (nE < 2 * e) {
            Vector<Degree> degrees = new Vector<>();
            for (int i = 0; i < adjList1.size(); i++) {
                if (!color[i])
                    for (int j = 0; j < adjList1.get(i).size(); j++) {
                        degrees.add(new Degree(i, adjList1.get(i).get(j),j, degree1.get(i) + degree1.get(adjList1.get(i).get(j))));
                    }
            }
            Degree max = Collections.max(degrees);
            int from = max.u, to = max.v,ind=max.indexOfV;
            if (!color[from] && !color[to]) {
                covers.add(from);
                color[from] = true;
                degree1.set(from, 0);
                n++;
                int size = (adjList1.get(from)).size();
                nE += size;

                covers.add(to);
                color[to] = true;
                degree1.set(to , 0);
                nE += adjList1.get(to).size();
                n++;
                for (int i = 0; i < adjList1.size(); i++) {
                    Vector<Integer> vector1 = adjList1.get(i);
                    if (!color[i]) {
                        if (vector1.removeElement(to)) {
                            nE++;
                            degree1.set(i,degree1.get(i)-1);
                        }
                        if (vector1.removeElement(from))
                        {
                            nE++;
                            degree1.set(i,degree1.get(i)-1);
                        }
                    }
                    if (vector1.isEmpty())
                        color[i] = true;
                }
            }
    }

        return covers.size();
}

    static void print(String pathName) {
        try (Scanner reader = new Scanner(new File(pathName))) {
            if (reader.hasNext()) {
                String s = reader.nextLine();
                String[] strings = s.split(" ");
                Integer v = Integer.parseInt(strings[2]);
                Integer e = Integer.parseInt(strings[3]);
                VertexCoverMain graph = new VertexCoverMain(v, e);

                for (int i = 0; i < e; i++) {
                    s = reader.nextLine();
                    strings = s.split(" ");
                    Integer from = Integer.parseInt(strings[1]) - 1;
                    Integer to = Integer.parseInt(strings[2]) - 1;

                    graph.adjList.get(from).add(to);
                    graph.adjList.get(to).add(from);
                    graph.degree.set(from, graph.degree.get(from) + 1);
                    graph.degree.set(to, graph.degree.get(to) + 1);
                    //System.out.println(from+"   "+graph.adjList.elementAt(from).elementAt(graph.adjList.elementAt(from).size()-1));
                }
                System.out.print("Random : "+graph.random());
                System.out.println("   Priority : "+graph.priority());

                System.out.println();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i=1;i<6;i++)
        {
            print("src\\frb30-15-mis\\frb30-15-"+i+".mis");
        }
        for (int i=1;i<6;i++)
        {
            print("src\\frb35-17-mis\\frb35-17-"+i+".mis");
        }
        for (int i=1;i<6;i++)
        {
            print("src\\frb40-19-mis\\frb40-19-"+i+".mis");
        }
        for (int i=1;i<6;i++)
        {
            print("src\\frb45-21-mis\\frb45-21-"+i+".mis");
        }
        print("src\\c-fat200-1.clq-compliment.txt");
    }
}
