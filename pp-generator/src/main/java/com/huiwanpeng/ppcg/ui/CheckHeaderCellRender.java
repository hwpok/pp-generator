package com.huiwanpeng.ppcg.ui;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import com.huiwanpeng.ppcg.ui.model.CheckTableModel;

/**
 * 表头渲染器
 * @version 1.0  
 */
public class CheckHeaderCellRender implements TableCellRenderer
{
    CheckTableModel tablemodel;
    
    JTableHeader tableHeader;
    
    final JCheckBox selectBox;
    
    public CheckHeaderCellRender(JTable table)
    {
        this.tablemodel = (CheckTableModel) table.getModel();
        this.tableHeader = table.getTableHeader();
        selectBox = new JCheckBox(tablemodel.getColumnName(0));
        selectBox.setSelected(false);
        tableHeader.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent e)
                {
                    if (e.getClickCount() > 0)
                    {
                       int selectColumn = tableHeader.columnAtPoint(e.getPoint());
                        if (selectColumn == 0)
                        {
                            boolean selected = !selectBox.isSelected();
                            selectBox.setSelected(selected);
                            tablemodel.selectAllOrNull(selected);
                            tableHeader.repaint();
                        }
                    }
                }
            });
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        String valueStr = (String) value;
        JLabel label = new JLabel(valueStr);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        selectBox.setHorizontalAlignment(SwingConstants.CENTER);
        selectBox.setBorderPainted(true);
        
        JComponent component = (column == 0) ? selectBox : label;
        component.setForeground(tableHeader.getForeground());
        component.setBackground(tableHeader.getBackground());
        component.setFont(tableHeader.getFont());
        component.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        
        return component;
    }
    
}
