
/*ZACHARY IAN P. BAUTISTA - A122 */
import java.util.*;

public class testBautistaDS {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Select the task number to run (1-8) or 0 to exit:");
            int task = scanner.nextInt();
            scanner.nextLine(); // CHOICES
            switch (task) {
                case 1:
                    runTask1();
                    break;
                case 2:
                    runTask2();
                    break;
                case 3:
                    runTask3();
                    break;
                case 4:
                    runTask4();
                    break;
                case 5:
                    runTask5();
                    break;
                case 6:
                    runTask6();
                    break;
                case 7:
                    runTask7();
                    break;
                case 8:
                    runTask8();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Invalid task number. Please try again.");
            }
        }
    }

    /*
     * Task 1: Determine if a graph is connected and find the number of connected
     * components
     */
    private static void runTask1() {
        System.out.println("Enter the number of vertices:");
        int n = scanner.nextInt();
        System.out.println("Enter the number of edges:");
        int m = scanner.nextInt();
        List<int[]> edges = new ArrayList<>();
        System.out.println("Enter the edges (format: u v):");
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            edges.add(new int[] { (u - 1), (v - 1) });
        }

        int numComponents = countConnectedComponents(n, edges);
        System.out.println("Number of connected components: " + numComponents);
    }

    private static int countConnectedComponents(int n, List<int[]> edges) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;

        for (int[] edge : edges) {
            int root1 = find(parent, edge[0]);
            int root2 = find(parent, edge[1]);
            if (root1 != root2)
                parent[root1] = root2;
        }

        Set<Integer> components = new HashSet<>();
        for (int i = 0; i < n; i++) {
            components.add(find(parent, i));
        }
        return components.size();
    }

    private static int find(int[] parent, int node) {
        if (parent[node] != node) {
            parent[node] = find(parent, parent[node]);
        }
        return parent[node];
    }

    /* Task 2: List edges from adjacency matrix and count their occurrences */
    private static void runTask2() {
        System.out.println("Enter the size of the adjacency matrix:");
        int n = scanner.nextInt();
        int[][] adjMatrix = new int[n][n];
        System.out.println("Enter the adjacency matrix row by row:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjMatrix[i][j] = scanner.nextInt();
            }
        }
        System.out.println("Occurrences:");
        Map<String, Integer> edgeCount = listEdges(adjMatrix);
        for (Map.Entry<String, Integer> entry : edgeCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static Map<String, Integer> listEdges(int[][] adjMatrix) {
        Map<String, Integer> edgeCount = new HashMap<>();
        int n = adjMatrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (adjMatrix[i][j] > 0) {
                    String edge = i + 1 + "-" + (j + 1);
                    edgeCount.put(edge, adjMatrix[i][j]);
                }
            }
        }
        return edgeCount;
    }

    /* Task 3: Check if a graph has a cycle */
    private static void runTask3() {
        System.out.println("Enter the number of vertices:");
        int n = scanner.nextInt();
        System.out.println("Enter the number of edges:");
        int m = scanner.nextInt();
        List<int[]> edges = new ArrayList<>();
        System.out.println("Enter the edges (format: u v):");
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            edges.add(new int[] { (u - 1), (v - 1) });
        }

        boolean hasCycle = hasCycle(n, edges);
        System.out.println("Graph has cycle: " + hasCycle);
    }

    private static boolean hasCycle(int n, List<int[]> edges) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;

        for (int[] edge : edges) {
            int root1 = find(parent, edge[0]);
            int root2 = find(parent, edge[1]);
            if (root1 == root2)
                return true;
            parent[root1] = root2;
        }
        return false;
    }

    /* Task 4: Output the degree of a vertex */
    private static void runTask4() {
        System.out.println("Enter the number of vertices:");
        int n = scanner.nextInt();
        System.out.println("Enter the number of edges:");
        int m = scanner.nextInt();
        List<int[]> edges = new ArrayList<>();
        System.out.println("Enter the edges (format: u v):");
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            edges.add(new int[] { (u - 1), (v - 1) });
        }
        System.out.println("Enter the vertex to find the degree of:");
        int vertex = scanner.nextInt();

        int degree = getDegree(edges, (vertex - 1));
        System.out.println("Degree of vertex " + vertex + ": " + degree);
    }

    private static int getDegree(List<int[]> edges, int vertex) {
        int degree = 0;
        for (int[] edge : edges) {
            if (edge[0] == vertex || edge[1] == vertex) {
                degree++;
            }
        }
        return degree;
    }

    /* Task 5: Determine if a graph is bipartite */
    private static void runTask5() {
        System.out.println("Enter the number of vertices:");
        int n = scanner.nextInt();
        System.out.println("Enter the number of edges:");
        int m = scanner.nextInt();
        List<int[]> edges = new ArrayList<>();
        System.out.println("Enter the edges (format: u v):");
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            edges.add(new int[] { (u - 1), (v - 1) });
        }

        boolean isBipartite = isBipartite(n, edges);
        System.out.println("Graph is bipartite: " + isBipartite);
    }

    private static boolean isBipartite(int n, List<int[]> edges) {
        int[] colors = new int[n];
        Arrays.fill(colors, -1);

        for (int i = 0; i < n; i++) {
            if (colors[i] == -1) {
                if (!bfsCheckBipartite(i, edges, colors)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean bfsCheckBipartite(int start, List<int[]> edges, int[] colors) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        colors[start] = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int[] edge : edges) {
                if (edge[0] == node) {
                    int neighbor = edge[1];
                    if (colors[neighbor] == -1) {
                        colors[neighbor] = 1 - colors[node];
                        queue.offer(neighbor);
                    } else if (colors[neighbor] == colors[node]) {
                        return false;
                    }
                } else if (edge[1] == node) {
                    int neighbor = edge[0];
                    if (colors[neighbor] == -1) {
                        colors[neighbor] = 1 - colors[node];
                        queue.offer(neighbor);
                    } else if (colors[neighbor] == colors[node]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Task 6: Construct an adjacency matrix from vertex pairs
    private static void runTask6() {
        System.out.println("Enter the number of vertices:");
        int n = scanner.nextInt();
        System.out.println("Enter the number of edges:");
        int m = scanner.nextInt();
        List<int[]> edges = new ArrayList<>();
        System.out.println("Enter the edges (format: u v):");
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            edges.add(new int[] { (u - 1), (v - 1) });
        }

        int[][] adjMatrix = constructAdjMatrix(n, edges);
        System.out.println("Adjacency matrix:");
        printMatrix(adjMatrix);
    }

    private static int[][] constructAdjMatrix(int n, List<int[]> edges) {
        int[][] adjMatrix = new int[n][n];
        for (int[] edge : edges) {
            adjMatrix[edge[0]][edge[1]]++;
        }
        return adjMatrix;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    /* Task 7: Construct an incidence matrix from vertex pairs */
    private static void runTask7() {
        System.out.println("Enter the number of vertices:");
        int n = scanner.nextInt();
        System.out.println("Enter the number of edges:");
        int m = scanner.nextInt();
        List<int[]> edges = new ArrayList<>();
        System.out.println("Enter the edges (format: u v):");
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            edges.add(new int[] { (u - 1), (v - 1) });
        }

        int[][] incMatrix = constructIncMatrix(n, edges);
        System.out.println("Incidence matrix:");
        printMatrix(incMatrix);
    }

    private static int[][] constructIncMatrix(int n, List<int[]> edges) {
        int[][] incMatrix = new int[n][edges.size()];
        for (int i = 0; i < edges.size(); i++) {
            int[] edge = edges.get(i);
            incMatrix[edge[0]][i] = 1;
            if (edge[0] != edge[1]) {
                incMatrix[edge[1]][i] = 1;
            }
        }
        return incMatrix;
    }

    /* Task 8: Check if two graphs are isomorphic */
    private static void runTask8() {
        System.out.println("Enter the number of vertices:");
        int n = scanner.nextInt();
        System.out.println("Enter the number of edges for the first graph:");
        int m1 = scanner.nextInt();
        List<int[]> edges1 = new ArrayList<>();
        System.out.println("Enter the edges for the first graph (format: u v):");
        for (int i = 0; i < m1; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            edges1.add(new int[] { (u - 1), (v - 1) });
        }

        System.out.println("Enter the number of edges for the second graph:");
        int m2 = scanner.nextInt();
        List<int[]> edges2 = new ArrayList<>();
        System.out.println("Enter the edges for the second graph (format: u v):");
        for (int i = 0; i < m2; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            edges2.add(new int[] { (u - 1), (v - 1) });
        }

        boolean isIsomorphic = areIsomorphic(n, edges1, edges2);
        System.out.println("Graphs are isomorphic: " + isIsomorphic);
    }

    private static boolean areIsomorphic(int n, List<int[]> edges1, List<int[]> edges2) {
        if (edges1.size() != edges2.size())
            return false;

        int[][] adjMatrix1 = constructAdjMatrix(n, edges1);
        int[][] adjMatrix2 = constructAdjMatrix(n, edges2);

        return Arrays.deepEquals(adjMatrix1, adjMatrix2);
    }
}