package com.mls.trend.service;

import com.mls.trend.entity.IndexData;
import com.mls.trend.entity.Profit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BackTestService {
    @Autowired
    IndexDataClient indexDataClient;

    public List<IndexData> listIndexData(String code){
        List<IndexData> result = indexDataClient.getIndexData(code);
        Collections.reverse(result);

//        for(IndexData indexData:result){
//            System.out.println(indexData.getDate());
//        }

        return result;
    }
    public Map<String,Object> simulate(int ma, float sellRate, float buyRate, float serviceCharge, List<IndexData> indexDatas)  {

        List<Profit> profits =new ArrayList<>();
        float initCash = 1000;          //本金
        float cash = initCash;         //当前手里的资金
        float share = 0;              //股票份额
        float value = 0;              //总资产

        float init =0;                //均线起点的收盘点
        if(!indexDatas.isEmpty())
            init = indexDatas.get(0).getClosePoint();

        for (int i = 0; i<indexDatas.size() ; i++) {
            IndexData indexData = indexDatas.get(i);
            float closePoint = indexData.getClosePoint();
            float avg = getMA(i,ma,indexDatas);         //均线
            float max = getMax(i,ma,indexDatas);        //最高点收盘价

            float increase_rate = closePoint/avg;      //增长率
            float decrease_rate = closePoint/max;      //下跌率

            if(avg!=0) {
                //buy 超过了均线
                if(increase_rate>buyRate  ) {
                    //如果没买
                    if(0 == share) {
                        share = cash / closePoint;
                        cash = 0;
                    }
                }
                //sell 低于了卖点
                else if(decrease_rate<sellRate ) {
                    //如果没卖
                    if(0!= share){
                        cash = closePoint * share * (1-serviceCharge);
                        share = 0;
                    }
                }
                //do nothing
                else{

                }
            }

            if(share!=0) {
                value = closePoint * share;
            }
            else {
                value = cash;
            }
            float rate = value/initCash;

            Profit profit = new Profit();
            profit.setDate(indexData.getDate());
            profit.setValue(rate*init);

            System.out.println("profit.value:" + profit.getValue());
            profits.add(profit);

        }
        Map<String,Object> map = new HashMap<>();
        map.put("profits", profits);
        return map;
    }


    //计算day天内最高收盘价
    private static float getMax(int i, int day, List<IndexData> list) {
        int start = i-day;   //均线计算日期的起点
        if(start<0) return 0;
        int now = i-1;          //均线计算日期的终点
        float max = 0;
        for (int j = start; j <=now; j++) {
            IndexData bean =list.get(j);
            if(bean.getClosePoint()>max) {
                max = bean.getClosePoint();
            }
        }
        return max;
    }

    //计算ma天内均值
    private static float getMA(int i, int ma, List<IndexData> list) {
        int start = i-ma;
        int now = i-1;

        if(start<0)
            return 0;

        float sum = 0;
        float avg = 0;
        for (int j = start; j <=now; j++) {
            IndexData bean =list.get(j);
            sum += bean.getClosePoint();
        }
        avg = sum / (now - start + 1);
        return avg;
    }

}
