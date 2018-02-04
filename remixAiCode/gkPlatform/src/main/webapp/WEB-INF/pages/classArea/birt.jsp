<%@ include file="../common/headerXf.jsp" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>统计报表</title>
    <script src="${ctxStaticNew}/js/jquery.min.js"></script>
    <script src="${ctxStaticNew}/js/highcharts.js"></script>
    <script src="${ctxStaticNew}/js/highcharts-more.js"></script>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
    <style type="text/css">
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        span {
            display: inline-block;
        }

        ul, li {
            list-style: none;
        }

        #headerxj {
            height: 90px;
            width: 1170px;
            margin: 0 auto;
            box-shadow: 0 0 3px #ddd;
            line-height: 90px;
        }

        #headerxj > div {
            width: 33%;
            display: inline-block;
        }

        #headerxj > div:after {
            content: '';
            display: table;
            clear: both;
        }

        #headerxj > div {
            font-size: 14px;
            color: #666;
        }

        #headerxj > div span {
            display: inline-block
        }

        .count-type {
            margin: 0 44px;
        }

        .count b {
            font-size: 22px;
            color: #333;
        }

        .block-count .divider {
            line-height: 30px;
            margin-top: 15px;
            float: left;
            text-align: center;
            height: 60px;
            width: 66px;
            border-right: 1px solid #dadada;
        }

        #main {
            width: 1170px;
            margin: 0 auto;
            overflow: hidden;
            padding-top: 20px;
        }

        #main > div {
            width: 32%;
            border: 1px solid #ddd;
        }

        #main > div headerxj {
            background: #e1e2e1;
            color: #333;
            font-size: 14px;
            padding: 10px 15px;
        }

        #main #political {
            float: right;
        }

        #main #gender {
            float: left;
        }

        #main #education {
            margin: 0 auto;
        }

        #main .fo-info {
            color: #525252;
            font-size: 13px;
            padding: 0 28px;
            height: 175px;
        }

        #main .fo-info p {
            height: 45px;
            line-height: 45px;
        }

        #main .s-bgcolor {
            width: 20px;
            height: 20px;
            margin: 0 10px 3px 0;
            vertical-align: middle;
        }

        #main .s-number {
            float: right;
            margin-right: 10px;
        }

        #headerxj > div {
            width: auto;
            display: inline-block;
        }

        #headerxj .statis-count {
            width: 35%;
        }

        .chart-warp-contiant > div {
            display: inline-block;
        }

        #main > div {
            width: auto;
            border: none;
            margin-bottom: 20px;
        }

        #main > div > div {
            border: 1px solid #ddd;
        }

        #main #stu-gender {
            width: 50%;
        }

        #main #stu-source {
            width: 48%;
            float: right;
        }
    </style>

</head>
<body>
<%@ include file="../common/sonHead/qujiXuejiHead.jsp" %>
<main class="container">
    <div id="headerxj">
        <div class="statis-count">
            <span class="divider"></span>
            <span class="count-type">区域学生统计</span>
            <span class="count"><b>${personCount.allStu}</b>&nbsp;&nbsp;人</span>
        </div>
        <div class="block-count">
            <span class="divider">其<br>中</span>
            <span class="count-type">小学</span>
            <span class="count"><b>${personCount.小学}</b>&nbsp;&nbsp;人</span>
        </div>
        <div class="school-count">
            <span class="divider"></span>
            <span class="count-type">初中</span>
            <span class="count"><b>${personCount.初中}</b>&nbsp;&nbsp;人</span>
        </div>
        <div class="school-count">
            <span class="divider"></span>
            <span class="count-type">高中</span>
            <span class="count"><b>${personCount.高中}</b>&nbsp;&nbsp;人</span>
        </div>
    </div>
    <main id="main">
        <div class="chart-warp-contiant">
            <div id="stu-gender">
                <header>学段性别比例</header>
                <div id="stu-gender-chart" style="width:90%;height:400px;margin:0 auto; padding-top: 50px;">
                </div>
            </div>
            <div id="stu-source">
                <header>来源地区</header>
                <div id="stu-source-chart" style="width:90%;height:400px;margin:0 auto;"></div>
            </div>
        </div>
        <div>
            <div id="stu-number">
                <header>班级人数情况</header>
                <div id="stu-number-chart" style="width:90%;height:500px;margin:0 auto;padding-top: 50px;">
                </div>
            </div>
        </div>
    </main>
</main>
<script>

    activeMenu("birtMenu", 0);
    $('#stu-gender-chart').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: ''
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: [
                <c:forEach items="${birtGender}" var="gender">
                "${gender.sectionName}",
                </c:forEach>
            ],
            crosshair: true
        },

        yAxis: {
            min: 0,
            title: {
                text: '人数（个）'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}:</td>' +
            '<td style="padding:0"><b>{point.y:.if} 个</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        credits: {
            enabled: false
        },
        legend: {
            enabled: true,
            itemStyle: {
                color: '#676767',
                fontSize: '12px',
                fontWeight: 'normal',
                fontFamily: 'microsoft Yahei'
            }
        },
        plotOptions: {
            column: {
                pointPadding: 0,
                borderWidth: 0
            }
        },
        series: [{
            name: '男',
            color: '#AAC458',
            data: [
                <c:forEach items="${birtGender}" var="gender">
                Number("${gender.man}"),
                </c:forEach>
            ]
        }, {
            name: '女',
            color: '#F18E85',
            data: [
                <c:forEach items="${birtGender}" var="gender">
                Number("${gender.woman}"),
                </c:forEach>
            ]
        }]
    });


    $('#stu-source-chart').highcharts({
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false,
                },
                title: {
                    floating: true,
                    text: '',
                },
                tooltip: {
                    pointFormat: '{point.name}: <b>{point.percentage:.1f}%</b>'
                },
                plotOptions: {
                    pie: {
                        size: '70%',
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: false,
                            format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black',
                            }
                        },
                        showInLegend: true,
                        point: {
                            events: {
                                mouseOver: function (e) {
                                    chart1.setTitle({
                                        text: '<b style="font-size:24px;color:' + e.target.color + '">' + e.target.y + ' %' + '</b><br/>\t' + '<span style="font-size:12px;">' + e.target.name + '</span>'
                                    });
                                }
                            }
                        }
                    }
                },

                credits: {enabled: false},
                legend: {
                    backgroundColor: '#FFFFFF',
                    floating: true,
                    align: 'center',
                    verticalAlign: 'bottom',
                    fontWeight: 'normal',
                    x: 0,
                    y: 0,
                    enabled: true,
                    itemStyle: {
                        color: '#676767',
                        fontSize: '12px',
                        fontWeight: 'normal',
                        fontFamily: 'microsoft Yahei'
                    }
                },
                series: [{
                    type: 'pie',
                    innerSize: '40%',
                    name: ' ',
                    data: [
                        {name: '区县内', y: divide("${birtLydq.qn}", "${birtLydq.allCount}", 4) * 100, color: '#AAC458'},
                        {name: '省市内', y: divide("${birtLydq.sn}", "${birtLydq.allCount}", 4) * 100, color: '#8DBCEB'},
                        {name: '省市外', y: divide("${birtLydq.sw}", "${birtLydq.allCount}", 4) * 100, color: '#F18E85'}
                    ]
                }]
            },
            function (a) {
                // 环形图圆心
                var centerY1 = a.series[0].center[1];
                a.setTitle({
                    y: centerY1
                });
                chart1 = a;
            });


    $("#stu-number-chart").highcharts({
        chart: {
            type: 'spline'
        },
        title: {
            text: '',
        },
        xAxis: {
            categories: [
                <c:forEach items="${birtLine}" var="lineC">
                "${lineC.indexName}",
                </c:forEach>
            ]
        },
        yAxis: {
            title: {
                text: '人数（个）',
            },
            labels: {
                formatter: function () {
                    return this.value;
                }
            }
        },
        tooltip: {
            crosshairs: true,
            shared: true
        },
        plotOptions: {
            spline: {
                marker: {
                    radius: 4,
                    lineColor: '#666',
                    lineWidth: 1
                }
            }
        },
        credits: {
            enabled: false
        },
        legend: {
            fontWeight: 'normal',
            enabled: true,
            itemStyle: {
                color: '#676767',
                fontSize: '12px',
                fontWeight: 'normal',
                fontFamily: 'microsoft Yahei'
            }
        },
        series: [{
            name: '最高人数',
            color: '#AAC458',
            data: [
                <c:forEach items="${birtLine}" var="lineC">
                Number("${lineC.maxP}"),
                </c:forEach>
            ]
        }, {
            name: '平均人数',
            color: '#8DBCEB',
            data: [
                <c:forEach items="${birtLine}" var="lineC">
                Number("${lineC.avgP}"),
                </c:forEach>
            ]
        }, {
            name: '最低人数',
            color: '#F18E85',
            data: [
                <c:forEach items="${birtLine}" var="lineC">
                Number("${lineC.minP}"),
                </c:forEach>
            ]
        }]
    });


    function divide(left, right, length) {
        var digit = left / right;
        if (length <= 0) return Math.round(digit);
        digit = Math.round(digit * Math.pow(10, length)) / Math.pow(10, length);
        return digit;
    }
</script>
</body>
</html>