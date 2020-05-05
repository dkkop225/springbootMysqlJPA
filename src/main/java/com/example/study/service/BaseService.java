package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

// autowired를 받기 위해서
@Component
public abstract class BaseService <Req,Res,Entity> implements CrudInterface<Req,Res> {

    @Autowired(required = false)
    protected JpaRepository<Entity,Long> baseRepository;

    //JpaRepository<Item,Long>

//    @Override
//    public Header<Res> create(Header<Req> request) {
//        return null;
//    }
//
//    @Override
//    public Header<Res> read(Long id) {
//        return null;
//    }
//
//    @Override
//    public Header<Res> update(Header<Req> request) {
//        return null;
//    }
//
//    @Override
//    public Header delete(Long id) {
//        return null;
//    }
}
