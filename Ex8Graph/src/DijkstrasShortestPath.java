import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class DijkstrasShortestPath {
	private int distances[];
	private Set<Integer> settled;
	private Set<Integer> unsettled;
	private int numberOfNodes;
	private int adjacencyMatrix[][];
	
	public DijkstrasShortestPath(int numberOfNodes) {
		this.numberOfNodes = numberOfNodes;
		distances = new int[numberOfNodes + 1];
		settled = new HashSet<Integer>();
		unsettled = new HashSet<Integer>();
		adjacencyMatrix = new int[numberOfNodes + 1][numberOfNodes + 1];
	}
	public void dijkstrasAlgorithm(int adjacencyMatrix[][], int source) {
		int evaluationNode;
		for(int i = 1; i <= numberOfNodes; i++) {
			for(int j = 1; j <= numberOfNodes; j++) {
				this.adjacencyMatrix[i][j] = adjacencyMatrix[i][j];
			}
		}
		for(int i = 1; i <= numberOfNodes; i++)
			distances[i] = Integer.MAX_VALUE;
		unsettled.add(source);
		distances[source] = 0;
		while(!unsettled.isEmpty()) {
			evaluationNode = getNodeWithMinDistanceUnsettled();
			unsettled.remove(evaluationNode);
			settled.add(evaluationNode);
			evaluateNeighbours(evaluationNode);
		}
	}
	private int getNodeWithMinDistanceUnsettled() {
		int node = 0;
		int min;
		Iterator<Integer> iterator = unsettled.iterator();
		node = iterator.next();
		min = distances[node];
		for(int i = 1; i <= distances.length; i++) {
			if(unsettled.contains(i)) {
				if(distances[i] <= min) {
					min = distances[i];
					node = i;
				}
			}
		}
		return node;
	}
	private void evaluateNeighbours(int evaluationNode) {
		int edgeDistance = -1;
		int newDistance = -1;
		for(int destinationNode = 1; destinationNode <= numberOfNodes; destinationNode++) {
			if(!settled.contains(destinationNode)) {
				if(adjacencyMatrix[evaluationNode][destinationNode] != Integer.MAX_VALUE) {
					edgeDistance = adjacencyMatrix[evaluationNode][destinationNode];
					newDistance = distances[evaluationNode] + edgeDistance;
					if(newDistance < distances[destinationNode])
						distances[destinationNode] = newDistance;
					unsettled.add(destinationNode);
				}
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int adjecencyMatrix[][];
		int numberOfvertices;
		int source = 0, destination = 0;
		Scanner scan = new Scanner(System.in);
		try {
			System.out.println("Enter the number of vertices");
			numberOfvertices = scan.nextInt();
			adjecencyMatrix = new int[numberOfvertices + 1][numberOfvertices + 1];
			
			System.out.println("Enter the weighted Matrix for graph.");
			for(int i = 1; i <= numberOfvertices; i++) {
				for(int j = 1; j <= numberOfvertices; j++) {
					adjecencyMatrix[i][j] = scan.nextInt();
					if(i == j) {
						adjecencyMatrix[i][j] = 0;
						continue;
					}
					if(adjecencyMatrix[i][j] == 0)
						adjecencyMatrix[i][j] = Integer.MAX_VALUE;
				}
			}
			System.out.print("\nEnter the source: ");
			source = scan.nextInt();
			System.out.print("\nEnter the destination: ");
			destination = scan.nextInt();
			DijkstrasShortestPath dijkstras = new DijkstrasShortestPath(numberOfvertices);
			dijkstras.dijkstrasAlgorithm(adjecencyMatrix, source);
			System.out.println("The Shortest Path from " + source + " to " + destination + " is ");
			for(int i = 1; i <= dijkstras.distances.length - 1; i++) {
				if(i == destination)
					System.out.println(source + " to " + i + " is " + dijkstras.distances[i]);
			}
		} catch (InputMismatchException e) {
			System.out.println("Worng Input Format!!!");
		}
		scan.close();
	}
}
