var main = {
    init : function () {
        var _this = this;
        $('#btn-posts-save').on('click', function () {
            var result = confirm("저장하시겠습니까?");
            if (result) {
                _this.postsSave();
            }
            else{
                alert("저장이 취소되었습니다.");
            }
        });

        $('#btn-posts-update').on('click', function () {
            var result = confirm("저장하시겠습니까?");
            if (result) {
                _this.postsUpdate();
            }
            else {
                alert("저장이 취소되었습니다.");
            }
        });

        $('#btn-posts-delete').on('click', function () {
            var result = confirm("삭제하시겠습니까?");
            if (result) {
                _this.postsDelete();
            }
            else{
                alert("삭제가 취소되었습니다.");
            }
        });

        $('#btn-comments-save').on('click', function () {
            var result = confirm("저장하시겠습니까?");
            if (result) {
                _this.commentsSave();
            }
            else{
                alert("저장이 취소되었습니다.");
            }
        });

        $('[id="btn-comments-update"]').on('click', function () {
            var commentsInfo = this.closest('li');
            var result = confirm("저장하시겠습니까?");
            if (result) {
                _this.commentsUpdate(commentsInfo);
            }
            else{
                alert("저장이 취소되었습니다.");
            }
        });
''
        $('[id="btn-comments-delete"]').on('click', function () {
            var commentsInfo = this.closest('li');
            var result = confirm("삭제하시겠습니까?");
            if (result) {
                _this.commentsDelete(commentsInfo);
            }
            else{
                alert("삭제가 취소되었습니다.");
            }
        });


        $('#btn-projectName-delete').on('click', function () {
            var result = confirm("삭제하시겠습니까?");
            if (result) {
                _this.projectNameDelete();
            }
            else{
                alert("삭제가 취소되었습니다.");
            }
        });
    },
    postsSave : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val(),
            categoryName: $('#categoryName').val(),
            projectName: $('#projectName').val()
        };

        $.ajax({
            type: 'POST',
            url: '/posts/api',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
        }).done(function(response) {
            alert('글이 등록되었습니다.');
            var arrayLink = document.location.href.split('/').slice(3, -1);
            var stringLink = arrayLink.join('/');
            var redirectUrl = '/' + stringLink + '/' + response;
            console.log(redirectUrl);
            window.location.href = redirectUrl;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    postsUpdate : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val(),
            projectName: $('#projectName').val(),
            categoryName: $('#categoryName').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/posts/api/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
        }).done(function(response) {
            alert('글이 수정되었습니다.');
            var arrayLink = document.location.href.split('/').slice(3, -2);
            var stringLink = arrayLink.join('/');
            var redirectUrl = '/' + stringLink + '/' + response;
            console.log(redirectUrl);
            window.location.href = redirectUrl;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    postsDelete : function () {
        var id = $('#postsId').val();

        $.ajax({
            type: 'DELETE',
            url: '/posts/api/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
        }).done(function() {
            alert('글이 삭제되었습니다.');
            var arrayLink = document.location.href.split('/').slice(3, -2);
            var stringLink = arrayLink.join('/');
            var redirectUrl = '/' + stringLink + '/board';
            console.log(redirectUrl);
            window.location.href = redirectUrl;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    commentsSave : function () {
        var data = {
            author: $('#author').val(),
            content: $('#content').val(),
            postsId: $('#postsId').val(),
        };
        var postsId = $('#postsId').val();

        $.ajax({
            type: 'POST',
            url: '/posts/api/' + postsId +  '/comments',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
        }).done(function() {
            alert('댓글이 등록되었습니다.');
            window.location.reload(true);
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    commentsUpdate : function (commentsInfo) {
        var data = {
            content: commentsInfo.querySelector('#updateContent').value,
        };
        var id = commentsInfo.querySelector('#commentsId').value;

        $.ajax({
            type: 'PUT',
            url: '/posts/api/comments/' + id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
        }).done(function(response) {
            alert('댓글이 수정되었습니다.');
            window.location.reload(true);
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    commentsDelete : function (commentsInfo) {
        var id = commentsInfo.querySelector('#commentsId').value;
        console.log(id);

        $.ajax({
            type: 'DELETE',
            url: '/posts/api/comments/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
        }).done(function() {
            alert('댓글이 삭제되었습니다.');
            window.location.reload(true);
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },


    projectNameDelete : function () {
        var projectName = $("#projectName").val();
        console.log(id);

        $.ajax({
            type: 'DELETE',
            url: '/projects/api/'+projectName,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
        }).done(function() {
            alert('프로젝트명이 삭제되었습니다.');
            window.location.reload(true);
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

};

main.init();