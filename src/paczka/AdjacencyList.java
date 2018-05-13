package paczka;

import java.util.Arrays;
import java.util.LinkedList;

public class AdjacencyList {
	private LinkedList<Integer>[] adjacencyList;
	public AdjacencyList(int n){
		adjacencyList = (LinkedList<Integer>[]) new LinkedList[n];
		for(int i = 0;i < adjacencyList.length; i++){
			adjacencyList[i] = new LinkedList<>();
		}
	}
	public void addEdge(int start, int end){
		adjacencyList[start].add(end);
	}
	public int getN(){
		return adjacencyList.length;
	}
	public LinkedList<Integer> getVertices(int start){
		return adjacencyList[start];
	}
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < adjacencyList.length; i++) {
			s.append(i+":");
			for (Integer v : adjacencyList[i]) {
				s.append(v+",");
			}
			s.deleteCharAt(s.length()-1);
			s.append("\n");
		}
		return s.toString();
	}
	
}
