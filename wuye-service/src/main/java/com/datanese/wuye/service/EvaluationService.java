package com.datanese.wuye.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.datanese.wuye.dto.EvaluationDTO;
import com.datanese.wuye.mapper.EvaluationMapper;
import com.datanese.wuye.po.EvaluationPO;
import com.datanese.wuye.po.CommunityPO;
import com.datanese.wuye.util.SnowflakeIdWorker;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by bing.a.qian on 9/8/2017.
 */
@Service
public class EvaluationService {

    @Autowired
    EvaluationMapper evaluationMapper;


    public List<EvaluationDTO> getPreviewEvaluation(Integer communityId) {
        List<EvaluationPO> poList=evaluationMapper.getPreviewEvaluation(communityId);
        List<EvaluationDTO> dtoList=new LinkedList();
        toDTO(poList, dtoList);
        return dtoList;
    }

    public  List<EvaluationDTO> getAllGoodEvaluation(Pageable pageable, Integer communityId){
        Page<EvaluationPO> page=new Page<EvaluationPO>(pageable.getPageNumber(),pageable.getPageSize());
        List<EvaluationPO> poList=evaluationMapper.getAllGoodEvaluation(page,communityId);

        List<EvaluationDTO> dtoList=new LinkedList();
        toDTO(poList, dtoList);
        return dtoList;
    }
    public  List<EvaluationDTO> getAllBadEvaluation(Pageable pageable, Integer communityId) {
        Page<EvaluationPO> page=new Page<EvaluationPO>(pageable.getPageNumber(),pageable.getPageSize());
        List<EvaluationPO> poList=evaluationMapper.getAllBadEvaluation(page,communityId);
        List<EvaluationDTO> dtoList=new LinkedList();
        toDTO(poList, dtoList);
        return dtoList;

    }
    private void toDTO(List<EvaluationPO> poList, List<EvaluationDTO> dtoList) {
        for (EvaluationPO evaluationPO:poList)
        {
            String poString = JSON.toJSONString(evaluationPO);
            EvaluationDTO evaluationDTO = JSON.parseObject(poString, EvaluationDTO.class);
            evaluationDTO.setImageURL(convertUrls2StringArray(evaluationPO.getUrls()));
            //updateUserNameAndAvatar(evaluationDTO);
            dtoList.add(evaluationDTO);
        }
    }

    private String[] convertUrls2StringArray(String urls){
        if(StringUtils.isBlank(urls)){
            return new String[0];
        }
        List<String> result = Splitter.on(",").trimResults().splitToList(urls);
        if(result.size()==1&&StringUtils.isBlank(result.get(0))){
            return new String[0];
        }
        return result.toArray(new String[result.size()]);


    }

    private void updateUserNameAndAvatar(EvaluationDTO evaluationDTO) {
        evaluationDTO.setUserImage(evaluationDTO.getUserId()+"image");
        evaluationDTO.setUserNickName(evaluationDTO.getUserId()+"nickname");
    }

    public void review(@RequestBody EvaluationDTO evaluationDTO) {
        evaluationDTO.setCreateTime(null);
        String dtoString = JSON.toJSONString(evaluationDTO);
        EvaluationPO evaluationPO = JSON.parseObject(dtoString, EvaluationPO.class);
        if(evaluationDTO.getImageURL()!=null){
            String result = Joiner.on(",").join(evaluationDTO.getImageURL());
            evaluationPO.setUrls(result);
        }
        evaluationPO.setId(SnowflakeIdWorker.nextId());
        evaluationMapper.insertEvaluation(evaluationPO);
    }

    public List<CommunityPO> search(String keyword) {
        return null;
    }

    public List<EvaluationPO> getAllEvaluation(Integer communityId) {

        return new ArrayList<>();
    }



    public long[] getEvaluationNumbers(Integer communityId){
       long positiveNumber= evaluationMapper.getEvaluationNumber(communityId,1);
       long negativeNumber= evaluationMapper.getEvaluationNumber(communityId,-1);
       return new long[]{positiveNumber,negativeNumber};
    }


    public static void main(String[] args){
        String input=null;
        List<String> sss= Splitter.on(",").trimResults().splitToList(null);
        if(input==null){

        }
        sss.size();

    }
}


