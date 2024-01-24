package treaps;

public class TreePrinter {
    public static <E> void print(Node<E> node) {
        System.out.println(toString(node));
    }

    // Convert the tree to a string using a pre-order traversal algorithm
    public static <E> String toString(Node<E> root) {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.data + "/" + root.priority);

        String ptRight = "└──";
        String ptLeft = (root.right != null) ? "├──" : "└──";

        traverseSubnode(sb, "", ptLeft, root.left, root.right != null);
        traverseSubnode(sb, "", ptRight, root.right, false);

        return sb.toString();
    }
    
    public static <E> void traverseSubnode(StringBuilder sb, String padding, String pointer, Node<E> node, boolean hasRightSibling) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.data + "/" + node.priority);


            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("│  ");
            } else {
                paddingBuilder.append("   ");
            }
    
            String paddingBoth = paddingBuilder.toString();
            String pointerRight = "└──";
            String pointerLeft = (node.right != null) ? "├──" : "└──";
    

            traverseSubnode(sb, paddingBoth, pointerLeft, node.left, node.right != null);
            traverseSubnode(sb, paddingBoth, pointerRight, node.right, false);
        }
    }
}
