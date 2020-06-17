package com.ak.api.dao;

import com.ak.entities.Activator;

public interface IActivatorDao extends IAGenericDao<Activator>{
    
    Activator getActivatorByCode(String code);

}
