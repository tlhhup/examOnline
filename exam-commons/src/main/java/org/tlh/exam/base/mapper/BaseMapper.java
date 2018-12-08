package org.tlh.exam.base.mapper;

import org.tlh.exam.base.entity.BaseEntity;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by 离歌笑tlh/hu ping on 2018/12/8
 * <p>
 * Github: https://github.com/tlhhup
 */
@org.apache.ibatis.annotations.Mapper
public interface BaseMapper<T extends BaseEntity> extends Mapper<T> {
}
