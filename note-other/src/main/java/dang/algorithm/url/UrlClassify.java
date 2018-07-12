package dang.algorithm.url;

import org.junit.Test;

/**
 * Created by dangqihe on 2017/2/10.
 */
public class UrlClassify {
    private Node<String> root = new Node<String>("");

    public void addUrl(String url) {
        String[] paths = split(url);
        Node node = root;
        for (String key : paths) {
            Node addNode = node.getChildren(key);
            if (addNode != null) {
                node = addNode;
            } else {
                Node newNode = new Node(url);
                node.addNode(key, newNode);
                node = newNode;
            }

        }
    }

    public static String[] split(String url) {
        return url.split("://|\\.|/");
    }

    @Test
    public void test() {
        UrlClassify urlClassify = new UrlClassify();
        urlClassify.addUrl("http://www.mi.com/index.jsp/1/2/3");
        urlClassify.addUrl("http://www.mi.com/index.jsp/1/2/4");
        urlClassify.addUrl("http://www.mi.com/index.jsp/1/2/2");
        urlClassify.addUrl("http://www.mi.com/index.jsp/2/2/1");
        System.out.println(urlClassify.root.toString());
    }
}
