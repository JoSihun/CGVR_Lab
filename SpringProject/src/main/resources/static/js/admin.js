var main = {
    init : function () {
        var _this = this;
        $('#admin-grant').on('click', function () {
            _this.grant();
        });
        $('#admin-revoke').on('click', function () {
            _this.revoke();
        });
    },
    grant : function () {
        var data = {
            contact: $('#contact').val(),
            email: $('#email').val(),
            korName: $('#korName').val(),
            role: $('#role').val(),
            userId: $('#userId').val()
        };
        console.log(data.userId);
        if(data.userId) {
            $.ajax({
                type: 'POST',
                url: '/admin/api',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('관리자가 등록되었습니다.');
                window.location.href = '/admin';
            }).fail(function (error) {
                alert('등록할 관리자의 학번이 이미 존재합니다.');
            });
        }
        else {
            alert('학번은 필수 입력사항입니다.');
        }
    },
    revoke : function (id) {

        const con_check = confirm('삭제하시겠습니까?');
        if(con_check) {
            $.ajax({
                type: 'DELETE',
                url: '/admin/api/' + id,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8'
            }).done(function () {
                alert('관리자가 삭제되었습니다.');
                window.location.href = '/admin';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    }
};

main.init();