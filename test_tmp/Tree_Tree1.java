import org.junit.Test;
import gov.nasa.jpf.util.test.TestJPF;

public class Tree_Tree1 extends TestJPF {

	@Test
	public void test_Tree1() throws Exception {
		Tree obj = new Tree();
		Tree x = new Tree();
		Tree left_1 = null;
		Tree right_2 = null;
		obj.right = null;
		obj.left = null;
		x.left = left_1;
		x.right = right_2;
		obj.Tree();
	}

}

