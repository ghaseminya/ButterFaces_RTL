package de.larmic.butterfaces.component.renderkit.html_basic.templatecombobox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import de.larmic.butterfaces.component.base.renderer.HtmlBasicRenderer;
import de.larmic.butterfaces.component.html.templatecombobox.HtmlTemplateComboBoxJsonp;
import de.larmic.butterfaces.component.partrenderer.RenderUtils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@FacesRenderer(componentFamily = HtmlTemplateComboBoxJsonp.COMPONENT_FAMILY, rendererType = HtmlTemplateComboBoxJsonp.RENDERER_TYPE)
public class TemplateComboBoxJsonpRenderer extends HtmlBasicRenderer {

    private static final ObjectWriter OBJECT_WRITER = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        rendererParamsNotNull(context, component);

        if (!shouldEncode(component)) {
            return;
        }

        final ResponseWriter writer = context.getResponseWriter();
        final HtmlTemplateComboBoxJsonp jsonpComponent = (HtmlTemplateComboBoxJsonp) component;

        writer.startElement("div", jsonpComponent);
        writeIdAttribute(context, writer, jsonpComponent);

        RenderUtils.renderJQueryPluginCall(component.getParent().getClientId(), "input", "TrivialComboBox().updateEntries("
                + createUpdateEntriesJson(jsonpComponent.getEntries()) + ");", writer, component);
    }

    public static String createUpdateEntriesJson(List entries) {
        StringBuilder sb = new StringBuilder("[");
        for (Iterator iterator = entries.iterator(); iterator.hasNext(); ) {
            Object entry = iterator.next();
            try {
                sb.append(OBJECT_WRITER.writeValueAsString(entry));
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(e);
            }
            if (iterator.hasNext()) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        rendererParamsNotNull(context, component);

        if (!shouldEncode(component)) {
            return;
        }

        final ResponseWriter writer = context.getResponseWriter();

//        RenderUtils.renderJQueryPluginCall(component.getClientId(), "_butterAutoCompleteInit()", writer, component);

        writer.endElement("div");
    }
}
