package Topics.Graphs;

/**
 * 2-3
 * | 
 * 1
 * 
 * 2[1 3]
 * OR
 * 1[2[3]]
 * 
 * 2-3
 * |/
 * 1
 * 
 * 2[1[3] 3]
 * OR
 * 1[2[3] 3]
 * 
 * 2-3-4 5-6
 * |/
 * 1
 * 
 * 2[1[3] 3[4]]
 * OR
 * 1[2[3] 3[4]]
 * OR
 * 3[1[2] 2 4] 5[6]
 * 
 * Serialize logic
 * DFS traversal of all nodes keep expanding each node and and put the child in brackets, recursively get the representation of the child
 * 
 * Deserialize logic
 * 1,2,3
 * Node n1 
 * graph{
 * 1-n1,
 * 2-n2,
 * 3-n3
 * }
 * 
 * 2[1 3]
 * e=2,prev=null
 * graph{
 * 2-n2
 * }
 * 
 * e=[
 * prev=2
 * stack=[2]
 * 
 * e=1
 * stack=[2]
 * graph{
 * 2-n2,
 * 1-n1
 * }
 * if !stack.isEmpty()
 * stack.peek().children.add(currNode);
 * n2.children.add(n1);
 * 
 * e=3, prev=1
 * stack=[2]
 * graph{
 * 2-n2,
 * 1-n1,
 * 3-n3
 * }
 * n2.children.add(n3)
 * 
 * e=]
 * stack.pop() = []
 * 
 * 
 * 
 * 2[1[3] 3]
 * 
 * e=2, prev=null
 * stack=[]
 * graph{
 * 2-n2
 * }
 * 2
 * 
 * e=[, prev=2
 * stack=[2]
 * graph
 * 2
 * 
 * e=1, prev=[
 * stack=[2]
 * graph
 * 2-1
 * 
 * e=[,prev=1
 * stack=[2,1]
 * 
 * e=3,prev=[
 * stack=[2,1]
 * graph
 * 2-1-3
 * 
 * e=]
 * stack.pop() = [2]
 * graph
 * 2-1-3
 * 
 * e=3
 * 2-1
 * | /
 * 3 
 * 
 * e=]
 * stack=[]
 * 
 */
public class SerializeGraph {

}
