import java.util.InputMismatchException;
import java.util.Scanner;

public class Prims {
	private boolean unsettled[];
	private boolean settled[];
	private int numberofVertices;
	private int adjacencyMatrix[][];
	private int key[];
	private int parent[];
	public static final int INFINITE = 999;

	public Prims(int numberofVertices) {
		this.numberofVertices = numberofVertices;
		unsettled = new boolean[numberofVertices + 1];
		settled = new boolean[numberofVertices + 1];
		adjacencyMatrix = new int[numberofVertices + 1][numberofVertices + 1];
		key = new int[numberofVertices + 1];
		parent = new int[numberofVertices + 1];
	}
	public int getUnsettledCount(boolean unsettled[]) {
		int count = 0;
		for(int index = 0; index < unsettled.length; index++) {
			if(unsettled[index])
				count++;
		}
		return count;
	}
	private int getMimumKeyVetexFromUnsettled(boolean[] unsettled) {
		int node = 0;
		int min = Integer.MAX_VALUE;
		for(int vertex = 1; vertex <= numberofVertices; vertex++) {
			if(unsettled[vertex] == true && key[vertex] < min) {
				node = vertex;
				min = key[vertex];
			}
		}
		return node;
	}
	public void evaluateNeighbours(int evaluationVertex) {
		for(int destinationVertex = 1; destinationVertex <= numberofVertices; destinationVertex++) {
			if(settled[destinationVertex] == false) {
				if(adjacencyMatrix[evaluationVertex][destinationVertex] != INFINITE) {
					if(adjacencyMatrix[evaluationVertex][destinationVertex] < key[destinationVertex]) {
						key[destinationVertex] = adjacencyMatrix[evaluationVertex][destinationVertex];
						parent[destinationVertex] = evaluationVertex;
					}
					unsettled[destinationVertex] = true;
				}
			}
		}
	}
	public void primsAlgorithm(int adjacencyMatrix[][]) {
		int evaluationVertex;
		for(int source = 1; source <= numberofVertices; source++) {
			for(int destination = 1; destination <= numberofVertices; destination++) {
				this.adjacencyMatrix[source][destination] = adjacencyMatrix[source][destination];
			}
		}
		for(int index = 1; index <= numberofVertices; index++) {
			key[index] = INFINITE;
		}
		key[1] = 0;
		unsettled[1] = true;
		parent[1] = 1;
		while(getUnsettledCount(unsettled) != 0) {
			evaluationVertex = getMimumKeyVetexFromUnsettled(unsettled);
			unsettled[evaluationVertex] = false;
			settled[evaluationVertex] = true;
			evaluateNeighbours(evaluationVertex);
		}
	}
	public void printMST() {
		System.out.println("SOURCE : DESTINATION = WEIGHT");
		for(int vertex = 2; vertex <= numberofVertices; vertex ++)
			System.out.println(parent[vertex] + "\t:\t" + vertex + "\t:\t" + adjacencyMatrix[parent[vertex]][vertex]);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int adjacencyMatrix[][];
		int numberOFVertices;
		Scanner scan = new Scanner(System.in);
		try {
			System.out.print("Enter the number of vertex: ");
			numberOFVertices = scan.nextInt();
			adjacencyMatrix = new int[numberOFVertices + 1][numberOFVertices + 1];
			System.out.println("Enter the weight Matrix for the graph.");
			for(int i = 1; i <= numberOFVertices; i++) {
				for(int j = 1; j <= numberOFVertices; j++) {
					adjacencyMatrix[i][j] = scan.nextInt();
					if(i == j) {
						adjacencyMatrix[i][j] = 0;
						continue;
					}
					if(adjacencyMatrix[i][j] == 0)
						adjacencyMatrix[i][j] = INFINITE;
				}
			}
			Prims prims = new Prims(numberOFVertices);
			prims.primsAlgorithm(adjacencyMatrix);
			prims.printMST();
		}catch (InputMismatchException e) {
			System.out.println("Worng Input Format");
		}
		scan.close();
	}
}
