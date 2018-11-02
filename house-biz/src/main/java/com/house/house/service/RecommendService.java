package com.house.house.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.house.house.common.bean.House;
import com.house.house.common.page.PageParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/11/2
 * @ Description：热门房产业务
 * @ throws
 */
@Service
public class RecommendService {

    @Autowired
    private HouseService houseService;

    private static final String HOT_HOUSE_KEY = "hot_house";

    private static final Logger logger = LoggerFactory.getLogger(RecommendService.class);

    public List<House> getHotHouse(Integer recomSize) {

        //获取Redis中热门房产的ids
        List<Long> ids = getHot();
        if (ids.isEmpty()){
            return Lists.newArrayList();
        }
        House house = new House();
        //取出规定size内的房产
        List<Long> idList = ids.subList(0, Math.min(ids.size(), recomSize));
        house.setIds(idList);
        final List<Long> order = ids;
        List<House> houses = houseService.queryAndSetImg(house, PageParams.build(recomSize, 1));
        //将houses结构倒序排列
        Ordering<House> houseSort = Ordering.natural().onResultOf(hs -> order.indexOf(hs.getId()));
        return houseSort.sortedCopy(houses);
    }

    private List<Long> getHot() {

        List<Long> idList = null;
        try {
            Jedis jedis = new Jedis("127.0.0.1");
            Set<String> idSet = jedis.zrevrange(HOT_HOUSE_KEY, 0, -1);
            jedis.close();
            //将string转换为long
            idList = idSet.stream().map(Long::parseLong).collect(Collectors.toList());
        } catch (Exception e) {
            logger.info(e.toString());
        }
        return idList;
    }

    /**
     * @ Author jmy
     * @ Description 添加房屋id到Redis中//TODO User
     * @ Date 2018/11/2
     * @ Param [id]
     * @ return void
     **/
    public void increase(Long id) {

        try {
            Jedis jedis = new Jedis("127.0.0.1");
            //同一个key值,增加分值
            jedis.zincrby(HOT_HOUSE_KEY, 1.0D, id+"");
            // 0代表第一个元素,-1代表最后一个元素，保留热度由低到高末尾10个房产
            jedis.zremrangeByRank(HOT_HOUSE_KEY, 0, -11);
            jedis.close();
        }catch ( Exception e){
            logger.info(e.toString());
        }
    }
}
