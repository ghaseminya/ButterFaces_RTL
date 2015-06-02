package de.larmic.butterfaces.component.renderkit.html_basic.templatecombobox;

import de.larmic.butterfaces.component.base.renderer.HtmlBasicRenderer;
import de.larmic.butterfaces.component.html.templatecombobox.HtmlTemplateComboBox;
import de.larmic.butterfaces.component.partrenderer.InnerComponentWrapperPartRenderer;
import de.larmic.butterfaces.component.partrenderer.LabelPartRenderer;
import de.larmic.butterfaces.component.partrenderer.OuterComponentWrapperPartRenderer;
import de.larmic.butterfaces.component.partrenderer.ReadonlyPartRenderer;
import de.larmic.butterfaces.component.partrenderer.RenderUtils;
import de.larmic.butterfaces.component.partrenderer.TooltipPartRenderer;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@FacesRenderer(componentFamily = HtmlTemplateComboBox.COMPONENT_FAMILY, rendererType = HtmlTemplateComboBox.RENDERER_TYPE)
public class TemplateComboBoxRenderer extends HtmlBasicRenderer {

    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        rendererParamsNotNull(context, component);

        if (!shouldEncode(component)) {
            return;
        }

        super.encodeBegin(context, component);

        final HtmlTemplateComboBox comboBox = (HtmlTemplateComboBox) component;
        final ResponseWriter writer = context.getResponseWriter();

        // Open outer component wrapper div
        new OuterComponentWrapperPartRenderer().renderComponentBegin(comboBox, writer);

        // Render label if components label attribute is set
        new LabelPartRenderer().renderLabel(comboBox, writer);

        new InnerComponentWrapperPartRenderer().renderInnerWrapperBegin(comboBox, writer);
        new ReadonlyPartRenderer().renderReadonly(comboBox, writer);

        writer.startElement("input", component);
        writer.writeAttribute("type", "text", null);
        writer.writeAttribute("name", comboBox.getClientId(), null);
        writer.endElement("input");

        new InnerComponentWrapperPartRenderer().renderInnerWrapperEnd(comboBox, writer);

        // render tooltip elements if necessary
        new TooltipPartRenderer().renderTooltip(comboBox, writer);

        // Open outer component wrapper div
        new OuterComponentWrapperPartRenderer().renderComponentEnd(writer);

        if (comboBox.getComboBoxDataProvider() != null) {
            List comboBoxEntries = comboBox.getComboBoxDataProvider().getComboBoxEntries("");
            comboBox.getJsonpChild().setEntries(comboBoxEntries);
        }

        RenderUtils.renderJQueryPluginCall(component.getClientId(), "input", "TrivialComboBox({\n" +
                "    entries: " + TemplateComboBoxJsonpRenderer.createUpdateEntriesJson(comboBox.getJsonpChild().getEntries()) + ", " +
                "    queryFunction: function(queryString) {\n" +
                "        jsf.ajax.request(" + RenderUtils.createJQueryBySelector(component.getClientId(), "input") + "[0], event, {render:\"" + comboBox.getJsonpChild().getClientId() + "\", execute: \"" + component.getClientId() + "\", templateComboBoxQueryString: queryString});" +
                "    }\n" +
                "});", writer, component);
    }

    @Override
    public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        rendererParamsNotNull(context, component);

        if (!shouldEncode(component)) {
            return;
        }

        final HtmlTemplateComboBox comboBox = (HtmlTemplateComboBox) component;
        final ResponseWriter writer = context.getResponseWriter();

        super.encodeEnd(context, component);

        // actually render nothing...
    }

    @Override
    public void encodeChildren(FacesContext context, UIComponent component)
            throws IOException {

        rendererParamsNotNull(context, component);

        if (component.getChildCount() > 0) {
            for (UIComponent kid : component.getChildren()) {
                encodeRecursive(context, kid);
            }
        }

    }

    @Override
    public void decode(FacesContext context, UIComponent component) {
        final HtmlTemplateComboBox comboBox = (HtmlTemplateComboBox) component;

        Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
        String queryString = requestMap.get("templateComboBoxQueryString");

        if (queryString != null && comboBox.getComboBoxDataProvider() != null) {
            List comboBoxEntries = comboBox.getComboBoxDataProvider().getComboBoxEntries(queryString);
            comboBox.getJsonpChild().setEntries(comboBoxEntries);
        }
    }

    @Override
    protected void encodeRecursive(FacesContext context, UIComponent component) throws IOException {
        super.encodeRecursive(context, component);
    }


}
