package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.ValueBlock;
import com.martinlinha.c3faces.script.property.Legend;
import java.util.Arrays;
import java.util.List;

/**
 * Instances of this class can be registered as a listeners to listen changes fired by corresponding objects.
 *
 * The main mission of this class is to provide C3.js scripts for dynamic legend showing.
 *
 * @author Martin Linha
 */
public class LegendShow extends Modifier {

    private static final String LEGEND = "legend";
    private static final String SHOW = "show";

    @Override
    protected Property getModificationProperty() {
        return new ValueBlock();
    }

    @Override
    protected String getMethodName() {
        return SHOW;
    }

    @Override
    public List<String> getFields() {
        return Arrays.asList(LEGEND);
    }

    @Override
    public boolean isMethod() {
        if (getPropertyLastChange(Legend.EVENT_LEGEND_SHOW) != null) {
            return (boolean) getPropertyLastChange(Legend.EVENT_LEGEND_SHOW);
        }
        return super.isMethod();
    }
}
