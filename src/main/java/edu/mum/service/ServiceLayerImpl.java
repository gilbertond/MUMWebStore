///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package edu.mum.service;
//
//import edu.mum.dao.DaoLayer;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// *
// * @author gilberto
// */
//@Service("servicelayer")
//public class ServiceLayerImpl implements ServiceLayer {
//
//    @Autowired
//    DaoLayer daoLayer;
//
//    @Override
//    public void saveRecord(Object object) {
//        daoLayer.saveRecord(object);
//    }
//
//    @Override
//    public <T> void updateRecord(T object) {
//        daoLayer.updateRecord(object);
//    }
//
//    @Override
//    public <T> T loadEntityObject(Class<T> entity, Integer pkid) {
//        return daoLayer.loadEntityObject(entity, pkid);
//    }
//
//    @Override
//    public <T> int deleteRecord(Class<T> entity, String pk, Integer pkid) {
//        return daoLayer.deleteRecord(entity, pk, pkid);
//    }
//
//    @Override
//    public void updateRecord(Class entity, String[] fields, Object[] fieldValues, String pk, Integer pkid) {
//        daoLayer.updateRecord(entity, fields, fieldValues, pk, pkid);
//    }
//
//    @Override
//    public List<?> fetchRecord(Class clazz, String[] fields, String whereClause, String[] params, Object[] paramsValues) {
//        return daoLayer.fetchRecord(clazz, fields, whereClause, params, paramsValues);
//    }
//}
