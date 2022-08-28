var main = {
    init : function () {
        var _this = this;

        $('#btn-category-save').on('click', function () {
            var result = confirm("카테고리를 저장하시겠습니까?");
            if (result) {
                _this.categorySave();
            } else {
                alert("카테고리 저장이 취소되었습니다.");
            }
        });

        $('[id="btn-category-update"]').on('click', function () {
            var result = confirm("카테고리를 수정하시겠습니까?");
            if (result) {
                var categoryInfo = this.closest('tr');
                _this.categoryUpdate(categoryInfo);
            } else {
                alert("카테고리 수정이 취소되었습니다.");
            }
        });

        $('[id="btn-category-delete"]').on('click', function () {
            var result = confirm("카테고리를 삭제하시겠습니까?");
            if (result) {
                var categoryInfo = this.closest('tr');
                _this.categoryDelete(categoryInfo);
            } else {
                alert("카테고리 삭제가 취소되었습니다.");
            }
        });


        $('#btn-project-save').on('click', function () {
            var result = confirm("프로젝트명을 저장하시겠습니까?");
            if (result) {
                _this.projectSave();
            } else {
                alert("프로젝트명 저장이 취소되었습니다.");
            }
        });

        $('[id="btn-project-update"]').on('click', function () {
            var result = confirm("프로젝트명을 수정하시겠습니까?");
            if (result) {
                var projectInfo = this.closest('tr');
                _this.projectUpdate(projectInfo);
            } else {
                alert("프로젝트명 수정이 취소되었습니다.");
            }
        });

        $('[id="btn-project-delete"]').on('click', function () {
            var result = confirm("프로젝트명을 삭제하시겠습니까?");
            if (result) {
                var projectInfo = this.closest('tr');
                _this.projectDelete(projectInfo);
            } else {
                alert("프로젝트명 삭제가 취소되었습니다.");
            }
        });
    },


    categorySave : function () {
        var data = {
            categoryName : $('#categoryName').val()
        }

        $.ajax({
            type: 'POST',
            url: '/category/api',
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(data),
        }).done(function (response) {
            alert("카테고리가 추가되었습니다.");
            window.location.reload(true);
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    categoryUpdate : function (categoryInfo) {
        var id = categoryInfo.querySelector('#updateCategoryId').value;
        var data = {
            categoryName : categoryInfo.querySelector('#updateCategoryName').value,
        }

        $.ajax({
            type: 'PUT',
            url: '/category/api/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(data),
        }).done(function (response) {
            alert("카테고리가 수정되었습니다.")
            window.location.reload(true);
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    categoryDelete : function (categoryInfo) {
        var id = categoryInfo.querySelector('#deleteCategoryId').value;

        $.ajax({
            type: 'DELETE',
            url: '/category/api/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8',
        }).done(function (response) {
            alert("카테고리가 삭제되었습니다.")
            window.location.reload(true);
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },


    projectSave : function () {
        var data = {
            projectName : $('#projectName').val()
        }

        $.ajax({
            type: 'POST',
            url: '/project/api',
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(data),
        }).done(function (response) {
            alert("프로젝트명이 추가되었습니다.");
            window.location.reload(true);
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    projectUpdate : function (projectInfo) {
        var id = projectInfo.querySelector('#updateProjectId').value;
        var data = {
            projectName : projectInfo.querySelector('#updateProjectName').value,
        }

        $.ajax({
            type: 'PUT',
            url: '/project/api/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(data),
        }).done(function (response) {
            alert("프로젝트명이 수정되었습니다.")
            window.location.reload(true);
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    projectDelete : function (projectInfo) {
        var id = projectInfo.querySelector('#deleteProjectId').value;

        $.ajax({
            type: 'DELETE',
            url: '/project/api/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8',
        }).done(function (response) {
            alert("프로젝트명이 삭제되었습니다.")
            window.location.reload(true);
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
};

main.init();