package org.opentsdb.client.bean.request;

import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询相关的参数，和opentsdb对应
 * 详见<a>http://opentsdb.net/docs/build/html/api_http/query/index.html</a>
 */
@Data
public class Query {

    /**
     * 开始时间 形如1h-ago
     */
    private String start;

    /***
     * 结束时间 形如1s-ago
     */
    private String end;

    /**
     * 是否以毫秒或秒为单位输出数据点时间戳。建议使用msResolution标志。如果未提供此标志并且在一秒内存在多个数据点，则将使用查询的聚合函数对这些数据点进行下采样。
     */
    private Boolean msResolution;

    /**
     *  是否返回带有查询的注释。默认设置是返回请求的时间跨度的注释，但此标志可以禁用返回
     */
    private Boolean noAnnotations;

    /**
     * 查询是否应检索所请求的时间跨度的全局注释
     */
    private Boolean globalAnnotations;

    /**
     * 是否在结果中输出与时间序列关联的TSUID。如果将多个时间序列聚合到一个集合中，则将以排序的方式返回多个TSUID
     */
    private Boolean showTSUIDs;

    /**
     * 是否在结果中显示查询周围的计时摘要。这将在地图中创建另一个与数据点对象不同的对象。
     */
    private Boolean showSummary;

    /**
     * 是否在结果中显示查询的详细时序
     */
    private Boolean showStats;

    /**
     * 是否使用查询结果返回原始子查询
     */
    private Boolean showQuery;

    /**
     * 基于日历的下采样的可选时区。必须是TSD服务器上安装的JRE支持的有效时区数据库名称
     */
    private String timezone;

    /**
     * 是否使用基于给定时区的日历来进行下采样间隔
     */
    private Boolean useCalendar;

    /**
     * 是否删除
     */
    private Boolean delete = false;

    /**
     * 包含的子查询，至少需要一个子查询
     */
    private List<SubQuery> queries;

    public static class Builder {

        private Long startTimestamp;

        private Long endTimestamp;

        private String start;

        private String end;

        private Boolean msResolution;

        private Boolean noAnnotations;

        private Boolean globalAnnotations;

        private Boolean showTSUIDs;

        private Boolean showSummary;

        private Boolean showStats;

        private Boolean showQuery;

        private String timezone;

        private Boolean useCalendar;

        private List<SubQuery> queries = new ArrayList<>();

        public Query build() {
            Query query = new Query();
            if (this.startTimestamp == null && StringUtils.isBlank(this.start)) {
                throw new IllegalArgumentException("the start time must be set");
            }

            if (CollectionUtils.isEmpty(queries)) {
                throw new IllegalArgumentException("the subQueries must be set");
            }
            query.queries = this.queries;

            if (StringUtils.isNoneBlank(start)) {
                query.start = this.start;
            } else if (this.startTimestamp != null) {
                query.start = this.startTimestamp.toString();
            }

            if (StringUtils.isNoneBlank(end)) {
                query.end = this.end;
            } else if (this.endTimestamp != null) {
                query.end = this.endTimestamp.toString();
            }

            query.msResolution = this.msResolution;
            query.noAnnotations = this.noAnnotations;
            query.globalAnnotations = this.globalAnnotations;
            query.showTSUIDs = this.showTSUIDs;
            query.showSummary = this.showSummary;
            query.showStats = this.showStats;
            query.showQuery = this.showQuery;
            query.timezone = this.timezone;
            query.useCalendar = this.useCalendar;
            return query;
        }

        public Builder begin(Long startTimestamp) {
            this.startTimestamp = startTimestamp;
            return this;
        }

        public Builder end(Long endTimestamp) {
            this.endTimestamp = endTimestamp;
            return this;
        }

        public Builder begin(String start) {
            this.start = start;
            return this;
        }

        public Builder end(String end) {
            this.end = end;
            return this;
        }

        public Builder msResolution() {
            this.msResolution = true;
            return this;
        }

        public Builder noAnnotations() {
            this.noAnnotations = true;
            return this;
        }

        public Builder globalAnnotations() {
            this.globalAnnotations = true;
            return this;
        }

        public Builder showTSUIDs() {
            this.showTSUIDs = true;
            return this;
        }

        public Builder showSummary() {
            this.showSummary = true;
            return this;
        }

        public Builder showStats() {
            this.showStats = true;
            return this;
        }

        public Builder showQuery() {
            this.showQuery = true;
            return this;
        }

        public Builder timezone(String timezone) {
            this.timezone = timezone;
            return this;
        }

        public Builder useCalendar() {
            this.useCalendar = true;
            return this;
        }

        public Builder sub(SubQuery subQuery) {
            queries.add(subQuery);
            return this;
        }

        public Builder sub(List<SubQuery> subQueryList) {
            if (!CollectionUtils.isEmpty(subQueryList)) {
                queries.addAll(subQueryList);
            }
            return this;
        }

    }

    public static Builder begin(Long startTimestamp) {
        return new Builder().begin(startTimestamp);
    }

    public static Builder begin(String start) {
        return new Builder().begin(start);
    }

    /***
     * 设置私有，不允许用户设置delete属性，会在client中通过反射来设置
     * @param delete
     */
    private void setDelete(Boolean delete) {
        this.delete = delete;
    }
}
