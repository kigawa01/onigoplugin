package net.kigawa.utilplugin.sql;

public interface DataSql {
    String[] getColumns();
    String getTable();
    String[] getType();
    String[] getWhere();
    String[] getValue();
    void setValue(String[] value);
}
