package com.tencent.wxcloudrun.dao;


import com.tencent.wxcloudrun.model.FaceInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Squall
 */
@Mapper
public interface FaceInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FaceInfo record);

    int insertSelective(FaceInfo record);

    FaceInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FaceInfo record);

    int updateByPrimaryKey(FaceInfo record);
}