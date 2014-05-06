package com.me.h.lycaon.animeseichibacklog.persistence;

import com.me.h.lycaon.animeseichibacklog.domain.Remark;

import java.util.List;

/**
 * Interface for MyBatis
 * <p/>
 * Method naming rule:
 * As much as possible, the method names should begin with that executing sql command name.
 * <p/>
 * For instance:
 * a method execute "SELECT ..." -> the method name begin with "select"
 * a method execute "INSERT ..." -> the method name begin with "insert"
 * <p/>
 * Created by lycaon_h on 2014/03/04.
 */
public interface RemarkMapper {

    public void insert(Remark remark);


    public long selectLastSerialId();


    public List<Remark> selectAll();


    public List<Remark> selectByFeatureId(long featureId);


    public Remark selectById(long remarkId);


    public void delete(long remarkId);


    public long count();

}
