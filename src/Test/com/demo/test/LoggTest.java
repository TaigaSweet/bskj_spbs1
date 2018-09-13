package com.demo.test;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.node.Node;
import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

public class LoggTest {
    public  static  void  main(String arg[]){
        Node node = nodeBuilder()
                .clusterName("elasticsearch") // 要加入的集群名为elasticsearch
                // .client(true) //如果设置为true，则该节点不会保存数据
                .data(true) // 本嵌入式节点可以保存数据
                .node(); // 构建并启动本节点

        // 获得一个Client对象，该对象可以对子网内的“elasticsearch”集群进行相关操作。
        Client nodeClient = node.client();
    }
}
