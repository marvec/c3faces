package com.martinlinha.c3faces.script;

/**
 *
 * @author Martin Linha
 */
public class CommaValueProp extends ValueProp {

    public CommaValueProp(String name, String body, boolean bodyQuoted, boolean nameQuoted) {
        super(name, body, bodyQuoted, nameQuoted);
    }

    @Override
    public boolean isBodyQuoted() {
        return true;
    }

    @Override
    public boolean isNameQuoted() {
        return true;
    }

    @Override
    public String getSeparator() {
        return ", ";
    }
}
