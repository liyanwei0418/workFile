<%--
  Created by IntelliJ IDEA.
  User: Lee-Yaoheng
  Date: 2018/1/29
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<link type="text/css" rel="stylesheet" href="${ctxStaticNew}/css/swiper.min.css">
<script type="text/javascript" src="${ctxStaticNew}/js/swiper.min.js"></script>
<style>
    main .col-xs-9 {
        width: 100% !important;
        height: 100%;
        border: none;
    }

    .banner {
        height: 100%;
    }

    .swiper-slide img {
        width: 100%;
        height: 100%;
    }

    .swiper-slide {
        position: relative;

    }

    .swiper-slide > div {
        position: absolute;
        right: 40px;
        bottom: 20px;
        border: 5px solid #ddd;
        height: 200px;
        width: 300px;
    }
</style>
<main class="">
    <div class="row" id="stu-manage">
        <main class="col-xs-9">
            <p id="headline">这是一行文字</p>
            <div class="custom-container">
                <div class="custom-main">
                    <div class="banner swiper-container">
                        <div class='swiper-wrapper' id="img_container">
                            <div class="swiper-slide" id="my-img">
                                <img src="https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2557141815,3257565825&fm=27&gp=0.jpg"
                                     alt="">
                            </div>
                        </div>
                        <!-- Add Pagination 页码-->
                        <div class="swiper-pagination"></div>
                        <!-- Add Arrows 箭头-->
                        <div class="swiper-button-next"></div>
                        <div class="swiper-button-prev"></div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</main>
<script>
    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        nextButton: '.swiper-button-next',
        prevButton: '.swiper',
        paginationClickable: true,
        spaceBetween: 30,
        centeredSlides: true,
        autoplay: 2500,
        autoplayDisableOnInteraction: false
    });
</script>
