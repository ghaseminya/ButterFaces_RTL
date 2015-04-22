package de.larmic.butterfaces.component.html.templatecombobox;

import java.util.List;

/**
 * @author Yann Massard
 */
public interface ComboBoxDataProvider<T> {

    List<T> getComboBoxEntries(String queryString);

}
