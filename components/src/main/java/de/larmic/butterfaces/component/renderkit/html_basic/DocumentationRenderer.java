package de.larmic.butterfaces.component.renderkit.html_basic;

import de.larmic.butterfaces.component.base.renderer.HtmlBasicRenderer;
import de.larmic.butterfaces.component.html.HtmlDocumentation;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import java.io.IOException;

/**
 * Created by larmic on 31.07.14.
 */
@FacesRenderer(componentFamily = HtmlDocumentation.COMPONENT_FAMILY, rendererType = HtmlDocumentation.RENDERER_TYPE)
public class DocumentationRenderer extends HtmlBasicRenderer {

    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        rendererParamsNotNull(context, component);

        if (!shouldEncode(component)) {
            return;
        }

        final ResponseWriter writer = context.getResponseWriter();

        writer.startElement(ELEMENT_DIV, component); // component
        writer.writeAttribute("class", "butter-component-documentation row", "styleClass");

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
        writer.endElement(ELEMENT_DIV); // component
    }

}
