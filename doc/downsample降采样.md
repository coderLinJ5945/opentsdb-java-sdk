# downsample 降采样分析

## demo
降采样分析查询例子：
```aidl
{
	"start": "1533571200000",
	"end": "1567094400000",
	"queries": [{
		"aggregator": "none",
		"metric": "r",
		"tags": {
			"deviceId": "G3529"
		},
		"downsample": "1h-first-none"
	}]

}
```
降采样参数说明：
1. "downsample": "1h-first-none"，表示以一小时为单位，采集每个小时的第一个值
2. 第一个"1h" 支持时间自定义，小时：h; 分钟：m; 天：d;
3. 第二个"first",这里是可选的聚合函数，需要注意的是不能为none
4. 第三个"none",指的是在时间单位内，未取到值做什么处理，包括：none、nan(非数)、zero 和 null


## 使用场景
1. 大数据量查询，可以按照自定义进行过滤查询，提高查询速度
2. 不需要过度关注细节的查询，只需要关注大概数据走向
