package org.iblitzc0de.movielist.provider.base;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class AbstractSelection<T extends AbstractSelection<?>> {
    private static final String AND = " AND ";
    private static final String COMMA = ",";
    private static final String CONTAINS = " LIKE '%' || ? || '%'";
    private static final String COUNT = "COUNT(*)";
    private static final String DESC = " DESC";
    private static final String ENDS = " LIKE '%' || ?";
    private static final String EQ = "=?";
    private static final String GT = ">?";
    private static final String GT_EQ = ">=?";
    private static final String IN = " IN (";
    private static final String IS_NOT_NULL = " IS NOT NULL";
    private static final String IS_NULL = " IS NULL";
    private static final String LIKE = " LIKE ?";
    private static final String LT = "<?";
    private static final String LT_EQ = "<=?";
    private static final String NOT_EQ = "<>?";
    private static final String NOT_IN = " NOT IN (";
    private static final String OR = " OR ";
    private static final String PAREN_CLOSE = ")";
    private static final String PAREN_OPEN = "(";
    private static final String STARTS = " LIKE ? || '%'";
    String mGroupBy;
    String mHaving;
    Integer mLimit;
    Boolean mNotify;
    private final StringBuilder mOrderBy = new StringBuilder();
    private final StringBuilder mSelection = new StringBuilder();
    private final List<String> mSelectionArgs = new ArrayList(5);

    protected abstract Uri baseUri();

    protected void addEquals(String column, Object[] value) {
        this.mSelection.append(column);
        if (value == null) {
            this.mSelection.append(IS_NULL);
        } else if (value.length > 1) {
            this.mSelection.append(IN);
            for (int i = 0; i < value.length; i++) {
                this.mSelection.append("?");
                if (i < value.length - 1) {
                    this.mSelection.append(COMMA);
                }
                this.mSelectionArgs.add(valueOf(value[i]));
            }
            this.mSelection.append(PAREN_CLOSE);
        } else if (value[0] == null) {
            this.mSelection.append(IS_NULL);
        } else {
            this.mSelection.append(EQ);
            this.mSelectionArgs.add(valueOf(value[0]));
        }
    }

    protected void addNotEquals(String column, Object[] value) {
        this.mSelection.append(column);
        if (value == null) {
            this.mSelection.append(IS_NOT_NULL);
        } else if (value.length > 1) {
            this.mSelection.append(NOT_IN);
            for (int i = 0; i < value.length; i++) {
                this.mSelection.append("?");
                if (i < value.length - 1) {
                    this.mSelection.append(COMMA);
                }
                this.mSelectionArgs.add(valueOf(value[i]));
            }
            this.mSelection.append(PAREN_CLOSE);
        } else if (value[0] == null) {
            this.mSelection.append(IS_NOT_NULL);
        } else {
            this.mSelection.append(NOT_EQ);
            this.mSelectionArgs.add(valueOf(value[0]));
        }
    }

    protected void addLike(String column, String[] values) {
        this.mSelection.append(PAREN_OPEN);
        for (int i = 0; i < values.length; i++) {
            this.mSelection.append(column);
            this.mSelection.append(LIKE);
            this.mSelectionArgs.add(values[i]);
            if (i < values.length - 1) {
                this.mSelection.append(OR);
            }
        }
        this.mSelection.append(PAREN_CLOSE);
    }

    protected void addContains(String column, String[] values) {
        this.mSelection.append(PAREN_OPEN);
        for (int i = 0; i < values.length; i++) {
            this.mSelection.append(column);
            this.mSelection.append(CONTAINS);
            this.mSelectionArgs.add(values[i]);
            if (i < values.length - 1) {
                this.mSelection.append(OR);
            }
        }
        this.mSelection.append(PAREN_CLOSE);
    }

    protected void addStartsWith(String column, String[] values) {
        this.mSelection.append(PAREN_OPEN);
        for (int i = 0; i < values.length; i++) {
            this.mSelection.append(column);
            this.mSelection.append(STARTS);
            this.mSelectionArgs.add(values[i]);
            if (i < values.length - 1) {
                this.mSelection.append(OR);
            }
        }
        this.mSelection.append(PAREN_CLOSE);
    }

    protected void addEndsWith(String column, String[] values) {
        this.mSelection.append(PAREN_OPEN);
        for (int i = 0; i < values.length; i++) {
            this.mSelection.append(column);
            this.mSelection.append(ENDS);
            this.mSelectionArgs.add(values[i]);
            if (i < values.length - 1) {
                this.mSelection.append(OR);
            }
        }
        this.mSelection.append(PAREN_CLOSE);
    }

    protected void addGreaterThan(String column, Object value) {
        this.mSelection.append(column);
        this.mSelection.append(GT);
        this.mSelectionArgs.add(valueOf(value));
    }

    protected void addGreaterThanOrEquals(String column, Object value) {
        this.mSelection.append(column);
        this.mSelection.append(GT_EQ);
        this.mSelectionArgs.add(valueOf(value));
    }

    protected void addLessThan(String column, Object value) {
        this.mSelection.append(column);
        this.mSelection.append(LT);
        this.mSelectionArgs.add(valueOf(value));
    }

    protected void addLessThanOrEquals(String column, Object value) {
        this.mSelection.append(column);
        this.mSelection.append(LT_EQ);
        this.mSelectionArgs.add(valueOf(value));
    }

    public void addRaw(String raw, Object... args) {
        this.mSelection.append(" ");
        this.mSelection.append(raw);
        this.mSelection.append(" ");
        for (Object arg : args) {
            this.mSelectionArgs.add(valueOf(arg));
        }
    }

    private String valueOf(Object obj) {
        if (obj instanceof Date) {
            return String.valueOf(((Date) obj).getTime());
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue() ? "1" : "0";
        } else {
            if (obj instanceof Enum) {
                return String.valueOf(((Enum) obj).ordinal());
            }
            return String.valueOf(obj);
        }
    }

    public T openParen() {
        this.mSelection.append(PAREN_OPEN);
        return (T) this;
    }

    public T closeParen() {
        this.mSelection.append(PAREN_CLOSE);
        return (T) this;
    }

    public T and() {
        this.mSelection.append(AND);
        return (T) this;
    }

    public T or() {
        this.mSelection.append(OR);
        return (T) this;
    }

    protected Object[] toObjectArray(int... array) {
        Object[] res = new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            res[i] = Integer.valueOf(array[i]);
        }
        return res;
    }

    protected Object[] toObjectArray(long... array) {
        Object[] res = new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            res[i] = Long.valueOf(array[i]);
        }
        return res;
    }

    protected Object[] toObjectArray(float... array) {
        Object[] res = new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            res[i] = Float.valueOf(array[i]);
        }
        return res;
    }

    protected Object[] toObjectArray(double... array) {
        Object[] res = new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            res[i] = Double.valueOf(array[i]);
        }
        return res;
    }

    protected Object[] toObjectArray(Boolean value) {
        return new Object[]{value};
    }

    public String sel() {
        return this.mSelection.toString();
    }

    public String[] args() {
        int size = this.mSelectionArgs.size();
        if (size == 0) {
            return null;
        }
        return (String[]) this.mSelectionArgs.toArray(new String[size]);
    }

    public String order() {
        return this.mOrderBy.length() > 0 ? this.mOrderBy.toString() : null;
    }

    public Uri uri() {
        Uri uri = baseUri();
        if (this.mNotify != null) {
            uri = BaseContentProvider.notify(uri, this.mNotify.booleanValue());
        }
        if (this.mGroupBy != null) {
            uri = BaseContentProvider.groupBy(uri, this.mGroupBy);
        }
        if (this.mHaving != null) {
            uri = BaseContentProvider.having(uri, this.mHaving);
        }
        if (this.mLimit != null) {
            return BaseContentProvider.limit(uri, String.valueOf(this.mLimit));
        }
        return uri;
    }

    public int delete(ContentResolver contentResolver) {
        return contentResolver.delete(uri(), sel(), args());
    }

    public int delete(Context context) {
        return context.getContentResolver().delete(uri(), sel(), args());
    }

    public T notify(boolean notify) {
        this.mNotify = Boolean.valueOf(notify);
        return (T) this;
    }

    public T groupBy(String groupBy) {
        this.mGroupBy = groupBy;
        return (T) this;
    }

    public T having(String having) {
        this.mHaving = having;
        return (T) this;
    }

    public T limit(int limit) {
        this.mLimit = Integer.valueOf(limit);
        return (T) this;
    }

    public T orderBy(String order, boolean desc) {
        if (this.mOrderBy.length() > 0) {
            this.mOrderBy.append(COMMA);
        }
        this.mOrderBy.append(order);
        if (desc) {
            this.mOrderBy.append(DESC);
        }
        return (T) this;
    }

    public T orderBy(String order) {
        return orderBy(order, false);
    }

    public T orderBy(String... orders) {
        for (String order : orders) {
            orderBy(order, false);
        }
        return (T) this;
    }

    public int count(ContentResolver resolver) {
        ContentResolver contentResolver = resolver;
        Cursor cursor = contentResolver.query(uri(), new String[]{COUNT}, sel(), args(), null);
        if (cursor == null) {
            return 0;
        }
        try {
            int i;
            if (cursor.moveToFirst()) {
                i = cursor.getInt(0);
            } else {
                i = 0;
            }
            cursor.close();
            return i;
        } catch (Throwable th) {
            cursor.close();
        }
		return mLimit;
    }
}
