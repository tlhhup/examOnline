package org.tlh.exam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 离歌笑tlh/hu ping on 2019/1/9
 * <p>
 * Github: https://github.com/tlhhup
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo<T> implements Serializable{

    private List<T> items;
    private long total;

}
