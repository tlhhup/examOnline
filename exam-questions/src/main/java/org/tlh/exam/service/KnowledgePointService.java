package org.tlh.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.dto.KnowledgePointDto;
import org.tlh.exam.entity.KnowledgePoint;
import org.tlh.exam.mapper.KnowledgePointMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 离歌笑tlh/hu ping on 2019/5/1
 * <p>
 * Github: https://github.com/tlhhup
 */
@Service
@Transactional(readOnly = true)
public class KnowledgePointService {

    @Autowired
    private KnowledgePointMapper knowledgePointMapper;

    public org.tlh.exam.model.PageInfo findAll(int page,int size,String name){
        PageHelper.startPage(page,size);
        List<KnowledgePoint> knowledgePoints = this.knowledgePointMapper.findAll(name);
        PageInfo<KnowledgePoint> pageInfo = new PageInfo<>(knowledgePoints);
        List<KnowledgePointDto> results = pageInfo.getList().parallelStream().map(this::dealKnowledgePoint2Dto).collect(Collectors.toList());
        org.tlh.exam.model.PageInfo result=new org.tlh.exam.model.PageInfo();
        result.setItems(results);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    public KnowledgePointDto detailById(int id){
        KnowledgePoint knowledgePoint = this.knowledgePointMapper.detailById(id);
        return knowledgePoint!=null?this.dealKnowledgePoint2Dto(knowledgePoint):null;
    }

    @Transactional
    public boolean create(KnowledgePointDto knowledgePointDto){
        KnowledgePoint knowledgePoint = this.dealDto2KnowledgePoint(knowledgePointDto);
        int flag = this.knowledgePointMapper.save(knowledgePoint);
        return flag>0;
    }

    @Transactional
    public boolean deleteById(int id){
        int i = this.knowledgePointMapper.deleteById(id);
        return i>0;
    }

    @Transactional
    public boolean update(KnowledgePointDto knowledgePointDto){
        KnowledgePoint knowledgePoint = this.dealDto2KnowledgePoint(knowledgePointDto);
        int update = this.knowledgePointMapper.update(knowledgePoint);
        return update>0;
    }

    @Transactional
    public boolean active(int id,boolean active){
        int active1 = this.knowledgePointMapper.active(id, active);
        return active1>0;
    }


    private KnowledgePointDto dealKnowledgePoint2Dto(KnowledgePoint knowledgePoint){
        KnowledgePointDto result=new KnowledgePointDto();
        BeanUtils.copyProperties(knowledgePoint,result);
        result.setActive(knowledgePoint.getIsActive());
        return result;
    }

    private KnowledgePoint dealDto2KnowledgePoint(KnowledgePointDto knowledgePointDto){
        KnowledgePoint result=new KnowledgePoint();
        BeanUtils.copyProperties(knowledgePointDto,result);
        result.setIsActive(knowledgePointDto.isActive());
        return result;
    }

}
