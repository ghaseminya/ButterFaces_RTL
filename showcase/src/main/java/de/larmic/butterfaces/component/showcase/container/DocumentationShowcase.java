package de.larmic.butterfaces.component.showcase.container;

import de.larmic.butterfaces.component.showcase.AbstractCodeShowcase;
import de.larmic.butterfaces.component.showcase.example.AbstractCodeExample;
import de.larmic.butterfaces.component.showcase.example.XhtmlCodeExample;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class DocumentationShowcase extends AbstractCodeShowcase implements Serializable {

    @Override
    public void buildCodeExamples(final List<AbstractCodeExample> codeExamples) {
        final XhtmlCodeExample xhtmlCodeExample = new XhtmlCodeExample(false);

        xhtmlCodeExample.appendInnerContent("        <b:documentation id=\"doc\"");
        xhtmlCodeExample.appendInnerContent("                   rendered=\"" + this.isRendered() + "\">");
        xhtmlCodeExample.appendInnerContent("            Lorem ipsum dolor sit amet, consectetuer ...");
        xhtmlCodeExample.appendInnerContent("        </b:documentation>");
        xhtmlCodeExample.appendInnerContent("        Lorem ipsum dolor sit amet, consectetuer ...", false);

        codeExamples.add(xhtmlCodeExample);
    }

}
