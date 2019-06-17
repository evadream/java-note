# mybaits批量更新的几种方法
## 更新单条
```
update shop_manager set marketing_category = #{marketingCategory} where id=#{id}
```
## 更新多条且条件为多值的记录

### 比较普通的写法是逐条更新

* 优点：容易，不易出错，出错只影响当前行
* 缺点：效率差，每条更新都会连接释放数据库资源。

代码示例：
```
<update id="updateBatch"  parameterType="java.util.List">
    <foreach collection="list" item="item" index="index" open="" close="" separator=";">
        update shop_manager
        <set>
            marketing_category=#{item.marketingCategory}
        </set>
        where entity_id = ${item.entityId}
    </foreach>
</update>
```
### 利用 case...when...then...end 语句
mybatis没有直接提供批量更新的语句但是我们可以利用mysql的case...when...then...end语句,如下sql：
```
UPDATE shop_manager
SET marketing_category = (
  CASE entity_id
  WHEN 1
    THEN 3
  WHEN 2
    THEN 4
  END
)

WHERE entity_id in(1,2)
```

注意没有where 会讲故事表都遍历一遍

对比下执行时间（shop_manager 表里面只有7条数据）
![where.png](https://img-blog.csdnimg.cn/20190617143103217.png)


![nowhere.png](https://img-blog.csdnimg.cn/20190617143115564.png)
看到如果没有where，影响了全表的数据

转化成mybatis语句

```
    <!--批量更新-->
    <update id="updateCategoryBatch2">
        UPDATE shop_manager
        SET marketing_category =(
        CASE entity_id
        <foreach collection="list" item="entity">
            when #{entity.entityId} then #{entity.marketingCategory}
        </foreach>
        END
        ),
        op_time = #{opTime}
        WHERE entity_id in
        <foreach collection="list" item="entity" open="(" close=")" separator=",">
            <if test="entity.marketingCategory !=null">
                #{entity.entityId}
            </if>
        </foreach>
    </update>
```

还可以使用<trim>实现
```
    <!--批量更新-->
    <update id="updateCategoryBatch">
        UPDATE shop_manager
        <trim prefix="set">
            <trim prefix="marketing_category=case" suffix="end,">
                <foreach collection="list" item="entity">
                    when entity_id=#{entity.entityId} then #{entity.marketingCategory}
                </foreach>
            </trim>
        </trim>
        op_time = #{opTime}
        WHERE entity_id in
        <foreach collection="list" item="entity" open="(" close=")" separator=",">
            <if test="entity.marketingCategory !=null">
                #{entity.entityId}
            </if>
        </foreach>
    </update>
```
上面两种生成方式生成的slq结果是一样的。
><trim>属性说明
1.prefix,suffix 表示在trim标签包裹的部分的前面或者后面添加内容
2.如果同时有prefixOverrides,suffixOverrides 表示会用prefix,suffix覆盖Overrides中的内容。
3.如果只有prefixOverrides,suffixOverrides 表示删除开头的或结尾的xxxOverides指定的内容。



