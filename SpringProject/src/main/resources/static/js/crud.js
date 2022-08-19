var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
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
            window.location.href = '/notice/all/posts/' + response;
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
        }).done(function(id) {
            alert('글이 수정되었습니다.');
            window.location.href = '/notice/all/posts/' + id;
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
            window.location.href = '/notice/all/board';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();