package com.waslabrowser.service.common.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PageResponseBody<T> {

    @JsonProperty("data")
    private final List<T> items = new ArrayList<>();
    @JsonProperty("meta")
    private Meta meta = new Meta();

    public PageResponseBody() {
    }

    public PageResponseBody(Page<T> page) {
        this(page.getContent(), page.isLast(), page.getTotalElements());
    }

    public PageResponseBody(List<T> items, boolean hasNext) {
        meta.hasNext = hasNext;
        if (items != null) {
            this.items.addAll(items);
            meta.totalItemsCount = items.size();
        }
    }

    public PageResponseBody(List<T> items, boolean hasNext, long totalItemsCount) {
        meta.hasNext = hasNext;
        meta.totalItemsCount = totalItemsCount;
        if (items != null) this.items.addAll(items);
    }

    @NonNull
    @JsonIgnore
    public Meta getMeta() {
        return meta;
    }

    @JsonIgnore
    public List<T> getItems() {
        return items;
    }

    @JsonIgnore
    public boolean hasNext() {
        return meta.hasNext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageResponseBody<?> that = (PageResponseBody<?>) o;
        return meta.equals(that.meta) && Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meta.hashCode(), items);
    }

    public static class Meta {

        @JsonProperty("has_next")
        private boolean hasNext;

        @JsonProperty("total")
        private long totalItemsCount;

        @JsonIgnore
        public boolean hasNext() {
            return hasNext;
        }

        @JsonIgnore
        public long getTotalItemsCount() {
            return totalItemsCount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Meta that = (Meta) o;
            return hasNext == that.hasNext && totalItemsCount == that.totalItemsCount;
        }

        @Override
        public int hashCode() {
            return Objects.hash(totalItemsCount, hasNext);
        }
    }

}
