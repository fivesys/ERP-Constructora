/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbean;

import com.bean.Concepts;
import com.bean.InsumoBean;
import com.bean.Nivel;
import com.bean.PresupuestoBean;
import com.bean.ProyectoSimple;
import com.bo.FiltroBO;
import com.conexion.ConexionBD;
import com.dao.PresupuestoDAO;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Mickey
 */
@ManagedBean(name = "vistaDos")
@ViewScoped
public class Vista2MBean implements Serializable {

    public Vista2MBean() {
        
    }

    
    private int id_proyecto;
    private String presupuesto;
    private int id_presupuesto;
    private String proyecto;
    
    private Concepts concept;
    private List<Concepts> list = new ArrayList<Concepts>();
    
    private Nivel lvl;
    private List<Nivel> levels = new ArrayList<Nivel>();
    
    private Nivel seclvl;
    private List<Nivel> secLevels = new ArrayList<Nivel>();
    
    private String partida;
    private String subPartida;
    
    private List<InsumoBean> listaIns = new ArrayList<InsumoBean>();
    private InsumoBean insumo;
    
    private ArrayList<ProyectoSimple> listaProy = new ArrayList<ProyectoSimple>();
    private ProyectoSimple proySel;
    
    private ArrayList<PresupuestoBean> listaPres = new ArrayList<PresupuestoBean>();
    private PresupuestoBean presB;
    
    public void obtenerPresupuesto() {
        setId_presupuesto(presB.getId_presupuesto());
        setPresupuesto(presB.getPresupuesto());
    }
    
    public void buscarPresupuesto() {
        
        if(proySel != null) {
            Connection con;
            ConexionBD conexion = new ConexionBD();
            con = conexion.getConexion();
            PresupuestoDAO presD = new PresupuestoDAO(con);
            
            setListaPres(presD.listarPresupuesto(proySel.getId_proyecto()));
            //System.out.println(listaPres.size());
        }
        
    }
    
    public void obtenerProyecto() {
        setId_proyecto(proySel.getId_proyecto());
        setProyecto(proySel.getProyecto());
        buscarPresupuesto();
    }
    
    public void buscarProyecto() {
        Connection con;
        ConexionBD conexion = new ConexionBD();
        con = conexion.getConexion();
        PresupuestoDAO presD = new PresupuestoDAO(con);
        setListaProy(presD.listarProyecto());      
    }
    
    public void listarInsumos() {
        Connection con;
        ConexionBD conexion = new ConexionBD();
        con = conexion.getConexion();
        PresupuestoDAO pres = new PresupuestoDAO(con);
        setListaIns(pres.listarInsumos(concept.getId_concepto()));
        //sumarTotIns();
        
    }
    
    public void cleanFields() {
        setPartida(null);
        setSubPartida(null);
        setLvl(null);
        setSeclvl(null);
        listarConceptos();
    }
    
    public void filters() {
        FiltroBO filter = new FiltroBO();
        setList(filter.createFilter(lvl, seclvl));
    }
    
    public void setSecLevel() {
        setSubPartida(seclvl.getNivel());
    }
    
    public void getSecLvl() {
        
        Connection con;
        ConexionBD conexion = new ConexionBD();
        con = conexion.getConexion();
        PresupuestoDAO presD = new PresupuestoDAO(con);
        setSecLevels(presD.getSecLevel(lvl.getId_partida(), id_presupuesto));
    }
    
    public void setFirstLevel() {
        
        setPartida(lvl.getNivel());
        getSecLvl();
    }
    
    public void getFirstLevels() {
        
        Connection con;
        ConexionBD conexion = new ConexionBD();
        con = conexion.getConexion();
        PresupuestoDAO presD = new PresupuestoDAO(con);
        setLevels(presD.readLevel(id_presupuesto));
        //System.out.println(id_presupuesto);
        //System.out.println("Lista llena");
        //System.out.println(levels.size());
        
    }
    
    public void getNames(String[] arr) {
        setProyecto(arr[0]);
        setPresupuesto(arr[1]);
    }
    
    public void listarConceptos () {
        
        
        Connection con;
        ConexionBD conexion = new ConexionBD();
        con = conexion.getConexion();
        PresupuestoDAO presD = new PresupuestoDAO(con);
        setList(presD.listBudget(id_proyecto, id_presupuesto));
        //System.out.println(id_proyecto +" "+ id_presupuesto);
        getNames(presD.nombrarProyecto(id_proyecto, id_presupuesto));
        getFirstLevels();
        
   
    }

    public int getId_proyecto() {
        return id_proyecto;
    }

    public void setId_proyecto(int id_proyecto) {
        this.id_proyecto = id_proyecto;
    }

    public int getId_presupuesto() {
        return id_presupuesto;
    }

    public void setId_presupuesto(int id_presupuesto) {
        this.id_presupuesto = id_presupuesto;
    }

    public Concepts getConcept() {
        return concept;
    }

    public void setConcept(Concepts concept) {
        this.concept = concept;
    }

    public List<Concepts> getList() {
        return list;
    }

    public void setList(List<Concepts> list) {
        this.list = list;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(String presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Nivel getLvl() {
        return lvl;
    }

    public void setLvl(Nivel lvl) {
        this.lvl = lvl;
    }

    public List<Nivel> getLevels() {
        //getFirstLevels();
        return levels;
    }

    public void setLevels(List<Nivel> levels) {
        this.levels = levels;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public String getSubPartida() {
        return subPartida;
    }

    public void setSubPartida(String subPartida) {
        this.subPartida = subPartida;
    }

    public List<Nivel> getSecLevels() {
        return secLevels;
    }

    public void setSecLevels(List<Nivel> secLevels) {
        this.secLevels = secLevels;
    }

    public Nivel getSeclvl() {
        return seclvl;
    }

    public void setSeclvl(Nivel seclvl) {
        this.seclvl = seclvl;
    }

    public List<InsumoBean> getListaIns() {
        return listaIns;
    }

    public void setListaIns(List<InsumoBean> listaIns) {
        this.listaIns = listaIns;
    }

    public InsumoBean getInsumo() {
        return insumo;
    }

    public void setInsumo(InsumoBean insumo) {
        this.insumo = insumo;
    }

    /**
     * @return the listaProy
     */
    public ArrayList<ProyectoSimple> getListaProy() {
        buscarProyecto();
        return listaProy;
    }

    /**
     * @param listaProy the listaProy to set
     */
    public void setListaProy(ArrayList<ProyectoSimple> listaProy) {
        
        this.listaProy = listaProy;
    }

    /**
     * @return the proySel
     */
    public ProyectoSimple getProySel() {
        return proySel;
    }

    /**
     * @param proySel the proySel to set
     */
    public void setProySel(ProyectoSimple proySel) {
        this.proySel = proySel;
    }

    /**
     * @return the listaPres
     */
    public ArrayList<PresupuestoBean> getListaPres() {
        return listaPres;
    }

    /**
     * @param listaPres the listaPres to set
     */
    public void setListaPres(ArrayList<PresupuestoBean> listaPres) {
        this.listaPres = listaPres;
    }

    /**
     * @return the presB
     */
    public PresupuestoBean getPresB() {
        return presB;
    }

    /**
     * @param presB the presB to set
     */
    public void setPresB(PresupuestoBean presB) {
        this.presB = presB;
    }
}
