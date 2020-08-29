package com.nikhilcodes.curiousbackend.dao;

import com.nikhilcodes.curiousbackend.model.QNAModel;

import java.util.List;

public interface QNADao {
    public List<QNAModel> get10QNAs(int start);
}
