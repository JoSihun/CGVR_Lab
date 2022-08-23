var main = {
    init : function () {
        var _this = this;
        $('#btn-posts-save').on('click', function () {
            _this.save();
        });

        $('#btn-posts-update').on('click', function () {
            _this.update();
        });

        $('#btn-posts-delete').on('click', function () {
            _this.delete();
        });

        $('#btn-comments-save').on('click', function () {
            _this.save();
        });

        $('#btn-comments-update').on('click', function () {
            _this.update();
        });

        $('#btn-comments-delete').on('click', function () {
            _this.delete();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val(),
            categoryName: $('#categoryName').val()
        };

        $.ajax({
            type: 'POST',
            url: '/notice/all/api/posts',
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
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val(),
            categoryName: $('#categoryName').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/notice/all/api/posts/'+id,
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
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/notice/all/api/posts/'+id,
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
    }

};

main.init();