package prefux.data.tuple;

import java.util.Date;

import prefux.data.Graph;
import prefux.data.Schema;
import prefux.data.Table;
import prefux.data.Tuple;

/**
 * Tuple implementation that pulls values from a backing data Table.
 * 
 * @author <a href="http://jheer.org">jeffrey heer</a>
 */
public class TableTuple <T extends Table> implements Tuple {

    protected T m_table;
    protected int m_row;
    
    /**
     * Initialize a new TableTuple for the given table and row. Tuples are
     * automatically generated by {@link TupleManager} instances, and
     * so application code should never need to invoke this method.
     * @param table the data Table
     * @param graph ignored by this class
     * @param row the table row index
     */
    protected void init(T table, Graph graph, int row) {
        m_table = table;
        m_row = m_table.isValidRow(row) ? row : -1;
    }
    
    /**
     * @see prefux.data.Tuple#getSchema()
     */
    public Schema getSchema() {
        return m_table.getSchema();
    }
    
    /**
     * @see prefux.data.Tuple#getTable()
     */
    public Table getTable() {
        return m_table;
    }
    
    /**
     * @see prefux.data.Tuple#getRow()
     */
    public int getRow() {
        return m_row;
    }
    
    // ------------------------------------------------------------------------
    // Index Checking
    
    /**
     * @see prefux.data.Tuple#isValid()
     */
    public boolean isValid() {
        return m_row != -1;
    }
    
    /**
     * Invalidates this tuple. Called by an enclosing table when a row 
     * is deleted.
     */
    void invalidate() {
        m_row = -1;
    }
    
    /**
     * Internal validity check. Throw an exception if the tuple is not valid.
     */
    private void validityCheck() {
        if ( m_row == -1 ) {
            throw new IllegalStateException("This tuple is no longer valid. " +
                "It has been deleted from its table");
        }
    }
    
    // ------------------------------------------------------------------------
    // Column Methods
    
    /**
     * @see prefux.data.Tuple#getColumnType(java.lang.String)
     */
    public Class getColumnType(String field) {
        return m_table.getColumnType(field);
    }
    
    /**
     * @see prefux.data.Tuple#getColumnType(int)
     */
    public Class getColumnType(int col) {
        return m_table.getColumnType(col);
    }

    /**
     * @see prefux.data.Tuple#getColumnIndex(java.lang.String)
     */
    public int getColumnIndex(String field) {
        return m_table.getColumnNumber(field);
    }
    
    /**
     * @see prefux.data.Tuple#getColumnCount()
     */
    public int getColumnCount() {
        return m_table.getColumnCount();
    }
    
    /**
     * @see prefux.data.Tuple#getColumnName(int)
     */
    public String getColumnName(int col) {
        return m_table.getColumnName(col);
    }
    
    // ------------------------------------------------------------------------
    // Data Access Methods
    
    /**
     * @see prefux.data.Tuple#canGet(java.lang.String, java.lang.Class)
     */
    public boolean canGet(String field, Class type) {
        return m_table.canGet(field, type);
    }
    
    /**
     * @see prefux.data.Tuple#canSet(java.lang.String, java.lang.Class)
     */
    public boolean canSet(String field, Class type) {
        return m_table.canSet(field, type);
    }
    
    /**
     * @see prefux.data.Tuple#get(java.lang.String)
     */
    public final Object get(String field) {
        validityCheck();
        return m_table.get(m_row, field);
    }
    
    /**
     * @see prefux.data.Tuple#set(java.lang.String, java.lang.Object)
     */
    public final void set(String field, Object value) {
        validityCheck();
        m_table.set(m_row, field, value);
    }
    
    /**
     * @see prefux.data.Tuple#get(int)
     */
    public final Object get(int idx) {
        validityCheck();
        return m_table.get(m_row, idx);
    }

    /**
     * @see prefux.data.Tuple#set(int, java.lang.Object)
     */
    public final void set(int idx, Object value) {
        validityCheck();
        m_table.set(m_row, idx, value);
    }
    
    /**
     * @see prefux.data.Tuple#getDefault(java.lang.String)
     */
    public Object getDefault(String field) {
        validityCheck();
        return m_table.getDefault(field);
    }
    
    /**
     * @see prefux.data.Tuple#revertToDefault(java.lang.String)
     */
    public void revertToDefault(String field) {
        validityCheck();
        m_table.revertToDefault(m_row, field);
    }
    
    // ------------------------------------------------------------------------
    // Convenience Data Access Methods
    
    /**
     * @see prefux.data.Tuple#canGetInt(java.lang.String)
     */
    public final boolean canGetInt(String field) {
        return m_table.canGetInt(field);
    }
    
    /**
     * @see prefux.data.Tuple#canSetInt(java.lang.String)
     */
    public final boolean canSetInt(String field) {
        return m_table.canSetInt(field);
    }
    
    /**
     * @see prefux.data.Tuple#getInt(java.lang.String)
     */
    public final int getInt(String field) {
        validityCheck();
        return m_table.getInt(m_row, field);
    }
    
    /**
     * @see prefux.data.Tuple#setInt(java.lang.String, int)
     */
    public final void setInt(String field, int val) {
        validityCheck();
        m_table.setInt(m_row, field, val);
    }
    
    /**
     * @see prefux.data.Tuple#getInt(int)
     */
    public final int getInt(int col) {
        validityCheck();
        return m_table.getInt(m_row, col);
    }
    
    /**
     * @see prefux.data.Tuple#setInt(int, int)
     */
    public final void setInt(int col, int val) {
        validityCheck();
        m_table.setInt(m_row, col, val);
    }
    
    // --------------------------------------------------------------
    
    /**
     * @see prefux.data.Tuple#canGetLong(java.lang.String)
     */
    public final boolean canGetLong(String field) {
        return m_table.canGetLong(field);
    }
    
    /**
     * @see prefux.data.Tuple#canSetLong(java.lang.String)
     */
    public final boolean canSetLong(String field) {
        return m_table.canSetLong(field);
    }
    
    /**
     * @see prefux.data.Tuple#getLong(java.lang.String)
     */
    public final long getLong(String field) {
        validityCheck();
        return m_table.getLong(m_row, field);
    }
    
    /**
     * @see prefux.data.Tuple#setLong(java.lang.String, long)
     */
    public final void setLong(String field, long val) {
        validityCheck();
        m_table.setLong(m_row, field, val);
    }
    
    /**
     * @see prefux.data.Tuple#getLong(int)
     */
    public final long getLong(int col) {
        validityCheck();
        return m_table.getLong(m_row, col);
    }
    
    /**
     * @see prefux.data.Tuple#setLong(int, long)
     */
    public final void setLong(int col, long val) {
        validityCheck();
        m_table.setLong(m_row, col, val);
    }

    // --------------------------------------------------------------
    
    /**
     * @see prefux.data.Tuple#canGetFloat(java.lang.String)
     */
    public final boolean canGetFloat(String field) {
        return m_table.canGetFloat(field);
    }
    
    /**
     * @see prefux.data.Tuple#canSetFloat(java.lang.String)
     */
    public final boolean canSetFloat(String field) {
        return m_table.canSetFloat(field);
    }
    
    /**
     * @see prefux.data.Tuple#getFloat(java.lang.String)
     */
    public final float getFloat(String field) {
        validityCheck();
        return m_table.getFloat(m_row, field);
    }
    
    /**
     * @see prefux.data.Tuple#setFloat(java.lang.String, float)
     */
    public final void setFloat(String field, float val) {
        validityCheck();
        m_table.setFloat(m_row, field, val);
    }
    
    /**
     * @see prefux.data.Tuple#getFloat(int)
     */
    public final float getFloat(int col) {
        validityCheck();
        return m_table.getFloat(m_row, col);
    }
    
    /**
     * @see prefux.data.Tuple#setFloat(int, float)
     */
    public final void setFloat(int col, float val) {
        validityCheck();
        m_table.setFloat(m_row, col, val);
    }
    
    // --------------------------------------------------------------
    
    /**
     * @see prefux.data.Tuple#canGetDouble(java.lang.String)
     */
    public final boolean canGetDouble(String field) {
        return m_table.canGetDouble(field);
    }
    
    /**
     * @see prefux.data.Tuple#canSetDouble(java.lang.String)
     */
    public final boolean canSetDouble(String field) {
        return m_table.canSetDouble(field);
    }
    
    /**
     * @see prefux.data.Tuple#getDouble(java.lang.String)
     */
    public final double getDouble(String field) {
        validityCheck();
        return m_table.getDouble(m_row, field);
    }
    
    /**
     * @see prefux.data.Tuple#setDouble(java.lang.String, double)
     */
    public final void setDouble(String field, double val) {
        validityCheck();
        m_table.setDouble(m_row, field, val);
    }
    
    /**
     * @see prefux.data.Tuple#getDouble(int)
     */
    public final double getDouble(int col) {
        validityCheck();
        return m_table.getDouble(m_row, col);
    }
    
    /**
     * @see prefux.data.Tuple#setDouble(int, double)
     */
    public final void setDouble(int col, double val) {
        validityCheck();
        m_table.setDouble(m_row, col, val);
    }
    
    // --------------------------------------------------------------
    
    /**
     * @see prefux.data.Tuple#canGetBoolean(java.lang.String)
     */
    public final boolean canGetBoolean(String field) {
        return m_table.canGetBoolean(field);
    }
    
    /**
     * @see prefux.data.Tuple#canSetBoolean(java.lang.String)
     */
    public final boolean canSetBoolean(String field) {
        return m_table.canSetBoolean(field);
    }
    
    /**
     * @see prefux.data.Tuple#getBoolean(java.lang.String)
     */
    public final boolean getBoolean(String field) {
        validityCheck();
        return m_table.getBoolean(m_row, field);
    }
    
    /**
     * @see prefux.data.Tuple#setBoolean(java.lang.String, boolean)
     */
    public final void setBoolean(String field, boolean val) {
        validityCheck();
        m_table.setBoolean(m_row, field, val);
    }
    
    /**
     * @see prefux.data.Tuple#getBoolean(int)
     */
    public final boolean getBoolean(int col) {
        validityCheck();
        return m_table.getBoolean(m_row, col);
    }
    
    /**
     * @see prefux.data.Tuple#setBoolean(java.lang.String, boolean)
     */
    public final void setBoolean(int col, boolean val) {
        validityCheck();
        m_table.setBoolean(m_row, col, val);
    }
    
    // --------------------------------------------------------------
    
    /**
     * @see prefux.data.Tuple#canGetString(java.lang.String)
     */
    public final boolean canGetString(String field) {
        return m_table.canGetString(field);
    }
    
    /**
     * @see prefux.data.Tuple#canSetString(java.lang.String)
     */
    public final boolean canSetString(String field) {
        return m_table.canSetString(field);
    }
    
    /**
     * @see prefux.data.Tuple#getString(java.lang.String)
     */
    public final String getString(String field) {
        validityCheck();
        return m_table.getString(m_row, field);
    }
    
    /**
     * @see prefux.data.Tuple#setString(java.lang.String, java.lang.String)
     */
    public final void setString(String field, String val) {
        validityCheck();
        m_table.setString(m_row, field, val);
    }
    
    /**
     * @see prefux.data.Tuple#getString(int)
     */
    public final String getString(int col) {
        validityCheck();
        return m_table.getString(m_row, col);
    }
    
    /**
     * @see prefux.data.Tuple#setString(int, java.lang.String)
     */
    public final void setString(int col, String val) {
        validityCheck();
        m_table.setString(m_row, col, val);
    }
    
    // --------------------------------------------------------------
    
    /**
     * @see prefux.data.Tuple#canGetDate(java.lang.String)
     */
    public final boolean canGetDate(String field) {
        return m_table.canGetDate(field);
    }
    
    /**
     * @see prefux.data.Tuple#canSetDate(java.lang.String)
     */
    public final boolean canSetDate(String field) {
        return m_table.canSetDate(field);
    }
    
    /**
     * @see prefux.data.Tuple#getDate(java.lang.String)
     */
    public final Date getDate(String field) {
        validityCheck();
        return m_table.getDate(m_row, field);
    }
    
    /**
     * @see prefux.data.Tuple#setDate(java.lang.String, java.util.Date)
     */
    public final void setDate(String field, Date val) {
        validityCheck();
        m_table.setDate(m_row, field, val);
    }
    
    /**
     * @see prefux.data.Tuple#getDate(int)
     */
    public final Date getDate(int col) {
        validityCheck();
        return m_table.getDate(m_row, col);
    }
    
    /**
     * @see prefux.data.Tuple#setDate(java.lang.String, java.util.Date)
     */
    public final void setDate(int col, Date val) {
        validityCheck();
        m_table.setDate(m_row, col, val);
    }
    
    // ------------------------------------------------------------------------
    
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Tuple[");
        for ( int i=0; i<getColumnCount(); ++i ) {
            if ( i > 0 ) sb.append(',');
            try {
                sb.append(get(i).toString());
            } catch ( Exception e ) {
                sb.append("?");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
} // end of class TableTuple
