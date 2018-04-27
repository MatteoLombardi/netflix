package org.forit.netflix.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = LocalDate.class)
public class LocalDateFacesConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        return LocalDate.parse(value);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        LocalDate localDate = (LocalDate) value;
        return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

}
