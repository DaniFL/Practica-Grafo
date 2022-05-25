import dominio.Grafo;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class TestGrafo {

    @Test
    public void shortestPathFindsAPath() {
        System.out.println("\nTest shortestPathFindsAPath");
        System.out.println("----------------------------");
// We build the graph
        Grafo<Integer> g = new Grafo();
        g.addEdge(1, 2);
        g.addEdge(1, 5);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(5, 4);
// We build the expected path
        List<Integer> expectedPath = new ArrayList<>();
        expectedPath.add(1);
        expectedPath.add(5);
        expectedPath.add(4);
//We check if the returned path is equal to the expected one.
        assertEquals(expectedPath, g.shortestPath(1, 4));
    }
}
