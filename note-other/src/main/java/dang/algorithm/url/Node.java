package dang.algorithm.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dangqihe on 2017/2/10.
 */
public class Node<T> {
    private T data;
    private Node parent;
    private Map<T, Node> childrens = new HashMap<T, Node>();

    public Node(T data) {
        this.data = data;
    }

    public Node getChildren(T key) {
        return childrens.get(key);
    }

    public Node addNode(T key, Node node) {
        childrens.put(key, node);
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Map<T, Node> getChildrens() {
        return childrens;
    }

    public void setChildrens(Map<T, Node> childrens) {
        this.childrens = childrens;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<T, Node> entry : childrens.entrySet()) {
            stringBuffer.append(entry.getKey() + "\t\t");
        }
        stringBuffer.append("\t\n");
        for (Map.Entry<T, Node> entry : childrens.entrySet()) {
            Node node = entry.getValue();
            stringBuffer.append(node.toString());
        }
        return stringBuffer.toString();
    }
}
