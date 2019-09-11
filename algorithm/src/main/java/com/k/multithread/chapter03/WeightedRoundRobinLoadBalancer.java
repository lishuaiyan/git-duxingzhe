package com.k.multithread.chapter03;

/**
 * 加权轮询负载均衡算法实现类
 */
public class WeightedRoundRobinLoadBalancer extends AbstractLoadBalancer {
    //私有构造器
    private WeightedRoundRobinLoadBalancer(Candidate candidate) {
        super(candidate);
    }
    //通过该静态方法创建该类的实例
    public static LoadBalancer newInsatnce(Candidate candidate) {
        WeightedRoundRobinLoadBalancer lb = new WeightedRoundRobinLoadBalancer(candidate);
        lb.init();
        return lb;
    }
    //在该方法中实现响应的负载均衡算法
    @Override
    public Endpoint nextEndpoint() {
        Endpoint selectedEndpoint = null;
        int subWeight = 0;
        int dynamicTototalWeight;
        final double rawRnd = super.random.nextDouble();
        int rand;
        //读取volatile变量candidate
        final Candidate candidate = super.candidate;
        dynamicTototalWeight = candidate.totalWeight;
        for (Endpoint endpoint : candidate) {
            //选取节点及计算总权重时跳过非在线节点
            if (!endpoint.isOnline()) {
                dynamicTototalWeight -= endpoint.weight;
                continue;
            }
            rand = (int) (rawRnd * dynamicTototalWeight);
            subWeight += endpoint.weight;
            if (rand <= subWeight) {
                selectedEndpoint = endpoint;
                break;
            }
        }
        return selectedEndpoint;
    }
}
