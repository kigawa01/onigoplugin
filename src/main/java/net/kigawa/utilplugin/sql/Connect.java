package net.kigawa.utilplugin.sql;

import net.kigawa.utilplugin.UtilConfig;
import net.kigawa.utilplugin.UtilPlugin;

import java.sql.*;

public class Connect {
    String url;
    UtilPlugin plugin;
    public Connect(UtilConfig config, UtilPlugin kigawaUtilLib){
        plugin=kigawaUtilLib;
        String host= config.getHost();
        int port=config.getPort();
        String database=config.getDatabase();
        String user=config.getUser();
        String password=config.getPassword();
        String option=config.getOption();
        url="jdbc:mysql://"+host+":"+port+"/"+database+"?user="+user+"&password="+password+option;
    }
    public void setData(DataSql datasql){
        try {
            Connection connection=DriverManager.getConnection(url);
            Statement statement=connection.createStatement();
            DatabaseMetaData databaseMetaData=connection.getMetaData();

            String table=datasql.getTable();
            String[] columns=datasql.getColumns();
            String[] type=datasql.getType();

            ResultSet resultSetTb=databaseMetaData.getTables(null,null,table,null);
            String createTableColumn="";
            if(!resultSetTb.next()){
                String toCreateTable;
                for(int i=0;i<columns.length;i++){
                    toCreateTable=createTableColumn+columns[i]+" "+type[i]+",";
                    createTableColumn=toCreateTable;
                }
                StringBuilder createTableColumnBuilder=new StringBuilder(createTableColumn);
                createTableColumnBuilder.setLength(createTableColumnBuilder.length()-1);
                createTableColumn=createTableColumnBuilder.toString();
                String createTable="CREATE TABLE "+table+" ("+createTableColumn+")";
                statement.executeUpdate(createTable);
            }
            resultSetTb.close();

            String[] values= datasql.getValue();
            String[] where= datasql.getWhere();
            String isData="SELECT "+where[0]+" FROM "+table+" WHERE "+where[1]+"="+where[1];
            ResultSet resultSetIsData=statement.executeQuery(isData);
            if(!resultSetIsData.next()){
                String column="";
                String toInsert;
                for(int i1=0;i1<columns.length;){
                    toInsert=column+columns[i1]+",";
                    column=toInsert;
                    i1++;
                }
                StringBuilder columnBuffer=new StringBuilder(column);
                columnBuffer.setLength(columnBuffer.length()-1);
                column=columnBuffer.toString();
                String value="";
                String toValue;
                for(int i2=0;i2< values.length;){
                    toValue=value+values[i2]+",";
                    value=toValue;
                    i2++;
                }
                StringBuilder valueBuffer=new StringBuilder(value);
                valueBuffer.setLength(valueBuffer.length()-1);
                value=valueBuffer.toString();
                String insert="INSERT INTO "+table+" ("+column+") VALUES ("+value+")";
                statement.executeUpdate(insert);
            }else {
                String update="";
                String toUpdate;
                for(int i3=0;i3< columns.length;i3++){
                    toUpdate=update+columns[i3]+"="+values[i3]+",";
                    update=toUpdate;
                }
                StringBuilder updateBuilder=new StringBuilder(update);
                updateBuilder.setLength(updateBuilder.length()-1);
                update=updateBuilder.toString();
                String updateStr="UPDATE "+table+" "+update+" WHERE "+where[0]+"="+where[1];
                statement.executeUpdate(updateStr);
            }
            resultSetIsData.close();

            statement.close();
            connection.close();
        }catch (SQLException e){
            plugin.getLogger().info(e.toString());
        }

    }
    public DataSql getDataSql(DataSql dataSql){
        try {
            Connection connection=DriverManager.getConnection(url);
            Statement statement=connection.createStatement();

            String[] where=dataSql.getWhere();
            String table=dataSql.getTable();
            String[] columns=dataSql.getColumns();

            String column="";
            String toColumn;
            for(int i1=0;i1<columns.length;){
                toColumn=column+columns[i1]+",";
                column=toColumn;
                i1++;
            }
            StringBuilder columnBuffer=new StringBuilder(column);
            columnBuffer.setLength(columnBuffer.length()-1);
            column=columnBuffer.toString();

            String isData="SELECT "+column+" FROM "+table+" WHERE "+where[0]+"="+where[1];
            ResultSet resultSet=statement.executeQuery(isData);
            if(resultSet.next()){
                String[] value=dataSql.getValue();
                for (int i=0;i<columns.length;i++){
                    value[i]=resultSet.getString(columns[i]);
                }
                dataSql.setValue(value);
            }else {
                plugin.getLogger().info("Can't get dataSql.");
            }

            resultSet.close();
            statement.close();
            connection.close();
        }catch (Exception e){
            plugin.getLogger().info(e.toString());
        }

        return dataSql;
    }
    public DataSql[] getDataSqlAll(DataSql dataSql){
        DataSql[] dataSqls;
        try {
            Connection connection=DriverManager.getConnection(url);
            Statement statement=connection.createStatement();

            String table=dataSql.getTable();
            String[] columns=dataSql.getColumns();

            String column="";
            String toColumn;
            for(int i1=0;i1<columns.length;){
                toColumn=column+columns[i1]+",";
                column=toColumn;
                i1++;
            }
            StringBuilder columnBuffer=new StringBuilder(column);
            columnBuffer.setLength(columnBuffer.length()-1);
            column=columnBuffer.toString();

            String countdata="SELECT "+dataSql.getWhere()[0]+" FROM "+table;
            ResultSet countrs=statement.executeQuery(countdata);

            String isData="SELECT "+column+" FROM "+table;
            ResultSet resultSet=statement.executeQuery(isData);
            int i3 = 0;
            while (countrs.next()) {
                i3++;
            }
            countrs.close();
            dataSqls=new DataSql[i3];
            for(int i1 = 0; resultSet.next(); i1++){
                String[] value=dataSql.getValue();
                for (int i=0;i<columns.length;i++){
                    value[i]=resultSet.getString(columns[i]);
                }
                dataSqls[i1].setValue(value);
            }

            resultSet.close();
            statement.close();
            connection.close();
        }catch (Exception e){
            plugin.getLogger().info(e.toString());
            dataSqls=new DataSql[0];
        }
        return dataSqls;
    }
    public void DataSqlDelete(String table, String[] where){
        try {
            Connection connection=DriverManager.getConnection(url);
            Statement statement=connection.createStatement();

            String delete="DELETE FROM "+table+" WHERE "+where[0]+"="+where[1];
            statement.executeUpdate(delete);

            statement.close();
            connection.close();
        }catch (Exception e){
            plugin.getLogger().info(e.toString());
        }
    }
}
