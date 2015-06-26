package de.larmic.butterfaces.component.html;

import de.larmic.butterfaces.component.base.component.UIComponentBase;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;

@ResourceDependencies({
        @ResourceDependency(library = "butterfaces-configurable", name = "bootstrap.min.css", target = "head"),
        @ResourceDependency(library = "butterfaces-css", name = "butterfaces-documentation.css", target = "head"),
        @ResourceDependency(library = "butterfaces-configurable", name = "bootstrap.min.js", target = "head") })
@FacesComponent(HtmlDocumentation.COMPONENT_TYPE)
public class HtmlDocumentation extends UIComponentBase {

	public static final String COMPONENT_TYPE = "de.larmic.butterfaces.component.documentation";
	public static final String COMPONENT_FAMILY = "de.larmic.butterfaces.component.family";
	public static final String RENDERER_TYPE = "de.larmic.butterfaces.renderkit.html_basic.DocumentationRenderer";

	public HtmlDocumentation() {
		super();
		this.setRendererType(RENDERER_TYPE);
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

}
