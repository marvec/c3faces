package com.martinlinha.c3faces.script;

import com.martinlinha.c3faces.listener.ChangeListener;
import com.martinlinha.c3faces.listener.change.Change;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Martin Linha
 */
public abstract class Property {

    private String name;
    private String body;
    private List<ChangeListener> listeners = new ArrayList<>();
    private Set<Property> children = new HashSet<>();
    private boolean childrenAppended;

    public Property() {
    }

    public Property(List<ChangeListener> listeners) {
        this.listeners = listeners;
    }

    public final String getScript() {
        return this.getScript(true);
    }

    public final String getScript(boolean includeName) {
        if (!childrenAppended) {
            preScriptBuild();
            childrenAppended = true;
        }

        final StringBuilder sb = new StringBuilder();
        String propName = includeName ? getName() : null;

        if (getChildren() == null || getChildren().isEmpty()) {
            if (getBody() == null || getBody().isEmpty()) {
                return "";
            }
            if (propName != null) {
                return propName + getSeparator() + getPrefix() + getBody() + getSuffix();
            } else {
                return getPrefix() + getBody() + getSuffix();
            }
        }

        StringBuilder sbChildren = new StringBuilder();
        for (Property attr : getChildren()) {
            if (sbChildren.length() > 0) {
                sbChildren.append(", \n");
            }
            sbChildren.append(attr.getScript());
        }

        if (sbChildren.length() == 0) {
            return "";
        }
        if (propName != null) {
            sb.append(propName);
            sb.append(getSeparator());
            sb.append("\n");
        }
        sb.append(getPrefix());
        sb.append(sbChildren.toString());
        sb.append(getSuffix());
        return sb.toString();
    }

    public abstract String getPrefix();

    public abstract String getSuffix();

    public Set<Property> getChildren() {
        return children;
    }

    public void setChildren(Set<Property> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void addChild(Property property) {
        if ((property.getBody() == null || property.getBody().isEmpty()) && property.getChildren().isEmpty()) {
            return;
        }
        getChildren().add(property);
    }

    public void fire(String name, Object value) {
        for (ChangeListener listener : listeners) {
            listener.onChange(new Change(name, value));
        }
    }

    protected void preScriptBuild() {

    }

    public String getSeparator() {
        return ": ";
    }

    public List<ChangeListener> getListeners() {
        return listeners;
    }

    public void addListener(ChangeListener listener) {
        listeners.add(listener);
    }

    public void addListeners(Collection<ChangeListener> listener) {
        listeners.addAll(listener);
    }
}
