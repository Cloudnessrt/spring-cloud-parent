package com.tomorrow.monitorbusi.Redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * redis集群 配置
 *
 * @AUTHOR TCH
 * @CREATE 2018-02-22
 **/
@Configuration
@ConditionalOnClass({JedisCluster.class})
public class JedisClusterConfig  {

    @Value("${spring.redis.cache.clusterNodes}")
    private String clusterNodes;
    @Value("${spring.redis.cache.commandTimeout}")
    private Integer   commandTimeout;


    @Bean
    @Scope
    public JedisCluster getJedisCluster() {
        String[] serverArray = clusterNodes.split(",");
        Set<HostAndPort> nodes = new HashSet<>();
        for (String ipPort: serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(),Integer.valueOf(ipPortPair[1].trim())));
        }
        return new JedisCluster(nodes, commandTimeout);
    }
}
