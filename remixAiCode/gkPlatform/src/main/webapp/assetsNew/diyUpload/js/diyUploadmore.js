/*
 *	jQuery文件上传插件,封装UI,上传处理操作采用Baidu WebUploader;
 *	@Author 黑爪爪;
 */
(function ($) {

    $.fn.extend({
        /*
         *	上传方法 opt为参数配置;
         *	serverCallBack回调函数 每个文件上传至服务端后,服务端返回参数,无论成功失败都会调用 参数为服务器返回信息;
         */
        diyUpload: function (opt, serverCallBack) {
            if (typeof opt != "object") {
                alert('参数错误!');
                return;
            }

            var $fileInput = $(this);
            var $fileInputId = $fileInput.attr('id');

            //组装参数;
            if (opt.url) {
                opt.server = opt.url;
                delete opt.url;
            }

            if (opt.success) {
                var successCallBack = opt.success;
                delete opt.success;
            }

            if (opt.error) {
                var errorCallBack = opt.error;
                delete opt.error;
            }

            //迭代出默认配置
            $.each(getOption('#' + $fileInputId), function (key, value) {
                opt[key] = opt[key] || value;
            });

            if (opt.buttonText) {
                opt['pick']['label'] = opt.buttonText;
                delete opt.buttonText;
            }

            var webUploader = getUploader(opt);

            if (!WebUploader.Uploader.support()) {
                alert(' 上传组件不支持您的浏览器！');
                return false;
            }

            //传到后台的属性
            webUploader.option('formData', {
                imgPath: opt.imgPath
            });

            //绑定文件加入队列事件;
            webUploader.on('fileQueued', function (file) {
                createBox($fileInput, file, webUploader);

            });

            //进度条事件
            webUploader.on('uploadProgress', function (file, percentage) {
                var $fileBox = $('#fileBox_' + file.id);
                var $diyBar = $fileBox.find('.diyBar');
                $diyBar.show();
                percentage = percentage * 100;
                showDiyProgress(percentage.toFixed(2), $diyBar);

            });

            //全部上传结束后触发;
            webUploader.on('uploadFinished', function () {
                $fileInput.next('.parentFileBox').children('.diyButton').remove();
                var failList = $("input[name='failList']").val();
                if (failList != '') {
                    var list = failList.split(',');
                    var showfail = '';
                    for (var i in list) {
                        showfail += list[i] + '&nbsp;'
                    }
                    hintYou('400px', '200px', '提示信息', '以下图片上传失败: <br/> ' + showfail);
                }
                $('.time').hide();
            });
            //绑定发送至服务端返回后触发事件;
            webUploader.on('uploadAccept', function (object, data) {
                if (serverCallBack) serverCallBack(data);
            });

            //上传成功后触发事件;
            webUploader.on('uploadSuccess', function (file, response) {
                var $fileBox = $('#fileBox_' + file.id);
                var $diyBar = $fileBox.find('.diyBar');
                $fileBox.removeClass('diyUploadHover');
                // $diyBar.fadeOut( 1000 ,function(){
                $fileBox.children('.diySuccess').show();
                // });
                if (successCallBack) {
                    successCallBack(response);
                }
            });

            //上传失败后触发事件;
            webUploader.on('uploadError', function (file, reason) {
                var $fileBox = $('#fileBox_' + file.id);
                var $diyBar = $fileBox.find('.diyBar');
                showDiyProgress(0, $diyBar, '上传失败!');
                var err = '上传失败! 文件:' + file.name + ' 错误码:' + reason;
                var failList = $("input[name='failList']").val();
                if (failList != '') {
                    $("input[name='failList']").val(failList + ',' + file.name);
                } else {
                    $("input[name='failList']").val(file.name);
                }
                if (errorCallBack) {
                    errorCallBack(err);
                }
            });

            //选择文件错误触发事件;
            webUploader.on('error', function (code) {
                var text = '';
                switch (code) {
                    case  'F_DUPLICATE' :
                        text = '该文件已经被选择了!';
                        break;
                    case  'Q_EXCEED_NUM_LIMIT' :
                        text = '文件数量超过限制!';
                        break;
                    case  'F_EXCEED_SIZE' :
                        text = '文件大小超过限制!';
                        break;
                    case  'Q_EXCEED_SIZE_LIMIT' :
                        text = '所有文件总大小超过限制!';
                        break;
                    case 'Q_TYPE_DENIED' :
                        text = '文件类型不正确或者是空文件!';
                        break;
                    default :
                        text = '未知错误!';
                        break;
                }
                layer.msg(text);
            });
        }
    });

    //Web Uploader默认配置;
    function getOption(objId) {
        /*
         *	配置文件同webUploader一致,这里只给出默认配置.
         *	具体参照:http://fex.baidu.com/webuploader/doc/index.html
         */
        return {
            //按钮容器;
            pick: {
                id: objId,
                label: "点击选择图片"
            },
            //类型限制;
            accept: {
                title: "Images",
                extensions: "gif,jpg,jpeg,bmp,png",
                mimeTypes: "image/*"
            },
            //配置生成缩略图的选项
            thumb: {
                width: 170,
                height: 150,
                // 图片质量，只有type为`image/jpeg`的时候才有效。
                quality: 70,
                // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
                allowMagnify: false,
                // 是否允许裁剪。
                crop: true,
                // 为空的话则保留原有图片格式。
                // 否则强制转换成指定的类型。
                type: "image/jpeg"
            },
            //文件上传方式
            method: "POST",
            //服务器地址;
            server: "",
            //是否已二进制的流的方式发送文件，这样整个上传内容php://input都为文件内容
            sendAsBinary: false,
            // 开起分片上传。 thinkphp的上传类测试分片无效,图片丢失;
            chunked: true,
            // 分片大小
            chunkSize: 512 * 1024,
            //最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
            fileNumLimit: 1,
            fileSizeLimit: 5000 * 1024,
            fileSingleSizeLimit: 500 * 1024
        };
    }

    //实例化Web Uploader
    function getUploader(opt) {

        return new WebUploader.Uploader(opt);
        ;
    }

    //操作进度条;
    function showDiyProgress(progress, $diyBar, text) {

        if (progress >= 100) {
            progress = progress + '%';
            text = text || '上传完成';
        } else {
            progress = progress + '%';
            text = text || progress;
        }

        var $diyProgress = $diyBar.find('.diyProgress');
        var $diyProgressText = $diyBar.find('.diyProgressText');
        $diyProgress.width(progress);
        $diyProgressText.text(text);

    }

    //取消事件;
    function removeLi($li, file_id, webUploader) {
        webUploader.removeFile(file_id);
        if ($li.siblings('li').length <= 0) {
            $li.parents('.parentFileBox').remove();
        } else {
            $li.remove();
        }

    }

    //创建文件操作div;
    function createBox($fileInput, file, webUploader) {

        var file_id = file.id;
        var $parentFileBox = $fileInput.next('.parentFileBox');

        //添加父系容器;
        if ($parentFileBox.length <= 0) {

            var div = '<div class="parentFileBox"> \
						<ul class="fileBoxUl">\
						</ul>\
					</div>';
            $fileInput.after(div);
            $parentFileBox = $fileInput.next('.parentFileBox');
        }

        //创建按钮
        // if ( $parentFileBox.find('.diyButton').length <= 0 ) {
        //
        //     var div = '<div class="diyButton"> \
        // 			<a class="diyStart" href="javascript:void(0)">开始上传</a> \
        // 			<a class="diyCancelAll" href="javascript:void(0)">全部取消</a> \
        // 		</div>';
        //     $parentFileBox.append( div );
        //     var $startButton = $parentFileBox.find('.diyStart');
        //     var $cancelButton = $parentFileBox.find('.diyCancelAll');
        //
        //     //开始上传,暂停上传,重新上传事件;
        //     var uploadStart = function (){
        //         webUploader.upload();
        //         $startButton.text('暂停上传').one('click',function(){
        //             webUploader.stop();
        //             $(this).text('继续上传').one('click',function(){
        //                 uploadStart();
        //             });
        //         });
        //     };

        //绑定开始上传按钮;
        // $startButton.one('click',uploadStart);

        //绑定取消全部按钮;
        // $cancelButton.bind('click',function(){
        //     var fileArr = webUploader.getFiles( 'queued' );
        //     $.each( fileArr ,function( i, v ){
        //         removeLi( $('#fileBox_'+v.id), v.id, webUploader );
        //     });
        // });
        // }

        //添加子容器;
        var li = '<li id="fileBox_' + file_id + '" class="diyUploadHover"> \
					<div class="diyCancel"></div> \
					<div class="diyFileName">' + file.name + '</div>\
				</li>';

        if ($fileInput.data('flag') != 'collectiondetail') {
            $parentFileBox.children('.fileBoxUl').append(li);
        }
        //fileBoxUl


        var imgPath = ''

        if (file.type.split("/")[0] == 'image') {
            if ($fileInput.data('flag') == 'schoolCulture') {
                imgPath = "classcard/schoolculture/pic/"
            } else {
                imgPath = "classcard/classspace/pic/"
            }

        } else if (file.type.split("/")[0] == 'video') {
            if ($fileInput.data('flag') == 'schoolCulture') {
                imgPath = "classcard/schoolculture/video/"
            } else {
                imgPath = "classcard/classspace/video/"
            }
        }

        //传到后台的属性
        webUploader.option('formData', {
            imgPath: imgPath
        });
        console.log('sssssss');
        // console.log($fileInput.data('flag'));
        $('.time').show();
        webUploader.upload();

        //父容器宽度;
        var $width = $('.fileBoxUl>li').length * 140;
        var $maxWidth = $fileInput.parent().width();
        $width = $maxWidth > $width ? $width : $maxWidth;
        $parentFileBox.width($width);

        var $fileBox = $parentFileBox.find('#fileBox_' + file_id);

        //绑定取消事件;
        var $diyCancel = $fileBox.children('.diyCancel').one('click', function () {
            if ($(".time").length == 0 || $(".time").is(":hidden")) {
                //var index = $('.fileBoxUl').children().index($(this).parent());
                var delName = $(this).next().text();
                removeLi($(this).parent('li'), file_id, webUploader);
                var $picUrls = $("input[name='pic_urls']").val();
                var $fileNames = $("input[name='fileName']").val();
                var $thumbnails = $("input[name='thumbnail_flag']").val();

                var urls = $picUrls.split(',');
                var fileNames = $fileNames.split(',');
                var thumbnails = $thumbnails.split(',');

                var finalUrls = '';
                var finalFileName = '';
                var finalthumbnails = '';


                var delUrl = '';
                for (var i = 0; i < urls.length; i++) {
                    if (urls[i].split("@#@")[0] != delName) {
                        if (finalUrls != '') {
                            finalUrls += "," + urls[i];
                            finalFileName += "," + fileNames[i];
                            finalthumbnails += "," + thumbnails[i];
                        } else {
                            finalUrls += urls[i];
                            finalFileName += fileNames[i];
                            finalthumbnails += thumbnails[i];
                        }
                    } else {
                        delUrl = urls[i].split("@#@")[1];
                    }
                }
                if ($fileInput.data('flagtype') == 'schoolculturevideo') {
                    $("#schoolVideoUrl").val('');
                    $("#schoolVideoName").val('');

                } else if ($fileInput.data('flagtype') == 'schoolculturebadge') {
                    $("#schoolBadgePicUrl").val('');
                    $("#schoolBadgePicName").val('');
                } else {
                    $("input[name='pic_urls']").val(finalUrls);
                    $("input[name='fileName']").val(finalFileName);
                    $("input[name='thumbnail_flag']").val(finalthumbnails);
                }

                var id_urls = [];
                var id_url = {};
                id_url.id = "-1";
                id_url.url = delUrl;
                id_urls.push(id_url);
                var classCardId = $("input[name='classCardId']").val();
                var ctx = $("input[name='ctx']").val();

                var url = ''
                if ($fileInput.data('flag') == 'schoolCulture') {
                    url = ctx + "/classcard/deleteschoolpic"
                } else {
                    url = ctx + "/classcard/picture/multidelete"
                }
                $.ajax({
                    url: url,
                    type: "post",
                    data: {
                        id_urls: JSON.stringify(id_urls),
                        classCardId: classCardId
                    },
                    success: function () {
                        layer.msg("删除成功");
                    },
                    error: function () {
                        layer.msg("删除失败");
                    }
                })
            } else {
                layer.msg('图片上传中,请等待');
            }
        });

        if (file.type.split("/")[0] != 'image') {
            var liClassName = getFileTypeClassName(file.name.split(".").pop());
            $fileBox.addClass(liClassName);
            return;
        }

        //生成预览缩略图;
       /* webUploader.makeThumb( file, function( error, dataSrc ) {
        	if ( !error ) {
        		$fileBox.find('.viewThumb').append('<img src="'+dataSrc+'" >');
        	}
        });*/
    }

    //获取文件类型;
    function getFileTypeClassName(type) {
        var fileType = {};
        var suffix = '_diy_bg';
        fileType['pdf'] = 'pdf';
        fileType['zip'] = 'zip';
        fileType['rar'] = 'rar';
        fileType['csv'] = 'csv';
        fileType['doc'] = 'doc';
        fileType['xls'] = 'xls';
        fileType['xlsx'] = 'xls';
        fileType['txt'] = 'txt';
        fileType = fileType[type] || 'txt';
        return fileType + suffix;
    }
})(jQuery);