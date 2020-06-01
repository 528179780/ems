(function() {

    var db = {

        loadData: function(filter) {
            return $.grep(this.clients, function(client) {
                return (!filter.CourseName || client.CourseName.indexOf(filter.CourseName) > -1)
                    && (!filter.TeacherName || client.TeacherName.indexOf(filter.TeacherName) > -1)
                    && (!filter.CourseNature || client.CourseNature === filter.CourseNature)
                    && (filter.Credit === undefined || client.Credit === filter.Credit)
                    && (filter.selected === undefined || client.selected === filter.selected);
            });
        },

        insertItem: function(insertingClient) {
            this.clients.push(insertingClient);
        },

        updateItem: function(updatingClient) { },

        deleteItem: function(deletingClient) {
            var clientIndex = $.inArray(deletingClient, this.clients);
            this.clients.splice(clientIndex, 1);
        }

    };

    window.db = db;


    db.CourseNature = [
        { Name: "", Id: 0 },
        { Name: "公共必修", Id: 1 },
        { Name: "通识教育选修", Id: 2 },
        { Name: "专业选修", Id: 3 },
    ];

    db.clients = [
        {
            "CourseName": "大学体育",
            "TeacherName": "牟骏睿",
            "CourseNature": 1,
            "Credit": "1.0",
            "selected": true
        },
        {
            "CourseName": "中外美术评析与欣赏[智慧树 在线自学课程]",
            "TeacherName": "李丽华",
            "CourseNature": 2,
            "Credit": "1.0",
            "Married": true
        },
        {
            "CourseName": "中外美术评析与欣赏[智慧树 在线自学课程]",
            "TeacherName": "李丽华",
            "CourseNature": 2,
            "Credit": "1.0",
            "Married": true
        },
        {
            "CourseName": "中外美术评析与欣赏[智慧树 在线自学课程]",
            "TeacherName": "李丽华",
            "CourseNature": 2,
            "Credit": "1.0",
            "Married": true
        },
        {
            "CourseName": "中外美术评析与欣赏[智慧树 在线自学课程]",
            "TeacherName": "李丽华",
            "CourseNature": 2,
            "Credit": "1.0",
            "Married": true
        },

    ];

    db.users = [
        {
            "ID": "x",
            "Account": "A758A693-0302-03D1-AE53-EEFE22855556",
            "Name": "sufu",
            "RegisterDate": "2020-05-11T22:55:52-07:00"
        },
        {
            "Account": "D89FF524-1233-0CE7-C9E1-56EFF017A321",
            "Name": "ural",
            "RegisterDate": "2020-05-11T05:59:55-08:00"
        },
        {
            "Account": "06FAAD9A-5114-08F6-D60C-961B2528B4F0",
            "Name": "haonan chen",
            "RegisterDate": "2020-05-11T09:17:49-07:00"
        },
     ];

}());