package com.ak.api.dao;

import com.ak.entities.Bookdetail;

public interface IBookdetailDao extends IAGenericDao<Bookdetail>{

    Bookdetail getByIsbn(String isbn);

}
