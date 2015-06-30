package de.larmic.butterfaces.component.renderkit.html_basic;

import de.larmic.butterfaces.component.base.renderer.HtmlBasicRenderer;
import de.larmic.butterfaces.component.html.HtmlDocumentation;
import de.larmic.butterfaces.component.html.HtmlSection;
import de.larmic.butterfaces.component.partrenderer.StringUtils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by larmic on 31.07.14.
 */
@FacesRenderer(componentFamily = HtmlDocumentation.COMPONENT_FAMILY, rendererType = HtmlDocumentation.RENDERER_TYPE)
public class DocumentationRenderer extends HtmlBasicRenderer {

    public static final String ELEMENT_NAV = "nav";

    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        rendererParamsNotNull(context, component);

        if (!shouldEncode(component)) {
            return;
        }

        final ResponseWriter writer = context.getResponseWriter();

        final HtmlDocumentation documentation = (HtmlDocumentation) component;
        final String style = documentation.getStyle();
        final String styleClass = documentation.getStyleClass();

        writer.startElement(ELEMENT_DIV, component); // component
        this.writeIdAttribute(context, writer, component);
        if (StringUtils.isNotEmpty(style)) {
            writer.writeAttribute(ATTRIBUTE_STYLE, style, "style");
        }
        if (StringUtils.isNotEmpty(styleClass)) {
            writer.writeAttribute(ATTRIBUTE_CLASS, "butter-component-documentation row " + styleClass, "styleClass");
        } else {
            writer.writeAttribute(ATTRIBUTE_CLASS, "butter-component-documentation row", "styleClass");
        }

        writer.startElement(ELEMENT_DIV, component); // left content
        writer.writeAttribute("class", "col-md-9 col-lg-10", "styleClass");
        writer.writeAttribute("role", "main", null);

    }


    @Override
    public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        rendererParamsNotNull(context, component);

        if (!shouldEncode(component)) {
            return;
        }

        final ResponseWriter writer = context.getResponseWriter();

        writer.endElement(ELEMENT_DIV); // left content
        encodeComplementary(component, writer);
        writer.endElement(ELEMENT_DIV); // component
    }

    private void encodeComplementary(final UIComponent component,
                                     final ResponseWriter writer) throws IOException {
        // start complementary
        writer.startElement(ELEMENT_DIV, component);
        writer.writeAttribute("class", "col-md-3 col-lg-2", "styleClass");
        writer.writeAttribute("role", "complementary", null);

        writer.startElement(ELEMENT_NAV, component);
        writer.writeAttribute("class", "hidden-print hidden-xs hidden-sm affix", "styleClass");
        writer.startElement("h4", component);
        writer.writeText("Content", null);
        writer.endElement("h4");
        encodeComplementaryChildren(component, writer);
        writer.endElement(ELEMENT_NAV);

        writer.endElement(ELEMENT_DIV);
    }

    private void encodeComplementaryChildren(UIComponent component, ResponseWriter writer) throws IOException {
        final List<HtmlSection> sections = findSectionChildren(component);

        if (!sections.isEmpty()) {
            writer.startElement("ul", component);
            writer.writeAttribute("class", "nav", "styleClass");

            for (HtmlSection section : sections) {
                if (StringUtils.isNotEmpty(section.getLabel()) && StringUtils.isNotEmpty(section.getAnchorId())) {
                    writer.startElement("li", component);
                    writer.startElement("a", component);
                    writer.writeAttribute("href", "#" + section.getAnchorId(), null);
                    writer.writeText(section.getLabel(), null);
                    writer.endElement("a");
                    encodeComplementaryChildren(section, writer);
                    writer.endElement("li");
                }
            }

            writer.endElement("ul");
        }
    }

    private List<HtmlSection> findSectionChildren(final UIComponent component) {
        final List<HtmlSection> sections = new ArrayList<>();

        for (UIComponent uiComponent : component.getChildren()) {
            if (uiComponent instanceof HtmlSection) {
                sections.add((HtmlSection) uiComponent);
            }
        }

        return sections;
    }
}
