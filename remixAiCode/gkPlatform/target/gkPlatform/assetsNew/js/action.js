$(function(){
    /*var navOffset=$("header").offset().top;
    $(window).scroll(function(){
        var scrollPos=$(window).scrollTop();
        if(scrollPos>navOffset){
            $("header").addClass("header-scroll");
        }else{
            $("header").removeClass("header-scroll");
        }
    });*/

//账号管理
    $('header .account').hover(function(){
        $('header .account-box').show();
        $('header .account>a').addClass('hover');
        $('header .account-box li a').mouseover(function(){
            $(this).css('background','#64B746');
        })
        $('header .account-box li a').mouseout(function(){
            $(this).css('background','#54ab37');
        })
    },function(){
        $('header .account-box').hide();
        $('header .account>a').removeClass('hover');

    })
    $('header .news-center').click(function(){
        if($('.news-center-menu').css('display')=='none'){
            $('.news-center-menu').css('display','block');
        }else{
            $('.news-center-menu').css('display','none');
        }
    })


    $('.roll-manage-menu>ul>li').hover(function(){
        $(this).children('ul').show();
    },function(){
        $(this).children('ul').hide();
    })

//应用商店菜单切换
    $('.app-store-menu a').click(function(){
//        console.log($(this).attr('href'))
        $(this).addClass('active');
        $(this).parent().siblings().children().removeClass('active');
        var href=$(this).attr('data');
        var div=$('div[id='+href+']');
//        console.log(div[0].id);
        if(href==div[0].id){
            $('div[id^=app]').hide();
            $('div[id='+href+']').show();
        }
    })

    $('.app-store-target a,.app-store-type a').click(function(){
        $(this).addClass('active');
        $(this).parent().siblings().children().removeClass('active');
    })


//账户设置菜单切换
    $('.account-tabs a').click(function(){
        var data=$(this).attr('data'),anotherData=$(this).parent().siblings().children().attr('data');
        var div=$('#'+data);
        $(this).addClass('active');
        $(this).parent().siblings().children().removeClass('active');
        console.log(anotherData);
        if(data==div[0].id){
            $('div[id='+data+']').show();
            $('div[id='+anotherData+']').hide();
        }
    })


//////通知公告菜单切换
/*    $('.notice-menu ul li a').click(function(){
        $('.notice-menu ul li a').removeClass('active');
        $(this).addClass('active');
        var data=$(this).attr('data');
        var div=$('#'+data);
        $('#'+div[0].id).show();
        $('#'+div[0].id).siblings().hide();
//            $(div[0].id).siblings().hide();

    })*/

    $('#column-manage main a').click(function(){
        $('#column-manage main a').removeClass('active');
        $(this).addClass('active');
        var data=$(this).attr('data');
        var div=$('#'+data);
        $('#'+div[0].id).show();
        $('#'+div[0].id).siblings('div').hide();
    })


    $('#column-manage main a').click(function(){
        $('#column-manage main a').removeClass('active');
        $(this).addClass('active');
        var data=$(this).attr('data');
        var div=$('#'+data);
        $('#'+div[0].id).show();
        $('#'+div[0].id).siblings('div').hide();
    })


/////////学校设置菜单切换
    $('.sch-setting-menu a').click(function(){
        $('.sch-setting-menu a').removeClass('active');
        $(this).addClass('active');
        var data=$(this).attr('data');
        var sections=$('#'+data);
        $('#'+sections[0].id).show();
        $('#'+sections[0].id).siblings('section').hide();
    })

//////学生账号管理切换
    $('.stu-num-manage-menu a').click(function(){
        $('.stu-num-manage-menu a').removeClass('active');
        $(this).addClass('active');
        var data=$(this).attr('data');
        var sections=$('#'+data);
        $('#'+sections[0].id).show();
        $('#'+sections[0].id).siblings('section').hide();
    })


    //////家长账号管理切换
    $('.parent-num-manage-menu a').click(function(){
        $('.parent-num-manage-menu a').removeClass('active');
        $(this).addClass('active');
        var data=$(this).attr('data');
        var sections=$('#'+data);
        $('#'+sections[0].id).show();
        $('#'+sections[0].id).siblings('section').hide();
    });


//    打开/关闭右侧菜单栏
    $('.edit-app span').click(function(){
        $('#edit-app').animate({right:'0'},800);
    })
    $('.close').click(function(){
        $('#edit-app').animate({right:'-355px'},800);
    })

});

function activeMenu(obj,num) {
    //菜单id，菜单下序号
    $("#"+obj+" >a").addClass("active");
    num = num + 2;
    $("#"+obj+" >ul li:nth-child("+num+")>a").addClass("active");
}

function activeMenuSchool(obj,num) {
    //菜单id，菜单下序号
    $("."+obj+" a").eq(num).addClass('active').siblings().removeClass('active');
}














