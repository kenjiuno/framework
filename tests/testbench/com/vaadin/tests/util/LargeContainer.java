package com.vaadin.tests.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.AbstractContainer;
import com.vaadin.data.util.ObjectProperty;

public class LargeContainer extends AbstractContainer implements
        Container.Indexed {

    public class TestItem implements Item {

        private final Object itemId;

        public TestItem(Object itemId) {
            this.itemId = itemId;
        }

        public Property getItemProperty(Object propertyId) {
            ObjectProperty<String> property = new ObjectProperty<String>(
                    containerPropertyIdDefaults.get(propertyId) + " (item "
                            + itemId + ")");
            return property;

        }

        public Collection<?> getItemPropertyIds() {
            return getContainerPropertyIds();
        }

        public boolean addItemProperty(Object id, Property property)
                throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Cannot add item property");
        }

        public boolean removeItemProperty(Object id)
                throws UnsupportedOperationException {
            throw new UnsupportedOperationException(
                    "Cannot remove item property");
        }

    }

    private int size = 1000;

    private Map<Object, Class<?>> containerPropertyIdTypes = new HashMap<Object, Class<?>>();
    private Map<Object, Object> containerPropertyIdDefaults = new HashMap<Object, Object>();

    public Object nextItemId(Object itemId) {
        Integer id = (Integer) itemId;
        if (id >= size() - 1) {
            return null;
        }
        return (id + 1);
    }

    public Object prevItemId(Object itemId) {
        Integer id = (Integer) itemId;
        if (id <= 0) {
            return null;
        }
        return (id - 1);
    }

    public Object firstItemId() {
        if (0 == size()) {
            return null;
        }
        return 0;
    }

    public Object lastItemId() {
        if (0 == size()) {
            return null;
        }
        return (size() - 1);
    }

    public boolean isFirstId(Object itemId) {
        if (null == itemId) {
            return false;
        }
        return itemId.equals(firstItemId());
    }

    public boolean isLastId(Object itemId) {
        if (null == itemId) {
            return false;
        }
        return itemId.equals(lastItemId());
    }

    public TestItem getItem(Object itemId) {
        if (!containsId(itemId)) {
            return null;
        }
        return new TestItem(itemId);
    }

    public Collection<?> getItemIds() {
        return new RangeCollection(size());
    }

    public Property getContainerProperty(Object itemId, Object propertyId) {
        TestItem item = getItem(itemId);
        if (null == item) {
            return null;
        }
        return item.getItemProperty(propertyId);
    }

    public int size() {
        return size;
    }

    public boolean containsId(Object itemId) {
        if (!(itemId instanceof Integer)) {
            return false;
        }
        Integer id = (Integer) itemId;
        return (id >= 0 && id < (size() - 1));
    }

    public int indexOfId(Object itemId) {
        if (!containsId(itemId)) {
            return -1;
        }
        return (Integer) itemId;
    }

    public Object getIdByIndex(int index) {
        return index;
    }

    public void setSize(int newSize) {
        size = newSize;
    }

    public boolean removeAllItems() throws UnsupportedOperationException {
        setSize(0);
        return true;
    }

    public Class<?> getType(Object propertyId) {
        return containerPropertyIdTypes.get(propertyId);
    }

    public Collection<?> getContainerPropertyIds() {
        return containerPropertyIdTypes.keySet();
    }

    public boolean addContainerProperty(Object propertyId, Class<?> type,
            Object defaultValue) throws UnsupportedOperationException {
        if (containerPropertyIdTypes.containsKey(propertyId) || null == type) {
            return false;
        }
        containerPropertyIdTypes.put(propertyId, type);
        containerPropertyIdDefaults.put(propertyId, defaultValue);
        return true;
    }

    public boolean removeContainerProperty(Object propertyId)
            throws UnsupportedOperationException {
        if (!containerPropertyIdTypes.containsKey(propertyId)) {
            return false;
        }
        containerPropertyIdTypes.remove(propertyId);
        containerPropertyIdDefaults.remove(propertyId);
        return true;
    }

    public Item addItem(Object itemId) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported");
    }

    public Object addItem() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported");
    }

    public boolean removeItem(Object itemId)
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported");
    }

    public Object addItemAt(int index) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported");
    }

    public Item addItemAt(int index, Object newItemId)
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported");
    }

    public Object addItemAfter(Object previousItemId)
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported");
    }

    public Item addItemAfter(Object previousItemId, Object newItemId)
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported");
    }

}