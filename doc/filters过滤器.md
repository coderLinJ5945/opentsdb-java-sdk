# filters 过滤器查询(opentsdb 2.4之后支持)

## demo以及说明
普通查询参数：
```aidl
{
	"start": "1533571200000",
	"end": "1567094400000",
	"queries": [{
		"aggregator": "none",
		"metric": "waterLevel"
	}]
}
```
参数说明：
1. start 开始时间
2. end 结束时间
3. queries查询数据集
4. aggregator 聚合函数为none
5. 查询metric 为 waterLevel 的数据

过滤查询参数：
```aidl
{
	"start": "1533571200000",
	"end": "1567094400000",
	"queries": [{
		"aggregator": "none",
		"metric": "waterLevel",
		"filters": [
			{
				"type": "literal_or",
				"tagk": "productKey",
				"filter":"WX19007|WX19001",
				"groupBy": true 				
				
			}
		]
	}]
}
```
过滤查询参数说明：
1. filters 过滤的内容集合
2. type 过滤器的类型 （从 http://ip:4242/api/config/filters 获取）
3. filter 需要过滤的 where 具体函数（自定义）
4. groupBy 是否分组


## 使用场景
按照业务的条件要求进行参数查询，简单理解类似关系型数据库的sql的自定义条件拼接查询
