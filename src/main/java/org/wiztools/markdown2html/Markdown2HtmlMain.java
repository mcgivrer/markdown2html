package org.wiztools.markdown2html;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;

/**
 *
 * @author subwiz
 */
public class Markdown2HtmlMain {
    private static void printHelp(PrintStream out) {
        out.println("Usage:");
        out.println("------");
        out.println(" Input is read from STDIN and output written to STDOUT.");
        out.println("  e.g.: $ java -jar MarkDown2Html.jar <inputfiltext.md >outputfile.html");
        out.println("");
    }

    public static void main(String[] args) {

        for (String arg : args) {
            switch (arg) {
                case "-h":
                case "--help":
                    printHelp(System.out);
                    System.exit(0);
            }
        }

        MutableDataSet options = new MutableDataSet();

        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        Reader in = new InputStreamReader(System.in);

        // You can re-use parser and renderer instances
        Node document;
        try {
            document = parser.parseReader(in);
            String html = renderer.render(document);
            System.out.println(html);
        } catch (Exception e) {
            System.err.print(e.getLocalizedMessage());
        }
    }

    /*
     * public static void main(String[] args) throws IOException {
     * for(String arg: args) {
     * switch(arg) {
     * case "-h":
     * case "--help":
     * printHelp(System.out);
     * System.exit(0);
     * }
     * }
     * 
     * // Do the conversion:
     * Reader in = new InputStreamReader(System.in);
     * String out = new Markdown4jProcessor().process(in);
     * System.out.print(out);
     * }
     */
}
