package com.emploc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListableResponse<T> implements Serializable {

    private static final long serialVersionUID = -587760260121137047L;
    private transient List<T> items = new ArrayList<>();

    public ListableResponse items(final List<T> items) {
        this.items = items;
        return this;
    }

    public ListableResponse addItem(T item) {
        this.items.add(item);
        return this;
    }

    @JsonProperty("items")
    @ApiModelProperty
    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListableResponse<?> that = (ListableResponse<?>) o;
        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }

    @Override
    public String toString() {
        return "ListableResponse{" +
                "items=" + items +
                '}';
    }
}
