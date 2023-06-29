

var uploading = new Object();


function preup() {
    uploadbuttonhide();
    var files=document.getElementById('upload_file').files;
    if (files.length<1) {
        uploadbuttonshow();
        return;
    };
    var table1=document.createElement('table');
    document.getElementById('upload_div').appendChild(table1);
    table1.setAttribute('class','list-table');
    var timea=new Date().getTime();
    var i=0;
    var uploadList = setInterval(function(){
        if (i<files.length) {
            if (Object.keys(uploading).length<10) {
                getuplink(i);
                i++;
            }// else console.log(Object.keys(uploading).length);
        } else clearInterval(uploadList);
    }, 1000);
    //getuplink(i);
    function getuplink(i, r=0) {
        var file=files[i];
        var td1;
        var td2;
        if (r==0) {
            var tr1=document.createElement('tr');
            table1.appendChild(tr1);
            tr1.setAttribute('data-to',1);
            td1=document.createElement('td');
            tr1.appendChild(td1);
            td1.setAttribute('style','width:30%;word-break:break-word;');
            td1.setAttribute('id','upfile_td1_'+timea+'_'+i);
            td1.innerHTML=(file.webkitRelativePath||file.name)+'<br>'+size_format(file.size)+' ('+(i+1)+'/'+files.length+')';
            td2=document.createElement('td');
            tr1.appendChild(td2);
            td2.setAttribute('id','upfile_td2_'+timea+'_'+i);
        }
        var tdnum = timea+'_'+i;
        td1=document.getElementById('upfile_td1_'+tdnum);
        td2=document.getElementById('upfile_td2_'+tdnum);
        if (file.size>100*1024*1024*1024) {
            td2.innerHTML='<font color="red">文件过大，终止上传。</font>';
            uploadbuttonshow();
            return;
        }
        var upbigfilename = file.webkitRelativePath||file.name;
        uploading[upbigfilename] = i;
        var filemd5='';

        function getext(str) {
            strarry=str.split('.');
            if (strarry.length==1) return '';
            ext=strarry[strarry.length-1].toLowerCase();
            var reg = new RegExp(".","g");
            var a = str.replace(reg,"");
            if (a == ext) ext = "";
            else ext = "." + ext;
            return ext;
        }
        var ext = getext(file.webkitRelativePath||file.name);
        var spark = new SparkMD5.ArrayBuffer();
        var reader = new FileReader();
        var chunksize=10*1024*1024;
        var asize = 0;
        function readblob(start) {
            var end=start+chunksize;
            var blob = file.slice(start,end);
            reader.readAsArrayBuffer(blob);
        }
        readblob(asize);

        reader.onload = function(e){
            var strarry=file.name.split('.');
            td2.innerHTML='计算 md5: '+(asize*100/file.size).toFixed(2)+'%';
            var binary = this.result;
            spark.append(binary);
            asize += chunksize;
            if (asize < file.size) {
                readblob(asize);
            } else {
                filemd5 = `${encodeURIComponent(strarry[0])}_${spark.end()}`;
                td2.innerHTML='新文件名: '+filemd5;
                upbigfilename = `${filemd5}${ext}`;

                td2.innerHTML='获取上传链接 ...';
                var xhr1 = new XMLHttpRequest();
                xhr1.open("POST", '?action=upbigfile');
                // xhr1.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
                xhr1.setRequestHeader('x-requested-with','XMLHttpRequest');
                xhr1.onprogress = function(e){
                    td2.innerHTML+='.';
                }
                xhr1.onload = function(e){
                    //console.log(xhr1.status+xhr1.responseText);
                    td2.innerHTML='<font color="red">'+xhr1.responseText+'</font>';
                    if (xhr1.status==200) {
                        if (xhr1.responseText=='') {
                            getuplink(i,1);
                            return;
                        }
                        var html=JSON.parse(xhr1.responseText);
                        if (!html['uploadUrl']) {
                            td2.innerHTML='<font color="red">'+xhr1.responseText+'</font><br>';
                        } else {
                            td2.innerHTML='开始上传 ...';
                            binupfile(file,html['uploadUrl'],timea+'_'+i, upbigfilename);
                        }
                    } else {
                        if (xhr1.status==409) {
                            // td2.innerHTML='nameAlreadyExists';
                            var html=JSON.parse(xhr1.responseText);
                            td2.innerHTML=html['error']['code']+': '+html['error']['message'];

                            td2.innerHTML='文件已存在: '+ decodeURIComponent(filemd5);
                            td1.innerHTML='<div style="color:green"><a href="/'+upbigfilename+'?preview" id="upfile_a_'+tdnum+'" target="_blank">'+td1.innerHTML+'</a><br><a href="/'+upbigfilename+'" id="upfile_a1_'+tdnum+'"></a>上传完成<button onclick="CopyAllDownloadUrl(\'#upfile_a1_'+tdnum+'\');" id="upfile_cpbt_'+tdnum+'">复制链接</button></div>';
                        }
                        /*if (i<files.length-1) {
                            i++;
                            getuplink(i);
                        }*/
                        delete uploading[upbigfilename];
                    }
                }


                let formData = new FormData();
                formData.append("upbigfilename", upbigfilename);
                formData.append("filesize", file.size);
                formData.append("filelastModified", file.lastModified);
                formData.append("filemd5", filemd5);
                xhr1.send(formData);

                // xhr1.send('upbigfilename='+ upbigfilename +'&filesize='+ file.size +'&filelastModified='+ file.lastModified +'&filemd5='+ filemd5);

            }
        }

    }
    uploadbuttonshow();
}

function binupfile(file,url,tdnum,filename){
    var label=document.getElementById('upfile_td2_'+tdnum);
    var reader = new FileReader();
    var StartStr='';
    var MiddleStr='';
    var StartTime;
    var EndTime;
    var newstartsize = 0;
    if(!!file){
        var asize=0;
        var totalsize=file.size;
        var xhr2 = new XMLHttpRequest();
        xhr2.open("GET", url);
        //xhr2.setRequestHeader('x-requested-with','XMLHttpRequest');
        xhr2.send(null);
        xhr2.onload = function(e){
            if (xhr2.status==200) {
                var html = JSON.parse(xhr2.responseText);
                var a = html['nextExpectedRanges'][0];
                newstartsize = Number( a.slice(0,a.indexOf("-")) );
                StartTime = new Date();
                asize = newstartsize;
                if (newstartsize==0) {
                    StartStr='开始于:' +StartTime.toLocaleString()+'<br>' ;
                } else {
                    StartStr='上次上传'+size_format(newstartsize)+ '<br>本次开始于:' +StartTime.toLocaleString()+'<br>' ;
                }
                var chunksize=5*1024*1024; // chunk size, max 60M. 每小块上传大小，最大60M，微软建议10M
                if (totalsize>200*1024*1024) chunksize=10*1024*1024;
                function readblob(start) {
                    var end=start+chunksize;
                    var blob = file.slice(start,end);
                    reader.readAsArrayBuffer(blob);
                }
                readblob(asize);

                reader.onload = function(e){
                    var binary = this.result;
                    var xhr = new XMLHttpRequest();
                    xhr.open("PUT", url, true);
                    //xhr.setRequestHeader('x-requested-with','XMLHttpRequest');
                    bsize=asize+e.loaded-1;
                    xhr.setRequestHeader('Content-Range', 'bytes ' + asize + '-' + bsize +'/'+ totalsize);
                    xhr.upload.onprogress = function(e){
                        if (e.lengthComputable) {
                            var tmptime = new Date();
                            var tmpspeed = e.loaded*1000/(tmptime.getTime()-C_starttime.getTime());
                            var remaintime = (totalsize-asize-e.loaded)/tmpspeed;
                            label.innerHTML=StartStr+'上传 ' +size_format(asize+e.loaded)+ ' / '+size_format(totalsize) + ' = ' + ((asize+e.loaded)*100/totalsize).toFixed(2) + '% 平均速度:'+size_format((asize+e.loaded-newstartsize)*1000/(tmptime.getTime()-StartTime.getTime()))+'/s<br>即时速度 '+size_format(tmpspeed)+'/s 预计还要 '+seconds2hour(remaintime.toFixed(1));
                        }
                    }
                    var C_starttime = new Date();
                    xhr.onload = function(e){
                        if (xhr.status<500) {
                            var response=JSON.parse(xhr.responseText);
                            if (response['size']>0) {
                                // contain size, upload finish. 有size说明是最终返回，上传结束
                                if (totalsize>10*1024*1024) {
                                    var xhr3 = new XMLHttpRequest();
                                    xhr3.open("GET", '?action=del_upload_cache&filelastModified='+file.lastModified+'&filesize='+file.size+'&filename='+filename);
                                    xhr3.setRequestHeader('x-requested-with','XMLHttpRequest');
                                    xhr3.send(null);
                                    xhr3.onload = function(e){
                                        console.log(xhr3.responseText+','+xhr3.status);
                                    }
                                }
                                EndTime=new Date();
                                MiddleStr = '结束于:'+EndTime.toLocaleString()+'<br>';
                                if (newstartsize==0) {
                                    MiddleStr += '平均速度:'+size_format(totalsize*1000/(EndTime.getTime()-StartTime.getTime()))+'/s<br>';
                                } else {
                                    MiddleStr += '本次平均速度:'+size_format((totalsize-newstartsize)*1000/(EndTime.getTime()-StartTime.getTime()))+'/s<br>';
                                }
                                delete uploading[filename];
                                while (filename.indexOf('%2F')>0) filename = filename.replace('%2F', '/');
                                document.getElementById('upfile_td1_'+tdnum).innerHTML='<div style="color:green"><a href="/'+filename+'?preview" id="upfile_a_'+tdnum+'" target="_blank">'+document.getElementById('upfile_td1_'+tdnum).innerHTML+'</a><br><a href="/'+filename+'" id="upfile_a1_'+tdnum+'"></a>上传完成<button onclick="CopyAllDownloadUrl(\'#upfile_a1_'+tdnum+'\');" id="upfile_cpbt_'+tdnum+'"  >复制链接</button></div>';
                                label.innerHTML=StartStr+MiddleStr;
                                label.style.color='green';

                                // uploadbuttonshow();

                            } else {
                                if (!response['nextExpectedRanges']) {
                                    label.innerHTML='<font color="red">'+xhr.responseText+'</font><br>';
                                    delete uploading[filename];
                                } else {
                                    var a=response['nextExpectedRanges'][0];

                                    asize=Number( a.slice(0,a.indexOf("-")) );
                                    readblob(asize);
                                }
                            } } else readblob(asize);
                    }
                    xhr.send(binary);
                }
            } else {
                if (window.location.pathname.indexOf('%23')>0||filename.indexOf('%23')>0) {
                    label.innerHTML='<font color="red">目录或文件名含有#，上传失败。</font>';
                } else {
                    label.innerHTML='<font color="red">'+xhr2.responseText+'</font>';
                }
                delete uploading[filename];
                // uploadbuttonshow();
            }
        }
    }
}

