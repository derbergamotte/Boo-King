package com.ak.dao;

import org.springframework.stereotype.Repository;

import com.ak.api.dao.IActivatorDao;
import com.ak.entities.Activator;
import com.ak.entities.Activator_;

@Repository
public class ActivatorDao extends AGenericDao<Activator> implements IActivatorDao {

    public ActivatorDao() {
	super(Activator.class);
    }

    @Override
    public Activator getActivatorByCode(String code) {
	return singleResultByQuery(code, Activator_.CODE);
    }

}
