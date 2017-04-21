package com.huiwanpeng.ppcg.ui.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.huiwanpeng.ppcg.logic.tblinfo.xls.util.MyDecode;
import com.huiwanpeng.ppcg.ui.lang.ILang;
import com.huiwanpeng.ppcg.ui.lang.LangFactory;
import com.huiwanpeng.ppcg.ui.model.UIParaBean;

public class AboutPanel extends JPanel
{
    
    private static final long serialVersionUID = 7429911398998651223L;
    
    // 语言
    private ILang lang;

    /**
     * Create the panel.
     */
    public AboutPanel(UIParaBean uiParaBean)
    {
        this.lang = LangFactory.getLang(uiParaBean.getLanguage());
        setLayout(null);
        
        
         // 捐助面板
        JPanel donatePl =  new JPanel();
        donatePl.setToolTipText(lang.getPayWarnToolTipTxt());
        donatePl.setBounds(14, 53, 440, 209);
        donatePl.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), lang.getATPDonateTile(), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        donatePl.setLayout(null);
        donatePl.add(new JLabel("aaa"));
        add(donatePl);
        
        // 捐助面板上的二维码面板
        /**/
        JPanel qrCodeAliPl = new JPanel(){
            private static final long serialVersionUID = 5561007777114581911L;
                protected void paintComponent(Graphics g){
                    ImageIcon icon = MyDecode.getImageAli();
                    Image img =icon.getImage();
                    g.drawImage(img, 0, 0, icon.getIconWidth(), icon.getIconHeight(),icon.getImageObserver());
                }
            };
         
        //JPanel qrCodeAliPl = new JPanel();
        qrCodeAliPl.setBackground(Color.red);
        qrCodeAliPl.setBounds(75, 46, 100, 100);
        donatePl.add(qrCodeAliPl);
        
        
        /**/
        JPanel qrCodeWXPl = new JPanel(){
            private static final long serialVersionUID = 5561007777114581912L;
                protected void paintComponent(Graphics g){
                    ImageIcon icon = MyDecode.getImageWX();
                    Image img =icon.getImage();
                    g.drawImage(img, 0, 0, icon.getIconWidth(), icon.getIconHeight(),icon.getImageObserver());
                }
            };
         
        //JPanel qrCodeWXPl = new JPanel();
        qrCodeWXPl.setBackground(Color.red);
        qrCodeWXPl.setBounds(265, 46, 100, 100);
        donatePl.add(qrCodeWXPl);
        
        JLabel aliPayScanLb = new JLabel(lang.getATPDonateAliScan());
        aliPayScanLb.setForeground(Color.BLACK);
        aliPayScanLb.setFont(new Font("宋体", Font.PLAIN, 11));
        aliPayScanLb.setHorizontalAlignment(SwingConstants.CENTER);
        aliPayScanLb.setBounds(75, 24, 100, 15);
        donatePl.add(aliPayScanLb);
        
        JLabel aliAccountLb = new JLabel(lang.getATPDonateAliAccount());
        aliAccountLb.setForeground(Color.BLACK);
        aliAccountLb.setFont(new Font("宋体", Font.PLAIN, 11));
        aliAccountLb.setHorizontalAlignment(SwingConstants.CENTER);
        aliAccountLb.setBounds(75, 152, 100, 15);
        donatePl.add(aliAccountLb);
        
        JLabel warmLb = new JLabel(lang.getATPDonateWarm());
        warmLb.setFont(new Font("宋体", Font.PLAIN, 11));
        warmLb.setForeground(Color.red);
        warmLb.setHorizontalAlignment(SwingConstants.CENTER);
        warmLb.setBounds(10, 175, 430, 15);
        donatePl.add(warmLb);
        
        JLabel label = new JLabel(lang.getATPDonateWXScan());
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("宋体", Font.PLAIN, 11));
        label.setBounds(265, 24, 100, 15);
        donatePl.add(label);
        
        JLabel lblNewLabel = new JLabel(lang.getATPDonateWXAccount());
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 11));
        lblNewLabel.setBounds(265, 152, 100, 15);
        donatePl.add(lblNewLabel);
        
        JPanel softwareInfoPl = new JPanel();
        softwareInfoPl.setForeground(SystemColor.controlText);
        softwareInfoPl.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        softwareInfoPl.setBackground(UIManager.getColor("Button.background"));
        softwareInfoPl.setBounds(14, 276, 440, 84);
        add(softwareInfoPl);
        softwareInfoPl.setLayout(null);
        
        JLabel otherInfoLb = new JLabel(lang.getATPSoftInfoWebSite());
        otherInfoLb.setForeground(Color.BLUE);
        otherInfoLb.setBounds(10, 10, 382, 15);
        softwareInfoPl.add(otherInfoLb);
        
        JLabel copyRightLb = new JLabel(lang.getATPSoftInfoCopyRight());
        copyRightLb.setForeground(Color.BLUE);
        copyRightLb.setBounds(10, 59, 397, 15);
        softwareInfoPl.add(copyRightLb);
        
        JLabel lblNewLabel_1 = new JLabel(lang.getATPSoftInfoQQGroup());
        lblNewLabel_1.setForeground(Color.BLUE);
        lblNewLabel_1.setBounds(10, 28, 312, 15);
        softwareInfoPl.add(lblNewLabel_1);
        
        JLabel lblEmialHwpokcom = new JLabel(lang.getATPSoftInfoEmail());
        lblEmialHwpokcom.setForeground(Color.BLUE);
        lblEmialHwpokcom.setBounds(10, 43, 201, 15);
        softwareInfoPl.add(lblEmialHwpokcom);
        
        JPanel panel = new JPanel();
        panel.setBounds(14, 10, 440, 35);
        panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        add(panel);
        panel.setLayout(null);
        
        JLabel softwareInfoLb = new JLabel(lang.getATPSoftName());
        softwareInfoLb.setHorizontalAlignment(SwingConstants.CENTER);
        softwareInfoLb.setBounds(10, 9, 420, 18);
        panel.add(softwareInfoLb);
        softwareInfoLb.setFont(new Font("宋体", Font.PLAIN, 14));
        softwareInfoLb.setForeground(Color.BLUE);
    }
}
