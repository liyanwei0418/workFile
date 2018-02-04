<%@ include file="../common/headerXf.jsp" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>区级人事管理</title>
    <script src="${ctxStaticNew}/js/jquery.min.js"></script>
    <script src="${ctxStaticNew}/js/highcharts.js"></script>
    <script src="${ctxStaticNew}/js/highcharts-more.js"></script>
    <style>
        #header_birt {
            height: 90px;
            width: 1170px;
            margin: 0 auto;
            box-shadow: 0 0 3px #ddd;
            line-height: 90px;
        }

        #header_birt > div {
            width: 32%;
            display: inline-block;
        }

        #header_birt > div:after {
            content: '';
            display: table;
            clear: both;
        }

        #header_birt > div {
            font-size: 14px;
            color: #666;
        }

        #header_birt > div span {
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

        #main_birt {
            width: 1170px;
            margin: 0 auto;
            overflow: hidden;
            padding-top: 20px;
        }

        #main_birt > div {
            width: 32%;
            border: 1px solid #ddd;
        }

        #main_birt > div header {
            background: #e1e2e1;
            color: #333;
            font-size: 14px;
            padding: 10px 15px;
        }

        #main_birt #political {
            float: right;
        }

        #main_birt #gender {
            float: left;
        }

        #main_birt #education {
            margin: 0 auto;
        }

        #main_birt .fo-info {
            color: #525252;
            font-size: 13px;
            padding: 0 28px;
            height: 175px;
        }

        #main_birt .fo-info p {
            height: 45px;
            line-height: 45px;
        }

        #main_birt .s-bgcolor {
            width: 20px;
            height: 20px;
            margin: 0 10px 3px 0;
            vertical-align: middle;
        }

        #main_birt .s-number {
            float: right;
            margin-right: 10px;
        }
    </style>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
</head>
<body>
<%@ include file="../common/sonHead/qujiRenShiHead.jsp" %>
<main class="container">
    <div class="container" id="header_birt">
        <div class="statis-count">
            <span class="divider"></span>
            <span class="count-type">区域人员统计</span>
            <span class="count"><b>${birt.countAll}</b>&nbsp;&nbsp;人</span>
        </div>
        <div class="block-count">
            <span class="divider">其<br>中</span>
            <span class="count-type">区教育局</span>
            <span class="count"><b>${birt.countp}</b>&nbsp;&nbsp;人</span>
        </div>
        <div class="school-count">
            <span class="divider"></span>
            <span class="count-type">在校教职工</span>
            <span class="count"><b>${birt.countAll - birt.countp}</b>&nbsp;&nbsp;人</span>
        </div>
    </div>
    <main id="main_birt">
        <div id="gender">
            <header>区域职工性别分布</header>
            <div id="gender-chart" style="width:100%;height:300px;margin:0 auto;"></div>
        </div>
        <div id="political">
            <header>区域职工政治面貌统计</header>
            <div id="political-chart" style="width:100%;height:300px;margin:0 auto;"></div>

        </div>
        <div id="education">
            <header>区域职工学历统计</header>
            <div id="education-chart" style="width:100%;height:300px;margin:0 auto;"></div>
        </div>
    </main>
</main>
<script>
    activeMenu("tongji", 0);

    $('#gender-chart').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: ''
        },
        tooltip: {
            headerFormat: '<span style="font-size:12px">{point.key}</span><table><br/>',
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
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
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'purple'
                    }
                },
                showInLegend: true
            }
        },
        series: [{
            name: '性别',
            colorByPoint: true,
            data: [{
                name: '男',
                y: ${birt.man},
                color: '#AAC458'
            }, {
                name: '女',
                y: ${birt.woman},
                color: '#FED98E',
            }]
        }],
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
            itemStyle: {color: '#676767', fontSize: '12px', fontWeight: 'normal', fontFamily: 'microsoft Yahei'}
        }
    });


    $('#education-chart').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
        },
        title: {
            floating: true,
            text: ''
        },
        tooltip: false,
        plotOptions: {
            pie: {
                size: '70%',
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                },
                showInLegend: true,
                point: {
                    events: {
                        mouseOver: function (e) {
                            chart.setTitle({
                                text: '<div style="position:relative;"><b style="font-size:14px;color:' + e.target.color + '">' + e.target.y + ' %' + '</b><br/>\t' + '<span style="font-size:12px;">' + e.target.name + '</span></div>'
                            });
                        }
                    }
                },
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
            y: 8,
            enabled: true,
            itemStyle: {color: '#676767', fontSize: '12px', fontWeight: 'normal', fontFamily: 'microsoft Yahei'}
        },
        series: [{
            type: 'pie',
            innerSize: '40%',
            name: '学历',
            data: [
                {name: '小学', y: Number("${birt.xl1c}"), color: '#AAC458'},
                {name: '初中', y: Number("${birt.xl2c}"), color: '#8DBCEB'},
                {name: '中职/高中', y: Number("${birt.xl3c}"), color: '#F18E85'},
                {name: '专科', y: Number("${birt.xl4c}"), color: '#FED98E'},
                {name: '本科', y: Number("${birt.xl5c}"), color: '#A291B4'},
                {name: '硕士研究生', y: Number("${birt.xl6c}"), color: '#F9BDD1'},
                {name: '博士研究生', y: Number("${birt.xl7c}"), color: '#B39D89'},
            ]
        }],
    }, function (c) {
        // 环形图圆心
        var centerY = c.series[0].center[1];
        //                titleHeight = parseInt(c.title.styles.fontSize);
        c.setTitle({
            y: centerY
        });
        chart = c;
    });


    $('#political-chart').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            //spacing : [100, 0 , 40, 0]
        },
        title: {
            floating: true,
            text: ''
        },
        tooltip: false,
        plotOptions: {
            pie: {
                size: '70%',
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                },
                showInLegend: true,
                point: {
                    events: {
                        mouseOver: function (e) {  // 鼠标滑过时动态更新标题
                            chart1.setTitle({
                                text: '<b style="font-size:14px;color:' + e.target.color + '">' + e.target.y + ' %' + '</b><br/>\t' + '<span style="font-size:12px;">' + e.target.name + '</span>'
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
            itemStyle: {color: '#676767', fontSize: '12px', fontWeight: 'normal', fontFamily: 'microsoft Yahei'}
        },
        series: [{
            type: 'pie',
            innerSize: '40%',
            name: ' ',
            data: [
                {name: '中共党员', y: Number("${birt.zzmm2c}"), color: '#AAC458'},
                {name: '共青团员', y: Number("${birt.zzmm3c}"), color: '#8DBCEB'},
                {name: '群众', y: Number("${birt.zzmm4c}"), color: '#F18E85'},
                {name: '其他', y: Number("${birt.zzmm1c}"), color: '#FED98E'}
            ]
        }]
    }, function (a) {
        // 环形图圆心

        var centerY1 = a.series[0].center[1];
//          titleHeight1 = parseInt(a.title.styles.fontSize);
        a.setTitle({
            y: centerY1
        });
        chart1 = a;
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