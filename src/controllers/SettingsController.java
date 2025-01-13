package controllers;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import views.SystemView;


public class SettingsController  implements MouseListener{
    
    private SystemView views;
    
    //Creamos nuestro constrictor el caul recibe una vista 
   public SettingsController(SystemView views){
       this.views = views;
       //Colcoaremos los paneles y jlabels en escucha
       this.views.jLabelProducts.addMouseListener(this);
       this.views.jLabelPurchases.addMouseListener(this);
       this.views.jLabelCustomers.addMouseListener(this);
       this.views.jLabelEmployes.addMouseListener(this);
       this.views.jLabelSuppliers.addMouseListener(this);
       this.views.jLabelCategories.addMouseListener(this);
       this.views.jLabelReports.addMouseListener(this);
       this.views.jLabel1Settings.addMouseListener(this);
   }
    
    

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //Aqui verificamos donde se encuentra el cursor en un momento determinado
        //validamos si el mouse se encuentra encima de la etiqueta productos o opcion productos
        if(e.getSource() == views.jLabelProducts){
            //Aqui le indicamos que cambie el color al pasar el mouse encima
            views.jPanelProducts.setBackground(new Color(152,202,63));
        } else if(e.getSource() == views.jLabelPurchases){
            views.jPanelPurchases.setBackground(new Color(152,202,63));
        } else if(e.getSource() == views.jLabelCustomers){
            views.jPanelCustomers.setBackground(new Color(152,202,63));
        } else if(e.getSource() == views.jLabelEmployes){
            views.jPanelEmployes.setBackground(new Color(152,202,63));
        } else if(e.getSource() == views.jLabelSuppliers){
            views.jPanelSuppliers.setBackground(new Color(152,202,63));
        } else if(e.getSource() == views.jLabelCategories){
            views.jPanelCategories.setBackground(new Color(152,202,63));
        } else if(e.getSource() == views.jLabelReports){
            views.jPanelReports.setBackground(new Color(152,202,63));
        } else if(e.getSource() == views.jLabel1Settings){
            views.jPanelSettings.setBackground(new Color(152,202,63));
        }
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //validamos si el mouse quito el muase encima de la etiqueta
        if(e.getSource() == views.jLabelProducts){
            //Aqui le indicamos que cambie el color al pasar el mouse encima
            views.jPanelProducts.setBackground(new Color(18,45,61));
        } else if(e.getSource() == views.jLabelPurchases){
            views.jPanelPurchases.setBackground(new Color(18,45,61));
        } else if(e.getSource() == views.jLabelCustomers){
            views.jPanelCustomers.setBackground(new Color(18,45,61));
        } else if(e.getSource() == views.jLabelEmployes){
            views.jPanelEmployes.setBackground(new Color(18,45,61));
        } else if(e.getSource() == views.jLabelSuppliers){
            views.jPanelSuppliers.setBackground(new Color(18,45,61));
        } else if(e.getSource() == views.jLabelCategories){
            views.jPanelCategories.setBackground(new Color(18,45,61));
        } else if(e.getSource() == views.jLabelReports){
            views.jPanelReports.setBackground(new Color(18,45,61));
        } else if(e.getSource() == views.jLabel1Settings){
            views.jPanelSettings.setBackground(new Color(18,45,61));
        }
    }
    
}
