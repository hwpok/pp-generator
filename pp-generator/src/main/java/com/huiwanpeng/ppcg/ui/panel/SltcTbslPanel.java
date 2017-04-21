package com.huiwanpeng.ppcg.ui.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumn;

import com.huiwanpeng.ppcg.logic.generator.XbatisGenarator;
import com.huiwanpeng.ppcg.ui.CheckHeaderCellRender;
import com.huiwanpeng.ppcg.ui.lang.ILang;
import com.huiwanpeng.ppcg.ui.lang.LangFactory;
import com.huiwanpeng.ppcg.ui.model.CheckTableModel;
import com.huiwanpeng.ppcg.ui.model.TableRowBean;
import com.huiwanpeng.ppcg.ui.model.UIParaBean;
import com.huiwanpeng.ppcg.ui.util.ComboxUtil;
import com.huiwanpeng.ppcg.ui.util.MsgUtil;
import com.huiwanpeng.ppcg.util.logs.ComRuntimeException;
import com.huiwanpeng.ppcg.util.logs.ExceptionInfoUtil;
import com.huiwanpeng.ppcg.util.logs.Logger;

/**
 * 选择数据库表Panel
 * @version 1.0  
 */
public class SltcTbslPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    
    private DbCfgPanel dbCfgPanel;
    private JComboBox<String> ftrCbx;
    private JButton ftrBtn;
    private JTable table;
    
    private ILang lang;
    
    /**
     * 初始化
     * @param frmXbatis
     * @param uiParaBean
     */
    public void init(JFrame frmXbatis, DbCfgPanel dbCfgPanel, UIParaBean uiParaBean){
        this.dbCfgPanel = dbCfgPanel;
        // 查询数据库表面板
        ComboxUtil.initCdnCbx(ftrCbx, uiParaBean.getCdnCbxItems());
        // 找到数据铵钮
        ftrBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                ComboxUtil.appendAndOrderCdnCbx(ftrCbx);
                queryTables();
            }
        });
    }
    
    /**
     * 查询数据库表
     */
    private void queryTables(){
        try{
            UIParaBean uiParaBean = new UIParaBean();
            dbCfgPanel.getValues(uiParaBean);
            
            // 条件检查, 如果是数据库数据库
            if(0 == uiParaBean.getDataSourceType()){
                if(!dbCfgPanel.canConnectDataBase(true)){
                    return;
                }
            }
            // 参数检查, 如果是Excel数据源
            else{
                if(!dbCfgPanel.checkExcelFile(true)){
                    return;
                }
            }
            
            //查询条件
            String condition = (String)ftrCbx.getSelectedItem();
            
            //查询返回集合
            List<TableRowBean> datas = XbatisGenarator.getTableRowBeanLst(uiParaBean, condition);
            
            // 删除所有的行
            CheckTableModel defaultTableModel = (CheckTableModel)table.getModel();
            defaultTableModel.setRowCount(0);
            
            // 添加数据
            for(TableRowBean bean : datas){
                defaultTableModel.addRow(bean.toVector());
            }
        }catch(ComRuntimeException cex){
            Logger.e(cex.getAllInfo());
            MsgUtil.getShowErrorInfoMessageDialog("Query target table error", cex);
        }
        catch(Exception ex){
            Logger.e(ExceptionInfoUtil.getStackTraceStr(ex));
            MsgUtil.getShowErrorInfoMessageDialog("Query target table error", null);
        }
    }
    
    public void getValues(UIParaBean uiParaBean){
        // 查询数据库表
        uiParaBean.setQueryTableCondtion((String)ftrCbx.getSelectedItem()); // 这个仅仅是查询使用
        uiParaBean.setCdnCbxItems(ComboxUtil.getCdnCbxVal(ftrCbx)); // 所有下拉选项目, 保存在UI参数文件之中
        // 设置选中的要生成的表
        uiParaBean.setSelTableNameLst(this.getSelTableRowBean());
    }
    
    @SuppressWarnings("unchecked")
    private List<TableRowBean> getSelTableRowBean(){
        List<TableRowBean> tableRowBeanLst = new ArrayList<TableRowBean>();
        CheckTableModel defaultTableModel = (CheckTableModel)table.getModel();
        for(Vector<Object> vector :(Vector<Vector<Object>>)defaultTableModel.getDataVector()){
            TableRowBean tableRowBean = new TableRowBean(vector);
            if(tableRowBean.isChecked()){
                tableRowBeanLst.add(tableRowBean);
            }
        }
        return tableRowBeanLst;
    }
    
    
    /**
     * Create the panel.
     */
    public SltcTbslPanel(UIParaBean uiParaBean)
    {
        this.lang = LangFactory.getLang(uiParaBean.getLanguage());
        setLayout(null);
        
        JLabel tableNameLb = new JLabel(lang.getQueryTableName());
        tableNameLb.setHorizontalAlignment(SwingConstants.RIGHT);
        tableNameLb.setBounds(14, 13, 69, 15);
        this.add(tableNameLb);
        
        ftrCbx = new JComboBox<String>();
        ftrCbx.setBounds(93, 10, 281, 21);
        ftrCbx.setEditable(true);
        this.add(ftrCbx);
        
        ftrBtn = new JButton(lang.getQueryTable());
        ftrBtn.setBounds(384, 9, 70, 23);
        ftrBtn.setFocusPainted(false);
        this.add(ftrBtn);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(14, 40, 440, 319);
        this.add(scrollPane);
        
        table = new JTable();
        
        Vector<String> headerNames = new Vector<>();
        headerNames.addElement("");
        headerNames.addElement(lang.getTableColumnTableName());
        headerNames.addElement(lang.getTableColumnComment());
        
        Vector<Vector<?>> datas = new Vector<Vector<?>>();
        
        CheckTableModel tableModel = new CheckTableModel(datas, headerNames);
        
        table.setModel(tableModel);
        table.getTableHeader().setDefaultRenderer(new CheckHeaderCellRender(table));

        TableColumn firstColumn = table.getColumnModel().getColumn(0);
        firstColumn.setPreferredWidth(30);
        firstColumn.setMaxWidth(30);
        firstColumn.setMinWidth(30);
        
        TableColumn secondColumn = table.getColumnModel().getColumn(1);
        secondColumn.setPreferredWidth(150);
        secondColumn.setMinWidth(100);
        secondColumn.setMaxWidth(300);
        //table.setRowHeight(20);
        
        scrollPane.setViewportView(table);
    }
    
}
