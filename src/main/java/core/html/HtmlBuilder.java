package core.html;

import core.TextType;
import core.parser.node.Node;
import core.parser.node.TextNode;

import java.util.List;

public class HtmlBuilder {

    public static String run(List<Node> nodes) {
        return "<head></head><body>" + generate(nodes) + "</body>";
    }

    public static String generate(List<Node> nodes) {
        StringBuilder html = new StringBuilder();
        for(Node node : nodes) {
            if(node instanceof TextNode textNode) {
                html.append("<p>" + textNode.getText() + "</p>");
            }
        }
        return html.toString();
    }

}
