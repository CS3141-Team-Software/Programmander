/**
 * 
 */
package gameEngine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author cgchapma
 *
 */
public class GraphTest {

	/**
	 * Test method for {@link gameEngine.Graph#Graph(java.lang.String)}.
	 */
	@Test
	public void testGraphString() {
		Graph graph = new Graph("jUnitTestCase.txt");
		//checking to see if the cols and rows were loaded correctly 
		assertTrue(graph.getCols() == 4 && graph.getRows() == 1);
		
		//are the nodes' information loaded correctly?
		GraphNode node = graph.getNode(2, 0);
		assertEquals(node.getBackground(), "Dirt");
	}

	/**
	 * Test method for {@link gameEngine.Graph#getNode(int, int)}.
	 */
	@Test
	public void testGetNode() {
		Graph graph = new Graph("jUnitTestCase.txt");
		
		//is the node's information loaded correctly?
		GraphNode node = graph.getNode(2, 0);
		assertEquals(node.getCoordinates(), "[Row: " + 0 + ", Col: " + 2 + "]");
		
		//testing directional referencing
		node = graph.getNode(0,0);
		assertNotEquals(node.getENode(), null);
		
	}

	/**
	 * Test method for {@link gameEngine.Graph#getNodes()}.
	 */
	@Test
	public void testGetNodes() {
		Graph graph = new Graph("jUnitTestCase.txt");
		//testing graph map dimensions
		assertEquals(graph.getCols() * graph.getRows(), 4);
		
		GraphNode[][] map = graph.getNodes();
		GraphNode[][] test = new GraphNode[1][4];
		
		//the graph map should equal the clone;
		 for(int i = 0; i < 4; i++){
			 test[0][i] = new GraphNode(0, i);
			 test[0][i].setBackground("Grass");
		 }
		 test[0][2].setBackground("Dirt");
		 
		 for(int i = 0; i < 4; i++){
			 assertEquals(map[0][i].getCoordinates(), test[0][i].getCoordinates());
			 assertEquals(map[0][i].getBackground(), test[0][i].getBackground());
			 
		 }
	}

}
