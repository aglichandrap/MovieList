package org.iblitzc0de.movielist.provider.base;

import android.database.Cursor;
import android.database.CursorWrapper;
import java.util.Date;
import java.util.HashMap;

public abstract class AbstractCursor extends CursorWrapper {
    private final HashMap<String, Integer> mColumnIndexes;

    public abstract long getId();

    public AbstractCursor(Cursor cursor) {
        super(cursor);
        this.mColumnIndexes = new HashMap((cursor.getColumnCount() * 4) / 3, 0.75f);
    }

    protected int getCachedColumnIndexOrThrow(String colName) {
        Integer index = (Integer) this.mColumnIndexes.get(colName);
        if (index == null) {
            index = Integer.valueOf(getColumnIndexOrThrow(colName));
            this.mColumnIndexes.put(colName, index);
        }
        return index.intValue();
    }

    public String getStringOrNull(String colName) {
        int index = getCachedColumnIndexOrThrow(colName);
        if (isNull(index)) {
            return null;
        }
        return getString(index);
    }

    public Integer getIntegerOrNull(String colName) {
        int index = getCachedColumnIndexOrThrow(colName);
        if (isNull(index)) {
            return null;
        }
        return Integer.valueOf(getInt(index));
    }

    public Long getLongOrNull(String colName) {
        int index = getCachedColumnIndexOrThrow(colName);
        if (isNull(index)) {
            return null;
        }
        return Long.valueOf(getLong(index));
    }

    public Float getFloatOrNull(String colName) {
        int index = getCachedColumnIndexOrThrow(colName);
        if (isNull(index)) {
            return null;
        }
        return Float.valueOf(getFloat(index));
    }

    public Double getDoubleOrNull(String colName) {
        int index = getCachedColumnIndexOrThrow(colName);
        if (isNull(index)) {
            return null;
        }
        return Double.valueOf(getDouble(index));
    }

    public Boolean getBooleanOrNull(String colName) {
        int index = getCachedColumnIndexOrThrow(colName);
        if (isNull(index)) {
            return null;
        }
        return Boolean.valueOf(getInt(index) != 0);
    }

    public Date getDateOrNull(String colName) {
        int index = getCachedColumnIndexOrThrow(colName);
        if (isNull(index)) {
            return null;
        }
        return new Date(getLong(index));
    }

    public byte[] getBlobOrNull(String colName) {
        int index = getCachedColumnIndexOrThrow(colName);
        if (isNull(index)) {
            return null;
        }
        return getBlob(index);
    }
}
