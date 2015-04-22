package de.larmic.butterfaces.component.html.templatecombobox;

import de.larmic.butterfaces.component.base.component.UIComponentBase;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import java.util.Collections;
import java.util.List;

@ResourceDependencies({
        @ResourceDependency(library = "butterfaces-css", name = "butterfaces-default.css", target = "head"),
        @ResourceDependency(library = "butterfaces-js", name = "butterfaces-fixed.js", target = "head"),
        @ResourceDependency(library = "butterfaces-configurable", name = "jquery.min.js", target = "head"),
        @ResourceDependency(library = "butterfaces-configurable", name = "bootstrap.min.css", target = "head"),
        @ResourceDependency(library = "butterfaces-configurable", name = "bootstrap.min.js", target = "head"),
        @ResourceDependency(library = "butterfaces-js", name = "butterfaces-tooltip.jquery.js", target = "head"),

        @ResourceDependency(library = "butterfaces-external", name = "jquery.caret.min.js", target = "head"),
        @ResourceDependency(library = "butterfaces-external", name = "jquery.position.min.js", target = "head"),
        @ResourceDependency(library = "butterfaces-external", name = "mustache.min.js", target = "head"),
        @ResourceDependency(library = "butterfaces-external", name = "trivial-combobox.min.js", target = "head"),
        @ResourceDependency(library = "butterfaces-external", name = "trivial-combobox.min.css", target = "head")
})
@FacesComponent(HtmlTemplateComboBoxJsonp.COMPONENT_TYPE)
public class HtmlTemplateComboBoxJsonp extends UIComponentBase {

    public static final String COMPONENT_TYPE = "de.larmic.butterfaces.component.htmlTemplateComboBoxJsonp";
    public static final String COMPONENT_FAMILY = "de.larmic.butterfaces.component.family";
    public static final String RENDERER_TYPE = "de.larmic.butterfaces.renderkit.html_basic.templateComboBoxJsonpRenderer";
    private List entries;

    public HtmlTemplateComboBoxJsonp() {
        super();
        this.setRendererType(RENDERER_TYPE);
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    public void setEntries(List entries) {
        this.entries = entries;
    }

    public List getEntries() {
        if (entries != null) {
            return entries;
        } else {
            return Collections.emptyList();
        }
    }
}
