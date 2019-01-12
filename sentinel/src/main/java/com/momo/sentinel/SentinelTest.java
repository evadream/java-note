package com.momo.sentinel;/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Sentinel
 *
 * @author huangtao
 * @date 2019/1/10
 * desc：
 */
public class SentinelTest {

    public static void main(String[] args) {
        // 配置规则.
        initFlowRules();

        while (true) {
            Entry entry = null;
            try {
                entry = SphU.entry("HelloWorld");
                // 资源中的逻辑.
//                HelloConsumerService.sayHello();
                Thread.sleep(10);
            } catch (BlockException e1) {
                System.out.println("blocked!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (entry != null) {
                    entry.exit();
                }
            }
        }
    }

    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<FlowRule>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(20);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}

