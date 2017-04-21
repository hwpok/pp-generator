package com.huiwanpeng.ppcg.ui.model;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

/**
 * 表模型
 * @version 1.0  
 */
public class CheckTableModel extends DefaultTableModel
{
    private static final long serialVersionUID = -1494841267383052111L;
    
    public CheckTableModel(Vector<Vector<?>> data,  Vector<String> columnNames){
        super(data, columnNames);
    }
    
    public Class<?> getColumnClass(int c){
        return getValueAt(0, c).getClass();
    }
    
    public void selectAllOrNull(boolean value){
        for(int i=0;i<getRowCount();i++){
            this.setValueAt(value, i, 0);
        }
    }
    
}
