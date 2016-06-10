/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iplayer;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author billd
 */
public class getFile {
    public String[] getListSong(String Texto,String Formato){
     JFileChooser dialogo=new JFileChooser();
     dialogo.setMultiSelectionEnabled(true);
     dialogo.setFileFilter(new FileNameExtensionFilter(Texto,Formato));
        int opcion=dialogo.showOpenDialog(null); 
        if(opcion==JFileChooser.APPROVE_OPTION){
            File Archivos[]=dialogo.getSelectedFiles();
            String Ubicaciones[]=new String[Archivos.length];
            int i=0;
            for(File Aux:Archivos){
                Ubicaciones[i]=Aux.getPath();
                i++;
            }
            return Ubicaciones;
        }   
            return null;
    }
}
