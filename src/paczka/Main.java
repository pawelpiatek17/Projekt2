package paczka;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class Main {
	private static AdjacencyList graph;
	static AdjacencyList revGraph;
	private static Stack<Integer> stack;
	private static boolean[] visited;
	private static LinkedList<LinkedList<Integer>> sccList;
	private static LinkedList<LinkedList<Integer>> rows;
	private static int gray = 0;
	private static int white = 1;
	private static int green = 2;
	private static int[] sortStatus;
	public static void main(String[] args) {
		graph = new AdjacencyList(13);
		sccList = new LinkedList<>();
		sortStatus = new int[graph.getN()];
		stack = new Stack<>();
		int v = 1;
//		graph.addEdge(0,2);
//		graph.addEdge(1, 0);
//		graph.addEdge(1, 2);
//		graph.addEdge(3, 0);
//		graph.addEdge(3, 4);
//		graph.addEdge(4, 1);
//		graph.addEdge(5, 0);
//		graph.addEdge(5, 4);
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 0);
		graph.addEdge(3, 4);
		graph.addEdge(4, 2);
		graph.addEdge(4, 5);
		graph.addEdge(5, 6);
		graph.addEdge(6, 4);
		graph.addEdge(7, 6);
		graph.addEdge(8, 10);
		graph.addEdge(9, 8);
		graph.addEdge(10, 11);
		graph.addEdge(11, 0);
		graph.addEdge(11, 9);
		graph.addEdge(11, 12);
		graph.addEdge(12, 0);
		graph.addEdge(12, 1);
		System.out.println(graph.toString());
		//System.out.println(getTranspose(graph).toString());
		scc(v);
		stack.clear();
		sort();
		System.out.println("c) = " + minRowsC());
		System.out.print("Rows: ");
		System.out.print(rows.toString());
		System.out.println();
		graph = getTranspose(graph);
		System.out.println(graph.toString());
		System.out.println("c) = " + minRowsC());
		System.out.print("Rows: ");
		System.out.print(rows.toString());
	}
	private static void dfsStack(int v){
		visited[v] = true;
		for (Integer i : graph.getVertices(v)) {
			if(!visited[i]){
				dfsStack(i);
			}
		}
		stack.push(v);
	}
	private static void scc(int v){
		int n = graph.getN();
		visited = new boolean[graph.getN()];
		Arrays.fill(visited, false);
		for(int i = 0; i < n; i++){
			if(!visited[i]){
				dfsStack(i);
			}
		}
		revGraph = getTranspose(graph);
		Arrays.fill(visited, false);
		while(!stack.empty()){
			int u = stack.pop();
			if(!visited[u]){
				System.out.print("ssc : ");
				dfsPrint(u);
				System.out.println();
			}
		}
	}
	private static void dfsPrint(int v) {
		visited[v] = true;
		System.out.print(v + " ");
		for (Integer u : revGraph.getVertices(v)) {
			if(!visited[u]){
				dfsPrint(u);
			}
		}
	}
	private static AdjacencyList getTranspose(AdjacencyList g)
    {
        AdjacencyList list = new AdjacencyList(g.getN());
        for (int i = 0; i < g.getN(); i++)
        {
            for (Integer u  : g.getVertices(i)) {
				list.addEdge(u, i);
			}
        }
        return list;
    }
	private static boolean dfsSort(int v){
		if(sortStatus[v] == gray){
			return false;
		}
		if(sortStatus[v] == green){
			return true;
		}
		sortStatus[v] = gray;
		for (Integer u : graph.getVertices(v)) {
			if(!dfsSort(u)){
				return false;
			}
		}
		sortStatus[v] = green;
		stack.push(v);
		return true;
	}
	private static void sort(){
		int n = graph.getN();
		Arrays.fill(sortStatus, white);
		for(int i = 0;i < n; i++){
			if(sortStatus[i] == white){
				if(dfsSort(i) == false){
					System.out.println("a) = no");
					return;
				}
			}
		}
		if(stack.size() == n){
			System.out.print("a) = ");
			for(int i = 0; i < n;i++){
				System.out.print(stack.pop()+ " ");
			}
		}
		System.out.println();
		return;
	}
	private static int minRowsC(){
		rows = new LinkedList<>();
		mainloop:
		for(int v = 0; v < graph.getN(); v++){
			loop:
			for (LinkedList<Integer> linkedList : rows) {
				for (Integer integer : linkedList) {
					if(graph.getVertices(v).contains(integer.intValue()) || graph.getVertices(integer).contains(v)){
						continue loop;
					}
				}
				linkedList.add(v);
				continue mainloop;
			}	
			rows.add(new LinkedList<>());
			rows.get(rows.size()-1).add(v);
		}
		return rows.size();
	}
	private static int minRowsC2(){
		rows = new LinkedList<>();
		mainloop:
		for(int v = 0; v < graph.getN(); v++){
			loop:
			for (LinkedList<Integer> linkedList : rows) {
				for (Integer integer : linkedList) {
					if(graph.getVertices(v).contains(integer.intValue()) || graph.getVertices(integer).contains(v)){
						continue loop;
					}
				}
				linkedList.add(v);
				continue mainloop;
			}	
			rows.add(new LinkedList<>());
			rows.get(rows.size()-1).add(v);
		}
		return rows.size();
	}
}
