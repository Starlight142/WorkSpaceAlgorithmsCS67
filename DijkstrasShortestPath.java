import java.util.*;

public class DijkstrasShortestPath {// Main class for computing the shortest path using Dijkstra's algorithm
	private int distances[];// Stores the shortest distances from the source to each node
	private Set<Integer> settled;// Nodes whose shortest distance has been finalized
	private Set<Integer> unsettled;// Nodes that are still being processed
	private int numberOfNodes;// Total number of nodes in the graph
	private int adjacencyMatrix[][];// Matrix storing edge weights between nodes
	
	public DijkstrasShortestPath(int numberOfNodes) { // prepares initial data structures
		this.numberOfNodes = numberOfNodes;			// Set number of nodes
		distances = new int[numberOfNodes + 1];		// Create distance array (index starts from 1)
		settled = new HashSet<Integer>();			// Set for finalized nodes
		unsettled = new HashSet<Integer>();			// Set for unprocessed nodes
		adjacencyMatrix = new int[numberOfNodes + 1][numberOfNodes + 1];	// Table for graph weights
	}
	
	public void dijkstarsAlgorithm(int adjacencyMatrix[][], int source) {	// executes Dijkstra's algorithm
		int evaluationNode;								
		for(int i = 1; i <= numberOfNodes; i++) {		// Copy input matrix into the class variable
			for(int j = 1; j <= numberOfNodes; j++) {	
				this.adjacencyMatrix[i][j] = adjacencyMatrix[i][j]; 
			}											
		}
		for(int i = 1; i <= numberOfNodes; i++) // Initialize all distances to infinity
			distances[i] = Integer.MAX_VALUE;
		unsettled.add(source); 					// Add the source node to unsettled (start point)
		distances[source] = 0;					// Distance from source to itself is 0
		while(!unsettled.isEmpty()) {			// Repeat until no nodes remain in unsettled
			evaluationNode = getNodeWithMinDistanceUnsettled(); // Find node with smallest distance in unsettled
			unsettled.remove(evaluationNode);		// Remove it from unsettled
			settled.add(evaluationNode);			// Add to settled (finalized)
			evaluateNeighbours(evaluationNode);		// Evaluate its neighbors
		}
	}
	
	private int getNodeWithMinDistanceUnsettled() { //Find node with the smallest distance in unsettled
		int node = 0;		// Node with the smallest distance
		int min;			// Temporary minimum distance
		Iterator<Integer> iterator = unsettled.iterator(); 	// Iterator
		node = iterator.next();		// Start with the first node in the set
		min = distances[node];		// Set initial min distance
		for(int i = 1; i <= distances.length; i++) {		 // Loop through all nodes
			if(unsettled.contains(i)) {				// If node is still unsettled	
				if(distances[i] <= min) {			// If its distance is smaller than current min
					min = distances[i];				// Update minimum distance
					node = i;
				}
			}
		}
		return node;		// Return node with smallest distance
	}
	
	private void evaluateNeighbours(int evaluationNode) { // Evaluate neighbors of the selected node
		int edgeDistance = -1;		// Edge weight
		int newDistance = -1;		// New calculated distance
		for(int distinationNode = 1; distinationNode <= numberOfNodes; distinationNode++) {
			if(!settled.contains(distinationNode)) {		// If node not finalized
				if(adjacencyMatrix[evaluationNode][distinationNode] != Integer.MAX_VALUE) { // If a path exists
					edgeDistance = adjacencyMatrix[evaluationNode][distinationNode]; // Edge weight
					newDistance = distances[evaluationNode] + edgeDistance;			// New distance = current + edge
					if(newDistance < distances[distinationNode])					// If new distance is shorter
						distances[distinationNode] = newDistance;			// Update to shorter distance
					unsettled.add(distinationNode);							// Add destination node to unsettled
				}
			}
		}
	}

	public static void main(String[] args) {		//main program
		// TODO Auto-generated method stub
		int adjacencyMatrix[][];					// Table storing edge weights
		int numberOfVertices;						// Number of graph vertices
		int source = 0, destination = 0;			// Start and end nodes
		Scanner scan = new Scanner(System.in);		// Scanner for user input
		try {
			System.out.println("Enter the number of vertices"); // Ask user for number of vertices
			numberOfVertices = scan.nextInt();						// Read vertex count
			adjacencyMatrix = new int[numberOfVertices + 1][numberOfVertices + 1]; // Create matrix
			
			System.out.println("Enter the weighted Matrix for graph.");		// Ask for weight matrix
			for(int i = 1; i <= numberOfVertices; i++) {				// Input all weights
				for(int j = 1; j <= numberOfVertices; j++) {
					adjacencyMatrix[i][j] = scan.nextInt();			// Read weight i → j
					if(i == j) {							// If same node
						adjacencyMatrix[i][j] = 0;			// Distance = 0
						continue;
					}
					if(adjacencyMatrix[i][j] == 0)			// If no path
						adjacencyMatrix[i][j] = Integer.MAX_VALUE;   // Set as infinity
				}
			}
			System.out.print("\nEnter the source: ");				// Input source node
			source = scan.nextInt();
			System.out.print("\nEnter the destination: ");			// Input destination node
			destination = scan.nextInt();
			DijkstrasShortestPath dijkstras = new DijkstrasShortestPath(numberOfVertices);		// Create object
			dijkstras.dijkstarsAlgorithm(adjacencyMatrix, source);			// Run Dijkstra
			System.out.println("The Shorted Path from " + source + " to " + destination + " is ");
			for(int i = 1; i <= dijkstras.distances.length - 1; i++) {			// Display only destination node result
				if(i == destination)
					System.out.println(source + " to " + i + " is " + dijkstras.distances[i]);		
			}
		}catch(InputMismatchException e) { // If user enters non-numeric input
			System.out.println("Wrong Input Format!!!"); // Show warning
		}
		scan.close(); // Close scanner
	}

}














