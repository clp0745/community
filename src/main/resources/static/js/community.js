function post(){
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    if (!content){
        alert("回复不能为空");
        return;
    }
    $.ajax({
        type:"POST",
        url:"/comment",
        contentType:"application/json",
        data: JSON.stringify({
            "parentId":questionId,
            "content":content,
            "type":1
        }),
        success: function (response) {
            console.log(response);
            if (response.code == 200){
                window.location.reload();
                // ("#comment_section").hide();
            } else {
                if (response.code == 2003){
                    var isAccepted = confirm(response.message);
                    if (isAccepted){
                        window.open("https://github.com/login/oauth/authorize?client_id=faf64a4734bd15b7fff7&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closable","true");
                    }
                }else {
                    alert(response.message);
                }

            }
        },
        dataType:"json"
    })
}