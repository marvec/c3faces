package com.martinlinha.c3faces.script.modifier;

import com.martinlinha.c3faces.constants.ChartType;
import com.martinlinha.c3faces.model.C3DataSet;
import com.martinlinha.c3faces.model.C3ViewDataSet;
import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.script.property.Data;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Martin Linha
 */
public class LoadTest {

    /**
     * Test generated script with listened change.
     */
    @Test
    public void testGetScriptWithChange() {
        Modifier load = new Load()
                .addModifier(new Colors())
                .addModifier(new Names())
                .addModifier(new Transform())
                .addModifier(new TransformTypes());
        Data data = new Data();
        List<Number> numbers = new ArrayList<>();
        Collections.addAll(numbers, 1, 2, 3, 4, 5);
        C3ViewDataSet c3ViewDataSet = new C3ViewDataSet("Name 1", new C3DataSet(numbers), "#EEAAEE");

        data.addListener(load);

        data.setDataSets(new LinkedHashSet<>(Arrays.asList(c3ViewDataSet)));
        data.setChartType(ChartType.BAR.getName());

        c3ViewDataSet.setColor("yellow");
        c3ViewDataSet.setName("new name");

        load.getScript("chart", 500);

        assertEquals(StringUtils.deleteWhitespace("setTimeout(function () {chart.load({columns: [['" + c3ViewDataSet.getId() + "', 1, 2, 3, 4, 5]]})}, 500); chart.data.colors({" + c3ViewDataSet.getId() + ": 'yellow'}); chart.data.names({" + c3ViewDataSet.getId() + ": 'new name'}); setTimeout(function () {chart.transform('bar')}, 1000);"),
                StringUtils.deleteWhitespace(load.getScript("chart", 500)));
    }
}
