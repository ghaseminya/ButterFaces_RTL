package de.larmic.butterfaces.component.html.templatecombobox;

import de.larmic.butterfaces.component.html.HtmlInputComponent;
import de.larmic.butterfaces.component.html.InputComponentFacet;
import de.larmic.butterfaces.component.html.feature.AutoFocus;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.component.html.HtmlInputText;
import java.util.Arrays;
import java.util.Collection;
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
        @ResourceDependency(library = "butterfaces-external", name = "trivial-components.js", target = "head"),
        @ResourceDependency(library = "butterfaces-external", name = "trivial-components.css", target = "head")
})
@FacesComponent(HtmlTemplateComboBox.COMPONENT_TYPE)
public class HtmlTemplateComboBox extends HtmlInputText implements HtmlInputComponent, AutoFocus, ClientBehaviorHolder {

    public static final String COMPONENT_TYPE = "de.larmic.butterfaces.component.templatecombobox";
    public static final String COMPONENT_FAMILY = "de.larmic.butterfaces.component.family";
    public static final String RENDERER_TYPE = "de.larmic.butterfaces.component.renderkit.html_basic.templatecombobox.TemplateComboBoxRenderer";

    protected static final String PROPERTY_TOOLTIP = "tooltip";
    protected static final String PROPERTY_HIDE_LABEL = "hideLabel";
    protected static final String PROPERTY_HTML5_AUTO_FOCUS = "autoFocus";
    protected static final String PROPERTY_TEMPLATE_TYPE = "templateType";
    protected static final String COMBO_BOX_DATA_PROVIDER = "comboBoxDataProvider";

    public HtmlTemplateComboBox() {
        super();
        HtmlTemplateComboBoxJsonp jsonpChild = new HtmlTemplateComboBoxJsonp();
        jsonpChild.setId(getJsonpChildId());
        getChildren().add(jsonpChild);
        this.setRendererType(RENDERER_TYPE);
    }

    @Override
    public List<InputComponentFacet> getSupportedFacets() {
        return Arrays.asList(InputComponentFacet.BOOTSTRAP_INPUT_GROUP_ADDON, InputComponentFacet.BOOTSTRAP_INPUT_GROUP_BTN);
    }

    @Override
    public Collection<String> getEventNames() {
        return Arrays.asList("keyup", "change", "query");
    }

    @Override
    public String getDefaultEventName() {
        return "keyup";
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    @Override
    public String getTooltip() {
        return (String) this.getStateHelper().eval(PROPERTY_TOOLTIP);
    }

    public void setTooltip(final String tooltip) {
        this.updateStateHelper(PROPERTY_TOOLTIP, tooltip);
    }

    @Override
    public boolean isHideLabel() {
        final Object eval = this.getStateHelper().eval(PROPERTY_HIDE_LABEL);
        return eval == null ? false : (Boolean) eval;
    }

    public void setHideLabel(final boolean hideLabel) {
        this.updateStateHelper(PROPERTY_HIDE_LABEL, hideLabel);
    }

    @Override
    public boolean isAutoFocus() {
        final Object eval = this.getStateHelper().eval(PROPERTY_HTML5_AUTO_FOCUS);
        return eval == null ? false : (Boolean) eval;
    }

    @Override
    public void setAutoFocus(final boolean autoFocus) {
        this.updateStateHelper(PROPERTY_HTML5_AUTO_FOCUS, autoFocus);
    }

    public ComboBoxTemplateType getTemplateType() {
        return (ComboBoxTemplateType) this.getStateHelper().eval(PROPERTY_TEMPLATE_TYPE);
    }

    public void setTemplateType(final ComboBoxTemplateType templateType) {
        this.updateStateHelper(PROPERTY_TEMPLATE_TYPE, templateType);
    }

    public ComboBoxDataProvider getComboBoxDataProvider() {
        return (ComboBoxDataProvider) this.getStateHelper().eval(COMBO_BOX_DATA_PROVIDER);
    }

    public void setComboBoxDataProvider(final ComboBoxDataProvider comboBoxDataProvider) {
        this.updateStateHelper(COMBO_BOX_DATA_PROVIDER, comboBoxDataProvider);
    }

    protected void updateStateHelper(final String propertyName, final Object value) {
        this.getStateHelper().put(propertyName, value);

        final ValueExpression ve = this.getValueExpression(propertyName);

        if (ve != null) {
            ve.setValue(this.getFacesContext().getELContext(), value);
        }
    }

    public HtmlTemplateComboBoxJsonp getJsonpChild() {
        HtmlTemplateComboBoxJsonp autoComplete = null;
        for (UIComponent uiComponent : getChildren()) {
            if (uiComponent instanceof HtmlTemplateComboBoxJsonp) {
                autoComplete = (HtmlTemplateComboBoxJsonp) uiComponent;
            }
        }
        return autoComplete;
    }

    private String getJsonpChildId() {
        return getId() + "_jsonp";
    }
}
